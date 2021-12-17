package com.felipeflohr.autodrawer.exception;

public class InvalidCoordinateOnPropertiesFileException extends Exception {

    public InvalidCoordinateOnPropertiesFileException() {}

    public InvalidCoordinateOnPropertiesFileException(String message) {
        super(message);
    }

    public InvalidCoordinateOnPropertiesFileException(Throwable cause) {
        super(cause);
    }

    public InvalidCoordinateOnPropertiesFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
