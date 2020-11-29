package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static Connection connection;

    public static Connection getConnnection() {
        try {
            if (connection == null) {
                Class.forName("org.postgresql.Driver");
                String path = "jdbc:postgresql:bdd";
                String user = "pi";
                String pwd = "cE6sC1gH";
                connection = DriverManager.getConnection(path, user, pwd);
            }
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
