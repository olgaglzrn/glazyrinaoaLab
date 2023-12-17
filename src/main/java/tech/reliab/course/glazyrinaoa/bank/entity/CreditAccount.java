package tech.reliab.course.glazyrinaoa.bank.entity;

import java.time.LocalDate;
import com.google.gson.annotations.Expose;
public class CreditAccount extends Account {
    @Expose(serialize = true)
    private LocalDate dateStart;
    @Expose(serialize = true)
    private LocalDate dateEnd;
    @Expose(serialize = true)
    private int monthCount;
    @Expose(serialize = true)
    private double creditAmount;
    @Expose(serialize = true)
    private double remainingCreditAmount;
    @Expose(serialize = true)
    private double montlyPayment;
    @Expose(serialize = true)
    private double interestRate;
    @Expose(serialize = true)
    private Employee employee;
    @Expose(serialize = true)
    private PaymentAccount paymentAccount;


    private void initDefault() {
        dateStart = null;
        dateEnd = null;
        monthCount = 0;
        creditAmount = 0;
        remainingCreditAmount = 0;
        montlyPayment = 0;
        interestRate = 0;
        employee = null;
        paymentAccount = null;
    }

    public CreditAccount() {
        super();
        initDefault();
    }

    public CreditAccount(User user) {
        super(user);
        initDefault();
    }

    public CreditAccount(int id, User user) {
        super(id, user);
        initDefault();
    }

    public CreditAccount(User user, Bank bank) {
        super(user, bank);
        initDefault();
    }

    public CreditAccount(int id, User user, Bank bank) {
        super(id, user, bank);
        initDefault();
    }

    public CreditAccount(User user, Bank bank, Employee employee, PaymentAccount paymentAccount) {
        super(user, bank);
        initDefault();
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    public CreditAccount(User user, Bank bank, LocalDate dateStart, LocalDate dateEnd, int monthCount,
                         double creditAmount, double remainingCreditAmount, double montlyPayment,
                         double interestRate, Employee employee, PaymentAccount paymentAccount) {
        super(user, bank);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.monthCount = monthCount;
        this.creditAmount = creditAmount;
        this.remainingCreditAmount = remainingCreditAmount;
        this.montlyPayment = montlyPayment;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    public CreditAccount(int id, User user, Bank bank, LocalDate dateStart, LocalDate dateEnd, int monthCount,
                         double creditAmount, double remainingCreditAmount, double montlyPayment,
                         double interestRate, Employee employee, PaymentAccount paymentAccount) {
        super(id, user, bank);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.monthCount = monthCount;
        this.creditAmount = creditAmount;
        this.remainingCreditAmount = remainingCreditAmount;
        this.montlyPayment = montlyPayment;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    public CreditAccount(CreditAccount creditAccount) {
        super(creditAccount.id, creditAccount.user, creditAccount.bank);
        this.dateStart = creditAccount.dateStart;
        this.dateEnd = creditAccount.dateEnd;
        this.monthCount = creditAccount.monthCount;
        this.creditAmount = creditAccount.creditAmount;
        this.remainingCreditAmount = creditAccount.remainingCreditAmount;
        this.montlyPayment = creditAccount.montlyPayment;
        this.interestRate = creditAccount.interestRate;
        this.employee = new Employee(creditAccount.employee);
        this.paymentAccount = new PaymentAccount(creditAccount.paymentAccount);
    }

    public CreditAccount(User user, Bank bank, LocalDate dateStart, int monthCount,
                         double creditAmount, double montlyPayment, double interestRate,
                         Employee employee, PaymentAccount paymentAccount) {
        super(user, bank);
        this.dateStart = dateStart;
        this.dateEnd = dateStart.plusMonths(monthCount);
        this.monthCount = monthCount;
        this.creditAmount = creditAmount;
        this.remainingCreditAmount = creditAmount;
        this.montlyPayment = montlyPayment;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    public LocalDate getDateStart() {
        return this.dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return this.dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getMonthCount() {
        return this.monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    public double  getCreditAmount() {
        return this.creditAmount;
    }

    public void setCreditAmount(double  creditAmount) {
        this.creditAmount = creditAmount;
    }

    public double  getRemainingCreditAmount() {
        return this.remainingCreditAmount;
    }

    public void setRemainingCreditAmount(double  remainingCreditAmount) {
        this.remainingCreditAmount = remainingCreditAmount;
    }

    public double  getMonthlyPayment() {
        return this.montlyPayment;
    }

    public void setMontlyPayment(double  montlyPayment) {
        this.montlyPayment = montlyPayment;
    }

    public double  getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PaymentAccount getPaymentAccount() {
        return this.paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }


    @Override
    public String toString() {
        return  "Кредитный " + super.toString() +
                " Дата начала кредита = '" + getDateStart() + "'" +
                ",\n Дата окончания кредита = '" + getDateEnd() + "'" +
                ",\n Срок кредита = '" + getMonthCount() + "'" + " мес." +
                ",\n Сумма кредита = '" + String.format("%.2f", getCreditAmount())  + "'" +
                ",\n Оставшаяся сумма кредита = '" + String.format("%.2f", getRemainingCreditAmount())  + "'" +
                ",\n Сумма ежемесячного платежа = '" + String.format("%.2f", getMonthlyPayment())  + "'" +
                ",\n Процентная ставка = '" + String.format("%.2f", getInterestRate())  + "'" +
                ",\n Сотрудник, который выдал кредит = '" + this.employee.getName() + "'" +
                ",\n Платежный счет = '" + this.paymentAccount.getBalance() + "'" + "\n";
    }
}
