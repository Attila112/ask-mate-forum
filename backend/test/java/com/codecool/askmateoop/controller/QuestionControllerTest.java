package com.codecool.askmateoop.controller;

import com.codecool.askmateoop.controller.dto.questionDTO.EditQuestionDTO;
import com.codecool.askmateoop.controller.dto.questionDTO.NewQuestionDTO;
import com.codecool.askmateoop.controller.dto.questionDTO.QuestionDTO;
import com.codecool.askmateoop.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class QuestionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionController questionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
    }

    @Test
    void testGetAllQuestions() throws Exception {
        List<QuestionDTO> questionList = Collections.singletonList(new QuestionDTO(1, "What is Java?", "Can someone explain Java?", LocalDateTime.now()));
        when(questionService.getAllQuestions()).thenReturn(questionList);

        mockMvc.perform(get("/api/question/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("What is Java?"))
                .andExpect(jsonPath("$[0].description").value("Can someone explain Java?"));

        verify(questionService, times(1)).getAllQuestions();
    }

    @Test
    void testGetQuestionById() throws Exception {
        QuestionDTO questionDTO = new QuestionDTO(1, "What is Java?", "Can someone explain Java?", LocalDateTime.now());
        when(questionService.getQuestionById(1)).thenReturn(questionDTO);

        mockMvc.perform(get("/api/question/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("What is Java?"))
                .andExpect(jsonPath("$.description").value("Can someone explain Java?"));

        verify(questionService, times(1)).getQuestionById(1);
    }

    @Test
    void testAddNewQuestion() throws Exception {
        NewQuestionDTO newQuestionDTO = new NewQuestionDTO("What is Java?", "Can someone explain Java?", 1);
        when(questionService.addNewQuestion(any(NewQuestionDTO.class))).thenReturn(1);

        mockMvc.perform(post("/api/question/")
                        .contentType("application/json")
                        .content("{\"title\":\"What is Java?\",\"description\":\"Can someone explain Java?\",\"user_id\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        verify(questionService, times(1)).addNewQuestion(any(NewQuestionDTO.class));
    }

    @Test
    void testDeleteQuestionById() throws Exception {
        when(questionService.deleteQuestionById(1)).thenReturn(true);

        mockMvc.perform(delete("/api/question/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(questionService, times(1)).deleteQuestionById(1);
    }

    @Test
    void testUpdateQuestionById() throws Exception {
        EditQuestionDTO editQuestionDTO = new EditQuestionDTO("Updated Title", "Updated Content");
        when(questionService.updateQuestion(1, editQuestionDTO)).thenReturn(1);

        mockMvc.perform(patch("/api/question/1")
                        .contentType("application/json")
                        .content("{\"title\":\"Updated Title\",\"description\":\"Updated Content\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        verify(questionService, times(1)).updateQuestion(eq(1), any(EditQuestionDTO.class));
    }
}
