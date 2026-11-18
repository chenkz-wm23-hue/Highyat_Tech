package model;

import java.math.BigDecimal;

public class Order {
    private int id;                  
    private int customerId;          
    private String paymentMethod;    
    private String orderStatus;      // Order status (e.g., "Pending", "Shipped", "Delivered")
    private BigDecimal totalAmount; 
    private String createdAt;        

    public Order() {
    }

    public Order(int id, int customerId, String paymentMethod, String orderStatus, BigDecimal totalAmount, String createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
