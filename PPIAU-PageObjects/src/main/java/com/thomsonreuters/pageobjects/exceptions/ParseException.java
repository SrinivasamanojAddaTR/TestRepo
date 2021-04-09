package com.thomsonreuters.pageobjects.exceptions;
import com.thomsonreuters.driver.exception.PLUKIException;

public class ParseException extends PLUKIException {
    public ParseException(String message) {
        super(message);
    }
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
