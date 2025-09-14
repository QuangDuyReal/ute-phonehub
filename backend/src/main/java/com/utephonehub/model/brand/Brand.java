package com.utephonehub.model.brand;


import java.sql.Timestamp;


public class Brand {
    private int id;
    private String name;
    private int categoryId; // FK tới categories.id
    private Boolean status;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    // Constructor rỗng
    public Brand() {}


    // Constructor đầy đủ
    public Brand(int id, String name, int categoryId, Boolean status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }


    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }


    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }


    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}
