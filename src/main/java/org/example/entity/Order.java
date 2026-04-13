package org.example.entity;

import org.example.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.*;

public class Order {
    private UUID orderId;
    private String description;
    private BigDecimal purchaseValue;
    private OrderStatus orderStatus;
    private Employee requester;
    private Employee approver;

    private List<OrderHistory> history = new ArrayList<>();

    public Order(String description, BigDecimal purchaseValue, Employee requester) {
        this.orderId = UUID.randomUUID();
        this.description = description;
        this.purchaseValue = purchaseValue;
        this.orderStatus = OrderStatus.PENDING;
        this.requester = requester;

        history.add(new OrderHistory("Created", requester));
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

        history.add(new OrderHistory("Approved", approver));
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

        history.add(new OrderHistory("Rejected", approver));
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

    public BigDecimal getPurchaseValue() {
        return purchaseValue;
    }


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Employee getEmployee() {
        return requester;
    }


    public Employee getRequester() {
        return requester;
    }

    public Employee getApprover() {
        return approver;
    }

    public List<OrderHistory> getHistory(){
        return Collections.unmodifiableList(history);
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