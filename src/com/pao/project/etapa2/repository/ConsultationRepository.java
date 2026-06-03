package com.pao.project.etapa2.repository;

import com.pao.project.etapa2.model.*;
import com.pao.project.etapa2.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsultationRepository implements Repository<Consultation, Long> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private Connection getConn() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private Consultation mapRow(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        long appointmentId = rs.getLong("appointment_id");
        long petId = rs.getLong("pet_id");
        String diagnosis = rs.getString("diagnosis");
        double price = rs.getDouble("price");
        LocalDateTime dateTime = LocalDateTime.parse(rs.getString("date_time"), formatter);

        Pet petShell = new Pet(petId, "", "", null);
        Consultation consultation = new Consultation(appointmentId, petShell, diagnosis, price);
        consultation.setId(id);

        return consultation;
    }

    @Override
    public void save(Consultation c) throws SQLException {
        String sql = "INSERT INTO consultations (appointment_id, pet_id, diagnosis, price, date_time) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, c.getAppointmentId());
            ps.setLong(2, c.getPet().getId());
            ps.setString(3, c.getDiagnosis());
            ps.setDouble(4, c.getPrice());
            ps.setString(5, c.getDateTime().format(formatter));
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    c.setId(keys.getLong(1));
                }
            }

        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Optional<Consultation> findById(Long id) throws SQLException {
        String sql = "SELECT id, appointment_id, pet_id, diagnosis, price, date_time FROM consultations WHERE id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (IOException e) {
            throw new SQLException(e);
        }

    }

    @Override
    public List<Consultation> findAll() throws SQLException {
        String sql = "SELECT id, appointment_id, pet_id, diagnosis, price, date_time FROM consultations ORDER BY id ASC";
        List<Consultation> list = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (IOException e){
            throw new SQLException(e);
        }
        return list;


    }

    @Override
    public void update(Consultation c) throws SQLException {
        throw new UnsupportedOperationException("Consultation entities are immutable.");
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM consultations WHERE id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    public void deleteByPetId(long petId) throws SQLException {
        String sql = "DELETE FROM consultations WHERE pet_id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setLong(1, petId);
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}
