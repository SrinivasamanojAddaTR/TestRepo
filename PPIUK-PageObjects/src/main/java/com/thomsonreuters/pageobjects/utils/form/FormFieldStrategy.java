package com.thomsonreuters.pageobjects.utils.form;


import org.openqa.selenium.WebElement;

public interface FormFieldStrategy {
    String getValue(WebElement element);
    void editValue(WebElement element, String value);
}
