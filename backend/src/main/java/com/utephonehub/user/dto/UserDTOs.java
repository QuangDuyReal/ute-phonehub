package com.utephonehub.user.dto;

public class UserDTOs {

    public static class UpdateProfileRequest {
        public String fullName;
        public String phone;
    }

    public static class ChangePasswordRequest {
        public String oldPassword;
        public String newPassword;
    }
}


