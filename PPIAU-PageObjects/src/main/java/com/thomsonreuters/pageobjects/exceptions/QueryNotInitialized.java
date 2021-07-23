package com.thomsonreuters.pageobjects.exceptions;

import com.thomsonreuters.driver.exception.PLUKIException;

public class QueryNotInitialized extends PLUKIException {
    public QueryNotInitialized(String message) {
        super(message);
    }
    public QueryNotInitialized(String message, Throwable cause) {
        super(message, cause);
    }
}
