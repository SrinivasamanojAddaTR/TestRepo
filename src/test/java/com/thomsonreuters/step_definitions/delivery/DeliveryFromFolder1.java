package com.thomsonreuters.step_definitions.delivery;

import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.utils.folders.FoldersUtils;
import com.thomsonreuters.step_definitions.annotations.AnnotationsStepDef;
import cucumber.api.java.en.When;

import java.util.List;

public class DeliveryFromFolder1 {

    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private AnnotationsStepDef annotationsStepDef = new AnnotationsStepDef();
    private FoldersUtils foldersUtils = new FoldersUtils();

    @When("^the user (opens|has) (.*?) folder with the set of documents$")
    public void theUserOpenFolder(String action, String folder, List<String> listOfGuid) throws Throwable {
        annotationsStepDef.theUserClicksOnLinkOnTheHeader("Folders");
        if (!researchOrganizerPage.isFolderPresentInLeftFrame(folder, "root")) {
            foldersUtils.createFolderWithContent(folder, listOfGuid);
        }
        if (action.equals("opens")) {
            foldersUtils.openFolder(folder);
        }
        researchOrganizerPage.waitForPageToLoad();
    }


    @When("^the user selects the checkbox associated with document \"([^\"]*)\"$")
    public void theUserSelectsTheCheckboxAssociatedWithResult(String arg1) throws Throwable {
        researchOrganizerPage.documentCheckboxByNumber(arg1).click();
    }


}
