package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "k2775G901Td9");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}