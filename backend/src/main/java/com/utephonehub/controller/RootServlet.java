package com.utephonehub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Root Servlet - Hiển thị thông tin cơ bản về project
 */
@OpenAPIDefinition(
    info = @Info(
        title = "UTE PhoneHub API",
        version = "1.0.0",
        description = "API Documentation for UTE PhoneHub E-commerce Platform"
    )
)
public class RootServlet extends HttpServlet {
    
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Map<String, Object> projectInfo = new HashMap<>();
        projectInfo.put("project", "ute-phonehub");
        projectInfo.put("version", "1.0.0");
        projectInfo.put("description", "UTE PhoneHub E-commerce Backend API");
        projectInfo.put("status", "running");
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("auth", "/api/auth");
        endpoints.put("products", "/api/products");
        endpoints.put("cart", "/api/cart");
        endpoints.put("orders", "/api/orders");
        endpoints.put("reviews", "/api/reviews");
        endpoints.put("admin", "/api/admin");
        endpoints.put("vouchers", "/api/vouchers");
        endpoints.put("api-docs", "/api-docs");
        
        projectInfo.put("endpoints", endpoints);
        
        String jsonResponse = objectMapper.writeValueAsString(projectInfo);
        response.getWriter().write(jsonResponse);
    }
}
