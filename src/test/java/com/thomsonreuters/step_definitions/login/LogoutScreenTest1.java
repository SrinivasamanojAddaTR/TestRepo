package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.onePass.OnePassLogoutPage;
import com.thomsonreuters.pageobjects.utils.OnepassLoginUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class LogoutScreenTest1 extends BaseStepDef {
	
    private OnepassLoginUtils onepassLoginUtils = new OnepassLoginUtils();
    private OnePassLogoutPage onePassLogoutPage = new OnePassLogoutPage();
    private CommonMethods commMethods = new CommonMethods();
	
	@Then("^the printed session details will include the start and end date and time of the user's session in timezone \"(.*?)\"$")
	public void thePrintedSessionDetailsWillIncludeTheStartAndEndDateAndTimeOfTheUserSSessionInTimezone(String timeZone) throws Throwable {
		assertTrue("Session summary date is not correct", onepassLoginUtils.isSessionSummaryTimeCorrect(timeZone, onePassLogoutPage.sessionSummary()));
	}

	@Then("^the following information will be listed for transaction: Event Type is \"(.*?)\", Description contains \"(.*?)\", Date/Time is in timezone \"(.*?)\" and Client ID is \"(.*?)\"$")
	public void theFollowingInformationWillBeListedForTransactionEventTypeIsDescriptionContainsDateTimeIsInTimezoneAndClientIDIs(String eventType, String description, String timeZone, String clientID) throws Throwable {
		SoftAssertions softly = new SoftAssertions();
		WebElement sessionDetailTable = onePassLogoutPage.sessionDetailsTable();
		String actualEvent  = commMethods.getTableCellByOtherColumnValueAndHeader(sessionDetailTable, "Event", eventType , "Event");
		String actualDescription = commMethods.getTableCellByOtherColumnValueAndHeader(sessionDetailTable, "Event", eventType , "Description");
		String actualDate = commMethods.getTableCellByOtherColumnValueAndHeader(sessionDetailTable, "Event", eventType , "Date/Time");
		String actualClientID = commMethods.getTableCellByOtherColumnValueAndHeader(sessionDetailTable, "Event", eventType , "Client ID");
		
		softly.assertThat(eventType.equalsIgnoreCase(actualEvent)).overridingErrorMessage("Expected event: <%s> but actual: <%s>", eventType, actualEvent).isTrue();
		softly.assertThat(actualDescription.contains(description))
				.overridingErrorMessage("Expected description: %s but actual: %s", description, actualDescription).isTrue();
		softly.assertThat(onepassLoginUtils.isSessionDetailTimeCorrect(timeZone,actualDate)).overridingErrorMessage("Event Date is not correct actual: <%s>", actualDate).isTrue();
		softly.assertThat(clientID.equalsIgnoreCase(actualClientID)).overridingErrorMessage("Expected clientId: <%s> but actual: <%s>", clientID, actualClientID).isTrue();
		softly.assertAll();
	}

}
