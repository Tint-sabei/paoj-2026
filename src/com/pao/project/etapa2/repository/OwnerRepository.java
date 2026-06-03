package com.pao.project.etapa2.repository;

import com.pao.project.etapa2.model.Owner;
import com.pao.project.etapa2.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OwnerRepository implements Repository<Owner, Long> {

    private Connection getConn() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    /** Maps a row from the ResultSet to a Owner object. */
    private Owner mapRow(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");

        return new Owner(id, firstName, lastName);
    }

    @Override
    public void save(Owner owner) throws SQLException {
        String sql = "INSERT INTO owners (first_name, last_name) VALUES (?, ?)";
        try (PreparedStatement ps = getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, owner.getFirstName());
            ps.setString(2, owner.getLastName());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    owner.setId(keys.getLong(1));
                }
            }

        } catch (IOException e){
            throw new SQLException(e);
        }

    }

    @Override
    public Optional<Owner> findById(Long id) throws SQLException {
        String sql = "SELECT id, first_name, last_name FROM owners WHERE id = ?";
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
    public List<Owner> findAll() throws SQLException {
        String sql = "SELECT id, first_name, last_name FROM owners ORDER BY id";
        List<Owner> list = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (IOException e) {
            throw new SQLException(e);
        }
        return list;

    }

    @Override
    public void update(Owner owner) throws SQLException {
        String sql = "UPDATE owners SET first_name = ?, last_name = ? WHERE id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, owner.getFirstName());
            ps.setString(2, owner.getLastName());
            ps.setLong(3, owner.getId());
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM owners WHERE id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }

    }
}
