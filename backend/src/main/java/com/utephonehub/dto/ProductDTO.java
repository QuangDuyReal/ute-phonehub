package com.utephonehub.dto;

import com.utephonehub.model.Product;
import java.math.BigDecimal;

// Đây là lớp đại diện cho dữ liệu Sản phẩm sẽ được gửi ra ngoài qua API
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private int categoryId;
    private int brandId;

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
}