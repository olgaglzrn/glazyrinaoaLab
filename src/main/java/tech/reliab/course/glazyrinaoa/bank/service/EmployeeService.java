package tech.reliab.course.glazyrinaoa.bank.service;

import tech.reliab.course.glazyrinaoa.bank.entity.BankOffice;
import tech.reliab.course.glazyrinaoa.bank.entity.Employee;

import java.util.*;

public interface EmployeeService {
    boolean isEmployeeSuitable(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(int id);

    Employee create(Employee employee);

}