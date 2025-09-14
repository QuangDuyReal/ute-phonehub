package com.utephonehub.dto.cart;

/**
 * Request DTO for updating cart item quantity
 * Module M05 - Shopping Cart
 */
public class UpdateCartItemRequest {
    
    private int quantity;
    
    // Constructors
    public UpdateCartItemRequest() {
    }
    
    public UpdateCartItemRequest(int quantity) {
        this.quantity = quantity;
    }
    
    // Getters and Setters
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "UpdateCartItemRequest{" +
                "quantity=" + quantity +
                '}';
    }
}