package com.thomsonreuters.pageobjects.pages.annotations;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * This page object is to depict the annotations blocks present on document page and this class holds the elements and functions to handle the annotations operations.
 * <p>
 * Created by UC186961 on 17/08/2015.
 */

public class SharedAnnotationsPage extends AbstractPage {

    public static final int TIMEOUT_2_SECONDS = 2;
    public static final int TIMEOUT_5_SECONDS = 5;
    private By IFRAME_LOCATOR = By.cssSelector("div.co_dropdownBoxExpanded .co_richEditor.co_noteArea.mce-content-body");
    private int urlFindingCount = 0;
    private By SAVE_INLINE_BUTTON = By.xpath("//div[@id='co_dropdownBody_1000']//input[@value='Save']");

    private TinyMceEditor tinyMceEditor;
    private CommonMethods commonMethods;

    private static final Logger LOG = LoggerFactory.getLogger(SharedAnnotationsPage.class);

    public SharedAnnotationsPage() {
        tinyMceEditor = new TinyMceEditor();
        commonMethods = new CommonMethods();
    }

    /*
     *  This enum is used for elements visiblity.
     */
    public enum ExpectedResult {
        VISIBLE,
        NOT_VISIBLE;
    }

    /**
     * This method is to verify the annotations textbox is displayed or not.
     *
     * @return boolean
     */

    public boolean isTextBoxDisplayed() {
        return isElementDisplayed(IFRAME_LOCATOR);
    }

    public boolean isBodyAnnotationPresent() {
        return isExists(By.cssSelector("div.co_viewNote:not([style*='none'])"));
    }

    public boolean isBodyInlineAnnotationNotPresent() {
        return isExists(By.cssSelector("div.co_notes.blue.co_hideState"));
    }

    public WebElement getCollapsOption() {
        return waitForExpectedElement(By.xpath("//div[@class='co_dropdownTab']//a[@class='co_noteIcon_toggle co_noteIcon_close']"));
    }

    public WebElement annotationNotesWidget() {
        return waitForExpectedElement(By.id("co_documentNotes"));
    }

    public void clickOnCollapsOptionAtTheTop() {
        getCollapsOption().click();
        LOG.info("Collaps option was clicked");
    }

    public WebElement getExpandOption() {
        return waitForExpectedElement(By.xpath("//div[@class='co_dropdownTab']//a[@class='co_noteIcon_toggle']"));
    }

    public void clickOnExpandOptionAtTheTop() {
        getExpandOption().click();
        LOG.info("Expand option was clicked");
    }

    public WebElement getWebElementCountAtTheTop() {
        return waitForExpectedElement(By.cssSelector("span.co_noteIcon_count"));
    }

    public void removeUserFromGroupAndSaveGroup(String user){
        getUserFromGroupMember(user).click();
        getSaveGroupButton().click();
    }

    public WebElement getUserFromGroupMember(String user){
        return waitForExpectedElement(By.xpath("//div[@id='coid_contacts_newGroupContactsInput']//a[contains(text(), '" + user + "')]"));
    }
    public String getUserCountForGroup(){
        return getCountForGroupElement().getText();
    }

    public WebElement getCountForGroupElement(){
        return waitForExpectedElement(By.xpath("//span[@class='co_contacts_groupsCount']/span"));
    }


    public String getCountAtTheTop() {
        return getWebElementCountAtTheTop().getText();
    }

    /**
     * This method is to clear the existing text on it and inserts the given input value into the annotations textbox present on tinymce editor
     *
     * @param input
     */
    public void insertInput(String input) {
        tinyMceEditor.clearAll();
        tinyMceEditor.addContent(input);
    }

    public String getTextFromPopUpAboutDeletedGroup(){
        return getPopUpAboutDeletedGroup().getText();
    }

    public WebElement getPopUpAboutDeletedGroup(){
        return  waitForExpectedElement(By.xpath("//div[@id='coid_contacts_groupDeleteInfoBox']//div[@class='co_infoBox_message']"));
    }

    /**
     * This private method is used to find the warning message presence on document and returns the boolean value accordingly.
     *
     * @return boolean
     */

    public boolean isWarningMessagePresent() {
        return isElementDisplayed(By.cssSelector(".co_overlayBox_optionsBottom .co_NotesLightBox_okbutton"));
    }

    /**
     * This private method is to get the displayed warning message webelement on document.
     *
     * @return WebElement
     */
//    private WebElement getWarningMessageOKButton() {
//        try {
//            return waitForExpectedElement(By.cssSelector(".co_overlayBox_optionsBottom .co_NotesLightBox_okbutton"), TIMEOUT_2_SECONDS);
//        } catch (TimeoutException te) {
//            throw new PageOperationException("Exceeded time to find the Annotations Warning message ok button" + te.getMessage());
//        }
//    }

    public WebElement getGroupsListItem(){
        return waitForExpectedElement(By.cssSelector("#coid_contacts_groupsListItems a[role='checkbox']"));
    }

    public WebElement getEditGroupOption(){
        return waitForExpectedElement(By.cssSelector("a.co_contacts_edit"));
    }

    public void clickOnEditOption(){
        getEditGroupOption().click();
    }

    public void removeGroup(){
        getRemoveGroupOption().click();
    }

    public WebElement getRemoveGroupOption(){
        return waitForExpectedElement(By.cssSelector("a.co_contacts_delete"));
    }

    /**
     * This method does the inserting the given input value into WLN annotations textbox.
     *
     * @param input
     */
    public void insertInputInWLNAnnotationTextBox(String input) {
        try {
            //TODO [Phase1] verify if waitForExpectedElement works similar to waitForExpectedElement
            waitForExpectedElement(By.cssSelector(".co_noteArea")).sendKeys(input);
        } catch (PageOperationException te) {
            throw new PageOperationException("Exceeded time to find the Annotations text box in WLN" + te.getMessage());
        }
    }


