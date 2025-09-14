package com.utephonehub.model.cart;

import java.time.Instant;

/**
 * Domain model representing a shopping cart. Mirrors the `carts` table.
 * Module M05 - Shopping Cart
 */
public class Cart {
    
    private long id;
    private long userId;
    private Instant createdAt;
    private Instant updatedAt;
    
    // Constructors
    public Cart() {
    }
    
    public Cart(long userId) {
        this.userId = userId;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
    
    public Cart(long id, long userId, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters and Setters
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public long getUserId() {
        return userId;
    }
    
    public void setUserId(long userId) {
        this.userId = userId;
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
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}