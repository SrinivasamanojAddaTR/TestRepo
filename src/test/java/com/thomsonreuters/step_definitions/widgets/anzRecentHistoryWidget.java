package com.thomsonreuters.step_definitions.widgets;

import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.StandardDocumentPage;
import com.thomsonreuters.pageobjects.pages.widgets.RecentHistoryWidget;
import com.thomsonreuters.pageobjects.utils.document.Document;
import com.thomsonreuters.pageobjects.utils.document.SortDocumentsByTitle;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.step_definitions.document.viewDocument;
import com.thomsonreuters.step_definitions.favourites.anzFavourites;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class anzRecentHistoryWidget extends BaseStepDef {
	
	private Document document = new Document();
	private viewDocument viewDocument = new viewDocument();
	private RecentHistoryWidget recentHistoryWidget = new RecentHistoryWidget();
	private StandardDocumentPage standardDocumentPage = new StandardDocumentPage();
	private SortDocumentsByTitle sortDocumentsByName = new SortDocumentsByTitle();
    private HomePage homepage = new HomePage();
	private anzFavourites anzFavourites = new anzFavourites();
	
	private ArrayList<Document> documents;
	
	private List<Document> widgetDocuments;
	
	@When("^ANZ user navigates directly to documents with guids and saves their details$")
	public void userNavigatesDirectlyToDocumentsWithGuidsAndSavesTheirDetails(List<String> documentGUIDs) throws Throwable {
		documents = new ArrayList<Document>();
		for (String documentGUID : documentGUIDs) {
			viewDocument.anzUserNavigatesDirectlyToDocumentWithGuid(documentGUID);
			homepage.waitForPageToLoad();
			homepage.waitForPageToLoadAndJQueryProcessing();
			document = new Document();
			document.setTitle(standardDocumentPage.documentTitle().getText().trim());
			document.setStatus(standardDocumentPage.documentStatus().getText().trim());
			document.setResourceType(standardDocumentPage.resourceType()
					.getText().trim());
			documents.add(document);
		}
	}
	
	@Then("^user should see recent history widget which contains recently opened documents$")
	public void userShouldSeeRecentHistoryWidgetWhichContainsRecentlyOpenedDocuments() throws Throwable {
		widgetDocuments = recentHistoryWidget.getAllDocumentsFromWidget();
		Collections.sort(widgetDocuments, sortDocumentsByName);
		LOG.info("Widget contains following documents: " + widgetDocuments);
		Collections.sort(documents, sortDocumentsByName);
		LOG.info("User has opened following documents: " + documents);
		assertTrue("Recent history widget does not contain all opened documents",widgetDocuments.containsAll(documents));

	}
	
	@Then("^user should see recent history widget on \"(.*)\" on resource page which contains recently opened documents$")
	public void userShouldSeeRecentHistoryWidgetOnResourcePageWhichContainsRecentlyOpenedDocuments(String resourcePage) throws Throwable {
		 anzFavourites.userNavigatesToResourceOnResourcePage(resourcePage);
		 userShouldSeeRecentHistoryWidgetWhichContainsRecentlyOpenedDocuments();

	}
	
	@Then("^user clicks View all link on recent history widget$")
	public void userClicksViewAllLinkOnRecentHistoryWidget() throws Throwable {
		recentHistoryWidget.veiwAllButton().click();
		homepage.waitForPageToLoadAndJQueryProcessing();

	}
	
	@Then("^the user clicks document link on recent history widget and document should open$")
	public void usecrClicksDocumentLinkOnRecentHostoryWidgetAndDocumentShouldOpen() throws Throwable {
		WebElement documentLinkFromWidget = recentHistoryWidget.documentsTitles().get(0);
		String documentTitleFromWidget = documentLinkFromWidget.getText().trim();
		documentLinkFromWidget.click();
		standardDocumentPage.waitForPageToLoadAndJQueryProcessing();
		assertTrue("Wrong document was opened from history widget", standardDocumentPage.documentTitle().getText().trim().equals(documentTitleFromWidget));

	}


}
