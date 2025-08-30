package com.utephonehub.filter;

import jakarta.servlet.*;
import java.io.IOException;

/**
 * Auth Filter - Kiểm tra JWT token (sẽ được implement sau)
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // TODO: Implement JWT token validation
        // Hiện tại chỉ cho phép tất cả request đi qua
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup filter
    }
}
