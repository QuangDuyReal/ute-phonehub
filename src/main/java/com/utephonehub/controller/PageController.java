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
@WebServlet(urlPatterns = {"/login", "/profile", "/cart", "/checkout", "/orders", "/order-lookup", "/vouchers", "/admin/dashboard", "/admin/products", "/admin/orders", "/admin/users", "/admin/categories", "/admin/brands", "/admin/vouchers"})
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
                
            case "/order-lookup":
                request.getRequestDispatcher("/WEB-INF/views/order/lookup.jsp").forward(request, response);
                break;
                
            case "/vouchers":
                request.getRequestDispatcher("/WEB-INF/views/voucher/list.jsp").forward(request, response);
                break;
                
            case "/admin/dashboard":
                request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
                break;
                
            case "/admin/products":
                request.getRequestDispatcher("/WEB-INF/views/admin/products.jsp").forward(request, response);
                break;
                
            case "/admin/orders":
                request.getRequestDispatcher("/WEB-INF/views/admin/orders.jsp").forward(request, response);
                break;
                
            case "/admin/users":
                request.getRequestDispatcher("/WEB-INF/views/admin/users.jsp").forward(request, response);
                break;
                
            case "/admin/categories":
                request.getRequestDispatcher("/WEB-INF/views/admin/categories.jsp").forward(request, response);
                break;
                
            case "/admin/brands":
                request.getRequestDispatcher("/WEB-INF/views/admin/brands.jsp").forward(request, response);
                break;
                
            case "/admin/vouchers":
                request.getRequestDispatcher("/WEB-INF/views/admin/vouchers.jsp").forward(request, response);
                break;
                
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
