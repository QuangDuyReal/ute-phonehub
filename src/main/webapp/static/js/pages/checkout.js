/**
 * Checkout Page JavaScript
 * Handles checkout flow: addresses, payment, vouchers, place order
 */

document.addEventListener('DOMContentLoaded', () => {
    // Check authentication
    if (!isLoggedIn()) {
        showToast('Vui lòng đăng nhập để thanh toán', 'warning');
        setTimeout(() => {
            window.location.href = `/login?redirect=${encodeURIComponent('/cart/checkout')}`;
        }, 2000);
        return;
    }
    
    // Initialize checkout page
    initCheckoutPage();
});

const contextPath = document.body.dataset.contextPath || '';
let selectedAddressId = null;
let cartData = null;
let provinces = [];
let districts = [];
let wards = [];

/**
 * Initialize checkout page
 */
async function initCheckoutPage() {
    try {
        showLoading();
        
        // Load cart, addresses, and provinces in parallel
        const [cart, addresses, provincesData] = await Promise.all([
            loadCart(),
            loadSavedAddresses(),
            loadProvinces()
        ]);
        
        if (!cart || !cart.items || cart.items.length === 0) {
            showError('Giỏ hàng trống. Vui lòng thêm sản phẩm trước khi thanh toán.');
            setTimeout(() => {
                window.location.href = `${contextPath}/cart`;
            }, 2000);
            return;
        }
        
        cartData = cart;
        provinces = provincesData || [];
        
        renderOrderSummary(cart);
        renderSavedAddresses(addresses);
        populateProvinces();
        setupEventListeners();
        
        hideLoading();
    } catch (error) {
        console.error('Error initializing checkout:', error);
        showError('Không thể tải trang thanh toán. Vui lòng thử lại.');
    }
}

/**
 * Load cart from API
 */
async function loadCart() {
    try {
        const response = await API.get('/cart/');
        if (response.success && response.data) {
            return response.data;
        }
        throw new Error(response.message || 'Failed to load cart');
    } catch (error) {
        console.error('Error loading cart:', error);
        throw error;
    }
}

/**
 * Load saved addresses
 */
async function loadSavedAddresses() {
    try {
        const response = await API.get('/user/addresses');
        if (response.success && response.data) {
            return response.data;
        }
        return [];
    } catch (error) {
        console.error('Error loading addresses:', error);
        return [];
    }
}

/**
 * Load provinces
 */
async function loadProvinces() {
    try {
        const response = await API.get('/provinces');
        if (response.success && response.data) {
            return response.data;
        }
        return [];
    } catch (error) {
        console.error('Error loading provinces:', error);
        return [];
    }
}

/**
 * Render saved addresses
 */
function renderSavedAddresses(addresses) {
    const container = document.getElementById('saved-addresses');
    if (!container) return;
    
    if (!addresses || addresses.length === 0) {
        container.innerHTML = `
            <div class="alert alert-info">
                <i class="fas fa-info-circle me-2"></i>
                Bạn chưa có địa chỉ đã lưu. Vui lòng thêm địa chỉ giao hàng.
            </div>
        `;
        // Auto show new address form
        document.getElementById('new-address-form').classList.remove('d-none');
        document.getElementById('toggle-address-form').style.display = 'none';
        return;
    }
    
    container.innerHTML = addresses.map((addr, index) => `
        <div class="saved-address ${index === 0 ? 'selected' : ''}" data-address-id="${addr.id}">
            <div class="address-name">${escapeHtml(addr.recipientName || 'Người nhận')}</div>
            <div class="address-phone">
                <i class="fas fa-phone me-1"></i>${escapeHtml(addr.phoneNumber || '')}
            </div>
            <div class="address-detail">
                ${escapeHtml(addr.streetAddress || '')}, 
                ${escapeHtml(addr.ward || '')}, 
                ${escapeHtml(addr.district || '')}, 
                ${escapeHtml(addr.city || '')}
            </div>
            ${addr.isDefault ? '<div class="address-badges"><span class="badge bg-primary">Mặc định</span></div>' : ''}
        </div>
    `).join('');
    
    // Set first address as selected
    if (addresses.length > 0) {
        selectedAddressId = addresses[0].id;
    }
}

/**
 * Render order summary
 */
function renderOrderSummary(cart) {
    // Render items
    const itemsContainer = document.getElementById('order-items-summary');
    if (itemsContainer) {
        itemsContainer.innerHTML = cart.items.map(item => `
            <div class="order-item-summary">
                <img src="${escapeHtml(item.thumbnailUrl || 'placeholder')}" 
                     alt="${escapeHtml(item.productName)}" 
                     class="order-item-image">
                <div class="order-item-info">
                    <div class="order-item-name">${escapeHtml(item.productName)}</div>
                    <div class="order-item-quantity">Số lượng: ${item.quantity}</div>
                </div>
                <div class="order-item-price">${formatPrice(item.lineTotal || (item.price * item.quantity))}</div>
            </div>
        `).join('');
    }
    
    // Update summary totals
    updateOrderSummary(cart);
}

