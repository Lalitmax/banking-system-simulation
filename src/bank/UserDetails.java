package src.bank;

import src.config.Db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDetails {
    private final Connection connection;

    public UserDetails() {
        this.connection = new Db().getConnection(); // Use a single DB connection
    }

    public Message addBalance(String accountNo, double money) {
        String updateQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
        String selectQuery = "SELECT balance FROM accounts WHERE account_number = ?";

        try {
            connection.setAutoCommit(false); // Begin Transaction

            PreparedStatement pstmt = connection.prepareStatement(updateQuery);
            pstmt.setDouble(1, money);
            pstmt.setString(2, accountNo);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Fetch updated balance
                PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                selectStmt.setString(1, accountNo);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    int updatedBalance = rs.getInt("balance");
                    connection.commit(); // Commit Transaction
                    return new Message("Success", updatedBalance);
                }
            }
            connection.rollback(); // Rollback if update fails
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Message("Failed", 0);
    }

    public Message withdrawBalance(String accountNo, int pin, double money) {
        String updateQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ? AND pin = ? AND balance >= ?";
        String selectQuery = "SELECT balance FROM accounts WHERE account_number = ?";

        try {
            connection.setAutoCommit(false); // Begin Transaction

            PreparedStatement pstmt = connection.prepareStatement(updateQuery);
            pstmt.setDouble(1, money);
            pstmt.setString(2, accountNo);
            pstmt.setInt(3, pin);
            pstmt.setDouble(4, money);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Fetch updated balance
                PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                selectStmt.setString(1, accountNo);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    int updatedBalance = rs.getInt("balance");
                    connection.commit(); // Commit Transaction
                    return new Message("Success", updatedBalance);
                }
            }
            connection.rollback(); // Rollback if update fails
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Message("Failed", 0);
    }

    public Message checkBalance(String accountNo, int pin) {
        String query = "SELECT balance FROM accounts WHERE account_number = ? AND pin = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, accountNo);
            pstmt.setInt(2, pin);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Message("Success", rs.getInt("balance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Message("Failed", 0);
    }
}
