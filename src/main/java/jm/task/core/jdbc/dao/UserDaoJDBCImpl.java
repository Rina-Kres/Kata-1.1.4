package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getConnection();

//    public UserDaoJDBCImpl() throws SQLException {
//
//    }

    public void createUsersTable()  {

        String sql = "CREATE TABLE IF NOT EXISTS users (\n" +
                " ID INT NOT NULL AUTO_INCREMENT, \n" +
                " NAME VARCHAR(255) NOT NULL,\n" +
                " LASTNAME VARCHAR(255) NOT NULL, \n"  +
                " AGE INT NOT NULL,\n" +
                " PRIMARY KEY (ID) \n" +
                " );";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The \"users\" table has been successfully created.");
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            System.out.println("Problem with dropping table.");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO users VALUES (NULL, '" + name + "', '" + lastName + "', " + age + ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(name + lastName + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem with save user.");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Problem with loading users.");
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM users");
        } catch (SQLException e) {
            System.out.println("Problem with cleaning table");
        }
        System.out.println("Данные таблицы 'users' успешно удалены");
    }
}
