package com.utephonehub.dto.checkout;

/**
 * DTO for checkout request from frontend
 * Used in Module 6: Checkout process
 */
public class CheckoutRequestDTO {
    private String shippingAddress;
    private String recipientName;
    private String recipientPhone;
    private String voucherCode;
    private String paymentMethod;
    private String notes;

    // Default constructor
    public CheckoutRequestDTO() {}

    // Constructor
    public CheckoutRequestDTO(String shippingAddress, String recipientName, String recipientPhone) {
        this.shippingAddress = shippingAddress;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
    }

    // Getters and Setters
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

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Validate required fields for checkout
     */
    public boolean isValid() {
        return shippingAddress != null && !shippingAddress.trim().isEmpty() &&
               recipientName != null && !recipientName.trim().isEmpty() &&
               recipientPhone != null && !recipientPhone.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "CheckoutRequestDTO{" +
                "shippingAddress='" + shippingAddress + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", recipientPhone='" + recipientPhone + '\'' +
                ", voucherCode='" + voucherCode + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
