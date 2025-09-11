package com.utephonehub.order.dao;

import com.utephonehub.order.model.OrderItem;
import com.utephonehub.order.connectDB.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsDAO {

    // Thêm một mục đơn hàng
    public void addOrderItem(OrderItem item) throws SQLException {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getProductId());
            ps.setInt(3, item.getQuantity());
            ps.setBigDecimal(4, item.getPrice());
            ps.executeUpdate();
        }
    }

    // Lấy danh sách mục đơn hàng theo order_id
    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        List<OrderItem> items = new ArrayList<>();

        try (Connection conn = ConnectDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setOrderId(rs.getInt("order_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getBigDecimal("price"));
                items.add(item);
            }
        }
        return items;
    }

    // Cập nhật số lượng của một mục đơn hàng
    public void updateOrderItemQuantity(int orderId, int productId, int quantity) throws SQLException {
        String sql = "UPDATE order_items SET quantity = ? WHERE order_id = ? AND product_id = ?";

        try (Connection conn = ConnectDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, orderId);
            ps.setInt(3, productId);
            ps.executeUpdate();
        }
    }

    // Xóa một mục đơn hàng
    public void deleteOrderItem(int orderId, int productId) throws SQLException {
        String sql = "DELETE FROM order_items WHERE order_id = ? AND product_id = ?";

        try (Connection conn = ConnectDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.executeUpdate();
        }
    }

    // Xóa tất cả mục đơn hàng của một đơn hàng
    public void deleteOrderItemsByOrderId(int orderId) throws SQLException {
        String sql = "DELETE FROM order_items WHERE order_id = ?";

        try (Connection conn = ConnectDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.executeUpdate();
        }
    }
}