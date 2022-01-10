package com.felipeflohr.autodrawer.exception;

public class InvalidBrushSizeException extends RuntimeException {

    public InvalidBrushSizeException() {}

    public InvalidBrushSizeException(String message) {
        super(message);
    }

    public InvalidBrushSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
