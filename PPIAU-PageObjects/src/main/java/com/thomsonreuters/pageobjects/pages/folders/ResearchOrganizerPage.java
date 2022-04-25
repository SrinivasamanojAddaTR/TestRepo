package com.thomsonreuters.pageobjects.pages.folders;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.DocumentColumn;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

import java.util.ArrayList;
import java.util.List;

public class ResearchOrganizerPage extends AbstractPage {

    private static final String FACET_CONTENT_TYPE = "//span[contains(text(), 'Content Type')]/../..";
    private static final String FACET_TYPE = "//*[@id='facet_div_Type']";
    private static final String EXPECTED_CLASS_ATTRIBUTE_VALUE_FOR_TABS = "co_tabLeft co_tabActive";
    private static final String LINK_TO_DOCUMENT_CONTENT_TYPE = "//*[contains(@href,'%s')]/ancestor::td/following-sibling::td[1]/span[text()=%s]";
    private static final By CANCCEL_FILTERS_1_XPATH = By.xpath("//*[@id='co_multifacet_selector_1']/*[contains(@class,'co_multifacet_cancel')]");
    private static final String FACET_PATH_PATTERN = "//*[@id='facet_div_Type']//*[(normalize-space(text())='%s')]/../descendant-or-self::input[@type='checkbox']";
    private static final String DOCUMENT_WITH_GUID_PATTERN = "//*[contains(@href,'%s')]";
    private static final String CONTENT_TYPE_PATH_PATTERN = "//h4[contains(., 'Content')]/following-sibling::ul/li[contains(., \"%1$s\")]/input | //*[(normalize-space(text())=\"%1$s\")]/../descendant-or-self::input[@type='checkbox']";
    private static final String FACET_CLIENT_ID = "//span[contains(text(), 'Client ID')]/../..";
    private static final String FOLDER_ITEM_PATTERN = "//*[@id='cobalt_foldering_ro_item_name_%s']";


    public WebElement foldersTab() {
        return waitForExpectedElement(By.id("co_researchOrganizer_myFolders"));
    }

    public WebElement historyTab() {
        return waitForExpectedElement(By.id("co_researchOrganizer_history"));
    }

    public WebElement createNewFolderButton() {
        return waitForExpectedElement(By.xpath("//*[@class='co_createNewFolder']/a"));
    }

    public WebElement getFacetClientId() {
        return waitForExpectedElement(By.xpath(FACET_CLIENT_ID));
    }

    public WebElement optionsButton() {
        return waitForExpectedElement(By.xpath("//*[@id='co_ro_folder_options']/button"), 15);
    }

    public WebElement getFacetType() {
        return waitForExpectedElement(By.xpath(FACET_TYPE));
    }

    public WebElement getFacetContentType() {
        return waitForExpectedElement(By.xpath(FACET_CONTENT_TYPE));
    }

    public WebElement optionsCopy() {
        return waitForExpectedElement(By.id("co_ro_fo_copy"));
    }

    public WebElement optionsExport() {
        return waitForExpectedElement(By.id("co_ro_fo_export"));
    }

    public WebElement optionsMove() {
        return waitForExpectedElement(By.id("co_ro_fo_move"));
    }

    public WebElement deleteOptionButton() {
        return waitForExpectedElement(By.id("co_ro_fo_delete"), 15);
    }

    public WebElement renameOptionButton() {
        return waitForExpectedElement(By.id("co_ro_fo_rename"));
    }

    public String getExpectedClassAttributeValueForTabs() {
        return EXPECTED_CLASS_ATTRIBUTE_VALUE_FOR_TABS;
    }

    public WebElement getLinkToDocumentAtRowPosition(String position) {
        return waitForExpectedElement(By.xpath(String.format(FOLDER_ITEM_PATTERN, (Integer.parseInt(position) - 1))), 15);
    }

    public WebElement getEventTypeAtRowPosition(String position) {
        return waitForExpectedElement(By.xpath("//*[@id='cobalt_foldering_ro_item_event_" + (Integer.parseInt(position) - 1) + "']"), 15);
    }

    public WebElement getContentTypeAtRowPosition(String position) {
        return waitForExpectedElement(By.xpath("//*[@id='cobalt_foldering_ro_item_contentType_" + (Integer.parseInt(position) - 1) + "']"), 15);
    }

    public WebElement getResourceTypeAtRowPosition(String position) {
        return waitForExpectedElement(By.xpath(String.format(FOLDER_ITEM_PATTERN, (Integer.parseInt(position) - 1)) + "/ancestor::td//div[@class='cobalt_ro_documentDescription']/span[2]"), 10);
    }

