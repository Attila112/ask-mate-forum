package com.codecool.askmateoop.dao.userDao;

import com.codecool.askmateoop.controller.dto.userDTO.NewUserDTO;
import com.codecool.askmateoop.model.User;
import com.codecool.askmateoop.database.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDAO {
    private DatabaseConnection databaseConnection;

    @Autowired
    public UserDaoJdbc(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String sql = "select id, user_name, user_password, created_at from users;";
        try (Connection connection = databaseConnection.getConnection(); Statement statement =
                connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                sql);) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                String password = resultSet.getString("user_password");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                User user = new User(id, userName, password, createdAt);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT id, user_name, user_password, created_at from users where id = ?;";
        try (Connection connection = databaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(
                sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                String password = resultSet.getString("user_password");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                User user = new User(userId, userName, password, createdAt);
                return user;
            }
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int saveUser(NewUserDTO user) {
        int id = 0;
        String sql = "INSERT INTO users (user_name, user_password) VALUES (?, ?);";
        try (Connection connection = databaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.user_name());
            statement.setString(2, user.user_password());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public void updateUser(int id, NewUserDTO user) {
        String sql = "UPDATE users SET user_name = ?, user_password = ? WHERE id = ?;";
        try (Connection connection = databaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(
                sql)) {
            statement.setString(1, user.user_name());
            statement.setString(2, user.user_password());
            statement.setInt(3, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?;";
        try (Connection connection = databaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(
                sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserToLogin(String name, String password) {
        String sql = "SELECT * FROM users WHERE user_name = ? AND user_password = ?;";
        try (Connection connection = databaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(
                sql)) {
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                String userPassword = resultSet.getString("user_password");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                return new User(userId, userName, password, createdAt);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
