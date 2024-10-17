package com.codecool.askmateoop.dao.answerDao;

import com.codecool.askmateoop.controller.dto.answerDTO.NewAnswerDTO;
import com.codecool.askmateoop.controller.dto.answerDTO.UpdateAnswerDTO;
import com.codecool.askmateoop.model.Answer;

import java.util.List;

public interface AnswerDAO {
    List<Answer> getAllAnswers();

    Answer getAnswerById(int id);

    List<Answer> getAnswersByQuestionId(int questionId);

    int saveAnswer(NewAnswerDTO answer);

    void deleteAnswer(int id);

    void updateAnswer(int id, UpdateAnswerDTO answer);
}
