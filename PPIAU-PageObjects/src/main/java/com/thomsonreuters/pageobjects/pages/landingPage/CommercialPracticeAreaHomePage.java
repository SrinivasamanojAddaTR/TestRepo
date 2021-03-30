package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by Phil Harper on 13/04/2016. This is the Practice Area Home Page for "Commercial".
 * Initially using these POs for CPET testing for the Page Creation project
 */

public class CommercialPracticeAreaHomePage extends AbstractPage {

/**
 * Page Object below created by Ian Hudson showing example of a PO that doesn't reference a specific
* element, rather targets those specific as variables in the FF
*/
     public WebElement genericLink(String linkText) {

        return waitForExpectedElement(By.xpath("//div[@id='coid_website_browseMainColumn']//a[text()='" + linkText + "']"));
    }

// Elements below intended for use for CPET testing by Phil Harper

    public WebElement menuTab (String linkText) {
        return waitForExpectedElement(By.xpath("//h2/a[text()='"+linkText+"']"));
    }



}


