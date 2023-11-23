package tech.reliab.course.glazyrinaoa.bank.service;

import java.util.*;

import tech.reliab.course.glazyrinaoa.bank.entity.PaymentAccount;

public interface PaymentAccountService {
    List<PaymentAccount> getAllPaymentAccounts();

    PaymentAccount getPaymentAccountById(int id);

    void printPaymentData(int id);

    PaymentAccount create(PaymentAccount paymentAccount);

    boolean depositMoney(PaymentAccount paymentAccount, double amount);

    boolean withdrawMoney(PaymentAccount paymentAccount, double amount);
}