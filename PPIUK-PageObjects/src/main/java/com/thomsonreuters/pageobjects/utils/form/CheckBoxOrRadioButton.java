package com.thomsonreuters.pageobjects.utils.form;

import org.openqa.selenium.WebElement;

public final class CheckBoxOrRadioButton implements FormFieldStrategy {

    public static final String SELECTED = "Selected";
    public static final String NOT_SELECTED = "Not Selected";

    public static CheckBoxOrRadioButton newInstance(){
      return new CheckBoxOrRadioButton();
    }

    @Override
    public String getValue(final WebElement element) {
        return element.isSelected() ? SELECTED : NOT_SELECTED ;
    }

    @Override
    public void editValue(final WebElement element, final String value) {
        String actualValue1 = element.isSelected() ? SELECTED : NOT_SELECTED;
        if (!actualValue1.equalsIgnoreCase(value)) {
            element.click();
        }
    }
}
