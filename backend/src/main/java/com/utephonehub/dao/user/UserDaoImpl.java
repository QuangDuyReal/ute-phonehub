package com.utephonehub.dao.user;

import com.utephonehub.model.user.User;
import com.utephonehub.model.user.UserRole;
import com.utephonehub.model.user.UserStatus;
import com.utephonehub.util.DBUtil;

import java.sql.*;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @Override
    public Optional<User> findById(int id) throws SQLException {
        String sql = "SELECT id, full_name, email, password_hash, phone_number, role, status, created_at, updated_at FROM users WHERE id = ?";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(mapRow(rs));
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        String sql = "SELECT id, full_name, email, password_hash, phone_number, role, status, created_at, updated_at FROM users WHERE email = ?";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(mapRow(rs));
            }
        }
    }

    @Override
    public boolean existsEmail(String email) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE email = ?";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public User create(User user) throws SQLException {
        String sql = "INSERT INTO users(full_name, email, password_hash, phone_number, role, status) VALUES(?,?,?,?,?,?) RETURNING id, created_at, updated_at";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            ps.setString(4, user.getPhoneNumber());
            ps.setString(5, user.getRole() != null ? user.getRole().toDatabase() : UserRole.customer.toDatabase());
            ps.setString(6, user.getStatus() != null ? user.getStatus().toDatabase() : UserStatus.active.toDatabase());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user.setId(rs.getInt("id"));
                    Timestamp cAt = rs.getTimestamp("created_at");
                    Timestamp uAt = rs.getTimestamp("updated_at");
                    user.setCreatedAt(cAt != null ? cAt : new Timestamp(System.currentTimeMillis()));
                    user.setUpdatedAt(uAt != null ? uAt : user.getCreatedAt());
                }
            }
            return user;
        }
    }

    @Override
    public int updateProfile(int id, String fullName, String phoneNumber) throws SQLException {
        String sql = "UPDATE users SET full_name = ?, phone_number = ? WHERE id = ?";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setString(2, phoneNumber);
            ps.setInt(3, id);
            return ps.executeUpdate();
        }
    }

    @Override
    public int updatePassword(int id, String passwordHash) throws SQLException {
        String sql = "UPDATE users SET password_hash = ? WHERE id = ?";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, passwordHash);
            ps.setInt(2, id);
            return ps.executeUpdate();
        }
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setFullName(rs.getString("full_name"));
        u.setEmail(rs.getString("email"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setPhoneNumber(rs.getString("phone_number"));
        u.setRole(UserRole.fromDatabase(rs.getString("role")));
        u.setStatus(UserStatus.fromDatabase(rs.getString("status")));
        Timestamp cAt = rs.getTimestamp("created_at");
        Timestamp uAt = rs.getTimestamp("updated_at");
        u.setCreatedAt(cAt != null ? cAt : null);
        u.setUpdatedAt(uAt != null ? uAt : null);
        return u;
    }
}


