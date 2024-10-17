package com.codecool.askmateoop.configuration;

import com.codecool.askmateoop.dao.answerDao.AnswerDAO;
import com.codecool.askmateoop.dao.answerDao.AnswerDaoJdbc;
import com.codecool.askmateoop.dao.questionDao.QuestionsDAO;
import com.codecool.askmateoop.dao.questionDao.QuestionsDaoJdbc;
import com.codecool.askmateoop.dao.userDao.UserDAO;
import com.codecool.askmateoop.dao.userDao.UserDaoJdbc;
import com.codecool.askmateoop.database.DatabaseConnection;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class Configuration {
    private static final String DB_URL = System.getenv("DATABASE_URL");
    private static final String DB_USERNAME= System.getenv("DATABASE_USERNAME");
    private static final String DB_PASSWORD = System.getenv("DATABASE_PASSWORD");

//    TODO: Add the url of your database to the Environment Variables of the Run Configuration
//    @Value("${askmate.database.url}")
//    private String databaseUrl;


    @Bean
    public DatabaseConnection createDatabaseConnection() {
        return new DatabaseConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
    @Bean
    public QuestionsDAO questionsDAO() {
        return new QuestionsDaoJdbc(createDatabaseConnection());
    }

    @Bean
    public AnswerDAO answerDAO() { return new AnswerDaoJdbc(createDatabaseConnection()); }

    @Bean
    public UserDAO userDAO(){return new UserDaoJdbc(createDatabaseConnection());}


}
