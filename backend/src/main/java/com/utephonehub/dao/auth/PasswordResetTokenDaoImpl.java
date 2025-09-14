package com.utephonehub.dao.auth;

import com.utephonehub.model.auth.PasswordResetToken;
import com.utephonehub.util.DBUtil;

import java.sql.*;
import java.time.Instant;
import java.util.Optional;

public class PasswordResetTokenDaoImpl implements PasswordResetTokenDao {

    @Override
    public PasswordResetToken create(PasswordResetToken token) throws SQLException {
        String sql = "INSERT INTO password_reset_tokens(user_id, token, expiry_date) VALUES(?,?,?) RETURNING id, created_at";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, token.getUserId());
            ps.setString(2, token.getToken());
            ps.setTimestamp(3, Timestamp.from(token.getExpiryDate()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    token.setId(rs.getLong("id"));
                    Timestamp cAt = rs.getTimestamp("created_at");
                    token.setCreatedAt(cAt != null ? cAt.toInstant() : Instant.now());
                }
            }
            return token;
        }
    }

    @Override
    public Optional<PasswordResetToken> findByToken(String token) throws SQLException {
        String sql = "SELECT id, user_id, token, expiry_date, created_at FROM password_reset_tokens WHERE token = ?";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(mapRow(rs));
            }
        }
    }

    @Override
    public Optional<PasswordResetToken> findByUserId(long userId) throws SQLException {
        String sql = "SELECT id, user_id, token, expiry_date, created_at FROM password_reset_tokens WHERE user_id = ? ORDER BY created_at DESC LIMIT 1";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(mapRow(rs));
            }
        }
    }

    @Override
    public int delete(long id) throws SQLException {
        String sql = "DELETE FROM password_reset_tokens WHERE id = ?";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate();
        }
    }

    @Override
    public int deleteByUserId(long userId) throws SQLException {
        String sql = "DELETE FROM password_reset_tokens WHERE user_id = ?";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, userId);
            return ps.executeUpdate();
        }
    }

    @Override
    public int deleteExpiredTokens() throws SQLException {
        String sql = "DELETE FROM password_reset_tokens WHERE expiry_date < NOW()";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            return ps.executeUpdate();
        }
    }

    private PasswordResetToken mapRow(ResultSet rs) throws SQLException {
        PasswordResetToken token = new PasswordResetToken();
        token.setId(rs.getLong("id"));
        token.setUserId(rs.getLong("user_id"));
        token.setToken(rs.getString("token"));
        Timestamp expiry = rs.getTimestamp("expiry_date");
        Timestamp cAt = rs.getTimestamp("created_at");
        token.setExpiryDate(expiry != null ? expiry.toInstant() : null);
        token.setCreatedAt(cAt != null ? cAt.toInstant() : null);
        return token;
    }
}