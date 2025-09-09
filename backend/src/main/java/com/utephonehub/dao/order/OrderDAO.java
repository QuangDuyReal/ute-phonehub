package com.utephonehub.dao.order;

import com.utephonehub.dao.GenericDAO;
import com.utephonehub.dto.order.OrderDetailDTO;
import com.utephonehub.dto.order.OrderItemDetailDTO;
import com.utephonehub.dto.order.OrderSummaryDTO;
import com.utephonehub.model.order.Order;
import com.utephonehub.model.order.OrderStatus;
import com.utephonehub.util.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Order operations
 * Handles database operations for orders table
 */
public class OrderDAO implements GenericDAO<Order, Integer> {

    // ========== GenericDAO Implementation ==========
    
    @Override
    public Integer create(Order order) throws SQLException {
        return createOrder(order);
    }
    
    @Override
    public Integer create(Order order, Connection conn) throws SQLException {
        return createOrder(order, conn);
    }
    
    @Override
    public Order findById(Integer id) throws SQLException {
        return findOrderById(id.intValue());
    }
    
    @Override
    public List<Order> findAll() throws SQLException {
        return findAll(0, 0);
    }
    
    @Override
    public List<Order> findAll(int limit, int offset) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM orders ORDER BY order_date DESC");
        
        if (limit > 0) {
            sql.append(" LIMIT ? OFFSET ?");
        }
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            
            if (limit > 0) {
                stmt.setInt(1, limit);
                stmt.setInt(2, offset);
            }
            
            ResultSet rs = stmt.executeQuery();
            List<Order> orders = new ArrayList<>();
            
            while (rs.next()) {
                orders.add(mapResultSetToOrder(rs));
            }
            
