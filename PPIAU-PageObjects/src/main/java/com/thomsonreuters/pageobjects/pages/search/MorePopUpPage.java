package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MorePopUpPage extends AbstractPage {

    private static final By searchBoxSelector = By.id("co_facet_searchBoxInput");
    private static final String SEARCH_ITEM = ".//a[@linkType='item']//span[text()='%s']";
    private static final By continueButton = By.id("co_facet_knowHowJurisdictionSummary_continueButton");
    private static final By selectedFacetsList = By.cssSelector("#co_facet_knowHowJurisdictionSummary_selectedOptions li a");
    private static final By cancelButton = By.id("co_facet_knowHowJurisdictionSummary_cancelButton");
    private static final By morePopupFrame = By.id("co_facet_knowHowJurisdictionSummary_popup");

    public void selectSearchItem(List<String> searchTerms) {
        for (String term : searchTerms) {
            WebElement searchBox = waitForExpectedElement(searchBoxSelector);
            searchBox.clear();
            searchBox.sendKeys(term);
            waitForExpectedElement(By.xpath(String.format(SEARCH_ITEM, term))).click();
        }
    }

    public void selectContinue() {
        waitForExpectedElement(continueButton).click();
        waitForElementInvisible(morePopupFrame);
    }

    public List<String> getSelectedFacetNames() {
        List<String> list = new ArrayList<>();
        for (WebElement element : waitForExpectedElements(selectedFacetsList)) {
            list.add(element.getText());
        }
        return list;
    }

    public void selectCancelButton() {
        waitForExpectedElement(cancelButton).click();
        waitForElementInvisible(morePopupFrame);
    }
}
