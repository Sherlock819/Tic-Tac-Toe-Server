package com.example.tictactoeserver.tictactoeserver.controller;

import com.example.tictactoeserver.tictactoeserver.model.Game;
import com.example.tictactoeserver.tictactoeserver.model.GameStartMessage;
import com.example.tictactoeserver.tictactoeserver.model.MoveMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class GameController {
    private final Map<String, Game> games = new ConcurrentHashMap<>();
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public GameController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    //Mapped as app/create-game
    @MessageMapping("/create-game")
    @SendTo("/topic/game-created")
    public void createGame(@Payload String gameId, SimpMessageHeaderAccessor headerAccessor) {
        String playerId = headerAccessor.getSessionId();
        games.put(gameId, new Game(gameId));
        games.get(gameId).addPlayer(playerId);
        System.out.println("Game created with ID: " + gameId);
        messagingTemplate.convertAndSend("/topic/game-created/" + gameId, gameId);
    }

    @MessageMapping("/join-game")
    public void joinGame(@Payload String gameId, SimpMessageHeaderAccessor headerAccessor) {
        String playerId = headerAccessor.getSessionId();
        Game game = games.get(gameId);

        if (game != null && game.addPlayer(playerId)) {
            if (game.isFull()) {
                System.out.println("Starting game for gameId : "+gameId);
                startGame(gameId);
            }
        }
    }

    private void startGame(String gameId) {
        Game game = games.get(gameId);
        game.start();
        String currentPlayer = game.getCurrentPlayer();
        GameStartMessage startMessage = new GameStartMessage(true, currentPlayer);
        messagingTemplate.convertAndSend("/topic/game/" + gameId + "/start", startMessage);
    }

    @MessageMapping("/make-move")
    public void makeMove(@Payload MoveMessage moveMessage, SimpMessageHeaderAccessor headerAccessor) {
        String playerId = headerAccessor.getSessionId();
        Game game = games.get(moveMessage.getGameId());

        if (game != null && game.isPlayerTurn(playerId)) {
            boolean moveMade = game.makeMove(playerId, moveMessage.getPosition());
            if (moveMade) {
                messagingTemplate.convertAndSend("/topic/game/" + moveMessage.getGameId(), moveMessage);
            }
        }
    }

    // ... (other methods as needed)
}