package com.utephonehub.dao;

// Import các lớp cần thiết từ đúng package theo cấu trúc dự án của bạn
// CÁC IMPORT ĐÃ ĐƯỢC SỬA LẠI CHO ĐÚNG
import com.utephonehub.user.User;
import com.utephonehub.user.UserRole;
import com.utephonehub.user.UserStatus;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * MỤC ĐÍCH:
 * - Lớp này triển khai (implements) interface UserDAO.
 * - Nó giả lập việc tương tác với cơ sở dữ liệu bằng cách sử dụng một danh sách (List) trong bộ nhớ.
 * - Giúp cho việc phát triển và kiểm thử các lớp Service và API (Controller) có thể diễn ra độc lập
 *   mà không cần chờ đợi cơ sở dữ liệu được thiết lập.
 * LƯU Ý:
 * - Toàn bộ dữ liệu trong danh sách này sẽ bị mất khi ứng dụng được khởi động lại.
 */
public class UserDAOImpl implements UserDAO {

    // 1. KHO LƯU TRỮ DỮ LIỆU GIẢ LẬP
    // `private static final List<User> MOCK_USERS`:
    // - `private`: Chỉ có thể truy cập bên trong lớp này.
    // - `static`: Biến này thuộc về chính lớp UserDAOImpl, không phải của một đối tượng cụ thể nào.
    //   Nó được chia sẻ và chỉ tồn tại một bản duy nhất trong suốt vòng đời ứng dụng. Điều này làm cho
    //   nó hoạt động giống như một "bảng" CSDL.
    // - `final`: Tham chiếu đến danh sách MOCK_USERS không thể thay đổi, nhưng nội dung bên trong nó
    //   (thêm/sửa/xóa User) thì có thể.
    private static final List<User> MOCK_USERS = new ArrayList<>();


    // 2. KHỞI TẠO DỮ LIỆU MẪU
    // Khối `static {}`:
    // - Đây là một khối khởi tạo tĩnh (static initializer block).
    // - Code bên trong khối này sẽ được thực thi MỘT LẦN DUY NHẤT, ngay khi lớp UserDAOImpl được nạp
    //   vào bộ nhớ lần đầu tiên.
    // - Chúng ta dùng nó để chèn một vài dữ liệu mẫu vào `MOCK_USERS` để có cái để kiểm thử.
    static {
        // Tạo người dùng Admin
        User admin = new User();
        admin.setId(1L);
        admin.setFullName("Admin Master");
        admin.setEmail("admin@ute.com");
        admin.setPasswordHash("hashed_password_admin"); // Sẽ không lộ ra ngoài qua DTO
        admin.setPhoneNumber("0987654321");
        admin.setRole(UserRole.admin);
        admin.setStatus(UserStatus.active);
        admin.setCreatedAt(Instant.now().minus(10, ChronoUnit.DAYS)); // Tạo 10 ngày trước
        admin.setUpdatedAt(Instant.now().minus(1, ChronoUnit.DAYS));
        MOCK_USERS.add(admin);

        // Tạo người dùng khách hàng 1 (đang hoạt động)
        User customer1 = new User();
        customer1.setId(2L);
        customer1.setFullName("Nguyen Van An");
        customer1.setEmail("an.nguyen@example.com");
        customer1.setPasswordHash("hashed_password_a");
        customer1.setPhoneNumber("0123456789");
        customer1.setRole(UserRole.customer);
        customer1.setStatus(UserStatus.active);
        customer1.setCreatedAt(Instant.now().minus(5, ChronoUnit.DAYS));
        customer1.setUpdatedAt(Instant.now().minus(2, ChronoUnit.DAYS));
        MOCK_USERS.add(customer1);

        // Tạo người dùng khách hàng 2 (đã bị khóa)
        User customer2 = new User();
        customer2.setId(3L);
        customer2.setFullName("Tran Thi Binh");
        customer2.setEmail("binh.tran@example.com");
        customer2.setPasswordHash("hashed_password_b");
        customer2.setPhoneNumber("0909090909");
        customer2.setRole(UserRole.customer);
        customer2.setStatus(UserStatus.disabled); // Trạng thái bị khóa
        customer2.setCreatedAt(Instant.now().minus(3, ChronoUnit.DAYS));
        customer2.setUpdatedAt(Instant.now());
        MOCK_USERS.add(customer2);
    }