    /**
     * This method is to adds the given input value to existing text presents in the annotations textbox.
     *
     * @param input
     */
    public void amendInput(String input) {
        tinyMceEditor.addContent(input);
    }

    public WebElement getAnnotationTextBox(){
        return tinyMceEditor.getTinyMceTextBox();
    }

    public void copyTextFromClipboardToTinyMceTextBox(){
        tinyMceEditor.getTinyMceTextBox().sendKeys(Keys.CONTROL + "v");
    }


    /**
     * Saves the annotation and waits until the document is loaded with all elements.
     */
    public void saveAnnotation() {
        try {
            WebElement saveButton = waitForExpectedElement(By.cssSelector("input[value='Save']"));
            waitForElementsClickable(By.cssSelector("input[value='Save']"));
            saveButton.click();
            waitForElementInvisible(By.cssSelector("input[value='Save']"));
            waitForPageToLoad();
            waitForPageToLoadAndJQueryProcessing();
        } catch (PageOperationException | TimeoutException te) {
            throw new PageOperationException("Exceeded time to find the save button" + te.getMessage());
        }
    }

    public void saveAnnotationAfterAtTheTop() {
        try {
            WebElement saveButton = waitForExpectedElement(SAVE_INLINE_BUTTON);
            waitForElementsClickable(SAVE_INLINE_BUTTON);
            saveButton.click();
            waitForElementInvisible(SAVE_INLINE_BUTTON);
            waitForPageToLoad();
            waitForPageToLoadAndJQueryProcessing();
        } catch (PageOperationException | TimeoutException te) {
            throw new PageOperationException("Exceeded time to find the save button" + te.getMessage());
        }
    }

    public void closeDisclaimer() {
        getCloseDisclaimer().click();
    }

    public WebElement getCloseDisclaimer() {
        return waitForExpectedElement(By.cssSelector("a#DisclaimerMessageClose"));
    }

    public boolean isDisclaimerPresent(){
        return isExists(By.cssSelector("a#DisclaimerMessageClose"));
    }

    public void waitForDisclaimerAbsent(){
        //TODO need to verify if waitForElementInvisible works similar to waitForElementToDissappear
        waitForElementInvisible(By.cssSelector("a#DisclaimerMessageClose"));
    }

    public void clickOnCancelButton() {
        getCancelButton().click();
    }

    public WebElement getCancelButton() {
        return waitForExpectedElement(By.cssSelector("a.co_note_cancelbutton"));
    }


    public void clickOnInlineAnnotationIcon() {
        getInlineAnnotationIcon().click();
    }

    public void waitForInlineIconPresentAfterUndo() {
        waitForElementsClickable(By.cssSelector("span#co_noteHolder_1"));
    }

    public WebElement getInlineAnnotationIcon() {
        return waitForExpectedElement(By.cssSelector("span#co_noteHolder_0"));
    }

    public boolean isHighlightedTextPresent(String colour) {
        return isExists(By.xpath("//span[@class='co_hl " + colour + " co_selection_0']"));
    }

    public void clickOnHighlightedText(String colour) {
        getHightlightedText(colour).click();
    }

    public void clickOnDeleteHighlight() {
        getDeleteHighlight().click();
    }

    public WebElement getDeleteHighlight() {
        return waitForExpectedElement(By.cssSelector("li#co_highlightMenuDeleteHighlight"));
    }

    public WebElement getHightlightedText(String colour) {
        return waitForExpectedElement(By.xpath("//span[@class='co_hl " + colour + " co_selection_0']"));
    }

