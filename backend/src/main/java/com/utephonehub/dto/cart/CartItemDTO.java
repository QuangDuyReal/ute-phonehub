package com.utephonehub.dto.cart;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * DTO for cart item with product information
 * Module M05 - Shopping Cart
 */
public class CartItemDTO {
    
    private int cartId;
    private int productId;
    private String productName;
    private BigDecimal productPrice;
    private String productImageUrl;
    private int quantity;
    private BigDecimal subtotal; // quantity * productPrice
    private Timestamp addedAt;
    private int stockQuantity; // Current stock to check availability
    
    // Constructors
    public CartItemDTO() {
    }
    
    public CartItemDTO(int cartId, int productId, String productName, BigDecimal productPrice, 
                      String productImageUrl, int quantity, Timestamp addedAt, int stockQuantity) {
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
    public int getCartId() {
        return cartId;
    }
    
    public void setCartId(int cartId) {
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
    
    public Timestamp getAddedAt() {
        return addedAt;
    }
    
    public void setAddedAt(Timestamp addedAt) {
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