            return orders;
        }
    }
    
    @Override
    public boolean update(Order order) throws SQLException {
        String sql = "UPDATE orders SET status = ?, total_amount = ?, shipping_address = ?, " +
                    "recipient_name = ?, recipient_phone = ?, voucher_code = ?, " +
                    "discount_amount = ?, updated_at = ? WHERE id = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, order.getStatus().name());
            stmt.setBigDecimal(2, order.getTotalAmount());
            stmt.setString(3, order.getShippingAddress());
            stmt.setString(4, order.getRecipientName());
            stmt.setString(5, order.getRecipientPhone());
            stmt.setString(6, order.getVoucherCode());
            stmt.setBigDecimal(7, order.getDiscountAmount());
            stmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(9, order.getId());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean update(Order order, Connection conn) throws SQLException {
        String sql = "UPDATE orders SET status = ?, total_amount = ?, shipping_address = ?, " +
                    "recipient_name = ?, recipient_phone = ?, voucher_code = ?, " +
                    "discount_amount = ?, updated_at = ? WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, order.getStatus().name());
            stmt.setBigDecimal(2, order.getTotalAmount());
            stmt.setString(3, order.getShippingAddress());
            stmt.setString(4, order.getRecipientName());
            stmt.setString(5, order.getRecipientPhone());
            stmt.setString(6, order.getVoucherCode());
            stmt.setBigDecimal(7, order.getDiscountAmount());
            stmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(9, order.getId());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean delete(Integer id, Connection conn) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean exists(Integer id) throws SQLException {
        String sql = "SELECT 1 FROM orders WHERE id = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
    
    @Override
    public long count() throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM orders";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("total");
            }
            return 0;
        }
    }

    // ========== Custom Order Methods ==========

    /**
     * Create a new order
     * @param order Order object to create
     * @return Generated order ID
     * @throws SQLException if database error occurs
     */
    public int createOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (user_id, order_date, status, total_amount, " +
                    "shipping_address, recipient_name, recipient_phone, voucher_code, " +
                    "discount_amount, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                    "RETURNING id";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, order.getUserId());
            stmt.setTimestamp(2, order.getOrderDate());
            stmt.setString(3, order.getStatus().name());
            stmt.setBigDecimal(4, order.getTotalAmount());
            stmt.setString(5, order.getShippingAddress());
            stmt.setString(6, order.getRecipientName());
            stmt.setString(7, order.getRecipientPhone());
            stmt.setString(8, order.getVoucherCode());
            stmt.setBigDecimal(9, order.getDiscountAmount());
            stmt.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
            throw new SQLException("Failed to create order, no ID returned");
        }
    }

    /**
     * Create order with transaction connection
     * @param order Order object to create
     * @param conn Database connection (for transaction)
     * @return Generated order ID
     * @throws SQLException if database error occurs
     */
    public int createOrder(Order order, Connection conn) throws SQLException {
        String sql = "INSERT INTO orders (user_id, order_date, status, total_amount, " +
                    "shipping_address, recipient_name, recipient_phone, voucher_code, " +
                    "discount_amount, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                    "RETURNING id";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, order.getUserId());
            stmt.setTimestamp(2, order.getOrderDate());
            stmt.setString(3, order.getStatus().name());
            stmt.setBigDecimal(4, order.getTotalAmount());
            stmt.setString(5, order.getShippingAddress());
            stmt.setString(6, order.getRecipientName());
            stmt.setString(7, order.getRecipientPhone());
            stmt.setString(8, order.getVoucherCode());
            stmt.setBigDecimal(9, order.getDiscountAmount());
            stmt.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
            throw new SQLException("Failed to create order, no ID returned");
        }
    }

    /**
     * Find order by ID (internal method)
     * @param orderId Order ID to find
     * @return Order object or null if not found
     * @throws SQLException if database error occurs
     */
    private Order findOrderById(int orderId) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToOrder(rs);
            }
            return null;
        }
    }

    /**
     * Get orders summary for a specific user
     * @param userId User ID
     * @param limit Number of orders to return (0 for all)
     * @param offset Offset for pagination
     * @return List of OrderSummaryDTO
     * @throws SQLException if database error occurs
     */
    public List<OrderSummaryDTO> getOrdersSummaryByUserId(int userId, int limit, int offset) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o.id, o.order_date, o.status, o.total_amount, o.recipient_name, ");
        sql.append("o.voucher_code, o.discount_amount, ");
        sql.append("COUNT(oi.product_id) as item_count ");
        sql.append("FROM orders o ");
        sql.append("LEFT JOIN order_items oi ON o.id = oi.order_id ");
        sql.append("WHERE o.user_id = ? ");
        sql.append("GROUP BY o.id, o.order_date, o.status, o.total_amount, o.recipient_name, ");
        sql.append("o.voucher_code, o.discount_amount ");
        sql.append("ORDER BY o.order_date DESC");
        
        if (limit > 0) {
            sql.append(" LIMIT ? OFFSET ?");
        }
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            
            stmt.setInt(1, userId);
            if (limit > 0) {
                stmt.setInt(2, limit);
                stmt.setInt(3, offset);
            }
            
            ResultSet rs = stmt.executeQuery();
            List<OrderSummaryDTO> orders = new ArrayList<>();
            
            while (rs.next()) {
                OrderSummaryDTO dto = new OrderSummaryDTO();
                dto.setId(rs.getInt("id"));
                dto.setOrderDate(rs.getTimestamp("order_date"));
                dto.setStatus(rs.getString("status"));
                dto.setStatusDisplayName(OrderStatus.fromString(rs.getString("status")).getDisplayName());
                dto.setTotalAmount(rs.getBigDecimal("total_amount"));
                dto.setItemCount(rs.getInt("item_count"));
                dto.setRecipientName(rs.getString("recipient_name"));
                dto.setVoucherCode(rs.getString("voucher_code"));
                dto.setDiscountAmount(rs.getBigDecimal("discount_amount"));
                
                orders.add(dto);
            }
            
            return orders;
        }
    }

    /**
     * Get all orders summary (for admin)
     * @param limit Number of orders to return (0 for all)
     * @param offset Offset for pagination
     * @return List of OrderSummaryDTO
     * @throws SQLException if database error occurs
     */
    public List<OrderSummaryDTO> getAllOrdersSummary(int limit, int offset) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o.id, o.order_date, o.status, o.total_amount, o.recipient_name, ");
        sql.append("o.voucher_code, o.discount_amount, ");
        sql.append("COUNT(oi.product_id) as item_count ");
        sql.append("FROM orders o ");
        sql.append("LEFT JOIN order_items oi ON o.id = oi.order_id ");
        sql.append("GROUP BY o.id, o.order_date, o.status, o.total_amount, o.recipient_name, ");
        sql.append("o.voucher_code, o.discount_amount ");
        sql.append("ORDER BY o.order_date DESC");
        
        if (limit > 0) {
            sql.append(" LIMIT ? OFFSET ?");
        }
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            
            if (limit > 0) {
                stmt.setInt(1, limit);
                stmt.setInt(2, offset);
            }
            
            ResultSet rs = stmt.executeQuery();
            List<OrderSummaryDTO> orders = new ArrayList<>();
            
            while (rs.next()) {
                OrderSummaryDTO dto = new OrderSummaryDTO();
                dto.setId(rs.getInt("id"));
                dto.setOrderDate(rs.getTimestamp("order_date"));
                dto.setStatus(rs.getString("status"));
                dto.setStatusDisplayName(OrderStatus.fromString(rs.getString("status")).getDisplayName());
                dto.setTotalAmount(rs.getBigDecimal("total_amount"));
                dto.setItemCount(rs.getInt("item_count"));
                dto.setRecipientName(rs.getString("recipient_name"));
                dto.setVoucherCode(rs.getString("voucher_code"));
                dto.setDiscountAmount(rs.getBigDecimal("discount_amount"));
                
                orders.add(dto);
            }
            
            return orders;
        }
    }

    /**
     * Get detailed order information
     * @param orderId Order ID
     * @param userId User ID (null for admin access)
     * @return OrderDetailDTO or null if not found
     * @throws SQLException if database error occurs
     */
    public OrderDetailDTO getOrderDetail(int orderId, Integer userId) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o.*, u.full_name as user_full_name, u.email as user_email ");
        sql.append("FROM orders o ");
        sql.append("JOIN users u ON o.user_id = u.id ");
        sql.append("WHERE o.id = ?");
        
        if (userId != null) {
            sql.append(" AND o.user_id = ?");
        }
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            
            stmt.setInt(1, orderId);
            if (userId != null) {
                stmt.setInt(2, userId);
            }
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                OrderDetailDTO dto = new OrderDetailDTO();
                dto.setId(rs.getInt("id"));
                dto.setUserId(rs.getInt("user_id"));
                dto.setUserFullName(rs.getString("user_full_name"));
                dto.setUserEmail(rs.getString("user_email"));
                dto.setOrderDate(rs.getTimestamp("order_date"));
                dto.setStatus(rs.getString("status"));
                dto.setStatusDisplayName(OrderStatus.fromString(rs.getString("status")).getDisplayName());
                dto.setTotalAmount(rs.getBigDecimal("total_amount"));
                dto.setShippingAddress(rs.getString("shipping_address"));
                dto.setRecipientName(rs.getString("recipient_name"));
                dto.setRecipientPhone(rs.getString("recipient_phone"));
                dto.setVoucherCode(rs.getString("voucher_code"));
                dto.setDiscountAmount(rs.getBigDecimal("discount_amount"));
                dto.setCreatedAt(rs.getTimestamp("created_at"));
                dto.setUpdatedAt(rs.getTimestamp("updated_at"));
                
                // Get order items
                dto.setItems(getOrderItems(orderId));
                
                return dto;
            }
            return null;
        }
    }

    /**
     * Update order status
     * @param orderId Order ID
     * @param status New status
     * @return true if updated successfully
     * @throws SQLException if database error occurs
     */
    public boolean updateOrderStatus(int orderId, OrderStatus status) throws SQLException {
        String sql = "UPDATE orders SET status = ?, updated_at = ? WHERE id = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status.name());
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(3, orderId);
            
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Get order items for an order
     * @param orderId Order ID
     * @return List of OrderItemDetailDTO
     * @throws SQLException if database error occurs
     */
    private List<OrderItemDetailDTO> getOrderItems(int orderId) throws SQLException {
        String sql = "SELECT oi.*, p.name as product_name, p.image_url as product_image_url, " +
                    "b.name as product_brand, c.name as product_category " +
                    "FROM order_items oi " +
                    "JOIN products p ON oi.product_id = p.id " +
                    "LEFT JOIN brands b ON p.brand_id = b.id " +
                    "LEFT JOIN categories c ON p.category_id = c.id " +
                    "WHERE oi.order_id = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            
            List<OrderItemDetailDTO> items = new ArrayList<>();
            while (rs.next()) {
                OrderItemDetailDTO item = new OrderItemDetailDTO();
                item.setProductId(rs.getInt("product_id"));
                item.setProductName(rs.getString("product_name"));
                item.setProductImageUrl(rs.getString("product_image_url"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setSubtotal(rs.getBigDecimal("subtotal"));
                item.setProductBrand(rs.getString("product_brand"));
                item.setProductCategory(rs.getString("product_category"));
                
                items.add(item);
            }
            
            return items;
        }
    }

    /**
     * Map ResultSet to Order object
     * @param rs ResultSet
     * @return Order object
     * @throws SQLException if database error occurs
     */
    private Order mapResultSetToOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setUserId(rs.getInt("user_id"));
        order.setOrderDate(rs.getTimestamp("order_date"));
        order.setStatus(OrderStatus.fromString(rs.getString("status")));
        order.setTotalAmount(rs.getBigDecimal("total_amount"));
        order.setShippingAddress(rs.getString("shipping_address"));
        order.setRecipientName(rs.getString("recipient_name"));
        order.setRecipientPhone(rs.getString("recipient_phone"));
        order.setVoucherCode(rs.getString("voucher_code"));
        order.setDiscountAmount(rs.getBigDecimal("discount_amount"));
        order.setCreatedAt(rs.getTimestamp("created_at"));
        order.setUpdatedAt(rs.getTimestamp("updated_at"));
        
        return order;
    }
}
