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
        String sql = "SELECT * FROM brands ORDER BY sort_order ASC, id ASC";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Brand b = new Brand();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setSlug(rs.getString("slug"));
                b.setDescription(rs.getString("description"));
                b.setLogoUrl(rs.getString("logo_url"));
                b.setCategoryId(rs.getInt("category_id"));
                b.setSortOrder(rs.getInt("sort_order"));
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
        String sql = "SELECT * FROM brands WHERE id=?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Brand b = new Brand();
                    b.setId(rs.getInt("id"));
                    b.setName(rs.getString("name"));
                    b.setSlug(rs.getString("slug"));
                    b.setDescription(rs.getString("description"));
                    b.setLogoUrl(rs.getString("logo_url"));
                    b.setCategoryId(rs.getInt("category_id"));
                    b.setSortOrder(rs.getInt("sort_order"));
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
        String sql = "INSERT INTO brands (name, slug, description, logo_url, category_id, sort_order, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getName());
            ps.setString(2, b.getSlug());
            ps.setString(3, b.getDescription());
            ps.setString(4, b.getLogoUrl());
            ps.setInt(5, b.getCategoryId());
            ps.setInt(6, b.getSortOrder());
            ps.setBoolean(7, b.isStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật brand
    public boolean updateBrand(Brand b) {
        String sql = "UPDATE brands SET name=?, slug=?, description=?, logo_url=?, category_id=?, " +
                "sort_order=?, status=?, updated_at=CURRENT_TIMESTAMP WHERE id=?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getName());
            ps.setString(2, b.getSlug());
            ps.setString(3, b.getDescription());
            ps.setString(4, b.getLogoUrl());
            ps.setInt(5, b.getCategoryId());
            ps.setInt(6, b.getSortOrder());
            ps.setBoolean(7, b.isStatus());
            ps.setInt(8, b.getId());
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

    public static void main(String[] args) {
        BrandDAO dao = new BrandDAO();

        System.out.println("=== Tất cả Brands ===");
        for (Brand b : dao.getAllBrands()) {
            System.out.println(b.getId() + " - " + b.getName() + " - " + b.getSlug()
                    + " - Category: " + b.getCategoryId()
                    + " - Status: " + b.isStatus());
        }

        System.out.println("\n=== Lấy Brand theo ID = 1 ===");
        Brand brand = dao.getBrandById(1);
        if (brand != null) {
            System.out.println(brand.getId() + " - " + brand.getName() + " - " + brand.getSlug());
        }

        System.out.println("\n=== Thêm Brand mới ===");
        Brand newBrand = new Brand();
        newBrand.setName("TestBrand");
        newBrand.setSlug("test-brand");
        newBrand.setDescription("Thương hiệu test");
        newBrand.setLogoUrl("/logos/test.png");
        newBrand.setCategoryId(7); // VD: gắn vào Hãng máy tính bảng
        newBrand.setSortOrder(99);
        newBrand.setStatus(true);
        dao.insertBrand(newBrand);

        System.out.println("\n=== Sau khi thêm ===");
        for (Brand b : dao.getAllBrands()) {
            System.out.println(b.getId() + " - " + b.getName());
        }

        System.out.println("\n=== Update Brand ID = 3 ===");
        Brand updateBrand = dao.getBrandById(3);
        if (updateBrand != null) {
            updateBrand.setDescription("Tôi đã update ID =3!");
            dao.updateBrand(updateBrand);
        }

        System.out.println("\n=== Delete Brand ID = 4 ===");
        dao.deleteBrand(4);

        System.out.println("\n=== Danh sách Brand cuối cùng ===");
        for (Brand b : dao.getAllBrands()) {
            System.out.println(b.getId() + " - " + b.getName());
        }
    }

}



