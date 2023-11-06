package tech.reliab.course.glazyrinaoa.bank.service.impl;

import tech.reliab.course.glazyrinaoa.bank.entity.BankOffice;
import tech.reliab.course.glazyrinaoa.bank.entity.Employee;
import tech.reliab.course.glazyrinaoa.bank.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Employee create(Employee employee) {
        if (employee == null) {
            return null;
        }

        if (employee.getSalary() < 0) {
            System.err.println("Ошибка! Работник - зарплата не может быть отрицательной.");
            return null;
        }

        return new Employee(employee);
    }

}