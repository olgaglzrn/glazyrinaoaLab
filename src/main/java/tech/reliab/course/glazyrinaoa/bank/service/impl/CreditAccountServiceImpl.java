package tech.reliab.course.glazyrinaoa.bank.service.impl;

import java.util.*;

import tech.reliab.course.glazyrinaoa.bank.entity.CreditAccount;
import tech.reliab.course.glazyrinaoa.bank.exception.PaymentException;
import tech.reliab.course.glazyrinaoa.bank.service.CreditAccountService;
import tech.reliab.course.glazyrinaoa.bank.service.UserService;

public class CreditAccountServiceImpl implements CreditAccountService {
    Map<Integer, CreditAccount> creditAccountsTable = new HashMap<Integer, CreditAccount>();
    private final UserService userService;

    public CreditAccountServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<CreditAccount> getAllCreditAccounts() {
        return new ArrayList<CreditAccount>(creditAccountsTable.values());
    }

    @Override
    public CreditAccount getCreditAccountById(int id) {
        CreditAccount account = creditAccountsTable.get(id);
        if (account == null) {
            System.err.println("Такой кредитный аккаунт отсутствует");
        }
        return account;
    }

    public boolean makeMonthlyPayment(CreditAccount account) throws PaymentException {
        if (account == null || account.getPaymentAccount() == null) {
            System.out.println("Отсутсвует счет, с которого можно снять деньги!");
            return false;
        }

        final double monthlyPayment = account.getMonthlyPayment();
        final double paymentAccountBalance = account.getPaymentAccount().getBalance();

        if (paymentAccountBalance < monthlyPayment) {
            throw new PaymentException("недостаточно средств.");
        }

        account.getPaymentAccount().setBalance(paymentAccountBalance - monthlyPayment);
        account.setRemainingCreditAmount(account.getRemainingCreditAmount() - monthlyPayment);

        return true;
    }

    @Override
    public CreditAccount create(CreditAccount creditAccount) {
        if (creditAccount == null) {
            return null;
        }

        CreditAccount newAccount = new CreditAccount(creditAccount);
        creditAccountsTable.put(newAccount.getId(), newAccount);
        userService.addCreditAccount(newAccount.getClient().getId(), newAccount);

        return newAccount;
    }
}