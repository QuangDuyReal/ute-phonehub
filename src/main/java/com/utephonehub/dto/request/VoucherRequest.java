package com.utephonehub.dto.request;

import com.utephonehub.entity.Voucher;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Voucher Request DTO
 * Dùng cho create và update voucher
 */
public class VoucherRequest {
    
    private String code;
    private Voucher.DiscountType discountType;
    private BigDecimal discountValue;
    private Integer maxUsage;
    private BigDecimal minOrderValue;
    private LocalDateTime expiryDate;
    private Voucher.VoucherStatus status;
    
    // Constructors
    public VoucherRequest() {
    }
    
    // Getters and Setters
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public Voucher.DiscountType getDiscountType() {
        return discountType;
    }
    
    public void setDiscountType(Voucher.DiscountType discountType) {
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
    
    public Voucher.VoucherStatus getStatus() {
        return status;
    }
    
    public void setStatus(Voucher.VoucherStatus status) {
        this.status = status;
    }
}
