package tech.reliab.course.glazyrinaoa.bank.service.impl;

import java.util.*;

import tech.reliab.course.glazyrinaoa.bank.entity.*;
import tech.reliab.course.glazyrinaoa.bank.service.BankService;

import tech.reliab.course.glazyrinaoa.bank.service.BankOfficeService;
import tech.reliab.course.glazyrinaoa.bank.service.UserService;

public class BankServiceImpl implements BankService {

    Map<Integer, Bank> bankTable = new HashMap<Integer, Bank>();
    Map<Integer, List<BankOffice>> officeByBankIdTable = new HashMap<Integer, List<BankOffice>>();
    Map<Integer, List<User>> userByBankIdTable = new HashMap<Integer, List<User>>();
    private BankOfficeService bankOfficeService;
    private UserService userService;

    @Override
    public void setBankOfficeService(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public void setClientService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Bank create(Bank bank) {
        if (bank == null) {
            return null;
        }

        Bank newBank = new Bank(bank.getId(), bank.getName());

        final Random random = new Random();

        newBank.setRating((byte) random.nextInt((int) (Bank.MAX_RATING + 1)));
        newBank.setTotalMoney(random.nextDouble() * Bank.MAX_TOTAL_MONEY);
        calculateInterestRate(newBank);
        
        bankTable.put(newBank.getId(), newBank);
        officeByBankIdTable.put(newBank.getId(), new ArrayList<>());
        userByBankIdTable.put(newBank.getId(), new ArrayList<>());

        return newBank;
    }

    @Override
    public double calculateInterestRate(Bank bank) {
        if (bank != null) {
            final Random random = new Random();
            final byte rating = bank.getRating();

            final double centralBankInterestRate = random.nextDouble() * bank.MAX_INTEREST_RATE;
            final double maxBankInterestRateMargin = bank.MAX_INTEREST_RATE - centralBankInterestRate;
            final double bankInterestRateMargin = (random.nextDouble() * maxBankInterestRateMargin) * ((double) (110 - rating) / 100);
            final double interestRate = centralBankInterestRate + bankInterestRateMargin;

            bank.setInterestRate(interestRate);
            return interestRate;
        }
        return 0;
    }

    @Override
    public Bank getBankById(int bankId) {
        Bank bank = bankTable.get(bankId);
        if (bank == null) {
            System.err.println("Bank with id " + bankId + " is not found");
        }
        return bank;
    }

    @Override
    public List<Bank> getAllBanks() {
        return new ArrayList<>(bankTable.values());
    }

    @Override
    public List<BankOffice> getAllOfficesByBankId(int id) {
        Bank bank = getBankById(id);
        if (bank != null) {
            List<BankOffice> bankOffices = officeByBankIdTable.get(id);
            return bankOffices;
        }
        return new ArrayList<>();
    }

    @Override
    public void printBankData(int bankId) {
        Bank bank = getBankById(bankId);
        if (bank == null) {
            return;
        }
        System.out.println(bank);

        List<BankOffice> offices = officeByBankIdTable.get(bankId);
        if (offices != null) {
            System.out.println("Офисы:");
            offices.forEach((BankOffice office) -> {
                bankOfficeService.printBankOfficeData(office.getId());
            });
        }
        List<User> clients = userByBankIdTable.get(bankId);
        if (clients != null) {
            System.out.println("Клиенты:");
            clients.forEach((User user) -> {
                userService.printUserData(user.getId(), false);
            });
        }
    }

    @Override
    public boolean addOffice(int bankId, BankOffice bankOffice) {
        Bank bank = getBankById(bankId);
        if (bank != null && bankOffice != null) {
            bankOffice.setBank(bank);
            bank.setOfficeCount(bank.getOfficeCount() + 1);
            bank.setAtmCount(bank.getAtmCount() + bankOffice.getAtmCount());
            List<BankOffice> bankOffices = getAllOfficesByBankId(bankId);
            bankOffices.add(bankOffice);
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(int id, User user) {
        Bank bank = getBankById(id);
        if (bank != null && user != null) {
            user.setBank(bank);
            bank.setClientCount(bank.getClientCount() + 1);
            List<User> clients = userByBankIdTable.get(id);
            clients.add(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean addEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            employee.setBank(bank);
            bank.setEmployeeCount(bank.getEmployeeCount() + 1);
            return true;
        }
        return false;
    }
}