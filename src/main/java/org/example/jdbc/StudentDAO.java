import java.sql.*;
import java.util.*;
import org.example.jdbc.database;

public class StudentDAO {

    private final Connection conn;
    public StudentDAO() {
        this.conn = database.getInstance().getConnection();
    }

    public void insertStudents(ArrayList<Hashtable<String, Object>> students) {
        String sql = "INSERT INTO students (id, name, age, grade) VALUES (?, ?, ?, ?)";

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

    // --- Main method for quick test ---
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        // Prepare data
        ArrayList<Hashtable<String, Object>> students = new ArrayList<>();

        Hashtable<String, Object> s1 = new Hashtable<>();
        s1.put("id", 101);
        s1.put("name", "Ali");
        s1.put("age", 21);
        s1.put("grade", "A");
        students.add(s1);

        Hashtable<String, Object> s2 = new Hashtable<>();
        s2.put("id", 102);
        s2.put("name", "Sara");
        s2.put("age", 23);
        s2.put("grade", "B");
        students.add(s2);

        // Insert data
        dao.insertStudents(students);

        // Retrieve and print all
        ArrayList<Hashtable<String, Object>> all = dao.getAllStudents();
        System.out.println("\n All Students:");
        for (Hashtable<String, Object> row : all) {
            System.out.println(row);
        }
    }
}
