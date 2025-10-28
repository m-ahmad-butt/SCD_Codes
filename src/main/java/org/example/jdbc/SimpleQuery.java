package org.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SimpleQuery {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://ep-icy-voice-adddhq55-pooler.c-2.us-east-1.aws.neon.tech/neondb?sslmode=require&channel_binding=require";
        String user = "neondb_owner";
        String password = "npg_yUZT5jmeB0ob";
        
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Example: parameterized query (no parameters here, but using PreparedStatement)
            String sql = "SELECT * FROM users WHERE 1 = ?";
            try (java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, 1); // Always true, but demonstrates usage of '?'
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int columnCount = rs.getMetaData().getColumnCount();
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(rs.getString(i) + "\t");
                        }
                        System.out.println();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
