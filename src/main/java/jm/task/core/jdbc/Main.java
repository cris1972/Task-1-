package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        System.out.println("---------------------");
        userService.saveUser("Виктор", "Журенко", (byte) 3);
        userService.saveUser("Александр", "Ветров", (byte) 2);
        userService.saveUser("Иван", "Журенко", (byte) 28);
        userService.saveUser("Ольга", "Михайловская", (byte) 45);
        System.out.println("---------------------");
        userService.getAllUsers();
        System.out.println("---------------------");
        userService.cleanUsersTable();
        System.out.println("---------------------");
        userService.dropUsersTable();
    }
}
