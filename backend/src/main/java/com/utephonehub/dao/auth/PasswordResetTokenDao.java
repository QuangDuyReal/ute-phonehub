package com.utephonehub.dao.auth;

import java.sql.SQLException;
import java.util.Optional;

import com.utephonehub.model.auth.PasswordResetToken;

public interface PasswordResetTokenDao {
    PasswordResetToken create(PasswordResetToken token) throws SQLException;
    Optional<PasswordResetToken> findByToken(String token) throws SQLException;
    Optional<PasswordResetToken> findByUserId(int userId) throws SQLException;
    int delete(int id) throws SQLException;
    int deleteByUserId(int userId) throws SQLException;
    int deleteExpiredTokens() throws SQLException;
}