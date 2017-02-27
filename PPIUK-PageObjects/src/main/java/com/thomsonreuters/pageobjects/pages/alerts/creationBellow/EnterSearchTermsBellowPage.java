package com.thomsonreuters.pageobjects.pages.alerts.creationBellow;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * This class describes elements and actions on the Enter Search Terms bellow on the 'Create Alert' page.
 * Created by Olga_Nadenenko on 2/6/2017.
 */
public class EnterSearchTermsBellowPage extends AbstractPage {

    private CommonMethods commonMethods = new CommonMethods();

    private static final By SEARCH_INPUT = By.id("searchInputIdAlerts");
    private static final By SEARCH_INPUT_LABEL = By.xpath("//*[@id='co_search_alertSearchPanel']//h3[contains(@class,'co_formLabel')]");
    private static final By TERMS_AND_CONNECTORS_INFO_ICON = By.id("co_search_alertSearchPanelTncHelpLink");
    private static final By TERMS_AND_CONNECTORS_POPUP_HEADER = By.id("co_search_tncHelp_tncLegendTitle");
    private static final By TERMS_AND_CONNECTORS_POPUP_CLOSE_ICON = By.id("co_search_tncHelp_popupclose");
    private static final By TERMS_AND_CONNECTORS_POPUP_CLOSE_BUTTON = By.id("co_search_tncHelpClose");
    private static final By SORT_ORDER_LABEL = By.xpath("//label[@for='co_search_alertSearchPanelSortTypes']");
    private static final By SORT_ORDER_DROPDOWN = By.id("co_search_alertSearchPanelSortTypes");
    private static final By DOCUMENTS_NO_OLDER_THAN_LABEL = By.xpath("//label[@for='co_search_alertSearchPanelDateFilterTypes']");
    private static final By DOCUMENTS_NO_OLDER_THAN_DROPDOWN = By.id("co_search_alertSearchPanelDateFilterTypes");
    private static final By SEARCH_TERMS_VALIDATION_BUTTON = By.xpath("//button[@id='co_button_continue_Search' and .='Validating']");
    private static final By PREVIEW_RESULTS_BUTTON = By.id("co_button_previewResults_Search");
    private static final By PREVIEW_RESULTS_POPUP_HEADER = By.id("coid_lightboxAriaLabel_5");
    private static final By PREVIEW_RESULTS_POPUP_CLOSE_ICON = By.id("co_search_alertPreview_popupclose");
    private static final By PREVIEW_RESULTS_POPUP_CLOSE_BUTTON = By.id("co_search_alertPreviewClose");
    private static final By PREVIEW_RESULTS_POPUP_CONTENT_TYPE_HEADERS = By.id("cobalt_search_ukCombinedResearch_results_header");
    private static final By PREVIEW_RESULTS_POPUP_DOCUMENTS = By.xpath("//*[contains(@id,'document_icons_')]");
    private static final By SEARCH_INFO_MESSAGE = By.xpath("//*[@id='co_searchInputErrorMessageContainerAlerts']//div[contains(@class,'co_infoBox_message')]");

    public WebElement searchInput() {
        return waitForExpectedElement(SEARCH_INPUT);
    }

    public boolean isSearchInputLabelDisplayed() {
        return isElementDisplayed(SEARCH_INPUT_LABEL);
    }

    public boolean isSearchInputDisplayed() {
        return isElementDisplayed(SEARCH_INPUT);
    }

    public WebElement documentsNoOlderThanDropdown() {
        return waitForExpectedElement(DOCUMENTS_NO_OLDER_THAN_DROPDOWN);
    }

    public boolean isDocumentsNoOlderThanDropdownDisplayed() {
        return isElementDisplayed(DOCUMENTS_NO_OLDER_THAN_DROPDOWN);
    }

    public boolean isDocumentsNoOlderThanLabelDisplayed() {
        return isElementDisplayed(DOCUMENTS_NO_OLDER_THAN_LABEL);
    }

    public WebElement searchTermsValidationButton() {
        return waitFluentForElement(SEARCH_TERMS_VALIDATION_BUTTON);
    }


