package com.thomsonreuters.step_definitions.header.responsive_design;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class FoldersAndFoldersDropDownTestSteps extends BaseStepDef {

    private WLNHeader header = new WLNHeader();
    private CommonMethods comMethods = new CommonMethods();

    @Then("^user should see \"(.*)\" link$")
    public void userShouldSeeLink(String linkText) throws Throwable {
        Assert.assertTrue(linkText+" link not displayed..!", comMethods.isLinkTextPresent(linkText,2000));
    }

    @Then("^user clicks on folders mainlink$")
    public void userClicksOnFoldersLink() throws Throwable {
        header.foldersLink().click();
    }

    @Then("^user should see folders page$")
    public void userShouldSeeFoldersPage() throws Throwable {
        Assert.assertTrue("Folders page not displayed..!", header.folderHeadingTitle().isDisplayed());
    }

    @Then("^user should see folders dropdown arrow$")
    public void userShouldSeeFoldersDropwdownArrow() throws Throwable {
        Assert.assertTrue("Folders header link not displayed..!", header.foldersLink().isDisplayed());
    }

    @Then("^user clicks on the folders dropdown arrow$")
    public void userhoversOnTheFodlersDropdownArrow() throws Throwable {
        header.foldersLink().click();
    }

    @Then("^user should see \"(\\d+)\" recent folders and view all link$")
    public void userShouldSeeRecentFolders(int noOfFolders) throws Throwable {
        Assert.assertTrue("Recent Folders not displayed..!", header.foldersRecentFoldersSubTitle().isDisplayed());
        Assert.assertTrue("View all Link not displayed..!", header.folderViewAllLink().isDisplayed());
        header.waitForPageToLoadAndJQueryProcessing();
        int noOfActualFolder = header.foldersRecentFoldersLinks().size();
        Assert.assertTrue(noOfFolders + " not displayed..!", noOfFolders == noOfActualFolder);
    }

}