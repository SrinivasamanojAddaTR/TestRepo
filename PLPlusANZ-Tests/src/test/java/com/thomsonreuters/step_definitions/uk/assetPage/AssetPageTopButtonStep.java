package com.thomsonreuters.step_definitions.uk.assetPage;

import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.AssetDocumentPage;
import com.thomsonreuters.pageobjects.utils.plPlusResearchDocDisplay.AssetPageUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;

import static org.junit.Assert.assertTrue;

public class AssetPageTopButtonStep extends BaseStepDef {

    private AssetDocumentPage assetDocumentPage = new AssetDocumentPage();
    private AssetPageUtils assetPageUtils = new AssetPageUtils();

    @When("^the user scrolled to the bottom of the document$")
    public void theUserScrolledToTheBottomOfTheDocument() throws Throwable {
        assetPageUtils.scrollToTheBottomOfTheDocument();
    }

    @Then("^the user see Top button$")
    public void theUserSeeTopButton() throws Throwable {
        assertTrue("The user doesn't see jump links in the left hand navigation panel",
                assetDocumentPage.isElementDisplayed(assetDocumentPage.topButton()));
    }

    @When("^the user click on Top button$")
    public void theUserClickOnTopButton() throws Throwable {
        assetDocumentPage.topButton().click();
    }

    @Then("^the user scrolled to the top of the document$")
    public void theUserScrolledToTheTopOfTheDocument() throws Throwable {
        assertTrue("The user doesn't scrolled to the top of the document",
                assetDocumentPage.isElementDisplayed(assetDocumentPage.partyNames()));
    }

    @Then("^apply document settings$")
    public void applyDocumentSettings() throws Throwable {
        assetPageUtils.addTextToTheDocumentPage();
        JavascriptExecutor e = (JavascriptExecutor) getDriver();
        e.executeScript("$('#co_backToTop').css('display', 'block');");
    }

}