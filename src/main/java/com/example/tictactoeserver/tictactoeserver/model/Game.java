package com.example.tictactoeserver.tictactoeserver.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class Game {
    private final String id;
    private List<String> players;
    private boolean started;
    @Getter
    private String currentPlayer;
    private String[] board;

    public Game(String id) {
        this.id = id;
        this.players = new ArrayList<>();
        this.started = false;
        this.board = new String[9];
        Arrays.fill(this.board, null);
    }

    public boolean addPlayer(String playerId) {
        if (players.size() < 2 && !players.contains(playerId)) {
            players.add(playerId);
            return true;
        }
        return false;
    }

    public void start() {
        if (players.size() == 2) {
            started = true;
            currentPlayer = players.get(0); // First player starts
        }
    }

    public boolean isFull() {
        return players.size() == 2;
    }

    public boolean isPlayerTurn(String playerId) {
        return currentPlayer.equals(playerId);
    }

    public boolean makeMove(String playerId, int position) {
        if (!isPlayerTurn(playerId) || position < 0 || position >= 9 || board[position] != null) {
            return false;
        }

        String symbol = players.indexOf(playerId) == 0 ? "X" : "O";
        board[position] = symbol;
        currentPlayer = (currentPlayer.equals(players.get(0))) ? players.get(1) : players.get(0);
        return true;
    }

    // ... (other methods as needed)
}