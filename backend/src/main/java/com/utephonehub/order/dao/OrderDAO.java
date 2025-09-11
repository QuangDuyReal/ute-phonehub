package com.utephonehub.order.dao;

import com.utephonehub.order.model.Order;
import com.utephonehub.order.model.OrderStatus;
import com.utephonehub.order.connectDB.ConnectDB;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private OrderItemsDAO orderItemsDAO = new OrderItemsDAO();

    // Tạo một đơn hàng mới
    public void createOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (user_id, order_date, status, total_amount, shipping_address, recipient_name, recipient_phone, voucher_id) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setObject(2, order.getOrderDate());
            ps.setString(3, order.getStatus().name());
            ps.setBigDecimal(4, order.getTotalAmount());
            ps.setString(5, order.getShippingAddress());
            ps.setString(6, order.getRecipientName());
            ps.setString(7, order.getRecipientPhone());
            if (order.getVoucherId() != null) {
                ps.setInt(8, order.getVoucherId());
            } else {
                ps.setNull(8, Types.INTEGER);
            }

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        order.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    // Lấy đơn hàng theo ID
    public Order getOrderById(int orderId) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";
        Order order = null;

        try (Connection conn = ConnectDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getObject("order_date", OffsetDateTime.class));
                order.setStatus(OrderStatus.valueOf(rs.getString("status")));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setShippingAddress(rs.getString("shipping_address"));
                order.setRecipientName(rs.getString("recipient_name"));
                order.setRecipientPhone(rs.getString("recipient_phone"));
                order.setVoucherId(rs.getObject("voucher_id", Integer.class));
            }
        }
        return order;
    }

    // Lấy danh sách đơn hàng của một người dùng
    public List<Order> getOrdersByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";
        List<Order> orders = new ArrayList<>();

        try (Connection conn = ConnectDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getObject("order_date", OffsetDateTime.class));
                order.setStatus(OrderStatus.valueOf(rs.getString("status")));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setShippingAddress(rs.getString("shipping_address"));
                order.setRecipientName(rs.getString("recipient_name"));
                order.setRecipientPhone(rs.getString("recipient_phone"));
                order.setVoucherId(rs.getObject("voucher_id", Integer.class));
                orders.add(order);
            }
        }
        return orders;
    }

    // Cập nhật trạng thái đơn hàng
    public void updateOrderStatus(int orderId, OrderStatus status) throws SQLException {
        String sql = "UPDATE orders SET status = ?, updated_at = NOW() WHERE id = ?";

        try (Connection conn = ConnectDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status.name());
            ps.setInt(2, orderId);
            ps.executeUpdate();
        }
    }

    // Xóa đơn hàng
    public void deleteOrder(int orderId) throws SQLException {
        try (Connection conn = ConnectDB.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction
            try {
                // Xóa các mục đơn hàng trước
                orderItemsDAO.deleteOrderItemsByOrderId(orderId);

                // Xóa đơn hàng
                String sql = "DELETE FROM orders WHERE id = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, orderId);
                    ps.executeUpdate();
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
}