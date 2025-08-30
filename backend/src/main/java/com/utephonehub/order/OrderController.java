package com.utephonehub.order;

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
 * Order Controller - Xử lý các API liên quan đến đơn hàng
 */
@Tag(name = "Orders", description = "APIs for order management and checkout")
public class OrderController extends HttpServlet {
    
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Operation(summary = "Get Order Module Info", description = "Returns information about the order module")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Map<String, Object> moduleInfo = new HashMap<>();
        moduleInfo.put("module", "order");
        moduleInfo.put("description", "Order Management and Checkout Module");
        moduleInfo.put("version", "1.0.0");
        
        Map<String, String> availableEndpoints = new HashMap<>();
        // Checkout Process (FR-CHECKOUT-01 to 05)
        availableEndpoints.put("checkout-info", "POST /api/orders/checkout");
        availableEndpoints.put("apply-voucher", "POST /api/orders/apply-voucher");
        availableEndpoints.put("place-order", "POST /api/orders/place");
        // Order Management  
        availableEndpoints.put("create", "POST /api/orders");
        availableEndpoints.put("list", "GET /api/orders");
        availableEndpoints.put("detail", "GET /api/orders/{id}");
        availableEndpoints.put("update-status", "PUT /api/orders/{id}/status (Admin)");
        availableEndpoints.put("cancel", "PUT /api/orders/{id}/cancel");
        availableEndpoints.put("history", "GET /api/orders/history");
        
        moduleInfo.put("endpoints", availableEndpoints);
        
        String jsonResponse = objectMapper.writeValueAsString(moduleInfo);
        response.getWriter().write(jsonResponse);
    }
}
