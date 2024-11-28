package com.example.tictactoeserver.tictactoeserver.controller;

import com.example.tictactoeserver.tictactoeserver.dto.RegistrationRequestDTO;
import com.example.tictactoeserver.tictactoeserver.dto.UserResponseDTO;
import com.example.tictactoeserver.tictactoeserver.service.UserService;
import com.example.tictactoeserver.tictactoeserver.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUserProfile")
    public ResponseEntity<?> getUserProfile() {
        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(userService.getUser());

        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateUserProfile")
    public ResponseEntity<UserResponseDTO> updateUserProfile(@RequestBody RegistrationRequestDTO registrationRequestDTO) {
        UserResponseDTO registeredUser = userService.updateUserProfile(registrationRequestDTO);

        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser() {
        return userService.deleteUser();
    }


    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userResponseDTOs = userMapper.toUserResponseDTOs(userService.findAll());
        return new ResponseEntity<>(userResponseDTOs, HttpStatus.OK);
    }
}
