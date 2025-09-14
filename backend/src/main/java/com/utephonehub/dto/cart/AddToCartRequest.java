package com.utephonehub.dto.cart;

/**
 * Request DTO for adding items to cart
 * Module M05 - Shopping Cart
 */
public class AddToCartRequest {
    
    private int productId;
    private int quantity;
    
    // Constructors
    public AddToCartRequest() {
    }
    
    public AddToCartRequest(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
    
    // Getters and Setters
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
    
    @Override
    public String toString() {
        return "AddToCartRequest{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}