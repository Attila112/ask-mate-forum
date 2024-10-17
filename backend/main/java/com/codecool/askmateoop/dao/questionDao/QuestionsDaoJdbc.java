package com.codecool.askmateoop.dao.questionDao;

import com.codecool.askmateoop.controller.dto.questionDTO.EditQuestionDTO;
import com.codecool.askmateoop.controller.dto.questionDTO.NewQuestionDTO;
import com.codecool.askmateoop.model.Question;
import com.codecool.askmateoop.database.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoJdbc implements QuestionsDAO {
    private final DatabaseConnection databaseConnection;

    @Autowired
    public QuestionsDaoJdbc(DatabaseConnection connection) {
        this.databaseConnection = connection;
    }

    @Override
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String sql = "Select id,title,description,created_at FROM questions;";
        try (Connection connection = databaseConnection.getConnection(); Statement statement =
                connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                sql);) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                Question question = new Question(id, title, description, createdAt);
                questions.add(question);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return questions;

    }

    @Override
    public Question getQuestionById(int id) {
        String sql = "SELECT id,title,description,created_at  FROM questions WHERE id = ?;";
        try (Connection conn = databaseConnection.getConnection(); PreparedStatement statement =
                conn.prepareStatement(
                sql);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int newId = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                return new Question(newId, title, description, createdAt);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int saveQuestion(NewQuestionDTO question) {
        int id = 0;
        String sql = "INSERT INTO questions(title, description,user_id) VALUES (?,?,?);";
        try (Connection conn = databaseConnection.getConnection(); PreparedStatement statement =
                conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, question.title());
            statement.setString(2, question.description());
            statement.setInt(3, question.user_id());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        id = generatedKeys.getInt(1);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Failed to retrieve generated key", e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public int updateQuestion(int id, EditQuestionDTO question) {
        String sql = "UPDATE questions SET title = ?, description = ?  WHERE id = ?;";
        try (Connection conn = databaseConnection.getConnection(); PreparedStatement statement =
                conn.prepareStatement(
                sql)) {
            statement.setString(1, question.title());
            statement.setString(2, question.description());
            statement.setInt(3, id);

            statement.executeUpdate();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteQuestion(int id) {
        String sql = "DELETE FROM questions WHERE id = ?;";
        try (Connection conn = databaseConnection.getConnection(); PreparedStatement statement =
                conn.prepareStatement(
                sql)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
