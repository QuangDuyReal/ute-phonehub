package com.utephonehub.dao.cart;

import com.utephonehub.dao.GenericDAO;
import com.utephonehub.model.cart.Cart;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 * DAO interface for Cart entity
 * Module M05 - Shopping Cart
 */
public interface CartDAO extends GenericDAO<Cart, Long> {
    
    /**
     * Find cart by user ID
     * @param userId User ID to search for
     * @return Optional containing Cart if found, empty otherwise
     * @throws SQLException if database error occurs
     */
    Optional<Cart> findByUserId(long userId) throws SQLException;
    
    /**
     * Find cart by user ID within a transaction
     * @param userId User ID to search for
     * @param conn Database connection for transaction
     * @return Optional containing Cart if found, empty otherwise
     * @throws SQLException if database error occurs
     */
    Optional<Cart> findByUserId(long userId, Connection conn) throws SQLException;
    
    /**
     * Create or get existing cart for user
     * @param userId User ID
     * @return Cart (existing or newly created)
     * @throws SQLException if database error occurs
     */
    Cart getOrCreateCartForUser(long userId) throws SQLException;
    
    /**
     * Create or get existing cart for user within a transaction
     * @param userId User ID
     * @param conn Database connection for transaction
     * @return Cart (existing or newly created)
     * @throws SQLException if database error occurs
     */
    Cart getOrCreateCartForUser(long userId, Connection conn) throws SQLException;
    
    /**
     * Update cart's updated_at timestamp
     * @param cartId Cart ID to update
     * @throws SQLException if database error occurs
     */
    void updateTimestamp(long cartId) throws SQLException;
    
    /**
     * Update cart's updated_at timestamp within a transaction
     * @param cartId Cart ID to update
     * @param conn Database connection for transaction
     * @throws SQLException if database error occurs
     */
    void updateTimestamp(long cartId, Connection conn) throws SQLException;
}