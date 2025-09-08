package com.utephonehub.user.dao;

import com.utephonehub.user.model.Address;
import com.utephonehub.util.DbUtil;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressDaoImpl implements AddressDao {

    @Override
    public List<Address> findAllByUser(long userId) throws SQLException {
        String sql = "SELECT id, user_id, receiver_name, phone, line1, line2, ward, district, city, is_default, created_at, updated_at FROM addresses WHERE user_id = ? ORDER BY id DESC";
        try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                List<Address> list = new ArrayList<>();
                while (rs.next()) list.add(mapRow(rs));
                return list;
            }
        }
    }

    @Override
    public Optional<Address> findByIdAndUser(long id, long userId) throws SQLException {
        String sql = "SELECT id, user_id, receiver_name, phone, line1, line2, ward, district, city, is_default, created_at, updated_at FROM addresses WHERE id = ? AND user_id = ?";
        try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.setLong(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(mapRow(rs));
            }
        }
    }

    @Override
    public Address create(Address address) throws SQLException {
        String sql = "INSERT INTO addresses(user_id, receiver_name, phone, line1, line2, ward, district, city, is_default) VALUES(?,?,?,?,?,?,?,?,?) RETURNING id, created_at, updated_at";
        try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, address.getUserId());
            ps.setString(2, address.getReceiverName());
            ps.setString(3, address.getPhone());
            ps.setString(4, address.getLine1());
            ps.setString(5, address.getLine2());
            ps.setString(6, address.getWard());
            ps.setString(7, address.getDistrict());
            ps.setString(8, address.getCity());
            ps.setBoolean(9, address.isDefault());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    address.setId(rs.getLong("id"));
                    Timestamp cAt = rs.getTimestamp("created_at");
                    Timestamp uAt = rs.getTimestamp("updated_at");
                    address.setCreatedAt(cAt != null ? cAt.toInstant() : Instant.now());
                    address.setUpdatedAt(uAt != null ? uAt.toInstant() : address.getCreatedAt());
                }
            }
            return address;
        }
    }

    @Override
    public int update(Address address) throws SQLException {
        String sql = "UPDATE addresses SET receiver_name=?, phone=?, line1=?, line2=?, ward=?, district=?, city=?, is_default=? WHERE id=? AND user_id=?";
        try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, address.getReceiverName());
            ps.setString(2, address.getPhone());
            ps.setString(3, address.getLine1());
            ps.setString(4, address.getLine2());
            ps.setString(5, address.getWard());
            ps.setString(6, address.getDistrict());
            ps.setString(7, address.getCity());
            ps.setBoolean(8, address.isDefault());
            ps.setLong(9, address.getId());
            ps.setLong(10, address.getUserId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(long id, long userId) throws SQLException {
        String sql = "DELETE FROM addresses WHERE id = ? AND user_id = ?";
        try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.setLong(2, userId);
            return ps.executeUpdate();
        }
    }

    @Override
    public int unsetDefaultForUser(long userId) throws SQLException {
        String sql = "UPDATE addresses SET is_default = FALSE WHERE user_id = ? AND is_default = TRUE";
        try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, userId);
            return ps.executeUpdate();
        }
    }

    private Address mapRow(ResultSet rs) throws SQLException {
        Address a = new Address();
        a.setId(rs.getLong("id"));
        a.setUserId(rs.getLong("user_id"));
        a.setReceiverName(rs.getString("receiver_name"));
        a.setPhone(rs.getString("phone"));
        a.setLine1(rs.getString("line1"));
        a.setLine2(rs.getString("line2"));
        a.setWard(rs.getString("ward"));
        a.setDistrict(rs.getString("district"));
        a.setCity(rs.getString("city"));
        a.setDefault(rs.getBoolean("is_default"));
        Timestamp cAt = rs.getTimestamp("created_at");
        Timestamp uAt = rs.getTimestamp("updated_at");
        a.setCreatedAt(cAt != null ? cAt.toInstant() : null);
        a.setUpdatedAt(uAt != null ? uAt.toInstant() : null);
        return a;
    }
}


