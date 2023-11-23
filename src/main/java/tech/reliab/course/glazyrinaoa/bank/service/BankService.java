package tech.reliab.course.glazyrinaoa.bank.service;

import tech.reliab.course.glazyrinaoa.bank.entity.Bank;
import tech.reliab.course.glazyrinaoa.bank.entity.BankOffice;
import tech.reliab.course.glazyrinaoa.bank.entity.Employee;
import tech.reliab.course.glazyrinaoa.bank.entity.User;

import java.util.List;

public interface BankService {
    void setBankOfficeService(BankOfficeService bankOfficeService);

    void setClientService(UserService userService);

    // Создание банка
    Bank create(Bank bank);

    // Расчет процентной ставки банка
    double calculateInterestRate(Bank bank);

    Bank getBankById(int bankId);

    List<Bank> getAllBanks();

    List<BankOffice> getAllOfficesByBankId(int id);

    void printBankData(int bankId);

    boolean addOffice(int bankId, BankOffice bankOffice);

    boolean addUser(int id, User user);

    boolean addEmployee(Bank bank, Employee employee);
}