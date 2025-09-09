package com.utephonehub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class ApplyVoucherResponseDTO {

    private boolean valid;
    private String message;
    private String discountType;
    private BigDecimal discountAmount;

    // Constructors
    public ApplyVoucherResponseDTO() {
    }

    public ApplyVoucherResponseDTO(boolean valid, String message, String discountType, BigDecimal discountAmount) {
        this.valid = valid;
        this.message = message;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
    }

    // Getters and Setters - Bắt buộc phải có để Jackson có thể chuyển thành JSON
    
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    // Dùng @JsonProperty để đảm bảo tên thuộc tính trong JSON là camelCase
    // Mặc dù getter là getDiscountType nhưng trong JSON sẽ là "discountType"
    @JsonProperty("discountType")
    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    @JsonProperty("discountAmount")
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
}