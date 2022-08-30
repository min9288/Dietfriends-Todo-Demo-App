package com.DietfriendsTodoDemoApp.exception;

public class TodoDeleteFailureException extends RuntimeException {
    public TodoDeleteFailureException() {
    }

    public TodoDeleteFailureException(String message) {
        super(message);
    }

    public TodoDeleteFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}

