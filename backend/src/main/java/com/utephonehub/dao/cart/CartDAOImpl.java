package com.utephonehub.dao.cart;

import com.utephonehub.model.cart.Cart;
import com.utephonehub.util.DBUtil;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of CartDAO interface
 * Module M05 - Shopping Cart
 */
public class CartDAOImpl implements CartDAO {
    
    @Override
    public Long create(Cart cart) throws SQLException {
        return create(cart, null);
    }
    
    @Override
    public Long create(Cart cart, Connection conn) throws SQLException {
        String sql = "INSERT INTO carts (user_id, created_at, updated_at) VALUES (?, ?, ?) RETURNING id";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, cart.getUserId());
            ps.setTimestamp(2, Timestamp.from(cart.getCreatedAt()));
            ps.setTimestamp(3, Timestamp.from(cart.getUpdatedAt()));
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long generatedId = rs.getLong(1);
                    cart.setId(generatedId);
                    return generatedId;
                }
                throw new SQLException("Creating cart failed, no ID obtained.");
            }
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public Cart findById(Long id) throws SQLException {
        Optional<Cart> cart = findByIdOptional(id, null);
        return cart.orElse(null);
    }
    
    public Optional<Cart> findByIdOptional(Long id, Connection conn) throws SQLException {
        String sql = "SELECT id, user_id, created_at, updated_at FROM carts WHERE id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToCart(rs));
                }
                return Optional.empty();
            }
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public List<Cart> findAll() throws SQLException {
        return findAllWithConnection(null);
    }
    
    private List<Cart> findAllWithConnection(Connection conn) throws SQLException {
        String sql = "SELECT id, user_id, created_at, updated_at FROM carts ORDER BY created_at DESC";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        List<Cart> carts = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                carts.add(mapResultSetToCart(rs));
            }
            return carts;
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public boolean update(Cart cart) throws SQLException {
        return update(cart, null);
    }
    
    @Override
    public boolean update(Cart cart, Connection conn) throws SQLException {
        String sql = "UPDATE carts SET updated_at = ? WHERE id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.from(Instant.now()));
            ps.setLong(2, cart.getId());
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                cart.setUpdatedAt(Instant.now());
                return true;
            }
            return false;
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public boolean delete(Long id) throws SQLException {
        return delete(id, null);
    }
    
    @Override
    public boolean delete(Long id, Connection conn) throws SQLException {
        String sql = "DELETE FROM carts WHERE id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public Optional<Cart> findByUserId(long userId) throws SQLException {
        return findByUserId(userId, null);
    }
    
    @Override
    public Optional<Cart> findByUserId(long userId, Connection conn) throws SQLException {
        return findByUserIdOptional(userId, conn);
    }
    
    private Optional<Cart> findByUserIdOptional(long userId, Connection conn) throws SQLException {
        String sql = "SELECT id, user_id, created_at, updated_at FROM carts WHERE user_id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToCart(rs));
                }
                return Optional.empty();
            }
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public Cart getOrCreateCartForUser(long userId) throws SQLException {
        return getOrCreateCartForUser(userId, null);
    }
    
    @Override
    public Cart getOrCreateCartForUser(long userId, Connection conn) throws SQLException {
        // First try to find existing cart
        Optional<Cart> existingCart = findByUserIdOptional(userId, conn);
        if (existingCart.isPresent()) {
            return existingCart.get();
        }
        
        // Create new cart if none exists
        Cart newCart = new Cart(userId);
        create(newCart, conn);
        return newCart;
    }
    
    @Override
    public void updateTimestamp(long cartId) throws SQLException {
        updateTimestamp(cartId, null);
    }
    
    @Override
    public void updateTimestamp(long cartId, Connection conn) throws SQLException {
        String sql = "UPDATE carts SET updated_at = NOW() WHERE id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, cartId);
            ps.executeUpdate();
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    /**
     * Helper method to map ResultSet to Cart object
     */
    private Cart mapResultSetToCart(ResultSet rs) throws SQLException {
        Cart cart = new Cart();
        cart.setId(rs.getLong("id"));
        cart.setUserId(rs.getLong("user_id"));
        cart.setCreatedAt(rs.getTimestamp("created_at").toInstant());
        cart.setUpdatedAt(rs.getTimestamp("updated_at").toInstant());
        return cart;
    }
    
    @Override
    public List<Cart> findAll(int limit, int offset) throws SQLException {
        String sql = "SELECT id, user_id, created_at, updated_at FROM carts ORDER BY created_at DESC";
        if (limit > 0) {
            sql += " LIMIT ? OFFSET ?";
        }
        
        List<Cart> carts = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (limit > 0) {
                ps.setInt(1, limit);
                ps.setInt(2, offset);
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    carts.add(mapResultSetToCart(rs));
                }
                return carts;
            }
        }
    }
    
    @Override
    public boolean exists(Long id) throws SQLException {
        String sql = "SELECT 1 FROM carts WHERE id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    @Override
    public long count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM carts";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getLong(1);
            }
            return 0;
        }
    }
}