package src.bank;

import src.config.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bank {

    private final Connection connection;

    public Bank() {
        this.connection = new Db().getConnection(); // Reuse a single DB connection
        System.out.println("Mangal Bank Initialized!");
    }


    public Message bankDetails(int bank_id) {
        String query = "SELECT total_balance FROM mangalbank WHERE bank_id = ?";

        try {
            PreparedStatement pstmp = connection.prepareStatement(query);
            pstmp.setInt(1, bank_id);
            ResultSet rs = pstmp.executeQuery();

            if (rs.next()) { // Check if a row exists
                double balance = rs.getDouble("total_balance"); // Retrieve as double
                return new Message("Success", balance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Message("Failed", 0.0); // Return 0 if no data found
    }


    //CREATE ACCOUNT (Using PreparedStatement to prevent SQL Injection)
    public void createAccount(String name, String accountNo, double balance, int pin) {
        String query = "INSERT INTO accounts (account_number, name, pin, balance) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, accountNo);
            pstmt.setString(2, name);
            pstmt.setInt(3, pin);
            pstmt.setDouble(4, balance);  // Using double instead of int

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Account created successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DEPOSIT MONEY
    public Message deposit(String accountNo, double money) {
        UserDetails ud = new UserDetails();
        return ud.addBalance(accountNo, money);
    }


    public Message withdraw(String accountNo, int pin, double money) {
        UserDetails ud = new UserDetails();
        return ud.withdrawBalance(accountNo, pin, money);
    }


    public Message checkBalance(String accountNo, int pin) {
        UserDetails ud = new UserDetails();
        return ud.checkBalance(accountNo, pin);
    }


    public static double checkMangalBankBalance() {
        String query = "SELECT SUM(balance) AS total_balance FROM accounts";
        double bankBalance = 0.0;

        try (Connection conn = new Db().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                bankBalance = rs.getDouble("total_balance");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankBalance;
    }
}
