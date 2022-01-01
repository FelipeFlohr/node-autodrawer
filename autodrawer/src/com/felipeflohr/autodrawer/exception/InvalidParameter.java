package com.felipeflohr.autodrawer.exception;

public class InvalidParameter extends RuntimeException {

    public InvalidParameter() {}

    public InvalidParameter(String message, Throwable cause) {
        super(message, cause);
    }
}
