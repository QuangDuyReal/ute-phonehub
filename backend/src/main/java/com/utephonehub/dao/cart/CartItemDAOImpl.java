package com.utephonehub.dao.cart;

import com.utephonehub.model.cart.CartItem;
import com.utephonehub.dto.cart.CartItemDTO;
import com.utephonehub.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of CartItemDAO interface
 * Module M05 - Shopping Cart
 */
public class CartItemDAOImpl implements CartItemDAO {
    
    @Override
    public boolean addToCart(CartItem cartItem) throws SQLException {
        return addToCart(cartItem, null);
    }
    
    @Override
    public boolean addToCart(CartItem cartItem, Connection conn) throws SQLException {
        // First validate stock availability
        if (!validateStock(cartItem.getProductId(), cartItem.getQuantity(), conn)) {
            throw new SQLException("Product is out of stock or requested quantity exceeds available stock.");
        }
        
        // Check if item already exists in cart
        Optional<CartItem> existingItem = findCartItem(cartItem.getCartId(), cartItem.getProductId(), conn);
        
        if (existingItem.isPresent()) {
            // Update quantity if item already exists - validate total quantity
            int newQuantity = existingItem.get().getQuantity() + cartItem.getQuantity();
            if (!validateStock(cartItem.getProductId(), newQuantity, conn)) {
                throw new SQLException("Adding this quantity would exceed available stock.");
            }
            return updateQuantity(cartItem.getCartId(), cartItem.getProductId(), newQuantity, conn);
        } else {
            // Insert new item
            String sql = "INSERT INTO cart_items (cart_id, product_id, quantity, added_at) VALUES (?, ?, ?, ?)";
            
            boolean shouldCloseConnection = (conn == null);
            if (conn == null) {
                conn = DBUtil.getConnection();
            }
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, cartItem.getCartId());
                ps.setInt(2, cartItem.getProductId());
                ps.setInt(3, cartItem.getQuantity());
                ps.setTimestamp(4, cartItem.getAddedAt());
                
                return ps.executeUpdate() > 0;
            } finally {
                if (shouldCloseConnection && conn != null) {
                    conn.close();
                }
            }
        }
    }
    
    @Override
    public boolean updateQuantity(int cartId, int productId, int quantity) throws SQLException {
        return updateQuantity(cartId, productId, quantity, null);
    }
    
    @Override
    public boolean updateQuantity(int cartId, int productId, int quantity, Connection conn) throws SQLException {
        // Validate quantity is positive
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        
        // Validate stock availability
        if (!validateStock(productId, quantity, conn)) {
            throw new SQLException("Requested quantity exceeds available stock.");
        }
        
        String sql = "UPDATE cart_items SET quantity = ? WHERE cart_id = ? AND product_id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, cartId);
            ps.setInt(3, productId);
            
            return ps.executeUpdate() > 0;
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public boolean removeFromCart(int cartId, int productId) throws SQLException {
        return removeFromCart(cartId, productId, null);
    }
    
    @Override
    public boolean removeFromCart(int cartId, int productId, Connection conn) throws SQLException {
        String sql = "DELETE FROM cart_items WHERE cart_id = ? AND product_id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            
            return ps.executeUpdate() > 0;
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public List<CartItem> findByCartId(int cartId) throws SQLException {
        return findByCartId(cartId, null);
    }
    
    @Override
    public List<CartItem> findByCartId(int cartId, Connection conn) throws SQLException {
        String sql = "SELECT cart_id, product_id, quantity, added_at FROM cart_items WHERE cart_id = ? ORDER BY added_at DESC";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        List<CartItem> items = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(mapResultSetToCartItem(rs));
                }
                return items;
            }
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public List<CartItemDTO> findCartItemsWithProductDetails(int cartId) throws SQLException {
        return findCartItemsWithProductDetails(cartId, null);
    }
    
    @Override
    public List<CartItemDTO> findCartItemsWithProductDetails(int cartId, Connection conn) throws SQLException {
        String sql = """
            SELECT ci.cart_id, ci.product_id, ci.quantity, ci.added_at,
                   p.name as product_name, p.price as product_price, p.stock_quantity,
                   COALESCE(pi.image_url, '') as product_image_url
            FROM cart_items ci
            JOIN products p ON ci.product_id = p.id
            LEFT JOIN product_images pi ON p.id = pi.product_id AND pi.is_thumbnail = true
            WHERE ci.cart_id = ?
            ORDER BY ci.added_at DESC
            """;
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        List<CartItemDTO> items = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CartItemDTO item = new CartItemDTO(
                        rs.getInt("cart_id"),
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getBigDecimal("product_price"),
                        rs.getString("product_image_url"),
                        rs.getInt("quantity"),
                        rs.getTimestamp("added_at"),
                        rs.getInt("stock_quantity")
                    );
                    items.add(item);
                }
                return items;
            }
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public Optional<CartItem> findCartItem(int cartId, int productId) throws SQLException {
        return findCartItem(cartId, productId, null);
    }
    
    @Override
    public Optional<CartItem> findCartItem(int cartId, int productId, Connection conn) throws SQLException {
        String sql = "SELECT cart_id, product_id, quantity, added_at FROM cart_items WHERE cart_id = ? AND product_id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, cartId);
            ps.setInt(2, productId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToCartItem(rs));
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
    public boolean clearCart(int cartId) throws SQLException {
        return clearCart(cartId, null);
    }
    
    @Override
    public boolean clearCart(int cartId, Connection conn) throws SQLException {
        String sql = "DELETE FROM cart_items WHERE cart_id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, cartId);
            return ps.executeUpdate() >= 0; // Returns true even if 0 rows affected (empty cart)
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public int countCartItems(int cartId) throws SQLException {
        String sql = "SELECT SUM(quantity) FROM cart_items WHERE cart_id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, cartId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            }
        }
    }
    
    /**
     * Helper method to map ResultSet to CartItem object
     */
    private CartItem mapResultSetToCartItem(ResultSet rs) throws SQLException {
        CartItem item = new CartItem();
        item.setCartId(rs.getInt("cart_id"));
        item.setProductId(rs.getInt("product_id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setAddedAt(rs.getTimestamp("added_at"));
        return item;
    }
    
    @Override
    public boolean validateStock(int productId, int requestedQuantity) throws SQLException {
        return validateStock(productId, requestedQuantity, null);
    }
    
    @Override
    public boolean validateStock(int productId, int requestedQuantity, Connection conn) throws SQLException {
        String sql = "SELECT stock_quantity FROM products WHERE id = ? AND status = true";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int availableStock = rs.getInt("stock_quantity");
                    return availableStock >= requestedQuantity;
                }
                // Product not found or inactive
                return false;
            }
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
}