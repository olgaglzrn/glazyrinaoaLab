package tech.reliab.course.glazyrinaoa.bank.entity;

import java.time.LocalDate;
import java.util.UUID;
public class User {
    public static final double MAX_MONTHLY_INCOME = 10000;

    private UUID id;
    private String name;
    private LocalDate birthDate;
    private String placeOfWork;
    private double monthlyIncome;
    private Bank bank;
    private double creditRating;


    private void initDefault() {
        id = UUID.randomUUID();
        name = "No name";
        birthDate = null;
        placeOfWork = "No place of work";
        monthlyIncome = 0;
        bank = null;
        creditRating = 0;
    }

    public User() {
        initDefault();
    }

    public User(String name) {
        initDefault();
        this.name = name;
    }

    public User(String name, LocalDate birthDate) {
        initDefault();
        this.name = name;
        this.birthDate = birthDate;
    }

    public User(String name, Bank bank) {
        initDefault();
        this.name = name;
        this.bank = bank;
    }

    public User(String name, Bank bank, LocalDate birthDate) {
        initDefault();
        this.name = name;
        this.bank = bank;
        this.birthDate = birthDate;
    }

    public User(String name, LocalDate birthDate, String placeOfWork, double monthlyIncome, Bank bank,
                double creditRating) {
        initDefault();
        this.name = name;
        this.birthDate = birthDate;
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    public User(UUID id, String name, LocalDate birthDate, String placeOfWork, double monthlyIncome, Bank bank,
                double creditRating) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }



    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.birthDate = user.birthDate;
        this.placeOfWork = user.placeOfWork;
        this.monthlyIncome = user.monthlyIncome;
        this.bank = new Bank(user.bank);
        this.creditRating = user.creditRating;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
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
