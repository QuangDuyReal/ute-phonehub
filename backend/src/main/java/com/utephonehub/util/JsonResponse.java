package com.utephonehub.util;

// Lớp này dùng để tạo cấu trúc response chuẩn
public class JsonResponse {
    private String status;
    private Object data;
    private String message;

    private JsonResponse(String status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static JsonResponse success(Object data) {
        return new JsonResponse("success", data, null);
    }

    public static JsonResponse error(String message) {
        return new JsonResponse("error", null, message);
    }

    // Getters để Jackson có thể serialize
    public String getStatus() { return status; }
    public Object getData() { return data; }
    public String getMessage() { return message; }
}