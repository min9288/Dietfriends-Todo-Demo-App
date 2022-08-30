package com.DietfriendsTodoDemoApp.exception;

public class UserNameAlreadyExistsException extends RuntimeException{

    public UserNameAlreadyExistsException() {
    }

    public UserNameAlreadyExistsException(String message) {
        super(message);
    }

    public UserNameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
