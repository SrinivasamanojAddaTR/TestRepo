package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.enums.Jurisdiction;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.slf4j.LoggerFactory;


/**
 * This page object is to identify the Legislation Document navigation elements and depicts the document navigation functionality.
 * <p/>
 */

public class LegislationDocumentNavigationPage extends DocumentNavigationPage {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LegislationDocumentNavigationPage.class);

    public static final String PROVISION = "Provision";
    public static final By PRIMARY_MENU_EXPANSION_LOCATOR = By.cssSelector(""); // TODO: Need to add the css

    /**
     * This method is to verify the Provision primary menu is expanded or not
     *
     * @return boolean
     */
    public boolean isProvisionPrimaryMenuExpanded() {
    	return isElementDisplayed(PRIMARY_MENU_EXPANSION_LOCATOR);
    }

    /**
     * This method is to verify the Provision Primary menu is selected or not
     *
     * @return boolean
     */
    public boolean isProvisionPrimaryMenuSelected() {
        try {
            return isPrimaryMenuSelected(PROVISION);
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return false;
    }

    /**
     * This method does the clicking on the given jurisdiction link on the left hand side navigation in provision document.
     *
     * @param jurisdiction
     */
    public void clickOnJurisdictionNaviagationLink(Jurisdiction jurisdiction) {
        if (jurisdiction == null) {
            throw new IllegalArgumentException(" Jurisdiction value is required.");
        }

        try {
            waitForExpectedElement(jurisdiction.getJurisdictionNavigationLinkLocator()).click();
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the jurisdiction " + jurisdiction.getJurisdictionDocumentLinkLocator() + "on Left hand navigation " + te.getMessage());
        }
    }

}