    public WebElement getCitationAtRowPosition(String position) {
        return waitForExpectedElement(By.xpath(String.format(FOLDER_ITEM_PATTERN, (Integer.parseInt(position) - 1)) + "/ancestor::td//div[@class='cobalt_ro_documentDescription']/span"), 10);
    }

    public WebElement getDescriptionAtRowPosition(String position) {
        return waitForExpectedElement(By.xpath(String.format(FOLDER_ITEM_PATTERN, (Integer.parseInt(position) - 1)) + "/ancestor::td//div[@class='cobalt_ro_documentDescription']"), 10);
    }

    public WebElement getDateAtRowPosition(String position) {
        return waitForExpectedElement(By.xpath("//*[@id='cobalt_foldering_ro_item_eventDate_" + (Integer.parseInt(position) - 1) + "']"));
    }

    public WebElement getClientIdAtRowPosition(String position) {
        return waitForExpectedElement(By.xpath("//*[@id='cobalt_foldering_ro_item_clientId_" + (Integer.parseInt(position) - 1) + "']"));
    }

    public WebElement rootFolderLinkLeftFrame() {
        return waitForExpectedElement(By.xpath("//*[@id='cobalt_ro_myFolders_folderTree']//a[contains(@class,'tree_selectable') and contains(.,'s Research')]"), 15);
    }

    public WebElement folderLinkLeftFrame(String folderName) {
        return waitForExpectedElement(By.xpath("//*[@id='co_researchFolderTree']//*[text()='" + folderName + "']"), 30);
    }

    public boolean isLinkToDocumentInTrashDisplayed(String title) {
        return isElementDisplayed(By.xpath("//*[@class='co_keyCite_treatment' and contains(text(),\"" + title + "\")]"));
    }

    public WebElement linkToDocument(String documentGuid, String title) {
        return findElement(By.xpath("//*[contains(@href, '" + documentGuid + "') and contains(text(),\"" + title + "\")]"));
    }

    public boolean isLinkToDocumentDisplayed(String documentGuid, String title) {
        return isElementDisplayed(By.xpath("//*[contains(@href, '" + documentGuid + "') and contains(text(),\"" + title + "\")]"));
    }

    public WebElement linkToDocumentContentType(String documentGuid, String contentType) {
        String text = "'" + contentType + "'";
        if (contentType.contains("'")) {
            contentType = "\"" + contentType + "\"";
            text = contentType;
        }
        return findElement(By.xpath(String.format(LINK_TO_DOCUMENT_CONTENT_TYPE, documentGuid, text)));
    }

    public boolean isLinktoDocumentContentTypePresent(String documentGuid, String contentType) {
        String text = "'" + contentType + "'";
        if (contentType.contains("'")) {
            contentType = "\"" + contentType + "\"";
            text = contentType;
        }
        return isExists(By.xpath(String.format(LINK_TO_DOCUMENT_CONTENT_TYPE, documentGuid, text)));
    }

    public boolean isLinkToDocumentInRecentDropdownPresent(String documentGuid, String title) {
        String text = "'" + title + "'";
        if (title.contains("'")) {
            title = "\"" + title + "\"";
            text = title;
        }
        return isExists(By.xpath("//*[@id='co_recentHistoryContainer']//*[contains(@href, '" + documentGuid + "') and text()=" + text + "]"));
    }

    public boolean isLinkToFolderInRecentDropdownPresent(String folderName) {
        return isExists(By.xpath("//*[@id='co_recentFoldersContainer']//*[text()='" + folderName + "']"));
    }

    public WebElement linkToFolderInRecentDropdown(String folderName) {
        return waitForExpectedElement(By.xpath("//*[@id='co_recentFoldersContainer']//*[text()='" + folderName + "']"));
    }

    public WebElement recentHistoryDropdown() {
        return waitForExpectedElement(By.xpath("//*[@class='co_dropdownBoxanchorLabel' and text()='History']"));
    }

    public WebElement recentFoldersDropdown() {
        return waitForExpectedElement(By.xpath("//*[@class='co_dropdownBoxanchorLabel' and text()='Folders']"));
    }

    public int getRootFolderCountInRecentFoldersDropdown() {
        return waitForExpectedElements(By.xpath("//*[@id='co_recentFoldersContainer']//a[contains(text(),'s Research')]")).size();
    }

