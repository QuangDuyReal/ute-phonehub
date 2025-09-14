package com.utephonehub.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Voucher {

    private int id;
    private String code;
    private String discountType; // 'percentage' hoặc 'fixed_amount'
    private BigDecimal discountValue;
    private int maxUsage;
    private int currentUsage;
    private Timestamp expiryDate;
    private Timestamp createdAt;
    private BigDecimal minOrderValue;
    private boolean isActive;

    // Constructors
    public Voucher() {
    }

    // Getters and Setters
    // Bạn có thể dùng IDE để tự động sinh ra các hàm này
    // (Trong IntelliJ: Alt + Insert -> Getter and Setter -> Chọn tất cả)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public int getMaxUsage() {
        return maxUsage;
    }

    public void setMaxUsage(int maxUsage) {
        this.maxUsage = maxUsage;
    }

    public int getCurrentUsage() {
        return currentUsage;
    }

    public void setCurrentUsage(int currentUsage) {
        this.currentUsage = currentUsage;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public BigDecimal getMinOrderValue() {
        return minOrderValue;
    }

    public void setMinOrderValue(BigDecimal minOrderValue) {
        this.minOrderValue = minOrderValue;
    }
    
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}