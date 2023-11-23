package tech.reliab.course.glazyrinaoa.bank.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class User {
    public static final double MAX_MONTHLY_INCOME = 10000;
    private static int currentId = 0;

    protected int id;
    private String name;
    private LocalDate birthDate;
    private String placeOfWork;
    private double monthlyIncome;
    private Bank bank;
    private double creditRating;

    private ArrayList<CreditAccount> creditAccounts;

    private ArrayList<PaymentAccount> paymentAccounts;

    private void initializeId() {
        this.id = currentId++;
    }

    private void initDefault() {
        name = "No name";
        birthDate = null;
        placeOfWork = "No place of work";
        monthlyIncome = 0;
        bank = null;
        creditRating = 0;
    }

    public ArrayList<CreditAccount> getCreditAccounts() {
        return creditAccounts;
    }

    public ArrayList<PaymentAccount> getPaymentAccounts() {
        return paymentAccounts;
    }

    public User() {

        initializeId();
        initDefault();
    }

    public User(String name) {
        initializeId();
        initDefault();
        this.name = name;
    }

    public User(String name, LocalDate birthDate) {
        initializeId();
        initDefault();
        this.name = name;
        this.birthDate = birthDate;
    }

    public User(String name, Bank bank) {
        initializeId();
        initDefault();
        this.name = name;
        this.bank = bank;
    }

    public User(String name, LocalDate birthDate, String placeOfWork, double monthlyIncome, Bank bank,
                double creditRating) {
        initializeId();
        initDefault();
        this.name = name;
        this.birthDate = birthDate;
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
        this.creditAccounts = new ArrayList<CreditAccount>();
        this.paymentAccounts = new ArrayList<PaymentAccount>();
    }

    public User(int id, String name, LocalDate birthDate, String placeOfWork, double monthlyIncome, Bank bank,
                double creditRating) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
        this.creditAccounts = new ArrayList<CreditAccount>();
        this.paymentAccounts = new ArrayList<PaymentAccount>();
    }

    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.birthDate = user.birthDate;
        this.placeOfWork = user.placeOfWork;
        this.monthlyIncome = user.monthlyIncome;
        this.bank = new Bank(user.bank);
        this.creditRating = user.creditRating;
        this.creditAccounts = new ArrayList<CreditAccount>();
        this.paymentAccounts = new ArrayList<PaymentAccount>();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPlaceOfWork() {
        return this.placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public double getMonthlyIncome() {
        return this.monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public double getCreditRating() {
        return this.creditRating;
    }

    public void setCreditRating(double creditRating) {
        this.creditRating = creditRating;
    }

    @Override
    public String toString() {
        return "Клиент банка " + this.bank.getName()  +
                "\n ФИО = '" + getName() + "'" +
                ",\n Дата рождения = '" + getBirthDate() + "'" +
                ",\n Место работы = '" + getPlaceOfWork() + "'" +
                ",\n Ежемесячный доход = '" + String.format("%.2f", getMonthlyIncome()) + "'" +
                ",\n Кредитный рейтинг для банка = '" + String.format("%.2f", getCreditRating()) + "'" + "\n";
    }
}
