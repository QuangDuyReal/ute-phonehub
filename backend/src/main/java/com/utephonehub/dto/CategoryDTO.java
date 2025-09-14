package com.utephonehub.dto;


public class CategoryDTO {
    private int id;
    private String name;
    private Integer parentId;
    private boolean status;

    // Default constructor for JSON deserialization
    public CategoryDTO() {}

    public CategoryDTO(int id, String name, Integer parentId, boolean status) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.status = status;
    }


    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public Integer getParentId() { return parentId; }
    public void setParentId(Integer parentId) { this.parentId = parentId; }


    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}
