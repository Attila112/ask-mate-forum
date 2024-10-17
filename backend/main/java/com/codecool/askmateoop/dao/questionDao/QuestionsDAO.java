package com.codecool.askmateoop.dao.questionDao;

import com.codecool.askmateoop.controller.dto.questionDTO.EditQuestionDTO;
import com.codecool.askmateoop.controller.dto.questionDTO.NewQuestionDTO;
import com.codecool.askmateoop.model.Question;

import java.util.List;

public interface QuestionsDAO {
    List<Question> getAllQuestions();

    Question getQuestionById(int id);

    int saveQuestion(NewQuestionDTO question);

    boolean deleteQuestion(int id);

    int updateQuestion(int id, EditQuestionDTO question);

}
