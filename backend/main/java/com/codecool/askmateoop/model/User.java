package com.codecool.askmateoop.model;

import java.time.LocalDateTime;

public record User(int id, String username, String password, LocalDateTime createdAt) {
}
