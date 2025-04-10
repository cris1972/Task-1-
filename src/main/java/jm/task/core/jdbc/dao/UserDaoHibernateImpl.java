package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age SMALLINT)";
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate(); //!!!!!!!!
            transaction.commit();
            System.out.println("Таблица 'users' создана (Hibernate)");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Ошибка при создании таблицы (Hibernate)");
            e.printStackTrace();
        }
    }


    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица 'users' удалена (Hibernate)");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Ошибка при удалении таблицы (Hibernate)");
            e.printStackTrace();
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            if (user != null) {
                session.remove(user);
            }
            transaction.commit();
            System.out.println("Пользователь с id = " + id + " удалён (Hibernate)");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Ошибка при удалении пользователя (Hibernate)");
            e.printStackTrace();
        }
    }


    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            List<User> users = session.createQuery("FROM User", User.class).getResultList();
            System.out.println("Получено пользователей: " + users.size());
            return users;
        } catch (Exception e) {
            System.out.println("Ошибка при получении пользователей (Hibernate)");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица 'users' очищена (Hibernate)");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Ошибка при очистке таблицы (Hibernate)");
            e.printStackTrace();
        }
    }
}

