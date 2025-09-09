package com.utephonehub.dao;

import com.utephonehub.model.order.OrderItem;
import com.utephonehub.util.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for OrderItem operations
 * Handles database operations for order_items table
 */
public class OrderItemDAO implements GenericDAO<OrderItem, OrderItemKey> {

    // ========== GenericDAO Implementation ==========
    
    @Override
    public OrderItemKey create(OrderItem orderItem) throws SQLException {
        if (createOrderItem(orderItem)) {
            return new OrderItemKey(orderItem.getOrderId(), orderItem.getProductId());
        }
        return null;
    }
    
    @Override
    public OrderItemKey create(OrderItem orderItem, Connection conn) throws SQLException {
        if (createOrderItem(orderItem, conn)) {
            return new OrderItemKey(orderItem.getOrderId(), orderItem.getProductId());
        }
        return null;
    }
    
    @Override
    public OrderItem findById(OrderItemKey key) throws SQLException {
        return getOrderItem(key.getOrderId(), key.getProductId());
    }
    
    @Override
    public List<OrderItem> findAll() throws SQLException {
        String sql = "SELECT * FROM order_items ORDER BY order_id, product_id";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            List<OrderItem> items = new ArrayList<>();
            
            while (rs.next()) {
                items.add(mapResultSetToOrderItem(rs));
            }
            
            return items;
        }
    }
    
    @Override
    public List<OrderItem> findAll(int limit, int offset) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM order_items ORDER BY order_id, product_id");
        
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
            List<OrderItem> items = new ArrayList<>();
            
            while (rs.next()) {
                items.add(mapResultSetToOrderItem(rs));
            }
            
            return items;
        }
    }
    
    @Override
    public boolean update(OrderItem orderItem) throws SQLException {
        String sql = "UPDATE order_items SET quantity = ?, price = ?, subtotal = ? " +
                    "WHERE order_id = ? AND product_id = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, orderItem.getQuantity());
            stmt.setBigDecimal(2, orderItem.getPrice());
            stmt.setBigDecimal(3, orderItem.getSubtotal());
            stmt.setInt(4, orderItem.getOrderId());
            stmt.setInt(5, orderItem.getProductId());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean update(OrderItem orderItem, Connection conn) throws SQLException {
        String sql = "UPDATE order_items SET quantity = ?, price = ?, subtotal = ? " +
                    "WHERE order_id = ? AND product_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderItem.getQuantity());
            stmt.setBigDecimal(2, orderItem.getPrice());
            stmt.setBigDecimal(3, orderItem.getSubtotal());
            stmt.setInt(4, orderItem.getOrderId());
            stmt.setInt(5, orderItem.getProductId());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean delete(OrderItemKey key) throws SQLException {
        return deleteOrderItem(key.getOrderId(), key.getProductId());
    }
    
    @Override
    public boolean delete(OrderItemKey key, Connection conn) throws SQLException {
        String sql = "DELETE FROM order_items WHERE order_id = ? AND product_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, key.getOrderId());
            stmt.setInt(2, key.getProductId());
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean exists(OrderItemKey key) throws SQLException {
        String sql = "SELECT 1 FROM order_items WHERE order_id = ? AND product_id = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, key.getOrderId());
            stmt.setInt(2, key.getProductId());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
    
    @Override
    public long count() throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM order_items";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("total");
            }
            return 0;
        }
    }

    // ========== Custom OrderItem Methods ==========

    /**
     * Create a single order item (internal method)
     * @param orderItem OrderItem to create
     * @return true if created successfully
     * @throws SQLException if database error occurs
     */
    private boolean createOrderItem(OrderItem orderItem) throws SQLException {
        try (Connection conn = DBContext.getConnection()) {
            return createOrderItem(orderItem, conn);
        }
    }

    /**
     * Create order items for an order
     * @param orderItems List of order items to create
     * @param conn Database connection (for transaction)
     * @return true if all items created successfully
     * @throws SQLException if database error occurs
     */
    public boolean createOrderItems(List<OrderItem> orderItems, Connection conn) throws SQLException {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity, price, subtotal, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (OrderItem item : orderItems) {
                stmt.setInt(1, item.getOrderId());
                stmt.setInt(2, item.getProductId());
                stmt.setInt(3, item.getQuantity());
                stmt.setBigDecimal(4, item.getPrice());
                stmt.setBigDecimal(5, item.getSubtotal());
                stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                
                stmt.addBatch();
            }
            
            int[] results = stmt.executeBatch();
            
            // Check if all inserts were successful
            for (int result : results) {
                if (result <= 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Create a single order item
     * @param orderItem OrderItem to create
     * @param conn Database connection (for transaction)
     * @return true if created successfully
     * @throws SQLException if database error occurs
     */
    public boolean createOrderItem(OrderItem orderItem, Connection conn) throws SQLException {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity, price, subtotal, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderItem.getOrderId());
            stmt.setInt(2, orderItem.getProductId());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setBigDecimal(4, orderItem.getPrice());
            stmt.setBigDecimal(5, orderItem.getSubtotal());
            stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Get order items by order ID
     * @param orderId Order ID
     * @return List of OrderItem
     * @throws SQLException if database error occurs
     */
    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            
            List<OrderItem> items = new ArrayList<>();
            while (rs.next()) {
                OrderItem item = mapResultSetToOrderItem(rs);
                items.add(item);
            }
            
            return items;
        }
    }

    /**
     * Get order item by order ID and product ID
     * @param orderId Order ID
     * @param productId Product ID
     * @return OrderItem or null if not found
     * @throws SQLException if database error occurs
     */
    public OrderItem getOrderItem(int orderId, int productId) throws SQLException {
        String sql = "SELECT * FROM order_items WHERE order_id = ? AND product_id = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, orderId);
            stmt.setInt(2, productId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToOrderItem(rs);
            }
            return null;
        }
    }

    /**
     * Update order item quantity and subtotal
     * @param orderId Order ID
     * @param productId Product ID
     * @param quantity New quantity
     * @param subtotal New subtotal
     * @return true if updated successfully
     * @throws SQLException if database error occurs
     */
    public boolean updateOrderItem(int orderId, int productId, int quantity, 
                                  java.math.BigDecimal subtotal) throws SQLException {
        String sql = "UPDATE order_items SET quantity = ?, subtotal = ? " +
                    "WHERE order_id = ? AND product_id = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, quantity);
            stmt.setBigDecimal(2, subtotal);
            stmt.setInt(3, orderId);
            stmt.setInt(4, productId);
            
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Delete order items by order ID
     * @param orderId Order ID
     * @return true if deleted successfully
     * @throws SQLException if database error occurs
     */
    public boolean deleteOrderItemsByOrderId(int orderId) throws SQLException {
        String sql = "DELETE FROM order_items WHERE order_id = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, orderId);
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Delete specific order item
     * @param orderId Order ID
     * @param productId Product ID
     * @return true if deleted successfully
     * @throws SQLException if database error occurs
     */
    public boolean deleteOrderItem(int orderId, int productId) throws SQLException {
        String sql = "DELETE FROM order_items WHERE order_id = ? AND product_id = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, orderId);
            stmt.setInt(2, productId);
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Get total quantity of items in an order
     * @param orderId Order ID
     * @return Total quantity
     * @throws SQLException if database error occurs
     */
    public int getTotalQuantityByOrderId(int orderId) throws SQLException {
        String sql = "SELECT SUM(quantity) as total_quantity FROM order_items WHERE order_id = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total_quantity");
            }
            return 0;
        }
    }

    /**
     * Map ResultSet to OrderItem object
     * @param rs ResultSet
     * @return OrderItem object
     * @throws SQLException if database error occurs
     */
    private OrderItem mapResultSetToOrderItem(ResultSet rs) throws SQLException {
        OrderItem item = new OrderItem();
        item.setOrderId(rs.getInt("order_id"));
        item.setProductId(rs.getInt("product_id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setPrice(rs.getBigDecimal("price"));
        item.setSubtotal(rs.getBigDecimal("subtotal"));
        item.setCreatedAt(rs.getTimestamp("created_at"));
        
        return item;
    }
}
