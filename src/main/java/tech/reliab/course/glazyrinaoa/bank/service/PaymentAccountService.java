package tech.reliab.course.glazyrinaoa.bank.service;

import java.math.BigDecimal;

import tech.reliab.course.glazyrinaoa.bank.entity.PaymentAccount;

public interface PaymentAccountService {
    PaymentAccount create(PaymentAccount paymentAccount);

    boolean depositMoney(PaymentAccount paymentAccount, double amount);

    boolean withdrawMoney(PaymentAccount paymentAccount, double amount);
}