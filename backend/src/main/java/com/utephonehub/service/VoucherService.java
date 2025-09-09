package com.utephonehub.service;

import com.utephonehub.dao.VoucherDAO;
import com.utephonehub.dto.ApplyVoucherResponseDTO;
import com.utephonehub.model.Voucher;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class VoucherService implements IVoucherService {

    private VoucherDAO voucherDAO;

    public VoucherService() {
        this.voucherDAO = new VoucherDAO();
    }

    /**
     * Lấy danh sách tất cả các voucher.
     * Logic đơn giản, chỉ cần gọi DAO.
     * @return Danh sách các đối tượng Voucher.
     */
    @Override
    public List<Voucher> getAllVouchers() {
        return voucherDAO.findAll();
    }

    /**
     * Logic nghiệp vụ cốt lõi: Xác thực một mã giảm giá.
     * @param code Mã voucher do người dùng nhập.
     * @param currentOrderTotal Tổng giá trị đơn hàng hiện tại của người dùng.
     * @return một DTO chứa kết quả xác thực.
     */
    @Override
    public ApplyVoucherResponseDTO validateVoucher(String code, BigDecimal currentOrderTotal) {
        Optional<Voucher> voucherOpt = voucherDAO.findByCode(code);

        // Trường hợp 1: Mã không tồn tại
        if (voucherOpt.isEmpty()) {
            return new ApplyVoucherResponseDTO(false, "Mã giảm giá không hợp lệ.", null, null);
        }

        Voucher voucher = voucherOpt.get();

        // Trường hợp 2: Voucher không được kích hoạt
        if (!voucher.isActive()) {
            return new ApplyVoucherResponseDTO(false, "Mã giảm giá đã bị vô hiệu hóa.", null, null);
        }

        // Trường hợp 3: Voucher đã hết hạn
        Timestamp now = Timestamp.from(Instant.now());
        if (voucher.getExpiryDate().before(now)) {
            return new ApplyVoucherResponseDTO(false, "Mã giảm giá đã hết hạn.", null, null);
        }

        // Trường hợp 4: Hết lượt sử dụng
        if (voucher.getCurrentUsage() >= voucher.getMaxUsage()) {
            return new ApplyVoucherResponseDTO(false, "Mã giảm giá đã hết lượt sử dụng.", null, null);
        }

        // Trường hợp 5: Giá trị đơn hàng không đủ điều kiện
        if (currentOrderTotal.compareTo(voucher.getMinOrderValue()) < 0) {
            String message = String.format("Mã này chỉ áp dụng cho đơn hàng từ %,.0fđ.", voucher.getMinOrderValue());
            return new ApplyVoucherResponseDTO(false, message, null, null);
        }

        // --- Mọi điều kiện đều hợp lệ ---
        
        // Tính toán giá trị được giảm
        BigDecimal discountAmount = calculateDiscount(voucher, currentOrderTotal);

        String successMessage = String.format("Áp dụng mã thành công! Bạn được giảm %,.0fđ.", discountAmount);
        
        return new ApplyVoucherResponseDTO(true, successMessage, voucher.getDiscountType(), discountAmount);
    }

    /**
     * Phương thức trợ giúp để tính toán số tiền được giảm thực tế.
     * @param voucher Đối tượng voucher.
     * @param orderTotal Tổng giá trị đơn hàng.
     * @return Số tiền được giảm.
     */
    private BigDecimal calculateDiscount(Voucher voucher, BigDecimal orderTotal) {
        if ("fixed_amount".equalsIgnoreCase(voucher.getDiscountType())) {
            return voucher.getDiscountValue();
        } 
        else if ("percentage".equalsIgnoreCase(voucher.getDiscountType())) {
            // Ví dụ: discountValue = 15.00 (cho 15%)
            BigDecimal discount = orderTotal.multiply(voucher.getDiscountValue().divide(new BigDecimal("100")));
            return discount;
            // Nâng cao: Có thể thêm giới hạn giảm giá tối đa cho voucher percentage
            // Ví dụ: giảm 15% tối đa 50.000đ
        }
        return BigDecimal.ZERO;
    }
    
    // TODO: Triển khai các service cho Admin: create, update, delete.
}