    public boolean isLinkToSearchInRecentDropdownPresent(String search) {
        return isExists(By.xpath("//*[@id='co_recentHistoryContainer']//*[contains(@href, '" + search + "')]"));
    }

    public int getDocumentCountInFolders() {
        return waitForExpectedElements(By.xpath("//table[contains(@class,'co_detailsTable')]//tbody//tr")).size();
    }

    public String getContentType(String documentGuid) {
        return waitForExpectedElement(By.xpath(String.format(DOCUMENT_WITH_GUID_PATTERN, documentGuid) + "/ancestor::td/following-sibling::td[1]/span")).getText();
    }

    public String getCitation(String documentGuid) {
        return waitForExpectedElement(By.xpath(String.format(DOCUMENT_WITH_GUID_PATTERN, documentGuid) + "/ancestor::td//div[@class='cobalt_ro_documentDescription']/span")).getText();
    }

    public String getDescription(String documentGuid) {
        return waitForExpectedElement(By.xpath(String.format(DOCUMENT_WITH_GUID_PATTERN, documentGuid) + "/ancestor::td//div[@class='cobalt_ro_documentDescription']")).getText();
    }

    public boolean isWMMetadataPresent(String documentGuid, String dealType, String dealValue, String date) {
        return isElementDisplayed(By.xpath(String.format(DOCUMENT_WITH_GUID_PATTERN, documentGuid) + "/ancestor::td//div[@class='cobalt_ro_documentDescription' and contains(.,\"" + dealType
                + "\") and contains(.,\"" + dealValue + "\") and contains(.,\"" + date + "\")]"));
    }


    public String getTitle(String documentGuid) {
        return waitForExpectedElement(By.xpath(String.format(DOCUMENT_WITH_GUID_PATTERN, documentGuid))).getText();
    }

    public WebElement descriptionWidgetAddButton(String title) {
        return findElement(By.xpath("//a[contains(@class, 'co_icon_addDesc') and contains(text(),'" + title + "')]"));
    }

    public WebElement descriptionWidgetInput() {
        return waitForElementVisible(By.xpath("//div[contains(@class, 'co_item_description_edit co_showState')]/input[@id='descriptionWidget_NaN']"));
    }

    public WebElement descriptionWidget() {
        return waitForElementVisible(By.xpath("//div[contains(@class, 'co_item_description_edit co_showState')]"));
    }


    public WebElement descriptionWidgetSaveButton() {
        return waitForElementVisible(By.xpath("//a[@class='co_document_previouslyviewed' and text()='Save']"));
    }

    public String getCustomDescriptionTextValue() {
        return waitForExpectedElement(By.xpath("//div[contains(@class, 'co_item_description_desc co_showState')]")).getText();
    }

    public String getContentTypeInTrash(String documentName) {
        return waitForExpectedElement(By.xpath("//*[contains(text(),\"" + documentName + "\")]/ancestor::tr/*[@class='co_detailsTable_type']")).getText();
    }

    public String getDateInTrash(String documentName) {
        return waitForExpectedElement(By.xpath("//*[contains(text(),\"" + documentName + "\")]/ancestor::tr/*[@class='co_detailsTable_date']")).getText();
    }

    public String getResourceType(String documentGuid) {
        return waitForExpectedElement(By.xpath(String.format(DOCUMENT_WITH_GUID_PATTERN, documentGuid) + "/ancestor::td//div[@class='cobalt_ro_documentDescription']/span[1]")).getText();
    }

    public String getDate(String documentGuid) {
        return waitForExpectedElement(By.xpath("//*[contains(@href,'" + documentGuid + "')]/ancestor::td/following-sibling::td[2]/span")).getText();
    }

    public boolean isLinkToDocumenttPresent() {
        return isExists(By.xpath("//*[contains(@href, '/Document/') and @name]"));
    }

    public boolean checkIfLinksVisible(List<String> ffhLinks) {
        int result = 0;
        for (String ffhLink : ffhLinks) {
            if (getElementByLinkText(ffhLink) != null) {
                LOG.info(ffhLink, "{} link is visvible for user");
                result++;
            }
        }
        return result == 0;
    }

    public WebElement documentCheckbox(String documentGuid) {
        return waitForExpectedElement(By.xpath("//input[contains(@value, '" + documentGuid + "')]"));
    }

    public WebElement selectAllDocumentsCheckbox() {
        return waitForExpectedElement(By.id("cobalt_foldering_ro_details_select_all"));
    }

    public WebElement deleteButton() {
        LOG.info("Should bug 916794 investigation - waiting for delete button in folders");
        return waitForExpectedElement(By.id("cobalt_ro_detail_trash"));
    }

