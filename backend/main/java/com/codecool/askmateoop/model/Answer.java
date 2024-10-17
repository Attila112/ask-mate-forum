package com.codecool.askmateoop.model;

import java.time.LocalDateTime;

public record Answer(int id, String description, int questionId, int userId,
                     LocalDateTime createdAt) {
}
