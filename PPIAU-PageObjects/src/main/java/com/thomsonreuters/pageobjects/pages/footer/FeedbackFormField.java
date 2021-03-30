package com.thomsonreuters.pageobjects.pages.footer;

import com.thomsonreuters.pageobjects.utils.form.DefaultFormField;
import com.thomsonreuters.pageobjects.utils.form.FormField;
import com.thomsonreuters.pageobjects.utils.form.FormFieldStrategy;
import org.openqa.selenium.By;

public enum FeedbackFormField implements FormField {

	FEEDBACK_FROM("Feedback from", By.id("testerName"), DefaultFormField.newInstance().getTextByValue()),
	USER_NAME("User name", By.id("userName"), DefaultFormField.newInstance().getTextByValue()),
	EMAIL_ADDRESS("Email address", By.id("email"), DefaultFormField.newInstance().getTextByValue()),
	PAGE("Page", By.id("page"), DefaultFormField.newInstance().getTextByValue()),
	URL("URL", By.id("url"), DefaultFormField.newInstance().getTextByValue()),
	GENERAL_COMMENTS("General comments", By.id("feedbackText"), DefaultFormField.newInstance().getTextByValue()),
	PROBLEM("If you encountered a problem", By.id("steps"), DefaultFormField.newInstance().getTextByValue());

	private String displayName;
	private By by;
	private FormFieldStrategy formFieldStrategy;

	FeedbackFormField(String displayName, By by, FormFieldStrategy formFieldStrategy) {
		this.displayName = displayName;
		this.by = by;
		this.formFieldStrategy = formFieldStrategy;
	}

	public String getDisplayName() {
		return displayName;
	}

	public By getBy() {
		return by;
	}

	public FormFieldStrategy getFormFieldStrategy() {
		return formFieldStrategy;
	}

	public static FeedbackFormField getByFieldDisplayName(String label) {
		for (FeedbackFormField field : FeedbackFormField.values()) {
			if (field.getDisplayName().equalsIgnoreCase(label)) {
				return field;
			}
		}
		throw new UnsupportedOperationException("Field " + label + " is not in the ENUM");
	}

}
