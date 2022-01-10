package com.felipeflohr.autodrawer.exception;

public class InvalidImageSizeException extends RuntimeException {

    public InvalidImageSizeException() {}

    public InvalidImageSizeException(String message) {
        super(message);
    }

    public InvalidImageSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
