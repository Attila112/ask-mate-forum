package com.codecool.askmateoop.controller;

import com.codecool.askmateoop.controller.dto.answerDTO.AnswerDTO;
import com.codecool.askmateoop.controller.dto.answerDTO.NewAnswerDTO;
import com.codecool.askmateoop.controller.dto.answerDTO.UpdateAnswerDTO;
import com.codecool.askmateoop.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnswerController.class)
public class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerService answerService;

    @Autowired
    private ObjectMapper objectMapper;

    private AnswerDTO answerDTO;
    private NewAnswerDTO newAnswerDTO;
    private UpdateAnswerDTO updateAnswerDTO;

    @BeforeEach
    public void setUp() {
        // Mocking AnswerDTO with LocalDateTime field
        LocalDateTime mockCreatedAt = LocalDateTime.now();
        answerDTO = new AnswerDTO(1, "Test answer", 1, 1, mockCreatedAt);

        // NewAnswerDTO has no LocalDateTime
        newAnswerDTO = new NewAnswerDTO("New answer", 1, 1);

        // UpdateAnswerDTO only requires the description
        updateAnswerDTO = new UpdateAnswerDTO("Updated answer");
    }

    @Test
    public void getAllAnswers_ReturnsOk() throws Exception {
        when(answerService.getAllAnswers()).thenReturn(Collections.singletonList(answerDTO));

        mockMvc.perform(get("/api/answer/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(answerDTO))));
    }

    @Test
    public void getAnswerById_ReturnsOk() throws Exception {
        when(answerService.getAnswerById(1)).thenReturn(answerDTO);

        mockMvc.perform(get("/api/answer/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(answerDTO)));
    }

    @Test
    public void addAnswer_ReturnsCreated() throws Exception {
        when(answerService.addAnswer(any(NewAnswerDTO.class))).thenReturn(1);

        mockMvc.perform(post("/api/answer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newAnswerDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    public void deleteAnswer_ReturnsIsOk() throws Exception {
        doNothing().when(answerService).deleteAnswer(1);

        mockMvc.perform(delete("/api/answer/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateAnswer_ReturnsIsOk() throws Exception {
        doNothing().when(answerService).updateAnswer(eq(1), any(UpdateAnswerDTO.class));

        mockMvc.perform(patch("/api/answer/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateAnswerDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void getQuestionAnswers_ReturnsOk() throws Exception {
        when(answerService.getAnswerByQuestionId(1)).thenReturn(Collections.singletonList(answerDTO));

        mockMvc.perform(get("/api/answer/question/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(answerDTO))));
    }
}
