package tech.reliab.course.glazyrinaoa.bank.service.impl;

import tech.reliab.course.glazyrinaoa.bank.entity.BankAtm;
import tech.reliab.course.glazyrinaoa.bank.service.AtmService;

public class AtmServiceImpl implements AtmService {

    @Override
    public BankAtm create(BankAtm bankAtm) {
        if (bankAtm == null) {
            return null;
        }
        if (bankAtm.getTotalMoney() < 0) {
            System.err.println("Error: cannot create BankAtm - totalMoney doesn't be negative");
            return null;
        }
        if (bankAtm.getMaintenanceCost() < 0) {
            System.err.println("Error: cannot create BankAtm - maintenanceCost doesn't be negative");
            return null;
        }
        if (bankAtm.getBankOffice() == null) {
            System.err.println("Error: cannot create BankAtm - bankOffice can't be null");
            return null;
        }
        return new BankAtm(bankAtm);
    }


}