package com.utephonehub.dto.auth;

/**
 * DTOs for authentication flows.
 */
public class AuthDTOs {

    public static class RegisterRequest {
        public String fullName;
        public String email;
        public String password;
        public String phoneNumber;
    }

    public static class LoginRequest {
        public String email;
        public String password;
    }

    public static class UserPublic {
        public int id;
        public String fullName;
        public String email;
        public String phoneNumber;
        public String role;
        public String createdAt;
    }

    public static class LoginResponse {
        public String token;
        public UserPublic user;
    }
}


