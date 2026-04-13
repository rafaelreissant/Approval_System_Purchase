package org.example.entity;

import java.time.LocalDateTime;

public class OrderHistory {
    private String action;
    private Employee actor;
    private LocalDateTime timestamp;

    public OrderHistory(String action, Employee actor) {
        this.action = action;
        this.actor = actor;
        this.timestamp = LocalDateTime.now();
    }

    public String getAction() {
        return action;
    }

    public Employee getActor() {
        return actor;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
