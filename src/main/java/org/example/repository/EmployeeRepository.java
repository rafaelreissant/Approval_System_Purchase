package org.example.repository;
import org.example.entity.Employee;
import java.util.*;

public class EmployeeRepository {

    private static final Map<UUID, Employee> employees = new HashMap<>();

    public boolean addEmployee(Employee employee){
        if (employees.containsKey(employee.getFullName())){
            return false;
        }

        employees.put(employee.getEmployeeId(), employee);
        return true;
    }

    public Employee findEmployeeById(UUID id){
        return employees.get(id);
    }

    public List<Employee> findAllEmployee(){
        return new ArrayList<>(employees.values());
    }
}