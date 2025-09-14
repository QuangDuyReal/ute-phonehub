package com.utephonehub.controller.product;

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
 * Product Controller - Xử lý các API liên quan đến sản phẩm
 */
@WebServlet(name = "ProductController", urlPatterns = {"/api/products/*"})
@Tag(name = "Products", description = "APIs for product management and display")
public class ProductController extends HttpServlet {
    
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Operation(summary = "Get Product Module Info", description = "Returns information about the product module")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Map<String, Object> moduleInfo = new HashMap<>();
        moduleInfo.put("module", "product");
        moduleInfo.put("description", "Product Management and Display Module");
        moduleInfo.put("version", "1.0.0");
        
        Map<String, String> availableEndpoints = new HashMap<>();
        availableEndpoints.put("list", "GET /api/products");
        availableEndpoints.put("detail", "GET /api/products/{id}");
        availableEndpoints.put("search", "GET /api/products/search");
        availableEndpoints.put("categories", "GET /api/products/categories");
        availableEndpoints.put("brands", "GET /api/products/brands");
        availableEndpoints.put("create", "POST /api/products (Admin)");
        availableEndpoints.put("update", "PUT /api/products/{id} (Admin)");
        availableEndpoints.put("delete", "DELETE /api/products/{id} (Admin)");
        
        moduleInfo.put("endpoints", availableEndpoints);
        
        String jsonResponse = objectMapper.writeValueAsString(moduleInfo);
        response.getWriter().write(jsonResponse);
    }
}