    public String getClipBoard() {
        try {
            return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (HeadlessException e) {
            LOG.error("HeadlessException: ", e);
        } catch (UnsupportedFlavorException e) {
            LOG.error("UnsupportedFlavorException: ", e);
        } catch (IOException e) {
            LOG.error("IOException: ", e);
        }
        return null;
    }


    public void clickOnMinimizeOptionForInlineAnnotation() {
        getCollapsedInlineAnnotation().click();
    }

    public WebElement getCollapsedInlineAnnotation() {
        return waitForExpectedElement(By.cssSelector("a.co_noteMinimize.co_widget_collapseIcon"));
    }

    /**
     * Finding the save button enabled or note - returns true if save button is enabled otherwise false.
     *
     * @return boolean
     */
//    public boolean isSaveAnnotationEnabled() {
//        try {
//            return waitForExpectedElement(By.cssSelector("input[value='Save']"), TIMEOUT_5_SECONDS).isEnabled();
//        } catch (TimeoutException te) {
//            return false;
//        }
//    }

    public boolean isSaveAnnotationEnabled() {
        return isElementDisplayed(By.cssSelector("input[value='Save']"))
                ? waitForExpectedElement(By.cssSelector("input[value='Save']"), TIMEOUT_5_SECONDS).isEnabled() : false;
    }

    /**
     * This method is to select the cancel button present on new/edit annotations.
     */
    public void cancelSavingAnnotation() {
        try {
            waitForExpectedElement(By.linkText("Cancel")).click();
        } catch (TimeoutException te) {
            throw new PageOperationException("Exceeded time to find the cancel button" + te.getMessage());
        }
    }

    /**
     * Verifies the sharing social icon visibility based on the given annotations text.
     * Returns true if icon is visible beside of the annotation created by value otherwise false
     *
     * @param input
     * @return boolean
     */


    public boolean isSharingIconVisible(String input) {
        return isElementDisplayed(By.xpath("//div[@class='co_viewNoteText mce-content-body']/p[text()='" + input + "']/../../div[@class='co_noteHeader']"));
    }

    /**
     * Verifies the saved annotation is displayed back on document annotations list or not.
     * Returns true if annotations is displayed otherwise false
     *
     * @return boolean
     */

    public boolean isAnnotationsDisplayed() {
        return isElementDisplayed(By.cssSelector(".co_viewNoteText.mce-content-body"));
    }

    public WebElement getContactPagePrevious(){
        return waitForExpectedElement(By.id("contactPagePrevious"));
    }

    public boolean isContactPagePreviousElementDisabled(){
        return isExists(By.xpath("//*[@class='co_prev co_disabled']"));
    }

    public WebElement getContactPageFirst(){
        return waitForExpectedElement(By.id("contactPageFirst"));
    }

    public boolean isContactPageFirstElementDisabled(){
        return isExists(By.xpath("//*[@class='co_first co_disabled']"));
    }

    public WebElement getContactPageNext(){
        return waitForExpectedElement(By.id("contactPageNext"));
    }

    public boolean isContactPageNextElementDisabled(){
        return isExists(By.xpath("//*[@class='co_next co_disabled']"));
    }

    public WebElement getContactPageLast(){
        return waitForExpectedElement(By.id("contactPageLast"));
    }

    public boolean isContactPageLastElementDisabled(){
        return isExists(By.xpath("//*[@class='co_last co_disabled']"));
    }

    /**
     * Verifies the annotations count is displayed on show/hide icon.
     * Returns true if annotations count is displayed otherwise false
     *
     * @return boolean
     */

    public boolean isAnnotationsCountDisplayed() {
        return isElementDisplayed(By.id("co_ToggleAnnotationCount"));
    }

    /**
     * Gets the displayed Notes count value on show/hide notes icon.
     *
     * @return int
     */
    public int getNotesCountFromShowAndHideIcon() {
        try {
            //TODO [Phase1] verify if waitForExpectedElement works similar to waitForExpectedElement
            return Integer.parseInt(waitForExpectedElement(By.id("co_ToggleAnnotationCount")).getText());
        } catch (PageOperationException | NumberFormatException poe) {
        }
        return 0;
    }

    /**
     * Gets the displayed total doc level notes elements count.
     *
     * @return int
     */
    public int getTotalNotesOnDocument() {
        return getInlineNotesCount() + getAnnotationsElements().size();
    }

    /**
     * Gets the displayed total inline notes elements count.
     *
     * @return int
     */
    private int getInlineNotesCount() {
        try {
            //TODO [Phase1] verify if waitForExpectedElements works similar to waitForExpectedElements
            return waitForExpectedElements(By.cssSelector("span.co_noteHolderActive>div.co_hideState+a[title='Minimize']")).size();
        } catch (PageOperationException poe) {
        }
        return 0;
    }

    /**
     * This does the deletion of the all inline notes present on document.
     */
    public void deleteInlineAnnotations() {
        try {
            //TODO [Phase1] verify if waitForExpectedElements works similar to waitForExpectedElements
            for (WebElement inlineNote : waitForExpectedElements(By.cssSelector("span.co_noteHolderActive>div.co_hideState+a[title='Minimize']"))) {
                inlineNote.click();
                WebElement ele = inlineNote.findElement(By.xpath("./.."));
                ele.findElement(By.className("co_viewNote")).click();
                getDeleteElement().click();
                if (isUndoButtonDisplayed() && isCloseButtonDisplayed()) {
                    try {
                        waitForElementInvisible(By.id(inlineNote.getAttribute("id")));
                    } catch (StaleElementReferenceException sle) {
                        LOG.warn("Inline note is not visible");
                    }
                }
            }
        } catch (PageOperationException | NoSuchElementException te) {
            throw new PageOperationException("Unable to select delete icon on inline note : ");
        }
    }

    /**
     * Verifies the paragraph style is added to entered text in notes textbox.
     * Returns true if paragraph is default style otherwise false
     *
     * @return boolean
     */

    public boolean isParagraphStyleAddedAsDefault(String input) {
        return isElementDisplayed(By.xpath("//p[text()='" + input + "']"));
    }

    /**
     * Verifies the given input annotation is displayed or not after saving it and
     * depends on the given ExpectedResult enum value the way of finding will be vary to save the waiting time.
     * Returns true if annotation is as expected result value otherwise false
     *
     * @return boolean
     */
    public boolean isSavedAnnotationDisplayed(String input, ExpectedResult result) {
        By pathToAnnotation = By.xpath("//div[@class='co_viewNoteText mce-content-body']/p[text()='" + input + "']");
        try {
            if (result.equals(ExpectedResult.VISIBLE)) {
                return waitForExpectedElement(pathToAnnotation, 120).isDisplayed();
            } else if (result.equals(ExpectedResult.NOT_VISIBLE)) {
                return findElement(pathToAnnotation).isDisplayed();
            }
        } catch (TimeoutException | NoSuchElementException te) {
        } catch (StaleElementReferenceException sle) {
            isSavedAnnotationDisplayed(input, result);
        }
        return false;
    }

    /**
     * Verifies the given input annotation is displayed or not in wln application
     * Returns true if annotation is displayed otherwise false
     *
     * @return boolean
     */
    public boolean isSavedAnnotationDisplayedInWLN(String input) {
        boolean result = false;
        try {
            for (WebElement element : waitForExpectedElements(By.cssSelector(".co_viewNoteText"))) {
                if (element.getText().contains(input)) {
                    result = true;
                }
            }
        } catch (PageOperationException te) {
            LOG.info("The input annotation is not displayed in WLN", te);
        } catch (TimeoutException e) {
            LOG.info("There are no annotations on the result page", e);
        }
        return result;
    }

    /**
     * Verifies the given input annotation is displayed as link.
     * Returns true if annotation text link is displayed otherwise false
     *
     * @param input
     * @return boolean
     */

    public boolean isSavedAnnotationTextDisplayedAsLink(String input) {
        return isElementDisplayed(By.xpath("//div[@class='co_viewNoteText mce-content-body']/p/a[text()='" + input + "']"));
    }

    /**
     * Verifies the given input annotation is displayed with link as part of its text.
     * Returns true if annotation text link is displayed otherwise false
     *
     * @param textOrUrl
     * @return boolean
     */

    public boolean isSavedAnnotationTextDisplayedWithLink(String textOrUrl) {
        return isElementDisplayed(By.xpath("//div[@class='co_viewNoteText mce-content-body']/p[contains(text(),'" + textOrUrl + "')]"));
    }

    /**
     * Verifies the given input annotation is saved and displayed with selected style formattype.
     * depends on the given ExpectedResult enum value the way of finding will be vary to save the waiting time.
     * Returns true if selected style formatted annotation is displayed otherwise false
     *
     * @param formatType
     * @param result
     * @param input
     * @return boolean
     */
    public boolean isSavedAnnotationDisplayedWithSelectedStyle(FormatType formatType, ExpectedResult result, String... input) {
        By pathToAnnotation = By.xpath(String.format("//div[@class='co_viewNoteText mce-content-body']" + formatType.getSavedTextCSS(), input));
        try {
            for (String text : input) {
                if (ExpectedResult.VISIBLE.equals(result)) {
                    if (!waitForElementPresent(pathToAnnotation).isDisplayed()) {
                        return false;
                    }
                } else if (ExpectedResult.NOT_VISIBLE.equals(result)) {
                    if (!findElement(pathToAnnotation).isDisplayed()) {
                        return false;
                    }
                }
            }
            return true;
        } catch (TimeoutException | NoSuchElementException te) {
            return false;
        }
    }

    /**
     * This method is to verify the complete metadata of the given annotation text.
     * And returns the boolean value is true if metadata is displayed otherwise false.
     *
     * @param input
     * @return boolean
     */
    public boolean isMetaDataDispalyed(String input) {

        List<WebElement> list = getAnnotationsElements();
        AnnotationMetaData metaData = null;
        Iterator<WebElement> iterator = list.iterator();

        while (iterator.hasNext()) {
            WebElement element = (WebElement) iterator.next();
            if (element.getText().contains(input)) {
                metaData = new AnnotationMetaData(element.findElement(By.xpath("./..")));
                break;
            }
        }

        try {
            if (metaData != null) {
                if (!StringUtils.isEmpty(metaData.getDate()) && !StringUtils.isEmpty(metaData.getTime()) && !StringUtils.isEmpty(metaData.getCreatedBy()) && metaData.getFontWeightValueForCreatedBy().equalsIgnoreCase("600")) {
                    return true;
                } else {
                    LOG.warn("Meta data is missing for annotations.");
                }
            }
        } catch (PageOperationException te) {
            LOG.info("The metadata can not be displayed", te);
        }
        return false;
    }

    public boolean isAddAnnotationIconPresentAtTheTop() {
        return isExists(By.cssSelector("a.co_noteIcon_add"));
    }

    public void clickOnAddAnnotationIconAtTheTop() {
        getAddAnnotationIconAtTheTop().click();
    }

    public WebElement getAddAnnotationIconAtTheTop() {
        return waitForExpectedElement(By.cssSelector("a.co_noteIcon_add"));
    }

    /**
     * This method is to select the existing annotation and makes it editable.
     *
     * @param input
     */
    public void selectEditMode(String input) {
        try {
            for (WebElement element : getAnnotationsElements()) {
                if (element.getText().contains(input)) {
                    element.click();
                    break;
                }
            }
        } catch (StaleElementReferenceException se) {
            selectEditMode(input);
        }
    }

    public void clickOnPreviouslySharedLink(){
        getPreviouslySharedLink().click();
    }

    public WebElement getPreviouslySharedLink(){
        return waitForExpectedElement(By.id("previousId"));
    }

    public void removeUserWhomSharedAnnotation(String userName){
        getUserWhomSharedAnnotation(userName).click();
    }

    public WebElement getUserWhomSharedAnnotation(String userName) {
        return waitForExpectedElement(By.xpath("//ul[@id='coid_contacts_addedContactsInput']//span[contains(text(), '" + userName + "')]"));
    }
    public boolean isUserExist(String userName){
        return isExists(By.xpath("//ul[@id='coid_contacts_addedContactsInput']//span[contains(text(), '" + userName + "')]"));
    }


    public void clickOnSharedWithLink(){
        getSharedWithLink().click();
    }

    public WebElement getSharedWithLink() {
        return waitForExpectedElement(By.cssSelector("span.sharedWithText"));
    }

    public String getNameOfGroupMember(int memberNumber){
        return getGroupMember(memberNumber).getText();
    }

    public WebElement getGroupMember(int memberNumber){
        memberNumber = memberNumber + 1;
        return waitForExpectedElement(By.xpath("//div[@id='coid_contacts_newGroupContactsInput']/ul/li[" + memberNumber + "]/a"));
    }

    /**
     * Returns the boolean value if given input annotation is editable otherwise false.
     *
     * @param input
     * @return boolean
     */
    public boolean isEditModeDisplayedWithText(String input) {
        try {
            //TODO [Phase1] verify if waitForExpectedElement works similar to waitForExpectedElement
            WebElement element = waitForExpectedElement(IFRAME_LOCATOR);
            if (element.isDisplayed()) {
                return tinyMceEditor.getText().equals(input);
            }
        } catch (TimeoutException te) {
            LOG.info("Exceeded time to verify whether the input annotation is editable", te);
        }
        return false;
    }

    /**
     * Verifies the given input annotation delete icon is displayed.
     * Returns true if annotation delete link is displayed otherwise false
     *
     * @param textOrUrl
     * @return boolean
     */
    public boolean isDeleteIconDisplayedOnAnnotation(String textOrUrl) {
        return isElementDisplayed(By.xpath("//div[@class='co_dropdownBoxExpanded'][contains(@style,'visible')]//a[@class='co_noteDelete']"));
    }

    /**
     * Gets the delete icon element on editable annotation textbox
     *
     * @return webelement
     */
    private WebElement getDeleteElement() {
        //TODO [Phase1] verify if waitForExpectedElement works similar to waitForExpectedElement
        return waitForExpectedElement(By.xpath("//div[@class='co_dropdownBoxExpanded'][contains(@style,'visible')]//a[@class='co_noteDelete']"));
    }

    /**
     * Deletes the given annotation
     *
     * @param input
     */
    public void deleteAnnotation(String input) {
        try {
        	waitForPageSourceChangedAfterClick(getDeleteElement());
        } catch (PageOperationException te) {
            throw new PageOperationException("Unable to select delete icon on annotation : " + input + " - " + te.getMessage());
        } catch (WebDriverException we) {
            deleteAnnotation(input);
        }
    }

    /**
     * Deletes all annotations for the given user
     *
     * @param user
     */
    public void deleteAllAnnotations(String user) {
        AnnotationMetaData metaData = null;
        try {
            for (WebElement element : getAnnotationsElements()) {
                metaData = new AnnotationMetaData(element.findElement(By.xpath("./..")));
                if (metaData.getCreatedBy().equals(user)) {
                    element.click();
                    getDeleteElement().click();
                    if (isUndoButtonDisplayed() && isCloseButtonDisplayed()) {
                        try {
                            waitForElementInvisible(By.id(element.getAttribute("id")));
                        } catch (StaleElementReferenceException sle) {
                        }
                    }
                }
            }
        } catch (StaleElementReferenceException sle) {
            deleteAnnotation(user);
        } catch (PageOperationException te) {
            throw new PageOperationException("Unable to select delete icon on annotations : " + te.getCause());
        }
    }

    /**
     * Gets all annotations present in doc level.
     *
     * @return List<WebElement>
     */
    private List<WebElement> getAnnotationsElements() {
        int count = 0;
        try {
            WebElement element = null;
            try {
                element = waitForExpectedElement(By.cssSelector(".co_viewNoteText.mce-content-body"), TIMEOUT_5_SECONDS);
            } catch (TimeoutException te) {
                LOG.info("Exceeded time to wait an expected element");
            }
            if (element != null && element.isDisplayed()) {
                return waitForExpectedElements(By.cssSelector(".co_viewNoteText.mce-content-body"), TIMEOUT_5_SECONDS);
            }
        } catch (TimeoutException te) {
            if (count < 3) {
                getAnnotationsElements();
            }
            count++;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * Verifies the given deleted annotation is displayed again.
     * Returns true if delted annotation is displayed otherwise false
     *
     * @param message
     * @return boolean
     */
//    public boolean isDeleteNotesDisplayed(String message) {
//        try {
//            return waitForExpectedElement(By.cssSelector(".co_notes .co_infoBox_message")).getText().contains(message);
//        } catch (PageOperationException te) {
//        }
//        return false;
//    }

    public boolean isDeleteNotesDisplayed(String message) {
        //TODO [Phase1] verify if waitForExpectedElement works similar to waitForExpectedElement
        return isElementDisplayed(By.cssSelector(".co_notes .co_infoBox_message"))
                ? waitForExpectedElement(By.cssSelector(".co_notes .co_infoBox_message")).getText().contains(message)
                : false;
    }


    /**
     * Verifies the Undo button is displaed after delete operation successful.
     * Returns true if undo button is displayed otherwise false
     *
     * @return boolean
     */

    public boolean isUndoButtonDisplayed() {
        return isElementDisplayed(By.className("co_notes_undoLink"));
    }

    /**
     * Verifies the Close button is displaed after delete operation successful.
     * Returns true if close button is displayed otherwise false
     *
     * @return boolean
     */

    public boolean isCloseButtonDisplayed() {
        return isElementDisplayed(By.className("co_notes_closeLink"));
    }

    /**
     * Clicking on undo delete button to undo the deleted annotation.
     */
    public void undoDelete() {
        try {
            //TODO [Phase1] verify if waitForExpectedElement works similar to waitForExpectedElement
            waitForExpectedElement(By.className("co_notes_undoLink")).click();
        } catch (PageOperationException te) {
            throw new PageOperationException("Unable to select undo icon on: " + te.getMessage());
        }
    }

    /**
     * Closes down the delete successful message.
     */
    public void closeDeleteMessage() {
        try {
            waitForExpectedElement(By.className("co_notes_closeLink")).click();
        } catch (PageOperationException te) {
            throw new PageOperationException("Unable to select close icon on: " + te.getMessage());
        }
    }

    /**
     * Verifies the Contacts Link is displayed under the annotations textbox.
     * Returns true if link is displayed otherwise false
     *
     * @return boolean
     */

    public boolean isContactsLinkDisplayed() {
        return isElementDisplayed(By.cssSelector("#sharedNotesHyperlink #contactsId"));
    }

    /**
     * Verifies the Previous Contacts Link is displayed under the annotations textbox.
     * Returns true if link is displayed otherwise false
     *
     * @return boolean
     */

    public boolean isPreviousContactsLinkDisplayed() {
        return isElementDisplayed(By.cssSelector("#sharedNotesHyperlink #previousId"));
    }

    /**
     * Verifies the Contacts And Groups popup page is displayed when clicking on contacts link.
     * Returns true if popup page is displayed otherwise false
     *
     * @return boolean
     */

    public boolean isContactsGroupsPageDisplayed() {
        return isElementDisplayed(By.cssSelector("#sharedNotesHyperlink #previousId"));
    }

    /**
     * Search for the given contact name in the contacts and groups popup page.
     *
     * @param contact
     */
    public void searchContact(String contact) {
        try {
            WebElement element = waitForExpectedElement(By.id("coid_contacts_people_searchBoxInput_contacts"));
            element.clear();
            element.sendKeys(contact);
        } catch (TimeoutException te) {
            throw new PageOperationException("Exceeded time to find the search box. " + te.getMessage());
        }
    }

    /**
     * Returns the entered text in annotations tinymce text editor.
     *
     * @return String
     */
    public String getText() {
        return tinyMceEditor.getText();
    }

    /**
     * Verifies that given contact has found or not in  Contacts And Groups popup page.
     * Returns true if given contact found otherwise false
     *
     * @param contact
     * @return boolean
     */
    public boolean isContactFoundInSearch(String contact) {
        try {
            //TODO [Phase1] verify if waitForExpectedElements works similar to waitForExpectedElements
            for (WebElement result : waitForExpectedElements(By.cssSelector("#coid_contacts_peopleListItems_contacts a[role='checkbox']"))) {
                if (contact.equals(result.getText())) {
                    return true;
                }
            }
        } catch (PageOperationException te) {
        } catch (StaleElementReferenceException sle) {
            isContactFoundInSearch(contact);
        }
        return false;
    }
    /**
     * Verifies that given group name has found or not in Groups popup page.
     * Returns true if given group found otherwise false
     *
     * @param group
     * @return boolean
     */
    public boolean isGroupFoundInSearch(String group) {
        try {
            //TODO [Phase1] verify if waitForExpectedElements works similar to waitForExpectedElements
            for (WebElement result : waitForExpectedElements(By.cssSelector("#coid_contacts_groupsListItems a[role='checkbox']"))) {
                if (group.equals(result.getText())) {
                    return true;
                }
            }
        } catch (PageOperationException te) {
        } catch (StaleElementReferenceException ste) {
            isGroupFoundInSearch(group);
        }
        return false;
    }

    /**
     * Verifies that client id value is not displayed on saved annotation
     * Returns true if client id found otherwise false
     *
     * @param input
     * @return boolean
     */
    public boolean verifyClientIDNotDisplayed(String input) {
        try {
            return waitForExpectedElement(By.xpath(".//div[@class='co_viewNoteText mce-content-body']/p[text()='" + input + "']/.."), TIMEOUT_5_SECONDS).findElement(By.className("co_noteHeader_clientID")).isDisplayed();
        } catch (TimeoutException | NoSuchElementException te) {
            return true;
        }
    }

    /**
     * Selects given format type in tinymce editro.
     *
     * @param formatType
     */
    public void selectStyle(FormatType formatType) {
        tinyMceEditor.selectStyle(formatType);
    }

    /**
     * Selects the given format group name from Formats Menu.
     *
     * @param styleGroupName
     */
    public void selectStyleGroupFromFormatsMenu(String styleGroupName) {
        tinyMceEditor.openFormatsMenu();
        tinyMceEditor.selectStylesMenuItem(styleGroupName);
    }

    /**
     * Verifies that Saved annotation is displayed with applied format style.
     * Returns true if expected formatted text found otherwise false
     *
     * @param formatType
     * @param text
     * @return boolean
     */
    public boolean isAnnoatationsTextDisplayedWithCharacterStyle(FormatType formatType, String... text) {
        return tinyMceEditor.isAnnoatationsTextDisplayedWithCharacterStyle(formatType, text);
    }

    /**
     * Select the text entered in tinymce text editor.
     */
    public void selectText() {
        tinyMceEditor.selectTextFromEditor();
    }

    public void selectTextFromDocumentWithColor(final String color) {
//        Actions select = new Actions(getDriver);
//        select.doubleClick(getTextFromDocument()).build().perform();
       final WebElement el = getTextFromDocument();       
        AbstractPage.waitFor(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driverObject) {
				commonMethods.selectParagraphFromDocumentWithJS(el);
				el.click();
	            waitForPageSourceChangedAfterClick(colourForNote(color));
	            return isTextBoxDisplayed();
			}
		}, commonMethods.getDriver());
//        getDriver.getMouse().doubleClick(((Locatable) getTextFromDocument()).getCoordinates());
    }
    
    public void selectTextFromDocument() {
//      Actions select = new Actions(getDriver);
//      select.doubleClick(getTextFromDocument()).build().perform();
      WebElement el = getTextFromDocument();
      el.click();
      el.sendKeys(Keys.SHIFT,Keys.UP);
      el.click();
//      getDriver.getMouse().doubleClick(((Locatable) getTextFromDocument()).getCoordinates());
  }

    public void selectTextFromDocument(String text) {
        Actions select = new Actions(getDriver);
        select.doubleClick(getTextFromDocument(text)).build().perform();
    }

    public WebElement getTextFromDocument() {
        return waitForExpectedElement(By.cssSelector("div.co_paragraphText"));
    }

    public WebElement getTextFromDocument(String text) {
        return waitForExpectedElement(By.xpath("//div[contains(text(),'" + text + "')]"));
    }

    public boolean isColourForNotePresent() {
        return isElementDisplayed(By.cssSelector("div#co_menuHolder"));
    }

    public WebElement getColourForNote(String colour) {
        return waitForExpectedElement(By.xpath("//li[@id='co_selectedTextMenuListItem_AddNote']//li[@class='highlightBox " + colour + "']"));
    }
    
    public WebElement colourForNote(String colour) {
        return waitForExpectedElement(By.xpath("//li[@id='co_selectedTextMenuListItem_AddNote']//li[@class='highlightBox " + colour + "']"));
    }

    public void chooseColorForNote(String colour) {
    	getColourForNote(colour).click();
    }

    public WebElement getColourForHighlightText(String colour) {
        return waitForExpectedElement(By.xpath("//li[@id='co_selectedTextMenuListItem_Highlight']//li[@class='highlightBox " + colour + "']"));
    }

    public void chooseColorForHighlightText(String colour) {
        getColourForHighlightText(colour).click();
    }

    public void clickOnCopyWithoutReference() {
        getCopyWithoutReference().click();
    }

    public WebElement getCopyWithoutReference() {
        return waitForExpectedElement(By.cssSelector("li#co_selectedTextMenuListItem_CopyWithoutRef"));
    }

    public void clickOnCopyWithReference() {
        getCopyWithReference().click();
    }

    public WebElement getCopyWithReference() {
        return waitForExpectedElement(By.cssSelector("li#co_selectedTextMenuListItem_CopyWithRef"));
    }

    /**
     * Verifies that Formats menu style is selected in tinymce editor.
     * Returns true if format type is selected otherwise false
     *
     * @param formatType
     * @return boolean
     */
    public boolean isLinkOptionSelected(FormatType formatType) {
        try {
            return waitForExpectedElement(formatType.getStyleLocator()).findElement(By.xpath(".//../..")).getAttribute("class").contains("mce-active");
        } catch (TimeoutException te) {
        }
        return false;
    }

    /**
     * Removes the entered text and styles from tinymce editor.
     */
    public void clearAll() {
        tinyMceEditor.clearAll();
    }

    /**
     * Verifies that Formats menu is selected in tinymce editor.
     * Returns true if format menu is selected otherwise false
     *
     * @param style
     * @return boolean
     */
    public boolean isMenuOptionSelected(String style) {
        try {
            for (WebElement element : waitForExpectedElements(By.cssSelector(".mce-menu-item.mce-active"))) {
                if (element.getText().contains(style)) {
                    return true;
                }
            }
        } catch (TimeoutException te) {
            LOG.info("Exceeded time to wait Formats menu in tinymce editor", te);
        }
        return false;
    }

    /**
     * Common method to find out the given formattype link is enabled or not on tinymce editor.
     * Returns true if given link is enabled otherwise false
     *
     * @param formatType
     * @return boolean
     */
    public boolean isLinkOptionEnabled(FormatType formatType) {
        try {
            for (WebElement element : waitForExpectedElements(formatType.getStyleLocator())) {
                if (element.isDisplayed()) {
                    if (element.findElement(By.xpath(".//../..")).getAttribute("aria-disabled").equals("true")) {
                        return false;
                    }
                    return true;
                }
            }
        } catch (TimeoutException | NoSuchElementException te) {
            LOG.info(te.getMessage());
        }
        return false;
    }

    public String getParticipantNameFromUserList(int participantNumber){
        return getParticipantFromUserList(participantNumber).getText();
    }

    public WebElement getParticipantFromUserList(int participantNumber){
        participantNumber = participantNumber - 1;
        return waitForExpectedElement(By.xpath("//ul[@id='coid_contacts_peopleListItems_contacts_edit']/li[@id='drag" + participantNumber + "']/div/a[2]"));
    }

    /**
     * Clicks on annotations link text present in tinymce editor.
     *
     * @param input
     */
    public void clickOnAnnotationLinkText(String input) {
        waitForElementPresent(By.xpath("//div[@class='co_viewNoteText mce-content-body']/p/a[text()='" + input + "']")).click();
    }

    /**
     * Clicks on given input url link present on the tinymce text editor.
     *
     * @param input
     */
    public void clickOnURLLink(String input) {
        WebElement textElement = null;
        try {
            textElement = waitForElementPresent(By.xpath("//div[@class='co_viewNoteText mce-content-body']/p[contains(text(),'" + input + "')]"));
            textElement.findElement(By.xpath(".//..//a")).click();
        } catch (Exception nse) {
            if (urlFindingCount < 3) {
                urlFindingCount++;
                clickOnURLLink(input);
            } else {
                throw new PageOperationException("Facing difficulty in selecting the textlink.");
            }
        }
    }

    static int textMenuCounter = 0;

    /**
     * Selects the given text and write inline notes.
     *
     * @param text
     */
    public void selectTextPresentInParaUsingDoubleClick(String text) {
        WebElement element = waitForExpectedElement(By.xpath(".//*[text()='" + text + "']"));
        highlightElement(element);
        try {
            waitForExpectedElement(By.xpath("//div[@id='co_selectedTextMenu'][contains(@style,'visible')]"), TIMEOUT_5_SECONDS);
            textMenuCounter = 0;
        } catch (TimeoutException te) {
            if (textMenuCounter <= 3) {
                textMenuCounter++;
                selectTextPresentInParaUsingDoubleClick(text);
            } else {
                textMenuCounter = 0;
                throw new PageOperationException("Facing page difficulty in having the inline text menu.");
            }
        }
    }

    public WebElement getCloseButtonFromContacts(){
        return waitForExpectedElement(By.cssSelector("#coid_contacts_cancelLink"));
    }

    public void closeContactsForm(){
        getCloseButtonFromContacts().click();
    }

    /**
     * Hightlights the given webelement.
     *
     * @param element
     */
    public void highlightElement(WebElement element) {
        for (int i = 0; i < 2; i++) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "background: yellow; border: 2px solid yellow;");
        }
    }

    /**
     * Selects color note from inline note popup
     */
    public void selectInlineNotesYellowColor() {
        waitForExpectedElement(By.cssSelector(".highlightBox.yellowBox.activeBox.highlightBoxHover")).click();
    }

    /**
     * Selects given Contact in contacts popup.
     *
     * @param contact
     */
    public void selectContact(String contact) {
        try {
            //TODO [Phase1] verify if waitForExpectedElements works similar to waitForExpectedElements
            for (WebElement result : waitForExpectedElements(By.cssSelector("#coid_contacts_peopleListItems_contacts a[role='checkbox']"))) {
                if (contact.equals(result.getText())) {
                    result.click();
                    break;
                }
            }
        } catch (PageOperationException te) {
            throw new PageOperationException("Unable to find the contact in contacts list: " + contact);
        } catch (StaleElementReferenceException se) {
            selectContact(contact);
        }
    }

    public void addGroupAvailableToOthers(String groupName, String... contacts) {
        try {
            getAddGroupOption().click();
            getGroupNameField().sendKeys(groupName);
            for (String contact : contacts) {
                getSearchByNameField().clear();
                getSearchByNameField().sendKeys(contact);
                getSearchResult(contact).click();
            }
            getCheckBoxGroupAvailableToOthers().click();
            getSaveGroupButton().click();
        } catch (PageOperationException poe) {
            throw new PageOperationException("Having issues in creating group" + poe.getMessage());
        }
    }

    public void createNewRandomGroup(String group, List<String> contacts) {
        try {
            getAddGroupOption().click();
            getGroupNameField().sendKeys(group);
            for (String contact : contacts) {
                getSearchByNameField().clear();
                getSearchByNameField().sendKeys(contact);
                getSearchResult(contact).click();
            }
            getSaveGroupButton().click();
        } catch (PageOperationException poe) {
            throw new PageOperationException("Having issues in creating group" + poe.getMessage());
        }
    }

    public String getTextFromPopUpWithMembers(){
        return getPopUpWithMembers().getText();
    }

    public WebElement getPopUpWithMembers(){
        return waitForExpectedElement(By.xpath("//div[@class='co_infoBox top co_contacts_moreInfo']//ul"));
    }

    public void clickOnGroupInfo(){
        getGroupInfo().click();
    }

    public WebElement getGroupInfo(){
        return waitForExpectedElement(By.xpath("//ul[@id='coid_contacts_groupsListItems']//a[@class='co_contacts_info']"));
    }

    public WebElement getAddGroupOption() {
        //TODO [Phase1] verify if waitForExpectedElement works similar to waitForExpectedElement
        return waitForExpectedElement(By.id("groupListBoxWidgetAddGroup"));
    }

    public WebElement getGroupNameField() {
        //TODO [Phase1] verify if waitForExpectedElement works similar to waitForExpectedElement
        return waitForExpectedElement(By.id("coid_contacts_newGroupName"));
    }

    public WebElement getSearchByNameField() {
        //TODO [Phase1] verify if waitForExpectedElement works similar to waitForExpectedElement
        return waitForExpectedElement(By.id("coid_contacts_people_searchBoxInput_contacts_edit"));
    }

    public WebElement getSearchResult(String contact) {
        //TODO [Phase1] verify if waitForExpectedElement works similar to waitForExpectedElement
        return waitForExpectedElement(By.xpath("//ul[@id='coid_contacts_peopleListItems_contacts_edit']//a[@role='checkbox'][text()='" + contact + "']"));
    }

    public WebElement getCheckBoxGroupAvailableToOthers() {
        //TODO [Phase1] verify if waitForExpectedElement works similar to waitForExpectedElement
        return waitForExpectedElement(By.id("coid_contacts_newGroupPublic"));
    }

    public WebElement getSaveGroupButton() {
        //TODO [Phase1] verify if waitForExpectedElement works similar to waitForExpectedElement
        return waitForExpectedElement(By.id("coid_contacts_newGroup_saveButton"));
    }

    /**
     * Scrolls up the document to tinymce editor.
     */
    public void scrollToTinyMceEditor() {
        tinyMceEditor.scrollToTinyMceEditor();
    }

    /**
     * Custom enum for the text types
     * TEXT and RICH_TEXT
     */
    public enum MessageType {
        TEXT,
        RICH_TEXT;
    }

    /**
     * Gets the warning message displayed on document because of the given message type text is exceeded the lenghth.
     *
     * @param messageType
     * @return String
     */
    public String getWarningMessage(MessageType messageType) {
        StringBuffer sb = new StringBuffer();
        String cssLocator = "";

        if (messageType.equals(MessageType.TEXT)) {
            cssLocator = "#co_NotesMessage";
        } else if (messageType.equals(MessageType.RICH_TEXT)) {
            cssLocator = "#co_NotesMessage>div";
        }

        try {
            //TODO [Phase1] verify if waitForExpectedElements works similar to waitForExpectedElements
            for (WebElement element : waitForExpectedElements(By.cssSelector(cssLocator))) {
                sb.append(element.getText());
            }
        } catch (PageOperationException pe) {
            LOG.info("Can't get the warning message displayed on the document", pe);
        }
        return sb.toString();
    }

    /**
     * clicks on OK button present on exceeded length warning message.
     */
    public void selectOKButtonOnWarningMessage() {
        try {
            //TODO [Phase1] verify if waitForExpectedElement works similar to waitForExpectedElement
            waitForExpectedElement(By.className("co_NotesLightBox_okbutton")).click();
        } catch (PageOperationException pe) {
            throw new PageOperationException("Having issues in selection ok button on warning message" + pe.getMessage());
        }
    }

    /**
     * Inserts the given xml/html contact as in the given format.
     *
     * @param content
     */
    public void insertContent(String content) {
        tinyMceEditor.setTinyMCeContent(content);
    }

    public List<WebElement> getSharedGroupLinks() {
        return waitForExpectedElements(By.xpath("//li[@class='co_contacts_addedContactsGroup']/a"));
    }

    public boolean isDocumentHasAnnotationWithText(String annotationText) {
        return isExists(By.xpath("//div[contains(@id, 'otes') and not(contains(@id, 'hidden')) and contains(., '" + annotationText +"')]"));
    }

    /**
     * Annotations metadata
     */
    class AnnotationMetaData {
        private WebElement element;

        public AnnotationMetaData(WebElement element) {
            this.element = element;
        }

        public String getCreatedBy() {
            return element.findElement(By.className("co_noteHeader_createdBy")).getText();
        }

        public String getFontWeightValueForCreatedBy() {
            return element.findElement(By.className("co_noteHeader_createdBy")).getCssValue("font-weight");
        }

        public String getDate() {
            return element.findElement(By.className("co_noteBody_date")).getText();
        }

        public String getTime() {
            return element.findElement(By.className("co_noteBody_time")).getText();
        }
    }
    
    public boolean cookiesPolicy(){
        return isExists(By.id("co_cookiePolicyContainer"));
  
}//cookies policy
    public boolean isCookiesPolicyPresent(){
	return isExists(By.xpath(".//*[@id='co_cookiePolicyContainer']"));
     
    }

       //close cookies policy
    public WebElement closeCookiesPolicy(){
      //  return waitForExpectedElement(By.id("coid_website_cookiePolicyAcknowledged"));
        return waitForExpectedElement(By.xpath(".//*[@id='coid_website_closeCookiePolicy']"));
          
    }

}