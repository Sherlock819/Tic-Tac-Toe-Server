package com.example.tictactoeserver.tictactoeserver.utils;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidationUtilities {
    private String cleanup(String data)
    {
        return data.trim();
    }

    public boolean isValidName(String name) {
        // Regular expression to check if the name contains only letters (case insensitive)

        return name != null && name.matches("[a-zA-Z]+");
    }

    public boolean isValidMobileNumber(String mobileNumber) {
        if(mobileNumber == null)
            return false;
        // You can customize the pattern to match your specific requirements
        String regex = "^\\d{10}$"; // For a 10-digit mobile number
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }

    public boolean isValidEmailAddress(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) return false;
        return pat.matcher(email).matches();
    }

    public boolean isValidPrice(Double price) {
        // Check if price is null
        if (price == null) {
            return false; // Invalid if price is null
        }
        // Check if price is greater than zero
        return price > 0;
    }

    public boolean isValidAveragePreparationTime(Duration averageTime) {
        // Check if averageTime is null
        if (averageTime == null) {
            return false; // Invalid if averageTime is null
        }

        // Check if averageTime is negative
        if (averageTime.isNegative()) {
            return false; // Invalid if averageTime is negative
        }

        // Optional: Define a maximum allowed average time (e.g., 2 hours)
        Duration maxAllowedTime = Duration.ofHours(2);

        return averageTime.compareTo(maxAllowedTime) <= 0; // Valid if it's not greater than max allowed
    }
}
