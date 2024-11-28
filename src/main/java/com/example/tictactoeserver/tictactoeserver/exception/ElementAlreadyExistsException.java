package com.example.tictactoeserver.tictactoeserver.exception;

public class ElementAlreadyExistsException extends RuntimeException {
    private String message;

    public ElementAlreadyExistsException() {
    }

    public ElementAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
