package com.thomsonreuters.pageobjects.exceptions;

public class CookieNotFoundException extends RuntimeException {

    public CookieNotFoundException(String message) {
        super(message);
    }

    public CookieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
