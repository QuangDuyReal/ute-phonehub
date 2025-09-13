package com.utephonehub.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Auth Controller - Xử lý các API liên quan đến xác thực
 */
@Tag(name = "Authentication", description = "APIs for user authentication and account management")
public class AuthController extends HttpServlet {
    
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Operation(summary = "Get Auth Module Info", description = "Returns information about the authentication module")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Map<String, Object> moduleInfo = new HashMap<>();
        moduleInfo.put("module", "auth");
        moduleInfo.put("description", "Authentication and User Management Module");
        moduleInfo.put("version", "1.0.0");
        
        Map<String, String> availableEndpoints = new HashMap<>();
        availableEndpoints.put("login", "POST /api/auth/login");
        availableEndpoints.put("register", "POST /api/auth/register");
        availableEndpoints.put("logout", "POST /api/auth/logout");
        availableEndpoints.put("profile", "GET /api/auth/profile");
        availableEndpoints.put("update-profile", "PUT /api/auth/profile");
        availableEndpoints.put("change-password", "PUT /api/auth/change-password");
        // Address Management (FR-USER-04)
        availableEndpoints.put("addresses", "GET /api/auth/addresses");
        availableEndpoints.put("add-address", "POST /api/auth/addresses");
        availableEndpoints.put("update-address", "PUT /api/auth/addresses/{id}");
        availableEndpoints.put("delete-address", "DELETE /api/auth/addresses/{id}");
        
        moduleInfo.put("endpoints", availableEndpoints);
        
        String jsonResponse = objectMapper.writeValueAsString(moduleInfo);
        response.getWriter().write(jsonResponse);
    }
}
