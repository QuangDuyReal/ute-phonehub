package com.utephonehub.cart.model;

public class CartItem {
    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private String thumbnailUrl;

    public CartItem() {
    }

    public CartItem(int productId, String productName, double price, int quantity, String thumbnailUrl) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.thumbnailUrl = thumbnailUrl;
    }

    // Gọi get/set của các thuộc tính
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}