package com.utephonehub.dto.order;

import com.utephonehub.model.order.OrderStatus;

/**
 * DTO for order status update request
 * Used in Module 7: Admin order management
 */
public class OrderStatusUpdateDTO {
    private OrderStatus status;
    private String notes;

    // Default constructor
    public OrderStatusUpdateDTO() {}

    // Constructor
    public OrderStatusUpdateDTO(OrderStatus status, String notes) {
        this.status = status;
        this.notes = notes;
    }

    // Getters and Setters
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Validate if the status is not null
     */
    public boolean isValid() {
        return status != null;
    }

    @Override
    public String toString() {
        return "OrderStatusUpdateDTO{" +
                "status='" + status + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
