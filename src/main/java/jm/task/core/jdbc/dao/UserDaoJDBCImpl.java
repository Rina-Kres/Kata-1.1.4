package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String TABLE_NAME = "users";
    private final Connection connection;

    public UserDaoJDBCImpl() {
        this.connection = Util.getConnection();
    }

    @Override
    public void createUsersTable() {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (" +
                "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, " +
                "last_name VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)", TABLE_NAME);

        executeUpdate(sql, "Problem with creating table");
    }

    @Override
    public void dropUsersTable() {
        executeUpdate("DROP TABLE IF EXISTS " + TABLE_NAME,
                "Problem with dropping table");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = String.format("INSERT INTO %s (name, last_name, age) VALUES ('%s', '%s', %d)",
                TABLE_NAME, name, lastName, age);
        executeUpdate(sql, "Problem with save user");
    }

    @Override
    public void removeUserById(long id) {
        executeUpdate("DELETE FROM " + TABLE_NAME + " WHERE id = " + id,
                "Problem with removing user");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, last_name, age FROM " + TABLE_NAME;

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        executeUpdate("DELETE FROM " + TABLE_NAME,
                "Problem with cleaning table");
    }

    private void executeUpdate(String sql, String errorMessage) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println(errorMessage + ": " + e.getMessage());
        }
    }
}