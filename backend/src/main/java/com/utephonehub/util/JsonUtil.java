package com.utephonehub.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Lớp này giúp gửi response JSON đi một cách dễ dàng
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void sendJsonResponse(HttpServletResponse response, int statusCode, Object object) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(statusCode);
        response.getWriter().write(mapper.writeValueAsString(object));
    }
}