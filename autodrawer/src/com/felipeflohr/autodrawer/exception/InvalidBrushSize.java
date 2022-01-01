package com.felipeflohr.autodrawer.exception;

public class InvalidBrushSize extends RuntimeException {

    public InvalidBrushSize() {}

    public InvalidBrushSize(String message) {
        super(message);
    }

    public InvalidBrushSize(String message, Throwable cause) {
        super(message, cause);
    }
}
