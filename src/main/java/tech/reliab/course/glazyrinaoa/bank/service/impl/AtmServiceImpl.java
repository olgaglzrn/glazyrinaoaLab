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
            System.err.println("Ошибка! Невозможно создать банкомат, так как общая сумма денег не может быть отрицательной. ");
            return null;
        }
        if (bankAtm.getMaintenanceCost() < 0) {
            System.err.println("Ошибка! Невозможно создать банкомат, так как затраты на техническое обслуживание не могут быть отрицательными.");
            return null;
        }
        if (bankAtm.getBankOffice() == null) {
            System.err.println("Ошибка! Невозможно создать банкомат, так как офис банка не может быть пустым.");
            return null;
        }
        return new BankAtm(bankAtm);
    }


}