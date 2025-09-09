package com.utephonehub.model.order;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Entity class representing an OrderItem in the database
 * Maps to the 'order_items' table
 */
public class OrderItem {
    private int orderId;
    private int productId;
    private int quantity;
    private BigDecimal price; // Price at the time of order (not current price)
    private BigDecimal subtotal; // quantity * price
    private Timestamp createdAt;

    // Default constructor
    public OrderItem() {}

    // Constructor with essential fields
    public OrderItem(int orderId, int productId, int quantity, BigDecimal price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = price.multiply(BigDecimal.valueOf(quantity));
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        // Recalculate subtotal when quantity changes
        if (this.price != null) {
            this.subtotal = this.price.multiply(BigDecimal.valueOf(quantity));
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        // Recalculate subtotal when price changes
        if (price != null) {
            this.subtotal = price.multiply(BigDecimal.valueOf(this.quantity));
        }
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Calculate subtotal based on current quantity and price
     */
    public void calculateSubtotal() {
        if (this.price != null) {
            this.subtotal = this.price.multiply(BigDecimal.valueOf(this.quantity));
        }
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", subtotal=" + subtotal +
                ", createdAt=" + createdAt +
                '}';
    }
}
