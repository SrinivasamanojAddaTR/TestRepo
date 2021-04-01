package com.thomsonreuters.step_definitions.header.responsiveDesign;

import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.GlossaryPage;
import com.thomsonreuters.pageobjects.pages.plcLegacy.PLCLegacyBooksPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.Transpose;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BrowseMenuTest extends BaseStepDef {

    private WLNHeader header = new WLNHeader();
    private GlossaryPage glossaryPage = new GlossaryPage();
    private PLCLegacyBooksPage plcLegacyBooksPage = new PLCLegacyBooksPage();
    private String currentSection;

    @Given("^user clicks on \"(.*?)\" dropdown$")
    public void userClicksOnDropdown(String arg1) throws Throwable {
        header.waitForPageToLoad();
        header.waitForPageToLoadAndJQueryProcessing();
        header.browseMenuButton().click();
    }

    @Then("^user should see the \"(.*?)\" button arrow and hover behavior according to design document$")
    public void userShouldSeeTheButtonArrowAndHoverBehaviorAccordingToDesignDocument(String arg1) throws Throwable {
        assertTrue("Popup is not open..!", header.browseMenuPopup().isDisplayed());
    }

    @Then("^user should see the \"(.*?)\" popup with Practice Area and Resources sub-menu$")
    public void userShouldSeeThePopupWithPracticeAreaAndResourcesSubMenu(String arg1) throws Throwable {
        assertTrue("Practice Area is not displayed..!", header.browseMenuSubMenuList().get(0).isDisplayed());
        assertTrue("Resources is not displayed..!", header.browseMenuSubMenuList().get(1).isDisplayed());
    }

    @Then("^user clicks on following sub-menu and see the respective links according to the design$")
    public void userClicksOnSubMenuAndSeeTheRespectiveLinksAccordingToTheDesign(List<String> subMenuList) throws Throwable {
        for (int linkCount = 0; linkCount < subMenuList.size(); linkCount++) {
            header.getElementByLinkText(subMenuList.get(linkCount)).click();
            if (subMenuList.get(linkCount).trim().equalsIgnoreCase("Australia Practice areas")) {
                assertTrue("Practice Area Links are not displayed..!", header.practiceAreaFirstColumnLinks().get(0).isDisplayed());
            } else if (subMenuList.get(linkCount).trim().equalsIgnoreCase("Australia Resources")) {
                assertTrue("Resources Links are not displayed..!", header.resourcesFirstColumnLinks().get(0).isDisplayed());
            }
        }
    }

    @Then("^user clicks on sub-menu \"(.*?)\" and see the respective links$")
    public void userClicksOnSubMenuAndSeeTheRespectiveLinks(String arg1) throws Throwable {
        header.browseMenuSubMenuList().get(1).click();
    }

    @Then("^user selects sub-menu \"(.*)\" and clicks on the link \"(.*)\"$") // Maybe @When should be there?
    public void userClicksOnSubMenuAndSeeTheRespectiveLinks(String subMenuLink, String linkText) throws Throwable {
        currentSection = subMenuLink;
        header.getElementByLinkText(subMenuLink).click();
        header.waitForPageToLoad();
        header.getElementByLinkText(linkText).click();
    }

    @When("^user selects sub-menu '(.*)'$")
    public void userClicksOnSubMenu(String subMenuLink) throws Throwable {
        currentSection = subMenuLink;
        header.waitForPageToLoad();
        header.getElementByLinkText(subMenuLink).click();
    }

    @Then("^the following links should be present on the section$")
    public void sectionLinksIsPresent(@Transpose List<String> linkNames) {
        List<WebElement> sectionLinks = header.getActiveSectionLinks();
        List<String> actualLinkNames = new ArrayList<>();
        for (WebElement tabLink : sectionLinks) {
            actualLinkNames.add(tabLink.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (String linkName : linkNames) {
            softAssertions.assertThat(actualLinkNames)
                    .withFailMessage("Link '" + linkName + "' is not present")
                    .contains(linkName);
        }
        softAssertions.assertAll();
    }

    @Then("the following links should be present on the section and every link opens page with selected resource")
    public void sectionLinksIsPresentAndOpensProperly(@Transpose List<String> linkNames) throws Throwable {
        sectionLinksIsPresent(linkNames);
        SoftAssertions softAssertions = new SoftAssertions();
        String placeToCheck; // Where we will check presence of expected page name
        for (String linkName : linkNames) {
            userClicksOnSubMenuAndSeeTheRespectiveLinks(currentSection, linkName);
            header.waitForPageToLoad();
            header.waitForPageToLoadAndJQueryProcessing();
            if (linkName.contains("Glossary")) { // On "Glossary" link open non-standard PA page
                placeToCheck = glossaryPage.glossaryHeading().getText();
            } else if (linkName.contains("book")) { // On "Book" link open Global PL page
                linkName = "Book"; // IMPORTANT! linkName redefined here
                placeToCheck = plcLegacyBooksPage.getPageHead().getText();
            } else { // In all other cases regular page with selected PA section is opened
                placeToCheck = header.pageHeaderLabel().getText();
            }
            header.browserGoBack();
            softAssertions.assertThat(placeToCheck)
                    .withFailMessage("Page '" + linkName + "' is not opened")
                    .contains(linkName);
            header.waitForPageToLoad();
            userClicksOnDropdown("Browse Menu");
        }
        softAssertions.assertAll();
    }

}