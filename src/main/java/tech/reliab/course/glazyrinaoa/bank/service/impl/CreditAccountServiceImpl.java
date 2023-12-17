package tech.reliab.course.glazyrinaoa.bank.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import tech.reliab.course.glazyrinaoa.bank.entity.Bank;
import tech.reliab.course.glazyrinaoa.bank.entity.CreditAccount;
import tech.reliab.course.glazyrinaoa.bank.entity.User;
import tech.reliab.course.glazyrinaoa.bank.exception.ExportException;
import tech.reliab.course.glazyrinaoa.bank.exception.PaymentException;
import tech.reliab.course.glazyrinaoa.bank.exception.TransferException;
import tech.reliab.course.glazyrinaoa.bank.service.BankService;
import tech.reliab.course.glazyrinaoa.bank.service.CreditAccountService;
import tech.reliab.course.glazyrinaoa.bank.service.UserService;

public class CreditAccountServiceImpl implements CreditAccountService {
    Map<Integer, CreditAccount> creditAccountsTable = new HashMap<Integer, CreditAccount>();
    private final UserService userService;
    private final BankService bankService;

    public CreditAccountServiceImpl(UserService userService, BankService bankService) {
        this.userService = userService;
        this.bankService = bankService;
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

    @Override
    public boolean importAccountsTxtAndTransferToBank(String fileName, int newBankId)
            throws TransferException {
        File file = new File(fileName);
        if (!file.exists())
            throw new TransferException("File not found");

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        JsonReader reader = null;

        try {
            reader = new JsonReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new TransferException(e.toString());
        }

        CreditAccount[] accountsArr = gson.fromJson(reader, CreditAccount[].class);
        List<CreditAccount> accounts = Arrays.asList(accountsArr);

        for (CreditAccount a : accounts) {
            CreditAccount creditAccount = getCreditAccountById(a.getId());
            if (creditAccount.getBank().getId() == newBankId) {
                System.out.println("Account with id: " + creditAccount.getId() + " already belongs to the given bank!");
            } else {
                Bank newBank = bankService.getBankById(newBankId);
                creditAccount.setBank(newBank);
                creditAccount.getPaymentAccount().setBank(newBank);
            }

            User user = userService.getUserById(creditAccount.getClient().getId());
            if (user.getBank().getId() != newBankId)
                bankService.transferClient(user, newBankId);
        }

        return true;
    }

    @Override
    public boolean exportClientAccountsToTxt(int clientId, int bankId) throws ExportException {
        List<CreditAccount> creditAccounts = userService.getAllCreditAccountsByClientId(clientId);

        if (creditAccounts.isEmpty())
            throw new PaymentException("нет платёжного счёта");
        try {
            PrintWriter file = new PrintWriter("output.txt");
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            file.println(gson.toJson(creditAccounts));
            file.close();
            return true;
        } catch (FileNotFoundException e) {
            throw new ExportException(e.toString());
        }
    }
}