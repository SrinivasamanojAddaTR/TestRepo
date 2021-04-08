package com.thomsonreuters.pageobjects.pages.footer;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ContributingFirmsPage extends AbstractPage {

	private Random rand = new Random();

	public WebElement jumpLink(String letter) {
		return waitForExpectedElement(By.xpath("//a[@class='contributor-anchors-letter' and text()='"+letter+"']"));
	}

	public boolean isTheUserTakenToSelectedPartOfList(String letter) {
		waitForPageToLoad();
		return isViewScrolledToElement(waitForExpectedElement(By.xpath("//h2[text()='"+letter+"']")));
	}

	public WebElement randomFirm() {
		List<WebElement> collumnFirms = waitForExpectedElements(By.xpath("//div[@id='one-column-page']/div[@class='co_2Column col-content']"));
		int randomInt = rand.nextInt(collumnFirms.size());
		List<WebElement> firms = waitForExpectedElements(By.xpath("//div[@id='one-column-page']/div[@class='co_2Column col-content']["+randomInt+"]//a"));
		randomInt = rand.nextInt(firms.size());
		return firms.get(randomInt);
	}

	public boolean isMainInformationDisplayed() {
		return isExists(By.xpath("//div[@id='co_contentColumn']//div[@class='main']"));
	}

	public boolean isContributedResourcesDisplayed() {
		return isExists(By.xpath("//h3[@class='co_genericBoxHeader' and text()='Contributed resources']"));
	}

	public boolean isLogoDisplayed() {
		return isExists(By.xpath("//div[@class='contributor-logo']/img"));
	}

	public boolean isContactInformationDisplayed() {
		return isExists(By.xpath("//div[@class='contributor-contact']/h3[text()='Contact information']"));
	}


}
