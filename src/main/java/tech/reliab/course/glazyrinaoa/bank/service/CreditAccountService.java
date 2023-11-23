package tech.reliab.course.glazyrinaoa.bank.service;

import tech.reliab.course.glazyrinaoa.bank.entity.CreditAccount;

import java.util.*;

public interface CreditAccountService {
    List<CreditAccount> getAllCreditAccounts();

    CreditAccount getCreditAccountById(int id);

    CreditAccount create(CreditAccount creditAccount);

}
