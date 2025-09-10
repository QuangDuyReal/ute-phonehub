package com.utephonedhub.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utephonedhub.cart.dto.*;
import com.utephonedhub.cart.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/api/v1/cart/*")
public class CartController extends HttpServlet {
    private final CartService cartService = new CartService();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userIdStr = req.getParameter("userId");
            if (userIdStr == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu userId");
                return;
            }

            int userId = Integer.parseInt(userIdStr);
            com.utephonedhub.cart.dto.CartDTO cart = cartService.getCart(userId);

            // Trả về JSON nếu gọi API REST
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            mapper.writeValue(resp.getWriter(), cart);


            // Nếu muốn trả về JSP
            req.setAttribute("cart", cart);
            req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);


        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "userId không hợp lệ");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi server");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String userIdStr = req.getParameter("userId");
            if (userIdStr == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu userId");
                return;
            }
            int userId = Integer.parseInt(userIdStr);

            com.utephonedhub.cart.dto.AddCartItemRequest request = mapper.readValue(req.getReader(), com.utephonedhub.cart.dto.AddCartItemRequest.class);
            cartService.addItem(userId, request);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("Thêm sản phẩm vào giỏ hàng thành công");

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "userId không hợp lệ");
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi server");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String userIdStr = req.getParameter("userId");
            if (userIdStr == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu userId");
                return;
            }
            int userId = Integer.parseInt(userIdStr);

            com.utephonedhub.cart.dto.UpdateCartItemRequest request = mapper.readValue(req.getReader(), com.utephonedhub.cart.dto.UpdateCartItemRequest.class);
            cartService.updateItem(userId, request);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Cập nhật giỏ hàng thành công");

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "userId không hợp lệ");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi server");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String userIdStr = req.getParameter("userId");
            String productIdStr = req.getParameter("productId");

            if (userIdStr == null || productIdStr == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu userId hoặc productId");
                return;
            }

            int userId = Integer.parseInt(userIdStr);
            int productId = Integer.parseInt(productIdStr);

            cartService.deleteItem(userId, productId);

            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "userId hoặc productId không hợp lệ");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi server");
        }
    }
}
