package com.utephonehub.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Admin Controller - Xử lý các API liên quan đến quản trị
 */
@WebServlet(name = "AdminController", urlPatterns = {"/api/admin/*"})
@Tag(name = "Admin", description = "APIs for admin management and dashboard")
public class AdminController extends HttpServlet {
    
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Operation(summary = "Get Admin Module Info", description = "Returns information about the admin module")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Map<String, Object> moduleInfo = new HashMap<>();
        moduleInfo.put("module", "admin");
        moduleInfo.put("description", "Admin Management and Dashboard Module");
        moduleInfo.put("version", "1.0.0");
        
        Map<String, String> availableEndpoints = new HashMap<>();
        // Dashboard & Statistics (FR-ADMIN-01)
        availableEndpoints.put("dashboard", "GET /api/admin/dashboard");
        availableEndpoints.put("statistics", "GET /api/admin/statistics");
        // User Management (FR-ADMIN-05)
        availableEndpoints.put("users", "GET /api/admin/users");
        availableEndpoints.put("user-detail", "GET /api/admin/users/{id}");
        availableEndpoints.put("lock-user", "PUT /api/admin/users/{id}/lock");
        availableEndpoints.put("unlock-user", "PUT /api/admin/users/{id}/unlock");
        // Category Management (FR-ADMIN-03)
        availableEndpoints.put("categories", "GET /api/admin/categories");
        availableEndpoints.put("create-category", "POST /api/admin/categories");
        availableEndpoints.put("update-category", "PUT /api/admin/categories/{id}");
        availableEndpoints.put("delete-category", "DELETE /api/admin/categories/{id}");
        // Brand Management (FR-ADMIN-03)
        availableEndpoints.put("brands", "GET /api/admin/brands");
        availableEndpoints.put("create-brand", "POST /api/admin/brands");
        availableEndpoints.put("update-brand", "PUT /api/admin/brands/{id}");
        availableEndpoints.put("delete-brand", "DELETE /api/admin/brands/{id}");
        
        moduleInfo.put("endpoints", availableEndpoints);
        
        String jsonResponse = objectMapper.writeValueAsString(moduleInfo);
        response.getWriter().write(jsonResponse);
    }
}
