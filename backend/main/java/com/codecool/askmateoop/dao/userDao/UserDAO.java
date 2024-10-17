package com.codecool.askmateoop.dao.userDao;

import com.codecool.askmateoop.controller.dto.userDTO.NewUserDTO;
import com.codecool.askmateoop.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    User getUserById(int id);

    int saveUser(NewUserDTO newUserDTO);

    void updateUser(int id, NewUserDTO user);

    boolean deleteUser(int id);

    User getUserToLogin(String name, String password);

}
