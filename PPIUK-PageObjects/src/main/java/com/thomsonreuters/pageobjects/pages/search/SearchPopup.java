package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Pavel_Ardenka on 25/11/2016.
 */
public class SearchPopup extends AbstractPage {

    private static final By POPUP_CSS = By.cssSelector("#scopedSearchOptions > .co_dropDownMenuContent");
    private static final By OPTIONS_BUTTON_ID = By.id("scopedSearchOptionsMenu");
    private static final By CONTENT_CHECKBOXES_NAMES = By.cssSelector("#optionsContentType label");
    private static final By TOPIC_CHECKBOXES_NAMES = By.cssSelector("#optionsTopicType label");
    private static final By ALL_CONTENT_CHECKBOXES_CSS = By.cssSelector("#optionsContentType input[type='checkbox']");
    private static final By ALL_TOPIC_CHECKBOXES_CSS = By.cssSelector("#optionsTopicType input[type='checkbox']");
    private static final String CHECKBOX_XPATH = "//div[@id='options%1$sType']//li[contains(., '%2$s')]/input[@type='checkbox']";
    private static final By POP_UP_CLEAR = By.xpath("//*[@id='optionsTopicClearAllLink']");

    public boolean isOptionsPopupDisplayed() {
        return isElementDisplayed(POPUP_CSS);
    }

    public WebElement getOptionsButton() {
        return waitForExpectedElement(OPTIONS_BUTTON_ID);
    }

    public List<WebElement> getContentCheckboxesNames() {
        return waitForExpectedElements(CONTENT_CHECKBOXES_NAMES);
    }

    public List<WebElement> getTopicCheckboxesNames() {
        return waitForExpectedElements(TOPIC_CHECKBOXES_NAMES);
    }

    public WebElement getCheckbox(String section, String checkboxName) {
        return waitForExpectedElement(By.xpath(
                String.format(CHECKBOX_XPATH, StringUtils.capitalize(section), checkboxName)));
    }

    public List<WebElement> getAllContentCheckboxes() {
        return waitForExpectedElements(ALL_CONTENT_CHECKBOXES_CSS);
    }

    public List<WebElement> getAllTopicCheckboxes() {
        return waitForExpectedElements(ALL_TOPIC_CHECKBOXES_CSS);
    }

    public WebElement clearAllButton() {
        return waitForExpectedElement(POP_UP_CLEAR);
    }

    public Boolean isClearAllButtonDisplayed() {
        return isElementDisplayed(POP_UP_CLEAR);
    }

}
