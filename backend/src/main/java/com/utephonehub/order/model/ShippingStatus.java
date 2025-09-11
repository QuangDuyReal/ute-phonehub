// package com.utephonehub.order.model;

// import java.time.LocalDateTime;

// public class ShippingStatus {
//     private Long id;
//     private Long orderId;
//     private String status; // PROCESSING, SHIPPED, DELIVERED
//     private LocalDateTime updatedAt;

//     // Constructor
//     public ShippingStatus() {
//     }

//     public ShippingStatus(Long id, Long orderId, String status, LocalDateTime updatedAt) {
//         this.id = id;
//         this.orderId = orderId;
//         this.status = status;
//         this.updatedAt = updatedAt;
//     }

//     // Getters and Setters
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public Long getOrderId() {
//         return orderId;
//     }

//     public void setOrderId(Long orderId) {
//         this.orderId = orderId;
//     }

//     public String getStatus() {
//         return status;
//     }

//     public void setStatus(String status) {
//         this.status = status;
//     }

//     public LocalDateTime getUpdatedAt() {
//         return updatedAt;
//     }

//     public void setUpdatedAt(LocalDateTime updatedAt) {
//         this.updatedAt = updatedAt;
//     }
// }