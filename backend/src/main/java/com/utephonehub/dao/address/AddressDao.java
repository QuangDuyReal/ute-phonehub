package com.utephonehub.dao.address;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.utephonehub.model.address.Address;

public interface AddressDao {
    List<Address> findAllByUser(int userId) throws SQLException;
    Optional<Address> findByIdAndUser(int id, int userId) throws SQLException;
    Address create(Address address) throws SQLException;
    int update(Address address) throws SQLException;
    int delete(int id, int userId) throws SQLException;
    int unsetDefaultForUser(int userId) throws SQLException;
}


