package com.thomsonreuters.step_definitions.footer;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.footer.FeedbackForm;
import com.thomsonreuters.pageobjects.pages.footer.FeedbackFormField;
import com.thomsonreuters.pageobjects.pages.footer.WLNFooter;
import com.thomsonreuters.pageobjects.utils.form.FormUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FeedbackTests extends BaseStepDef {

    private WLNFooter footer = new WLNFooter();
    private FeedbackForm feedbackForm = new FeedbackForm();
    private FormUtils formUtils = new FormUtils();
    private CommonMethods commonMethods = new CommonMethods();

    @When("^the user clicks on Beta Feedback button on footer$")
    public void theUserClicksOnBetaFeedbackButton() throws InterruptedException {
        footer.betaFeedbackLink().click();
    }

    @When("^user clicks on the Feedback link on the footer$")
    public void userClicksOnTheGivenLinkOnFooter() {
        footer.clickOnFeedBackLink();
    }

    @Then("^the user should see the following fields on Feedback form$")
    public void theUserShouldSeeFieldsOnFeedbackForm(DataTable dataTable) throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        for (Map.Entry<String, String> entry : dataTable.asMap(String.class, String.class).entrySet()) {
            String value = null;
            try {
                value = formUtils.getValue(FeedbackFormField.getByFieldDisplayName(entry.getKey())).trim();
				if (entry.getValue().equals("$not_empty")) {
					softly.assertThat(value).isNotEmpty().overridingErrorMessage("Value of field %s is empty", entry.getKey());
				} else {
					softly.assertThat(value).contains(entry.getValue().trim())
                        .overridingErrorMessage("Value of field %s is not %s but %s", entry.getKey(), entry.getValue(), value);
				}
            } catch (Exception e) {
                throw new Exception("Could not find or modify field '" + entry.getKey() + "'", e);
            }
        }
        softly.assertAll();
    }
    
    @Then("^the user should see the following email address \"(.*?)\" and telephone number \"(.*?)\" on Feedback form$")
	public void userShouldSeeSupportContactsOnFeedbackform(String email, String telephoneNumber) throws Throwable  {
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(feedbackForm.emailAddress().getText().equalsIgnoreCase(email)).overridingErrorMessage("Email address is not correct: <%s>, actual address: <%s>", email, feedbackForm.emailAddress()).isTrue();
		softly.assertThat(feedbackForm.telephoneNumber().getText().equalsIgnoreCase(telephoneNumber)).overridingErrorMessage("Telphone number is not correct: <%s>, actual number: <%s>", telephoneNumber, feedbackForm.telephoneNumber()).isTrue();
		softly.assertAll();
	}
    
	@Then("^Submit button on feedback form is (disabled|enabled)$")
	public void checkSubmitButtonOnfeedbackForm(String isButtonEnabled) throws Throwable  {
		WebElement element = feedbackForm.submitButton();
		boolean classContainsDisabledStatus = element.getAttribute("class").contains("co_disabled");
		if (isButtonEnabled.equals("disabled")) {
			assertTrue("Button should be disabled but got enabled", classContainsDisabledStatus);
		} else {
			assertFalse("Button should be enabled but got disabled", classContainsDisabledStatus);
		}
	}

    @When("^the user updates the following fields on Feedback form$")
    public void theUserUpdatesFieldsOnFeedbackForm(DataTable dataTable) throws Throwable {
        for (Map.Entry<String, String> entry : dataTable.asMap(String.class, String.class).entrySet()) {
            try {
                formUtils.editValue(FeedbackFormField.getByFieldDisplayName(entry.getKey()), entry.getValue());
            } catch (Exception e) {
                throw new Exception("Could not find or modify field '" + entry.getKey() + "'", e);
            }
        }
    }

    @When("^the user clicks on Submit button$")
    public void theUserClicksOnSubmitButton() {
        feedbackForm.submitButton().click();
    }

    @Then("^the feedback is submitted successfully$")
    public void theFeedbackIsSubmittedSuccessfully() throws Throwable {
        assertTrue("The feedback was successfully submitted".equalsIgnoreCase(commonMethods.getAlertDialogMsg()));
        commonMethods.acceptAlertDialogMsg();
    }

    @When("^the user clicks on the Close icon on the feedback form$")
    public void theUserClicksOnTheCloseIconOnTheFeedbackForm() throws Throwable {
        feedbackForm.closeFeedBackForm();
    }

	@Then("^the user should be presented with a confirmation box to close feedback form$")
    public void theUserShouldBePresentedWithAConfirmationBox() throws Throwable {
        assertTrue("All changes will be lost. Do you really want to cancel feedback?".equalsIgnoreCase(commonMethods.getAlertDialogMsg()));
    }

	@Then("^the user should be presented with a message box telling that feedback is submitted$")
	public void theUserShouldBePresentedWithAMessageBoxOk() throws Throwable {
		assertTrue("The feedback was successfully submitted".equalsIgnoreCase(commonMethods.getAlertDialogMsg()));
		commonMethods.acceptAlertDialogMsg();
	}

    @Then("^feedback form should close when the user clicks the ok button$")
    public void feedbackFormShouldCloseWhenTheUserClicksTheOkButton() throws Throwable {
        commonMethods.acceptAlertDialogMsg();
    }

	@Then("^captcha is present$")
	public void captchaIsPresent() throws Throwable {
		assertTrue("Captcha is not displayed", feedbackForm.captcha().isDisplayed());
	}
}
