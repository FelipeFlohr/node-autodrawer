package com.felipeflohr.autodrawer.exception;

public class InvalidValueOnPropertiesFileException extends Exception {

    public InvalidValueOnPropertiesFileException() {}

    public InvalidValueOnPropertiesFileException(String message) {
        super(message);
    }

    public InvalidValueOnPropertiesFileException(Throwable cause) {
        super(cause);
    }

    public InvalidValueOnPropertiesFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
