package org.example.jdbc;

import java.sql.*;
import java.util.Hashtable;

public class TodoDB {
    private final Connection conn;

    public TodoDB() {
        this.conn = database.getInstance().getConnection();
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS todo (" +
                "name TEXT PRIMARY KEY, " +
                "status TEXT)";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Todo table checked/created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Hashtable<String, String> t) {
        String sql = "INSERT INTO todo (name,status) VALUES (?,?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, t.get("name"));
            stmt.setObject(2, t.get("status"));
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void edit(String keyName, Hashtable<String, String> t) {
        String sql = "UPDATE todo SET name=?,status=? WHERE name = ?";
        //name is primary key its unique so,
        String name = t.get("name");
        String status = t.get("status");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, name);
            stmt.setObject(2, status);
            stmt.setObject(3, keyName);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void delete(String keyName) {
        String sql = "DELETE FROM todo WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, keyName);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // public ArrayList<Hashtable<String,String>> read(){

    // }
    public static void main(String[] args) {
        TodoDB db = new TodoDB();
    }
}
