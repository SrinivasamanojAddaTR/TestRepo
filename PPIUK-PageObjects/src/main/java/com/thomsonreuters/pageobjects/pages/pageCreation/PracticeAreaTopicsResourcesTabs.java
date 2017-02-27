package com.thomsonreuters.pageobjects.pages.pageCreation;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by Phil Harper on 06/05/2016. The Topics and Resources Tabs are Tabs that the user MAY see on
 * SOME Practice Area pages after selecting a particular Practice Area.
 */

public class PracticeAreaTopicsResourcesTabs extends AbstractPage {

    public WebElement PLHorizontalTabHeader(String TabName) {
        //Generic PO for tabs in various areas of PL, such as PL Homepage ( Practice areas,         Resources,       International)
        return waitForExpectedElement(By.xpath("//a[@class='co_tabLink'][@href='#'][contains(.,'"+TabName+"')]"));
    }

    public WebElement TabLink(String TabNumber, String LinkName) {
        return waitForExpectedElement(By.xpath
                ("//div[@id='coid_categoryBoxTabPanel"+ TabNumber +"']//ul/li/a[normalize-space(.)" +
                "=\"" + LinkName + "\"]"));
    }

    public String TabNumber(String TabName){
        String DerivedTabNumber;
        String ReturnedID;
        WebElement TabID=waitForExpectedElement(By.xpath("//h2/a[contains(.,'"+ TabName +"')]/ancestor::li"));
        ReturnedID=TabID.getAttribute("id");
        String StringParts[]=ReturnedID.split("coid_categoryTab");
        //the id returned will look like "coid_categoryTab1_main_0"
        //we want the number following the 'coid_categoryTab'
        //the number is contained within the second part of the split
        String StringParts2[]=StringParts[1].split("_");
        DerivedTabNumber=StringParts2[0];
        return DerivedTabNumber;
            }

    //div[@id='coid_categoryBoxTabPanel1']//ul/li/a[normalize-space(.)="Insurance"]

 /*   public WebElement TopicCompetition(String TopicName) {
        return waitForExpectedElement(By.xpath("//div[@id='coid_categoryBoxTabPanel2']//ul/li/a[normalize-space(.)" +
                "=\"" + TopicName + "\"]"));
    }

    public WebElement TopicEULaw(String TopicName) {
        return waitForExpectedElement(By.xpath("//div[@id='coid_categoryBoxTabPanel3']//ul/li/a[normalize-space(.)" +
                "='" + TopicName + "']"));
    }
*/
    public WebElement ResourcesTab() {
        return waitForExpectedElement(By.xpath("//a[@class='co_tabLink'][@href='#'][contains(.,'Resources')]"));
    }

    public WebElement ResourcesDefault(String ResourcesName) {
        return waitForExpectedElement(By.xpath("//div[@id='coid_categoryBoxTabPanel2']//ul/li/a[normalize-space(.)" +
                "='" + ResourcesName + "']"));
    }

    public WebElement ResourcesDefaultLabel() {
        return waitForExpectedElement(By.xpath("//div[@id='coid_categoryBoxTabPanel2']//ul/li/a"));
    }


}
