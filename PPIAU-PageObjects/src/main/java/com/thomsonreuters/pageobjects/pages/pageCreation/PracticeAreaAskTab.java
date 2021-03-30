package com.thomsonreuters.pageobjects.pages.pageCreation;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by Phil Harper on 06/05/2016. The Ask Tab is one of the Tabs that the user MAY see on SOME Practice Area
 * pages after selecting a particular Practice Area.
 */

public class PracticeAreaAskTab extends AbstractPage {

    public WebElement PLHome(){
        return waitForExpectedElement(By.xpath("//a[@class='co_tabLink'][@href='#'][contains(.,'Ask')]"));
    }

    public WebElement AskTab (){
        return waitForExpectedElement(By.xpath("//a[@class='co_tabLink'][@href='#'][contains(.,'Ask')]"));
    }

    public WebElement AskTabDisclaimer (){
        return waitForExpectedElement(By.xpath("//div[@class='co_scrollWrapper co_categoryBoxTabContents']//p[contains(.,'Disclaimer: None of the Editorial team providing responses to Ask Questions are practising solicitors or barristers. The Ask scope and rules apply.')]"));
    }

    public WebElement AskTabScopeandRules (){
        return waitForExpectedElement(By.xpath("//div[@class='co_scrollWrapper co_categoryBoxTabContents']//a[contains(@href,'/7-508-0007')]"));
    }

    public WebElement AskTabScopeandRulesPage () {
        return waitForExpectedElement(By.xpath("//h1[contains(@class,'co_title noTOC')][text()='Ask scope and rules']"));
    }

    public WebElement AskTabRecentQuery (String RecentQueryNo){
        return waitForExpectedElement(By.xpath("//h3[contains(@class,'co_genericBoxHeader')][text()='Recent queries']/following-sibling::div[@class='co_genericBoxContent']//div[@id='ContentBlock" + RecentQueryNo + "']"));
    }

    public WebElement AskTabRecentQueryReply (String RecentQueryNo){
        return waitForExpectedElement(By.xpath("//div[@class='co_genericBoxContent']//div[@id='ContentBlock" +
                RecentQueryNo + "']//span[@class='co_comments_count']"));
    }

    public WebElement AskTabRecentQueryDate (String RecentQueryNo){
        return waitForExpectedElement(By.xpath("//h3[contains(@class,'co_genericBoxHeader')][text()='Recent queries']/following-sibling::div[@class='co_genericBoxContent']//div[@id='ContentBlock" +
                RecentQueryNo + "']//span[@class='co_date']"));
    }

    public WebElement AskTabGoToPracticeAreaButton (String PracticeArea){
        return waitForExpectedElement(By.xpath("//div[@class='co_genericBoxFooter']/a[contains(.,'Go to Ask:')" +
                "][contains(.,'" + PracticeArea + "')]"));
    }

    public WebElement AskTabAskPracticeAreaPageLabel (String PracticeArea) {
        return waitForExpectedElement(By.xpath("//h1[@id='co_browsePageLabel'][contains(.,'Ask:')]"));
    }

}