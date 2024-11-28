package com.example.tictactoeserver.tictactoeserver.controller;


import com.example.tictactoeserver.tictactoeserver.dto.LoginRequestDTO;
import com.example.tictactoeserver.tictactoeserver.dto.LoginValidationDTO;
import com.example.tictactoeserver.tictactoeserver.service.OtpService;
import com.example.tictactoeserver.tictactoeserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserService userService;

    // Endpoint for requesting an OTP
    @PostMapping("/request-otp")
    public ResponseEntity<String> requestOtp(@RequestBody LoginRequestDTO otpRequest) {
        try {
            if (otpRequest.getEmail() != null) {
                otpService.generateAndSendOtp(otpRequest.getEmail(), otpRequest.getRole(), true);
                return ResponseEntity.ok("OTP sent to " + otpRequest.getEmail());
            } else if (otpRequest.getMobile() != null) {
                otpService.generateAndSendOtp(otpRequest.getMobile(), otpRequest.getRole(), false);
                return ResponseEntity.ok("OTP sent to " + otpRequest.getMobile());
            } else {
                return ResponseEntity.badRequest().body("Please provide either an email or mobile number.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred while sending OTP.");
        }
    }

    // Endpoint for validating the OTP
    @PostMapping("/validate-otp")
    public ResponseEntity<Map<String, String>> validateOtp(@RequestBody LoginValidationDTO otpValidationRequest) {
        try {
            String jwtToken = otpService.validateOtpForEmail(otpValidationRequest.getEmail(), otpValidationRequest.getOtp());
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("token", jwtToken);
            }});
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "An error occurred during OTP validation."));
        }
    }

    @CrossOrigin(origins = "http://localhost:3006") // Allow requests from this origin
    @GetMapping("/isAuthenticated")
    public Boolean isAuthenticated() {
        return userService.isAuthenticated();
    }
}

