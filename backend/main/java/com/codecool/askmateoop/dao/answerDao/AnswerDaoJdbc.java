package com.codecool.askmateoop.dao.answerDao;

import com.codecool.askmateoop.controller.dto.answerDTO.NewAnswerDTO;
import com.codecool.askmateoop.controller.dto.answerDTO.UpdateAnswerDTO;
import com.codecool.askmateoop.model.Answer;
import com.codecool.askmateoop.database.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoJdbc implements AnswerDAO {
    private final DatabaseConnection databaseConnection;

    @Autowired
    public AnswerDaoJdbc(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public List<Answer> getAllAnswers() {
        List<Answer> answers = new ArrayList<>();
        String sql = "SELECT id,description, answers.question_id, answers.user_id, created_at " +
                "FROM answers;";
        try (Connection connection = databaseConnection.getConnection(); Statement statement =
                connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                sql);) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                int questionId = resultSet.getInt("question_id");
                int userId = resultSet.getInt("user_id");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                Answer answer = new Answer(id, description, questionId, userId, createdAt);
                answers.add(answer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answers;
    }

    @Override
    public Answer getAnswerById(int id) {
        String sql = "SELECT id, description, question_id, user_id, created_at FROM answers WHERE" +
                " id = ?;";
        try (Connection conn = databaseConnection.getConnection(); PreparedStatement statement =
                conn.prepareStatement(
                sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int answerId = rs.getInt("id");
                String description = rs.getString("description");
                int questionId = rs.getInt("question_id");
                int userId = rs.getInt("user_id");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                return new Answer(answerId, description, questionId, userId, createdAt);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Answer> getAnswersByQuestionId(int questionId) {
        List<Answer> answers = new ArrayList<>();
        String sql = "SELECT id,description, answers.question_id, answers.user_id, created_at " +
                "FROM answers WHERE question_id = ?;";
        try (Connection connection = databaseConnection.getConnection(); Statement statement =
                connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                sql);) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                int qId = resultSet.getInt("question_id");
                int userId = resultSet.getInt("user_id");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                Answer answer = new Answer(id, description, qId, userId, createdAt);
                answers.add(answer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answers;
    }

    @Override
    public int saveAnswer(NewAnswerDTO answer) {
        String sql = "INSERT INTO answers(description, question_id, user_id) VALUES (?,?,?);";
        int id = 0;
        try (Connection conn = databaseConnection.getConnection(); PreparedStatement statement =
                conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, answer.description());
            statement.setInt(2, answer.question_id());
            statement.setInt(3, answer.user_id());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID IS :" + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public void deleteAnswer(int id) {
        String sql = "DELETE FROM answers WHERE id = ?;";
        try (Connection conn = databaseConnection.getConnection(); PreparedStatement statement =
                conn.prepareStatement(
                sql)) {
            statement.setInt(1, id);
            System.out.println("ez az:" + statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAnswer(int id, UpdateAnswerDTO answer) {
        String sql = "UPDATE answers SET description = ? WHERE id = ?;";
        try (Connection conn = databaseConnection.getConnection(); PreparedStatement statement =
                conn.prepareStatement(
                sql)) {
            statement.setString(1, answer.description());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
