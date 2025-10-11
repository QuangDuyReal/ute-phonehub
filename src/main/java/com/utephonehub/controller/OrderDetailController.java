package com.utephonehub.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Order Detail Page Controller
 * Handle routing cho order detail page
 * Path: /orders/{id}
 */
@WebServlet("/orders/*")
public class OrderDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        // If no path info, redirect to orders list
        if (pathInfo == null || pathInfo.equals("/")) {
            request.getRequestDispatcher("/WEB-INF/views/user/orders.jsp").forward(request, response);
            return;
        }
        
        // Extract order ID from path
        String[] pathParts = pathInfo.split("/");
        if (pathParts.length >= 2) {
            String orderId = pathParts[1];
            
            try {
                // Validate it's a number
                Long.parseLong(orderId);
                
                // Forward to order detail page
                request.getRequestDispatcher("/WEB-INF/views/order/detail.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Invalid ID format
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

