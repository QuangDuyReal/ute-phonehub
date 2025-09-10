package com.utephonedhub.cart.service;

import com.utephonedhub.cart.dao.CartDAO;
import com.utephonedhub.cart.dto.*;
import com.utephonedhub.cart.model.Cart;
import com.utephonedhub.cart.model.CartItem;

import java.util.List;
import java.util.stream.Collectors;

public class CartService {
    private final CartDAO cartDAO = new CartDAO();

    public CartDTO getCart(int userId) {
        Cart cart = cartDAO.getCartByUserId(userId);

        List<CartItemDTO> itemDTOs = cart.getItems().stream()
                .map(item -> {
                    int productId = item.getProductId();
                    String productName = item.getProductName();
                    double price = item.getPrice();
                    int quantity = item.getQuantity();
                    double totalPrice = price * quantity;
                    String thumbnailUrl = item.getThumbnailUrl();

                    return new CartItemDTO(productId, productName, price, quantity, thumbnailUrl, totalPrice);
                })
                .collect(Collectors.toList());

        double total = itemDTOs.stream()
                .mapToDouble(CartItemDTO::getTotalPrice)
                .sum();

        return new CartDTO(userId, itemDTOs, total);
    }

    public void addItem(int userId, AddCartItemRequest request) {
        boolean success = cartDAO.addCartItem(userId, request.getProductId(), request.getQuantity());
        if (!success) {
            throw new IllegalArgumentException("Sản phẩm không tồn tại hoặc số lượng vượt quá tồn kho");
        }
    }

    public void updateItem(int userId, UpdateCartItemRequest request) {
        boolean success = cartDAO.updateCartItem(userId, request.getProductId(), request.getQuantity());
        if (!success) {
            throw new IllegalArgumentException("Cập nhật thất bại: sản phẩm không tồn tại hoặc số lượng vượt quá tồn kho");
        }
    }

    public void deleteItem(int userId, int productId) {
        cartDAO.deleteCartItem(userId, productId);
    }
}