    /**
     * 3. LẤY DANH SÁCH NGƯỜI DÙNG (CÓ TÌM KIẾM VÀ PHÂN TRANG)
     * Đáp ứng cho API: GET /api/v1/admin/users
     */
    @Override
    public List<User> getUsers(int pageNumber, int pageSize, String searchTerm) {
        // Sử dụng Java Stream API để xử lý dữ liệu một cách linh hoạt.

        // BƯỚC A: LỌC DỮ LIỆU THEO `searchTerm`
        List<User> filteredUsers = MOCK_USERS.stream() // Chuyển danh sách thành một stream
                .filter(user -> { // Giữ lại những phần tử thỏa mãn điều kiện
                    // Nếu không có từ khóa tìm kiếm, hoặc từ khóa chỉ là khoảng trắng,
                    // thì xem như không lọc, trả về true để giữ lại tất cả người dùng.
                    if (searchTerm == null || searchTerm.trim().isEmpty()) {
                        return true;
                    }
                    // Chuyển cả từ khóa và dữ liệu về chữ thường để tìm kiếm không phân biệt hoa/thường.
                    String lowerCaseSearchTerm = searchTerm.toLowerCase().trim();
                    boolean nameMatches = user.getFullName().toLowerCase().contains(lowerCaseSearchTerm);
                    boolean emailMatches = user.getEmail().toLowerCase().contains(lowerCaseSearchTerm);
                    // Trả về true nếu tên HOẶC email khớp với từ khóa
                    return nameMatches || emailMatches;
                })
                .collect(Collectors.toList()); // Thu thập kết quả sau khi lọc vào một danh sách mới.

        // BƯỚC B: ÁP DỤNG PHÂN TRANG TRÊN DANH SÁCH ĐÃ LỌC
        // Tính toán vị trí bắt đầu (offset). Trang 1 bắt đầu từ index 0.
        int fromIndex = (pageNumber - 1) * pageSize;

        // Xử lý trường hợp pageNumber quá lớn, dẫn đến fromIndex vượt ra ngoài kích thước danh sách.
        if (fromIndex >= filteredUsers.size()) {
            return new ArrayList<>(); // Trả về danh sách rỗng, không có lỗi.
        }

        // Tính toán vị trí kết thúc. Dùng Math.min để không bị lỗi `IndexOutOfBoundsException`
        // nếu trang cuối cùng không đủ `pageSize` phần tử.
        int toIndex = Math.min(fromIndex + pageSize, filteredUsers.size());

        // Trả về một danh sách con (sublist) đại diện cho trang dữ liệu hiện tại.
        return filteredUsers.subList(fromIndex, toIndex);
    }

    /**
     * 4. ĐẾM TỔNG SỐ NGƯỜI DÙNG (THEO ĐIỀU KIỆN TÌM KIẾM)
     * Cần thiết cho việc tính toán tổng số trang ở phía Frontend.
     */
    @Override
    public int getTotalUsers(String searchTerm) {
        // Logic lọc tương tự như phương thức `getUsers`
        long total = MOCK_USERS.stream()
                .filter(user -> {
                    if (searchTerm == null || searchTerm.trim().isEmpty()) {
                        return true;
                    }
                    String lowerCaseSearchTerm = searchTerm.toLowerCase().trim();
                    return user.getFullName().toLowerCase().contains(lowerCaseSearchTerm) ||
                            user.getEmail().toLowerCase().contains(lowerCaseSearchTerm);
                })
                .count(); // Thay vì .collect(), ta dùng .count() để đếm số lượng phần tử.
        return (int) total;
    }

    /**
     * 5. LẤY CHI TIẾT MỘT NGƯỜI DÙNG BẰNG ID
     * Đáp ứng cho API: GET /api/v1/admin/users/{id}
     */
    @Override
    public Optional<User> findById(long id) {
        // Sử dụng stream để tìm người dùng.
        return MOCK_USERS.stream()
                .filter(user -> user.getId() == id) // Lọc ra người dùng có ID khớp
                .findFirst(); // Trả về `Optional<User>` chứa người dùng đầu tiên tìm thấy,
        // hoặc `Optional.empty()` nếu không tìm thấy.
        // `Optional` giúp tránh lỗi NullPointerException ở tầng Service.
    }


    /**
     * 6. CẬP NHẬT TRẠNG THÁI TÀI KHOẢN
     * Đáp ứng cho API: PUT /api/v1/admin/users/{id}/status
     */
    @Override
    public boolean updateUserStatus(long id, UserStatus status) { // <-- Sửa Long thành long ở đây
        // BƯỚC 1: Tìm người dùng cần cập nhật bằng phương thức `findById` đã viết.
        Optional<User> userOptional = this.findById(id);

        // BƯỚC 2: Kiểm tra xem người dùng có tồn tại không.
        if (userOptional.isPresent()) {
            // Nếu có, lấy đối tượng User ra khỏi Optional.
            User userToUpdate = userOptional.get();

            // Cập nhật trạng thái.
            userToUpdate.setStatus(status);

            // Cập nhật thời gian sửa đổi cuối cùng.
            userToUpdate.setUpdatedAt(Instant.now());

            return true; // Trả về true để báo hiệu cập nhật thành công.
        }

        // Nếu không tìm thấy người dùng (userOptional is empty), trả về false.
        return false;
    }
}