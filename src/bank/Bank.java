package src.bank;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    Map<String, UserDetails> details;
    protected static int bankBalance;

    public Bank() {
        details = new HashMap<>();
        bankBalance = 0;
    }

    public void createAccount(String name, String accountNo, int balance, int pin) {
        details.put(accountNo, new UserDetails(name, accountNo, balance, pin));
    }

    public Message deposit(String accountNo, int money) {
        UserDetails userDetails = details.get(accountNo);
        return userDetails.addBalance(money);
    }

    public Message withdraw(String accountNo, int pin, int money) {
        UserDetails userDetails = details.get(accountNo);
        return userDetails.withdrawBalance(accountNo, pin, money);
    }

    public Message checkBalance(String accountNo, int pin) {
        UserDetails userDetails = details.get(accountNo);
        return userDetails.checkBalance(accountNo, pin);
    }

    public static int checkMangalBankBalance() {
        return bankBalance;
    }
}
