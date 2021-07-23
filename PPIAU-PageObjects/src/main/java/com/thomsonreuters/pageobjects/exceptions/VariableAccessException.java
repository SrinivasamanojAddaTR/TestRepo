package com.thomsonreuters.pageobjects.exceptions;

import com.thomsonreuters.driver.exception.PLUKIException;

public class VariableAccessException extends PLUKIException {

    public VariableAccessException(String message) {
        super(message);
    }
    public VariableAccessException(String message, Throwable cause) {
        super(message, cause);
    }

}
