package org.example.entity.enums;

import java.math.BigDecimal;

public enum EmployeePosition {
    EMPLOYEE(BigDecimal.ZERO),
    SUPERVISOR(new BigDecimal("1000")),
    MANAGER(new BigDecimal("5000")),
    DIRECTOR(new BigDecimal("999999999"));

    private final BigDecimal approvalLimit;

    EmployeePosition(BigDecimal approvalLimit){
        this.approvalLimit = approvalLimit;
    }

    public BigDecimal getApprovalLimit(){
        return approvalLimit;
    }
}
