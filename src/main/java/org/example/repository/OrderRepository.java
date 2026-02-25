package org.example.repository;

import org.example.entity.Employee;
import org.example.entity.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderRepository {

    private static final Map<UUID, Order> orders = new HashMap<>();

    public void saveOrder(Order order){
        orders.put(order.getOrderId(), order);
    }

    public Order findOrderById(UUID id){
        return orders.get(id);
    }

    public List<Order> getRequester(Employee employee){
        return orders.values()
                .stream()
                .filter(o -> o.getRequester().equals(employee))
                .toList();
    }

    public List<Order> getApprover(Employee employee){
        return orders.values()
                .stream()
                .filter(o -> o.getApprover().equals(employee))
                .toList();
    }
}