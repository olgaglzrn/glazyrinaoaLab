package tech.reliab.course.glazyrinaoa.bank.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.reliab.course.glazyrinaoa.bank.entity.BankAtm;
import tech.reliab.course.glazyrinaoa.bank.service.AtmService;
import tech.reliab.course.glazyrinaoa.bank.service.BankOfficeService;

public class AtmServiceImpl implements AtmService {

    Map<Integer, BankAtm> atmTable = new HashMap<Integer, BankAtm> ();

    private final BankOfficeService bankOfficeService;

    public AtmServiceImpl(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public List<BankAtm> getAllBankAtms() {
        return new ArrayList<>(atmTable.values());
    }

    @Override
    public BankAtm getBankAtmById(int id) {
        BankAtm atm = atmTable.get(id);
        if (atm == null) {
            System.err.println("Банкомат не найден");
        }
        return atm;
    }

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
        BankAtm newAtm = new BankAtm(bankAtm);
        atmTable.put(newAtm.getId(), newAtm);
        bankOfficeService.addAtm(newAtm.getBankOffice().getId(), newAtm);

        return newAtm;
    }


}