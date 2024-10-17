package com.example.tictactoeserver.tictactoeserver.service;

import com.example.tictactoeserver.tictactoeserver.model.Move;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class GameWebSocketHandler extends TextWebSocketHandler {
    private List<WebSocketSession> sessions = new ArrayList<>();
    private String[] board = new String[9];
    private String currentPlayer = "X";

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        // Parse move
        Move move = new ObjectMapper().readValue(payload, Move.class);
        board[move.getIndex()] = move.getPlayer();

        // Notify all clients about the move
        for (WebSocketSession s : sessions) {
            s.sendMessage(new TextMessage(payload));
        }

        // Check for winner and handle game logic
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    // Additional methods to check for winners and reset the game
}
