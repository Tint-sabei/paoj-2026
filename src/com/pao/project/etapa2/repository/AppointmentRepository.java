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

public class AppointmentRepository implements Repository<Appointment, Long> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private Connection getConn() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private Appointment mapRow(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        long petId = rs.getLong("pet_id");
        long vetId = rs.getLong("vet_id");

        ServiceType serviceType = ServiceType.valueOf(rs.getString("service_type"));
        State currentState = State.valueOf(rs.getString("current_state"));
        LocalDateTime dateTime = LocalDateTime.parse(rs.getString("date_time"), formatter);
        String clientReason = rs.getString("client_reason");
        String doctorNote = rs.getString("doctor_note");
        Pet petShell = new Pet(petId, "", "", null);
        Vet vetShell = new Vet(vetId, "", "");

        return new Appointment(id, petShell, vetShell, serviceType, currentState, dateTime, clientReason, doctorNote);
    }

    @Override
    public void save(Appointment app) throws SQLException {
        String sql = "INSERT INTO appointments (pet_id,vet_id, service_type, current_state, " +
                "date_time, client_reason, doctor_note) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, app.getPet().getId());
            ps.setLong(2, app.getVet().getId());
            ps.setString(3, app.getService().toString());
            ps.setString(4, app.getState().toString());
            ps.setString(5, app.getDateTime().format(formatter));
            ps.setString(6, app.getClientReason());

            String noteValue = app.getDoctorNote();
            if (noteValue == null) { noteValue = ""; }
            ps.setString(7, noteValue);

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    app.setId(keys.getLong(1));
                }
            }

        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Optional<Appointment> findById(Long id) throws SQLException {
        String sql = "SELECT id, pet_id, vet_id, service_type, current_state, " +
                "date_time, client_reason, doctor_note FROM appointments WHERE id = ?";
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
    public List<Appointment> findAll() throws SQLException {
        String sql = "SELECT id, pet_id, vet_id, service_type, current_state, " +
                "date_time, client_reason, doctor_note FROM appointments ORDER BY date_time ASC";
        List<Appointment> list = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (IOException e) {
            throw new SQLException(e);
        }
        return list;
    }

    @Override
    public void update(Appointment app) throws SQLException {
        String sql = "UPDATE appointments SET pet_id = ?, vet_id = ?, service_type = ?, " +
                "current_state = ?, date_time = ?, client_reason = ?, doctor_note = ? WHERE id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setLong(1, app.getPet().getId());
            ps.setLong(2, app.getVet().getId());
            ps.setString(3, app.getService().toString());
            ps.setString(4, app.getState().toString());
            ps.setString(5, app.getDateTime().format(formatter));
            ps.setString(6, app.getClientReason());

            String noteValue = app.getDoctorNote();
            if (noteValue == null) { noteValue = ""; }
            ps.setString(7, noteValue);

            ps.setLong(8, app.getId());
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM appointments WHERE id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}

