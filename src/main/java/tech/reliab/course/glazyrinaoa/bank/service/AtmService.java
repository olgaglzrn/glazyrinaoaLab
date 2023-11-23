package tech.reliab.course.glazyrinaoa.bank.service;

import java.util.*;

import tech.reliab.course.glazyrinaoa.bank.entity.BankAtm;

public interface AtmService {
    List<BankAtm> getAllBankAtms();

    BankAtm getBankAtmById(int id);

    BankAtm create(BankAtm bankAtm);

}