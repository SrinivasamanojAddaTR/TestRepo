package com.thomsonreuters.step_definitions.header;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.ListFunctions;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.onePass.OnePassLogoutPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.Assert.assertTrue;

public class AnzTimeZoneTest extends BaseStepDef {

    private WLNHeader header = new WLNHeader();
    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private ListFunctions listFunctions = new ListFunctions();
    private OnePassLogoutPage onePassLogoutPage = new OnePassLogoutPage();
    private CommonMethods commonMethods = new CommonMethods();
    private String storedDate = null;
    public static final int US_TIME_ZONE = -6;
    public static final int SYDNEY_TIME_ZONE = 9;

    @When("^the user opens time-zone popup using user dropdown$")
    public void theUserOpensTimeZonePopupUsingUserDropdown() throws Throwable {
        header.expandUserAvatarDropDown();
        header.waitForExpectedElement(By.linkText("Set time zone")).click();
    }

    @When("^user changes the time-zone to \"(.*?)\"$")
    public void userChangesTheTimeZoneTo(String country) throws Throwable {
        if (country.equalsIgnoreCase("US")) {
            listFunctions.SelectValueInList(header.timeZoneSelectDropdown(), "Central Standard Time");
        } else if (country.equalsIgnoreCase("London")) {
            listFunctions.SelectValueInList(header.timeZoneSelectDropdown(), "GMT Standard Time");
        } else if (country.equalsIgnoreCase("Sydney")) {
            listFunctions.SelectValueInList(header.timeZoneSelectDropdown(), "AUS Eastern Standard Time");
        }
        header.timeZoneSaveButton().click();
    }

    @When("^user navigates to the History Page to store the time at row \"(.*?)\"$")
    public void userNavigatesToTheHistoryPageToStoreTheTime(String rowNo) throws Throwable {
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(30);
        researchOrganizerPage.getElementByLinkText("History").click();
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.historyPageResultTitleLinks().get(0).isDisplayed();
        storedDate = researchOrganizerPage.getDateAtRowPosition(rowNo).getText();
    }

    @Then("^user verifies the time at row \"(.*?)\" on History page changes to \"(.*?)\" time$")
    public void userVerifiesResultTimeChangesToTime(String rowNo, String country) throws Throwable {
        String storedTime[] = storedDate.split(" ");
        Calendar storedCalTime = commonMethods.convertInCalendar(storedTime[3]);
        String currentDate = researchOrganizerPage.getDateAtRowPosition(rowNo).getText();
        String currentTime[] = currentDate.split(" ");
        Calendar currentCalTime = commonMethods.convertInCalendar(currentTime[3]);
        if (country.equalsIgnoreCase("US")) {
            storedCalTime.add(Calendar.HOUR, US_TIME_ZONE);
        } else if (country.equalsIgnoreCase("Sydney")) {
            storedCalTime.add(Calendar.HOUR, SYDNEY_TIME_ZONE);
        }
        assertTrue(
                "Current Time is not equal to stored time: current hour is " + currentCalTime.get(Calendar.HOUR_OF_DAY) + " while stored hour is " + storedCalTime.get(Calendar.HOUR_OF_DAY),
                currentCalTime.get(Calendar.HOUR_OF_DAY) == storedCalTime.get(Calendar.HOUR_OF_DAY));
    }

    @Then("^user verifies the sign out time is according to \"(.*?)\" time-zone$")
    public void userVerifiesTheSignOutTimeIsAccordingToTimeZone(String country) throws Throwable {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat amPMFormat = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");
        String signOffDateTime = onePassLogoutPage.sessionDateAndTime().getText();
        String signOffTime[] = signOffDateTime.split(" ");
        signOffDateTime = date24Format.format(amPMFormat.parse(signOffTime[7] + " " + signOffTime[8]));
        Calendar signOffCalTime = commonMethods.convertInCalendar(signOffDateTime);
        if (country.equalsIgnoreCase("US")) {
            cal.add(Calendar.HOUR, -6);
        } else if (country.equalsIgnoreCase("Sydney")) {
            cal.add(Calendar.HOUR, 10);
        }

        assertTrue("Current Time is not equal to stored time..!", cal.get(Calendar.HOUR_OF_DAY) == signOffCalTime.get(Calendar.HOUR_OF_DAY));
    }
}
