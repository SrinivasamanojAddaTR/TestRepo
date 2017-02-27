package com.thomsonreuters.pageobjects.utils.folders;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.*;
import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.folders.*;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation.DocumentDeliveryPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.utils.document.Document;
import com.thomsonreuters.pageobjects.utils.legalUpdates.CalendarAndDate;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.*;

public class FoldersUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FoldersUtils.class);
    private static final String ENTER_BUTTON_CODE = "13";
    private static final int EXPECTED_DOCUMENT_NAME_LENGTH = 60;

    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private NewGroupPopup newGroupPopup = new NewGroupPopup();
    private NavigationCobalt navigationCobalt = new NavigationCobalt();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private ShareFolderRolesPopup shareFolderRolesPopup = new ShareFolderRolesPopup();
    private ShareFolderPopup shareFolderPopup = new ShareFolderPopup();
    private RestoreFromTrashPopup restoreFromTrashPopup = new RestoreFromTrashPopup();
    private CommonMethods commonMethods = new CommonMethods();
    private SaveToPopup saveToPopup = new SaveToPopup();
    private NewFolderPopup newFolderPopup = new NewFolderPopup();
    private DeleteFolderPopup deleteFolderPopup = new DeleteFolderPopup();
    private DocumentDeliveryPage documentDeliveryPage = new DocumentDeliveryPage();
    private MoveFolderPopUp moveFolderPopUp = new MoveFolderPopUp();
    private CopyFolderPopUp copyFolderPopUp = new CopyFolderPopUp();
    private ExportFolderPopup exportFolderPopUp = new ExportFolderPopup();

    public void openFolder(String folder) {
        researchOrganizerPage.rootFolderLinkLeftFrame().click();
        if (!folder.equals("root")) {
            researchOrganizerPage.folderLinkLeftFrame(folder).click();
        }
        waitAjaxIfNecessary();
    }

    public boolean doesFolderExist(String folder) {
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        researchOrganizerPage.rootFolderLinkLeftFrame().click();
        try {
            researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
            researchOrganizerPage.folderLinkLeftFrame(folder).click();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void shareFolder(String folderName) {
        openFolder(folderName);
        researchOrganizerPage.shareFolder(folderName).click();
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    public void shareFolderViaEmail(String owner, String folderName, String userNameToShare) {
        String email = ExcelFileReader.getCobaltEmail(userNameToShare);
        shareFolderViaEmailByEmail(owner, folderName, email);
    }

    public void shareFolderViaEmailByEmail(String owner, String folderName, String emailToShare) {
        shareFolder(folderName);
        JavascriptExecutor js = (JavascriptExecutor) AbstractPage.getDriver;
        js.executeScript("$('#coid_contacts_addedContactsInput').click();");
        js.executeScript(
                String.format("$('#coid_contacts_addedContactsInput input').val(\"%s\").trigger($.Event(\"keyup\", { keyCode: %s }));",
                        emailToShare, ENTER_BUTTON_CODE)
        );
        shareFolderPopup.continueButton().click();
        shareFolderPopup.waitForPageToLoad();
        shareFolderPopup.emailToNotify().sendKeys(ExcelFileReader.getCobaltEmail(owner));
        shareFolderPopup.continueButton().click();
        shareFolderPopup.waitForPageToLoad();
        shareFolderRolesPopup.shareButton().click();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    public String addDescription(String description, String documentGUID) {
        String docTitle = researchOrganizerPage.getTitle(documentGUID);
//    	pageActions.mouseOverAndClickElement(researchOrganizerPage.descriptionWidgetAddButton(docTitle));
        WebElement el = researchOrganizerPage.descriptionWidgetAddButton(docTitle);
        commonMethods.mouseOver(el);
        el.click();
        researchOrganizerPage.descriptionWidget().click();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        researchOrganizerPage.descriptionWidgetInput().click();
        researchOrganizerPage.descriptionWidgetInput().clear();
        researchOrganizerPage.descriptionWidgetInput().sendKeys(description);
        researchOrganizerPage.descriptionWidgetSaveButton().click();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        return description;
    }

    public void createNewGroupToShareFolder(String groupName, String member) {
        newGroupPopup.waitForPageToLoadAndJQueryProcessing();
        newGroupPopup.groupName().sendKeys(groupName);
        commonMethods.clickElementUsingJS(newGroupPopup.people(member));
        newGroupPopup.saveGroup().click();
    }

    public void deleteGroupToShareFolderIfExists(String groupName) {
        if (shareFolderPopup.isSpecificGroupExists((groupName))) {
            commonMethods.mouseOver(shareFolderPopup.getSpecificGroup(groupName));
            shareFolderPopup.deleteSpecificGroup(groupName).click();
            shareFolderPopup.waitForPageToLoadAndJQueryProcessing();
        } else {
            LOG.info("Such group is not present");
        }
    }

    public void deleteFolderIfExists(String folderName) {
        if (doesFolderExist(folderName)) {
            openFolder(folderName);
            researchOrganizerPage.optionsButton().click();
            researchOrganizerPage.deleteOptionButton().click();
            researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
            researchOrganizerPage.waitForPageToLoad();
            deleteFolderPopup.clickOK().click();
            deleteFolderPopup.waitForPageToLoad();
            String actualMessage = deleteFolderPopup.getDeletedMessage().getText();
            String expectedMessage = "The contents of '" + folderName + "' were moved to Trash.";
            if (!actualMessage.equals(expectedMessage)) {
                throw new PageOperationException("Message is incorrect when folder is deleted");
            }
            deleteFolderPopup.clickOK().click();
            deleteFolderPopup.waitForPageToLoad();
        }
    }

    /**
     * Check if rows on the page sorted in according to necessary alghorythm
     * IMPORTANT! Only documents data will be counted. Rows, which contains nested folders, will be ignored.
     *
     * @param column        Column to get data from.
     * @param sortDirection Expected sorting direction {@link SortOptions}
     * @return True - if check was passed, otherwise - false.
     * @see DocumentColumn
     */
    public boolean isDocSortedBy(DocumentColumn column, SortOptions sortDirection) {
        List<String> inputStrings = researchOrganizerPage.getDocumentsDataInColumn(column);
        switch (column) {
            case TITLE:
            case CONTENT:
                return checkStringsSorting(inputStrings, sortDirection);
            case DATE_ADDED:
                return checkDatesSorting(inputStrings, sortDirection);
            default:
                throw new UnsupportedOperationException("There is no sorting check alghorythm for column " + column.getName());
        }
    }

    /**
     * Check if strings in list going in order according to given direction (asc or desc)
     *
     * @param inputStrings  List with strings
     * @param sortDirection Sort direction {@link SortOptions}
     * @return True - if check was passed, otherwise - false.
     */
    private boolean checkStringsSorting(List<String> inputStrings, SortOptions sortDirection) {
        String previousString = inputStrings.get(0);
        for (String currentString : inputStrings) {
            // if words going in alphabet order then comparison method will return int > 0
            if ((currentString.compareTo(previousString) < 0 && sortDirection == SortOptions.ASC) ||
                    (currentString.compareTo(previousString) > 0 && sortDirection == SortOptions.DESC)) {
                return false;
            }
            previousString = currentString;
        }
        return true;
    }

    /**
     * Check if strings with dates represents in list are going in order according to given direction (asc or desc)
     *
     * @param inputStrings  List with strings
     * @param sortDirection Sort direction {@link SortOptions}
     * @return True - if check was passed, otherwise - false.
     */
    private boolean checkDatesSorting(List<String> inputStrings, SortOptions sortDirection) {
        try {
            Date previousDate = CalendarAndDate.convertPublishedDateStringInstanceToDateInstanceFromLUPage(inputStrings.get(0));
            for (String currentString : inputStrings) {
                // if dates going in chronology order then comparison method will return int > 0
                Date currentDate = CalendarAndDate.convertPublishedDateStringInstanceToDateInstanceFromLUPage(currentString);
                if ((currentDate.compareTo(previousDate) < 0 && sortDirection == SortOptions.ASC) ||
                        (currentDate.compareTo(previousDate) > 0 && sortDirection == SortOptions.DESC)) {
                    return false;
                }
                previousDate = currentDate;
            }
        } catch (ParseException e) {
            LOG.warn("Error occurred when trying to parse date", e);
            return false;
        }
        return true;
    }

    /**
     * if the document has too long name, it is needed to make it shorter for checks
     *
     * @param documentName
     * @return
     */
    public static String makeDocumentShorterForFoldersAndHistoryChecks(String documentName) {
        if (documentName.length() > EXPECTED_DOCUMENT_NAME_LENGTH) {
            documentName = documentName.substring(0, EXPECTED_DOCUMENT_NAME_LENGTH);
        }
        return documentName;
    }

    public void moveToOriginalFolder(String folderName) {
        restoreFromTrashPopup.waitForPageToLoad();
        restoreFromTrashPopup.selectFolder(folderName).click();
        restoreFromTrashPopup.moveButton().click();
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

    // TODO 1. Move to AbstractPage. 2. Make waitForPageToLoadAndJQueryProcessing() more flexible / parametrized
    public void waitAjaxIfNecessary() {
        if (!commonMethods.getDriver().getCurrentUrl().contains("Glossary")) {
            researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        }
    }

    public String saveToFolder(String folder) {
        String folderName = null;
        saveToPopup.waitForPageToLoad();
        waitAjaxIfNecessary();
        if (folder.equals("root")) {
            saveToPopup.rootFolder().click();
            folderName = saveToPopup.rootFolder().getText();
        } else {
            waitAjaxIfNecessary();
            try {
                if(saveToPopup.isRootFolderExpanded()){
                    saveToPopup.expandRootFolderWait().click();
                }
                saveToPopup.selectFolderWait(folder).click();
            } catch (NoSuchElementException e) {
                throw new RuntimeException("Folder '" + folder + "'doesn't present");
            }
        }
        saveToPopup.save().click();
        return folderName;
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
        newFolderPopup.waitForPageToLoad();
        newFolderPopup.save().click();
        newFolderPopup.waitForPageToLoadAndJQueryProcessing();
        return parentFolder;
    }

    public void deleteFolder(String folderName) {
        openFolder(folderName);
        researchOrganizerPage.optionsButton().click();
        researchOrganizerPage.deleteOptionButton().click();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        researchOrganizerPage.waitForPageToLoad();
    }

    public void renameFolderDoubleClick(String folderName) {
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.rootFolderLinkLeftFrame().click();
        researchOrganizerPage.folderLinkLeftFrame(folderName).click();
        researchOrganizerPage.folderLinkLeftFrame(folderName).click();
    }

    public void renameFolder(String folderName) {
        openFolder(folderName);
        researchOrganizerPage.optionsButton().click();
        researchOrganizerPage.renameOptionButton().click();
    }

    public boolean isDocumentPresentInTheFolder(String folder, Document document) {
        openFolder(folder);
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        return folder.equals("Trash") ? researchOrganizerPage.isLinkToDocumentInTrashDisplayed(document.getTitle())
                : researchOrganizerPage.isLinkToDocumentDisplayed(document.getGuid(), document.getTitle());
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
        if (!(exportFolderPopUp.isFolderVisible(folderName))) {
            exportFolderPopUp.expandFolder().click();
            exportFolderPopUp.waitForPageToLoadAndJQueryProcessing();
        }
        exportFolderPopUp.checkBox(folderName).click();
    }

    public Map<String, List<String>> selectDocuments(String count) {
        List<String> guids = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        Map<String, List<String>> mapOfLists = new HashMap<>();
        int documentCount = Integer.parseInt(count);
        researchOrganizerPage.waitForPageToLoad();
        for (int i = 1; i <= documentCount; i++) {
            searchResultsPage.searchResultPositionCheckbox(i).click();
            WebElement document = searchResultsPage.searchResultPosition(String.valueOf(i));
            String guid = document.getAttribute("docguid");
            String documentName = document.getText();
            LOG.info("Document guid is '" + guid + "'. Document name is '" + documentName + "'");
            guids.add(guid);
            titles.add(documentName);
        }
        mapOfLists.put("titles", titles);
        mapOfLists.put("guids", guids);
        return mapOfLists;
    }

    public void checksFolderAbsent(String folderName) {
        saveToPopup.waitForPageToLoad();
        saveToPopup.waitForPageToLoad();
        try {
            saveToPopup.expandRootFolder().click();
            saveToPopup.selectFolder(folderName).click();
        } catch (NoSuchElementException e) {
            LOG.info("Folder with " + folderName + " name was not found");
        }
        throw new RuntimeException("Folder '" + folderName + "' presents");
    }

    public void createFolderWithContent(String folder, List<String> listOfGuid) {
        researchOrganizerPage.createNewFolderButton().click();
        createNewFolder(folder, "root");
        for (String entry : listOfGuid) {
            navigationCobalt.navigateToANZSpecificResourcePage("/Document/" + entry + "/View/FullText.html");
            documentDeliveryPage.waitForPageToLoadAndJQueryProcessing();
            documentDeliveryPage.clickOnAddToFolderLink();
            saveToFolder(folder);
        }
    }
}
