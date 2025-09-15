package com.utephonehub.model.auth;

import java.sql.Timestamp;

/**
 * Domain model representing a password reset token. Mirrors the `password_reset_tokens` table.
 */
public class PasswordResetToken {

    private int id;
    private int userId;
    private String token;
    private Timestamp expiryDate;
    private Timestamp createdAt;

    // Constructors
    public PasswordResetToken() {
    }

    public PasswordResetToken(int userId, String token, Timestamp expiryDate) {
        this.userId = userId;
        this.token = token;
        this.expiryDate = expiryDate;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public PasswordResetToken(int id, int userId, String token, Timestamp expiryDate, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.expiryDate = expiryDate;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isExpired() {
        return new Timestamp(System.currentTimeMillis()).after(expiryDate);
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" +
                "id=" + id +
                ", userId=" + userId +
                ", token='" + token + '\'' +
                ", expiryDate=" + expiryDate +
                ", createdAt=" + createdAt +
                ", expired=" + isExpired() +
                '}';
    }
}