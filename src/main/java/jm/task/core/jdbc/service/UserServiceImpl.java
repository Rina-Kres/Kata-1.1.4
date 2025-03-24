package jm.task.core.jdbc.service;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;


public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        userDao.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    @Override
    public void saveUser (String name, String lastName, byte age) {
        userDao.saveUser (name, lastName, age);
        System.out.printf("User  с именем %s добавлен в базу данных%n", name);
    }

    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
        System.out.printf("User  с id %d удален из базы данных%n", id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
        System.out.println("Таблица пользователей очищена");
    }
}
