package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        List<User> users = new ArrayList<>();
        users.add(new User("Иван", "Иванов", (byte) 20));
        users.add(new User("Александр", "Петров", (byte) 45));
        users.add(new User("Сергей", "Сидоров", (byte) 34));
        users.add(new User("Константин", "Смирнов", (byte) 20));
        for (User user : users) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
        }

        boolean isTest = false;

        if (isTest) {
            userService.cleanUsersTable();
            userService.dropUsersTable();
        }

    }
}
