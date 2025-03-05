package src.config;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Db {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
              Properties props = new Properties();
              props.load(new FileInputStream(".env"));
              String url = props.getProperty("DB_URL");
              String user = props.getProperty("DB_USER");
              String password = props.getProperty("DB_PASSWORD");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("✅ Database Connected Successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}


