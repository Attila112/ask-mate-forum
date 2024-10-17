package com.codecool.askmateoop.service;

import com.codecool.askmateoop.controller.dto.questionDTO.EditQuestionDTO;
import com.codecool.askmateoop.controller.dto.questionDTO.NewQuestionDTO;
import com.codecool.askmateoop.controller.dto.questionDTO.QuestionDTO;
import com.codecool.askmateoop.dao.questionDao.QuestionsDAO;
import com.codecool.askmateoop.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions() {
        List<Question> allQuestions = questionsDAO.getAllQuestions();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : allQuestions) {
            QuestionDTO dto = new QuestionDTO(question.id(), question.title(),
                    question.description(), question.createdAt());
            questionDTOList.add(dto);
        }
        return questionDTOList;
    }

    public QuestionDTO getQuestionById(int id) {
        Question question = questionsDAO.getQuestionById(id);
        return new QuestionDTO(id, question.title(), question.description(), question.createdAt());
    }

    public boolean deleteQuestionById(int id) {
        return questionsDAO.deleteQuestion(id);
    }

    public int addNewQuestion(NewQuestionDTO question) {
        return questionsDAO.saveQuestion(question);
    }

    public int updateQuestion(int id, EditQuestionDTO question) {
        return questionsDAO.updateQuestion(id, question);
    }
}
