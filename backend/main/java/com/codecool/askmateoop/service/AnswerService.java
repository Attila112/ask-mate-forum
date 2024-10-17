package com.codecool.askmateoop.service;

import com.codecool.askmateoop.controller.dto.answerDTO.AnswerDTO;
import com.codecool.askmateoop.controller.dto.answerDTO.NewAnswerDTO;
import com.codecool.askmateoop.controller.dto.answerDTO.UpdateAnswerDTO;
import com.codecool.askmateoop.dao.answerDao.AnswerDAO;
import com.codecool.askmateoop.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {
    private final AnswerDAO answerDAO;

    @Autowired
    public AnswerService(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    public List<AnswerDTO> getAllAnswers() {
        List<Answer> allAnswers = answerDAO.getAllAnswers();
        List<AnswerDTO> answewrDTOList = new ArrayList<>();
        for (Answer answer : allAnswers) {
            AnswerDTO dto = new AnswerDTO(answer.id(), answer.description(), answer.questionId(),
                    answer.userId(), answer.createdAt());
            answewrDTOList.add(dto);
        }
        return answewrDTOList;
    }

    public AnswerDTO getAnswerById(int id) {
        Answer answer = answerDAO.getAnswerById(id);
        return new AnswerDTO(id, answer.description(), answer.questionId(), answer.userId(),
                answer.createdAt());
    }

    public int addAnswer(NewAnswerDTO answer) {
        return answerDAO.saveAnswer(answer);
    }

    public void deleteAnswer(int id) {
        answerDAO.deleteAnswer(id);
    }

    public void updateAnswer(int id, UpdateAnswerDTO answer) {
        answerDAO.updateAnswer(id, answer);
    }

    public List<AnswerDTO> getAnswerByQuestionId(int questionId) {
        List<Answer> allAnswers = answerDAO.getAllAnswers();
        List<AnswerDTO> answerDTOList = new ArrayList<>();
        for (Answer answer : allAnswers) {
            if (answer.questionId() == questionId) {
                answerDTOList.add(getAnswerById(answer.id()));
            }
        }
        return answerDTOList;
    }
}
