package com.thomsonreuters.pageobjects.pages.annotations;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;


public class TinyMceEditor extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(TinyMceEditor.class);

    private CommonMethods comMethods;

    public TinyMceEditor(){
        comMethods = new CommonMethods();
    }

    public static final String TINYMCE_CONTENT = ".co_dropdownBoxExpanded .co_richEditor.co_noteArea.mce-content-body";

    /**
     * This method is to adds the given input value to existing text presents in the annotations textbox.
     *
     * @param txt
     */
    public void addContent(String txt) {
        try {
            waitForExpectedElement(By.cssSelector(TINYMCE_CONTENT)).sendKeys(txt);
        } catch (TimeoutException noSuchElementExp) {
            LOGGER.error("Element : {} is not present", txt);
        }
    }

    public WebElement getTinyMceTextBox(){
       return waitForExpectedElement(By.cssSelector(TINYMCE_CONTENT));
    }



    /**
     * Scrolls up the document to tinymce editor.
     */
    public void scrollToTinyMceEditor() {
        comMethods.moveToElementUsingLocation(waitForExpectedElement(By.cssSelector(TINYMCE_CONTENT)));
    }

    /**
     * Select the text entered in tinymce text editor.
     */
    public void selectTextFromEditor() {
        // This select all in the edit pane
        waitForExpectedElement(By.cssSelector(TINYMCE_CONTENT)).sendKeys(Keys.chord(Keys.CONTROL, "a"));
    }

    /**
     * Gets the entered text into tinymce editor.
     *
     * @return String
     */
    public String getText() {
        try {
            return waitForExpectedElement(By.cssSelector(TINYMCE_CONTENT)).getText();
        } catch (TimeoutException noSuchElementExp) {
            throw new PageOperationException("Unable to find text in tinyMCE editor." + noSuchElementExp);
        }
    }

    /**
     * This method does the removing of text/links and format from the tinymce editor.
     */
    public void clearAll() {
        try {
            waitForExpectedElement(By.cssSelector(TINYMCE_CONTENT)).clear();
            waitForExpectedElement(By.cssSelector(TINYMCE_CONTENT)).sendKeys(Keys.BACK_SPACE);
        } catch (TimeoutException noSuchElementExp) {
            throw new PageOperationException("Unable to find text css in tinyMCE editor." + noSuchElementExp);
        }
    }

    /**
     * Inserts the given xml/html contact as in the given format.
     *
     * @param content
     */
    public void setTinyMCeContent(String content) {
        try {
            clearAll();
            String setCommentJs = String.format("tinyMCE.activeEditor.setContent('%s');", content);
            executeScript(setCommentJs);
        } catch (TimeoutException noSuchElementExp) {
            throw new PageOperationException("Unable to find text css in tinyMCE editor." + noSuchElementExp);
        }
    }

    /**
     * Selects given format type in tinymce editro.
     *
     * @param formatType
     */
    public void selectStyle(FormatType formatType) {
        try {
            for (WebElement element : waitForExpectedElements(formatType.getStyleLocator())) {
                if (element.isDisplayed()) {
                    element.click();
                    break;
                }
            }
        } catch (TimeoutException te) {
            throw new PageOperationException("Unable to select the format." + formatType);
        }
    }

    /**
     * Selelcts the Formats Menu present on tinymce editor.
     */
    public void openFormatsMenu() {
        try {
            waitForExpectedElement(By.xpath("//span[text()='Formats']")).click();
        } catch (PageOperationException te) {
            throw new PageOperationException("Unable to select the formatsMenu.");
        }

    }

    /**
     * Selects the given format group name from Formats Menu.
     *
     * @param styleGroupName
     */
    public void selectStylesMenuItem(String styleGroupName) {
        try {
            for (WebElement element : waitForExpectedElements(By.xpath("//span[text()='" + styleGroupName + "']"))) {

                if (!(element.findElement(By.xpath(".//../../..")).getAttribute("style").contains("none"))) {
                    element.click();
                    break;
                }
            }
        } catch (PageOperationException te) {
            throw new PageOperationException("Unable to select the formats.");
        }
    }

    /**
     * Verifies that Saved annotation is displayed with applied format style.
     * Returns true if expected formatted text found otherwise false
     *
     * @param formatType
     * @param multipleText
     * @return boolean
     */
    public boolean isAnnoatationsTextDisplayedWithCharacterStyle(FormatType formatType, String... multipleText) {
        boolean bool = true;
        try {
            List<WebElement> list = waitForExpectedElements(formatType.getCssOfText(), 10);
            for (String text : multipleText) {
                int count = 0;
                for (WebElement element : list) {
                    if (element.getText().equals(text)) {
                        count = count + 1;
                    }
                }
                if (count == 0) {
                    return false;
                }
            }
        } catch (Exception e) {
            bool = false;
        }
        return bool;
    }
}