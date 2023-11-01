package tech.reliab.course.glazyrinaoa.bank.service.impl;

import java.math.BigDecimal;

import tech.reliab.course.glazyrinaoa.bank.entity.CreditAccount;
import tech.reliab.course.glazyrinaoa.bank.service.CreditAccountService;

public class CreditAccountServiceImpl implements CreditAccountService {

    @Override
    public CreditAccount create(CreditAccount creditAccount) {
        if (creditAccount == null) {
            return null;
        }

//        if (creditAccount.getMonthCount() < 1) {
//            System.err.println("Error: CreditAccount - monthCount must be at least 1");
//            return null;
//        }

//        if (creditAccount.getCreditAmount().signum() <= 0) {
//            System.err.println("Error: CreditAccount - creditAmount doesn't be negative");
//            return null;
//        }
//
//        if (creditAccount.getBank() == null) {
//            System.err.println("Error: CreditAccount - creditAmount haven't bank");
//            return null;
//        }

        return new CreditAccount(creditAccount);
    }

}