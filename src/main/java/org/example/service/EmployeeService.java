package org.example.service;

import org.example.entity.Employee;
import org.example.entity.enums.EmployeePosition;
import org.example.repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee createEmployee(String name, String department, String email, EmployeePosition position){
        Employee employee = new Employee(name, department, email, position);
        repository.addEmployee(employee);
        return employee;
    }

    public List<Employee> listEmployees(){
        return repository.findAllEmployee();
    }
}