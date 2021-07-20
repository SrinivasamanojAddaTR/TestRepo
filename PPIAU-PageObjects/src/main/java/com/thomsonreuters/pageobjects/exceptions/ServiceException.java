package com.thomsonreuters.pageobjects.exceptions;

import com.thomsonreuters.driver.exception.PLUKIException;

public class ServiceException extends PLUKIException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
