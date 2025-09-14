package com.utephonehub.dao.cart;

import com.utephonehub.model.cart.CartItem;
import com.utephonehub.dto.cart.CartItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * DAO interface for CartItem entity
 * Module M05 - Shopping Cart
 */
public interface CartItemDAO {
    
    /**
     * Add item to cart
     * @param cartItem Cart item to add
     * @return true if added successfully
     * @throws SQLException if database error occurs
     */
    boolean addToCart(CartItem cartItem) throws SQLException;
    
    /**
     * Add item to cart within a transaction
     * @param cartItem Cart item to add
     * @param conn Database connection for transaction
     * @return true if added successfully
     * @throws SQLException if database error occurs
     */
    boolean addToCart(CartItem cartItem, Connection conn) throws SQLException;
    
    /**
     * Update cart item quantity
     * @param cartId Cart ID
     * @param productId Product ID
     * @param quantity New quantity
     * @return true if updated successfully
     * @throws SQLException if database error occurs
     */
    boolean updateQuantity(long cartId, int productId, int quantity) throws SQLException;
    
    /**
     * Update cart item quantity within a transaction
     * @param cartId Cart ID
     * @param productId Product ID
     * @param quantity New quantity
     * @param conn Database connection for transaction
     * @return true if updated successfully
     * @throws SQLException if database error occurs
     */
    boolean updateQuantity(long cartId, int productId, int quantity, Connection conn) throws SQLException;
    
    /**
     * Remove item from cart
     * @param cartId Cart ID
     * @param productId Product ID
     * @return true if removed successfully
     * @throws SQLException if database error occurs
     */
    boolean removeFromCart(long cartId, int productId) throws SQLException;
    
    /**
     * Remove item from cart within a transaction
     * @param cartId Cart ID
     * @param productId Product ID
     * @param conn Database connection for transaction
     * @return true if removed successfully
     * @throws SQLException if database error occurs
     */
    boolean removeFromCart(long cartId, int productId, Connection conn) throws SQLException;
    
    /**
     * Get all items in a cart
     * @param cartId Cart ID
     * @return List of cart items
     * @throws SQLException if database error occurs
     */
    List<CartItem> findByCartId(long cartId) throws SQLException;
    
    /**
     * Get all items in a cart within a transaction
     * @param cartId Cart ID
     * @param conn Database connection for transaction
     * @return List of cart items
     * @throws SQLException if database error occurs
     */
    List<CartItem> findByCartId(long cartId, Connection conn) throws SQLException;
    
    /**
     * Get cart items with product details
     * @param cartId Cart ID
     * @return List of cart item DTOs with product information
     * @throws SQLException if database error occurs
     */
    List<CartItemDTO> findCartItemsWithProductDetails(long cartId) throws SQLException;
    
    /**
     * Get cart items with product details within a transaction
     * @param cartId Cart ID
     * @param conn Database connection for transaction
     * @return List of cart item DTOs with product information
     * @throws SQLException if database error occurs
     */
    List<CartItemDTO> findCartItemsWithProductDetails(long cartId, Connection conn) throws SQLException;
    
    /**
     * Find specific cart item
     * @param cartId Cart ID
     * @param productId Product ID
     * @return Optional containing CartItem if found
     * @throws SQLException if database error occurs
     */
    Optional<CartItem> findCartItem(long cartId, int productId) throws SQLException;
    
    /**
     * Find specific cart item within a transaction
     * @param cartId Cart ID
     * @param productId Product ID
     * @param conn Database connection for transaction
     * @return Optional containing CartItem if found
     * @throws SQLException if database error occurs
     */
    Optional<CartItem> findCartItem(long cartId, int productId, Connection conn) throws SQLException;
    
    /**
     * Clear all items from cart
     * @param cartId Cart ID
     * @return true if cleared successfully
     * @throws SQLException if database error occurs
     */
    boolean clearCart(long cartId) throws SQLException;
    
    /**
     * Clear all items from cart within a transaction
     * @param cartId Cart ID
     * @param conn Database connection for transaction
     * @return true if cleared successfully
     * @throws SQLException if database error occurs
     */
    boolean clearCart(long cartId, Connection conn) throws SQLException;
    
    /**
     * Count total items in cart
     * @param cartId Cart ID
     * @return Total number of items
     * @throws SQLException if database error occurs
     */
    int countCartItems(long cartId) throws SQLException;
    
    /**
     * Validate if requested quantity is available in stock
     * @param productId Product ID to check
     * @param requestedQuantity Quantity requested
     * @return true if stock is sufficient, false otherwise
     * @throws SQLException if database error occurs
     */
    boolean validateStock(int productId, int requestedQuantity) throws SQLException;
    
    /**
     * Validate if requested quantity is available in stock within a transaction
     * @param productId Product ID to check
     * @param requestedQuantity Quantity requested
     * @param conn Database connection for transaction
     * @return true if stock is sufficient, false otherwise
     * @throws SQLException if database error occurs
     */
    boolean validateStock(int productId, int requestedQuantity, Connection conn) throws SQLException;
}