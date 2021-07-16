package com.thomsonreuters.pageobjects.pages.landing_page;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by U4400015 on 03/01/2017.
 */
public class UnreleasedCategoryPages extends AbstractPage {


    /**
     * object representing project link options on unreleased category page landing page e.g. search and browse link
     */

    public WebElement projectLink(String project) {
     return waitForExpectedElement(By.xpath("//*[starts-with(@id,'coid_categoryTab')]//h2/a[contains(text(),'" + project + "')]"));

    }

    /**
     * object representing link within search and browse to topic level 1
     */

    public WebElement topicLevel1() {
        return waitForExpectedElement(By.xpath("//a[@id='coid_westlawuktopic1'][contains(text(),'WestlawUktopic1')]"));
    }


}
