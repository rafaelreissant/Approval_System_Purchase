package org.example.entity;

import org.example.entity.enums.EmployeePosition;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Employee {
    private UUID employeeId;
    private String fullName;
    private String department;
    private String emailCorp;
    private EmployeePosition employeePosition;


    public Employee(String fullName, String department, String emailCorp, EmployeePosition employeePosition){
        this.employeeId = UUID.randomUUID();
        this.fullName = fullName;
        this.department = department;
        this.emailCorp = emailCorp;
        this.employeePosition = employeePosition;
    }

    public boolean canApprove(BigDecimal orderValue){
        return employeePosition.getApprovalLimit().compareTo(orderValue) >= 0;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmailCorp() {
        return emailCorp;
    }

    public void setEmailCorp(String emailCorp) {
        this.emailCorp = emailCorp;
    }

    public EmployeePosition getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(EmployeePosition employeePosition) {
        this.employeePosition = employeePosition;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(employeeId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", fullName='" + fullName + '\'' +
                ", department='" + department + '\'' +
                ", emailCorp='" + emailCorp + '\'' +
                '}';
    }
}