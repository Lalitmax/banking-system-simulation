package src.main;

import src.bank.Bank;
import src.bank.Message;

import java.util.Scanner;

public class BankImplementation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        System.out.println("Welcome to MangalBank!");

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Create account
                    System.out.print("Enter your name: ");
                    scanner.nextLine(); // Consume newline
                    String name = scanner.nextLine();

                    System.out.print("Enter your account number: ");
                    String accountNo = scanner.nextLine();

                    System.out.print("Enter initial balance: ");
                    int balance = scanner.nextInt();

                    System.out.print("Set your PIN: ");
                    int pin = scanner.nextInt();

                    bank.createAccount(name, accountNo, balance, pin);
                    System.out.println("Account created successfully!");
                    break;

                case 2:
                    // Check balance
                    System.out.print("Enter account number: ");
                    scanner.nextLine();  
                    accountNo = scanner.nextLine();

                    System.out.print("Enter your PIN: ");
                    pin = scanner.nextInt();

                    Message checkBalanceMessage = bank.checkBalance(accountNo, pin);
                    if (checkBalanceMessage.status.equals("Success")) {
                        System.out.println("Your balance is: " + checkBalanceMessage.money);
                    } else {
                        System.out.println("Failed to retrieve balance. Check your account number and PIN.");
                    }
                    break;

                case 3:
                    // Deposit money
                    System.out.print("Enter account number: ");
                    scanner.nextLine(); // Consume newline
                    accountNo = scanner.nextLine();

                    System.out.print("Enter amount to deposit: ");
                    int depositAmount = scanner.nextInt();

                    Message depositMessage = bank.deposit(accountNo, depositAmount);
                    if (depositMessage.status.equals("Success")) {
                        System.out.println("Deposit successful! Your new balance is: " + depositMessage.money);
                    } else {
                        System.out.println("Deposit failed.");
                    }
                    break;

                case 4:
                    // Withdraw money
                    System.out.print("Enter account number: ");
                    scanner.nextLine(); // Consume newline
                    accountNo = scanner.nextLine();

                    System.out.print("Enter your PIN: ");
                    pin = scanner.nextInt();

                    System.out.print("Enter amount to withdraw: ");
                    int withdrawAmount = scanner.nextInt();

                    Message withdrawMessage = bank.withdraw(accountNo, pin, withdrawAmount);
                    if (withdrawMessage.status.equals("Success")) {
                        System.out.println("Withdrawal successful! You withdrew: " + withdrawMessage.money);
                    } else {
                        System.out.println("Withdrawal failed. Check your balance and try again.");
                    }
                    break;

                case 5:
                    // Exit
                    System.out.println("Thank you for using MangalBank. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please choose a valid number.");
            }
        }
    }
}
