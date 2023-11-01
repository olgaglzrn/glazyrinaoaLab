package tech.reliab.course.glazyrinaoa.bank.entity;

import java.util.UUID;
public class PaymentAccount extends Account{
    private double  balance;

    private void initDefault() {
        balance = 0;
    }

    public PaymentAccount() {
        super();
        initDefault();
    }

    public PaymentAccount(UUID id, User user) {
        super(id, user);
        initDefault();
    }

    public PaymentAccount(User user, Bank bank) {
        super(user, bank);
        initDefault();
    }

    public PaymentAccount(UUID id, User user, Bank bank) {
        super(id, user, bank);
        initDefault();
    }

    public PaymentAccount(User user, Bank bank, double balance) {
        super(user, bank);
        this.balance = balance;
    }

    public PaymentAccount(UUID id, User user, Bank bank, double balance) {
        super(id, user, bank);
        this.balance = balance;
    }

    public PaymentAccount(PaymentAccount paymentAccount) {
        super(paymentAccount.id, paymentAccount.user, paymentAccount.bank);
        this.balance = paymentAccount.balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double  balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Платёжный " + super.toString() +
                " Баланс = '" + String.format("%.2f", getBalance()) + "'" +
                "\n";
    }
}
