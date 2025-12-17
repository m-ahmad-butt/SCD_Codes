package org.example.jdbc;
 import java.sql.*;
 import java.util.*;

 public class StudentDAO {
     private final Connection conn;

     public StudentDAO() {
        this.conn = database.getInstance().getConnection();
        createTable();
    }

    private void createTable() {
        // Schema from schema.txt
        String sql = "CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT NOT NULL, age INTEGER, grade TEXT)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     // ---------------- CREATE ----------------
     public void insertStudents(ArrayList<Hashtable<String, Object>> students) {
         String sql = "INSERT INTO students (id, name, age, grade) VALUES (?, ?, ?, ?)";

         for(Hashtable<String,Object> tb:students){
             System.out.println(tb);
         }

         try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
             for (Hashtable<String, Object> student : students) {
                 pstmt.setObject(1, student.get("id"));
                 pstmt.setObject(2, student.get("name"));
                 pstmt.setObject(3, student.get("age"));
                 pstmt.setObject(4, student.get("grade"));
                 pstmt.addBatch();
             }
             pstmt.executeBatch();
             System.out.println("Students inserted successfully.");
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }

     // ---------------- READ ----------------
     public ArrayList<Hashtable<String, Object>> getAllStudents() {
         ArrayList<Hashtable<String, Object>> list = new ArrayList<>();
         String sql = "SELECT * FROM students";

         try (Statement stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql)) {

             ResultSetMetaData meta = rs.getMetaData();
             int columnCount = meta.getColumnCount();

             while (rs.next()) {
                 Hashtable<String, Object> row = new Hashtable<>();
                 for (int i = 1; i <= columnCount; i++) {
                     row.put(meta.getColumnName(i), rs.getObject(i));
                 }
                 list.add(row);
             }

         } catch (SQLException e) {
             e.printStackTrace();
         }

         return list;
     }

     // ---------------- UPDATE ----------------
     public void updateStudent(int id, Hashtable<String, Object> data) {
         String sql = "UPDATE students SET name = ?, age = ?, grade = ? WHERE id = ?";

         try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setObject(1, data.get("name"));
             pstmt.setObject(2, data.get("age"));
             pstmt.setObject(3, data.get("grade"));
             pstmt.setObject(4, id);

             int rows = pstmt.executeUpdate();
             if (rows > 0) {
                 System.out.println("Student with ID " + id + " updated successfully.");
             } else {
                 System.out.println("No student found with ID " + id + ".");
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }

     // ---------------- DELETE ----------------
     public void deleteStudent(int id) {
         String sql = "DELETE FROM students WHERE id = ?";

         try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setObject(1, id);

             int rows = pstmt.executeUpdate();
             if (rows > 0) {
                 System.out.println("Student with ID " + id + " deleted successfully.");
             } else {
                 System.out.println("No student found with ID " + id + ".");
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }

     // ---------------- MAIN TEST ----------------
     public static void main(String[] args) {
         StudentDAO dao = new StudentDAO();

         // --- Insert ---
         ArrayList<Hashtable<String, Object>> students = new ArrayList<>();
         Hashtable<String, Object> s1 = new Hashtable<>();
         s1.put("id", 101);
         s1.put("name", "saim");
         s1.put("age", 21);
         s1.put("grade", "A");
         students.add(s1);

         Hashtable<String, Object> s2 = new Hashtable<>();
         s2.put("id", 105);
         s2.put("name", "ahmed");
         s2.put("age", 23);
         s2.put("grade", "B");
         students.add(s2);

         dao.insertStudents(students);

         // // --- Update ---
         // Hashtable<String, Object> updated = new Hashtable<>();
         // updated.put("name", "Sara Khan");
         // updated.put("age", 24);
         // updated.put("grade", "A");
         // dao.updateStudent(102, updated);

         // --- Delete ---
         dao.deleteStudent(101);

         // --- Read ---
         System.out.println("\nAll Students:");
         ArrayList<Hashtable<String, Object>> all = dao.getAllStudents();
         for (Hashtable<String, Object> row : all) {
             System.out.println(row);
         }
     }
 }
