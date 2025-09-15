package com.utephonehub.dao.voucher;

import com.utephonehub.model.voucher.Voucher;
import com.utephonehub.model.voucher.DiscountType;
import com.utephonehub.util.DBUtil; // Sử dụng DBContext của Hưng

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VoucherDAO {

    // Phương thức chuyển đổi từ ResultSet sang đối tượng Voucher (giữ nguyên)
    private Voucher mapResultSetToVoucher(ResultSet rs) throws SQLException {
        Voucher voucher = new Voucher();
        voucher.setId(rs.getInt("id"));
        voucher.setCode(rs.getString("code"));
        // Cần lấy giá trị của Enum từ DB dưới dạng String và convert sang DiscountType
        voucher.setDiscountType(DiscountType.fromDatabase(rs.getString("discount_type"))); 
        voucher.setDiscountValue(rs.getBigDecimal("discount_value"));
        voucher.setMaxUsage(rs.getInt("max_usage"));
        voucher.setCurrentUsage(rs.getInt("current_usage"));
        voucher.setExpiryDate(rs.getTimestamp("expiry_date"));
        voucher.setCreatedAt(rs.getTimestamp("created_at"));
        voucher.setMinOrderValue(rs.getBigDecimal("min_order_value"));
        voucher.setActive(rs.getBoolean("is_active"));
        return voucher;
    }

    /**
     * Tìm một voucher bằng mã (code) của nó.
     */
    public Optional<Voucher> findByCode(String code) {
        String sql = "SELECT * FROM vouchers WHERE code = ?";
        // Sử dụng getNewConnection() để đảm bảo an toàn luồng (thread-safety)
        // và try-with-resources để tự động đóng kết nối.
        try (Connection conn = DBUtil.getNewConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToVoucher(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Nên sử dụng một logger trong dự án thực tế
        }
        return Optional.empty();
    }

    /**
     * Lấy tất cả các voucher có trong CSDL.
     */
    public List<Voucher> findAll() {
        List<Voucher> vouchers = new ArrayList<>();
        String sql = "SELECT * FROM vouchers ORDER BY created_at DESC";
        try (Connection conn = DBUtil.getNewConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                vouchers.add(mapResultSetToVoucher(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vouchers;
    }
    
    // Các phương thức khác sẽ được thêm vào sau...
}