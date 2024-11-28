package com.example.tictactoeserver.tictactoeserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtp(String toEmail, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Your OTP for Authentication");
            message.setText("Your OTP is: " + otp);

            message.setFrom("noreply@example.com");

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
