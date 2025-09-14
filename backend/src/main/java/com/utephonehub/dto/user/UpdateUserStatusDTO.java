package com.utephonehub.dto.user;

import com.utephonehub.model.user.UserStatus;

public class UpdateUserStatusDTO {
    private UserStatus status;

    // Getters and Setters
    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}