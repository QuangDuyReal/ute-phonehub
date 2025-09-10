package com.utephonehub.model;

/**
 * User status enum consistent with `users.status` column.
 */
public enum UserStatus {
    active,
    disabled;

    public static UserStatus fromDatabase(String value) {
        if (value == null) return active;
        try {
            return UserStatus.valueOf(value);
        } catch (IllegalArgumentException ex) {
            return active;
        }
    }

    public String toDatabase() {
        return name();
    }
}


