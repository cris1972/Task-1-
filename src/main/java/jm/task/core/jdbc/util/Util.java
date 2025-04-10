package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

    public static final SessionFactory sessionFactory = bildSessionFactory();
    private static SessionFactory bildSessionFactory() {
        try {
            return new Configuration()
                    .addAnnotatedClass(User.class)
                    .setProperty("hibernate.connection.url", URL)
                    .setProperty("hibernate.connection.username", USERNAME)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.hbm2ddl.auto", "none")
                    .buildSessionFactory();
            } catch (Exception e) {
            System.out.println("неуспешно");
            throw new ExceptionInInitializerError(e);
        }
        }
        public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}


