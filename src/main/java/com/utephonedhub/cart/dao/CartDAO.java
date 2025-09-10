package com.utephonedhub.cart.dao;

import com.utephonedhub.cart.model.Cart;
import com.utephonedhub.cart.model.CartItem;
import com.utephonedhub.config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    public Cart getCartByUserId(int userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);

        List<CartItem> items = new ArrayList<>();
        String sql = "SELECT product_id, product_name, price, quantity, thumbnail_url FROM cart_items WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                // Lấy thumbnailUrl, nếu DB không có trường này bạn có thể đổi thành "" hoặc null
                String thumbnailUrl = "";
                try {
                    thumbnailUrl = rs.getString("thumbnail_url");
                    if (thumbnailUrl == null) thumbnailUrl = "";
                } catch (SQLException e) {
                    // Nếu DB không có cột này thì bỏ qua
                }

                CartItem item = new CartItem(productId, productName, price, quantity, thumbnailUrl);
                items.add(item);
            }

            cart.setItems(items);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Bạn có thể ném exception hoặc xử lý lỗi tùy theo ứng dụng
        }

        return cart;
    }

    // Thêm item vào giỏ hàng (nếu tồn tại thì tăng số lượng)
    // Trả về true nếu thành công, false nếu lỗi (vd: sản phẩm không tồn tại hoặc vượt quá tồn kho)
    public boolean addCartItem(int userId, int productId, int quantity) {
        String sql = "INSERT INTO cart_items(user_id, product_id, product_name, price, quantity, thumbnail_url) " +
                "VALUES (?, ?, (SELECT name FROM products WHERE id = ?), " +
                "(SELECT price FROM products WHERE id = ?), ?, " +
                "(SELECT thumbnail_url FROM products WHERE id = ?)) " +
                "ON CONFLICT (user_id, product_id) DO UPDATE SET quantity = cart_items.quantity + EXCLUDED.quantity " +
                "WHERE cart_items.quantity + EXCLUDED.quantity <= (SELECT stock FROM products WHERE id = ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Kiểm tra tồn kho trước khi thêm
            int stock = getProductStock(productId, conn);
            if (stock < quantity) {
                return false;
            }

            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.setInt(3, productId);
            stmt.setInt(4, productId);
            stmt.setInt(5, quantity);
            stmt.setInt(6, productId);
            stmt.setInt(7, productId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    // Trả về true nếu thành công, false nếu lỗi
    public boolean updateCartItem(int userId, int productId, int quantity) {
        String sql = "UPDATE cart_items SET quantity = ? WHERE user_id = ? AND product_id = ? " +
                "AND ? <= (SELECT stock FROM products WHERE id = ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Kiểm tra tồn kho trước khi cập nhật
            int stock = getProductStock(productId, conn);
            if (stock < quantity) {
                return false;
            }

            stmt.setInt(1, quantity);
            stmt.setInt(2, userId);
            stmt.setInt(3, productId);
            stmt.setInt(4, quantity);
            stmt.setInt(5, productId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void deleteCartItem(int userId, int productId) {
        String sql = "DELETE FROM cart_items WHERE user_id = ? AND product_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Helper lấy stock sản phẩm từ DB
    private int getProductStock(int productId, Connection conn) throws SQLException {
        String sql = "SELECT stock FROM products WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("stock");
            }
        }
        return 0; // không tìm thấy sản phẩm => stock 0
    }
}

