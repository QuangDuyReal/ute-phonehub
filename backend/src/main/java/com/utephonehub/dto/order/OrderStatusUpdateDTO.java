package com.utephonehub.dto.order;

/**
 * DTO for order status update request
 * Used in Module 7: Admin order management
 */
public class OrderStatusUpdateDTO {
    private String status;
    private String notes;

    // Default constructor
    public OrderStatusUpdateDTO() {}

    // Constructor
    public OrderStatusUpdateDTO(String status, String notes) {
        this.status = status;
        this.notes = notes;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Validate if the status is not empty
     */
    public boolean isValid() {
        return status != null && !status.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "OrderStatusUpdateDTO{" +
                "status='" + status + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
