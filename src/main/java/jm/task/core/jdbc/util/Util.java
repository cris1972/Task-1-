package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    // реализуйте настройку соеденения с БД
    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String user = "postgres";
    private static String password = "zadedi92";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Подключение к postgreSQL");
            } else {
                System.out.println("Ошибка подключения к postgreSQL");
            }
            return connection;
        }
    }
