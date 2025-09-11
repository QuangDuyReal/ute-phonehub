package com.utephonehub.product;

import com.utephonehub.model.Brand;
import com.utephonehub.util.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO {

    // Lấy tất cả brands
    public List<Brand> getAllBrands() {
        List<Brand> list = new ArrayList<>();
        String sql = "SELECT id, name, category_id, status, created_at, updated_at FROM brands ORDER BY id ASC";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Brand b = new Brand();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setCategoryId(rs.getInt("category_id"));
                b.setStatus(rs.getBoolean("status"));
                b.setCreatedAt(rs.getTimestamp("created_at"));
                b.setUpdatedAt(rs.getTimestamp("updated_at"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Tìm brand theo id
    public Brand getBrandById(int id) {
        String sql = "SELECT id, name, category_id, status, created_at, updated_at FROM brands WHERE id=?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Brand b = new Brand();
                    b.setId(rs.getInt("id"));
                    b.setName(rs.getString("name"));
                    b.setCategoryId(rs.getInt("category_id"));
                    b.setStatus(rs.getBoolean("status"));
                    b.setCreatedAt(rs.getTimestamp("created_at"));
                    b.setUpdatedAt(rs.getTimestamp("updated_at"));
                    return b;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm brand
    public boolean insertBrand(Brand b) {
        String sql = "INSERT INTO brands (name, category_id, status) VALUES (?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getName());
            ps.setInt(2, b.getCategoryId());
            ps.setBoolean(3, b.getStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật brand
    public boolean updateBrand(Brand b) {
        String sql = "UPDATE brands SET name=?, category_id=?, status=?, updated_at=CURRENT_TIMESTAMP WHERE id=?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getName());
            ps.setInt(2, b.getCategoryId());
            ps.setBoolean(3, b.getStatus());
            ps.setInt(4, b.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa brand
    public boolean deleteBrand(int id) {
        String sql = "DELETE FROM brands WHERE id=?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
