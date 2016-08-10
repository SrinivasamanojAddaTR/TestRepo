package com.thomsonreuters.step_definitions.uk.folders;

import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.utils.folders.FoldersUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class abilityToAddDocumentsToFolder extends BaseStepDef {

	private SearchResultsPage searchResultsPage = new SearchResultsPage();
	private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
	private BaseFoldersBehaviour baseFoldersBehavior = new BaseFoldersBehaviour();
    private FoldersUtils foldersUtils = new FoldersUtils();

	private List<String> guids;
	private List<String> titles;
	private int documentCount;

	@When("^the user selects '(.+)' documents, stores its titles and guids and saves to \"([^\"]*)\" folder$")
	public void selectDocumentsAndSave(String count, String folder) throws Throwable {
		selectDocuments(count);
		searchResultsPage.saveToFolder().click();
		String folderName = baseFoldersBehavior.saveToFolder(folder);
		searchResultsPage.waitForPageToLoad();
		String message = searchResultsPage.folderingPopupMessage().getText();
		LOG.info(message);
		if (Integer.valueOf(count) > 1) {
			assertEquals("Message is incorrect", count + " of " + count + " items saved to '" + folderName + "'.", message);
		} else {
			assertEquals("Message is incorrect", titles.get(0) + " saved to '" + folderName + "'.", message);
		}
	}

	@When("^the user selects '(.+)' documents, stores its titles and guids and saves to new \"([^\"]*)\" folder with parent folder \"([^\"]*)\"$")
	public void selectDocumentsAndSaveToNewFolder(String count, String folder, String parentFolder) throws Throwable {
		selectDocuments(count);
		searchResultsPage.saveToFolder().click();
		baseFoldersBehavior.saveToNewFolder(folder, parentFolder);
		searchResultsPage.waitForPageToLoad();
		searchResultsPage.waitForPageToLoadAndJQueryProcessing();
		String message = searchResultsPage.folderingPopupMessage().getText();
		LOG.info(message);
		if (Integer.valueOf(count) > 1) {
			assertEquals("Message is incorrect", count + " of " + count + " items saved to '" + folder + "'.", message);
		} else {
			assertEquals("Message is incorrect", titles.get(0) + " saved to '" + folder + "'.", message);
		}
	}
	
	@Then("^the user selects '(.+)' documents and checks \"([^\"]*)\" folder is absent in root folder$")
	public void selectDocumentsAndChecksFolderAbsent(String count, String folder) throws Throwable {
		selectDocuments(count);
		searchResultsPage.saveToFolder().click();
		baseFoldersBehavior.checksFolderAbsent(folder);
	}

	private void selectDocuments(String count) {
		documentCount = Integer.parseInt(count);
		guids = new ArrayList<>();
		titles = new ArrayList<>();
		searchResultsPage.waitForPageToLoad();
		for (int i = 1; i <= documentCount; i++) {
			searchResultsPage.searchResultPositionCheckbox(i).click();
			WebElement document = searchResultsPage.searchResultPosition(String.valueOf(i));
			String guid = document.getAttribute("docguid");
			String documentName = document.getText();
			LOG.info("Document guid is '" + guid + "'. Document name is '" + documentName + "'");
			guids.add(guid);
			titles.add(documentName);
		}
	}

	@Then("^all documents present in the \"([^\"]*)\" folder$")
	public void checkDocuments(String folder) throws Throwable {
		StringBuffer error = new StringBuffer();
		foldersUtils.openFolder(folder);
		researchOrganizerPage.waitForPageToLoad();
		for (int i = 1; i <= documentCount; i++) {
			String guid = guids.get(i - 1);
			String documentName = titles.get(i - 1);
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
