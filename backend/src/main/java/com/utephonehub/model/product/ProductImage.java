package com.utephonehub.model.product;

import java.sql.Timestamp;

/**
 * Domain model representing a product image. Mirrors the `product_images` table.
 * Module M04 - Product Display & Search
 */
public class ProductImage {
    
    private int id;
    private int productId;
    private String imageUrl;
    private boolean isThumbnail;
    private long displayOrder;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    // Constructors
    public ProductImage() {
    }
    
    public ProductImage(int productId, String imageUrl, boolean isThumbnail, long displayOrder) {
        this.productId = productId;
        this.imageUrl = imageUrl;
        this.isThumbnail = isThumbnail;
        this.displayOrder = displayOrder;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
    
    public ProductImage(int id, int productId, String imageUrl, boolean isThumbnail, 
                       long displayOrder, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.productId = productId;
        this.imageUrl = imageUrl;
        this.isThumbnail = isThumbnail;
        this.displayOrder = displayOrder;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public boolean isThumbnail() {
        return isThumbnail;
    }
    
    public void setThumbnail(boolean thumbnail) {
        isThumbnail = thumbnail;
    }
    
    public long getDisplayOrder() {
        return displayOrder;
    }
    
    public void setDisplayOrder(long displayOrder) {
        this.displayOrder = displayOrder;
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
        return "ProductImage{" +
                "id=" + id +
                ", productId=" + productId +
                ", imageUrl='" + imageUrl + '\'' +
                ", isThumbnail=" + isThumbnail +
                ", displayOrder=" + displayOrder +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}