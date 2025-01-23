package src.bank;

public class UserDetails {

    @SuppressWarnings("unused")
    private String name;
    private String accountNo;
    private int balance;
    private int pin;

    public UserDetails(String name, String accountNo, int balance, int pin) {
        this.name = name;
        this.accountNo = accountNo;
        this.balance = balance;
        Bank.bankBalance += balance;
        this.pin = pin;
    }

    public Message addBalance(int money) {
        Bank.bankBalance += money;
        this.balance += money;
        return new Message("Success", this.balance);
    }

    public Message withdrawBalance(String accountNo, int pin, int money) {
        if (this.accountNo.equals(accountNo) && this.pin == pin && this.balance >= money && Bank.bankBalance >= money) {
            this.balance -= money;
            Bank.bankBalance -= money;
            return new Message("Success", money);
        }
        return new Message("Failed", 0);
    }

    public Message checkBalance(String accountNo, int pin) {
        if (this.accountNo.equals(accountNo) && this.pin == pin) {
            return new Message("Success", this.balance);
        }
        return new Message("Failed", 0);
    }
}
