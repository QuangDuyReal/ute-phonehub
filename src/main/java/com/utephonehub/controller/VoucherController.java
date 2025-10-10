package com.utephonehub.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utephonehub.dto.request.ValidateVoucherRequest;
import com.utephonehub.dto.response.ValidateVoucherResponse;
import com.utephonehub.dto.response.VoucherValidationResult;
import com.utephonehub.entity.Voucher;
import com.utephonehub.service.VoucherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for public voucher operations
 * Handles voucher validation before checkout
 */
@WebServlet("/api/v1/vouchers/*")
public class VoucherController extends HttpServlet {
    
    private static final Logger logger = LogManager.getLogger(VoucherController.class);
    private final Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        .create();
    
    private final VoucherService voucherService;
    
    public VoucherController() {
        this.voucherService = new VoucherService();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String pathInfo = request.getPathInfo();
        
        try {
            if ("/validate".equals(pathInfo)) {
                validateVoucher(request, response);
            } else {
                sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, 
                    "Endpoint không tồn tại");
            }
        } catch (Exception e) {
            logger.error("Error in VoucherController", e);
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                "Đã xảy ra lỗi: " + e.getMessage());
        }
    }
    
    /**
     * POST /api/v1/vouchers/validate
     * Validate voucher code and preview discount
     * 
     * Request body:
     * {
     *   "code": "SUMMER2024",
     *   "orderTotal": 1000000,
     *   "userId": 1  // optional
     * }
     * 
     * Response:
     * {
     *   "success": true,
     *   "message": "Mã giảm giá hợp lệ",
     *   "data": {
     *     "valid": true,
     *     "message": "Mã giảm giá hợp lệ",
     *     "voucher": {
     *       "code": "SUMMER2024",
     *       "discountType": "PERCENTAGE",
     *       "discountValue": 10.00,
     *       "minOrderValue": 500000.00
     *     },
     *     "discountAmount": 100000.00,
     *     "finalAmount": 900000.00
     *   }
     * }
     */
    private void validateVoucher(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        logger.info("POST /api/v1/vouchers/validate - Validating voucher");
        
        // Parse request body
        ValidateVoucherRequest voucherRequest = parseRequestBody(request, ValidateVoucherRequest.class);
        
        if (voucherRequest == null) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, 
                "Dữ liệu request không hợp lệ");
            return;
        }
        
        // Validate required fields
        if (voucherRequest.getCode() == null || voucherRequest.getCode().trim().isEmpty()) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, 
                "Mã voucher không được để trống");
            return;
        }
        
        if (voucherRequest.getOrderTotal() == null || 
            voucherRequest.getOrderTotal().compareTo(BigDecimal.ZERO) <= 0) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, 
                "Tổng giá trị đơn hàng phải lớn hơn 0");
            return;
        }
        
        // Validate voucher using VoucherService
        VoucherValidationResult validationResult = voucherService.validateVoucher(
            voucherRequest.getCode(),
            voucherRequest.getOrderTotal(),
            voucherRequest.getUserId()
        );
        
        if (!validationResult.isValid()) {
            // Voucher is invalid
            logger.warn("Voucher validation failed: {}", validationResult.getErrorMessage());
            
            ValidateVoucherResponse voucherResponse = ValidateVoucherResponse.error(
                validationResult.getErrorMessage()
            );
            
            sendSuccessResponse(response, voucherResponse);
            return;
        }
        
        // Voucher is valid, calculate discount
        Voucher voucher = validationResult.getVoucher();
        BigDecimal discountAmount = voucherService.calculateDiscount(voucher, voucherRequest.getOrderTotal());
        BigDecimal finalAmount = voucherRequest.getOrderTotal().subtract(discountAmount);
        
        // Build voucher info
        ValidateVoucherResponse.VoucherInfo voucherInfo = new ValidateVoucherResponse.VoucherInfo(
            voucher.getCode(),
            voucher.getDiscountType().toString(),
            voucher.getDiscountValue(),
            voucher.getMinOrderValue()
        );
        
        // Build success response
        ValidateVoucherResponse voucherResponse = ValidateVoucherResponse.success(
            voucherInfo,
            discountAmount,
            finalAmount
        );
        
        logger.info("Voucher validation successful: {} - Discount: {}", 
            voucher.getCode(), discountAmount);
        
        sendSuccessResponse(response, voucherResponse);
    }
    
    /**
     * Parse request body to object
     */
    private <T> T parseRequestBody(HttpServletRequest request, Class<T> clazz) {
        try {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
            
            String json = sb.toString();
            logger.debug("Request body: {}", json);
            
            return gson.fromJson(json, clazz);
            
        } catch (Exception e) {
            logger.error("Failed to parse request body", e);
            return null;
        }
    }
    
    /**
     * Send success response
     */
    private void sendSuccessResponse(HttpServletResponse response, Object data) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("success", true);
        responseBody.put("data", data);
        
        String json = gson.toJson(responseBody);
        response.getWriter().write(json);
        
        logger.debug("Response: {}", json);
    }
    
    /**
     * Send error response
     */
    private void sendErrorResponse(HttpServletResponse response, int status, String message) 
            throws IOException {
        response.setStatus(status);
        
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("success", false);
        responseBody.put("message", message);
        
        String json = gson.toJson(responseBody);
        response.getWriter().write(json);
        
        logger.debug("Error response: {}", json);
    }
}
