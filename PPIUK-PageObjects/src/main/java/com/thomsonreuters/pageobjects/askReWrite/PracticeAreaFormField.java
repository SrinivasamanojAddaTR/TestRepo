package com.thomsonreuters.pageobjects.askReWrite;

import com.thomsonreuters.pageobjects.utils.form.DefaultFormField;
import com.thomsonreuters.pageobjects.utils.form.FormField;
import com.thomsonreuters.pageobjects.utils.form.FormFieldStrategy;
import org.openqa.selenium.By;


public enum PracticeAreaFormField implements FormField {
    NAME("Name", By.cssSelector(".active #askName"), DefaultFormField.newInstance().getTextByValue(), "ASK_NAME",
            By.cssSelector(".active span[ng-show*='.askName.$error']:not(.ng-hide)")),
    VALUE_ON_ASK_FORM("Value on Ask Form", By.cssSelector(".active #formName"), DefaultFormField.newInstance().getTextByValue(), "FORM_NAME",
            By.cssSelector(".active span[ng-show*='.formName.$error']:not(.ng-hide)"));

    private String displayName;
    private By by;
    private FormFieldStrategy formFieldStrategy;
    private String nameInDatabase;
    private By errorBy;

    PracticeAreaFormField(String displayName, By by, FormFieldStrategy formFieldStrategy, String nameInDatabase, By errorBy) {
        this.displayName = displayName;
        this.by = by;
        this.formFieldStrategy = formFieldStrategy;
        this.nameInDatabase = nameInDatabase;
        this.errorBy = errorBy;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getNameInDatabase() {
        return nameInDatabase;
    }

    @Override
    public By getBy() {
        return by;
    }

    @Override
    public FormFieldStrategy getFormFieldStrategy() {
        return formFieldStrategy;
    }

    public By getErrorBy() {
        return this.errorBy;
    }

    public static PracticeAreaFormField getByFieldDisplayName(String label) {
        for (PracticeAreaFormField formField : PracticeAreaFormField.values()) {
            if (formField.getDisplayName().equalsIgnoreCase(label)) {
                return formField;
            }
        }
        throw new UnsupportedOperationException("Field " + label + " is not in the form");
    }
}