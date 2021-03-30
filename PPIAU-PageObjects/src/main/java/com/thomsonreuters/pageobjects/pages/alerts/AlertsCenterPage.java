package com.thomsonreuters.pageobjects.pages.alerts;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.util.StringUtils;

import java.util.List;


public class AlertsCenterPage extends AbstractPage {
    private static final By CREATE_ALERT_DROPDOWN_LINK = By.xpath("//a[@title='Create Alert']");
    private static final By NEWSLETTERS_TAB = By.xpath("//ul[@id='co_alertNav_menu']//a[text()='Newsletters']");
    private static final By ALERTS_TAB = By.xpath("//ul[@id='co_alertNav_menu']//a[text()='Alerts']");
    private static final By ALERTS_HISTORY_LINK = By.id("co_alertHistory");
    private static final By ALERTS_TITLE = By.xpath("//div[@id='co_alertCenter']//*[text()='Alerts']");
    private static final By NEWSLETTERS_TITLE = By.xpath("//div[@id='co_alertCenter']//*[text()='Newsletters']");
    private static final By ALERTS_LIST = By.id("alertListBody");
    private static final By ALERT_FROM_ALERT_LIST = By.xpath("//ul[@id='alertListBody']/li");
    private static final By SORT_ORDER_SELECT = By.id("co_alert_sortOptions");
    private static final By ALERT_NAMES_FROM_LIST = By.xpath("//li[@class='co_user_alert_item']//span[contains(@class,'co_lessDetail co_showState')]");
    private static final By SEARCH_ALERTS_OPTION = By.id("co_searchWithinWidget");
    private static final By SEARCH_ALERTS_INPUT = By.id("co_searchWithinWidget_textArea");
    private static final By SEARCH_ALERTS_SEARCH_BUTTON = By.id("co_searchWithinWidget_searchButton");
    private static final By SEARCH_ALERTS_CANCEL_BUTTON = By.id("co_searchWithinWidget_cancelButton");
    private static final By SEARCH_ALERTS_NO_RESULTS_MSG = By.id("co_alerts_center_nocontent");
    private static final By RESULTS_PER_PAGE_MENU = By.id("displayItemCount");
    private static final By RESULTS_PER_PAGE_DROPDOWN_LIST = By.xpath("//*[@id='displayItemCount']//*[contains(@class,'co_dropDownMenuList')]");
    private static final By VISIBLE_RESULTS_PER_PAGE = By.xpath("//*[@id='co_alertCenter']//*[contains(@class,'co_resultRange')]");
    private static final String ALERT_EDIT_LINK_BY_NUMBER = "//*[@id='alertListBody']/li[%d]//a[contains(@class,'co_editAlert')]";
    private static final By ALERT_EDIT_LINKS = By.xpath("//*[@id='alertListBody']//a[contains(@class,'co_editAlert')]");
    private static final By ALERT_ITEM_WITHOUT_EDIT_LINK = By.xpath("//li[@class='co_user_alert_item' and not(descendant::a[contains(@class,'co_editAlert')])]");
    private static final By ALERT_DELETE_POPUP_MESSAGE = By.id("DeleteItemContainer");
    private static final By ALERT_DELETE_POPUP_PAGE = By.id("co_delete_item_lightbox");
    private static final String ALERT_DELETE_POPUP_BUTTON_BY_NAME = "co_delete_item_%s";
    private static final String ALERT_NAME_BY_NUMBER = "//li[@class='co_user_alert_item'][%d]//span[contains(@class,'co_lessDetail co_showState')]";
    private static final By ALERT_LIST_CHECKBOXES = By.xpath("//li[contains(@class,'co_user_alert_item')]//input");
    private static final String FOOTER_PAGE_NAVIGATION_ARROW_PATTERN = "//ul[contains(@class,'co_navFooter_pagination')]//a[contains(@class,'th_flat_icon co_%s')]";
    private static final String CURRENT_SELECTED_PAGE_NUMBER_PATTERN = "//div[contains(@class,'co_navFooter')]//strong[text()='%s']";
    private static final String NOT_SELECTED_PAGE_NUMBER_PATTERN = "//div[contains(@class,'co_navFooter')]//a[text()='%s']";

    private CommonMethods commonMethods = new CommonMethods();

    public boolean isAlertsCenterHeadingTitleDisplayed() {
        return isElementDisplayed(ALERTS_TITLE);
    }

    public WebElement createAlertDropdownLink() {
        return waitForExpectedElement(CREATE_ALERT_DROPDOWN_LINK);
    }

    public WebElement createSpecificAlertLink(String alertType) {
        return waitForExpectedElement(By.xpath("//a[@class='co_navDropDownHeader']/strong[text()='" + alertType + "']"));
    }

    public WebElement newslettersTab() {
        return waitForExpectedElement(NEWSLETTERS_TAB);
    }

    public boolean isNewslettersTabDisplayed() {
        return isElementDisplayed(NEWSLETTERS_TAB);
    }

    public boolean isAlertsHistoryLinkDisplayed() {
        return isElementDisplayed(ALERTS_HISTORY_LINK);
    }

    public WebElement alertsTab() {
        return waitForExpectedElement(ALERTS_TAB);
    }

    public boolean isNewslettersTitleDisplayed() {
        return isElementDisplayed(NEWSLETTERS_TITLE);
    }

    public boolean isAlertsListDisplayed() {
        return isElementDisplayed(ALERTS_LIST);
    }

    public WebElement sortOrderSelect() {
        return waitForExpectedElement(SORT_ORDER_SELECT);
    }

