package tech.reliab.course.glazyrinaoa.bank.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.reliab.course.glazyrinaoa.bank.entity.BankOffice;
import tech.reliab.course.glazyrinaoa.bank.service.BankOfficeService;
import tech.reliab.course.glazyrinaoa.bank.entity.BankAtm;
import tech.reliab.course.glazyrinaoa.bank.entity.Employee;
import tech.reliab.course.glazyrinaoa.bank.service.BankService;

public class BankOfficeServiceImpl implements BankOfficeService {
    Map<Integer, BankOffice> bankOfficesTable  = new HashMap<Integer, BankOffice>();
    Map<Integer, List<Employee>> employeByOfficeIdTable   = new HashMap<Integer, List<Employee>>();
    Map<Integer, List<BankAtm>> atmByOfficeIdTable   = new HashMap<Integer, List<BankAtm>>();

    private final BankService bankService;

    public BankOfficeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public List<Employee> getAllEmployeesByOfficeId(int id) {
        return employeByOfficeIdTable.get(id);
    }

    @Override
    public List<BankOffice> getAllOffices() {
        return new ArrayList<BankOffice>(bankOfficesTable.values());
    }

    @Override
    public BankOffice getBankOfficeById(int id) {
        BankOffice office = bankOfficesTable.get(id);
        if (office == null) {
            System.err.println("Офис не найден");
        }
        return office;
    }
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

        BankOffice newOffice = new BankOffice(bankOffice);
        bankOfficesTable.put(bankOffice.getId(), bankOffice);
        employeByOfficeIdTable.put(bankOffice.getId(), new ArrayList<>());
        atmByOfficeIdTable.put(bankOffice.getId(), new ArrayList<>());
        bankService.addOffice(bankOffice.getBank().getId(), bankOffice);

        return newOffice;
    }

    @Override
    public void printBankOfficeData(int id) {
        BankOffice bankOffice = getBankOfficeById(id);
        if (bankOffice == null) {
            return;
        }
        System.out.println(bankOffice);
        List<Employee> employees = getAllEmployeesByOfficeId(id);
        if (employees != null) {
            System.out.println("Сотрудники:");
            employees.forEach(System.out::println);
        }
        List<BankAtm> atms = atmByOfficeIdTable.get(id);
        if (atms != null) {
            System.out.println("Банкоматы:");
            atms.forEach(System.out::println);
        }
    }

    @Override
    public boolean addAtm(int id, BankAtm bankAtm) {
        BankOffice bankOffice = getBankOfficeById(id);
        if (bankOffice != null && bankAtm != null) {
            if (!bankOffice.getIsAtmPlaceable()) {
                System.err.println("Ошибка! Невозможно установить банкомат!");
                return false;
            }

            bankOffice.setAtmCount(bankOffice.getAtmCount() + 1);
            bankOffice.getBank().setAtmCount(bankOffice.getBank().getAtmCount() + 1);
            bankAtm.setBankOffice(bankOffice);
            bankAtm.setAddress(bankOffice.getAddress());
            bankAtm.setBank(bankOffice.getBank());
            List<BankAtm> officeAtms = atmByOfficeIdTable.get(bankOffice.getId());
            officeAtms.add(bankAtm);

            return true;
        }
        return false;
    }

    @Override
    public boolean addEmployee(int id, Employee employee) {
        BankOffice bankOffice = getBankOfficeById(id);
        if (bankOffice != null && employee != null) {
            employee.setBankOffice(bankOffice);
            employee.setBank(bankOffice.getBank());
            List<Employee> officeEmployees = employeByOfficeIdTable.get(bankOffice.getId());
            officeEmployees.add(employee);
            return true;
        }
        return false;
    }
}