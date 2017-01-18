package com.thomsonreuters.step_definitions.folders;

import com.thomsonreuters.pageobjects.pages.folders.ExportFolderPopup;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.utils.folders.FoldersUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class AbilityToExportFolder extends BaseStepDef {

    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private ExportFolderPopup exportFolderPopup = new ExportFolderPopup();
	private FoldersUtils foldersUtils = new FoldersUtils();

    @When("^the user selects option Export and chooses the \"(.*?)\" folder$")
	public void theUserExportFolder(String sourceFolderName) throws Throwable {
    	theUserSelectExport();
		foldersUtils.chooseFolderforExport(sourceFolderName);
		exportFolderPopup.next().click();
		exportFolderPopup.waitForPageToLoadAndJQueryProcessing();
	}
    
    @When("^the user chooses set of folders for Export$")
	public void theUserSeesExportWizardAndChooseFolder(List<String> folderNames) throws Throwable {
    	theUserSelectExport(); 
    	for (String folderName:folderNames) {
			foldersUtils.chooseFolderforExport(folderName);
		}
    	exportFolderPopup.next().click();
    	exportFolderPopup.waitForPageToLoadAndJQueryProcessing();
	}
    
    @When("^the user selects option Export$")
	public void theUserSelectExport() throws Throwable {
        researchOrganizerPage.optionsButton().click();
        researchOrganizerPage.optionsExport().click();
	}
    
    @When("^the user clicks the (Cancel|Back) button on Export Wizard$")
	public void theUserClicksCancel(String buttonName) throws Throwable {
    	switch (buttonName) {
		case "Cancel":
			exportFolderPopup.cancel().click();
			break;
		case "Back":
	        exportFolderPopup.back().click();
			break;
		}
    	exportFolderPopup.waitForPageToLoadAndJQueryProcessing();
	}
      
     
    @Then("^the user sees an Export wizard$")
	public void theUserSeesExportWizard() throws Throwable {
    	exportFolderPopup.waitForPageToLoadAndJQueryProcessing();
		assertTrue("The user doesn't see 'Export Wizard",
				exportFolderPopup.isElementDisplayed(exportFolderPopup.exportPopup()));
	}
    
    @Then("^the user doesn't see an Export wizard$")
	public void theUserDoesNotSeeExportWizard() throws Throwable {
    	exportFolderPopup.waitForPageToLoadAndJQueryProcessing();
		Assert.assertFalse("The user sees 'Export Wizard",
				exportFolderPopup.isExportPopupPresent());
	}
    
    
}
