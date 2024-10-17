package com.codecool.askmateoop.controller.dto.userDTO;

import java.time.LocalDateTime;

public record UserDTO(int id, String user_name, String user_password, LocalDateTime created_at) {
}
