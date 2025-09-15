package com.utephonehub.model.product;

/**
 * Product status enum consistent with `products.status` column.
 */
public enum ProductStatus {
    active,
    inactive,
    discontinued;

    public static ProductStatus fromDatabase(String value) {
        if (value == null) return active;
        try {
            return ProductStatus.valueOf(value);
        } catch (IllegalArgumentException ex) {
            return active;
        }
    }

    public String toDatabase() {
        return name();
    }
}