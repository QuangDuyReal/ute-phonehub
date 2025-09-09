package com.utephonehub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utephonehub.service.IVoucherService;
import com.utephonehub.service.VoucherService;
import com.utephonehub.util.JsonResponse;
import com.utephonehub.util.JsonUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Collectors;

// Annotation @WebServlet để đăng ký Servlet với Tomcat
// Chúng ta sẽ bắt tất cả các URL có dạng /api/vouchers/*
@WebServlet("/api/vouchers/*")
public class VoucherController extends HttpServlet {

    private IVoucherService voucherService;
    
    // Sử dụng constructor để khởi tạo service
    public VoucherController() {
        this.voucherService = new VoucherService();
    }

    // doGet xử lý các request GET (vd: lấy danh sách voucher)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // API: GET /api/vouchers -> Lấy danh sách voucher cho Admin
        var vouchers = voucherService.getAllVouchers();
        JsonUtil.sendJsonResponse(resp, HttpServletResponse.SC_OK, JsonResponse.success(vouchers));
    }

    // doPost xử lý các request POST (vd: áp dụng voucher, tạo voucher)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo(); // Lấy phần sau của URL, vd: "/validate"

        if ("/validate".equals(pathInfo)) {
            handleValidateVoucher(req, resp);
        } else {
            // Mặc định là tạo voucher mới (chưa implement)
             JsonUtil.sendJsonResponse(resp, HttpServletResponse.SC_NOT_FOUND, JsonResponse.error("Endpoint not found."));
        }
    }

    // Phương thức riêng để xử lý logic validate voucher
    private void handleValidateVoucher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // Đọc JSON body từ request
            String jsonBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            
            // Dùng ObjectMapper để chuyển JSON thành một Map
            ObjectMapper mapper = new ObjectMapper();
            var body = mapper.readValue(jsonBody, java.util.Map.class);

            String code = (String) body.get("code");
            BigDecimal orderTotal = new BigDecimal(body.get("orderTotal").toString());

            if (code == null || code.isBlank()) {
                JsonUtil.sendJsonResponse(resp, HttpServletResponse.SC_BAD_REQUEST, JsonResponse.error("Voucher code is required."));
                return;
            }

            var result = voucherService.validateVoucher(code, orderTotal);

            if (result.isValid()) {
                JsonUtil.sendJsonResponse(resp, HttpServletResponse.SC_OK, JsonResponse.success(result));
            } else {
                // Trả về lỗi 400 (Bad Request) vì mã không hợp lệ
                JsonUtil.sendJsonResponse(resp, HttpServletResponse.SC_BAD_REQUEST, JsonResponse.error(result.getMessage()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJsonResponse(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, JsonResponse.error("An internal server error occurred."));
        }
    }
}