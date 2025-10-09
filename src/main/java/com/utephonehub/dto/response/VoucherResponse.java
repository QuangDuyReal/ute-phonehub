package com.utephonehub.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Voucher Response DTO
 */
public class VoucherResponse {
    
    private Long id;
    private String code;
    private String discountType;
    private BigDecimal discountValue;
    private Integer maxUsage;
    private BigDecimal minOrderValue;
    private LocalDateTime expiryDate;
    private String status;
    private Integer usageCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public VoucherResponse() {
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
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
    
    public Integer getMaxUsage() {
        return maxUsage;
    }
    
    public void setMaxUsage(Integer maxUsage) {
        this.maxUsage = maxUsage;
    }
    
    public BigDecimal getMinOrderValue() {
        return minOrderValue;
    }
    
    public void setMinOrderValue(BigDecimal minOrderValue) {
        this.minOrderValue = minOrderValue;
    }
    
    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }
    
    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Integer getUsageCount() {
        return usageCount;
    }
    
    public void setUsageCount(Integer usageCount) {
        this.usageCount = usageCount;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
