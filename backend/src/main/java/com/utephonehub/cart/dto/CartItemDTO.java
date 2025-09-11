package com.utephonehub.cart.dto;

public class CartItemDTO {
    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private String thumbnailUrl; // Thêm ảnh sản phẩm
    private double totalPrice; // Thêm tổng tiền

    public CartItemDTO(int productId, String productName, double price, int quantity, String thumbnailUrl, double totalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.thumbnailUrl = thumbnailUrl;
        this.totalPrice = totalPrice;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
