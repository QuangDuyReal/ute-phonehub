package com.utephonehub.user.dao;

import com.utephonehub.user.model.User;
import com.utephonehub.util.DbUtil;

import java.sql.*;
import java.time.Instant;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @Override
    public Optional<User> findById(long id) throws SQLException {
        String sql = "SELECT id, email, password_hash, full_name, phone, status, created_at, updated_at FROM users WHERE id = ?";
        try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(mapRow(rs));
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        String sql = "SELECT id, email, password_hash, full_name, phone, status, created_at, updated_at FROM users WHERE email = ?";
        try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
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
        try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public User create(User user) throws SQLException {
        String sql = "INSERT INTO users(email, password_hash, full_name, phone, status) VALUES(?,?,?,?,?) RETURNING id, created_at, updated_at";
        try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPasswordHash());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPhone());
            ps.setShort(5, user.getStatus());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user.setId(rs.getLong("id"));
                    Timestamp cAt = rs.getTimestamp("created_at");
                    Timestamp uAt = rs.getTimestamp("updated_at");
                    user.setCreatedAt(cAt != null ? cAt.toInstant() : Instant.now());
                    user.setUpdatedAt(uAt != null ? uAt.toInstant() : user.getCreatedAt());
                }
            }
            return user;
        }
    }

    @Override
    public int updateProfile(long id, String fullName, String phone) throws SQLException {
        String sql = "UPDATE users SET full_name = ?, phone = ? WHERE id = ?";
        try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setString(2, phone);
            ps.setLong(3, id);
            return ps.executeUpdate();
        }
    }

    @Override
    public int updatePassword(long id, String passwordHash) throws SQLException {
        String sql = "UPDATE users SET password_hash = ? WHERE id = ?";
        try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, passwordHash);
            ps.setLong(2, id);
            return ps.executeUpdate();
        }
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getLong("id"));
        u.setEmail(rs.getString("email"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setFullName(rs.getString("full_name"));
        u.setPhone(rs.getString("phone"));
        u.setStatus(rs.getShort("status"));
        Timestamp cAt = rs.getTimestamp("created_at");
        Timestamp uAt = rs.getTimestamp("updated_at");
        u.setCreatedAt(cAt != null ? cAt.toInstant() : null);
        u.setUpdatedAt(uAt != null ? uAt.toInstant() : null);
        return u;
    }
}


