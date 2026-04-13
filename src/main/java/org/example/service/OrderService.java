package org.example.service;

import org.example.entity.Employee;
import org.example.entity.Order;
import org.example.entity.OrderHistory;
import org.example.repository.EmployeeRepository;
import org.example.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderService {

    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;

    public OrderService(OrderRepository orderRepository,
                        EmployeeRepository employeeRepository) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
    }

    public Order createOrder(String description, BigDecimal value, UUID requesterId){

        Employee requester = employeeRepository.findEmployeeById(requesterId);

        if(requester == null){
            throw new RuntimeException("Employee not found");
        }

        Order order = new Order(description, value, requester);
        orderRepository.saveOrder(order);

        return order;
    }

    public void approveOrder(UUID orderId, UUID approverId){

        Order order = orderRepository.findOrderById(orderId);
        Employee approver = employeeRepository.findEmployeeById(approverId);

        if(order == null){
            throw new RuntimeException("Order not found");
        }

        if(approver == null){
            throw new RuntimeException("Approver not found");
        }

        order.approve(approver);
    }

    public void rejectOrder(UUID orderId, UUID approverId){

        Order order = orderRepository.findOrderById(orderId);
        Employee approver = employeeRepository.findEmployeeById(approverId);

        if(order == null){
            throw new RuntimeException("Order not found");
        }

        if(approver == null){
            throw new RuntimeException("Approver not found");
        }

        order.reject(approver);
    }

    public List<Order> findOrdersByRequester(UUID requesterId){

        Employee requester = employeeRepository.findEmployeeById(requesterId);

        if(requester == null){
            throw new RuntimeException("Employee not found");
        }

        return orderRepository.getRequester(requester);
    }

    public List<Order> findOrdersByApprover(UUID approverId){

        Employee approver = employeeRepository.findEmployeeById(approverId);

        if(approver == null){
            throw new RuntimeException("Employee not found");
        }

        return orderRepository.getApprover(approver);
    }

    public List<OrderHistory> getOrderHistory(UUID orderId){

        Order order = new OrderRepository().findOrderById(orderId);

        if (order == null){
            throw new RuntimeException("Order not found");
        }

        return order.getHistory();
    }
}