    public WebElement folderingSearch() {
        return waitForExpectedElement(By.id("co_searchWithinWidget_textArea"));
    }

    public void selectMultipleFilters1() {
        WebElement select = null;
        try {
            select = waitForElementVisible(By.xpath("//*[@id='co_multifacet_selector_1']/*[contains(@class,'co_multifacet_select_multiple')]"));
            select.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            LOG.info("context", e);
            return;
        }
        new CommonMethods().clickElementUsingJS(select);
    }

    public WebElement applyFilters1() {
        return waitForExpectedElement(By.xpath("//*[@id='co_multifacet_selector_1']/*[contains(@class,'co_multifacet_apply')]"));
    }

    public WebElement cancelFilters1() {
        return waitForExpectedElement(CANCCEL_FILTERS_1_XPATH);
    }

    public boolean isCancelFilters1Displayed() {
        return isElementDisplayed(CANCCEL_FILTERS_1_XPATH);
    }

    public WebElement facetedViewSelectType(String facetName) {
        return waitForExpectedElement(By.xpath(String.format(FACET_PATH_PATTERN, facetName)));
    }

    public WebElement facetedViewSelectClientID(String clientID) {
        return waitForExpectedElement(By.xpath("//*[@id='facet_div_Client_IDs']//*[text()='" + clientID + "']/../descendant-or-self::input[@type='checkbox']"));
    }

    public WebElement facetedViewSelectContentType(String contentType) {
        return waitForExpectedElement(By.xpath(String.format(CONTENT_TYPE_PATH_PATTERN, contentType)));
    }

    public WebElement folderInLeftFrame(String folderName) {
        return waitForExpectedElement(By.xpath("//*[@id='co_researchOrganizerNavigationContainer']//a[text()='" + folderName + "']"));
    }

    public boolean isFolderPresentInLeftFrame(String folderName, String parentFolder) {
        return isExists(By.xpath(parentFolder.equals("root")
                ? "(//*[@id='co_researchOrganizerNavigationContainer']//*[text()='" + folderName
                + "']//ancestor::li[2]//*[text()=.])[1]"
                : "//*[@id='co_researchOrganizerNavigationContainer']//*[text()='" + folderName
                + "']//ancestor::li[2]//*[text()='" + parentFolder + "']"));
    }

    //leave as is due complicated logic for receiving final xpath
    public boolean isFolderAbsentOnSameLevelAsSpecifiedFolder(String folderName, String specifiedFolder) {
        WebElement folder = null;
        String treeDeepthOfSpecifiedFolder;
        try {
            if (specifiedFolder.equals("root")) {
                treeDeepthOfSpecifiedFolder = rootFolderLinkLeftFrame().findElement(By.xpath("/ancestor::li")).getAttribute("class");
            } else {
                treeDeepthOfSpecifiedFolder = findElement(By.xpath("//a[(contains(@class,'co_tree_name')) and contains(text(),'" + specifiedFolder + "')]/ancestor::li[@class!='co_tree_depth_0']")).getAttribute("class");
            }
            folder = findElement(By.xpath("//li[@class='" + treeDeepthOfSpecifiedFolder + "']/div//a[text()='" + folderName + "']"));
            folder.isDisplayed();
        } catch (NoSuchElementException e) {
            LOG.info("context", e);
            return false;
        }
        return true;
    }

    public WebElement getHistoryEmpty() {
        return waitForExpectedElement(By.xpath("//*[@id='co_researchOrg_detailsContainer']//td[@class='empty' and text()='No records found.']"));
    }


    public void checkFacetingIsAbsent() {
        waitForElementToDissappear(By.xpath("//*[@id='facet_div_Content Types' or @id='facet_div_Client_IDs']"));
    }

    public boolean hasDocumentFolderedSign(String documentId) {
        return isElementDisplayed(By.xpath("//*[contains(@href,'/Document/" + documentId + "')]/preceding::li[@class='co_document_icon_foldered']"));
    }


    public boolean hasDocumentPreviouslyViewedSign(String documentId) {
        return isElementDisplayed(By.xpath("//*[contains(@href,'/Document/" + documentId + "')]/preceding::li[@class='co_document_icon_previouslyviewed']"));
    }

    public WebElement shareFolder(String folderName) {
        LOG.info("Sharing folder {}", folderName);
        return waitForExpectedElement(By.xpath("//*[@class='co_shared_icon']"));
    }

