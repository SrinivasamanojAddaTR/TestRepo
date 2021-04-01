package com.thomsonreuters.step_definitions.uk.pageAndDocumentDisplay;

import com.thomsonreuters.pageobjects.pages.delivery.DownloadOptionsPage;
import com.thomsonreuters.pageobjects.pages.delivery.EmailOptionsPage;
import com.thomsonreuters.pageobjects.pages.delivery.PrintOptionsPage;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.DocumentDeliveryOptionsPage;
import com.thomsonreuters.pageobjects.utils.delivery.DeliveryFormField;
import com.thomsonreuters.pageobjects.utils.form.FormUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.Is;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class KHDelivery extends BaseStepDef {

	private EmailOptionsPage email = new EmailOptionsPage();
	private DownloadOptionsPage download = new DownloadOptionsPage();
	private DocumentDeliveryOptionsPage deliveryOptionsPage = new DocumentDeliveryOptionsPage();
	private FormUtils formUtils = new FormUtils();
	private PrintOptionsPage print = new PrintOptionsPage();

	public KHDelivery() {
	}

	@When("^clicks on (email|Print|Download) delivery option for the document$")
	public void clicksOnEmailDeliveryOptionForTheDocument(String deliveryOption) throws Throwable {
		switch (deliveryOption) {
		case "email":
			deliveryOptionsPage.email().click();
			email.basicTab().isDisplayed();
			email.emailButton().isDisplayed();
			break;
		case "Print":
			deliveryOptionsPage.print().click();
			print.printButton().isDisplayed();
			break;
		case "Download":
			deliveryOptionsPage.download().click();
			download.basicTab().isDisplayed();
			download.downloadButton().isDisplayed();
			break;
		default:
			break;
		}
	}

	@Then("^the user should be able to see (Email|Print|Download) (basic|advanced) tab options as follows$")
	public void theUserShouldBeAbleToSeeEmailBasicTabOptionsAsFollows(String deliveryType, String tabType, DataTable dataTable)
			throws Throwable {
		for (Map.Entry<String, String> entry : dataTable.asMap(String.class, String.class).entrySet()) {
			String value;
			try {
				value = formUtils.getValue(DeliveryFormField.getByFieldDisplayName(entry.getKey())).trim();
			} catch (Exception e) {
				throw new Exception("Could not find or modify field '" + entry.getKey() + "'", e);
			}
			assertThat(value, Is.is(entry.getValue().trim()));
		}
	}

	@Then("^the user edits the (basic|advanced) (email|print|download) options as follows$")
	public void theUserEditsTheBasicEmailOptionsAsFollows(String tabType, String deliveryType, DataTable dataTable) throws Throwable {
		for (Map.Entry<String, String> entry : dataTable.asMap(String.class, String.class).entrySet()) {
			try {
				formUtils.editValue(DeliveryFormField.getByFieldDisplayName(entry.getKey()), entry.getValue());
			} catch (Exception e) {
				throw new Exception("Could not find or modify field '" + entry.getKey() + "'", e);
			}
		}
	}

	@Then("^the user clicks on (Email|Print|Download) advanced tab$")
	public void clicksOnEmailAdvancedTab(String deliveryOption) throws Throwable {
		email.waitTillAdvancedTabIsClickable();
		email.advancedTab().click();
	}

	@Then("^the 'Expanded Margin for Notes' is not displayed on the advanced tab$")
	public void theExpandedMarginForNotesIsNotDisplayedOnTheAdvancedTab() throws Throwable {
		try {
			assertThat(" Expanded Margin for Notes is displayed", email.expandedMarginForNotes().isDisplayed(), Is.is(false));
		} catch (TimeoutException te) {
			LOG.info("Expanded Margin for Notes is not displayed when delivering list of items");
		}
	}

	@Then("^the cover page comment textbox is displayed$")
	public void coverPageCommentIsDisplayed() throws Throwable {
		assertThat(email.coverPageComment().isDisplayed(), Is.is(true));
	}

	@Then("^an email is sent successfully by clicking on the Email button$")
	public void anEmailIsSentSuccessfullyByClickingOnTheEmailButton() throws Throwable {
		email.emailButton().click();
		email.waitForSuccessDeliveryMessage("The item will be emailed.");
	}

	@When("^an email with the list of resources is sent successfully by clicking on the Email button$")
	public void anEmailWithTheListOfResourcesIsSentSuccessfullyByClickingOnTheEmailButton() throws Throwable {
		email.emailButton().click();
		email.waitForSuccessDeliveryMessage("The items will be emailed.");
	}

	@When("^the user selects \"(.*?)\" as email format$")
	public void theUserSelectsAsEmailFormat(String emailFormat) throws Throwable {
		new Select(email.formatDropdown()).selectByVisibleText(emailFormat);
	}

	@Then("^the following options will not be displayed$")
	public void theFollowingOptionsWillNotBeDisplayed(List<String> options) throws Throwable {
		for (String option : options) {
			assertThat(option + " is displayed", formUtils.isNotDisplayed(DeliveryFormField.getByFieldDisplayName(option)), Is.is(true));
		}
	}

	@Then("^the \"(.*?)\" tab is not displayed$")
	public void advancedTabIsNotDisplayed(String label) throws Throwable {
		try {
			assertThat(label + " is displayed", formUtils.findElement(DeliveryFormField.getByFieldDisplayName(label)).isDisplayed(),
					Is.is(false));
		} catch (NoSuchElementException ne) {
			LOG.info("Basic tab is not visible when there is nothing to display in it");
		}
	}

	@Then("^the following options get disabled$")
	public void theFollowingOptionsGetDisabled(List<String> fields) throws Throwable {
		for (String field : fields) {
			assertThat(field + " is enabled", email.findElement(DeliveryFormField.getByFieldDisplayName(field).getBy()).isEnabled(),
					Is.is(false));
		}
	}

	@Then("^user closes the delivery box by clicking on the cancel button$")
	public void userClosesTheDeliveryBoxByClickingOnTheButton() throws Throwable {
		email.cancelButton().click();
	}

}