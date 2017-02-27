package com.thomsonreuters.pageobjects.pages.inhouse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.thomsonreuters.driver.framework.AbstractPage;

public class InHouseCollectionPage extends AbstractPage {
	
	public List<WebElement> leftBlockLinks(String title) {
		return waitForExpectedElements(By.xpath("//h3[text()='" + title + "']//ancestor::div[@class='co_2Column']/descendant::div[@id='coid_categoryBoxTab1SubPanel1-0-main']//a"));
	} 
	
	public List<WebElement> leftBlockDescriptions(String title) {
		return waitForExpectedElements(By.xpath("//h3[text()='" + title + "']//ancestor::div[@class='co_2Column']/descendant::div[@id='coid_categoryBoxTab1SubPanel1-0-main']//p"));
	}
	
	public List<WebElement> rightBlockDescriptions(String title) {
		return waitForExpectedElements(By.xpath("//h3[text()='" + title + "']//ancestor::div[@class='co_2Column']/descendant::div[@id='coid_categoryBoxTab1SubPanel2-0-main']//p"));
	}
	
	public List<WebElement> leftBlockPictures(String title) {
		return waitForExpectedElements(By.xpath("//h3[text()='" + title + "']//ancestor::div[@class='co_2Column']/descendant::div[@id='coid_categoryBoxTab1SubPanel1-0-main']//img"));
	}
	
	public WebElement linkInWidgetWithPicture(String title) {
		return waitForExpectedElement(By.xpath("//h3[text()='" + title + "']//ancestor::div[@class='co_featureBoxInner']/descendant::a"));
	}
	
	public WebElement pictureFromLink(WebElement element) {
		return element.findElement(By.tagName("img"));
	}
	
	public List<WebElement> rightBlockLinks(String title) {
		return waitForExpectedElements(By.xpath("//h3[text()='" + title + "']//ancestor::div[@class='co_2Column']/descendant::div[@id='coid_categoryBoxTab1SubPanel2-0-main']//a"));
	}
	
	public List<WebElement> leftBlockDates(String title) {
		return waitForExpectedElements(By.xpath("//h3[text()='" + title + "']//ancestor::div[@class='co_2Column']/descendant::div[@id='coid_categoryBoxTab1SubPanel1-0-main']//span"));
	}	
	
	public List<WebElement> rightBlockDates(String title) {
		return waitForExpectedElements(By.xpath("//h3[text()='" + title + "']//ancestor::div[@class='co_2Column']/descendant::div[@id='coid_categoryBoxTab1SubPanel2-0-main']//span"));
	}
	
	public WebElement readMoreOnInhouseBlogButton() {
		return waitForExpectedElement(By.linkText("Read more on the In-house Blog"));
	}
	
	public WebElement readMoreButton(String title) {
		return waitForExpectedElement(By.xpath("//h3[text()='" + title + "']//ancestor::div[@class='co_2Column']/descendant::div[@class='co_genericBoxFooter']/a"));
	}
	
	public WebElement learnMoreButton(String title) {
		return waitForExpectedElement(By.xpath("//h3[text()='" + title + "']//ancestor::div[@class='co_featureBoxInner']/descendant::a[text()='Learn more']"));
	}
	
	public boolean isSectionTitleDisplayed(String title){
		return isElementDisplayed(By.xpath("//h3[text() = '" + title + "']"));
	}

}
