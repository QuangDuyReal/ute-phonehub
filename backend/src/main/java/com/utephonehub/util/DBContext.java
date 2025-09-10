package com.utephonehub.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    private static final String URL = "jdbc:postgresql://localhost:5432/UTE_phonehub";
    private static final String USER = "postgres";  // user đăng nhập PostgreSQL
    private static final String PASSWORD = "thach160605";  // mật khẩu của user postgres

    // Hàm tạo kết nối
    public static Connection getConnection() throws SQLException {
        try {
            // load driver PostgreSQL
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver not found!", e);
        }
    }
}
