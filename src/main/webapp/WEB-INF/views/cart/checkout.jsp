<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Thanh toán" scope="request"/>
<c:set var="pageCss" value="pages/checkout.css" scope="request"/>
<c:set var="pageJs" value="pages/checkout.js" scope="request"/>
<!DOCTYPE html>
<html lang="vi">
<head>
    <%@ include file="/WEB-INF/views/common/meta.jspf" %>
    <title><c:out value="${pageTitle}"/> - UTE Phone Hub</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/components/product.css">
    <c:if test="${not empty pageCss}">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/${pageCss}">
    </c:if>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jspf" %>
    
    <main class="main-content container my-4">
        <!-- Breadcrumb -->
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Trang chủ</a></li>
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/cart">Giỏ hàng</a></li>
                <li class="breadcrumb-item active" aria-current="page">Thanh toán</li>
            </ol>
        </nav>

        <h1 class="page-heading mb-4">Thanh toán đơn hàng</h1>

        <!-- Progress Steps -->
        <div class="checkout-progress mb-4">
            <div class="progress-step completed">
                <div class="step-number">1</div>
                <div class="step-label">Giỏ hàng</div>
            </div>
            <div class="progress-line completed"></div>
            <div class="progress-step active">
                <div class="step-number">2</div>
                <div class="step-label">Thanh toán</div>
            </div>
            <div class="progress-line"></div>
            <div class="progress-step">
                <div class="step-number">3</div>
                <div class="step-label">Hoàn tất</div>
            </div>
        </div>

        <!-- Loading State -->
        <div id="checkout-loading" class="text-center py-5 d-none">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Đang tải...</span>
            </div>
            <p class="mt-3 text-muted">Đang tải thông tin đơn hàng...</p>
        </div>

        <!-- Error State -->
        <div id="checkout-error" class="alert alert-danger d-none">
            <i class="fas fa-exclamation-circle me-2"></i>
            <span id="checkout-error-message">Có lỗi xảy ra. Vui lòng thử lại sau.</span>
        </div>

        <!-- Main Checkout Content -->
        <div id="checkout-content" class="row">
            <!-- Left Column: Shipping Info & Payment -->
            <div class="col-lg-8">
                <!-- Shipping Address Section -->
                <div class="card shadow-sm mb-4">
                    <div class="card-header bg-white">
                        <h5 class="mb-0">
                            <i class="fas fa-shipping-fast text-primary me-2"></i>
                            Thông tin giao hàng
                        </h5>
                    </div>
                    <div class="card-body">
                        <!-- Saved Addresses -->
                        <div id="saved-addresses" class="mb-3">
                            <!-- Loaded dynamically by JS -->
                        </div>

                        <!-- New Address Form -->
                        <div id="new-address-form" class="d-none">
                            <form id="address-form">
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="recipient-name" class="form-label">Họ và tên người nhận <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control" id="recipient-name" name="recipientName" required>
                                        <div class="invalid-feedback">Vui lòng nhập họ tên</div>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="phone-number" class="form-label">Số điện thoại <span class="text-danger">*</span></label>
                                        <input type="tel" class="form-control" id="phone-number" name="phoneNumber" pattern="[0-9]{10,11}" required>
                                        <div class="invalid-feedback">Số điện thoại không hợp lệ</div>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="email" name="email">
                                </div>
                                <div class="row">
                                    <div class="col-md-4 mb-3">
                                        <label for="city" class="form-label">Tỉnh/Thành phố <span class="text-danger">*</span></label>
                                        <select class="form-select" id="city" name="city" required>
                                            <option value="">Chọn tỉnh/thành</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4 mb-3">
                                        <label for="district" class="form-label">Quận/Huyện <span class="text-danger">*</span></label>
                                        <select class="form-select" id="district" name="district" required>
                                            <option value="">Chọn quận/huyện</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4 mb-3">
                                        <label for="ward" class="form-label">Phường/Xã <span class="text-danger">*</span></label>
                                        <select class="form-select" id="ward" name="ward" required>
                                            <option value="">Chọn phường/xã</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="street-address" class="form-label">Địa chỉ cụ thể <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="street-address" name="streetAddress" placeholder="Số nhà, tên đường..." required>
                                    <div class="invalid-feedback">Vui lòng nhập địa chỉ</div>
                                </div>
                                <div class="mb-3">
                                    <label for="notes" class="form-label">Ghi chú giao hàng</label>
                                    <textarea class="form-control" id="notes" name="notes" rows="3" placeholder="Ví dụ: Giao giờ hành chính, gọi trước 15 phút..."></textarea>
                                </div>
                            </form>
                        </div>

                        <button type="button" class="btn btn-outline-primary btn-sm" id="toggle-address-form">
                            <i class="fas fa-plus me-2"></i>Thêm địa chỉ mới
                        </button>
                    </div>
                </div>

                <!-- Payment Method Section -->
                <div class="card shadow-sm mb-4">
                    <div class="card-header bg-white">
                        <h5 class="mb-0">
                            <i class="fas fa-credit-card text-primary me-2"></i>
                            Phương thức thanh toán
                        </h5>
                    </div>
                    <div class="card-body">
                        <div class="payment-methods">
                            <div class="form-check payment-option mb-3">
                                <input class="form-check-input" type="radio" name="paymentMethod" id="payment-cod" value="COD" checked>
                                <label class="form-check-label" for="payment-cod">
                                    <div class="d-flex align-items-center">
                                        <i class="fas fa-money-bill-wave fa-2x text-success me-3"></i>
                                        <div>
                                            <strong>Thanh toán khi nhận hàng (COD)</strong>
                                            <p class="text-muted small mb-0">Thanh toán bằng tiền mặt khi nhận hàng</p>
                                        </div>
                                    </div>
                                </label>
                            </div>
                            <div class="form-check payment-option mb-3">
                                <input class="form-check-input" type="radio" name="paymentMethod" id="payment-bank" value="BANK_TRANSFER">
                                <label class="form-check-label" for="payment-bank">
                                    <div class="d-flex align-items-center">
                                        <i class="fas fa-university fa-2x text-primary me-3"></i>
                                        <div>
                                            <strong>Chuyển khoản ngân hàng</strong>
                                            <p class="text-muted small mb-0">Chuyển khoản qua ngân hàng (sẽ có hướng dẫn sau)</p>
                                        </div>
                                    </div>
                                </label>
                            </div>
                            <div class="form-check payment-option">
                                <input class="form-check-input" type="radio" name="paymentMethod" id="payment-card" value="CREDIT_CARD">
                                <label class="form-check-label" for="payment-card">
                                    <div class="d-flex align-items-center">
                                        <i class="fas fa-credit-card fa-2x text-info me-3"></i>
                                        <div>
                                            <strong>Thẻ tín dụng/Ghi nợ</strong>
                                            <p class="text-muted small mb-0">Visa, Mastercard, JCB (đang phát triển)</p>
                                        </div>
                                    </div>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Right Column: Order Summary -->
            <div class="col-lg-4">
                <div class="card shadow-sm sticky-top" style="top: 20px;">
                    <div class="card-header bg-primary text-white">
                        <h5 class="mb-0">Thông tin đơn hàng</h5>
                    </div>
                    <div class="card-body">
                        <!-- Order Items -->
                        <div id="order-items-summary" class="mb-3">
                            <!-- Loaded dynamically -->
                        </div>

                        <hr>

                        <!-- Voucher -->
                        <div class="mb-3">
                            <label for="voucher-code" class="form-label">Mã giảm giá</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="voucher-code" placeholder="Nhập mã giảm giá">
                                <button class="btn btn-outline-secondary" type="button" id="apply-voucher-btn">
                                    <i class="fas fa-tags me-1"></i>Áp dụng
                                </button>
                            </div>
                            <div id="voucher-message" class="mt-2"></div>
                        </div>

                        <hr>

                        <!-- Order Summary -->
                        <div class="order-summary">
                            <div class="d-flex justify-content-between mb-2">
                                <span>Tạm tính:</span>
                                <strong id="summary-subtotal">0₫</strong>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span>Phí vận chuyển:</span>
                                <strong id="summary-shipping">0₫</strong>
                            </div>
                            <div class="d-flex justify-content-between mb-2 text-success" id="discount-row" style="display: none !important;">
                                <span>Giảm giá:</span>
                                <strong id="summary-discount">-0₫</strong>
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between mb-3">
                                <span class="h5">Tổng cộng:</span>
                                <strong class="h5 text-danger" id="summary-total">0₫</strong>
                            </div>
                        </div>

                        <!-- Place Order Button -->
                        <button type="button" class="btn btn-danger btn-lg w-100" id="place-order-btn">
                            <i class="fas fa-check-circle me-2"></i>Đặt hàng
                        </button>

                        <div class="mt-3 text-center text-muted small">
                            <i class="fas fa-shield-alt me-1"></i>
                            Thông tin của bạn được bảo mật
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <%@ include file="/WEB-INF/views/common/footer.jspf" %>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- jQuery (optional) -->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <!-- Custom JS -->
    <script src="${pageContext.request.contextPath}/static/js/utils.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/api.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/auth.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/cart.js"></script>
    <c:if test="${not empty pageJs}">
        <script src="${pageContext.request.contextPath}/static/js/${pageJs}"></script>
    </c:if>
</body>
</html>

