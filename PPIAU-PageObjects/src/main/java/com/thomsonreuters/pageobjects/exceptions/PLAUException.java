package com.thomsonreuters.pageobjects.exceptions;

public class PLAUException extends RuntimeException {

    public PLAUException(String message) {
        super(message);
    }

    public PLAUException(String message, Throwable cause) {
        super(message, cause);
    }
}
