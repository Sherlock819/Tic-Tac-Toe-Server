package com.example.tictactoeserver.tictactoeserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Move {
    private int index;
    private String player;

    // Getters and Setters
}

