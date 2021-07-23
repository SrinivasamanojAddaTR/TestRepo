package com.thomsonreuters.pageobjects.exceptions;

import com.thomsonreuters.driver.exception.PLUKIException;

public class PropertyFileReaderException extends PLUKIException {

    public PropertyFileReaderException(String message) {
        super(message);
    }
    public PropertyFileReaderException(String message, Throwable cause) {
        super(message, cause);
    }

}
