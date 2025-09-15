package com.utephonehub.dao.address;

import com.utephonehub.model.address.Address;
import com.utephonehub.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressDaoImpl implements AddressDao {

    @Override
    public List<Address> findAllByUser(int userId) throws SQLException {
        String sql = "SELECT id, user_id, recipient_name, phone_number, street_address, city, is_default, created_at, updated_at FROM addresses WHERE user_id = ? ORDER BY id DESC";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                List<Address> list = new ArrayList<>();
                while (rs.next()) list.add(mapRow(rs));
                return list;
            }
        }
    }

    @Override
    public Optional<Address> findByIdAndUser(int id, int userId) throws SQLException {
        String sql = "SELECT id, user_id, recipient_name, phone_number, street_address, city, is_default, created_at, updated_at FROM addresses WHERE id = ? AND user_id = ?";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(mapRow(rs));
            }
        }
    }

    @Override
    public Address create(Address address) throws SQLException {
        String sql = "INSERT INTO addresses(user_id, recipient_name, phone_number, street_address, city, is_default) VALUES(?,?,?,?,?,?) RETURNING id, created_at, updated_at";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, address.getUserId());
            ps.setString(2, address.getRecipientName());
            ps.setString(3, address.getPhoneNumber());
            ps.setString(4, address.getStreetAddress());
            ps.setString(5, address.getCity());
            ps.setBoolean(6, address.isDefault());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    address.setId(rs.getInt("id"));
                    Timestamp cAt = rs.getTimestamp("created_at");
                    Timestamp uAt = rs.getTimestamp("updated_at");
                    address.setCreatedAt(cAt != null ? cAt : new Timestamp(System.currentTimeMillis()));
                    address.setUpdatedAt(uAt != null ? uAt : address.getCreatedAt());
                }
            }
            return address;
        }
    }

    @Override
    public int update(Address address) throws SQLException {
        String sql = "UPDATE addresses SET recipient_name=?, phone_number=?, street_address=?, city=?, is_default=? WHERE id=? AND user_id=?";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, address.getRecipientName());
            ps.setString(2, address.getPhoneNumber());
            ps.setString(3, address.getStreetAddress());
            ps.setString(4, address.getCity());
            ps.setBoolean(5, address.isDefault());
            ps.setInt(6, address.getId());
            ps.setInt(7, address.getUserId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(int id, int userId) throws SQLException {
        String sql = "DELETE FROM addresses WHERE id = ? AND user_id = ?";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, userId);
            return ps.executeUpdate();
        }
    }

    @Override
    public int unsetDefaultForUser(int userId) throws SQLException {
        String sql = "UPDATE addresses SET is_default = FALSE WHERE user_id = ? AND is_default = TRUE";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            return ps.executeUpdate();
        }
    }

    private Address mapRow(ResultSet rs) throws SQLException {
        Address a = new Address();
        a.setId(rs.getInt("id"));
        a.setUserId(rs.getInt("user_id"));
        a.setRecipientName(rs.getString("recipient_name"));
        a.setPhoneNumber(rs.getString("phone_number"));
        a.setStreetAddress(rs.getString("street_address"));
        a.setCity(rs.getString("city"));
        a.setDefault(rs.getBoolean("is_default"));
        Timestamp cAt = rs.getTimestamp("created_at");
        Timestamp uAt = rs.getTimestamp("updated_at");
        a.setCreatedAt(cAt != null ? cAt : null);
        a.setUpdatedAt(uAt != null ? uAt : null);
        return a;
    }
}


