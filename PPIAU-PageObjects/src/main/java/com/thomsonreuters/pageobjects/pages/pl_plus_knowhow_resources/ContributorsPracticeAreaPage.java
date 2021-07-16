package com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.DocumentDisplayAbstractPage;

public class ContributorsPracticeAreaPage extends DocumentDisplayAbstractPage {
	
	public List<WebElement> contibutorFirmsLogosOnNewSite() {
        return waitForExpectedElements(By.xpath("//tbody//img[@src]"));
    }
	
	//there are src elements that do not contain images on old site 
	public List<WebElement> contibutorFirmsLogosOnOldSite() {
        return waitForExpectedElements(By.xpath("//tbody//img[contains(@src,'Satellite')]"));
    }

}
