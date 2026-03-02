package org.example;

import org.example.entity.Employee;
import org.example.entity.Order;
import org.example.entity.enums.EmployeePosition;
import org.example.repository.EmployeeRepository;
import org.example.repository.OrderRepository;
import org.example.service.EmployeeService;
import org.example.service.OrderService;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EmployeeRepository employeeRepository = new EmployeeRepository();
        OrderRepository orderRepository = new OrderRepository();

        EmployeeService employeeService = new EmployeeService(employeeRepository);
        OrderService orderService = new OrderService(orderRepository, employeeRepository);

        boolean running = true;

        while (running) {

            System.out.println("\n=== MENU ===");
            System.out.println("1 - Create Employee");
            System.out.println("2 - Create Order");
            System.out.println("3 - Approve Order");
            System.out.println("4 - List Orders by Employee");
            System.out.println("0 - Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {

                case 1:
                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Department: ");
                    String department = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.println("Select position:");
                    EmployeePosition[] positions = EmployeePosition.values();

                    for (int i = 0; i < positions.length; i++) {
                        System.out.println(i + " - " + positions[i]);
                    }

                    int posIndex = scanner.nextInt();
                    scanner.nextLine();

                    EmployeePosition position = positions[posIndex];

                    Employee employee = employeeService
                            .createEmployee(name, department, email, position);

                    System.out.println("Employee created with ID: " + employee.getEmployeeId());
                    break;

                case 2:
                    System.out.print("Description: ");
                    String description = scanner.nextLine();

                    System.out.print("Value: ");
                    BigDecimal value = scanner.nextBigDecimal();
                    scanner.nextLine();

                    System.out.print("Requester ID: ");
                    UUID requesterId = UUID.fromString(scanner.nextLine());

                    Order order = orderService.createOrder(description, value, requesterId);
                    System.out.println("Order created with ID: " + order.getOrderId());
                    break;

                case 3:
                    System.out.print("Order ID: ");
                    UUID orderId = UUID.fromString(scanner.nextLine());

                    System.out.print("Approver ID: ");
                    UUID approverId = UUID.fromString(scanner.nextLine());

                    orderService.approveOrder(orderId, approverId);
                    System.out.println("Order approved.");
                    break;

                case 4:
                    System.out.print("Employee ID: ");
                    UUID employeeId = UUID.fromString(scanner.nextLine());

                    orderService.findOrdersByRequester(employeeId)
                            .forEach(o -> System.out.println(
                                    o.getOrderId() + " - " + o.getDescription() + " - " + o.getApprover()
                            ));
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }

        scanner.close();
    }
}
