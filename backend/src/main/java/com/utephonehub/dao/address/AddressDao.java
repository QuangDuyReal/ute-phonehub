package com.utephonehub.dao.address;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.utephonehub.model.address.Address;

public interface AddressDao {
    List<Address> findAllByUser(long userId) throws SQLException;
    Optional<Address> findByIdAndUser(long id, long userId) throws SQLException;
    Address create(Address address) throws SQLException;
    int update(Address address) throws SQLException;
    int delete(long id, long userId) throws SQLException;
    int unsetDefaultForUser(long userId) throws SQLException;
}


