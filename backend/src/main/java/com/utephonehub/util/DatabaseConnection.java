package com.utephonehub.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:postgresql://localhost:5432/ute-phonehub";
    private static final String USER = "postgres";  // thay bằng user của bạn
    private static final String PASSWORD = "hhh1475369"; // thay bằng password bạn đặt khi cài PostgreSQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
