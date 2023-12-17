package tech.reliab.course.glazyrinaoa.bank.service;

import tech.reliab.course.glazyrinaoa.bank.entity.CreditAccount;
import tech.reliab.course.glazyrinaoa.bank.exception.ExportException;
import tech.reliab.course.glazyrinaoa.bank.exception.TransferException;

import java.util.*;

public interface CreditAccountService {
    List<CreditAccount> getAllCreditAccounts();

    CreditAccount getCreditAccountById(int id);

    CreditAccount create(CreditAccount creditAccount);

    boolean importAccountsTxtAndTransferToBank(String fileName, int newBankId)
            throws TransferException;

    boolean exportClientAccountsToTxt(int clientId, int bankId) throws ExportException;
}
