package com.utephonehub.dao.review;

import com.utephonehub.dao.GenericDAO;
import com.utephonehub.model.review.Review;
import com.utephonehub.dto.review.ReviewDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * DAO interface for Review entity
 * Module M08 - Review & Comment
 */
public interface ReviewDAOInterface extends GenericDAO<Review, Integer> {
    
    // Business logic methods for FR-REVIEW-01
    /**
     * Check if user can review product (must have completed purchase)
     * @param userId User ID
     * @param productId Product ID  
     * @return true if user can review this product
     * @throws SQLException if database error occurs
     */
    boolean canUserReviewProduct(int userId, int productId) throws SQLException;
    
    /**
     * Check if user can review product within a transaction
     * @param userId User ID
     * @param productId Product ID
     * @param conn Database connection for transaction
     * @return true if user can review this product
     * @throws SQLException if database error occurs
     */
    boolean canUserReviewProduct(int userId, int productId, Connection conn) throws SQLException;
    
    // CRUD operations with business validation
    /**
     * Add review with business validation
     * @param review Review to add
     * @return Generated review ID
     * @throws SQLException if database error occurs
     * @throws IllegalArgumentException if user cannot review product
     */
    Integer addReviewWithValidation(Review review) throws SQLException;
    
    /**
     * Add review with business validation within a transaction
     * @param review Review to add
     * @param conn Database connection for transaction
     * @return Generated review ID
     * @throws SQLException if database error occurs
     * @throws IllegalArgumentException if user cannot review product
     */
    Integer addReviewWithValidation(Review review, Connection conn) throws SQLException;
    
    /**
     * Update review with ownership validation
     * @param reviewId Review ID to update
     * @param rating New rating (1-5)
     * @param comment New comment
     * @param userId User ID (for ownership validation)
     * @return true if updated successfully
     * @throws SQLException if database error occurs
     * @throws IllegalArgumentException if user doesn't own review
     */
    boolean updateReview(int reviewId, int rating, String comment, int userId) throws SQLException;
    
    /**
     * Update review with ownership validation within a transaction
     * @param reviewId Review ID to update
     * @param rating New rating (1-5)
     * @param comment New comment
     * @param userId User ID (for ownership validation)
     * @param conn Database connection for transaction
     * @return true if updated successfully
     * @throws SQLException if database error occurs
     * @throws IllegalArgumentException if user doesn't own review
     */
    boolean updateReview(int reviewId, int rating, String comment, int userId, Connection conn) throws SQLException;
    
    // Query methods
    /**
     * Get reviews for a product with user details
     * @param productId Product ID
     * @return List of review DTOs with user information
     * @throws SQLException if database error occurs
     */
    List<ReviewDTO> findReviewsByProduct(int productId) throws SQLException;
    
    /**
     * Get reviews for a product with user details within a transaction
     * @param productId Product ID
     * @param conn Database connection for transaction
     * @return List of review DTOs with user information
     * @throws SQLException if database error occurs
     */
    List<ReviewDTO> findReviewsByProduct(int productId, Connection conn) throws SQLException;
    
    /**
     * Find review by user and product (for UNIQUE constraint check)
     * @param userId User ID
     * @param productId Product ID
     * @return Optional containing Review if found, empty otherwise
     * @throws SQLException if database error occurs
     */
    Optional<Review> findByUserAndProduct(int userId, int productId) throws SQLException;
    
    /**
     * Find review by user and product within a transaction
     * @param userId User ID
     * @param productId Product ID
     * @param conn Database connection for transaction
     * @return Optional containing Review if found, empty otherwise
     * @throws SQLException if database error occurs
     */
    Optional<Review> findByUserAndProduct(int userId, int productId, Connection conn) throws SQLException;
    
    // Authorization methods
    /**
     * Check if user owns the review
     * @param reviewId Review ID
     * @param userId User ID
     * @return true if user owns the review
     * @throws SQLException if database error occurs
     */
    boolean isReviewOwner(int reviewId, int userId) throws SQLException;
    
    /**
     * Check if user owns the review within a transaction
     * @param reviewId Review ID
     * @param userId User ID
     * @param conn Database connection for transaction
     * @return true if user owns the review
     * @throws SQLException if database error occurs
     */
    boolean isReviewOwner(int reviewId, int userId, Connection conn) throws SQLException;
    
    /**
     * Delete review with ownership/admin validation
     * @param reviewId Review ID to delete
     * @param userId User ID (for ownership validation, 0 for admin)
     * @return true if deleted successfully
     * @throws SQLException if database error occurs
     * @throws IllegalArgumentException if user doesn't have permission
     */
    boolean deleteReviewWithValidation(int reviewId, int userId) throws SQLException;
    
    /**
     * Delete review with ownership/admin validation within a transaction
     * @param reviewId Review ID to delete
     * @param userId User ID (for ownership validation, 0 for admin)
     * @param conn Database connection for transaction
     * @return true if deleted successfully
     * @throws SQLException if database error occurs
     * @throws IllegalArgumentException if user doesn't have permission
     */
    boolean deleteReviewWithValidation(int reviewId, int userId, Connection conn) throws SQLException;
}