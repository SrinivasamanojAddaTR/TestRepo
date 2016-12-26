package com.thomsonreuters.step_definitions.header.responsiveDesign;

import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawHomepage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class HistoryAndHistoryDropDownTest extends BaseStepDef {

    private WLNHeader header = new WLNHeader();
    private PracticalLawHomepage plcHomePage = new PracticalLawHomepage();

    @Then("^user should see history link$")
    public void userShouldSeeHitoryLink() throws Throwable {
        Assert.assertTrue("History link not displayed..!", header.historyLink().isDisplayed());
    }

    @Then("^user clicks on history mainlink$")
    public void userClicksOnHistoryLink() throws Throwable {
        header.historyLink().click();
    }

    @Then("^user should see history page$")
    public void userShouldSeeHistoryPage() throws Throwable {
        Assert.assertTrue("History page not displayed..!", header.historyHeadingTitle().isDisplayed());
    }

    @Then("^user should see View all link and recent documents and searches results and labels$")
    public void userShouldSeeRecentDocumentsAndSearches() throws Throwable {
        Assert.assertTrue("Recent Searches not displayed..!", header.historyRecentSearchesSubTitle().isDisplayed());
        Assert.assertTrue("Recent Docs not displayed..!", header.historyRecentDocSubTitle().isDisplayed());
        Assert.assertTrue("Recent Docs View All not displayed..!", header.historyDocViewAllLink().isDisplayed());
    }

    @Then("^user clicks on recent docs view all links and should see history searches$")
    public void userClicksOnRecentSearchesViewAllLinksAndShouldSeeHistorySearches() throws Throwable {
        header.historyDocViewAllLink().click();
        header.waitForElementVisible(header.historyHeadingTitle(), 3000);
        Assert.assertTrue("History Documents page not displayed..!", header.historyHeadingTitle().getText().contains("Documents"));
    }

    @Then("^user clicks on recent searches view all links and should see history searches$")
    public void userClicksOnRecentDocsViewAllLinksAndShouldSeeHistorySearches() throws Throwable {
        plcHomePage.waitForPageToLoadAndJQueryProcessing();
        header.historyArrowLink().click();
        header.waitForPageToLoad();
        header.historySearchViewAllLink().click();
        Assert.assertTrue("History Searches page not displayed..!", header.historyHeadingTitle().getText().contains("Searches"));
    }

}