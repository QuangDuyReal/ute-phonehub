package org.example;

import java.sql.Connection;

import com.utephonehub.config.DBConnection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Kết nối thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
