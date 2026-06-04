package com.pao.project.etapa2.service;

import com.pao.project.etapa2.model.Appointment;
import com.pao.project.etapa2.model.Consultation;
import com.pao.project.etapa2.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClinicService {

    private static ClinicService instance;

    private ClinicService() {}

    public static ClinicService getInstance() {
        if (instance == null) instance = new ClinicService();
        return instance;
    }

    private Connection getConn() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    // =========================================================
    // Explicit Transaction
    // =========================================================

    public void saveAppointmentUpdateWithBilling(Appointment app, Consultation consultation) throws SQLException, IOException {
        Connection conn = getConn();
        conn.setAutoCommit(false);

        try {
            // --- SQL 1: Check appointment status ---
            String checkSql = "SELECT current_state FROM appointments WHERE id = ?";
            try (PreparedStatement checkPs = conn.prepareStatement(checkSql)) {
                checkPs.setLong(1, app.getId());
                try (ResultSet rs = checkPs.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("Appointment with ID=" + app.getId() + " does not exist.");
                    }

                    String dbState = rs.getString("current_state");
                    if ("CANCELED".equals(dbState)) {
                        throw new SQLException("Cannot process financial billing for a CANCELED appointment.");
                    }
                    if ("PAID".equals(dbState)) {
                        throw new SQLException("Transaction rejected: This appointment has already been finalized and PAID.");
                    }
                }
            }

            // --- SQL 2: Update status to PAID ---
            String updateSql = "UPDATE appointments SET current_state = 'PAID', doctor_note = ? WHERE id = ?";
            try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
                updatePs.setString(1, app.getDoctorNote());
                updatePs.setLong(2, app.getId());
                updatePs.executeUpdate();
            }

            // --- SQL 3: Insert invoice record ---
            String insertSql = "INSERT INTO consultations (appointment_id, pet_id, diagnosis, price, date_time) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertPs = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                insertPs.setLong(1, consultation.getAppointmentId());
                insertPs.setLong(2, consultation.getPet().getId());
                insertPs.setString(3, consultation.getDiagnosis());
                insertPs.setDouble(4, consultation.getPrice());
                insertPs.setString(5, consultation.getDateTime().toString());
                insertPs.executeUpdate();

                try (ResultSet keys = insertPs.getGeneratedKeys()) {
                    if (keys.next()) {
                        long consultationId = keys.getLong(1);
                        consultation.setId(consultationId);
                    }
                }
            }
            conn.commit();
            System.out.println("[TX] saveAppointmentUpdateWithBilling successfully committed.");

        } catch (SQLException e) {
            conn.rollback();
            System.out.println("[TX] saveAppointmentUpdateWithBilling — rollback due to: " + e.getMessage());
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    // =========================================================================
    // 3 SQL with JOIN
    // =========================================================================

    /**
     * JOIN #1: Get schedule for a specific vet
     */
    public List<String> findAppointmentsByVetId(long vetId) throws SQLException, IOException {
        String sql = """
                SELECT a.id            AS app_id,
                       p.name          AS pet_name,
                       v.last_name     AS vet_name,
                       a.date_time     AS schedule_time,
                       a.current_state AS app_state
                FROM appointments a
                JOIN pets p ON a.pet_id = p.id
                JOIN vets v ON a.vet_id = v.id
                WHERE a.vet_id = ?
                ORDER BY a.date_time ASC
                """;
        List<String> results = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setLong(1, vetId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String rawTime = rs.getString("schedule_time");
                    String cleanTime;
                    if (rawTime.contains("T")) {
                        cleanTime = rawTime.replace("T", " ").substring(0, 16);
                    } else {
                        cleanTime = rawTime;
                    }

                    results.add(String.format("ID: %d | Pet: %s | Vet: Dr. %s | Time: %s | State: %s",
                            rs.getLong("app_id"),
                            rs.getString("pet_name"),
                            rs.getString("vet_name"),
                            cleanTime,
                            rs.getString("app_state")));
                }
            }
        }
        return results;
    }

    /**
     * JOIN #2: Get revenue stats by service type
     */
    public List<String> getServiceStatistics() throws SQLException, IOException {
        String sql = """
            SELECT a.service_type                      AS service,
                   COUNT(DISTINCT a.id)                AS completed_visits,
                   SUM(c.price)                        AS total_revenue,
                   GROUP_CONCAT(DISTINCT v.last_name)  AS active_vets
            FROM appointments a
            JOIN consultations c ON a.id = c.appointment_id  
            JOIN vets v          ON a.vet_id = v.id
            WHERE a.current_state = 'PAID'
            GROUP BY a.service_type
            ORDER BY total_revenue DESC
            """;

        List<String> results = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            results.add("\n--- Service Usage (Completed Visits) ---");
            while (rs.next()) {
                String vetsList = rs.getString("active_vets");
                String doctors;
                if (vetsList != null && !vetsList.isEmpty()) {
                    doctors = "Vets: Dr. " + vetsList.replace(",", ", Dr. ");
                } else {
                    doctors = "Vets: None";
                }

                results.add(String.format("%s: %d  | Total Revenue: %.2f RON",
                        rs.getString("service"),
                        rs.getInt("completed_visits"),
                        rs.getDouble("total_revenue")));
                results.add("  " + doctors);
            }
        }
        return results;
    }

    /**
     * JOIN #3: Get complete invoicing balance per pet
     */
    public List<String> getPetInvoice() throws SQLException, IOException {
        String sql = """
                SELECT p.name          AS pet_name,
                       p.specie        AS pet_specie,
                       COUNT(c.id)     AS total_consultations,
                       COALESCE(SUM(c.price), 0.0) AS total_spent
                FROM pets p
                LEFT JOIN consultations c ON p.id = c.pet_id
                GROUP BY p.id, p.name, p.specie
                ORDER BY total_spent DESC
                """;
        List<String> results = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                results.add(String.format("Patient: %s (%s) | Consultations: %d | Total Invoiced: %.2f RON",
                        rs.getString("pet_name"), rs.getString("pet_specie"), rs.getInt("total_consultations"), rs.getDouble("total_spent")));
            }
        }
        return results;
    }


}
