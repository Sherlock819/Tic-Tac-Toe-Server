package com.example.tictactoeserver.tictactoeserver.controller;

import com.example.tictactoeserver.tictactoeserver.model.Move;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    @MessageMapping("/move") // Endpoint for receiving moves
    @SendTo("/topic/moves") // Send updates to all subscribed clients
    public Move handleMove(Move move) {
        System.out.println("Received move: " + move.getIndex() + " by player: " + move.getPlayer());  // Add this log
        return move; // Echo back the move (you can add game logic here)
    }
}

