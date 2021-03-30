package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by u4400015 on 26/10/2015.
 */
public class CountryQAToolPage extends AbstractPage {

    public CountryQAToolPage() {
    }

    public WebElement internationalTransactionGuidesLink() {

        return waitForExpectedElement(By.xpath("//div[@id='co_document']//a[contains(text(),'International transaction guides')]"));
    }


    public WebElement topicLinks(String topic) {

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//a[contains(text(),'" + topic + "')]"));
    }


    /**
     * this is an object representing the select all checkbox on the questions page of the country Q&A tool
     * and also on the jurisdictions page
     * @return
     */

    public WebElement selectAllCheckbox() {

        return waitForExpectedElement(By.xpath("//div[starts-with(@class,'co_comparisonTool')]//input[@ng-model='allCheckbox']"),20);
    }


    public WebElement selectJurisdictionsButton() {

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//a[contains(text(),'Select Jurisdictions')]"),20);
    }

    public WebElement compareButton() {

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//a[contains(text(),'Compare')]"),20);
    }


    /**
     * this is an object representing the author name link on an article - the user can click this and be taken to contributor
     * information photograph and bio
     */

    public WebElement authorNameLink() {

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//span[contains(text(),'by')]//following-sibling::span/a"));
    }


    /**
     * this is an object representing a photo of an author - it is deliberately non specific since contributors change
     * over time and the test using it is just seeking to validate that author photos and bio etc are displayed
     */

    public WebElement authorPhoto() {

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//a/img"));
    }

    /**
     * this is an object representing part of the author biography information - all of which should contain the text
     * Professional qualifications
     */

    public WebElement authorBio() {

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//strong[contains(text(),'Professional qualifications')]"));
    }

    /**
     * Object on the comparison tool results page representing the non selected Jurisdictions
     */

    public WebElement countryNameLink(String name) {
        return waitForExpectedElement(By.xpath("//div[@id='co_jurisdictionSelection']//self::input[@value='" + name + "']"));
    }
    
    public void waitToDocumentDisplayed() {
    	waitForExpectedElement(By.id("co_jurisdictionSelection"));
	}
    
    /**
     * Object on the comparison tool page representing the breadCrumbs on the header
     */
    
    public WebElement breadCrumb() {
        return waitForExpectedElement(By.xpath("//div[@id='coid_docBreadcrumbContainer']"));
    }
 
    public WebElement breadCrumbMainLink() {
        return breadCrumb().findElement(By.xpath("./a"));
    }
    
    public WebElement breadCrumbTopicLink() {
        return breadCrumb().findElement(By.xpath("./span/a"));
    }

}
