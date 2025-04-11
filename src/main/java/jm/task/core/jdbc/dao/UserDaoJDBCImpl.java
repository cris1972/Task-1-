package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        System.out.println("⏳ createUsersTable() вызван");
        String sql =
                "CREATE TABLE IF NOT EXISTS users (" + "id SERIAL PRIMARY KEY, " + "name VARCHAR(50), " + "lastName VARCHAR(50), " + "age SMALLINT);";

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {

            statement.execute(sql);
            System.out.println("Таблица 'users' создана (если ещё не существовала)");

        } catch (Exception e) {
            System.out.println("Ошибка при создании таблицы:");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE users;";
        try (Connection connDrop = Util.getConnection();
             PreparedStatement psDrop = connDrop.prepareStatement(sql)) {
            psDrop.execute(sql);
            System.out.println("Таблица удалена");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connectionSave = Util.getConnection();
             PreparedStatement psSave = connectionSave.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            psSave.setString(1, name);
            psSave.setString(2, lastName);
            psSave.setByte(3, age);
            psSave.executeUpdate();
            System.out.println("Пользователь " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connDelete = Util.getConnection();
             PreparedStatement psDelete = connDelete.prepareStatement(sql)) {
            psDelete.setLong(1, id);
            psDelete.executeUpdate();
            System.out.println("Пользователь с id = " + id + " удалён из базы данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = Util.getConnection();
             Statement sAllUsers = connection.createStatement();
             ResultSet rsAllUsers = sAllUsers.executeQuery(sql)) {
            while (rsAllUsers.next()) {
                User user = new User();
                user.setId(rsAllUsers.getLong("id"));
                user.setName(rsAllUsers.getString("name"));
                user.setLastName(rsAllUsers.getString("lastName"));
                user.setAge(rsAllUsers.getByte("age"));
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица 'users' очищена");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
