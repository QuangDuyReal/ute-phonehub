package com.utephonehub.model.voucher;

/**
 * Enum representing discount types for vouchers.
 * Mirrors the `discount_type` enum in PostgreSQL database.
 */
public enum DiscountType {
    percentage("percentage"),
    fixed_amount("fixed_amount");
    
    private final String dbValue;
    
    DiscountType(String dbValue) {
        this.dbValue = dbValue;
    }
    
    public String getDbValue() {
        return dbValue;
    }
    
    /**
     * Convert database string value to enum
     * @param value database string value
     * @return corresponding DiscountType enum, defaults to percentage if invalid
     */
    public static DiscountType fromDatabase(String value) {
        if (value == null) {
            return percentage; // default
        }
        
        for (DiscountType type : values()) {
            if (type.dbValue.equals(value)) {
                return type;
            }
        }
        return percentage; // default fallback
    }
    
    /**
     * Convert enum to database string value
     * @return database string representation
     */
    public String toDatabase() {
        return this.dbValue;
    }
    
    @Override
    public String toString() {
        return this.dbValue;
    }
}