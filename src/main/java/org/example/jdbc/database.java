package org.example.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

//connection
public class Database {
    private static Database instance;        
    private Connection connection;     
    private String jdbcUrl;

    private Database() {
        try (InputStream input = Database.class.getClassLoader().getResourceAsStream("system.properties")) {
            if (input == null) {
                System.out.println("Unable to find system.properties");
            } else {
                Properties properties = new Properties();
                properties.load(input);
                jdbcUrl = properties.getProperty("DB_URL");
                System.out.println("Loaded DB URL!");
            }

            //  SQLite driver
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection(jdbcUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
