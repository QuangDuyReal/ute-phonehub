package com.utephonehub.dao;

/**
 * Composite key class for OrderItem
 * Used as ID type in GenericDAO implementation
 */
public class OrderItemKey {
    private int orderId;
    private int productId;
    
    public OrderItemKey() {}
    
    public OrderItemKey(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }
    
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        OrderItemKey that = (OrderItemKey) obj;
        return orderId == that.orderId && productId == that.productId;
    }
    
    @Override
    public int hashCode() {
        return 31 * orderId + productId;
    }
    
    @Override
    public String toString() {
        return "OrderItemKey{orderId=" + orderId + ", productId=" + productId + '}';
    }
}
