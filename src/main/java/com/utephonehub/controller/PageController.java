package com.utephonehub.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Page Controller
 * Handle routing cho các trang view (login, profile, products, etc.)
 */
@WebServlet(urlPatterns = {"/login", "/profile", "/cart", "/checkout", "/orders"})
public class PageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String servletPath = request.getServletPath();
        
        switch (servletPath) {
            case "/login":
                request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                break;
                
            case "/profile":
                request.getRequestDispatcher("/WEB-INF/views/user/profile.jsp").forward(request, response);
                break;
                
            case "/cart":
                request.getRequestDispatcher("/WEB-INF/views/cart/index.jsp").forward(request, response);
                break;
                
            case "/checkout":
                request.getRequestDispatcher("/WEB-INF/views/cart/checkout.jsp").forward(request, response);
                break;
                
            case "/orders":
                request.getRequestDispatcher("/WEB-INF/views/user/orders.jsp").forward(request, response);
                break;
                
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
