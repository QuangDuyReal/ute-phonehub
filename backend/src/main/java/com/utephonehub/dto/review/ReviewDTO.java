package com.utephonehub.dto.review;

import java.time.Instant;

/**
 * DTO for review with user information
 * Module M08 - Review & Comment
 */
public class ReviewDTO {
    private int reviewId;        // Added for identification
    private int productId;       // Added for context
    private int rating;          // Changed from long to int (1-5)
    private String comment;
    private Instant createdAt;   // Changed from Timestamp to Instant
    private String fullName;     // From users table join

    // Constructors
    public ReviewDTO() {}

    public ReviewDTO(int reviewId, int productId, int rating, String comment, Instant createdAt, String fullName) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
        this.fullName = fullName;
    }
    
    // Constructor for DAO query result
    public ReviewDTO(int rating, String comment, Instant createdAt, String fullName) {
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
        this.fullName = fullName;
    }

    // Getters & Setters
    public int getReviewId() { 
        return reviewId; 
    }
    
    public void setReviewId(int reviewId) { 
        this.reviewId = reviewId; 
    }
    
    public int getProductId() { 
        return productId; 
    }
    
    public void setProductId(int productId) { 
        this.productId = productId; 
    }
    
    public int getRating() { 
        return rating; 
    }
    
    public void setRating(int rating) { 
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

    public String getFullName() { 
        return fullName; 
    }
    
    public void setFullName(String fullName) { 
        this.fullName = fullName; 
    }
    
    @Override
    public String toString() {
        return "ReviewDTO{" +
                "reviewId=" + reviewId +
                ", productId=" + productId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
