package com.utephonehub.model.product;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private int categoryId;
    private int brandId;
    private String specifications; // JSON string for product technical specifications
    private boolean status = true; // Product status (true = active, false = inactive)
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructors, Getters, and Setters...
    public Product() {
        this.status = true; // Default status - active
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public int getBrandId() { return brandId; }
    public void setBrandId(int brandId) { this.brandId = brandId; }
    public String getSpecifications() { return specifications; }
    public void setSpecifications(String specifications) { this.specifications = specifications; }
    public boolean getStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public boolean isActive() { return status; }
    public void setActive(boolean active) { this.status = active; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}