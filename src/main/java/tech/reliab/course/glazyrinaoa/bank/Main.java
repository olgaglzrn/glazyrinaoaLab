package tech.reliab.course.glazyrinaoa.bank;

import java.math.BigDecimal;
import java.time.LocalDate;

import tech.reliab.course.glazyrinaoa.bank.entity.Bank;
import tech.reliab.course.glazyrinaoa.bank.entity.BankAtm;
import tech.reliab.course.glazyrinaoa.bank.entity.BankOffice;
import tech.reliab.course.glazyrinaoa.bank.entity.User;
import tech.reliab.course.glazyrinaoa.bank.entity.CreditAccount;
import tech.reliab.course.glazyrinaoa.bank.entity.Employee;
import tech.reliab.course.glazyrinaoa.bank.entity.PaymentAccount;
import tech.reliab.course.glazyrinaoa.bank.service.AtmService;
import tech.reliab.course.glazyrinaoa.bank.service.BankOfficeService;
import tech.reliab.course.glazyrinaoa.bank.service.BankService;
import tech.reliab.course.glazyrinaoa.bank.service.CreditAccountService;
import tech.reliab.course.glazyrinaoa.bank.service.EmployeeService;
import tech.reliab.course.glazyrinaoa.bank.service.UserService;
import tech.reliab.course.glazyrinaoa.bank.service.PaymentAccountService;
import tech.reliab.course.glazyrinaoa.bank.service.impl.AtmServiceImpl;
import tech.reliab.course.glazyrinaoa.bank.service.impl.BankOfficeServiceImpl;
import tech.reliab.course.glazyrinaoa.bank.service.impl.BankServiceImpl;
import tech.reliab.course.glazyrinaoa.bank.service.impl.CreditAccountServiceImpl;
import tech.reliab.course.glazyrinaoa.bank.service.impl.EmployeeServiceImpl;
import tech.reliab.course.glazyrinaoa.bank.service.impl.PaymentAccountServiceImpl;
import tech.reliab.course.glazyrinaoa.bank.service.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankServiceImpl();
        Bank bank = bankService.create(new Bank("ВТБ"));
        System.out.println(bank);

        BankOfficeService bankOfficeService = new BankOfficeServiceImpl();
        BankOffice bankOffice = bankOfficeService.create(new BankOffice(
                "Офис №1",
                "Проспект Славы, 35а",
                bank
        ));
        System.out.println(bankOffice);

        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = employeeService
                .create(new Employee("Иванов Иван Иванович",
                        bank,
                        bankOffice,LocalDate.of(1999,11,7), Employee.Job.Worker
                ));
        System.out.println(employee);

        AtmService atmService = new AtmServiceImpl();
        BankAtm bankAtm = atmService.create(new BankAtm("Банкомат офиса №1",
                bank,
                bankOffice,
                employee
        ));
        System.out.println(bankAtm);

        UserService userService = new UserServiceImpl();
        User user = userService
                .create(new User("Солнышко Артем Евгеньевич",
                        bank, LocalDate.of(2002,2,1)
                ));
        System.out.println(user);

        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl();
        PaymentAccount paymentAccount = paymentAccountService
                .create(new PaymentAccount(user, bank, 99999));
        System.out.println(paymentAccount);

        CreditAccountService creditAccountService = new CreditAccountServiceImpl();
        CreditAccount creditAccount = creditAccountService.create(new CreditAccount(user, bank, LocalDate.of(2023,4,23), LocalDate.of(2033,4,23), employee, paymentAccount));
        System.out.println(creditAccount);
    }
}