package tech.reliab.course.glazyrinaoa.bank.service;

import java.util.*;

import tech.reliab.course.glazyrinaoa.bank.entity.BankAtm;
import tech.reliab.course.glazyrinaoa.bank.entity.BankOffice;
import tech.reliab.course.glazyrinaoa.bank.entity.Employee;

public interface BankOfficeService {
    void setEmployeeService(EmployeeService employeeService);

    void setAtmService(AtmService atmService);

    List<Employee> getAllEmployeesByOfficeId(int id);

    List<BankOffice> getAllOffices();

    BankOffice getBankOfficeById(int id);

    BankOffice create(BankOffice bankOffice);

    void printBankOfficeData(int id);

    boolean addAtm(int id, BankAtm bankAtm);

    boolean addEmployee(int id, Employee employee);

    boolean addMoney(BankOffice bankOffice, double amount);

    List<BankAtm> getSuitableBankAtmInOffice(BankOffice bankOffice, double money);

    List<Employee> getSuitableEmployeeInOffice(BankOffice bankOffice);

    boolean isSuitableBankOffice(BankOffice bankOffice, double money);
}