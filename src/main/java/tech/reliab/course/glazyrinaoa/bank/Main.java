package tech.reliab.course.glazyrinaoa.bank;

import java.time.LocalDate;
import java.util.*;

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
import tech.reliab.course.glazyrinaoa.bank.utils.AtmStatus;
import tech.reliab.course.glazyrinaoa.bank.utils.Const;
import tech.reliab.course.glazyrinaoa.bank.utils.JobStatus;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        BankService bankService = new BankServiceImpl();
        BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankService);
        bankService.setBankOfficeService(bankOfficeService);
        EmployeeService employeeService = new EmployeeServiceImpl(bankOfficeService);
        bankOfficeService.setEmployeeService(employeeService);
        AtmService atmService = new AtmServiceImpl(bankOfficeService);
        bankOfficeService.setAtmService(atmService);
        bankService.setBankOfficeService(bankOfficeService);
        UserService userService = new UserServiceImpl(bankService);
        bankService.setClientService(userService);
        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(userService);
        CreditAccountService creditAccountService = new CreditAccountServiceImpl(userService);


        for (int i = 0; i < 5; i++) {
            bankService.create(new Bank(Const.BankNames.get(i)));
        }

        List<Bank> banks = bankService.getAllBanks();
        for (Bank bank : banks) {
            for (int i = 1; i <= 3; i++) {
                bankOfficeService.create(new BankOffice("Офис №" + i + " банка: " + bank.getName(), "г.Белгород, Гражданский проспект, " + String.valueOf(i + random.nextInt(0, 50)), bank, true, true, 0, true, true, true, 0, 100 * i));
            }
        }

        List<BankOffice> offices = bankOfficeService.getAllOffices();
        for (BankOffice office : offices) {
            for (int i = 1; i <= 5; i++) {
                employeeService.create(new Employee(Const.PeopleNames.get(random.nextInt(Const.PeopleNames.size())), LocalDate.of(random.nextInt(1980, 2000), random.nextInt(1, 13), random.nextInt(1, 29)), JobStatus.Manager, office.getBank(), true, office, true, 10000 * random.nextDouble()));
            }
        }

        for (BankOffice office : offices) {
            for (int i = 1; i <= 3; i++) {
                atmService.create(new BankAtm("Банкомат № " + i, office.getAddress(), AtmStatus.WORK, office.getBank(), office, bankOfficeService.getAllEmployeesByOfficeId(office.getId()).get(random.nextInt(bankOfficeService.getAllEmployeesByOfficeId(office.getId()).size())), true, true, 15000, random.nextDouble() * 25));
            }
        }

        for (Bank bank : banks) {
            for (int i = 1; i <= 5; i++) {
                userService.create(new User(Const.PeopleNames.get(random.nextInt(Const.PeopleNames.size())), LocalDate.of(random.nextInt(1970, 2003), random.nextInt(1, 13), random.nextInt(1, 29)), Const.CompanyNames.get(random.nextInt(Const.CompanyNames.size())), random.nextDouble() * 10000, bank, random.nextInt(100000)));
            }
        }

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            for (int i = 1; i <= 3; i++) {
                paymentAccountService.create(new PaymentAccount(user, user.getBank(), random.nextDouble() * 10000));
            }
        }

        for (User user : users) {
            for (int i = 1; i <= 2; i++) {
                List<BankOffice> bankOffices = bankService.getAllOfficesByBankId(user.getBank().getId());
                BankOffice randomOffice = bankOffices.get(random.nextInt(bankOffices.size()));
                List<Employee> officeEmployees = bankOfficeService.getAllEmployeesByOfficeId(randomOffice.getId());
                Employee randomEmployee = officeEmployees.get(random.nextInt(officeEmployees.size()));
                creditAccountService.create(new CreditAccount(user, user.getBank(), LocalDate.of(random.nextInt(2023, 2027), random.nextInt(1, 13), random.nextInt(1, 29)), LocalDate.of(2028, 12, 13), 60, random.nextInt(10000, 1000000), random.nextInt(10000, 1000000), random.nextInt(100, 100000), user.getBank().getInterestRate(), randomEmployee, userService.getAllPaymentAccountsByClientId(user.getId()).get(random.nextInt(userService.getAllPaymentAccountsByClientId(user.getId()).size()))));
            }
        }

        while (true) {
            System.out.print("Выберите, что хотите сделать:\n");
            System.out.print("Получить информацию по банку введите: Банк \n");
            System.out.print("Получить всю информацию о клиенте введите: Клиент \n");
            System.out.print("Для получения кредита введите: Кредит \n");

            Scanner in = new Scanner(System.in);
            String command = in.next();
            switch (command) {
                case ("Банк"):
                    for (Bank bank : bankService.getAllBanks()) {
                        System.out.println(bank.getId() + " - " + bank.getName());
                    }
                    System.out.println("Какой банк вас интересует? (введите номер):");
                    int bankId2 = in.nextInt();
                    bankService.printBankData(bankId2);
                    break;
                case ("Клиент"):
                    for (User user : userService.getAllUsers()) {
                        System.out.println(user.getId() + " - " + user.getName());
                    }
                    System.out.println("Какой клиент вас интересует? (введите номер):");
                    int userId = in.nextInt();
                    userService.printUserData(userId, true);
                    break;
                case ("Кредит"):
                    for (User user : userService.getAllUsers()) {
                        System.out.println(user.getId() + " - " + user.getName());
                    }
                    System.out.println("Введите номер заёмщика:");
                    int clientId = in.nextInt();
                    in.nextLine();
                    System.out.println("Введите сумму кредита:");
                    double amount = Double.parseDouble(in.nextLine());
                    System.out.println("Введите срок кредита(в месяцах)");
                    int months = in.nextInt();
                    in.nextLine();

                    List<Bank> suitableBanks = bankService.getBanksSuitable(amount, months);
                    System.out.println("Список подходящих банков:");
                    for (Bank bank : suitableBanks) {
                        System.out.println(bank.getId() + " - " + bank.getName());
                    }
                    int bankId = in.nextInt();
                    in.nextLine();
                    Bank bank = bankService.getBankById(bankId);
                    BankOffice bankOffice = bankService.getBankOfficeSuitableInBank(bank, amount)
                            .get(0);
                    Employee employee = bankOfficeService.getSuitableEmployeeInOffice(bankOffice)
                            .get(0);
                    PaymentAccount paymentAccount;
                    paymentAccount = userService.getBestPaymentAccount(clientId);
                    CreditAccount creditAccount = creditAccountService.create(new CreditAccount(
                            userService.getUserById(clientId), bank, LocalDate.now(), months, amount, 0, 0, employee, paymentAccount));
                    if (bankService.approveCredit(bank, creditAccount, employee)) {
                        System.out.println("Кредит одобрен " + creditAccount.getId());
                    } else {
                        System.out.println("Кредит не одобрен");
                    }
                    break;
                default:
                    System.out.print("Некорректный ввод!\n");
                    break;
            }
        }
    }
}