package tech.reliab.course.glazyrinaoa.bank.service;

import tech.reliab.course.glazyrinaoa.bank.entity.*;
import tech.reliab.course.glazyrinaoa.bank.exception.CreditException;
import tech.reliab.course.glazyrinaoa.bank.exception.IncomException;
import tech.reliab.course.glazyrinaoa.bank.exception.RatingException;

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

    List<BankOffice> getBankOfficeSuitableInBank(Bank bank, double money);

    boolean isBankSuitable(Bank bank, double money);

    List<Bank> getBanksSuitable(double sum, int countMonth) throws CreditException;

    boolean approveCredit(Bank bank, CreditAccount account, Employee employee) throws CreditException, RatingException, IncomException;

    boolean transferClient(User user, int newBankId);
}