package com.DietfriendsTodoDemoApp.exception;

public class UserNotWriterException extends RuntimeException{

    public UserNotWriterException() {
    }
    public UserNotWriterException(String message) {
        super(message);
    }

    public UserNotWriterException(String message, Throwable cause) {
        super(message, cause);
    }
}
