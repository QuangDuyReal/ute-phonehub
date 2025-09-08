package com.utephonehub.user.dao;

import com.utephonehub.user.model.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(long id) throws SQLException;
    Optional<User> findByEmail(String email) throws SQLException;
    boolean existsEmail(String email) throws SQLException;
    User create(User user) throws SQLException;
    int updateProfile(long id, String fullName, String phone) throws SQLException;
    int updatePassword(long id, String passwordHash) throws SQLException;
}


