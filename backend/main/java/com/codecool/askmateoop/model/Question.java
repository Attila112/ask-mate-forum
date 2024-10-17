package com.codecool.askmateoop.model;

import java.time.LocalDateTime;

public record Question(int id, String title, String description, LocalDateTime createdAt) {
}
