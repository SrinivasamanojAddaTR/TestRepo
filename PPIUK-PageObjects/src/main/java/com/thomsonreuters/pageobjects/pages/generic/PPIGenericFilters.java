package com.thomsonreuters.pageobjects.pages.generic;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by U6038385 on 12/01/2017.
 */
public class PPIGenericFilters extends AbstractPage {

    public WebElement selectFacetAfterHeading(String facetHeading,
        String facetToSelect) throws Throwable {
        String checkboxIdentifier;
        // Identifies the label for facet, the takes the "for" attribute to identify the matching checkbox
        // This could have been done by expanding the XPath but given it's already long, I decided to use 2 steps
        checkboxIdentifier = waitForExpectedElement((By.xpath("//div[@class='co_divider']/h4[@class='co_facet_header'][text()='" + facetHeading +"']//parent::div//label[text()='" + facetToSelect + "']")),15).getAttribute("for");
        return waitAndFindElement((By.id(checkboxIdentifier)));
    }
}
