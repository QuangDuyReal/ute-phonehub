package com.utephonehub.dao.user;

import java.sql.SQLException;
import java.util.Optional;

import com.utephonehub.model.user.User;

public interface UserDao {
    Optional<User> findById(long id) throws SQLException;
    Optional<User> findByEmail(String email) throws SQLException;
    boolean existsEmail(String email) throws SQLException;
    User create(User user) throws SQLException;
    int updateProfile(long id, String fullName, String phoneNumber) throws SQLException;
    int updatePassword(long id, String passwordHash) throws SQLException;
}


