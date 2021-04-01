package com.thomsonreuters.step_definitions.folders;

import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.utils.folders.FoldersUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.When;

public class AbilityToCopyAndMoveFolder1 extends BaseStepDef {

    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private FoldersUtils foldersUtils = new FoldersUtils();
	
	@When("^the user copies \"(.*?)\" in \"(.*?)\" folder$")
	public void theUserCopiesInFolder(String sourceFolderName, String destinationFolderName) throws Throwable {
		foldersUtils.openFolder(sourceFolderName);
        researchOrganizerPage.optionsButton().click();
        researchOrganizerPage.optionsCopy().click();
        foldersUtils.copyFolder(destinationFolderName);
	}
	
	@When("^the user moves \"(.*?)\" in \"(.*?)\" folder$")
	public void theUserMovesInFolder(String sourceFolderName, String destinationFolderName) throws Throwable {
		foldersUtils.openFolder(sourceFolderName);
        researchOrganizerPage.optionsButton().click();
        researchOrganizerPage.optionsMove().click();
        foldersUtils.moveFolder(destinationFolderName);
	}
	
	@When("^the folder \"(.*?)\" disappears from \"(.*?)\" folder level$")
	public void theFolderDisappearsFromFolderLevel(String movedFolderName, String destinationFolderName) throws Throwable {
        if (researchOrganizerPage.isFolderAbsentOnSameLevelAsSpecifiedFolder(movedFolderName, destinationFolderName)) {
            throw new RuntimeException("Folder '" + movedFolderName + "' present on same level as '" + destinationFolderName + "'");
        }
	}
	
	

}
