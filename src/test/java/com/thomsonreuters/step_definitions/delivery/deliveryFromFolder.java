package com.thomsonreuters.step_definitions.delivery;

import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.step_definitions.annotations.AnnotationsStepDef;
import com.thomsonreuters.step_definitions.uk.folders.BaseFoldersBehaviour;
import cucumber.api.java.en.When;

import java.util.List;

public class deliveryFromFolder {

	 private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
	 private BaseFoldersBehaviour baseFoldersBehavior = new BaseFoldersBehaviour();
	private AnnotationsStepDef annotationsStepDef = new AnnotationsStepDef();
	 
	@When("^the user (opens|has) (.*?) folder with the set of documents$")
	public void theUserOpenFolder(String action, String folder, List<String> listOfGuid) throws Throwable {
		annotationsStepDef.theUserClicksOnLinkOnTheHeader("Folders");
       	if (!researchOrganizerPage.isFolderPresentInLeftFrame(folder, "root")){
       		baseFoldersBehavior.createFolderWithContent(folder, listOfGuid);
        } 
       	if (action.equals("opens")){
       		baseFoldersBehavior.openFolder(folder);
       	}
	}
	

    @When("^the user selects the checkbox associated with document \"([^\"]*)\"$")
    public void theUserSelectsTheCheckboxAssociatedWithResult(String arg1) throws Throwable {
    	researchOrganizerPage.documentCheckboxByNumber(arg1).click();
    }
    

	
}
