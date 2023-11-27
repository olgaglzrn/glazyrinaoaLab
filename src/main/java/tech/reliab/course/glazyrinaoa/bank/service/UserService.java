package tech.reliab.course.glazyrinaoa.bank.service;

import java.util.*;

import tech.reliab.course.glazyrinaoa.bank.entity.CreditAccount;
import tech.reliab.course.glazyrinaoa.bank.entity.PaymentAccount;
import tech.reliab.course.glazyrinaoa.bank.entity.User;

public interface UserService {
    User create(User user);

    double calculateCreditRating(User user);

    boolean addCreditAccount(int id, CreditAccount account);

    boolean addPaymentAccount(int id, PaymentAccount account);

    List<PaymentAccount> getAllPaymentAccountsByClientId(int id);

    List<CreditAccount> getAllCreditAccountsByClientId(int id);

    List<User> getAllUsers();

    User getUserById(int id);

    void printUserData(int id, boolean withAccounts);

    PaymentAccount getBestPaymentAccount(int id);
}
