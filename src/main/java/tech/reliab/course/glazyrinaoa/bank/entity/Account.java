package tech.reliab.course.glazyrinaoa.bank.entity;
import java.util.UUID;

public class Account {
    protected UUID id;
    protected User user;
    protected Bank bank;

    private void initDefault() {
        id = UUID.randomUUID();
        user = null;
        bank = null;
    }

    public Account() {
        initDefault();
    }

    public Account(User user) {
        this.user = user;
    }

    public Account(UUID id, User user) {
        this.id = id;
        this.user = user;
    }

    public Account(UUID id, User user, Bank bank) {
        this.id = id;
        this.user = user;
        this.bank = bank;
    }

    public Account(User user, Bank bank) {
        initDefault();
        this.user = user;
        this.bank = bank;
    }

    public Account(Account account) {
        this.id = UUID.fromString(account.id.toString());
        this.user = new User(account.user);
        this.bank = new Bank(account.bank);
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getClient() {
        return this.user;
    }

    public void setUser(User client) {
        this.user = client;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "аккаунт" +
                "\n id аккаунта = '" + getId() + "'" +
                ",\n Имя клиента = '" + this.user.getName() + "'" +
                ",\n Банк = '" + this.bank.getName()  + "'" + ",\n";
    }
}
