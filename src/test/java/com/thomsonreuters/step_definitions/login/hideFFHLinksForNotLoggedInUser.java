package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.search.SearchHomePage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class hideFFHLinksForNotLoggedInUser extends BaseStepDef {

    private SearchHomePage searchHomePage = new SearchHomePage();
    private CommonMethods comMethods = new CommonMethods();
    private WLNHeader wlnHeader = new WLNHeader();

    @When("^he does a search \"(.*?)\"$")
    public void heDoesASearch(String searchText) throws Throwable {
        searchHomePage.enterSearchText(searchText);
        searchHomePage.searchButton().click();
        searchHomePage.waitForPageToLoad();
    }

    @Then("^he does not see in the search results page any link related to FFH$")
    public void heDoesNotSeeInTheSearchResultsPageAnyLinkRelatedToFFH(List<String> ffhLinks) throws Throwable {
        checkIfLinksVisible(ffhLinks);
    }

    @When("^he looks at the header , no matter which page he is at$")
    public void heLooksAtTheHeaderNoMatterWhichPageHeIsAt() throws Throwable {
        assertTrue("Header is not visible", wlnHeader.header().isDisplayed());
    }

    @Then("^he does not see in the header any link related to FFH$")
    public void heDoesNotSeeInTheHeaderAnyLinkRelatedToFFH() throws Throwable {
        int result = 0;
        if (wlnHeader.isFavoritesLinkPresent()) {
            result++;
        }
        if (wlnHeader.isFoldersLinkPresent()) {
            result++;
        }
        if (wlnHeader.isHistoryLinkPresent()) {
            result++;
        }
        assertTrue("FFH is visible for user", result == 0);
    }

    @Then("^he does not see in the document page any link related to FFH$")
    public void heDoesNotSeeInTheDocumentPageAnyLinkRelatedToFFH(List<String> ffhLinks) throws Throwable {
        checkIfLinksVisible(ffhLinks);
    }

    private void checkIfLinksVisible(List<String> ffhLinks) {
        int result = 0;
        for (String ffhLink : ffhLinks) {
            if (comMethods.getElementByLinkText(ffhLink) != null) {
                LOG.info(ffhLink + " link is visvible for user");
                result++;
            }
        }
        assertTrue("FFH is visvible for user", result == 0);
    }

}