    public WebElement expandSharedFolder(String folderName) {
        return findElement(By.xpath("//*[@id='cobalt_ro_sharedFolders_folderTree']//a[contains(@class,'co_tree_expand') and text()='Expand " + folderName + "']"));
    }

    public void checkFolderIsNowSharedMessage(String folderName) {
        waitForElementVisible(By.xpath("//*[@id='co_researchOrganizerNotification' and text()=\"'" + folderName + "' is now shared.\"]"));
    }

    public WebElement folderIconInLeftFrame(String folderName) {
        return waitForExpectedElement(By.xpath("//*[@id='co_researchFolderTree']//*[text()='" + folderName + "']//ancestor::div[contains(@class,'co_listItem')]"), 30);
    }

    public boolean isMetaDataPresentBelowTheDocumentTitleInFolder(String documentGuid, String metadata) {
        return isExists(By.xpath("//*[contains(@href,'" + documentGuid
                + "')]/ancestor::td//div[@class='cobalt_ro_documentDescription']/span[text()='" + metadata + "']"));
    }

    public boolean isMetaDataPresentBelowTheDocumentTitleOnHistoryPage(String position, String metadata) {
        return isExists(By.xpath("//*[@id='cobalt_foldering_ro_item_name_"
                + (Integer.parseInt(position) - 1)
                + "']/ancestor::td//div[@class='cobalt_ro_documentDescription']/span[text()='" + metadata + "']"));
    }

    public boolean isFolderPresentInSharedFolders(String folderName) {
        return isElementDisplayed(By.xpath("//*[@id='cobalt_ro_sharedFolders_folderTree']//*[text()='" + folderName + "']"));
    }

    public boolean isUserAbleToExpandSharedFolder(String folderName) {
        return isElementDisplayed(By.xpath("//*[@id='cobalt_ro_sharedFolders_folderTree']//a[contains(@class,'co_tree_expand') and text()='Expand " + folderName + "']"));
    }


    public void checkFolderIsNoLongerSharedMessage(String folderName) {
        waitForElementVisible(By.xpath("//*[@class='co_infoBox_message' and text()=\"'"
                + folderName + "' is no longer shared.\"]"));
    }

    /**
     * This method is used to find the notes added icon presence based on the given document index position in the history list.
     *
     * @param position
     * @return boolean
     */
    public boolean isNotesIconPresentForDocument(String position) {
        return isElementDisplayed(By.cssSelector("#cobalt_foldering_ro_item_icon_container_" + (Integer.parseInt(position) - 1)));
    }

    public By cancelByFilters() {
        return By.xpath("//*[@id='co_multifacet_selector_1']/*[contains(@class,'co_multifacet_cancel')]");
    }

    public By selectMultipleByFilters() {
        return By.xpath("//*[@id='co_multifacet_selector_1']/*[contains(@class,'co_multifacet_select_multiple')]");
    }

    /**
     * element that provides the search area within History page
     */
    public WebElement searchAreaWithinHistory() {

        return waitForElementVisible(By.name("SearchFacetSearchWithin-inputKeyword"));
    }

    /**
     * element that provides the total search term selected regardless of row number
     */
    public List<WebElement> totalSelectedSearchTerm() {

        return waitForElementsVisible(By.xpath("//div[@id='co_researchOrg_detailsTable']//tr//span[@class='co_searchTerm co_keyword']"));
    }


    /**
     * element that provides the search term selected
     */
    public List<WebElement> selectedRowWiseSearchTerm(int rowNumber) {

        return waitForElementsVisible(By.xpath("//div[@id='co_researchOrg_detailsTable']//tr[" + rowNumber + "]//span[@class='co_searchTerm co_keyword']"));
    }

    /**
     * element that provides the list of all history page result title links
     */
    public List<WebElement> historyPageResultTitleLinks() {
        return waitForElementsVisible(By.xpath("//div[@id='co_researchOrg_detailsTable']//tr//a[contains(@class,'cobalt_foldering_ro_documentlink')]"));
    }

    public List<WebElement> historyPageDocumentResultTitleLinks() {
        return waitForElementsVisible(By.xpath("//div[@id='co_researchOrg_detailsTable']//tr[@class='cobalt_ro_documentRow']//a[contains(@id,'cobalt_foldering_ro_item_name')]"));
    }

    public List<WebElement> historyPageSearchResultTitleLinks() {
        return waitForElementsVisible(By.xpath("//div[@id='co_researchOrg_detailsTable']//tr[@class='cobalt_ro_searchRow']//a[contains(@id,'cobalt_foldering_ro_item_name')]"));
    }

