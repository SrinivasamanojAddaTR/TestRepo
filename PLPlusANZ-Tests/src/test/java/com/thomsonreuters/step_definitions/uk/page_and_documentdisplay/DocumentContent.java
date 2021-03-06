package com.thomsonreuters.step_definitions.uk.page_and_documentdisplay;

import com.thomsonreuters.pageobjects.other_pages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.urls.plcuk.KHDocumentPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DocumentContent extends BaseStepDef {

    private NavigationCobalt navigationCobalt = new NavigationCobalt();
    private KHDocumentPage documentPagePLCUK = new KHDocumentPage();

    @When("^the user opens document with (.+) guid$")
    public void theUserOpensDocumentWithGuid(String guid) throws Throwable {
        navigationCobalt.navigateToPLUKPlus("/Document/" + guid + "/View/FullText.html");
        documentPagePLCUK.waitForPageToLoad();
    }

    @Then("^the document opens correctly$")
    public void theDocumentOpensCorrectly() throws Throwable {
        assertTrue("Document not present", documentPagePLCUK.isDocumentBlockPresent());
    }

    @Then("^the document contains images$")
    public void theDocumentContainsImages() throws Throwable {
        assertTrue("Document doesn't contain images", documentPagePLCUK.isDocumentContainImages());
    }

    @Then("^the user see (.+) images on document$")
    public void theDocumentContainsNumberOfImages(int quantityOfImages) throws Throwable {
        assertEquals("Document doesn't contain " + quantityOfImages + " images",
                quantityOfImages, documentPagePLCUK.getAllImages().size());
    }

}