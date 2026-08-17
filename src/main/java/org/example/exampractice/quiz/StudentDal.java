package org.example.exampractice.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import org.example.jdbc.Database;

// DAL: query Student (rollno, name, gpa) filtered by batch
public class StudentDal {
    private final Connection conn;

    public StudentDal() {
        conn = Database.getInstance().getConnection();
        createTable();
    }

    private void createTable() {
        if (conn == null) {
            System.err.println("No database connection.");
            return;
        }
        String sql = "CREATE TABLE IF NOT EXISTS Student ("
                + "rollno TEXT PRIMARY KEY, "
                + "name TEXT, "
                + "gpa REAL, "
                + "batch TEXT)";
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Hashtable<String, Object>> getStudentsByBatch(String batch) {
        ArrayList<Hashtable<String, Object>> list = new ArrayList<>();
        String sql = "SELECT rollno, name, gpa FROM Student WHERE batch = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, batch);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Hashtable<String, Object> row = new Hashtable<>();
                row.put("rollno", rs.getString("rollno"));
                row.put("name", rs.getString("name"));
                row.put("gpa", rs.getDouble("gpa"));
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        StudentDal dal = new StudentDal();
        ArrayList<Hashtable<String, Object>> rows = dal.getStudentsByBatch("2022");
        for (Hashtable<String, Object> row : rows) {
            System.out.println(row);
        }
    }
}
