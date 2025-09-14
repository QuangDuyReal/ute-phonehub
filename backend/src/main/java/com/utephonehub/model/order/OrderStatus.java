package com.utephonehub.model.order;

/**
 * Enum representing different states of an Order
 */
public enum OrderStatus {
    PENDING("pending"),
    PROCESSING("processing"), 
    SHIPPED("shipped"),
    COMPLETED("completed"),
    CANCELLED("cancelled");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Convert string to OrderStatus enum
     */
    public static OrderStatus fromString(String status) {
        if (status == null) {
            return PENDING;
        }
        
        try {
            return OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return PENDING;
        }
    }

    /**
     * Get Vietnamese display name for status
     */
    public String getVietnameseDisplayName() {
        switch (this) {
            case PENDING: return "Chờ xử lý";
            case PROCESSING: return "Đang xử lý";
            case SHIPPED: return "Đang giao hàng";
            case COMPLETED: return "Hoàn thành";
            case CANCELLED: return "Đã hủy";
            default: return displayName;
        }
    }
}
