package com.utephonehub.service;

import com.utephonehub.dto.ApplyVoucherResponseDTO;
import com.utephonehub.model.Voucher;

import java.math.BigDecimal;
import java.util.List;

public interface IVoucherService {
    /**
     * Lấy danh sách tất cả các voucher.
     * @return Danh sách các đối tượng Voucher.
     */
    List<Voucher> getAllVouchers();

    /**
     * Xác thực một mã giảm giá.
     * @param code Mã voucher do người dùng nhập.
     * @param currentOrderTotal Tổng giá trị đơn hàng hiện tại.
     * @return một DTO chứa kết quả xác thực.
     */
    ApplyVoucherResponseDTO validateVoucher(String code, BigDecimal currentOrderTotal);

    // Các phương thức khác cho Admin sẽ được thêm vào đây sau
    // Voucher createVoucher(VoucherDTO newVoucher);
    // Voucher updateVoucher(int voucherId, VoucherDTO updatedVoucher);
    // boolean deleteVoucher(int voucherId);
}