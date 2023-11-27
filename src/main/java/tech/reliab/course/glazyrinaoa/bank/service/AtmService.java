package tech.reliab.course.glazyrinaoa.bank.service;

import java.util.*;

import tech.reliab.course.glazyrinaoa.bank.entity.BankAtm;

public interface AtmService {
    List<BankAtm> getAllBankAtms();

    BankAtm getBankAtmById(int id);

    boolean isAtmSuitable(BankAtm bankAtm, double money);

    boolean addMoney(BankAtm bankAtm, double amount);

    BankAtm create(BankAtm bankAtm);

}