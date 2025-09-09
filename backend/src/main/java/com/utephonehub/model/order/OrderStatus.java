package com.utephonehub.model.order;

/**
 * Enum representing different states of an Order
 */
public enum OrderStatus {
    PENDING("Chờ xử lý"),
    CONFIRMED("Đã xác nhận"), 
    PREPARING("Đang chuẩn bị"),
    SHIPPING("Đang giao hàng"),
    DELIVERED("Đã giao hàng"),
    CANCELLED("Đã hủy"),
    RETURNED("Đã trả hàng");

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
}
