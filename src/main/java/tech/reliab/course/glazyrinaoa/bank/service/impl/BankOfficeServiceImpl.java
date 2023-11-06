package tech.reliab.course.glazyrinaoa.bank.service.impl;

import java.math.BigDecimal;

import tech.reliab.course.glazyrinaoa.bank.entity.BankOffice;
import tech.reliab.course.glazyrinaoa.bank.service.BankOfficeService;

public class BankOfficeServiceImpl implements BankOfficeService {
    @Override
    public BankOffice create(BankOffice bankOffice) {
        if (bankOffice == null) {
            return null;
        }

        if (bankOffice.getTotalMoney() < 0) {
            System.err.println("Ошибка! Офис банка - общая сумма не может быть отрицальной.");
            return null;
        }

        if (bankOffice.getBank() == null) {
            System.err.println("Ошибка! Офис банка - должен иметь банк.");
            return null;
        }

        if (bankOffice.getRentPrice() < 0) {
            System.err.println("Ошибка! Офис банка - Арендная плата не может быть отрицальной.");
            return null;
        }

        return new BankOffice(bankOffice);
    }
}