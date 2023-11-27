package tech.reliab.course.glazyrinaoa.bank.service.impl;

import java.util.*;

import tech.reliab.course.glazyrinaoa.bank.entity.User;
import tech.reliab.course.glazyrinaoa.bank.entity.PaymentAccount;
import tech.reliab.course.glazyrinaoa.bank.entity.CreditAccount;
import tech.reliab.course.glazyrinaoa.bank.service.UserService;
import tech.reliab.course.glazyrinaoa.bank.service.BankService;



public class UserServiceImpl implements UserService {
    Map<Integer, User> userTable  = new HashMap<Integer, User>();
    Map<Integer, List<PaymentAccount>> paymentAccountsByClientIdTable  = new HashMap<Integer, List<PaymentAccount>>();
    Map<Integer, List<CreditAccount>> creditAccountsByClientIdTable  = new HashMap<Integer, List<CreditAccount>>();
    private final BankService bankService;

    public UserServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public User create(User user) {
        if (user == null) {
            return null;
        }

        if (user.getBank() == null) {
            System.err.println("Ошибка! У пользователя нет банковского счета.");
            return null;
        }

        User createdclient = new User(user);
        final Random random = new Random();
        final double monthlyIncome = random.nextDouble() * (User.MAX_MONTHLY_INCOME);
        createdclient.setMonthlyIncome(monthlyIncome);
        calculateCreditRating(createdclient);
        userTable.put(createdclient.getId(), createdclient);
        paymentAccountsByClientIdTable.put(createdclient.getId(), new ArrayList<>());
        creditAccountsByClientIdTable.put(createdclient.getId(), new ArrayList<>());
        bankService.addUser(user.getBank().getId(), createdclient);

        return createdclient;
    }

    @Override
    public double calculateCreditRating(User user) {
        user.setCreditRating((int)Math.ceil(user.getMonthlyIncome() / 1000) * 100 );
        return user.getCreditRating();
    }

    @Override
    public boolean addCreditAccount(int id, CreditAccount account) {
        User user = userTable.get(id);
        if (user != null) {
            List<CreditAccount> clientCreditAccounts = creditAccountsByClientIdTable.get(id);
            clientCreditAccounts.add(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean addPaymentAccount(int id, PaymentAccount account) {
        User user = userTable.get(id);
        if (user != null) {
            List<PaymentAccount> clientCreditAccounts = paymentAccountsByClientIdTable.get(id);
            clientCreditAccounts.add(account);
            return true;
        }
        return false;
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccountsByClientId(int id) {
        return paymentAccountsByClientIdTable.get(id);
    }

    @Override
    public List<CreditAccount> getAllCreditAccountsByClientId(int id) {
        return creditAccountsByClientIdTable.get(id);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userTable.values());
    }

    @Override
    public User getUserById(int id) {
        User user = userTable.get(id);
        if (user == null) {
            System.err.println("Такого клиента нет.");
        }
        return user;
    }

    @Override
    public void printUserData(int id, boolean withAccounts) {
        User user = getUserById(id);

        if (user == null) {
            return;
        }

        System.out.println(user);
        if (withAccounts) {
            List<PaymentAccount> paymentAccounts = getAllPaymentAccountsByClientId(id);
            if (paymentAccounts != null) {
                System.out.println("Платёжные счета:");
                paymentAccounts.forEach(System.out::println);
            }
            List<CreditAccount> creditAccounts = getAllCreditAccountsByClientId(id);
            if (creditAccounts != null) {
                System.out.println("Кредитные счета:");
                creditAccounts.forEach(System.out::println);
            }
        }

    }

    @Override
    public PaymentAccount getBestPaymentAccount(int id) {
        List<PaymentAccount> paymentAccounts = getAllPaymentAccountsByClientId(id);
        PaymentAccount paymentAccount =  paymentAccounts.stream().min(Comparator.comparing(PaymentAccount::getBalance)).orElseThrow(NoSuchElementException::new);
        return paymentAccount;
    }
}