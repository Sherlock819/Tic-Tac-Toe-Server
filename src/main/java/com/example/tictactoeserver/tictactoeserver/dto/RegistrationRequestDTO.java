package com.example.tictactoeserver.tictactoeserver.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
}
