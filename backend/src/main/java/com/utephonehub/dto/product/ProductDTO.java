package com.utephonehub.dto.product;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.utephonehub.model.product.Product;

// Đây là lớp đại diện cho dữ liệu Sản phẩm sẽ được gửi ra ngoài qua API
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private int categoryId;
    private int brandId;
    private String specifications; // JSON string for technical specifications
    private List<String> images; // List of image URLs
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ProductDTO() {
    }

    // Constructor để dễ dàng chuyển đổi từ Entity sang DTO
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stockQuantity = product.getStockQuantity();
        this.categoryId = product.getCategoryId();
        this.brandId = product.getBrandId();
        this.specifications = product.getSpecifications();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
        // Note: images will be populated separately from ProductImage entities
    }

    // Getters and Setters...
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
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}