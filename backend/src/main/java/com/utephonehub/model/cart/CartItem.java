package com.utephonehub.model.cart;

import java.time.Instant;

/**
 * Domain model representing a cart item. Mirrors the `cart_items` table.
 * Module M05 - Shopping Cart
 */
public class CartItem {
    
    private long cartId;
    private int productId;
    private int quantity;
    private Instant addedAt;
    
    // Constructors
    public CartItem() {
    }
    
    public CartItem(long cartId, int productId, int quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.addedAt = Instant.now();
    }
    
    public CartItem(long cartId, int productId, int quantity, Instant addedAt) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.addedAt = addedAt;
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
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public Instant getAddedAt() {
        return addedAt;
    }
    
    public void setAddedAt(Instant addedAt) {
        this.addedAt = addedAt;
    }
    
    @Override
    public String toString() {
        return "CartItem{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", addedAt=" + addedAt +
                '}';
    }
}