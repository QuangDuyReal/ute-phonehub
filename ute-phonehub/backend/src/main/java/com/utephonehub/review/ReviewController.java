package com.utephonehub.review;

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
 * Review Controller - Xử lý các API liên quan đến đánh giá sản phẩm
 */
@Tag(name = "Reviews", description = "APIs for product reviews and ratings")
public class ReviewController extends HttpServlet {
    
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Operation(summary = "Get Review Module Info", description = "Returns information about the review module")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Map<String, Object> moduleInfo = new HashMap<>();
        moduleInfo.put("module", "review");
        moduleInfo.put("description", "Product Review and Rating Module");
        moduleInfo.put("version", "1.0.0");
        
        Map<String, String> availableEndpoints = new HashMap<>();
        availableEndpoints.put("create", "POST /api/reviews");
        availableEndpoints.put("list", "GET /api/reviews/product/{productId}");
        availableEndpoints.put("update", "PUT /api/reviews/{id}");
        availableEndpoints.put("delete", "DELETE /api/reviews/{id}");
        availableEndpoints.put("user-reviews", "GET /api/reviews/user");
        availableEndpoints.put("statistics", "GET /api/reviews/product/{productId}/stats");
        
        moduleInfo.put("endpoints", availableEndpoints);
        
        String jsonResponse = objectMapper.writeValueAsString(moduleInfo);
        response.getWriter().write(jsonResponse);
    }
}
