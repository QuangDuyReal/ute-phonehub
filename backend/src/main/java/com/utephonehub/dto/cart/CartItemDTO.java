package com.utephonehub.dto.cart;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for cart item with product information
 * Module M05 - Shopping Cart
 */
public class CartItemDTO {
    
    private long cartId;
    private int productId;
    private String productName;
    private BigDecimal productPrice;
    private String productImageUrl;
    private int quantity;
    private BigDecimal subtotal; // quantity * productPrice
    private Instant addedAt;
    private int stockQuantity; // Current stock to check availability
    
    // Constructors
    public CartItemDTO() {
    }
    
    public CartItemDTO(long cartId, int productId, String productName, BigDecimal productPrice, 
                      String productImageUrl, int quantity, Instant addedAt, int stockQuantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;
        this.quantity = quantity;
        this.addedAt = addedAt;
        this.stockQuantity = stockQuantity;
        this.subtotal = productPrice.multiply(BigDecimal.valueOf(quantity));
    }
    
    // Getters and Setters
    public long getCartId() {
        return cartId;
    }
    
    public void setCartId(long cartId) {
        this.cartId = cartId;
    }
    
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public BigDecimal getProductPrice() {
        return productPrice;
    }
    
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
        // Recalculate subtotal when price changes
        if (this.quantity > 0) {
            this.subtotal = productPrice.multiply(BigDecimal.valueOf(this.quantity));
        }
    }
    
    public String getProductImageUrl() {
        return productImageUrl;
    }
    
    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        // Recalculate subtotal when quantity changes
        if (this.productPrice != null) {
            this.subtotal = this.productPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }
    
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
    public Instant getAddedAt() {
        return addedAt;
    }
    
    public void setAddedAt(Instant addedAt) {
        this.addedAt = addedAt;
    }
    
    public int getStockQuantity() {
        return stockQuantity;
    }
    
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    @Override
    public String toString() {
        return "CartItemDTO{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", addedAt=" + addedAt +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}