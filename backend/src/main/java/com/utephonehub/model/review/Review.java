package com.utephonehub.model.review;

import java.time.Instant;

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
    private Instant createdAt;  // Changed from Timestamp to Instant
    private Instant updatedAt;  // Added missing field from database schema

    // Constructors
    public Review() {}
    
    public Review(int productId, int userId, int rating, String comment) {
        this.productId = productId;
        this.userId = userId;
        this.setRating(rating);  // Use setter for validation
        this.comment = comment;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public Review(int id, int productId, int userId, int rating, String comment, Instant createdAt, Instant updatedAt) {
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

    public Instant getCreatedAt() { 
        return createdAt; 
    }
    
    public void setCreatedAt(Instant createdAt) { 
        this.createdAt = createdAt; 
    }
    
    public Instant getUpdatedAt() { 
        return updatedAt; 
    }
    
    public void setUpdatedAt(Instant updatedAt) { 
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
