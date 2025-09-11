package com.utephonedhub.test;

import com.utephonedhub.config.DBConnection;

import java.sql.Connection;

public class TestDBConnection {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Kết nối DB thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kết nối DB thất bại!");
        }
    }
}
