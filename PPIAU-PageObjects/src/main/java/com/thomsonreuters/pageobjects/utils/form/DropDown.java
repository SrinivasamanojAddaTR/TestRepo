package com.thomsonreuters.pageobjects.utils.form;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.apache.commons.lang3.StringUtils.isNotBlank;


public final class DropDown implements FormFieldStrategy {

    public enum SelectBy {
        VISIBLE_TEXT,
        VALUE,
        INDEX
    }

    private SelectBy selectBy;

    public static DropDown newInstance() {
        DropDown dropDown = new DropDown();
        dropDown.selectBy(SelectBy.VISIBLE_TEXT);
        return dropDown;
    }

    public DropDown selectBy(SelectBy selectBy) {
        this.selectBy = selectBy;
        return this;
    }

    @Override
    public String getValue(final WebElement element) {
        return new Select(element).getFirstSelectedOption().getText();
    }

    @Override
    public void editValue(final WebElement element, final String value) {
        if (isNotBlank(value)) {
            switch (selectBy) {
                case VISIBLE_TEXT:
                    new Select(element).selectByVisibleText(value);
                    break;
                case VALUE:
                    new Select(element).selectByValue(value);
                    break;
                case INDEX:
                    new Select(element).selectByIndex(Integer.parseInt(value));
                    break;
                default:
                    break;
            }
        }
    }
}
