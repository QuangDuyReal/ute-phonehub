package com.utephonehub.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Simple DB utility to obtain JDBC connections.
 * Configure via environment variables:
 *   DB_URL, DB_USERNAME, DB_PASSWORD
 */
public final class DbUtil {

    private static final String URL = System.getenv().getOrDefault("DB_URL", "jdbc:postgresql://localhost:5432/ute_phonehub");
    private static final String USER = System.getenv().getOrDefault("DB_USERNAME", "postgres");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "123456");

    private DbUtil() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


