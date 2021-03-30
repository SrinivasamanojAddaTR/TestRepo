package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by Steph Armytage on 26/01/2015. This is the category page that the user arrives at after selecting
 * the link to "Practice" on the Practical Law landing page.
 */

public class PracticeAreaLandingPage extends AbstractPage {

    public PracticeAreaLandingPage() {
    }

    public WebElement mediaAndTelecomsLink() {

        return waitForExpectedElement(By.xpath("//a[@id='coid_media___telecoms']"));
    }

    public WebElement genericLink(String linkText) {

        return waitForExpectedElement(By.xpath("//div[@id='coid_website_browseMainColumn']//a[text()='" + linkText + "']"));
    }

    public WebElement socialMediaLink() {

        return waitForExpectedElement(By.linkText("Social media"));
    }

    public WebElement PASearchDefault(String practiceAreaSearchDefault) {
        return waitForExpectedElement(By.xpath
                ("//button[@id='scopedSearchMenu'][contains(@title,'" + practiceAreaSearchDefault + "')]"));
    }

    public WebElement topicLink(String topic) {
        return waitForExpectedElement(By.xpath("//*[@id='coid_website_browseMainColumn']//a[contains(text(),'" + topic + "')]"));
    }

}