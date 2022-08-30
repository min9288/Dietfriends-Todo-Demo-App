package com.DietfriendsTodoDemoApp.exception;

public class TodoNotfoundException extends RuntimeException {
    public TodoNotfoundException() {
    }

    public TodoNotfoundException(String message) {
        super(message);
    }

    public TodoNotfoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
