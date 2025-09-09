package com.utephonehub.dto.checkout;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * DTO for checkout response
 * Used in Module 6: Return information after successful checkout
 */
public class CheckoutResponseDTO {
    private int orderId;
    private Timestamp orderDate;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private String status;
    private String message;

    // Default constructor
    public CheckoutResponseDTO() {}

    // Constructor
    public CheckoutResponseDTO(int orderId, Timestamp orderDate, BigDecimal totalAmount, String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.discountAmount = BigDecimal.ZERO;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Calculate final amount after discount
     */
    public BigDecimal getFinalAmount() {
        if (totalAmount == null) return BigDecimal.ZERO;
        if (discountAmount == null) return totalAmount;
        return totalAmount.subtract(discountAmount);
    }

    @Override
    public String toString() {
        return "CheckoutResponseDTO{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", discountAmount=" + discountAmount +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
