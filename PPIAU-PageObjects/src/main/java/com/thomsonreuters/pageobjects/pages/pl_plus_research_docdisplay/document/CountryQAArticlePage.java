package com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by u4400015 on 18/11/2015.
 */

public class CountryQAArticlePage extends AbstractPage {


    public WebElement contributorProfilesHeading() {

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//h2[contains(text(),'Contributor')]"));

    }

}
