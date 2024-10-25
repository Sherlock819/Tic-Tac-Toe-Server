package com.example.tictactoeserver.tictactoeserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameStartMessage {
    private boolean started;
    private String currentPlayer;

    public GameStartMessage(boolean started, String currentPlayer) {
        this.started = started;
        this.currentPlayer = currentPlayer;
    }

    // Add getters and setters
}