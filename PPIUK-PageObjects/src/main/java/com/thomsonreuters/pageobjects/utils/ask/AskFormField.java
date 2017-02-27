package com.thomsonreuters.pageobjects.utils.ask;

import com.thomsonreuters.pageobjects.utils.form.DefaultFormField;
import com.thomsonreuters.pageobjects.utils.form.DropDown;
import com.thomsonreuters.pageobjects.utils.form.FormField;
import com.thomsonreuters.pageobjects.utils.form.FormFieldStrategy;
import org.openqa.selenium.By;

public enum AskFormField implements FormField {

    //AGREE_TO_DISCLAIMER("Agree to disclaimer", By.id("HasReadTerms"), CheckBoxOrRadioButton.newInstance(), By.cssSelector("span[data-valmsg-for='HasReadTerms']"), "Please indicate that you have read the disclaimer"),

    QUESTION(Constants.QUESTION, By.id(Constants.QUESTION), DefaultFormField.newInstance(), By.cssSelector("span[data-valmsg-for='Question']"), "Please enter your query"),
    DOCUMENT_URL("Document Id", By.id("ResourceId"), DefaultFormField.newInstance().getTextByValue(), null, null),

    FIRST_NAME("First Name", By.id("FirstName"), DefaultFormField.newInstance().getTextByValue(), By.cssSelector("span[data-valmsg-for='FirstName']"), "Please enter your first name"),
    LAST_NAME("Last Name", By.id("LastName"), DefaultFormField.newInstance().getTextByValue(), By.cssSelector("span[data-valmsg-for='LastName']"), "Please enter your last name"),
    EMAIL("Email", By.id("Email"), DefaultFormField.newInstance().getTextByValue(), By.cssSelector("span[data-valmsg-for='Email']"), "Please enter your e-mail address"),
    ORGANISATION_TYPE("Organisation Type", By.id("OrganisationType"), DropDown.newInstance(), By.cssSelector("span[data-valmsg-for='OrganisationType']"), "Please enter your organisation type"),
    POSITION("Position", By.id("Position"), DropDown.newInstance(), By.cssSelector("span[data-valmsg-for='Position']"), "Please enter your position"),
    JURISDICTION("Jurisdiction", By.id("Jurisdiction"), DropDown.newInstance(), By.cssSelector("span[data-valmsg-for='Jurisdiction']"), "Please enter a jurisdiction type"),
    ANSWERING_SERVICE("Answering Service", By.id("AnsweringService"), DropDown.newInstance(), By.cssSelector("span[data-valmsg-for='AnsweringService']"), "Please enter an answering service");


    private String displayName;
    private By by;
    private FormFieldStrategy formFieldStrategy;
    private By errorBy;
    private String errorMessage;

    AskFormField(String displayName, By by, FormFieldStrategy formFieldStrategy, By errorBy, String errorMessage) {
        this.displayName = displayName;
        this.by = by;
        this.formFieldStrategy = formFieldStrategy;
        this.errorBy = errorBy;
        this.errorMessage = errorMessage;
    }

    public String getDisplayName() {
        return displayName;
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
        return errorBy;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static AskFormField getByFieldDisplayName(String label) {
        for (AskFormField formField : AskFormField.values()) {
            if (formField.getDisplayName().equalsIgnoreCase(label)) {
                return formField;
            }
        }
        throw new UnsupportedOperationException("Field " + label + " is not in the form");
    }

    private static class Constants {
        public static final String QUESTION = "Question";
    }
}
