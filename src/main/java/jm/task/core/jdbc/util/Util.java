package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/mydb";
    private static final String USERNAME = "evgen";
    private static final String PASSWORD = "12345";

    public static Connection getConnection() {
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("подключение успешно");
        } catch (SQLException e) {
            System.out.println("неуспешно");
            e.printStackTrace();
        }
        return connection;
        // реализуйте настройку соеденения с БД
    }
}

