package com.utephonehub.test;

import java.sql.Connection;
import com.utephonehub.util.DatabaseConnection;

public class TestDB {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Kết nối PostgreSQL thành công!");
            } else {
                System.out.println("❌ Kết nối thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
