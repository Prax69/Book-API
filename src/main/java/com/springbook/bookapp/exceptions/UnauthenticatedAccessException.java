package com.springbook.bookapp.exceptions;

public class UnauthenticatedAccessException extends RuntimeException {

    public UnauthenticatedAccessException(String message) {
        super(message);
    }

    public UnauthenticatedAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthenticatedAccessException(Throwable cause) {
        super(cause);
    }
}
