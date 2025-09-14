package com.utephonehub.model.product;

import java.time.Instant;

/**
 * Domain model representing a product image. Mirrors the `product_images` table.
 * Module M04 - Product Display & Search
 */
public class ProductImage {
    
    private long id;
    private long productId;
    private String imageUrl;
    private boolean isThumbnail;
    private long displayOrder;
    private Instant createdAt;
    private Instant updatedAt;
    
    // Constructors
    public ProductImage() {
    }
    
    public ProductImage(long productId, String imageUrl, boolean isThumbnail, long displayOrder) {
        this.productId = productId;
        this.imageUrl = imageUrl;
        this.isThumbnail = isThumbnail;
        this.displayOrder = displayOrder;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
    
    public ProductImage(long id, long productId, String imageUrl, boolean isThumbnail, 
                       long displayOrder, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.productId = productId;
        this.imageUrl = imageUrl;
        this.isThumbnail = isThumbnail;
        this.displayOrder = displayOrder;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters and Setters
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public long getProductId() {
        return productId;
    }
    
    public void setProductId(long productId) {
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
    
    public Instant getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    
    public Instant getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Instant updatedAt) {
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