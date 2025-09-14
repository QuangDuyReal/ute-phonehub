package com.utephonehub.model.category;


import java.sql.Timestamp;


public class Category {
    private int id;
    private String name;
    private Integer parentId; // Dùng cho danh mục đa cấp
    private Boolean status;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    // Constructor rỗng
    public Category() {}


    // Constructor đầy đủ
    public Category(int id, String name, Integer parentId, Boolean status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public Integer getParentId() { return parentId; }
    public void setParentId(Integer parentId) { this.parentId = parentId; }


    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }


    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }


    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}
