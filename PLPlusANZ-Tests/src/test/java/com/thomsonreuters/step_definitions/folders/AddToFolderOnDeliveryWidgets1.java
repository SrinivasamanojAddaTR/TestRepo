package com.thomsonreuters.step_definitions.folders;

import com.thomsonreuters.pageobjects.pages.folders.SaveToPopup;
import com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources.ListOfItemsDeliveryOptionsPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document_navigation.DocumentDeliveryPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class AddToFolderOnDeliveryWidgets1 extends BaseStepDef {
	
	
    private DocumentDeliveryPage documentDeliveryPage = new DocumentDeliveryPage();
	private ListOfItemsDeliveryOptionsPage listOfItemsDeliveryOptionsPage = new ListOfItemsDeliveryOptionsPage();
	private SaveToPopup saveToPopup = new SaveToPopup();
	
	@When("^user clicks on Add To Folder icon on document delivery$")
	public void userClicksOnAddToFolderIconOnDocumentDelivery() throws Throwable {
		documentDeliveryPage.waitForPageToLoadAndJQueryProcessing();
		documentDeliveryPage.clickOnAddToFolderLink();
	}
	
	@When("^user clicks on Add To Folder icon on search delivery$")
	public void userClicksOnAddToFolderIconOnSearchDelivery() throws Throwable {
		listOfItemsDeliveryOptionsPage.saveToFolderLink().click();
		listOfItemsDeliveryOptionsPage.waitForPageToLoadAndJQueryProcessing();
		
	}
	
	@Then("^folders widget on document will be displayed after print option and download option$")
	public void foldersWidgetOnDocumentWillBeDisplayedAfterPrintOptionAndBeforeTheDownloadOption() throws Throwable {
		SoftAssertions softly = new SoftAssertions();
		List<WebElement> deliveryIcons = documentDeliveryPage.deliveryIcons();
		
		int addToFolderPosition = deliveryIcons.indexOf(documentDeliveryPage.addToFolderIcon());
		int printPosition = deliveryIcons.indexOf(documentDeliveryPage.printIcon());
		int downloadPosition = deliveryIcons.indexOf(documentDeliveryPage.downloadIcon());
		softly.assertThat(printPosition < addToFolderPosition).overridingErrorMessage("Add to Folder Icon is displayed after Print icon Icon. Print position is: <%s> Add To Folder position is: <%s>", printPosition, addToFolderPosition).isTrue();
		softly.assertThat(downloadPosition < addToFolderPosition ).overridingErrorMessage(" Add to Folder Icon is displayed after Download Icon. Add To Folder position is: <%s> Download position is: <%s>", addToFolderPosition, downloadPosition).isTrue();
		softly.assertAll();
		
	}


	@Then("^user should see Add To Folder pop up$")
	public void userShouldSeeAddToFolderPopUp() throws Throwable {
		assertTrue("Add to Folder pop up is not displayed", saveToPopup.isPopUpDispayed());
	}
}
