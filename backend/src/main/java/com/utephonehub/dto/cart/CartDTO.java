package com.utephonehub.dto.cart;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * DTO for shopping cart with items and total information
 * Module M05 - Shopping Cart
 */
public class CartDTO {
    
    private int id;
    private int userId;
    private List<CartItemDTO> items;
    private long totalItems; // Total quantity of all items
    private BigDecimal totalAmount; // Total price of all items
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    // Constructors
    public CartDTO() {
    }
    
    public CartDTO(int id, int userId, List<CartItemDTO> items, Timestamp createdAt, Timestamp updatedAt) {
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