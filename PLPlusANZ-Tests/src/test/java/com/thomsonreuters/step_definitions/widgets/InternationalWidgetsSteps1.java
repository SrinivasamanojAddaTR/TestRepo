package com.thomsonreuters.step_definitions.widgets;

import com.thomsonreuters.pageobjects.pages.widgets.InternationalWidgetsPanel;
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InternationalWidgetsSteps1 extends BaseStepDef {

	private InternationalWidgetsPanel widgets = new InternationalWidgetsPanel();
    private FooterUtils footerUtils = new FooterUtils();

	@Then("^the following widgets should(| not) be displayed$")
	public void theFollowingWidgetsShouldBeDisplayed(String not, DataTable dataTable) {
		List<String> titles = dataTable.asList(String.class);
		SoftAssertions softly = new SoftAssertions();
		for (String title : titles) {
			if (not.contains("not")) {
				softly.assertThat(widgets.isWidgetWithTitlePresent(title)).overridingErrorMessage("Widget with title %s is present", title).isFalse();
			} else {
				softly.assertThat(widgets.isWidgetWithTitlePresent(title)).overridingErrorMessage("Widget with title %s is not present", title).isTrue();
			}
		}
		softly.assertAll();
	}

	@When("^the user clicks on button \"(.*)\" on widget \"(.*)\"$")
	public void theUserClicksOnButtonOnWidget(String buttonText, String widgetTitle) {
        footerUtils.closeDisclaimerMessage();
		widgets.waitForPageToLoadAndJQueryProcessing();
		widgets.buttonOnWidgetWithTitle(widgetTitle, buttonText).click();
	}

	@Then("^the user can see the button \"(.*)\" on widget \"(.*)\"$")
	public void theUserCanSeeButtonOnWidget(String buttonText, String widgetTitle) {
		WebElement button = widgets.buttonOnWidgetWithTitle(widgetTitle, buttonText);
		Assert.assertNotNull(String.format("Button '%s' not present on widget '%s'", buttonText, widgetTitle), button);
	}

	@Then("^the text on widget \"(.*)\" is \"(.*)\"$")
	public void theTextOnWidgetIs(String widgetTitle, String widgetText) {
		String actualText = widgets.textOnWidgetWithTitle(widgetTitle);
		Assert.assertEquals("Text on widget '" + widgetTitle + "' is different", widgetText.trim(), actualText.trim());
	}
}