    public WebElement sortOrderDropdown() {
        return waitForExpectedElement(SORT_ORDER_DROPDOWN);
    }

    public boolean isSortOrderDropdownDisplayed() {
        return isElementDisplayed(SORT_ORDER_DROPDOWN);
    }

    public boolean isSortOrderLabelDisplayed() {
        return isElementDisplayed(SORT_ORDER_LABEL);
    }

    public WebElement termsAndConnectorsInfoIcon() {
        return waitForExpectedElement(TERMS_AND_CONNECTORS_INFO_ICON);
    }

    public boolean isTermsAndConnectorsInfoIconDisplayed() {
        return isElementDisplayed(TERMS_AND_CONNECTORS_INFO_ICON);
    }

    public String termsAndConnectorsPopupTitle() {
        return waitForExpectedElement(TERMS_AND_CONNECTORS_POPUP_HEADER).getText();
    }

    public boolean isTermsAndConnectorsPopupTitleDisplayed() {
        return isElementDisplayed(TERMS_AND_CONNECTORS_POPUP_HEADER);
    }

    public WebElement termsAndConnectorsPopupCloseIcon() {
        return waitForExpectedElement(TERMS_AND_CONNECTORS_POPUP_CLOSE_ICON);
    }

    public boolean isTermsAndConnectorsPopupCloseIconDisplayed() {
        return isElementDisplayed(TERMS_AND_CONNECTORS_POPUP_CLOSE_ICON);
    }

    public boolean isTermsAndConnectorsPopupCloseButtonDisplayed() {
        return isElementDisplayed(TERMS_AND_CONNECTORS_POPUP_CLOSE_BUTTON);
    }

    public String searchBarInfoMessage() {
        return waitForExpectedElement(SEARCH_INFO_MESSAGE).getText();
    }

    public WebElement previewResultsButton() {
        return waitForExpectedElement(PREVIEW_RESULTS_BUTTON);
    }

    public boolean isPreviewResultsButtonDisplayed() {
        return isElementDisplayed(PREVIEW_RESULTS_BUTTON);
    }

    public boolean isPreviewResultsPopupHeaderDisplayed() {
        return isElementDisplayed(PREVIEW_RESULTS_POPUP_HEADER);
    }

    public WebElement previewResultsPopupCloseIcon() {
        return waitForExpectedElement(PREVIEW_RESULTS_POPUP_CLOSE_ICON);
    }

    public boolean isPreviewResultsPopupCloseIconDisplayed() {
        return isElementDisplayed(PREVIEW_RESULTS_POPUP_CLOSE_ICON);
    }

    public boolean isPreviewResultsPopupCloseButtonDisplayed() {
        return isElementDisplayed(PREVIEW_RESULTS_POPUP_CLOSE_BUTTON);
    }

    public List<String> previewResultsPopupContentTypes() {
        return commonMethods.getTextsFromWebElements(waitForExpectedElements(PREVIEW_RESULTS_POPUP_CONTENT_TYPE_HEADERS));
    }

    public List<String> previewResultsPopupResultsGuid() {
        List<WebElement> previewResults = waitForExpectedElements(PREVIEW_RESULTS_POPUP_DOCUMENTS);
        List<String> previewResultsGUID = new ArrayList<>();
        for(WebElement previewResult : previewResults) {
            String documentIdInList = previewResult.getAttribute("id");
            previewResultsGUID.add(StringUtils.removeStart(documentIdInList, "document_icons_"));
        }
        return previewResultsGUID;
    }

    public List<String> documentsNoOlderThanDropdownOptionsList() {
        return commonMethods.getTextsFromWebElements(new Select(documentsNoOlderThanDropdown()).getOptions());
    }

    public void selectOptionFromDocumentsNoOlderThanDropdown(String dropdownOption) {
        selectDropDownByVisibleText(documentsNoOlderThanDropdown(), dropdownOption);
    }

    /**
     * @param dropdownOption Date or Relevance
     */
    public void selectOptionFromSortOrderDropdown(String dropdownOption) {
        selectDropDownByVisibleText(sortOrderDropdown(), dropdownOption);
    }
}
