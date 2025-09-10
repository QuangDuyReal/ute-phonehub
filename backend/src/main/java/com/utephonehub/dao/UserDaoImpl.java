package com.utephonehub.dao;

import com.utephonehub.model.User;
import com.utephonehub.model.UserRole;
import com.utephonehub.model.UserStatus;
import com.utephonehub.util.DbUtil;

import java.sql.*;
import java.time.Instant;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @Override
    public Optional<User> findById(long id) throws SQLException {
        String sql = "SELECT id, full_name, email, password_hash, phone_number, role, status, created_at, updated_at FROM users WHERE id = ?";
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
        String sql = "SELECT id, full_name, email, password_hash, phone_number, role, status, created_at, updated_at FROM users WHERE email = ?";
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
        String sql = "INSERT INTO users(full_name, email, password_hash, phone_number, role, status) VALUES(?,?,?,?,?,?) RETURNING id, created_at, updated_at";
        try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            ps.setString(4, user.getPhoneNumber());
            ps.setString(5, user.getRole() != null ? user.getRole().toDatabase() : UserRole.customer.toDatabase());
            ps.setString(6, user.getStatus() != null ? user.getStatus().toDatabase() : UserStatus.active.toDatabase());
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
    public int updateProfile(long id, String fullName, String phoneNumber) throws SQLException {
        String sql = "UPDATE users SET full_name = ?, phone_number = ? WHERE id = ?";
        try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setString(2, phoneNumber);
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
        u.setFullName(rs.getString("full_name"));
        u.setEmail(rs.getString("email"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setPhoneNumber(rs.getString("phone_number"));
        u.setRole(UserRole.fromDatabase(rs.getString("role")));
        u.setStatus(UserStatus.fromDatabase(rs.getString("status")));
        Timestamp cAt = rs.getTimestamp("created_at");
        Timestamp uAt = rs.getTimestamp("updated_at");
        u.setCreatedAt(cAt != null ? cAt.toInstant() : null);
        u.setUpdatedAt(uAt != null ? uAt.toInstant() : null);
        return u;
    }
}


