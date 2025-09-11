package com.utephonedhub.cart.dto;

import java.util.List;

public class CartDTO {
    private int userId;
    private List<com.utephonedhub.cart.dto.CartItemDTO> items;
    private double total;

    public CartDTO(int userId, List<com.utephonedhub.cart.dto.CartItemDTO> items, double total) {
        this.userId = userId;
        this.items = items;
        this.total = total;
    }

    public int getUserId() {
        return userId;
    }

    public List<com.utephonedhub.cart.dto.CartItemDTO> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }
}
