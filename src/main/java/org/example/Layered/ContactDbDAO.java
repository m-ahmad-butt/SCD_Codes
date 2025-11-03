package org.example.Layered;

import org.example.jdbc.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ContactDbDAO implements ContactDAO {

    private Connection getConnection() {
        return database.getInstance().getConnection();
    }

    @Override
    public boolean save(Contact contact) throws Exception {
        String sql = "INSERT INTO contacts (name, email) VALUES (?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getEmail());
            int affected = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    contact.setId(rs.getInt(1));
                }
            }
            return affected > 0;
        }
    }

    @Override
    public boolean update(Contact contact) throws Exception {
        if (contact.getId() == null) throw new IllegalArgumentException("id required for update");
        String sql = "UPDATE contacts SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getEmail());
            ps.setInt(3, contact.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Contact findById(int id) throws Exception {
        String sql = "SELECT id, name, email FROM contacts WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Contact> findAll() throws Exception {
        String sql = "SELECT id, name, email FROM contacts";
        List<Contact> list = new ArrayList<>();
        try (Statement st = getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    private Contact mapRow(ResultSet rs) throws Exception {
        Contact c = new Contact();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setEmail(rs.getString("email"));
        return c;
    }
}
