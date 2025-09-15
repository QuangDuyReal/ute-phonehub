package com.utephonehub.model.cart;

import java.sql.Timestamp;

/**
 * Domain model representing a cart item. Mirrors the `cart_items` table.
 * Module M05 - Shopping Cart
 */
public class CartItem {
    
    private int cartId;
    private int productId;
    private int quantity;
    private Timestamp addedAt;
    
    // Constructors
    public CartItem() {
    }
    
    public CartItem(int cartId, int productId, int quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.addedAt = new Timestamp(System.currentTimeMillis());
    }
    
    public CartItem(int cartId, int productId, int quantity, Timestamp addedAt) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.addedAt = addedAt;
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
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public Timestamp getAddedAt() {
        return addedAt;
    }
    
    public void setAddedAt(Timestamp addedAt) {
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