package com.thomsonreuters.step_definitions.uk.folders;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.DocumentColumn;
import com.thomsonreuters.pageobjects.common.SortOptions;
import com.thomsonreuters.pageobjects.pages.folders.*;
import com.thomsonreuters.pageobjects.utils.folders.FoldersUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.step_definitions.annotations.AnnotationsStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class BaseFoldersBehaviour extends BaseStepDef {

    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private CommonMethods comMethods = new CommonMethods();
    private FoldersUtils foldersUtils = new FoldersUtils();
    private AnnotationsStepDef annotationsStepDef = new AnnotationsStepDef();

    private String folderName;


    @When("^the user clicks on '(.+)' tab$")
    public void theUserClicksOnTab(String tabName) throws Throwable {
        switch (tabName) {
            case "Folders":
                researchOrganizerPage.foldersTab().click();
                break;
            case "History":
                researchOrganizerPage.historyTab().click();
                break;
            default:
        }
        researchOrganizerPage.waitForPageToLoad();
    }

    @Then("^'(.+)' page opens$")
    public void pageOpens(String pageName) throws Throwable {
        switch (pageName) {
            case "Folders":
                String actualFoldersClassAttributeValue = researchOrganizerPage.foldersTab().getAttribute("class");
                assertEquals("Folders tab is not active", researchOrganizerPage.getExpectedClassAttributeValueForTabs(),
                        actualFoldersClassAttributeValue);
                break;
            case "History":
                String actualHistoryClassAttributeValue = researchOrganizerPage.historyTab().getAttribute("class");
                assertEquals("History tab is not active", researchOrganizerPage.getExpectedClassAttributeValueForTabs(),
                        actualHistoryClassAttributeValue);
                break;
            default:
        }
    }

    @When("^the user selects '(.+)' Type$")
    public void selectType(String type) throws Throwable {
        researchOrganizerPage.facetedViewSelectType(type).click();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user selects '(.+)' Client ID$")
    public void selectClientID(String clientID) throws Throwable {
        researchOrganizerPage.facetedViewSelectClientID(clientID).click();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user selects '(.+)' Content type$")
    public void selectContentTypes(String contentType) throws Throwable {
        researchOrganizerPage.facetedViewSelectContentType(contentType).click();
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user clicks Select Multiple Filters$")
    public void multipleFilters() throws Throwable {
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        researchOrganizerPage.selectMultipleFilters1();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user clicks Cancel Filters$")
    public void cancelFilters() throws Throwable {
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        WebElement cancel = researchOrganizerPage.cancelFilters1();
        comMethods.mouseOver(cancel);
        cancel.click();
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user clicks Apply Filters$")
    public void applyFilters() throws Throwable {
        researchOrganizerPage.applyFilters1().click();
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^there is no root folder duplication in recent folders drop down$")
    public void checkRootFolderDuplicationAbsentInRecentFoldersDropdown() throws Throwable {
        researchOrganizerPage.waitForPageToLoad();
        comMethods.mouseOver(researchOrganizerPage.recentFoldersDropdown());
        int rootFolderCount = researchOrganizerPage.getRootFolderCountInRecentFoldersDropdown();
        if (rootFolderCount > 1) {
            throw new RuntimeException("There is root folder duplication in recent folders");
        }
    }

    @Then("^there is no the \"([^\"]*)\" folder in recent folders drop down$")
    public void checkFolderAbsentInRecentFoldersDropdown(String folderName) throws Throwable {
        researchOrganizerPage.waitForPageToLoad();
        comMethods.mouseOver(researchOrganizerPage.recentFoldersDropdown());
        try {
            researchOrganizerPage.linkToFolderInRecentDropdown(folderName).isDisplayed();
        } catch (NoSuchElementException e) {
            LOG.info("Element link to folder in recent dropdown was not found");
        }
        throw new RuntimeException("Folder '" + folderName + "' is present in recent folders drop down");
    }

    @Then("^all the folders listed$")
    public void checkThatFoldersPresent(List<String> folderNames) {
        for (String folderName : folderNames) {
            assertTrue("Folder '" + folderName + "' is abdsent.", researchOrganizerPage.isFolderPresent(folderName));
        }
    }

    @When("^the user goes to previous folder \"([^\"]*)\"$")
    public void openFolder(String folderName) {
        foldersUtils.openFolder(folderName);
        this.folderName = folderName;
    }

    @When("^the user goes to folder '(.*)'$")
    public void theUserGoesToFolderSubFolder(String folderName) throws Throwable {
        annotationsStepDef.theUserClicksOnLinkOnTheHeader("Folders");
        openFolder(folderName);
    }

    @Then("^user should see the selected folder page with multiple documents$")
    public void checkThatFolderOpenedAndItContainsTwoOrMoreDocs() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(researchOrganizerPage.getOpenedFolderTitle()).isEqualTo(folderName)
                .withFailMessage("Selected folder does not opened");
        softAssertions.assertThat(researchOrganizerPage.getDocumentsDataInColumn(DocumentColumn.TITLE).size()).isGreaterThan(1)
                .withFailMessage("Selected folder '" + folderName + "' does not contains multiple document");
        softAssertions.assertAll();
    }

    @When("^the user clicks on the '([^\"]*)' column$")
    public void clickOnColumn(String columnName) {
        researchOrganizerPage.getColumn(DocumentColumn.getByName(columnName)).click();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user clicks on the '([^\"]*)' column again$")
    public void clickOnColumnAgain(String columnName) {
        clickOnColumn(columnName);
    }

    @Then("^the rows sorted by '([^\"]*)' column in ascending$")
    public void checkColumnSortingAscBy(String columnName) {
        checkThatFolderOpenedAndItContainsTwoOrMoreDocs();
        DocumentColumn documentColumn = DocumentColumn.getByName(columnName);
        Assert.assertTrue("Documents not sorted by value in column '" + documentColumn.getName() + "', ascending",
                foldersUtils.isDocSortedBy(documentColumn, SortOptions.ASC));
    }

    @Then("^the rows sorted by '([^\"]*)' column in descending$")
    public void checkColumnSortingDescBy(String columnName) {
        DocumentColumn documentColumn = DocumentColumn.getByName(columnName);
        Assert.assertTrue("Documents not sorted by value in column '" + documentColumn.getName() + "', descending",
                foldersUtils.isDocSortedBy(documentColumn, SortOptions.DESC));
    }
}