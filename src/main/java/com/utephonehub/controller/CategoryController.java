package com.utephonehub.controller;

import com.utephonehub.dto.response.CategoryResponse;
import com.utephonehub.service.CategoryService;
import com.utephonehub.util.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for Category operations
 * Handles public endpoints for categories
 */
public class CategoryController extends HttpServlet {
    
    private static final Logger logger = LogManager.getLogger(CategoryController.class);
    private final CategoryService categoryService;
    private final JsonUtil jsonUtil;
    
    public CategoryController() {
        this.categoryService = new CategoryService();
        this.jsonUtil = new JsonUtil();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        logger.info("CategoryController GET request");
        
        try {
            // GET /api/v1/categories - Get all categories
            List<CategoryResponse> categories = categoryService.getAllCategories();
            
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("success", true);
            responseData.put("message", "Lấy danh sách danh mục thành công.");
            responseData.put("data", categories);
            
            sendJsonResponse(response, HttpServletResponse.SC_OK, responseData);
            
        } catch (Exception e) {
            logger.error("Error in CategoryController", e);
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                "Lỗi khi lấy danh sách danh mục");
        }
    }
    
    /**
     * Send JSON response
     */
    private void sendJsonResponse(HttpServletResponse response, int statusCode, Map<String, Object> data) 
            throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonUtil.toJson(data));
    }
    
    /**
     * Send error response
     */
    private void sendErrorResponse(HttpServletResponse response, int statusCode, String message) 
            throws IOException {
        Map<String, Object> errorData = new HashMap<>();
        errorData.put("success", false);
        errorData.put("message", message);
        sendJsonResponse(response, statusCode, errorData);
    }
}
