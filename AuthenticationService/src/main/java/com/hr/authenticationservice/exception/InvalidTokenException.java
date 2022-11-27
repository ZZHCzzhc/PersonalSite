package com.hr.authenticationservice.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("InvalidToken");
    }
}
