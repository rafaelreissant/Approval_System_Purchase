package org.example.service;

import org.example.entity.Employee;
import org.example.entity.Order;
import org.example.entity.OrderHistory;
import org.example.repository.EmployeeRepository;
import org.example.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
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

        Employee requester = employeeRepository.findEmployeeById(requesterId)
                .orElseThrow(() -> new RuntimeException("cannot be found"));

        Order order = new Order(description, value, requester);
        orderRepository.saveOrder(order);

        return order;
    }

    public void approveOrder(UUID orderId, UUID approverId){

        Order order = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Employee approver = employeeRepository.findEmployeeById(approverId)
                .orElseThrow(() -> new RuntimeException("cannot be found"));

        order.approve(approver);
    }

    public void rejectOrder(UUID orderId, UUID approverId){

        Order order = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Employee approver = employeeRepository.findEmployeeById(approverId)
                .orElseThrow(() -> new RuntimeException("cannot be found"));

        order.reject(approver);
    }

    public List<Order> findOrdersByRequester(UUID requesterId){

        Employee requester = employeeRepository.findEmployeeById(requesterId)
                .orElseThrow(() -> new RuntimeException("cannot be found"));

        return orderRepository.getRequester(requester);
    }

    public List<Order> findOrdersByApprover(UUID approverId){

        Employee approver = employeeRepository.findEmployeeById(approverId)
                .orElseThrow(() -> new RuntimeException("cannot be found"));

        return orderRepository.getApprover(approver);
    }

    public List<OrderHistory> getOrderHistory(UUID orderId){

        Order order = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return order.getHistory();
    }
}