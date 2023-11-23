package tech.reliab.course.glazyrinaoa.bank.service.impl;

import java.util.*;

import tech.reliab.course.glazyrinaoa.bank.entity.Employee;
import tech.reliab.course.glazyrinaoa.bank.service.BankOfficeService;
import tech.reliab.course.glazyrinaoa.bank.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
    Map<Integer, Employee> employeeTable  = new HashMap<Integer, Employee>();
    private final BankOfficeService bankOfficeService;

    public EmployeeServiceImpl(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public boolean isEmployeeSuitable(Employee employee) {
        return employee.getIsCreditAvailable();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<Employee>(employeeTable.values());
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = employeeTable.get(id);
        if (employee == null) {
            System.err.println("Employee with id " + id + " is not found");
        }
        return employee;
    }

    @Override
    public Employee create(Employee employee) {
        if (employee == null) {
            return null;
        }

        if (employee.getSalary() < 0) {
            System.err.println("Ошибка! Работник - зарплата не может быть отрицательной.");
            return null;
        }

        Employee newEmployee = new Employee(employee);
        employeeTable.put(newEmployee.getId(), newEmployee);
        bankOfficeService.addEmployee(newEmployee.getBankOffice().getId(), newEmployee);

        return newEmployee;
    }

}