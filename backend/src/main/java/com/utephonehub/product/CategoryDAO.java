package com.utephonehub.product;

import com.utephonehub.model.Category;
import com.utephonehub.util.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    // Lấy tất cả categories
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories ORDER BY sort_order ASC, id ASC";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setSlug(rs.getString("slug"));
                c.setDescription(rs.getString("description"));
                c.setImageUrl(rs.getString("image_url"));
                c.setParentId((Integer) rs.getObject("parent_id"));
                c.setSortOrder(rs.getInt("sort_order"));
                c.setStatus(rs.getBoolean("status"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setUpdatedAt(rs.getTimestamp("updated_at"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Tìm category theo id
    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Category c = new Category();
                    c.setId(rs.getInt("id"));
                    c.setName(rs.getString("name"));
                    c.setSlug(rs.getString("slug"));
                    c.setDescription(rs.getString("description"));
                    c.setImageUrl(rs.getString("image_url"));
                    c.setParentId((Integer) rs.getObject("parent_id"));
                    c.setSortOrder(rs.getInt("sort_order"));
                    c.setStatus(rs.getBoolean("status"));
                    c.setCreatedAt(rs.getTimestamp("created_at"));
                    c.setUpdatedAt(rs.getTimestamp("updated_at"));
                    return c;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm category
    public boolean insertCategory(Category c) {
        String sql = "INSERT INTO categories (name, slug, description, image_url, parent_id, sort_order, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getSlug());
            ps.setString(3, c.getDescription());
            ps.setString(4, c.getImageUrl());
            if (c.getParentId() != null) {
                ps.setInt(5, c.getParentId());
            } else {
                ps.setNull(5, Types.INTEGER);
            }
            ps.setInt(6, c.getSortOrder());
            ps.setBoolean(7, c.isStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật category
    public boolean updateCategory(Category c) {
        String sql = "UPDATE categories SET name=?, slug=?, description=?, image_url=?, parent_id=?, " +
                "sort_order=?, status=?, updated_at=CURRENT_TIMESTAMP WHERE id=?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getSlug());
            ps.setString(3, c.getDescription());
            ps.setString(4, c.getImageUrl());
            if (c.getParentId() != null) {
                ps.setInt(5, c.getParentId());
            } else {
                ps.setNull(5, Types.INTEGER);
            }
            ps.setInt(6, c.getSortOrder());
            ps.setBoolean(7, c.isStatus());
            ps.setInt(8, c.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa category
    public boolean deleteCategory(int id) {
        String sql = "DELETE FROM categories WHERE id=?";
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
        CategoryDAO dao = new CategoryDAO();

        System.out.println("=== Tất cả Categories ===");
        for (Category c : dao.getAllCategories()) {
            System.out.println(c.getId() + " - " + c.getName() + " - " + c.getSlug()
                    + " - Parent: " + c.getParentId()
                    + " - Status: " + c.isStatus());
        }

        System.out.println("\n=== Lấy Category theo ID = 1 ===");
        Category cate = dao.getCategoryById(1);
        if (cate != null) {
            System.out.println(cate.getId() + " - " + cate.getName() + " - " + cate.getSlug());
        }

        System.out.println("\n=== Thêm Category mới ===");
        Category newCate = new Category();
        newCate.setName("TestCategory");
        newCate.setSlug("test-category");
        newCate.setDescription("Danh mục test");
        newCate.setImageUrl("/images/test.png");
        newCate.setParentId(null);
        newCate.setSortOrder(99);
        newCate.setStatus(true);
        dao.insertCategory(newCate);

        System.out.println("\n=== Sau khi thêm ===");
        for (Category c : dao.getAllCategories()) {
            System.out.println(c.getId() + " - " + c.getName());
        }

        System.out.println("\n=== Update Category ID = 1 ===");
        Category updateCate = dao.getCategoryById(1);
        if (updateCate != null) {
            updateCate.setDescription("Mô tả đã update!");
            dao.updateCategory(updateCate);
        }

        System.out.println("\n=== Delete Category ID = 2 ===");
        dao.deleteCategory(2);

        System.out.println("\n=== Danh sách Category cuối cùng ===");
        for (Category c : dao.getAllCategories()) {
            System.out.println(c.getId() + " - " + c.getName());
        }
    }

}

