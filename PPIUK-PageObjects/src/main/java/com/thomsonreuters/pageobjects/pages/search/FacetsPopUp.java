package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Steph Armytage on 19/01/2015. This is an object to represent the pop up which appears when the
 * user selects the link entitled "More Resource Types" on the search results page
 */
public class FacetsPopUp extends AbstractPage {

    public FacetsPopUp() {
    }

    /**
     * object to represent the facets pop up as a whole
     */

    public WebElement FacetsPopUpBox() {

        return waitForExpectedElement(By.id("co_facet_documentType_popup"));
    }

    /**
     * object to represent the facets pop up filter button used once additional facets selected
     */

    public WebElement FacetsPopUpFilterButton() {

        return waitForExpectedElement(By.id("co_facet_documentType_filterButton"));
    }

    /**
     * object to represent the facets cancel button
     */

    public WebElement FacetsPopUpCancelButton() {

        return waitForExpectedElement(By.id("co_facet_documentType_cancelButton"));
    }

    /**
     * object to represent the facets pop up close option (cross on the top right hand corner)
     */

    public WebElement FacetsPopUpClose() {

        return waitForExpectedElement(By.id("co_facet_documentType_popupClose"));
    }

    /**
     * object to represent the search input field
     */

    public WebElement facetsPopUpInputField() {

        return waitForExpectedElement(By.id("co_facet_searchBoxInput"));
    }

    /**
     * object to represent available facet - pass name in as a string e.g. Checklists
     */

    public WebElement facetsPopUpFacetOption(String Name) {

        return waitForExpectedElement(By.xpath("//ul[@id='co_facet_documentType_availableOptions']//span[contains(text(),'" + Name + "')]"));
    }

    /**
     * object to represent the selected facets section as a whole
     */

    public WebElement facetsPopUpSelectedFacets() {

        return waitForExpectedElement(By.id("co_facet_documentType_selectedOptions"));
    }

    /**
     * object to represent the facets pop up heading - Resource Type
     */

    public WebElement facetsPopUpHeading() {

        return waitForExpectedElement(By.id("co_facet_documentType_popup_title"));
    }




}
