package com.thomsonreuters.pageobjects.pages.inhouse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.thomsonreuters.driver.framework.AbstractPage;

public class LegalDepartmentManagementPage extends AbstractPage{
	

	public List<WebElement> sectionTitles() {
	return waitForExpectedElements(By.xpath("//div[@class='box-border container-twoCol']/h3"));
	}
	
	public List<WebElement> videoItems() {
		return waitForExpectedElements(By.xpath("//div[@class='co_3Column']/div[@class='co_column']"));
	}
	
	public WebElement videoItem(WebElement element) {
		return element.findElement(By.cssSelector("iframe"));
	}
	
	public WebElement titleItem(WebElement element) {
		return element.findElement(By.cssSelector("a"));
	}
	
	public WebElement descriptionItem(WebElement element) {
		return element.findElement(By.cssSelector("p"));
	}
	
	public boolean isFeaturedContentDisplayed(){
		return isElementDisplayed(By.xpath("//h3[text()='Featured']//ancestor::div[contains(@class,'co_genericBox')]//h3"));
	}

	public List<WebElement> rightBlockLinks(int positionOfSection) {
		return waitForExpectedElements(By.xpath("//div[@class='box-border container-twoCol']/div[" + positionOfSection + "]/div[@id='coid_categoryBoxTab1SubPanel2-0-main']//a"));
	}
	
	public List<WebElement> leftBlockLinks(int positionOfSection) {
		return waitForExpectedElements(By.xpath("//div[@class='box-border container-twoCol']/div[" + positionOfSection + "]/div[@id='coid_categoryBoxTab1SubPanel1-0-main']//a"));
	} 
	
	public List<WebElement> leftBlockDescriptions(int positionOfSection) {
		return waitForExpectedElements(By.xpath("//div[@class='box-border container-twoCol']/div[" + positionOfSection + "]/div[@id='coid_categoryBoxTab1SubPanel1-0-main']//p"));
	}
	
	public List<WebElement> rightBlockDescriptions(int positionOfSection) {
		return waitForExpectedElements(By.xpath("//div[@class='box-border container-twoCol']/div[" + positionOfSection + "]/div[@id='coid_categoryBoxTab1SubPanel2-0-main']//p"));
	}
	
	public List<WebElement> leftBlockPictures(int positionOfSection) {
		return waitForExpectedElements(By.xpath("//div[@class='box-border container-twoCol']/div[" + positionOfSection + "]/div[@id='coid_categoryBoxTab1SubPanel1-0-main']//img"));
	}
	
	public List<WebElement> leftBlockDates(int positionOfSection) {
		return waitForExpectedElements(By.xpath("//div[@class='box-border container-twoCol']/div[" + positionOfSection + "]/div[@id='coid_categoryBoxTab1SubPanel1-0-main']//span"));
	}	
	
	public List<WebElement> rightBlockDates(int positionOfSection) {
		return waitForExpectedElements(By.xpath("//div[@class='box-border container-twoCol']/div[" + positionOfSection + "]/div[@id='coid_categoryBoxTab1SubPanel2-0-main']//span"));
	}
	
	public WebElement readMoreButton(int positionOfSection) {
		return waitForExpectedElement(By.xpath("//div[@class='box-border container-twoCol']/div[" + positionOfSection + "]/div[@class='co_genericBoxFooter']/a"));
	}

}
