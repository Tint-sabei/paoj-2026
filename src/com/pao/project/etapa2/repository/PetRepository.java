package com.pao.project.etapa2.repository;

import com.pao.project.etapa2.model.Owner;
import com.pao.project.etapa2.model.Pet;
import com.pao.project.etapa2.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PetRepository implements Repository<Pet, Long>{

    private Connection getConn() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private Pet mapRow(ResultSet rs) throws SQLException {
        long petId = rs.getLong("id");
        String petName = rs.getString("name");
        String specie = rs.getString("specie");
        long ownerId = rs.getLong("owner_id");

        Owner owner = null;
        if (ownerId > 0) {
            owner = new Owner();
            owner.setId(ownerId);
        }

        return new Pet(petId, petName, specie, owner);
    }

    @Override
    public void save(Pet pet) throws SQLException {
        String sql = "INSERT INTO pets (name, specie, owner_id) VALUES (?, ?, ?)";
        try (PreparedStatement ps = getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, pet.getPetName());
            ps.setString(2, pet.getSpecie());

            if (pet.getOwner() != null) {
                ps.setLong(3, pet.getOwner().getId());
            } else {
                ps.setNull(3, Types.INTEGER);
            }

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    pet.setId(keys.getLong(1));
                }
            }

        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Optional<Pet> findById(Long id) throws SQLException {
        String sql = "SELECT id, name, specie, owner_id FROM pets WHERE id = ?";
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
    public List<Pet> findAll() throws SQLException {
        String sql = "SELECT id, name, specie, owner_id FROM pets ORDER BY id";
        List<Pet> list = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()){
                list.add(mapRow(rs));
            }
        } catch (IOException e) {
            throw new SQLException(e);
        }
        return list;
    }

    @Override
    public void update(Pet pet) throws SQLException {
        String sql = "UPDATE pets SET name = ?, specie = ?, owner_id = ? WHERE id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {

            ps.setString(1, pet.getPetName());
            ps.setString(2, pet.getSpecie());

            if (pet.getOwner() != null) {
                ps.setLong(3, pet.getOwner().getId());
            } else {
                ps.setNull(3, Types.INTEGER);
            }

            ps.setLong(4, pet.getId());
            ps.executeUpdate();

        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM pets WHERE id = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}
