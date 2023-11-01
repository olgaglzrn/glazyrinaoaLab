package tech.reliab.course.glazyrinaoa.bank.service.impl;

import java.math.BigDecimal;
import java.util.Random;

import tech.reliab.course.glazyrinaoa.bank.entity.Bank;
import tech.reliab.course.glazyrinaoa.bank.service.BankService;


public class BankServiceImpl implements BankService {

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
}