package org.example.table.shopping_cart;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:src/main/java/org/example/table/shopping_cart/database.db";
    private Connection connection;

    public DatabaseManager() {
        try {

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Database connection established.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }

    public void initializeDatabase() {
        String dropCartItems = "DROP TABLE IF EXISTS cart_items";
        String dropItems = "DROP TABLE IF EXISTS items";

        String createItemsTable = "CREATE TABLE IF NOT EXISTS items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "price REAL NOT NULL, " +
                "expiry TEXT NOT NULL, " +
                "stock INTEGER NOT NULL DEFAULT 0, " +
                "CHECK (price >= 0), " +
                "CHECK (stock >= 0))";

        String createCartTable = "CREATE TABLE IF NOT EXISTS cart_items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "item_id INTEGER NOT NULL, " +
                "quantity INTEGER NOT NULL DEFAULT 1, " +
                "FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE, " +
                "CHECK (quantity > 0), " +
                "UNIQUE(item_id))";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(dropCartItems);
            stmt.execute(dropItems);
            stmt.execute(createItemsTable);
            stmt.execute(createCartTable);

            // Create indexes
            stmt.execute("CREATE INDEX IF NOT EXISTS idx_items_name ON items(name)");
            stmt.execute("CREATE INDEX IF NOT EXISTS idx_cart_item_id ON cart_items(item_id)");

            System.out.println("Database tables initialized.");
            insertSampleData();
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    private void insertSampleData() {
        String sql = "INSERT INTO items (name, price, expiry, stock) VALUES (?, ?, ?, ?)";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            Date expiry = new Date();
            expiry.setTime(expiry.getTime() + (365L * 24 * 60 * 60 * 1000)); // 1 year from now

            String[][] data = {
                    {"Milk", "120.0", "10"},
                    {"Bread", "80.0", "25"},
                    {"Eggs", "200.0", "30"},
                    {"Rice", "150.0", "50"},
                    {"Sugar", "110.0", "40"}
            };

            for (String[] item : data) {
                pstmt.setString(1, item[0]);
                pstmt.setDouble(2, Double.parseDouble(item[1]));
                pstmt.setString(3, sdf.format(expiry));
                pstmt.setInt(4, Integer.parseInt(item[2]));
                pstmt.executeUpdate();
            }

            System.out.println("Sample data inserted.");
        } catch (SQLException e) {
            System.err.println("Error inserting sample data: " + e.getMessage());
        }
    }

    public List<Items> getAllItems() {
        List<Items> items = new ArrayList<>();
        String sql = "SELECT * FROM items";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {
                Items item = new Items();
                item.id = rs.getInt("id");
                item.name = rs.getString("name");
                item.price = rs.getDouble("price");
                try {
                    item.expiry = sdf.parse(rs.getString("expiry"));
                } catch (Exception e) {
                    item.expiry = new Date();
                }
                item.stock = rs.getInt("stock");
                items.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Error getting items: " + e.getMessage());
        }

        return items;
    }

    public boolean updateStock(int itemId, int newStock) {
        String sql = "UPDATE items SET stock = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, newStock);
            pstmt.setInt(2, itemId);
            int affected = pstmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating stock: " + e.getMessage());
            return false;
        }
    }

    // Add item to cart
    public boolean addToCart(int itemId) {
        String checkSql = "SELECT quantity FROM cart_items WHERE item_id = ?";
        String insertSql = "INSERT INTO cart_items (item_id, quantity) VALUES (?, 1)";
        String updateSql = "UPDATE cart_items SET quantity = quantity + 1 WHERE item_id = ?";

        try {
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setInt(1, itemId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Item exists, update quantity
                PreparedStatement updateStmt = connection.prepareStatement(updateSql);
                updateStmt.setInt(1, itemId);
                updateStmt.executeUpdate();
            } else {
                // New item, insert
                PreparedStatement insertStmt = connection.prepareStatement(insertSql);
                insertStmt.setInt(1, itemId);
                insertStmt.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error adding to cart: " + e.getMessage());
            return false;
        }
    }

    public List<CartItem> getCartItems() {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT i.id, i.name, i.price, c.quantity " +
                "FROM cart_items c " +
                "JOIN items i ON c.item_id = i.id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CartItem item = new CartItem();
                item.id = rs.getInt("id");
                item.name = rs.getString("name");
                item.price = rs.getDouble("price");
                item.quantity = rs.getInt("quantity");
                cartItems.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Error getting cart items: " + e.getMessage());
        }

        return cartItems;
    }

    public boolean removeFromCart(int itemId) {
        String checkSql = "SELECT quantity FROM cart_items WHERE item_id = ?";
        String updateSql = "UPDATE cart_items SET quantity = quantity - 1 WHERE item_id = ?";
        String deleteSql = "DELETE FROM cart_items WHERE item_id = ?";

        try {
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setInt(1, itemId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                if (quantity <= 1) {
                    // Delete the item
                    PreparedStatement deleteStmt = connection.prepareStatement(deleteSql);
                    deleteStmt.setInt(1, itemId);
                    deleteStmt.executeUpdate();
                } else {
                    // Decrease quantity
                    PreparedStatement updateStmt = connection.prepareStatement(updateSql);
                    updateStmt.setInt(1, itemId);
                    updateStmt.executeUpdate();
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error removing from cart: " + e.getMessage());
        }
        return false;
    }

    public List<Items> searchItems(String searchTerm) {
        List<Items> items = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE name LIKE ?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + searchTerm + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Items item = new Items();
                item.id = rs.getInt("id");
                item.name = rs.getString("name");
                item.price = rs.getDouble("price");
                try {
                    item.expiry = sdf.parse(rs.getString("expiry"));
                } catch (Exception e) {
                    item.expiry = new Date();
                }
                item.stock = rs.getInt("stock");
                items.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Error searching items: " + e.getMessage());
        }

        return items;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}