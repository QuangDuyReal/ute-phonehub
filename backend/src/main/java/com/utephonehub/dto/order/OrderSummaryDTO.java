package com.utephonehub.dto.order;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * DTO for order summary information
 * Used in Module 7: Order listing and management
 */
public class OrderSummaryDTO {
    private int id;
    private Timestamp orderDate;
    private String status;
    private String statusDisplayName;
    private BigDecimal totalAmount;
    private int itemCount;
    private String recipientName;
    private String voucherCode;
    private BigDecimal discountAmount;

    // Default constructor
    public OrderSummaryDTO() {}

    // Constructor
    public OrderSummaryDTO(int id, Timestamp orderDate, String status, 
                          BigDecimal totalAmount, int itemCount, String recipientName) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.itemCount = itemCount;
        this.recipientName = recipientName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDisplayName() {
        return statusDisplayName;
    }

    public void setStatusDisplayName(String statusDisplayName) {
        this.statusDisplayName = statusDisplayName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public String toString() {
        return "OrderSummaryDTO{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", statusDisplayName='" + statusDisplayName + '\'' +
                ", totalAmount=" + totalAmount +
                ", itemCount=" + itemCount +
                ", recipientName='" + recipientName + '\'' +
                ", voucherCode='" + voucherCode + '\'' +
                ", discountAmount=" + discountAmount +
                '}';
    }
}
