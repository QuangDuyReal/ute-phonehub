package com.utephonehub.user.dto;

/**
 * DTOs for authentication flows.
 */
public class AuthDTOs {

    public static class RegisterRequest {
        public String email;
        public String password;
        public String fullName;
        public String phone;
    }

    public static class LoginRequest {
        public String email;
        public String password;
    }

    public static class UserPublic {
        public long id;
        public String email;
        public String fullName;
        public String phone;
        public String createdAt;
    }

    public static class LoginResponse {
        public String token;
        public UserPublic user;
    }
}


