package jm.task.core.jdbc.dao;
import java.sql.SQLException;
import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable() throws SQLException;

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