    /**
     * element that provides the list of all history page result title links
     */
    public By historyPageResultByTitleLink() {

        return By.xpath("//div[@id='co_researchOrg_detailsTable']//tr//a[contains(@id,'cobalt_foldering_ro_item_name')]");
    }

    /**
     * element that provides date picker dropdown
     */
    public WebElement historyPageDatePickerDropdownLink() {
        return waitForExpectedElement(By.id("co_dateWidget_Date_dropdown"));
    }

    /**
     * element that provides date picker dropdown
     */
    public List<WebElement> datePickerDropdownOptionsList() {

        return waitForElementsVisible(By.xpath("//div[@id='co_dateWidget_footer_Date']//li//a"));
    }

    /**
     * element that provides date picker dropdown by popup
     */
    public By datePickerDropdownByOptionsPopup() {

        return By.xpath("//div[@id='co_dateWidget_footer_1']");
    }

    /**
     * element that provides date picker dropdown selection
     */
    public WebElement datePickerDropdownSelectedOption() {

        return waitForElementVisible(By.id("co_dateWidget_Date_dropdown_span"));
    }

    /**
     * element that provides date picker dropdown option count
     */
    public WebElement datePickerOptionCount(String option) {

        return waitForElementVisible(By.xpath("//ul[@id='co_dateWidgetFixedList_1']//span[.='" + option + "']/..//span"));
    }

    /**
     * Check if folder present in main content frame
     *
     * @param folderName Folder name
     * @return True - if folder present, otherwise - false.
     */
    public boolean isFolderPresent(String folderName) {
        return waitForElementPresent(By.xpath("//tr[contains(@id, 'datatable')]//a[contains(@id, 'item') and .='" + folderName + "']")).isDisplayed();
    }

    /**
     * Get title of opened folder
     *
     * @return Selected folder title
     */
    public WebElement getOpenedFolderTitle() {
        return waitForExpectedElement(By.cssSelector(".co_folderTitle"));
    }

    /**
     * Click on column of table at folder view page
     *
     * @param documentColumn Column to get data from.
     * @see DocumentColumn
     */
    public WebElement getColumn(DocumentColumn documentColumn) {
        return waitForElementPresent(By.xpath("//th[.='" + documentColumn.getName() + "']"));
    }

    /**
     * Get content of selected folder.
     * IMPORTANT! Only documents data will be retrieved. Rows, which contains nested folders, will be ignored.
     *
     * @param column Column to get data from.
     * @return List with data from all presented document rows in selected folder for given column
     * @see DocumentColumn
     */
    public List<String> getDocumentsDataInColumn(DocumentColumn column) {
        String rowXpath = "//tr[contains(@id, 'datatable') and .//input[@type='checkbox']]";
        String tableCellsXpath;
        List<String> result = new ArrayList<>();
        switch (column) {
            case TITLE:
                tableCellsXpath = rowXpath + "//a[contains(@id, 'item')]";
                break;
            case CONTENT:
                tableCellsXpath = rowXpath + "//td[contains(@class, 'type')]//span";
                break;
            case DATE_ADDED:
                tableCellsXpath = rowXpath + "//td[contains(@class, 'date')]//span";
                break;
            default:
                throw new UnsupportedOperationException("There is no locator pattern for column " + column.getName());
        }
        List<WebElement> cells = waitForExpectedElements(By.xpath(tableCellsXpath));
        for (WebElement cell : cells) {
            result.add(cell.getText());
        }
        return result;
    }

    /**
     * Get document checkbox
     *
     * @param documentName Document name which checkbox should be clicked to
     * @return Element with document checkbox
     */
    public WebElement getDocumentCheckbox(String documentName) {
        return waitForExpectedElement(By.xpath("//tr[contains(@id, 'datatable') and contains(., '" + documentName + "')]//input[@type='checkbox']"));
    }

    /**
     * Get "Save to folder" button
     *
     * @return Element with "Save to folder" button
     */
    public WebElement getSaveToFolderButton() {
        return waitForExpectedElement(By.xpath("//li[contains(@id, 'saveTo')]//a"));
    }

    /**
     * Check if document with name is exists on the page
     *
     * @param documentName Document name which checkbox should be clicked to
     * @return True - if doc exists, otherwise - false.
     */
    public boolean isDocumentExists(String documentName) {
        return isElementDisplayed(
                By.xpath("//tr[contains(@id, 'datatable') and contains(., " + Quotes.escape(documentName) + ")]//input[@type='checkbox']"));
    }

