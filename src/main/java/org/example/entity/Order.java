package org.example.entity;

import org.example.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private UUID orderId;
    private String description;
    private BigDecimal purchaseValue;
    private OrderStatus orderStatus;
    private Employee requester;
    private Employee approver;

    public Order(String description, BigDecimal purchaseValue, Employee requester) {
        this.orderId = UUID.randomUUID();
        this.description = description;
        this.purchaseValue = purchaseValue;
        this.orderStatus = OrderStatus.PENDING;
        this.requester = requester;
    }

    public void approve(Employee approver){
        if (orderStatus != OrderStatus.PENDING){
            throw new RuntimeException("Order already finished");
        }

        if (requester.equals(approver)){
            throw new RuntimeException("Requester and approver can´t be the same ");
        }

        if (!approver.canApprove(purchaseValue)){
            throw new RuntimeException("Approver does not have permission");
        }


        this.approver = approver;
        this.orderStatus = OrderStatus.APPROVED;
    }

    public void reject(Employee approver){
        if (orderStatus != OrderStatus.PENDING){
            throw new RuntimeException("Order already finished");
        }

        if (!approver.canApprove(purchaseValue)){
            throw new RuntimeException("Approver does not have permission");
        }

        this.approver = approver;
        this.orderStatus = OrderStatus.REPROVED;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(BigDecimal purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Employee getEmployee() {
        return requester;
    }

    public void setEmployee(Employee requester) {
        this.requester = requester;
    }

    public Employee getRequester() {
        return requester;
    }

    public void setRequester(Employee requester) {
        this.requester = requester;
    }

    public Employee getApprover() {
        return approver;
    }

    public void setApprover(Employee approver) {
        this.approver = approver;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", description='" + description + '\'' +
                ", purchaseValue=" + purchaseValue +
                ", orderStatus=" + orderStatus +
                ", employee=" + requester +
                '}';
    }
}