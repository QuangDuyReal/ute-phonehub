package com.utephonehub.controller.voucher;

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
 * Voucher Controller - Xử lý các API liên quan đến mã giảm giá
 */
@Tag(name = "Vouchers", description = "APIs for voucher and discount management")
public class VoucherController extends HttpServlet {
    
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Operation(summary = "Get Voucher Module Info", description = "Returns information about the voucher module")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Map<String, Object> moduleInfo = new HashMap<>();
        moduleInfo.put("module", "voucher");
        moduleInfo.put("description", "Voucher and Discount Management Module");
        moduleInfo.put("version", "1.0.0");
        
        Map<String, String> availableEndpoints = new HashMap<>();
        availableEndpoints.put("list", "GET /api/vouchers");
        availableEndpoints.put("validate", "POST /api/vouchers/validate");
        availableEndpoints.put("create", "POST /api/vouchers (Admin)");
        availableEndpoints.put("update", "PUT /api/vouchers/{id} (Admin)");
        availableEndpoints.put("delete", "DELETE /api/vouchers/{id} (Admin)");
        availableEndpoints.put("usage", "GET /api/vouchers/{id}/usage (Admin)");
        
        moduleInfo.put("endpoints", availableEndpoints);
        
        String jsonResponse = objectMapper.writeValueAsString(moduleInfo);
        response.getWriter().write(jsonResponse);
    }
}
