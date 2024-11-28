package com.example.tictactoeserver.tictactoeserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginValidationDTO {
    private String email;
    private String mobile;
    private String otp;
}
