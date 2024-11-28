package com.example.tictactoeserver.tictactoeserver.exception;

public class InValidOperationException extends RuntimeException {
    private String message;

    public InValidOperationException() {
    }

    public InValidOperationException(String msg) {
        super(msg);
        this.message = msg;
    }
}
