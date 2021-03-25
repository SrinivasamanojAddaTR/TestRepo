package com.thomsonreuters.pageobjects.exceptions;
import com.thomsonreuters.driver.exception.PLUKIException;

public class EmailException extends PLUKIException {
    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
