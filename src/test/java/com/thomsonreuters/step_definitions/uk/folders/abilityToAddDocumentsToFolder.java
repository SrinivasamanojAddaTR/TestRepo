package com.thomsonreuters.step_definitions.uk.folders;

import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.utils.folders.FoldersUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.NoSuchElementException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AbilityToAddDocumentsToFolder extends BaseStepDef {

	private SearchResultsPage searchResultsPage = new SearchResultsPage();
	private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private FoldersUtils foldersUtils = new FoldersUtils();
	private Map<String,List<String>> mapOfValues = new HashMap<>();
	private int documentCount;

	@When("^the user selects '(.+)' documents, stores its titles and guids and saves to \"([^\"]*)\" folder$")
	public void selectDocumentsAndSave(String count, String folder) throws Throwable {
		mapOfValues = foldersUtils.selectDocuments(count);
		searchResultsPage.saveToFolder().click();
		String folderName = foldersUtils.saveToFolder(folder);
		searchResultsPage.waitForPageToLoad();
		String message = searchResultsPage.folderingPopupMessage().getText();
		LOG.info(message);
		if (Integer.valueOf(count) > 1) {
			assertEquals("Message is incorrect", count + " of " + count + " items saved to '" + folderName + "'.", message);
		} else {
			assertEquals("Message is incorrect", mapOfValues.get("titles").get(0) + " saved to '" + folderName + "'.", message);
		}
	}

	@When("^the user selects '(.+)' documents, stores its titles and guids and saves to new \"([^\"]*)\" folder with parent folder \"([^\"]*)\"$")
	public void selectDocumentsAndSaveToNewFolder(String count, String folder, String parentFolder) throws Throwable {
		mapOfValues = foldersUtils.selectDocuments(count);
		documentCount = Integer.parseInt(count);
		searchResultsPage.saveToFolder().click();
		foldersUtils.saveToNewFolder(folder, parentFolder);
		searchResultsPage.waitForPageToLoad();
		searchResultsPage.waitForPageToLoadAndJQueryProcessing();
		String message = searchResultsPage.folderingPopupMessage().getText();
		LOG.info(message);
		if (Integer.valueOf(count) > 1) {
			assertEquals("Message is incorrect", count + " of " + count + " items saved to '" + folder + "'.", message);
		} else {
			assertEquals("Message is incorrect", mapOfValues.get("titles").get(0) + " saved to '" + folder + "'.", message);
		}
	}
	
	@Then("^the user selects '(.+)' documents and checks \"([^\"]*)\" folder is absent in root folder$")
	public void selectDocumentsAndChecksFolderAbsent(String count, String folder) throws Throwable {
		foldersUtils.selectDocuments(count);
		searchResultsPage.saveToFolder().click();
		foldersUtils.checksFolderAbsent(folder);
	}

	@Then("^all documents present in the \"([^\"]*)\" folder$")
	public void checkDocuments(String folder) throws Throwable {
		StringBuffer error = new StringBuffer();
		foldersUtils.openFolder(folder);
		researchOrganizerPage.waitForPageToLoad();
		for (int i = 1; i <= documentCount; i++) {
			String guid = mapOfValues.get("guids").get(i - 1);
			String documentName = mapOfValues.get("titles").get(i - 1);
			LOG.info("Check document with name '" + documentName + "' presents");
			try {
				researchOrganizerPage.linkToDocument(guid, documentName).isDisplayed();
			} catch (NoSuchElementException e) {
				error.append("Document is '" + documentName + "' absent\n");
			}
		}
		if (error.length() > 0) {
			throw new RuntimeException(error.toString());
		}
	}

}
