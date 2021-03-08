package com.thomsonreuters.pageobjects.pages.footer;

import com.thomsonreuters.pageobjects.pages.company.AboutCompanyPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdvisoryBoardsPage extends AboutCompanyPage {
	
	public List<WebElement> paNamesInList() {
		return waitForExpectedElements(By.xpath("//div[@id='one-column-page']//a"));
	}

	public WebElement specifiedPracticeArea(String paName) {
		return waitForExpectedElement(By.xpath("//div[@id='one-column-page']//a[text()='"+paName+"']"));
	}

	public WebElement textOnTop() {
		return waitForExpectedElement(By.xpath("//div[@id='one-column-page']/p"));
	}

	public List<WebElement> peopleNames() {
		return waitForExpectedElements(By.xpath("//div[@class='co_featureBoxInner']/p[1]"));
	}

	public WebElement tableOfContentSection() {
		return waitForExpectedElement(By.id("coid_categoryBoxTab1SubPanel1-0-main"));
	}



}
