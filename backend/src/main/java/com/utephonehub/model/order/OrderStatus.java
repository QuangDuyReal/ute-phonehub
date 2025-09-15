package com.utephonehub.model.order;

/**
 * Enum representing different states of an Order
 * Mirrors the `order_status` enum in PostgreSQL database
 */
public enum OrderStatus {
    pending("pending"),
    processing("processing"), 
    shipped("shipped"),
    completed("completed"),
    cancelled("cancelled");

    private final String dbValue;

    OrderStatus(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }
    
    public String getDisplayName() {
        return dbValue;
    }

    /**
     * Convert string to OrderStatus enum
     */
    public static OrderStatus fromString(String status) {
        if (status == null) {
            return pending;
        }
        
        try {
            return OrderStatus.valueOf(status.toLowerCase());
        } catch (IllegalArgumentException e) {
            return pending;
        }
    }
    
    /**
     * Convert database string value to enum
     */
    public static OrderStatus fromDatabase(String value) {
        if (value == null) {
            return pending;
        }
        
        for (OrderStatus status : values()) {
            if (status.dbValue.equals(value)) {
                return status;
            }
        }
        return pending; // default fallback
    }
    
    /**
     * Convert enum to database string value
     */
    public String toDatabase() {
        return this.dbValue;
    }

    /**
     * Get Vietnamese display name for status
     */
    public String getVietnameseDisplayName() {
        switch (this) {
            case pending: return "Chờ xử lý";
            case processing: return "Đang xử lý";
            case shipped: return "Đang giao hàng";
            case completed: return "Hoàn thành";
            case cancelled: return "Đã hủy";
            default: return dbValue;
        }
    }
}
