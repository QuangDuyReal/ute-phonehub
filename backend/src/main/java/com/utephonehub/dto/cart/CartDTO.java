package com.utephonehub.dto.cart;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * DTO for shopping cart with items and total information
 * Module M05 - Shopping Cart
 */
public class CartDTO {
    
    private long id;
    private long userId;
    private List<CartItemDTO> items;
    private long totalItems; // Total quantity of all items
    private BigDecimal totalAmount; // Total price of all items
    private Instant createdAt;
    private Instant updatedAt;
    
    // Constructors
    public CartDTO() {
    }
    
    public CartDTO(long id, long userId, List<CartItemDTO> items, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        calculateTotals();
    }
    
    // Helper method to calculate totals
    public void calculateTotals() {
        if (items == null || items.isEmpty()) {
            this.totalItems = 0;
            this.totalAmount = BigDecimal.ZERO;
            return;
        }
        
        this.totalItems = items.stream()
                .mapToLong(CartItemDTO::getQuantity)
                .sum();
                
        this.totalAmount = items.stream()
                .map(CartItemDTO::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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
    
    public List<CartItemDTO> getItems() {
        return items;
    }
    
    public void setItems(List<CartItemDTO> items) {
        this.items = items;
        calculateTotals();
    }
    
    public long getTotalItems() {
        return totalItems;
    }
    
    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
        return "CartDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", totalItems=" + totalItems +
                ", totalAmount=" + totalAmount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", itemsCount=" + (items != null ? items.size() : 0) +
                '}';
    }
}