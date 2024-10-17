package com.codecool.askmateoop.controller.dto.answerDTO;

import java.time.LocalDateTime;

public record AnswerDTO(int id, String description, int question_id, int user_id,
                        LocalDateTime created_at) {
}
