package org.example.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class database {
    private static database instance;        
    private Connection connection;     
    private String jdbcUrl;

    private database() {
        try (InputStream input = database.class.getClassLoader().getResourceAsStream("system.properties")) {
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

    public static database getInstance() {
        if (instance == null) {
            synchronized (database.class) {
                if (instance == null) {
                    instance = new database();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
