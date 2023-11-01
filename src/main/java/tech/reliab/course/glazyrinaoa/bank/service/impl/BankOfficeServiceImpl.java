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
            System.err.println("Error: BankOffice - total money must be non-negative");
            return null;
        }

        if (bankOffice.getBank() == null) {
            System.err.println("Error: BankOffice - must have bank");
            return null;
        }

        if (bankOffice.getRentPrice() < 0) {
            System.err.println("Error: BankOffice - rentPrice must be non-negative");
            return null;
        }

        return new BankOffice(bankOffice);
    }
}