    /**
     * Check if document with guid in the document link is exists on the page
     *
     * @param guid Document guid in link which should be visible
     * @return True - if doc exists, otherwise - false.
     */
    public boolean isDocumentWithGuidExists(String guid) {
        return isElementDisplayed(
                By.xpath("//tr[contains(@id, 'datatable')]//a[contains(@href, '" + guid + "')]"));
    }

    /**
     * Get element with link of expected document GUID
     *
     * @param guid Document guid in link which
     * @return Element with link to the document with GUID
     */
    public WebElement getLinkWithDocGuid(String guid) {
        return waitForExpectedElement(
                By.xpath("//tr[contains(@id, 'datatable')]//a[contains(@href, '" + guid + "')]"));
    }

    /**
     * Check if document with name is not exists on the page
     *
     * @param documentName Document name which checkbox should be clicked to
     * @return True = if doc is not exists, otherwise - false.
     */
    public boolean isDocumentAbsent(String documentName) {
        return waitForElementToDissappear(By.xpath("//tr[contains(@id, 'datatable') and contains(., '" + documentName + "')]//input[@type='checkbox']"));
    }

    /**
     * element that provides date picker for Before, After, On and From Date Text-boxes
     */

    public WebElement datePickerBeforeAfterOnFromTextbox(String option) {

        return waitForElementVisible(By.xpath("//a[.='" + option + "']/..//label/following-sibling::input"));
    }

    public WebElement datePickerUntilDateTextbox() {

        return waitForElementVisible(By.id("co_dateWidgetCustomRangeUntilText_Date_fromUntil"));
    }

    public WebElement datePikcerOKButton(String option) {

        return waitForElementVisible(By.xpath("//a[.='" + option + "']/..//input[@class='co_button_submit_small co_primaryBtn']"));
    }

    public WebElement documentCheckboxByNumber(String number) {
        int numberOfDocument = Integer.parseInt(number) - 1;
        return waitForExpectedElement(By.xpath("//input[@id='cobalt_foldering_ro_select_checkbox_" + numberOfDocument + "']"));
    }

    /**
     * This method clicks on given search string history.
     *
     * @param searchTerm
     */
    public void clickOnSearchTermInHistory(String searchTerm) {
        try {
            waitForExpectedElement(By.xpath(String.format("//a[contains(@id,'cobalt_foldering_ro_item_name')][contains(text(),'%s')]", searchTerm))).click();
        } catch (TimeoutException te) {
            throw new PageOperationException("Exceeded time to find the search Term " + searchTerm + " in History. " + te.getMessage());
        }
    }

    /**
     * by element that returns History filter link (Documents, Searches, All History)
     */
    public By historyFacetByLink(String option) {
        return By.xpath("//div[@id='co_ro_history_search_types_container']//a[text()='" + option + "']");
    }

    /**
     * The title of search within text area
     *
     * @return Element with title
     */
    public WebElement getSearchWithinWidgetTitle() {
        return waitForExpectedElement(By.id("co_searchWithinWidget_header"));
    }

    /**
     * Check if there are some visible elements below the buttons on search within popup. For verification that we don't
     * have any text below the buttons
     *
     * @return True - if there are no visible elements after buttons, false - otherwise
     */
    public boolean areElementsAfterButtonDisplayed() {
        return isElementDisplayed(By.xpath("//div[@id='co_searchWithinWidget_buttons']/following-sibling::*"));
    }

    /**
     * Get the search within results popup
     *
     * @return Element with the search within results popup
     */
    public WebElement getSearchWithinPopup() {
        return waitForExpectedElement(By.id("co_searchWithinWidget"));
    }

    /**
     * search button on search within results pop up
     */

    public WebElement searchButton() {
        return waitForExpectedElement(By.id("co_searchWithinWidget_searchButton"));
    }

    /**
     * cases search result count following search within history
     */

    public WebElement casesSearchResultCount() {

        return waitForExpectedElement(By.xpath("//*[@class='co_detailsTable_subHeader'][contains(text(),'Cases')]"));
    }

    /**
     * Wait while table with content on the FFH pages will be loaded
     */
    public void waitContentToLoad() {
        // Wait while loading picture will appears
        try {
            waitForElementVisible(By.id("folderingProgress"));
        } catch (TimeoutException e) {
            LOG.info("FFH loading image did not appeared");
        }

        // Wait while loading picture will disappears
        waitForElementInvisible(By.id("folderingProgress"));

        // Wait while overlay which blocks from interacting with a page will disappears
        waitForElementInvisible(By.id("co_searchDisableDiv"));

        // Wait while table with results will be present
        waitForElementVisible(By.xpath("//table[@class='co_detailsTable']/tbody//tr"));
    }

