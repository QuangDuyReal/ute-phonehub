package com.utephonehub.dto;

public class CategoryDTO {
    private int id;
    private String name;
    private String slug;
    private String description;
    private String imageUrl;
    private Integer parentId;
    private int sortOrder;
    private boolean status;

    public CategoryDTO(int id, String name, String slug, String description,
                       String imageUrl, Integer parentId, int sortOrder, boolean status) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.imageUrl = imageUrl;
        this.parentId = parentId;
        this.sortOrder = sortOrder;
        this.status = status;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Integer getParentId() { return parentId; }
    public void setParentId(Integer parentId) { this.parentId = parentId; }

    public int getSortOrder() { return sortOrder; }
    public void setSortOrder(int sortOrder) { this.sortOrder = sortOrder; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}
