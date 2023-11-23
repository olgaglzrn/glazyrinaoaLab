package tech.reliab.course.glazyrinaoa.bank.entity;

import java.util.ArrayList;
import java.util.List;

public class BankOffice {
    private static int currentId = 0;

    protected int id;
    private String name;
    private String address;
    private Bank bank;
    private boolean isWorking;
    private boolean isAtmPlaceable;
    private int atmCount;
    private boolean isCreditAvailable;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private double totalMoney;
    private double rentPrice;

    List<Employee> lstEmployees = new ArrayList<Employee>();

    List<BankAtm> lstBankAtm = new ArrayList<BankAtm>();

    private void initializeId() {
        this.id = currentId++;
    }

    private void initWithDefaults() {
        name = "No name";
        address = "No address";
        bank = null;
        isWorking = false;
        isAtmPlaceable = false;
        atmCount = 0;
        isCreditAvailable = false;
        isCashWithdrawalAvailable = false;
        isCashDepositAvailable = false;
        totalMoney = 0;
        rentPrice = 0;
    }

    public List<BankAtm> getLstBankAtm() {
        return lstBankAtm;
    }

    public List<Employee> getLstEmployees() {
        return lstEmployees;
    }

    public BankOffice() {
        initializeId();
        initWithDefaults();
    }

    public BankOffice(String name) {
        initializeId();
        initWithDefaults();
        this.name = name;
    }

    public BankOffice(String name, String address) {
        initializeId();
        initWithDefaults();
        this.name = name;
        this.address = address;
    }

    public BankOffice(String name, String address, Bank bank) {
        initializeId();
        initWithDefaults();
        this.name = name;
        this.address = address;
        this.bank = bank;
    }

    public BankOffice(String name, String address, Bank bank, boolean isWorking, boolean isAtmPlaceable,
                      int atmCount, boolean isCreditAvailable, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable,
                      double  totalMoney, double  rentPrice) {
        initializeId();
        initWithDefaults();
        this.name = name;
        this.address = address;
        this.bank = bank;
        this.isWorking = isWorking;
        this.isAtmPlaceable = isAtmPlaceable;
        this.atmCount = atmCount;
        this.isCreditAvailable = isCreditAvailable;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.rentPrice = rentPrice;
    }

    public BankOffice(int id, String name, String address, Bank bank, boolean isWorking, boolean isAtmPlaceable,
                      int atmCount, boolean isCreditAvailable, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable,
                      double  totalMoney, double  rentPrice) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.bank = bank;
        this.isWorking = isWorking;
        this.isAtmPlaceable = isAtmPlaceable;
        this.atmCount = atmCount;
        this.isCreditAvailable = isCreditAvailable;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.rentPrice = rentPrice;
    }

    public BankOffice(BankOffice bankOffice) {
        this.id = bankOffice.id;
        this.name = bankOffice.name;
        this.address = bankOffice.address;
        this.bank = new Bank(bankOffice.bank);
        this.isWorking = bankOffice.isWorking;
        this.isAtmPlaceable = bankOffice.isAtmPlaceable;
        this.atmCount = bankOffice.atmCount;
        this.isCreditAvailable = bankOffice.isCreditAvailable;
        this.isCashWithdrawalAvailable = bankOffice.isCashWithdrawalAvailable;
        this.isCashDepositAvailable = bankOffice.isCashDepositAvailable;
        this.totalMoney = bankOffice.totalMoney;
        this.rentPrice = bankOffice.rentPrice;
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public boolean isIsWorking() {
        return this.isWorking;
    }

    public boolean getIsWorking() {
        return this.isWorking;
    }

    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public boolean isIsAtmPlaceable() {
        return this.isAtmPlaceable;
    }

    public boolean getIsAtmPlaceable() {
        return this.isAtmPlaceable;
    }

    public void setIsAtmPlaceable(boolean isAtmPlaceable) {
        this.isAtmPlaceable = isAtmPlaceable;
    }

    public int getAtmCount() {
        return this.atmCount;
    }

    public void setAtmCount(int atmCount) {
        this.atmCount = atmCount;
    }

    public boolean isIsCreditAvailable() {
        return this.isCreditAvailable;
    }

    public boolean getIsCreditAvailable() {
        return this.isCreditAvailable;
    }

    public void setIsCreditAvailable(boolean isCreditAvailable) {
        this.isCreditAvailable = isCreditAvailable;
    }

    public boolean isIsCashWithdrawalAvailable() {
        return this.isCashWithdrawalAvailable;
    }

    public boolean getIsCashWithdrawalAvailable() {
        return this.isCashWithdrawalAvailable;
    }

    public void setIsCashWithdrawalAvailable(boolean isCashWithdrawalAvailable) {
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
    }

    public boolean isIsCashDepositAvailable() {
        return this.isCashDepositAvailable;
    }

    public boolean getIsCashDepositAvailable() {
        return this.isCashDepositAvailable;
    }

    public void setIsCashDepositAvailable(boolean isCashDepositAvailable) {
        this.isCashDepositAvailable = isCashDepositAvailable;
    }

    public double  getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(double  totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double  getRentPrice() {
        return this.rentPrice;
    }

    public void setRentPrice(double  rentPrice) {
        this.rentPrice = rentPrice;
    }

    @Override
    public String toString() {
        return  "Банковский офис №" + getId()+
                "\n Название офиса = '" + getName() + "'" +
                ",\n Адрес = '" + getAddress() + "'" +
                ",\n Банк = '" + this.bank.getName() + "'" +
                ",\n Статус работы = '" + getIsWorking() + "'" +
                ",\n Можно ли разместить банкомат = '" + getIsAtmPlaceable() + "'" +
                ",\n Кол-во банкоматов в данном офисе = '" + getAtmCount() + "'" +
                ",\n Можно ли оформить кредит в данном офисе ? '" + getIsCreditAvailable() + "'" +
                ",\n Работает ли на выдачу денег ? '" + getIsCashWithdrawalAvailable() + "'" +
                ",\n Можно ли внести деньги ? '" + getIsCashDepositAvailable() + "'" +
                ",\n Кол-во денег в банковском офисе = '" + getTotalMoney() + "'" +
                ",\n Стоимость аренды банковского офиса = '" +  String.format("%.2f", getRentPrice()) + "'" + "\n";
    }
}
