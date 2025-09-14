package com.utephonehub.dao.review;

import com.utephonehub.model.review.Review;
import com.utephonehub.dto.review.ReviewDTO;
import com.utephonehub.util.DBUtil;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of ReviewDAOInterface
 * Module M08 - Review & Comment
 */
public class ReviewDAOImpl implements ReviewDAOInterface {
    
    @Override
    public Integer create(Review review) throws SQLException {
        return create(review, null);
    }
    
    @Override
    public Integer create(Review review, Connection conn) throws SQLException {
        String sql = "INSERT INTO reviews (user_id, product_id, rating, comment, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, review.getUserId());
            ps.setInt(2, review.getProductId());
            ps.setInt(3, review.getRating());
            ps.setString(4, review.getComment());
            ps.setTimestamp(5, Timestamp.from(review.getCreatedAt()));
            ps.setTimestamp(6, Timestamp.from(review.getUpdatedAt()));
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    review.setId(generatedId);
                    return generatedId;
                }
                throw new SQLException("Creating review failed, no ID obtained.");
            }
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public Review findById(Integer id) throws SQLException {
        Optional<Review> review = findByIdOptional(id, null);
        return review.orElse(null);
    }
    
    private Optional<Review> findByIdOptional(Integer id, Connection conn) throws SQLException {
        String sql = "SELECT id, user_id, product_id, rating, comment, created_at, updated_at FROM reviews WHERE id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToReview(rs));
                }
            }
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
        return Optional.empty();
    }
    
    @Override
    public List<Review> findAll() throws SQLException {
        return findAllWithConnection(null);
    }
    
    private List<Review> findAllWithConnection(Connection conn) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT id, user_id, product_id, rating, comment, created_at, updated_at FROM reviews ORDER BY created_at DESC";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                reviews.add(mapResultSetToReview(rs));
            }
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
        return reviews;
    }
    
    @Override
    public boolean update(Review review) throws SQLException {
        return update(review, null);
    }
    
    @Override
    public boolean update(Review review, Connection conn) throws SQLException {
        String sql = "UPDATE reviews SET rating = ?, comment = ?, updated_at = ? WHERE id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, review.getRating());
            ps.setString(2, review.getComment());
            ps.setTimestamp(3, Timestamp.from(Instant.now()));
            ps.setInt(4, review.getId());
            
            return ps.executeUpdate() > 0;
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public boolean delete(Integer id) throws SQLException {
        return delete(id, null);
    }
    
    @Override
    public boolean delete(Integer id, Connection conn) throws SQLException {
        String sql = "DELETE FROM reviews WHERE id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    // Business logic methods implementation
    @Override
    public boolean canUserReviewProduct(int userId, int productId) throws SQLException {
        return canUserReviewProduct(userId, productId, null);
    }
    
    @Override
    public boolean canUserReviewProduct(int userId, int productId, Connection conn) throws SQLException {
        // Check if user has completed order containing this product (FR-REVIEW-01)
        String sql = """
            SELECT COUNT(*) FROM order_items oi
            JOIN orders o ON oi.order_id = o.id  
            WHERE o.user_id = ? AND oi.product_id = ? 
            AND o.status = 'completed'
        """;
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
        return false;
    }
    
    @Override
    public Integer addReviewWithValidation(Review review) throws SQLException {
        return addReviewWithValidation(review, null);
    }
    
    @Override
    public Integer addReviewWithValidation(Review review, Connection conn) throws SQLException {
        // Validate user can review product
        if (!canUserReviewProduct(review.getUserId(), review.getProductId(), conn)) {
            throw new IllegalArgumentException("User has not purchased this product or order is not completed");
        }
        
        // Check if user already reviewed this product (UNIQUE constraint)
        Optional<Review> existingReview = findByUserAndProduct(review.getUserId(), review.getProductId(), conn);
        if (existingReview.isPresent()) {
            throw new IllegalArgumentException("User has already reviewed this product");
        }
        
        // Create review
        return create(review, conn);
    }
    
    @Override
    public boolean updateReview(int reviewId, int rating, String comment, int userId) throws SQLException {
        return updateReview(reviewId, rating, comment, userId, null);
    }
    
    @Override
    public boolean updateReview(int reviewId, int rating, String comment, int userId, Connection conn) throws SQLException {
        // Validate ownership
        if (!isReviewOwner(reviewId, userId, conn)) {
            throw new IllegalArgumentException("User does not own this review");
        }
        
        String sql = "UPDATE reviews SET rating = ?, comment = ?, updated_at = ? WHERE id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rating);
            ps.setString(2, comment);
            ps.setTimestamp(3, Timestamp.from(Instant.now()));
            ps.setInt(4, reviewId);
            
            return ps.executeUpdate() > 0;
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public List<ReviewDTO> findReviewsByProduct(int productId) throws SQLException {
        return findReviewsByProduct(productId, null);
    }
    
    @Override
    public List<ReviewDTO> findReviewsByProduct(int productId, Connection conn) throws SQLException {
        List<ReviewDTO> reviews = new ArrayList<>();
        String sql = """
            SELECT r.id, r.rating, r.comment, r.created_at, u.full_name
            FROM reviews r
            JOIN users u ON r.user_id = u.id
            WHERE r.product_id = ?
            ORDER BY r.created_at DESC
        """;
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReviewDTO dto = new ReviewDTO(
                        rs.getInt("id"),
                        productId,
                        rs.getInt("rating"),
                        rs.getString("comment"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getString("full_name")
                    );
                    reviews.add(dto);
                }
            }
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
        return reviews;
    }
    
    @Override
    public Optional<Review> findByUserAndProduct(int userId, int productId) throws SQLException {
        return findByUserAndProduct(userId, productId, null);
    }
    
    @Override
    public Optional<Review> findByUserAndProduct(int userId, int productId, Connection conn) throws SQLException {
        String sql = "SELECT id, user_id, product_id, rating, comment, created_at, updated_at FROM reviews WHERE user_id = ? AND product_id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToReview(rs));
                }
            }
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
        return Optional.empty();
    }
    
    @Override
    public boolean isReviewOwner(int reviewId, int userId) throws SQLException {
        return isReviewOwner(reviewId, userId, null);
    }
    
    @Override
    public boolean isReviewOwner(int reviewId, int userId, Connection conn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM reviews WHERE id = ? AND user_id = ?";
        
        boolean shouldCloseConnection = (conn == null);
        if (conn == null) {
            conn = DBUtil.getConnection();
        }
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reviewId);
            ps.setInt(2, userId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } finally {
            if (shouldCloseConnection && conn != null) {
                conn.close();
            }
        }
        return false;
    }
    
    @Override
    public boolean deleteReviewWithValidation(int reviewId, int userId) throws SQLException {
        return deleteReviewWithValidation(reviewId, userId, null);
    }
    
    @Override
    public boolean deleteReviewWithValidation(int reviewId, int userId, Connection conn) throws SQLException {
        // userId = 0 means admin can delete any review
        if (userId != 0 && !isReviewOwner(reviewId, userId, conn)) {
            throw new IllegalArgumentException("User does not have permission to delete this review");
        }
        
        return delete(reviewId, conn);
    }
    
    // Helper methods
    private Review mapResultSetToReview(ResultSet rs) throws SQLException {
        return new Review(
            rs.getInt("id"),
            rs.getInt("product_id"),
            rs.getInt("user_id"),
            rs.getInt("rating"),
            rs.getString("comment"),
            rs.getTimestamp("created_at").toInstant(),
            rs.getTimestamp("updated_at").toInstant()
        );
    }
    
    // GenericDAO required methods
    @Override
    public List<Review> findAll(int limit, int offset) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT id, user_id, product_id, rating, comment, created_at, updated_at FROM reviews ORDER BY created_at DESC LIMIT ? OFFSET ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    reviews.add(mapResultSetToReview(rs));
                }
            }
        }
        return reviews;
    }
    
    @Override
    public boolean exists(Integer id) throws SQLException {
        String sql = "SELECT 1 FROM reviews WHERE id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    @Override
    public long count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM reviews";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getLong(1);
            }
        }
        return 0;
    }
}