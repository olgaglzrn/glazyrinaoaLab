package tech.reliab.course.glazyrinaoa.bank.service.impl;

import java.util.*;

import tech.reliab.course.glazyrinaoa.bank.entity.PaymentAccount;
import tech.reliab.course.glazyrinaoa.bank.service.PaymentAccountService;
import tech.reliab.course.glazyrinaoa.bank.service.UserService;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    Map<Integer, PaymentAccount> paymentAccountsTable = new HashMap<Integer, PaymentAccount>();
    private final UserService userService;

    public PaymentAccountServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccounts() {
        return new ArrayList<PaymentAccount>(paymentAccountsTable.values());
    }

    @Override
    public PaymentAccount getPaymentAccountById(int id) {
        PaymentAccount account = paymentAccountsTable.get(id);
        if (account == null) {
            System.err.println("Payment account with id " + id + " is not found");
        }
        return account;
    }

    @Override
    public void printPaymentData(int id) {
        PaymentAccount account = getPaymentAccountById(id);
        if (account == null) {
            return;
        }

        System.out.println(account);
    }

    @Override
    public PaymentAccount create(PaymentAccount paymentAccount) {
        if (paymentAccount == null) {
            return null;
        }

        if (paymentAccount.getBalance() < 0) {
            System.err.println("Error: PaymentAccount - payment account balance doesn't be negative");
            return null;
        }

        PaymentAccount newAccount = new PaymentAccount(paymentAccount);
        paymentAccountsTable.put(newAccount.getId(), newAccount);
        userService.addPaymentAccount(newAccount.getClient().getId(), newAccount);

        return newAccount;
    }

    @Override
    public boolean depositMoney(PaymentAccount paymentAccount, double amount) {
        if (paymentAccount == null) {
            System.err.println("Error: PaymentAccount - non existing payment account");
            return false;
        }

        if (amount <= 0) {
            System.err.println("Error: PaymentAccount - deposit amount doesn't be negative");
            return false;
        }

        paymentAccount.setBalance(paymentAccount.getBalance() + amount);
        return true;
    }

    @Override
    public boolean withdrawMoney(PaymentAccount paymentAccount, double amount) {
        if (paymentAccount == null) {
            System.err.println("Ошибка! Платежный счет - несуществующий платежный счет.");
            return false;
        }

        if (amount <= 0) {
            System.err.println("Ошибка! Платежный счет - сумма вывода не может быть отрицательной.");
            return false;
        }

        paymentAccount.setBalance(paymentAccount.getBalance() - amount);

        return true;
    }

}