/**
 * Update order summary totals
 */
function updateOrderSummary(cart) {
    const subtotal = cart.totalPrice || 0;
    const shipping = subtotal >= 1000000 ? 0 : 30000; // Free shipping > 1M VND
    const discount = cart.discountAmount || 0;
    const total = subtotal + shipping - discount;
    
    document.getElementById('summary-subtotal').textContent = formatPrice(subtotal);
    document.getElementById('summary-shipping').textContent = shipping === 0 ? 'Miễn phí' : formatPrice(shipping);
    document.getElementById('summary-discount').textContent = formatPrice(discount);
    document.getElementById('summary-total').textContent = formatPrice(total);
    
    // Show/hide discount row
    const discountRow = document.getElementById('discount-row');
    if (discount > 0) {
        discountRow.classList.add('active');
    } else {
        discountRow.classList.remove('active');
    }
}

/**
 * Populate provinces dropdown
 */
function populateProvinces() {
    const citySelect = document.getElementById('city');
    if (!citySelect || !provinces || provinces.length === 0) return;
    
    citySelect.innerHTML = '<option value="">Chọn tỉnh/thành</option>' +
        provinces.map(p => `<option value="${p.id}">${escapeHtml(p.name)}</option>`).join('');
}

/**
 * Load districts by province
 */
async function loadDistricts(provinceId) {
    try {
        const response = await API.get(`/provinces/${provinceId}/districts`);
        if (response.success && response.data) {
            districts = response.data;
            populateDistricts();
        }
    } catch (error) {
        console.error('Error loading districts:', error);
    }
}

/**
 * Populate districts dropdown
 */
function populateDistricts() {
    const districtSelect = document.getElementById('district');
    if (!districtSelect) return;
    
    districtSelect.innerHTML = '<option value="">Chọn quận/huyện</option>' +
        districts.map(d => `<option value="${d.id}">${escapeHtml(d.name)}</option>`).join('');
    
    // Reset ward
    document.getElementById('ward').innerHTML = '<option value="">Chọn phường/xã</option>';
}

/**
 * Load wards by district
 */
async function loadWards(districtId) {
    try {
        const response = await API.get(`/districts/${districtId}/wards`);
        if (response.success && response.data) {
            wards = response.data;
            populateWards();
        }
    } catch (error) {
        console.error('Error loading wards:', error);
    }
}

/**
 * Populate wards dropdown
 */
function populateWards() {
    const wardSelect = document.getElementById('ward');
    if (!wardSelect) return;
    
    wardSelect.innerHTML = '<option value="">Chọn phường/xã</option>' +
        wards.map(w => `<option value="${w.id}">${escapeHtml(w.name)}</option>`).join('');
}

/**
 * Setup event listeners
 */
function setupEventListeners() {
    // Address selection
    document.getElementById('saved-addresses').addEventListener('click', (e) => {
        const addressDiv = e.target.closest('.saved-address');
        if (addressDiv) {
            document.querySelectorAll('.saved-address').forEach(a => a.classList.remove('selected'));
            addressDiv.classList.add('selected');
            selectedAddressId = parseInt(addressDiv.dataset.addressId);
        }
    });
    
    // Toggle new address form
    document.getElementById('toggle-address-form').addEventListener('click', () => {
        const form = document.getElementById('new-address-form');
        const btn = document.getElementById('toggle-address-form');
        
        if (form.classList.contains('d-none')) {
            form.classList.remove('d-none');
            btn.innerHTML = '<i class="fas fa-minus me-2"></i>Ẩn form';
        } else {
            form.classList.add('d-none');
            btn.innerHTML = '<i class="fas fa-plus me-2"></i>Thêm địa chỉ mới';
        }
    });
    
    // Province change
    document.getElementById('city').addEventListener('change', (e) => {
        const provinceId = e.target.value;
        if (provinceId) {
            loadDistricts(provinceId);
        } else {
            document.getElementById('district').innerHTML = '<option value="">Chọn quận/huyện</option>';
            document.getElementById('ward').innerHTML = '<option value="">Chọn phường/xã</option>';
        }
    });
    
    // District change
    document.getElementById('district').addEventListener('change', (e) => {
        const districtId = e.target.value;
        if (districtId) {
            loadWards(districtId);
        } else {
            document.getElementById('ward').innerHTML = '<option value="">Chọn phường/xã</option>';
        }
    });
    
    // Apply voucher
    document.getElementById('apply-voucher-btn').addEventListener('click', handleApplyVoucher);
    
    // Place order
    document.getElementById('place-order-btn').addEventListener('click', handlePlaceOrder);
}

/**
 * Handle apply voucher
 */
