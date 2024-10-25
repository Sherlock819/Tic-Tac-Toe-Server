package com.example.tictactoeserver.tictactoeserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MoveMessage {
    private String gameId;
    private int position;
    private String symbol;

    // Default constructor
    public MoveMessage() {}

    // Constructor with all fields
    public MoveMessage(String gameId, int position, String symbol) {
        this.gameId = gameId;
        this.position = position;
        this.symbol = symbol;
    }

    // Getters and setters
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
