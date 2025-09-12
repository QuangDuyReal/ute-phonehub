package com.utephonehub.dao;

import com.utephonehub.user.User;
import com.utephonehub.user.UserStatus;
import java.util.List;
import java.util.Optional;

public interface UserDAO {
    /**
     * Lấy danh sách người dùng có phân trang và tìm kiếm.
     * @param pageNumber Số trang hiện tại (bắt đầu từ 1).
     * @param pageSize Số lượng người dùng trên mỗi trang.
     * @param searchTerm Từ khóa tìm kiếm theo email hoặc tên.
     * @return Danh sách người dùng.
     */
    List<User> getUsers(int pageNumber, int pageSize, String searchTerm);

    /**
     * Đếm tổng số người dùng khớp với điều kiện tìm kiếm.
     * @param searchTerm Từ khóa tìm kiếm theo email hoặc tên.
     * @return Tổng số người dùng.
     */
    int getTotalUsers(String searchTerm);

    /**
     * Lấy thông tin chi tiết của một người dùng bằng ID.
     * @param id ID của người dùng.
     * @return Một Optional chứa User nếu tìm thấy.
     */
    Optional<User> findById(long id);

    /**
     * Cập nhật trạng thái của một người dùng.
     * @param id ID của người dùng cần cập nhật.
     * @param status Trạng thái mới (active hoặc locked).
     * @return true nếu cập nhật thành công, false nếu thất bại.
     */
    boolean updateUserStatus(long id, UserStatus status);
}