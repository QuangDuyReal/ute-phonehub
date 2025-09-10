package com.utephonehub.dto;

public class BrandDTO {
    private int id;
    private String name;
    private String slug;
    private String description;
    private String logoUrl;
    private int categoryId;
    private int sortOrder;
    private boolean status;

    public BrandDTO(int id, String name, String slug, String description,
                    String logoUrl, int categoryId, int sortOrder, boolean status) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.logoUrl = logoUrl;
        this.categoryId = categoryId;
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

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public int getSortOrder() { return sortOrder; }
    public void setSortOrder(int sortOrder) { this.sortOrder = sortOrder; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}
