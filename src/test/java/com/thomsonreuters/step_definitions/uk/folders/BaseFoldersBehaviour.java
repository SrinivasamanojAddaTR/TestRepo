package com.thomsonreuters.step_definitions.uk.folders;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.DocumentColumn;
import com.thomsonreuters.pageobjects.common.SortOptions;
import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.folders.*;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation.DocumentDeliveryPage;
import com.thomsonreuters.pageobjects.utils.folders.FoldersUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.step_definitions.annotations.AnnotationsStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class BaseFoldersBehaviour extends BaseStepDef {

    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private WLNHeader header = new WLNHeader();
    private CommonMethods comMethods = new CommonMethods();
    private NewFolderPopup newFolderPopup = new NewFolderPopup();
    private SaveToPopup saveToPopup = new SaveToPopup();
    private CopyFolderPopUp copyFolderPopUp = new CopyFolderPopUp();
    private MoveFolderPopUp moveFolderPopUp = new MoveFolderPopUp();
    private ExportFolderPopup exportFolderPopUp = new ExportFolderPopup();
    private RestoreFromTrashPopup restoreFromTrashPopup = new RestoreFromTrashPopup();
    private FoldersUtils foldersUtils = new FoldersUtils();
    private NavigationCobalt navigationCobalt = new NavigationCobalt();
    private DocumentDeliveryPage documentDeliveryPage = new DocumentDeliveryPage();
    private AnnotationsStepDef annotationsStepDef = new AnnotationsStepDef();

    private String folderName;


    @When("^the user clicks on '(.+)' tab$")
    public void theUserClicksOnTab(String tabName) throws Throwable {
        switch (tabName) {
            case "Folders":
                researchOrganizerPage.foldersTab().click();
                break;
            case "History":
                researchOrganizerPage.historyTab().click();
                break;
            default:
        }
        researchOrganizerPage.waitForPageToLoad();
    }

    @Then("^'(.+)' page opens$")
    public void pageOpens(String pageName) throws Throwable {
        switch (pageName) {
            case "Folders":
                String actualFoldersClassAttributeValue = researchOrganizerPage.foldersTab().getAttribute("class");
                assertEquals("Folders tab is not active", researchOrganizerPage.getExpectedClassAttributeValueForTabs(),
                        actualFoldersClassAttributeValue);
                break;
            case "History":
                String actualHistoryClassAttributeValue = researchOrganizerPage.historyTab().getAttribute("class");
                assertEquals("History tab is not active", researchOrganizerPage.getExpectedClassAttributeValueForTabs(),
                        actualHistoryClassAttributeValue);
                break;
            default:
        }
    }

    @When("^the user selects '(.+)' Type$")
    public void selectType(String type) throws Throwable {
        researchOrganizerPage.facetedViewSelectType(type).click();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user selects '(.+)' Client ID$")
    public void selectClientID(String clientID) throws Throwable {
        researchOrganizerPage.facetedViewSelectClientID(clientID).click();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user selects '(.+)' Content type$")
    public void selectContentTypes(String contentType) throws Throwable {
        researchOrganizerPage.facetedViewSelectContentType(contentType).click();
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user clicks Select Multiple Filters$")
    public void multipleFilters() throws Throwable {
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        researchOrganizerPage.selectMultipleFilters1();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user clicks Cancel Filters$")
    public void cancelFilters() throws Throwable {
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        WebElement cancel = researchOrganizerPage.cancelFilters1();
        comMethods.mouseOver(cancel);
        cancel.click();
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user clicks Apply Filters$")
    public void applyFilters() throws Throwable {
        researchOrganizerPage.applyFilters1().click();
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^there is no root folder duplication in recent folders drop down$")
    public void checkRootFolderDuplicationAbsentInRecentFoldersDropdown() throws Throwable {
        researchOrganizerPage.waitForPageToLoad();
        comMethods.mouseOver(researchOrganizerPage.recentFoldersDropdown());
        int rootFolderCount = researchOrganizerPage.getRootFolderCountInRecentFoldersDropdown();
        if (rootFolderCount > 1) {
            throw new RuntimeException("There is root folder duplication in recent folders");
        }
    }

    @Then("^there is no the \"([^\"]*)\" folder in recent folders drop down$")
    public void checkFolderAbsentInRecentFoldersDropdown(String folderName) throws Throwable {
        researchOrganizerPage.waitForPageToLoad();
        comMethods.mouseOver(researchOrganizerPage.recentFoldersDropdown());
        try {
            researchOrganizerPage.linkToFolderInRecentDropdown(folderName).isDisplayed();
        } catch (NoSuchElementException e) {
            return;
        }
        throw new RuntimeException("Folder '" + folderName + "' is present in recent folders drop down");
    }


    @Then("^all the folders listed$")
    public void checkThatFoldersPresent(List<String> folderNames) {
        for (String folderName : folderNames) {
            assertTrue("Folder '" + folderName + "' is abdsent.", researchOrganizerPage.isFolderPresent(folderName));
        }
    }

    @When("^the user goes to previous folder \"([^\"]*)\"$")
    public void openFolder(String folderName) {
        foldersUtils.openFolder(folderName);
        this.folderName = folderName;
    }


    @When("^the user goes to folder '(.*)'$")
    public void theUserGoesToFolderSubFolder(String folderName) throws Throwable {
        annotationsStepDef.theUserClicksOnLinkOnTheHeader("Folders");
        openFolder(folderName);
    }

    @Then("^user should see the selected folder page with multiple documents$")
    public void checkThatFolderOpenedAndItContainsTwoOrMoreDocs() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(researchOrganizerPage.getOpenedFolderTitle()).isEqualTo(folderName)
                .withFailMessage("Selected folder does not opened");
        softAssertions.assertThat(researchOrganizerPage.getDocumentsDataInColumn(DocumentColumn.TITLE).size()).isGreaterThan(1)
                .withFailMessage("Selected folder '" + folderName + "' does not contains multiple document");
        softAssertions.assertAll();
    }

    @When("^the user clicks on the '([^\"]*)' column$")
    public void clickOnColumn(String columnName) {
        researchOrganizerPage.getColumn(DocumentColumn.getByName(columnName)).click();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user clicks on the '([^\"]*)' column again$")
    public void clickOnColumnAgain(String columnName) {
        clickOnColumn(columnName);
    }

    @Then("^the rows sorted by '([^\"]*)' column in ascending$")
    public void checkColumnSortingAscBy(String columnName) {
        checkThatFolderOpenedAndItContainsTwoOrMoreDocs();
        DocumentColumn documentColumn = DocumentColumn.getByName(columnName);
        Assert.assertTrue("Documents not sorted by value in column '" + documentColumn.getName() + "', ascending",
                foldersUtils.isDocSortedBy(documentColumn, SortOptions.ASC));
    }

    @Then("^the rows sorted by '([^\"]*)' column in descending$")
    public void checkColumnSortingDescBy(String columnName) {
        DocumentColumn documentColumn = DocumentColumn.getByName(columnName);
        Assert.assertTrue("Documents not sorted by value in column '" + documentColumn.getName() + "', descending",
                foldersUtils.isDocSortedBy(documentColumn, SortOptions.DESC));
    }

    public String saveToNewFolder(String newFolderName, String parentFolder) {
        String folderName = null;
        saveToPopup.waitForPageToLoad();
        saveToPopup.waitForPageToLoadAndJQueryProcessing();
        saveToPopup.newFolder().click();
        saveToPopup.waitForPageToLoadAndJQueryProcessing();
        folderName = createNewFolder(newFolderName, parentFolder);
        saveToPopup.waitForPageToLoadAndJQueryProcessing();
        saveToPopup.waitFolderSelected(newFolderName);
        saveToPopup.save().click();
        return folderName;
    }

    public String saveToFolder(String folder, String resourceType) {
        String folderName = null;
        saveToPopup.waitForPageToLoad();
        saveToPopup.waitForPageToLoadAndJQueryProcessing();
        if (folder.equals("root")) {
            saveToPopup.selectRootFolder().click();
            folderName = saveToPopup.selectRootFolder().getText();
        } else {
            saveToPopup.waitForPageToLoadAndJQueryProcessing();
			try {
				saveToPopup.expandRootFolderWait().click();
			} catch (NoSuchElementException | TimeoutException e) {
				LOG.info("Root folder is already expanded");
			}
            try {
                saveToPopup.selectFolderWait(folder).click();
            } catch (NoSuchElementException e) {
            	throw new RuntimeException("Folder '" + folder + "'doesn't present");
            }
        }
        saveToPopup.save().click();
        return folderName;
    }
    
    public String saveToFolder(String folder) {
    	return saveToFolder(folder, null);
    }

    public String createNewFolder(String newFolderName, String parentFolder) {
        newFolderPopup.waitForPageToLoad();
        newFolderPopup.waitForPageToLoadAndJQueryProcessing();
        newFolderPopup.newFolderInput().sendKeys(newFolderName);
        if (parentFolder.equals("root")) {
            newFolderPopup.selectRootFolder().click();
            parentFolder = newFolderPopup.selectRootFolder().getText();
        } else {
            newFolderPopup.selectFolder(parentFolder).click();
        }
        newFolderPopup.waitForPageToLoadAndJQueryProcessing();
        newFolderPopup.save().click();
        newFolderPopup.waitForPageToLoadAndJQueryProcessing();
        return parentFolder;
    }

    public void checksFolderAbsent(String folderName) {
        saveToPopup.waitForPageToLoad();
        saveToPopup.waitForPageToLoad();
        try {
            saveToPopup.expandRootFolder().click();
            saveToPopup.selectFolder(folderName).click();
        } catch (NoSuchElementException e) {
            return;
        }
        throw new RuntimeException("Folder '" + folderName + "' presents");
    }

    public String moveFolder(String destinationFolderName) {
    	moveFolderPopUp.waitForPageToLoad();
        if (destinationFolderName.equals("root")) {
            newFolderPopup.selectRootFolder().click();
            destinationFolderName = newFolderPopup.selectRootFolder().getText();
        } else {
        	moveFolderPopUp.selectFolder(destinationFolderName).click();
        }
        moveFolderPopUp.waitForPageToLoad();
        moveFolderPopUp.waitForPageToLoadAndJQueryProcessing();
        moveFolderPopUp.save().click();
        moveFolderPopUp.waitForPageToLoadAndJQueryProcessing();
        return destinationFolderName;
    }

    public String copyFolder(String destinationFolderName) {
    	copyFolderPopUp.waitForPageToLoad();
        if (destinationFolderName.equals("root")) {
            newFolderPopup.selectRootFolder().click();
            destinationFolderName = newFolderPopup.selectRootFolder().getText();
        } else {
        	copyFolderPopUp.selectFolder(destinationFolderName).click();
        }
        copyFolderPopUp.waitForPageToLoad();
        copyFolderPopUp.waitForPageToLoadAndJQueryProcessing();
        copyFolderPopUp.save().click();
        copyFolderPopUp.waitForPageToLoadAndJQueryProcessing();
        return destinationFolderName;
    }
    public void chooseFolderforExport(String folderName) {
    	exportFolderPopUp.waitForPageToLoad();
    	if (!(exportFolderPopUp.isFolderVisible(folderName))){
    		exportFolderPopUp.expandFolder().click();
        	exportFolderPopUp.waitForPageToLoadAndJQueryProcessing();
    	}
   		exportFolderPopUp.checkBox(folderName).click();
    }

	public void moveToOriginalFolder(String folderName) {
		restoreFromTrashPopup.waitForPageToLoad();
		restoreFromTrashPopup.selectFolder(folderName).click();
		restoreFromTrashPopup.moveButton().click();
	}

	public void createFolderWithContent(String folder, List<String> listOfGuid){
        researchOrganizerPage.createNewFolderButton().click();
        createNewFolder(folder, "root");
    	for (String entry : listOfGuid) {
   	    	navigationCobalt.navigateToANZSpecificResourcePage("/Document/" + entry+ "/View/FullText.html");
   	    	documentDeliveryPage.waitForPageToLoadAndJQueryProcessing();
   	        documentDeliveryPage.clickOnAddToFolderLink();
   	    	saveToFolder(folder);   	    		   	      	       	
        }      
    	researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    	header.foldersLink().click();
	}
    

}