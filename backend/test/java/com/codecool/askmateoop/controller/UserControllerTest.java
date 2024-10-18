package com.codecool.askmateoop.controller;

import com.codecool.askmateoop.controller.dto.userDTO.LogedInUserDTO;
import com.codecool.askmateoop.controller.dto.userDTO.NewUserDTO;
import com.codecool.askmateoop.controller.dto.userDTO.UserDTO;
import com.codecool.askmateoop.model.User;
import com.codecool.askmateoop.service.UserService;
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

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<UserDTO> userList = Collections.singletonList(new UserDTO(1, "JohnDoe", "password", LocalDateTime.now()));
        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/api/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].user_name").value("JohnDoe"))
                .andExpect(jsonPath("$[0].user_password").value("password"));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById() throws Exception {
        UserDTO userDTO = new UserDTO(1, "JohnDoe", "password", LocalDateTime.now());
        when(userService.getUserById(1)).thenReturn(userDTO);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.user_name").value("JohnDoe"))
                .andExpect(jsonPath("$.user_password").value("password"));

        verify(userService, times(1)).getUserById(1);
    }

    @Test
    void testCreateUser() throws Exception {
        NewUserDTO newUserDTO = new NewUserDTO("JohnDoe", "password");
        when(userService.saveUser(any(NewUserDTO.class))).thenReturn(1);

        mockMvc.perform(post("/api/users/")
                        .contentType("application/json")
                        .content("{\"user_name\":\"JohnDoe\",\"user_password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        verify(userService, times(1)).saveUser(any(NewUserDTO.class));
    }

    @Test
    void testUpdateUser() throws Exception {
        NewUserDTO updatedUserDTO = new NewUserDTO("UpdatedJohnDoe", "newpassword");

        mockMvc.perform(patch("/api/users/1")
                        .contentType("application/json")
                        .content("{\"user_name\":\"UpdatedJohnDoe\",\"user_password\":\"newpassword\"}"))
                .andExpect(status().isOk());

        verify(userService, times(1)).updateUser(eq(1), any(NewUserDTO.class));
    }

    @Test
    void testDeleteUser() throws Exception {
        when(userService.deleteUser(1)).thenReturn(true);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(userService, times(1)).deleteUser(1);
    }

    @Test
    void testLogin() throws Exception {
        LogedInUserDTO userData = new LogedInUserDTO("JohnDoe", "password");
        User loggedInUser = new User(1, "JohnDoe", "password", LocalDateTime.now());
        when(userService.login("JohnDoe", "password")).thenReturn(loggedInUser);

        mockMvc.perform(post("/api/users/login")
                        .contentType("application/json")
                        .content("{\"user_name\":\"JohnDoe\",\"user_password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(userService, times(1)).login("JohnDoe", "password");
    }

    @Test
    void testLogout() throws Exception {
        mockMvc.perform(post("/api/users/logout"))
                .andExpect(status().isOk());

    }

//    @Test
//    void testGetLoggedInUser() throws Exception {
//        User loggedInUser = new User(1, "JohnDoe", "password", LocalDateTime.now());
//        userController.login(new LogedInUserDTO("JohnDoe", "password"));
//        when(userService.login("JohnDoe", "password")).thenReturn(loggedInUser);
//
//        mockMvc.perform(get("/api/users/user"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.user_name").value("JohnDoe"))
//                .andExpect(jsonPath("$.user_password").value("password"));
//
//        verify(userService, times(1)).login("JohnDoe", "password");
//    }
}
