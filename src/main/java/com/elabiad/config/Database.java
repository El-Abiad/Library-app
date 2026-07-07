package com.elabiad.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Database.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new IllegalStateException("db.properties not found in resources");
            }
            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection connect() throws SQLException {

        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        );

    }
}