package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {
    public static void main(String[] args) {
            // реализуйте алгоритм здесь
            UserServiceImpl userService = new UserServiceImpl();
            userService.createUsersTable();
            userService.saveUser("Иван", "Иванов", (byte) 25);
            userService.saveUser("Катя", "Рожкова", (byte) 30);
            userService.saveUser("Петр", "Петров", (byte) 28);
            userService.saveUser("Юля", "Мельникова", (byte) 20);

            userService.cleanUsersTable();
            userService.dropUsersTable();

        }

        }

