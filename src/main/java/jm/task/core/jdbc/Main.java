package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();

        try {
            userDao.createUsersTable();
            System.out.println("Таблица пользователей создана");

            userDao.saveUser("Name1", "LastName1", (byte) 20);
            System.out.println("User с именем Name1 добавлен в базу данных");
            userDao.saveUser("Name2", "LastName2", (byte) 25);
            System.out.println("User с именем Name2 добавлен в базу данных");
            userDao.saveUser("Name3", "LastName3", (byte) 31);
            System.out.println("User с именем Name3 добавлен в базу данных");
            userDao.saveUser("Name4", "LastName4", (byte) 38);
            System.out.println("User с именем Name4 добавлен в базу данных");

            userDao.removeUserById(1);
            System.out.println("User с id 1 удален из базы данных");

            List<User> users = userDao.getAllUsers();
            System.out.println("Список пользователей:");
            for (User user : users) {
                System.out.println(user);
            }

            userDao.cleanUsersTable();
            System.out.println("Таблица пользователей очищена");

            userDao.dropUsersTable();
            System.out.println("Таблица пользователей удалена");

        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}