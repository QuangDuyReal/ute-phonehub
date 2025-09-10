package com.utephonehub.model;

/**
 * User role enum consistent with `users.role` column.
 */
public enum UserRole {
    customer,
    admin;

    public static UserRole fromDatabase(String value) {
        if (value == null) return customer;
        try {
            return UserRole.valueOf(value);
        } catch (IllegalArgumentException ex) {
            return customer;
        }
    }

    public String toDatabase() {
        return name();
    }
}


