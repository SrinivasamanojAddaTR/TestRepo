package com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document_navigation;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.enums.Jurisdiction;
import org.openqa.selenium.TimeoutException;


/**
 * This page object is to identify the Legislation Document navigation elements and depicts the document navigation functionality.
 * <p/>
 */

public class LegislationDocumentNavigationPage extends DocumentNavigationPage {

    public static final String PROVISION = "Provision";

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
            throw new PageOperationException("Exceeded time to find the jurisdiction " + jurisdiction.getJurisdictionDocumentLinkLocator() + "on Left hand navigation " + te.getMessage());
        }
    }

}