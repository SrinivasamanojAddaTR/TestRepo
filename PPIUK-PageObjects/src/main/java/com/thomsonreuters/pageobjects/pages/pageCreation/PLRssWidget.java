package com.thomsonreuters.pageobjects.pages.pageCreation;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Phil Harper on 19/04/2016. This is the PO class for RSS Legal Update "RSS widgets" that are
 * sometimes included on PL pages, most notably on Practice Area Home Pages.
 * This includes the "PLC Subscribe via RSS" page
 * Initially using these POs for CPET testing within the Page Creation project
 */

public class PLRssWidget extends AbstractPage {

    public WebElement RSSItem(String RSSNumber) {
        return waitForExpectedElement(By.xpath("//a[text()='RSS']/ancestor::h3/following-sibling::div//div[@id='ContentBlock" + RSSNumber + "']"),15);
        }

    public WebElement RSSItemCB0() {
        return waitForExpectedElement(By.id("ContentBlock0"),15);
    }

    public WebElement RSSLegalUpdateHeader() {
        return waitForExpectedElement(By.xpath
                ("//h3[@class='co_genericBoxHeader'][contains(.,'Legal updates')]"));
    }
    public List<WebElement> LegalUpdatesWholewidget(String LegalUpdateHeading) {
        String Heading;
        if(LegalUpdateHeading.length()==0) {
            Heading="Legal updates";
        }else{
            Heading=LegalUpdateHeading;
        }
        return waitForExpectedElements(By.xpath
                ("//h3[@class='co_genericBoxHeader'][contains(.,'" + Heading + "')]/following-sibling::*//li"));
    }

    public WebElement RSSLegalUpdateRSSIcon() {
        return waitForExpectedElement(By.xpath
                ("//h3[@class='co_genericBoxHeader']//a[contains(.,'RSS')]"));
    }

    public WebElement RSSLegalUpdateViewAll() {
        return waitForExpectedElement(By.xpath
                ("//div[@class='co_genericBoxFooter'][contains(.,'View all')]"));
    }

    public WebElement RSSPLSubscribePLUpdatesRSS() {
        return waitForExpectedElement(By.xpath
                ("//h1[contains(@class,'co_title')][text()='Subscribe to Practical Law updates via RSS']"));
    }

    public WebElement RSSItemUsingHeading(String Heading, String RSSNumber) {
        return waitForExpectedElement(By.xpath("//h3[@class='co_genericBoxHeader'][contains(.,'" + Heading + "')" +
                "]/following-sibling::*//div[@id='ContentBlock" + RSSNumber + "']"));
    }

    public WebElement RSSItemUsingHeadingAndAssocDate(String Heading, String RSSNumber) {
        return waitForExpectedElement(By.xpath("//div[@class='co_genericBoxHeader'][contains(.,'" + Heading + "')" +
                "]/following-sibling::*//div[@id='ContentBlock" + RSSNumber + "']/div[1]/h4/span[@class='co_date']"));
    }

    public WebElement RSSItemAndAssocDate(String RSSNumber) {
        return waitForExpectedElement(By.xpath("//div[@class='co_genericBoxContent']//div[@id='ContentBlock" +
                RSSNumber + "']//h4/span[@class='co_date']"));
    }

    public WebElement RSSLegalUpdateViewAllCompEU() {
        return waitForExpectedElement(By.xpath("//div[@class='co_genericBoxFooter']/a[contains(@href,'7-103-2186')]"));
    }

    public WebElement RSSLegalUpdateViewAllCompUK() {
        return waitForExpectedElement(By.xpath("//div[@class='co_genericBoxFooter']/a[contains(@href,'5-103-1748')]"));
    }

    public WebElement RSSLegalUpdatePageLabel(String PracticeArea) {
        return waitForExpectedElement(By.xpath
                ("//h1[@id='co_browsePageLabel'][contains(.,'" + PracticeArea + "')]"));
    }

}


