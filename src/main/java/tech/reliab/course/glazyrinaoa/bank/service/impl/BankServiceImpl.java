package tech.reliab.course.glazyrinaoa.bank.service.impl;

import java.time.LocalDate;
import java.util.*;

import tech.reliab.course.glazyrinaoa.bank.entity.*;
import tech.reliab.course.glazyrinaoa.bank.exception.CreditException;
import tech.reliab.course.glazyrinaoa.bank.exception.IncomException;
import tech.reliab.course.glazyrinaoa.bank.exception.RatingException;
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
            System.err.println("Банка с id = " + bankId + " нет");
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
            bank.setTotalMoney(bank.getTotalMoney() + bankOffice.getTotalMoney());
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

    @Override
    public List<BankOffice> getBankOfficeSuitableInBank(Bank bank, double money) {
        List<BankOffice> bankOfficesByBank = getAllOfficesByBankId(bank.getId());
        List<BankOffice> suitableBankOffice = new ArrayList<>();
        for (BankOffice bankOffice : bankOfficesByBank) {
            if (bankOfficeService.isSuitableBankOffice(bankOffice, money)) {
                suitableBankOffice.add(bankOffice);
            }
        }
        return suitableBankOffice;
    }

    @Override
    public boolean isBankSuitable(Bank bank, double money) {
        List<BankOffice> bankOfficeSuitable = getBankOfficeSuitableInBank(bank, money);
        return !bankOfficeSuitable.isEmpty();
    }

    @Override
    public List<Bank> getBanksSuitable(double sum, int countMonth) throws CreditException {
        List<Bank> banksSuitable = new ArrayList<>();
        for (Bank bank : bankTable.values()) {
            if (isBankSuitable(bank, sum)) {
                banksSuitable.add(bank);
            }
        }
        if (banksSuitable.isEmpty()) {
            throw new CreditException("подходящие банки отсутствуют.");
        }
        return banksSuitable;
    }

    @Override
    public boolean approveCredit(Bank bank, CreditAccount account, Employee employee) throws CreditException, RatingException, IncomException {
        if (account != null && bank != null && employee != null) {
            double sum = account.getCreditAmount();

            if (bank.getTotalMoney() >= sum && employee.getIsCreditAvailable()) {
                double bankInterestRateMultiplier = 1 + (bank.getInterestRate() / 100);
                double sumMonthPay = sum * bankInterestRateMultiplier / account.getMonthCount();

                if (account.getClient().getMonthlyIncome() >= sumMonthPay) {
                    if (account.getClient().getCreditRating() < 5000 && bank.getRating() > 50) {
                        throw new RatingException("неподходящие рейтинг.");
                    }

                    account.setEmployee(employee);
                    account.setMontlyPayment(sumMonthPay);
                    account.setBank(bank);
                    account.setInterestRate(bank.getInterestRate());

                    LocalDate dateEnd = account.getDateStart();
                    dateEnd = dateEnd.plusMonths(account.getMonthCount());
                    account.setDateEnd(dateEnd);

                    return true;
                } else {
                    throw new IncomException("неподходящий доход.");
                }
            }
        }

        return false;
    }

    @Override
    public boolean transferClient(User user, int newBankId) {
        Bank curBank = getBankById(user.getBank().getId());
        Bank newBank = getBankById(newBankId);
        if (newBank != null && curBank != null && user != null) {
            List<User> users = userByBankIdTable.get(user.getBank().getId());
            users.remove(user);
            curBank.removeUser(user);

            user.setBank(newBank);
            users.add(user);
            newBank.addUser(user);
            return true;
        }
        return false;
    }
}