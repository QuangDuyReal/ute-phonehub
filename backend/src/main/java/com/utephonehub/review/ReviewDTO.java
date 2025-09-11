package com.utephonehub.review;

import java.sql.Timestamp;

public class ReviewDTO {
    private int rating;
    private String comment;
    private Timestamp createdAt;
    private String fullName; // lấy từ bảng users

    public ReviewDTO() {}

    public ReviewDTO(int rating, String comment, Timestamp createdAt, String fullName) {
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
        this.fullName = fullName;
    }

    // Getters & Setters
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
}
