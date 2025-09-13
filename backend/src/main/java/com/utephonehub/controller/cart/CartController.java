package com.utephonehub.controller.cart;

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
 * Cart Controller - Xử lý các API liên quan đến giỏ hàng
 */
@Tag(name = "Shopping Cart", description = "APIs for shopping cart management")
public class CartController extends HttpServlet {
    
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Operation(summary = "Get Cart Module Info", description = "Returns information about the cart module")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Map<String, Object> moduleInfo = new HashMap<>();
        moduleInfo.put("module", "cart");
        moduleInfo.put("description", "Shopping Cart Management Module");
        moduleInfo.put("version", "1.0.0");
        
        Map<String, String> availableEndpoints = new HashMap<>();
        availableEndpoints.put("view", "GET /api/cart");
        availableEndpoints.put("add", "POST /api/cart/add");
        availableEndpoints.put("update", "PUT /api/cart/update");
        availableEndpoints.put("remove", "DELETE /api/cart/remove/{productId}");
        availableEndpoints.put("clear", "DELETE /api/cart/clear");
        availableEndpoints.put("count", "GET /api/cart/count");
        
        moduleInfo.put("endpoints", availableEndpoints);
        
        String jsonResponse = objectMapper.writeValueAsString(moduleInfo);
        response.getWriter().write(jsonResponse);
    }
}