    /**
     * Is document for the content type present E.g.:
     * ---------------------------------
     * Legislation
     * ---------------------------------
     * -- Document1
     * -- Document2
     * ---------------------------------
     * Know-how
     * ---------------------------------
     * -- Document3
     * -- Document4
     * To get to know that "Document3" related to "Know-how" is present, you can use isHeaderContentTypeForDocumentExists("Know-how", "Document3");
     * WARNING! It is hard to write reliable xpath due to HTML markup (rows with sub headers and data on the same level).
     * Therefore, the following approach was applied.
     * <p>
     * WARNING! If you use WebDriver implicitly wait > 0, this method could be veeeery sloooooow
     *
     * @param contentType Document content type
     * @param resultText  Document title or some another text in the search result
     * @return True - if document related ot expected content type and it is visible. False - otherwise.
     */
    public boolean isHeaderContentTypeForDocumentExists(String contentType, String resultText) {
        List<WebElement> rows = waitForElementsVisible(By.xpath("//table[@class='co_detailsTable']//tbody/tr"));
        String subHeader = "";
        By subHeaderChildBy = By.xpath("./td[@class='co_detailsTable_subHeader']");
        for (WebElement row : rows) {

            // if current row contains sub header - grab it and continue
            if (!row.findElements(subHeaderChildBy).isEmpty()) {
                subHeader = row.findElement(subHeaderChildBy).getText();
                continue;
            }

            // Hooray! Record found :)
            if (subHeader.contains(contentType) && row.getText().contains(resultText)) {
                return true;
            }
        }

        // All results were iterated but nothing expected was found
        return false;
    }

    /**
     * Get all facets checkboxes on the research organizer page
     *
     * @return List with checkboxes for the facets
     */
    public List<WebElement> getAllFacetsCheckboxes() {
        return waitForExpectedElements(By.xpath("//input[@type='checkbox' and contains(@onclick, 'co_facets')]"));
    }

    /**
     * List of elements with all facet names in "Content Type" facet group
     *
     * @return List with elements which contain all facet names
     */
    public List<WebElement> getContentTypeFacetNames() {
        return waitForExpectedElements(By.xpath("//div[contains(@id, 'facet_div') and contains(., 'Content')]//label"));
    }

    /**
     * Get results count for the facet with name in "Content Type" group
     *
     * @param facetName Facet name
     * @return Element with count
     */
    public WebElement getCountsForContentTypeFacet(String facetName) {
        return waitForExpectedElement(By.xpath("//div[contains(@id, 'facet_div') and contains(., 'Content')]//li[contains(., " +
                Quotes.escape(facetName) + ")]/span[@class='co_facetCount']"));
    }

    /**
     * Get the content type title of results
     *
     * @param contentType Content type name
     * @return Element with content type title
     */
    public WebElement getResultsContentTypeTitle(String contentType) {
        return waitForExpectedElement(By.xpath("//table[@class='co_detailsTable']//td[contains(@class, 'Header') and contains(., " +
                Quotes.escape(contentType) + ")]"));
    }

    /**
     * Get checkbox for particular facet
     *
     * @param facetName Facet name
     * @return Element with facet checkbox
     */
    public WebElement getFacetCheckbox(String facetName) {
        return waitForExpectedElement(By.xpath("//div[contains(@id, 'facet_div')]//li[contains(., " +
                Quotes.escape(facetName) + ")]/input[@type='checkbox']"));
    }

    /**
     * Get the field (title inclusive) for result with the number in description cell
     *
     * @param resultNumber Result number
     * @param fieldName    Field name (e.g., Search Type, Content)
     * @return Element with data including field title
     */
    public WebElement getDescriptionFieldForResult(String resultNumber, String fieldName) {
        return waitForExpectedElement(
                By.xpath("//tr[contains(@id, 'datatable-row')][" + resultNumber + "]" +
                        "/td[contains(@class, 'content')]//div[contains(@class, 'cobalt_ro_search') and starts-with(.,'" + fieldName + ":')]"));

    }

    public Boolean isDateDisplayed(String dateOption) {
        return isElementDisplayed(By.xpath("//*[@id='datatable-row-0']//div[3]/strong[contains(.,'Date')]/..[contains(.,'" + dateOption + "')]"));
    }


}