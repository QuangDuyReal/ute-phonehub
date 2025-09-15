package com.utephonehub.model.review;

import java.sql.Timestamp;

/**
 * Domain model representing a product review. Mirrors the `reviews` table.
 * Module M08 - Review & Comment
 */
public class Review {
    private int id;  // Changed from reviewId to match database schema
    private int productId;
    private int userId;
    private int rating;  // CHECK constraint: 1-5
    private String comment;
    private Timestamp createdAt;  // Changed from Instant to Timestamp for JDBC compatibility
    private Timestamp updatedAt;  // Added missing field from database schema

    // Constructors
    public Review() {}
    
    public Review(int productId, int userId, int rating, String comment) {
        this.productId = productId;
        this.userId = userId;
        this.setRating(rating);  // Use setter for validation
        this.comment = comment;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public Review(int id, int productId, int userId, int rating, String comment, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.setRating(rating);
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getId() { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }

    public int getProductId() { 
        return productId; 
    }
    
    public void setProductId(int productId) { 
        this.productId = productId; 
    }

    public int getUserId() { 
        return userId; 
    }
    
    public void setUserId(int userId) { 
        this.userId = userId; 
    }

    public int getRating() { 
        return rating; 
    }
    
    public void setRating(int rating) { 
        // Validation for rating constraint (1-5)
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rating = rating; 
    }

    public String getComment() { 
        return comment; 
    }
    
    public void setComment(String comment) { 
        this.comment = comment; 
    }

    public Timestamp getCreatedAt() { 
        return createdAt; 
    }
    
    public void setCreatedAt(Timestamp createdAt) { 
        this.createdAt = createdAt; 
    }
    
    public Timestamp getUpdatedAt() { 
        return updatedAt; 
    }
    
    public void setUpdatedAt(Timestamp updatedAt) { 
        this.updatedAt = updatedAt; 
    }
    
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", productId=" + productId +
                ", userId=" + userId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
