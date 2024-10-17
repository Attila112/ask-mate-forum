package com.codecool.askmateoop.controller.dto.questionDTO;

import java.time.LocalDateTime;

public record QuestionDTO(int id, String title, String description, LocalDateTime created) {}