async function handleApplyVoucher() {
    const voucherCode = document.getElementById('voucher-code').value.trim();
    const messageDiv = document.getElementById('voucher-message');
    
    if (!voucherCode) {
        messageDiv.innerHTML = '<i class="fas fa-exclamation-circle"></i> Vui lòng nhập mã giảm giá';
        messageDiv.className = 'error';
        return;
    }
    
    try {
        // TODO: Implement voucher API when available
        messageDiv.innerHTML = '<i class="fas fa-info-circle"></i> Tính năng áp dụng mã giảm giá đang được phát triển';
        messageDiv.className = 'error';
        
        /*
        const response = await API.post('/vouchers/apply', { code: voucherCode });
        
        if (response.success) {
            messageDiv.innerHTML = `<i class="fas fa-check-circle"></i> Đã áp dụng mã giảm giá: ${voucherCode}`;
            messageDiv.className = 'success';
            
            // Reload cart to get updated prices
            cartData = await loadCart();
            updateOrderSummary(cartData);
        } else {
            messageDiv.innerHTML = `<i class="fas fa-times-circle"></i> ${response.message || 'Mã giảm giá không hợp lệ'}`;
            messageDiv.className = 'error';
        }
        */
    } catch (error) {
        console.error('Error applying voucher:', error);
        messageDiv.innerHTML = '<i class="fas fa-times-circle"></i> Đã xảy ra lỗi khi áp dụng mã giảm giá';
        messageDiv.className = 'error';
    }
}

/**
 * Handle place order
 */
async function handlePlaceOrder() {
    const placeOrderBtn = document.getElementById('place-order-btn');
    
    // Validate address
    const addressForm = document.getElementById('address-form');
    const isNewAddressVisible = !document.getElementById('new-address-form').classList.contains('d-none');
    
    let shippingData = {};
    
    if (isNewAddressVisible) {
        // Validate new address form
        if (!addressForm.checkValidity()) {
            addressForm.classList.add('was-validated');
            showToast('Vui lòng điền đầy đủ thông tin giao hàng', 'error');
            return;
        }
        
        // Collect form data
        const formData = new FormData(addressForm);
        shippingData = {
            recipientName: formData.get('recipientName'),
            phoneNumber: formData.get('phoneNumber'),
            email: formData.get('email') || '',
            city: document.getElementById('city').options[document.getElementById('city').selectedIndex].text,
            district: document.getElementById('district').options[document.getElementById('district').selectedIndex].text,
            ward: document.getElementById('ward').options[document.getElementById('ward').selectedIndex].text,
            streetAddress: formData.get('streetAddress'),
            notes: formData.get('notes') || ''
        };
    } else {
        // Use selected saved address
        if (!selectedAddressId) {
            showToast('Vui lòng chọn địa chỉ giao hàng', 'error');
            return;
        }
        
        shippingData = {
            addressId: selectedAddressId
        };
    }
    
    // Get payment method
    const paymentMethod = document.querySelector('input[name="paymentMethod"]:checked').value;
    
    // Prepare checkout data
    const checkoutData = {
        ...shippingData,
        paymentMethod: paymentMethod,
        voucherCode: document.getElementById('voucher-code').value.trim() || null
    };
    
    // Show loading
    placeOrderBtn.classList.add('loading');
    placeOrderBtn.disabled = true;
    
    try {
        const response = await API.post('/checkout', checkoutData);
        
        if (response.success && response.data) {
            const order = response.data;
            
            showToast('Đặt hàng thành công!', 'success');
            
            // Redirect to success page or order detail
            setTimeout(() => {
                window.location.href = `${contextPath}/orders/${order.id}?success=true`;
            }, 1500);
        } else {
            showToast(response.message || 'Không thể đặt hàng. Vui lòng thử lại.', 'error');
            placeOrderBtn.classList.remove('loading');
            placeOrderBtn.disabled = false;
        }
    } catch (error) {
        console.error('Error placing order:', error);
        showToast('Đã xảy ra lỗi khi đặt hàng. Vui lòng thử lại.', 'error');
        placeOrderBtn.classList.remove('loading');
        placeOrderBtn.disabled = false;
    }
}

/**
 * Show loading state
 */
function showLoading() {
    document.getElementById('checkout-loading').classList.remove('d-none');
    document.getElementById('checkout-content').classList.add('d-none');
    document.getElementById('checkout-error').classList.add('d-none');
}

/**
 * Hide loading state
 */
function hideLoading() {
    document.getElementById('checkout-loading').classList.add('d-none');
    document.getElementById('checkout-content').classList.remove('d-none');
}

/**
 * Show error state
 */
function showError(message) {
    document.getElementById('checkout-error-message').textContent = message;
    document.getElementById('checkout-error').classList.remove('d-none');
    document.getElementById('checkout-loading').classList.add('d-none');
    document.getElementById('checkout-content').classList.add('d-none');
}

