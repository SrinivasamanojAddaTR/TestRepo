package com.thomsonreuters.pageobjects.exceptions;
import com.thomsonreuters.driver.exception.PLUKIException;

public class PLAUException extends PLUKIException {
    public PLAUException(String message) {
        super(message);
    }
    public PLAUException(String message, Throwable cause) {
        super(message, cause);
    }
}