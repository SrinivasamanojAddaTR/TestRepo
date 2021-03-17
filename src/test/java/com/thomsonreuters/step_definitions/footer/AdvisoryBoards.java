package com.thomsonreuters.step_definitions.footer;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.pages.footer.AdvisoryBoardsPage;
import com.thomsonreuters.pageobjects.utils.homepage.HomePageUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;

import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdvisoryBoards extends BaseStepDef {

    private AdvisoryBoardsPage consultationBoardsPage = new AdvisoryBoardsPage();
    private List<String> paNamesFromList;
    private HomePageUtils homeUtils = new HomePageUtils();

    @Then("^there are following Practice Area listed on (?:Consultation Boards|Advisory Boards)$")
    public void theUserVerifiesPAinList(List<String> paNames) throws Throwable {
        paNamesFromList = homeUtils.getPAnamesFromList();
        assertTrue("the list practice areas not include all PA", paNamesFromList.equals(paNames));
    }

    @Then("^the user selects a practice area \"(.*?)\" within the list on Consultation Boards$")
    public void theUserSelectsAPracticeAreaWithinList(String paName) throws Throwable {
        consultationBoardsPage.specifiedPracticeArea(paName).click();
        consultationBoardsPage.waitForPageToLoad();
    }

    @Then("^the user verifies that following text is present on (?:Consultation Boards|Advisory Boards) page$")
    public void theUserVerifiesText(String text) throws Throwable {
        consultationBoardsPage.waitForPageToLoad();
        assertTrue("following text is not present on page", consultationBoardsPage.textOnTop().getText().equals(text));
    }

    @Then("^the table on (?:Consultation Boards|Advisory Boards) page includes following people")
    public void theUserVerifiesColumnsInTable(List<String> columnNames) throws Throwable {
        List<String> advisoryBoardPeople = homeUtils.getPeopleNamesFromAdvisoryBoard();
        assertTrue("Expected: " + columnNames + ", actual: " + advisoryBoardPeople, advisoryBoardPeople.containsAll(columnNames));
    }

    @Then("^the table of contents on (?:Consultation Boards|Advisory Boards) page is hidden$")
    public void theTableOfContentsIsHide() throws Throwable {
        consultationBoardsPage.waitForPageToLoad();
        try {
            assertFalse("The user see table of content", consultationBoardsPage.isElementDisplayed(consultationBoardsPage.tableOfContentSection()));
        } catch (PageOperationException poe) {
            LOG.info("Element table of content section was not found", poe);
        }
    }
}