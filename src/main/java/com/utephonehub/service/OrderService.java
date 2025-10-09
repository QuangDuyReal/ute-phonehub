package com.utephonehub.service;

import com.utephonehub.entity.*;
import com.utephonehub.repository.*;

import java.math.BigDecimal;
import java.util.*;

public class OrderService {
    
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final VoucherRepository voucherRepository;
    private final ProductRepository productRepository;
    
    public OrderService() {
        this.orderRepository = new OrderRepository();
        this.cartRepository = new CartRepository();
        this.voucherRepository = new VoucherRepository();
        this.productRepository = new ProductRepository();
    }
    
    public Map<String, Object> checkout(Long userId, Map<String, Object> shippingInfo, 
                                       String voucherCode, String paymentMethod) {
        
        // Get user's cart
        Optional<Cart> cartOpt = userId != null ? cartRepository.findByUserId(userId) : Optional.empty();
        
        if (cartOpt.isEmpty() || cartOpt.get().getItems().isEmpty()) {
            throw new RuntimeException("Giỏ hàng trống");
        }
        
        Cart cart = cartOpt.get();
        
        // Calculate total
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartItem item : cart.getItems()) {
            BigDecimal lineTotal = item.getProduct().getPrice()
                .multiply(new BigDecimal(item.getQuantity()));
            totalAmount = totalAmount.add(lineTotal);
        }
        
        // Apply voucher if provided
        Voucher voucher = null;
        if (voucherCode != null && !voucherCode.isEmpty()) {
            Optional<Voucher> voucherOpt = voucherRepository.findByCode(voucherCode);
            if (voucherOpt.isEmpty()) {
                throw new RuntimeException("Mã giảm giá không tồn tại");
            }
            
            voucher = voucherOpt.get();
            
            if (!voucherRepository.isVoucherValid(voucher)) {
                throw new RuntimeException("Mã giảm giá không hợp lệ hoặc đã hết hạn");
            }
            
            // Check min order value
            if (voucher.getMinOrderValue() != null && 
                totalAmount.compareTo(voucher.getMinOrderValue()) < 0) {
                throw new RuntimeException("Đơn hàng chưa đủ giá trị tối thiểu để áp dụng mã giảm giá");
            }
            
            // Apply discount
            if (voucher.getDiscountType() == Voucher.DiscountType.PERCENTAGE) {
                BigDecimal discount = totalAmount.multiply(voucher.getDiscountValue())
                    .divide(new BigDecimal(100));
                totalAmount = totalAmount.subtract(discount);
            } else if (voucher.getDiscountType() == Voucher.DiscountType.FIXED_AMOUNT) {
                totalAmount = totalAmount.subtract(voucher.getDiscountValue());
            }
            
            // Ensure total is not negative
            if (totalAmount.compareTo(BigDecimal.ZERO) < 0) {
                totalAmount = BigDecimal.ZERO;
            }
        }
        
        // Create order
        Order order = new Order();
        order.setOrderCode(orderRepository.generateOrderCode());
        
        // Set user if logged in
        if (userId != null) {
            User user = new User();
            user.setId(userId);
            order.setUser(user);
        }
        
        // Set shipping info
        order.setEmail((String) shippingInfo.get("email"));
        order.setRecipientName((String) shippingInfo.get("recipientName"));
        order.setPhoneNumber((String) shippingInfo.get("phoneNumber"));
        order.setStreetAddress((String) shippingInfo.get("streetAddress"));
        order.setCity((String) shippingInfo.get("city"));
        
        // Set payment method
        order.setPaymentMethod(Order.PaymentMethod.valueOf(paymentMethod.toUpperCase()));
        
        order.setTotalAmount(totalAmount);
        order.setVoucher(voucher);
        order.setStatus(Order.OrderStatus.PENDING);
        
        // Save order first to get ID
        Order savedOrder = orderRepository.save(order);
        
        // Create order items from cart
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            
            savedOrder.getItems().add(orderItem);
            
            // Decrease stock
            Product product = cartItem.getProduct();
            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            productRepository.save(product);
        }
        
        // Save order with items
        savedOrder = orderRepository.save(savedOrder);
        
        // Clear cart
        for (CartItem item : new ArrayList<>(cart.getItems())) {
            cartRepository.deleteCartItem(item.getId());
        }
        
        // Build response
        Map<String, Object> response = new HashMap<>();
        response.put("orderId", savedOrder.getId());
        response.put("orderCode", savedOrder.getOrderCode());
        response.put("totalAmount", savedOrder.getTotalAmount());
        
        return response;
    }
    
    public List<Map<String, Object>> getUserOrders(Long userId, int page, int limit) {
        List<Order> orders = orderRepository.findByUserId(userId, page, limit);
        
        List<Map<String, Object>> result = new ArrayList<>();
        for (Order order : orders) {
            Map<String, Object> orderData = new HashMap<>();
            orderData.put("id", order.getId());
            orderData.put("orderCode", order.getOrderCode());
            orderData.put("status", order.getStatus().toString());
            orderData.put("totalAmount", order.getTotalAmount());
            orderData.put("createdAt", order.getCreatedAt());
            
            result.add(orderData);
        }
        
        return result;
    }
    
    public Map<String, Object> getOrderDetail(Long orderId, Long userId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        
        if (orderOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy đơn hàng với ID: " + orderId);
        }
        
        Order order = orderOpt.get();
        
        // Check if order belongs to user
        if (userId != null && (order.getUser() == null || !order.getUser().getId().equals(userId))) {
            throw new RuntimeException("Bạn không có quyền xem đơn hàng này");
        }
        
        return buildOrderDetailResponse(order);
    }
    
    public Map<String, Object> lookupOrder(String orderCode, String email) {
        Optional<Order> orderOpt = orderRepository.findByOrderCodeAndEmail(orderCode, email);
        
        if (orderOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy đơn hàng với thông tin đã cung cấp");
        }
        
        return buildOrderDetailResponse(orderOpt.get());
    }
    
    private Map<String, Object> buildOrderDetailResponse(Order order) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", order.getId());
        response.put("orderCode", order.getOrderCode());
        response.put("status", order.getStatus().toString());
        response.put("paymentMethod", order.getPaymentMethod().toString());
        response.put("totalAmount", order.getTotalAmount());
        
        // Shipping info
        Map<String, Object> shippingInfo = new HashMap<>();
        shippingInfo.put("recipientName", order.getRecipientName());
        shippingInfo.put("phoneNumber", order.getPhoneNumber());
        shippingInfo.put("email", order.getEmail());
        shippingInfo.put("streetAddress", order.getStreetAddress());
        shippingInfo.put("city", order.getCity());
        response.put("shippingInfo", shippingInfo);
        
        // Items
        List<Map<String, Object>> items = new ArrayList<>();
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                Map<String, Object> itemData = new HashMap<>();
                itemData.put("productId", item.getProduct().getId());
                itemData.put("productName", item.getProduct().getName());
                itemData.put("quantity", item.getQuantity());
                itemData.put("price", item.getPrice());
                itemData.put("lineTotal", item.getPrice().multiply(new BigDecimal(item.getQuantity())));
                
                items.add(itemData);
            }
        }
        response.put("items", items);
        
        // Voucher
        if (order.getVoucher() != null) {
            Map<String, Object> voucherData = new HashMap<>();
            voucherData.put("code", order.getVoucher().getCode());
            response.put("voucher", voucherData);
        }
        
        response.put("createdAt", order.getCreatedAt());
        
        return response;
    }
}
