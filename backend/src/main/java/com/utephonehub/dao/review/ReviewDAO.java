package com.utephonehub.dao.review;
import com.utephonehub.util.DBUtil;
import com.utephonehub.dto.review.ReviewDTO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    private Connection conn;

    public ReviewDAO() {
        try {
            this.conn = DBUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Thêm review mới
    public boolean addReview(int productId, int userId, int rating, String comment) {
        String sql = "INSERT INTO reviews(product_id, user_id, rating, comment) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.setInt(2, userId);
            ps.setInt(3, rating);
            ps.setString(4, comment);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách ReviewDTO theo product_id
    public List<ReviewDTO> getReviewsByProduct(int productId) {
        List<ReviewDTO> list = new ArrayList<>();
        String sql = """
            SELECT r.rating, r.comment, r.created_at, u.full_name
            FROM reviews r
            JOIN users u ON r.user_id = u.id
            WHERE r.product_id = ?
            ORDER BY r.created_at DESC
        """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReviewDTO dto = new ReviewDTO(
                        rs.getInt("rating"),
                        rs.getString("comment"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getString("full_name")
                );
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Xoá review (dành cho admin)
    public boolean deleteReview(int reviewId) {
        String sql = "DELETE FROM reviews WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reviewId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
