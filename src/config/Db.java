package src.config;

import java.sql.*;

public class Db {
    private Connection connection;
    public Statement statement;

    public Db() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Lalit!@#123");
            statement = connection.createStatement();
            System.out.println("Database Connected Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            System.out.println("Database Connection Closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printUserDetails() {
        String query = "SELECT * FROM accounts";
        Db db = new Db();

        try (

             Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Account No | PIN | Balance");

            while (rs.next()) {
                System.out.println(rs.getString("account_number") + " | "
                        + rs.getString("pin") + " | "
                        + rs.getString("balance"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        printUserDetails();
    }
}
