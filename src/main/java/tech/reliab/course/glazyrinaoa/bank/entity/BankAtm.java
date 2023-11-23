package tech.reliab.course.glazyrinaoa.bank.entity;

import tech.reliab.course.glazyrinaoa.bank.utils.AtmStatus;

import java.util.UUID;
public class BankAtm {
    private static int currentId = 0;

    protected int id;
    private String name;
    private String address;
    private AtmStatus status;
    private Bank bank;
    private BankOffice bankOffice;
    private Employee employee;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private double  totalMoney;
    private double  maintenanceCost;

    private void initializeId() {
        this.id = currentId++;
    }

    private void initDefault() {
        name = "No name";
        address = "No address";
        status = AtmStatus.NOT_WORK;
        bank = null;
        bankOffice = null;
        employee = null;
        isCashWithdrawalAvailable = false;
        isCashDepositAvailable = false;
        totalMoney = 0;
        maintenanceCost = 0;
    }

    public BankAtm() {
        initializeId();
        initDefault();
    }

    public BankAtm(String name) {
        initializeId();
        initDefault();
        this.name = name;
    }

    public BankAtm(String name, String address) {
        initializeId();
        initDefault();
        this.name = name;
        this.address = address;
    }

    public BankAtm(String name, Bank bank, BankOffice bankOffice,
                   Employee employee) {
        initializeId();
        initDefault();
        this.name = name;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.employee = employee;
    }

    public BankAtm(String name, String address, AtmStatus status, Bank bank, BankOffice bankOffice,
                   Employee employee, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable, double  totalMoney,
                   double  maintenanceCost) {
        initializeId();
        initDefault();
        this.name = name;
        this.address = address;
        this.status = status;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.employee = employee;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.maintenanceCost = maintenanceCost;
    }

    public BankAtm(int id, String name, String address, AtmStatus status, Bank bank, BankOffice bankOffice,
                   Employee employee, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable, double  totalMoney,
                   double  maintenanceCost) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.status = status;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.employee = employee;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.maintenanceCost = maintenanceCost;
    }

    public BankAtm(BankAtm bankAtm) {
        this.id = bankAtm.id;
        this.name = bankAtm.name;
        this.address = bankAtm.address;
        this.status = bankAtm.status;
        this.bank = new Bank(bankAtm.bank);
        this.bankOffice = new BankOffice(bankAtm.bankOffice);
        this.employee = new Employee(bankAtm.employee);
        this.isCashWithdrawalAvailable = bankAtm.isCashWithdrawalAvailable;
        this.isCashDepositAvailable = bankAtm.isCashDepositAvailable;
        this.totalMoney = bankAtm.totalMoney;
        this.maintenanceCost = bankAtm.maintenanceCost;
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

    public AtmStatus getStatus() {
        return this.status;
    }

    public void setStatus(AtmStatus status) {
        this.status = status;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public BankOffice getBankOffice() {
        return this.bankOffice;
    }

    public void setBankOffice(BankOffice bankOffice) {
        this.bankOffice = bankOffice;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public double  getMaintenanceCost() {
        return this.maintenanceCost;
    }

    public void setMaintenanceCost(double  maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    @Override
    public String toString() {
        return "Банкомат №" + getId() +
                "\n Название = '" + getName() + "'" +
                ",\n Адрес = '" + getAddress() + "'" +
                ",\n Статус состояния = '" + getStatus() + "'" +
                ",\n Банк, которому принадлжеит банокмат = '" + this.bank.getName() + "'" +
                ",\n Расположение банкомата = '" + this.bankOffice.getAddress() + "'" +
                ",\n Обслуживающий сотрудник = '" + this.employee.getName() + "'" +
                ",\n Выдаёт деньги = '" + getIsCashWithdrawalAvailable() + "'" +
                ",\n Принимает деньги = '" + getIsCashDepositAvailable() + "'" +
                ",\n Кол-во денег в банкомате = '" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n Стоимость обслуживания банкомата = '" + String.format("%.2f", getMaintenanceCost())  + "'" + "\n";
    }
}
