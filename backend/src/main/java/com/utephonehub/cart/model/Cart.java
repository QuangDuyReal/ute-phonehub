package com.utephonehub.cart.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id;
    private int userId;
    private List<com.utephonehub.cart.model.CartItem> items = new ArrayList<>();

    public Cart() {
    }

    public Cart(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<com.utephonehub.cart.model.CartItem> getItems() {
        return items;
    }

    public void setItems(List<com.utephonehub.cart.model.CartItem> items) {
        this.items = items;
    }
}
