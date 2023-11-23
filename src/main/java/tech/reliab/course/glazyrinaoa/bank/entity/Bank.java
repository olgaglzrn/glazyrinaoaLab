package tech.reliab.course.glazyrinaoa.bank.entity;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    public static final double MAX_RATING = 100;
    public static final double MAX_TOTAL_MONEY = 1000000;
    public static final double MAX_INTEREST_RATE = 20;
    private static int currentId = 0;

    protected int id;
    private String name;
    private int officeCount;
    private int atmCount;
    private int employeeCount;
    private int clientCount;
    private byte rating;
    private double totalMoney;
    private double interestRate;

    List<BankOffice> lstOffices = new ArrayList<BankOffice>();

    List<User> lstUsers = new ArrayList<User>();

    private void initializeId() {
        this.id = currentId++;
    }

    private void initDefaults() {
        name = "No name";
        officeCount = 0;
        atmCount = 0;
        employeeCount = 0;
        clientCount = 0;
        rating = 0;
        totalMoney = 0;
        interestRate = 0;
    }

    public Bank() {
        initializeId();
        initDefaults();
    }

    public Bank(String name) {
        initializeId();
        initDefaults();
        this.name = name;
    }

    public Bank(int id, String name) {
        initDefaults();
        this.id = id;
        this.name = name;
    }

    public Bank(String name, int officeCount, int atmCount, int employeeCount, int clientCount,
                byte rating, double  totalMoney, double  interestRate) {
        initializeId();
        initDefaults();
        this.name = name;
        this.officeCount = officeCount;
        this.atmCount = atmCount;
        this.employeeCount = employeeCount;
        this.clientCount = clientCount;
        this.rating = rating;
        this.totalMoney = totalMoney;
        this.interestRate = interestRate;
    }

    public Bank(int id, String name, int officeCount, int atmCount, int employeeCount, int clientCount,
                byte rating, double  totalMoney, double  interestRate) {
        this.id = id;
        this.name = name;
        this.officeCount = officeCount;
        this.atmCount = atmCount;
        this.employeeCount = employeeCount;
        this.clientCount = clientCount;
        this.rating = rating;
        this.totalMoney = totalMoney;
        this.interestRate = interestRate;
    }

    public Bank(Bank bank) {
        this.id = bank.id;
        this.name = bank.name;
        this.officeCount = bank.officeCount;
        this.atmCount = bank.atmCount;
        this.employeeCount = bank.employeeCount;
        this.clientCount = bank.clientCount;
        this.rating = bank.rating;
        this.totalMoney = bank.totalMoney;
        this.interestRate = bank.interestRate;
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

    public int getOfficeCount() {
        return this.officeCount;
    }

    public void setOfficeCount(int officeCount) {
        this.officeCount = officeCount;
    }

    public int getAtmCount() {
        return this.atmCount;
    }

    public void setAtmCount(int atmCount) {
        this.atmCount = atmCount;
    }

    public int getEmployeeCount() {
        return this.employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public int getClientCount() {
        return this.clientCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }

    public byte getRating() {
        return this.rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public double  getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(double  totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double  getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double  interestRate) {
        this.interestRate = interestRate;
    }

    public List<User> getLstUsers() {
        return lstUsers;
    }

    public List<BankOffice> getLstOffices() {
        return lstOffices;
    }

    @Override
    public String toString() {
        return "Банк " + getName() +
                "\n Кол-во офисов = '" + getOfficeCount() + "'" +
                ",\n Кол-во банкоматов = '" + getAtmCount() + "'" +
                ",\n Кол-во сотрудников = '" + getEmployeeCount() + "'" +
                ",\n Кол-во клиентов = '" + getClientCount() + "'" +
                ",\n Рейтинг банка = '" + getRating() + "'" +
                ",\n Всего денег = '" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n Процентная ставка = '" + String.format("%.2f", getInterestRate()) + "'" + "\n";
    }
}
