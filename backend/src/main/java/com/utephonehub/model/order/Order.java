package com.utephonehub.model.order;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Entity class representing an Order in the database
 * Maps to the 'orders' table
 */
public class Order {
    private int id;
    private int userId;
    private Timestamp orderDate;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private String shippingAddress;
    private String recipientName;
    private String recipientPhone;
    private Integer voucherId;

    // Default constructor
    public Order() {}

    // Constructor with essential fields
    public Order(int userId, BigDecimal totalAmount, String shippingAddress, 
                 String recipientName, String recipientPhone) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.shippingAddress = shippingAddress;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.status = OrderStatus.PENDING;
        this.orderDate = new Timestamp(System.currentTimeMillis());
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", recipientPhone='" + recipientPhone + '\'' +
                ", voucherId=" + voucherId +
                '}';
    }
}
