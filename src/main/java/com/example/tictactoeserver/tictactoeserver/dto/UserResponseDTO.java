package com.example.tictactoeserver.tictactoeserver.dto;

import com.example.tictactoeserver.tictactoeserver.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private UserRole role;
}
