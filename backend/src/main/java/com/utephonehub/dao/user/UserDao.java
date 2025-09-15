package com.utephonehub.dao.user;

import java.sql.SQLException;
import java.util.Optional;

import com.utephonehub.model.user.User;

public interface UserDao {
    Optional<User> findById(int id) throws SQLException;
    Optional<User> findByEmail(String email) throws SQLException;
    boolean existsEmail(String email) throws SQLException;
    User create(User user) throws SQLException;
    int updateProfile(int id, String fullName, String phoneNumber) throws SQLException;
    int updatePassword(int id, String passwordHash) throws SQLException;
}


