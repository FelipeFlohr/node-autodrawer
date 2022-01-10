package com.felipeflohr.autodrawer.exception;

public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException() {}

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
