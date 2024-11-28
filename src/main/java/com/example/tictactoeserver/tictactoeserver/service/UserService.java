package com.example.tictactoeserver.tictactoeserver.service;


import com.example.tictactoeserver.tictactoeserver.dto.RegistrationRequestDTO;
import com.example.tictactoeserver.tictactoeserver.dto.UserResponseDTO;
import com.example.tictactoeserver.tictactoeserver.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    String deleteUser();

    UserResponseDTO updateUserProfile(RegistrationRequestDTO registrationRequestDTO);

    User getUser();

    Boolean isAuthenticated();

    User loadUserByUsername(String email);
}
