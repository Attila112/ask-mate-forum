package com.codecool.askmateoop.service;

import com.codecool.askmateoop.controller.dto.userDTO.NewUserDTO;
import com.codecool.askmateoop.controller.dto.userDTO.UserDTO;
import com.codecool.askmateoop.model.User;
import com.codecool.askmateoop.dao.userDao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userDAO.getAllUsers();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : users) {
            UserDTO dto = new UserDTO(user.id(), user.username(), user.password(),
                    user.createdAt());
            userDTOList.add(dto);
        }
        return userDTOList;
    }

    public UserDTO getUserById(int id) {
        User user = userDAO.getUserById(id);
        return new UserDTO(user.id(), user.username(), user.password(), user.createdAt());
    }

    public int saveUser(NewUserDTO newUserDTO) {
        return userDAO.saveUser(newUserDTO);
    }

    public void updateUser(int id, NewUserDTO user) {
        userDAO.updateUser(id, user);
    }

    public boolean deleteUser(int id) {
        return userDAO.deleteUser(id);
    }

    public User login(String name, String password) {
        return userDAO.getUserToLogin(name, password);
    }
}
