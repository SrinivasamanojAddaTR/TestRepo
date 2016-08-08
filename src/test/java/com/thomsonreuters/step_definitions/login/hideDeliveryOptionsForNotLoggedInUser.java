package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.pages.legalUpdates.LegalUpdatesResultsPage;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.DocumentDeliveryOptionsPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;

import static org.junit.Assert.assertFalse;

public class hideDeliveryOptionsForNotLoggedInUser extends BaseStepDef {

    private DocumentDeliveryOptionsPage documentDeliveryOptionsPage = new DocumentDeliveryOptionsPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private LegalUpdatesResultsPage legalUpdatesResultsPage = new LegalUpdatesResultsPage();

    @Then("^he does not see in the document page any link related to delivery options \\(email, download, print\\)$")
    public void heDoesNotSeeInTheDocumentPageAnyLinkRelatedToDeliveryOptionsEmailDownloadPrint() throws Throwable {
        assertFalse("Delivery options are visible for user", documentDeliveryOptionsPage.isDownloadPresent() & documentDeliveryOptionsPage.isPrintPresent() & documentDeliveryOptionsPage.isEmailPresent());
    }

    @Then("^he is not able to use these features on document page$")
    public void heIsNotAbleToUseTheseFeaturesOnDocumentPage() throws Throwable {
        try {
            documentDeliveryOptionsPage.download().click();
			Assert.fail("User is able to click on download");
        } catch (PageOperationException poe) {
        }
        try {
            documentDeliveryOptionsPage.print().click();
			Assert.fail("User is able to click on print");
        } catch (PageOperationException poe) {
        }
        try {
            documentDeliveryOptionsPage.email().click();
			Assert.fail("User is able to click on email");
        } catch (PageOperationException poe) {
        }
    }

    @Then("^he does not see in the search results page any link related to delivery options \\(email, download, print\\)$")
    public void heDoesNotSeeInTheSearchResultsPageAnyLinkRelatedToDeliveryOptionsEmailDownloadPrint() throws Throwable {
        assertFalse("Delivery options are visible for user", searchResultsPage.isDeliveryDropButtonPresent() & searchResultsPage.isDownloadDeliveryOptionPresent() & searchResultsPage.isPrintDeliveryOptionPresent() & searchResultsPage.isEmailDeliveryOptionPresent());
    }

    @Then("^he is not able to use these features on search page$")
    public void heIsNotAbleToUseTheseFeaturesOnSearchPage() throws Throwable {
        boolean isUserAbleToUseDeliveryOptions = true;
        try {
            searchResultsPage.deliveryDropButton().click();
        } catch (TimeoutException ex) {
            isUserAbleToUseDeliveryOptions = false;
        }
        assertFalse("User is not able to use delivery options", isUserAbleToUseDeliveryOptions);
    }

    @Then("^he does not see on a legal updates results page any link related to delivery options \\(email, download, print\\)$")
    public void heDoesNotSeeOnALegalUpdatesResultsPageAnyLinkRelatedToDeliveryOptionsEmailDownloadPrint() throws Throwable {
        assertFalse("Delivery options are visible for user", legalUpdatesResultsPage.isDeliveryMethodLinkPresent() & searchResultsPage.isDownloadDeliveryOptionPresent() & searchResultsPage.isPrintDeliveryOptionPresent() & searchResultsPage.isEmailDeliveryOptionPresent());
    }

    @Then("^he is not able to use these features on legal updates results page$")
    public void heIsNotAbleToUseTheseFeaturesOnLegalUpdatesResultsPage() throws Throwable {
        boolean isUserAbleToUseDeliveryOptions = true;
        try {
            legalUpdatesResultsPage.deliveryMethodLink().click();
        } catch (PageOperationException poe) {
            isUserAbleToUseDeliveryOptions = false;
        }
        assertFalse("User is not able to use delivery options", isUserAbleToUseDeliveryOptions);
    }

}