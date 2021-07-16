package com.thomsonreuters.step_definitions.uk.assetPage;

import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.AssetDocumentPage;
import com.thomsonreuters.pageobjects.utils.plPlusResearchDocDisplay.AssetPageUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AssetPageTableOfContentStep extends BaseStepDef {

    AssetPageUtils assetPageUtils = new AssetPageUtils();
    AssetDocumentPage assetDocumentPage = new AssetDocumentPage();

    @Then("^the user see Table of content$")
    public void theUserSeeTableOfContent() throws Throwable {
        assertTrue("The user doesn't see table of content",
                assetPageUtils.isTableOfContentDisplayed());
    }

    @When("^the user click on \"(.*?)\" Table of content$")
    public void theUserClickOnTableOfContent(String tableOfContentText)
            throws Throwable {
        assetDocumentPage.tableOfContentButton(tableOfContentText).click();
    }

    @When("^the user click on Table of content Toggle$")
    public void userClickOnTableOfContent()
            throws Throwable {
        assetDocumentPage.tableOfContentToggleIcon().click();
    }

    @Then("^the Table of content will hide$")
    public void theTableOfContentWillHide() throws Throwable {
        assertFalse("The user see table of content",
                assetPageUtils.isTableOfContentDisplayed());
    }

}