    public boolean isSearchAlertsOptionDisplayed() {
        return isElementDisplayed(SEARCH_ALERTS_OPTION);
    }

    public WebElement searchAlertsInput() {
        return waitForExpectedElement(SEARCH_ALERTS_INPUT);
    }

    public String searchAlertsDisplayedTerm() {
        return waitForExpectedElement(SEARCH_ALERTS_INPUT).getAttribute("value");
    }

    public WebElement searchAlertsSearchButton() {
        return waitForExpectedElement(SEARCH_ALERTS_SEARCH_BUTTON);
    }

    public boolean isSearchAlertsSearchButtonDisplayed() {
        return isElementDisplayed(SEARCH_ALERTS_SEARCH_BUTTON);
    }

    public boolean isSearchAlertsCancelButtonDisplayed() {
        return isElementDisplayed(SEARCH_ALERTS_CANCEL_BUTTON);
    }

    public String searchAlertsNoResultsMessage() {
        return waitForExpectedElement(SEARCH_ALERTS_NO_RESULTS_MSG).getText();
    }

    public WebElement editAlertLink(int alertNumber) {
        return waitForExpectedElement(By.xpath(String.format(ALERT_EDIT_LINK_BY_NUMBER, alertNumber)));
    }

    public boolean isAlertWithoutEditLinkExists() {
        return isExists(ALERT_ITEM_WITHOUT_EDIT_LINK);
    }

    public List<WebElement> alertsEditLinks() {
        return waitForExpectedElements(ALERT_EDIT_LINKS);
    }

    public List<WebElement> alertListCheckboxes() {
        return waitForExpectedElements(ALERT_LIST_CHECKBOXES);
    }

    public String alertDeletePopupMessage() {
        return waitForExpectedElement(ALERT_DELETE_POPUP_MESSAGE).getText().trim().replace("\n", "");
    }

    public boolean isAlertDeletePopupDisplayed() {
        return isElementDisplayed(ALERT_DELETE_POPUP_PAGE);
    }

    public WebElement alertDeletePopupButton(String buttonName) {
        return waitForExpectedElement(By.id(String.format(ALERT_DELETE_POPUP_BUTTON_BY_NAME, StringUtils.uncapitalize(buttonName))));
    }

    public WebElement alertName(int alertIndex) {
        return waitForExpectedElement(By.xpath(String.format(ALERT_NAME_BY_NUMBER, alertIndex)));
    }

    public WebElement alertFooterPageNavigationIcon(String alertHeaderNavigationIconType) {
        return waitForExpectedElement(By.xpath(String.format(FOOTER_PAGE_NAVIGATION_ARROW_PATTERN, alertHeaderNavigationIconType)));
    }

    public boolean isAlertFooterPageNavigationIconDisplayed(String alertHeaderNavigationIconType) {
        return isElementDisplayed(By.xpath(String.format(FOOTER_PAGE_NAVIGATION_ARROW_PATTERN, alertHeaderNavigationIconType)));
    }

    public WebElement currentSelectedPage(String pageNumber) {
        return waitForExpectedElement(By.xpath(String.format(CURRENT_SELECTED_PAGE_NUMBER_PATTERN, pageNumber)));
    }

    public boolean isCurrentSelectedPagePresent(String pageNumber) {
        return isElementDisplayed(By.xpath(String.format(CURRENT_SELECTED_PAGE_NUMBER_PATTERN, pageNumber)));
    }

    public WebElement nonSelectedPage(String pageNumber) {
        return waitForExpectedElement(By.xpath(String.format(NOT_SELECTED_PAGE_NUMBER_PATTERN, pageNumber)));
    }

    public boolean isNonSelectedPagePresent(String pageNumber) {
        return isElementDisplayed(By.xpath(String.format(NOT_SELECTED_PAGE_NUMBER_PATTERN, pageNumber)));
    }

    public void enterSearchTermsIntoSearchAlertsField(String searchTerms) {
        WebElement searchAlertsInput = waitForExpectedElement(SEARCH_ALERTS_INPUT);
        searchAlertsInput.click();
        searchAlertsInput.sendKeys(searchTerms);
    }

    public List<String> alertsListText() {
        return commonMethods.getTextsFromWebElements(waitForExpectedElements(ALERT_FROM_ALERT_LIST));
    }

    public List<String> alertNames() {
        return commonMethods.getTextsFromWebElements(waitForExpectedElements(ALERT_NAMES_FROM_LIST));
    }

    public boolean isParticularAlertFieldByKeywordDisplayed(int alertNumber, String fieldKeyword) {
        String alertByNumberXPath = "//ul[@id='alertListBody']/li[" + alertNumber + "]";

        By labeledField = By.xpath(alertByNumberXPath + "//li[contains(text(),'" + fieldKeyword + "')]");
        By nonLabeledField = By.xpath(alertByNumberXPath + "//span[contains(@class,'co_lessDetail co_showState')] | "
                + alertByNumberXPath + "//a[contains(@id,'coid_alert_emailRecipientLink')]");

        return isElementDisplayed(labeledField) || isElementDisplayed(nonLabeledField);
    }

    public void selectValueInResultsPerPageDropdown(String resultPerPage) {
        waitForExpectedElement(RESULTS_PER_PAGE_MENU).click();
        waitForElementsVisible(RESULTS_PER_PAGE_DROPDOWN_LIST);
        waitForExpectedElement(By.linkText(resultPerPage + " per page")).click();
    }

    public String visibleResultsPerPageText() {
        return waitForExpectedElement(VISIBLE_RESULTS_PER_PAGE).getText();
    }

}
