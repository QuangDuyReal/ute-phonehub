package com.utephonedhub.cart.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id; // Mã định danh riêng của giỏ hàng
    private int userId; // Mã định danh của người dùng sử dụng giỏ hàng
    private List<com.utephonedhub.cart.model.CartItem> items = new ArrayList<>(); // Danh sách các sản phẩm (CartItem) trong giỏ hàng
    public Cart() {}
    public Cart(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }
    public int getId() { return id; } // Chỉ truy cập được trong Cart
    public void setId(int id) { this.id = id; } // Semilar

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public List<com.utephonedhub.cart.model.CartItem> getItems() { return items; }
    public void setItems(List<com.utephonedhub.cart.model.CartItem> items) { this.items = items; }
}
