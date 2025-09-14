package com.utephonehub.dto;


public class BrandDTO {
    private int id;
    private String name;
    private int categoryId;
    private boolean status;

    // Default constructor for JSON deserialization
    public BrandDTO() {}

    public BrandDTO(int id, String name, int categoryId, boolean status) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.status = status;
    }


    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }


    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}
