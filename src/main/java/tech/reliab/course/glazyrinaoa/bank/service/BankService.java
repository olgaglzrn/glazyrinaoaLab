package tech.reliab.course.glazyrinaoa.bank.service;

import tech.reliab.course.glazyrinaoa.bank.entity.Bank;

public interface BankService {
    // Создание банка
    Bank create(Bank bank);

    // Расчет процентной ставки банка
    double calculateInterestRate(Bank bank);
}