package com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;

public class PracticalLawToolsPage extends AbstractPage {

    public boolean isFastDraftTabActive() {
    	return isElementDisplayed(By.xpath("//li[contains(@class,'co_tabActive')]//a[text()='Fastdraft']"));
    }

    public boolean isFirmStyleTabActive() {
    	return isElementDisplayed(By.xpath("//li[contains(@class,'co_tabActive')]//a[text()='Firmstyle']"));
    }

}