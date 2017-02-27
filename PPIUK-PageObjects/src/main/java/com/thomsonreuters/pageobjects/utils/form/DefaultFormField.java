package com.thomsonreuters.pageobjects.utils.form;


import org.openqa.selenium.WebElement;

public final class DefaultFormField implements FormFieldStrategy {

    private String attribute;

    public static DefaultFormField newInstance() {
        return new DefaultFormField();
    }

    public DefaultFormField getTextByValue() {
        this.attribute = "value";
        return this;
    }

    @Override
    public String getValue(final WebElement element) {
        if (null != attribute) {
            return element.getAttribute(attribute);
        } else {
            return element.getText();
        }
    }

    @Override
    public void editValue(final WebElement element, final String value) {
        element.clear();
        element.sendKeys(value);
    }

}
