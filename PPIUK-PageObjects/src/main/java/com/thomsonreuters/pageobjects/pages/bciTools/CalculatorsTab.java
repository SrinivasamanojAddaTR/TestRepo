package com.thomsonreuters.pageobjects.pages.bciTools;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.thomsonreuters.driver.framework.AbstractPage;

public class CalculatorsTab extends AbstractPage {
	
	private static final By TIPS_LOCATOR = By.xpath("//div[@class='ui-tooltip-content']");
	private final By calcWidgetLocator = By.xpath("//div[@id='calcContainer']//form");

	public WebElement offenceTypeDropdown() {
		return waitForExpectedElement(By.id("srCalcNavigation"));
	}
	
	public By getCalcWidgetLocator() {
		return calcWidgetLocator;
	}

	public WebElement calcWidget() {
		return waitForExpectedElement(calcWidgetLocator);
	}
	
	public void loadingSpinnerIsAbsent() {
		waitForElementInvisible(By.className("co_loading"));
	}

	public WebElement adjustmentDropDown() {
		return waitForExpectedElement(By.id("adjustmentsDropdown"));
	}
	
	public List<WebElement> adjustmentDropDownList() {
		return waitForExpectedElements(By.id("adjustmentsDropdown"));
	}

	public WebElement guiltyPleaReductionDropDown() {
		return waitForExpectedElement(By.id("guiltyPlea"));
	}

	public WebElement adjustmentInput() {
		return waitForExpectedElement(By.id("adjustmentsInput"));
	}
	
	public List<WebElement> adjustmentInputList() {
		return waitForExpectedElements(By.id("adjustmentsInput"));
	}

	public WebElement adjustmentNotesDropdown() {
		return waitForExpectedElement(By.xpath("//select[contains(@class,'adjustmentsNotes')]"));
	}

	public WebElement addAnotherAdjustmentButton() {
		return waitForExpectedElement(By.id("adjustmentsButton"));
	}
	
	public WebElement removeAdjustmentButton() {
		return waitForElementPresent(By.className("removeAdjustmentButton"));
	}

	public WebElement culpabilityDropDown() {
		return waitForExpectedElement(By.id("culpability"));
	}

	public WebElement organizationDropDown() {
		return waitForExpectedElement(By.id("organizationSize"));
	}
	
	public WebElement seriousnessSlider() {
		return waitForExpectedElement(By.xpath("//div[contains(@class,'sliderCalc')]"));
	}

	public WebElement seriousnessSliderButton() {
		return waitForExpectedElement(By.xpath("//div[contains(@class,'sliderCalc')]//a[contains(@class,'ui-slider-handle')]"));
	}
	
	public WebElement seriousnessSliderLessValue() {
		return waitForElementPresent(By.xpath("//div[@class='sliderlegend_less']/span[contains(@class,'sliderlegend_value')]"));
	}
	
	public WebElement seriousnessSliderCurrentValue() {
		return waitForElementPresent(By.xpath("//div[@class='sliderlegend_current']/span[contains(@class,'sliderlegend_value')]"));
	}
	
	public WebElement startingPointLabel(){
		return waitForElementPresent(By.xpath("//div[@class='sliderlegend_current']/div/span[text()='Starting point']"));
	}

	public WebElement seriousnessSliderMoreValue() {
		return waitForElementPresent(By.xpath("//div[@class='sliderlegend_more']/span[contains(@class,'sliderlegend_value')]"));
	}
	
	public List<WebElement> seriousnessFactorsReducing() {
		return waitForExpectedElements(By.xpath("//div[contains(@class,'s_factor_subgroup')]//div[contains(@ng-repeat,'reducingFactor')]"));
	}

	public List<WebElement> seriousnessFactorsIncreasing() {
		return waitForExpectedElements(By.xpath("//div[contains(@class,'s_factor_subgroup')]//div[contains(@ng-repeat,'increasingFactor')]"));
	}
	
	public List<WebElement> seriousnessFactors() {
		return waitForExpectedElements(By.xpath("//div[contains(@class,'s_factor_subgroup_item')]"));
	}
	
	public WebElement getCheckbox(WebElement element){
		return element.findElement(By.xpath(("./input")));
	}
	
	public WebElement toggleSFactorsButton(){
		return waitForExpectedElement(By.xpath("//a[contains(text(),'Show Factors')]"));
	}
	
	public By getNotionalSentenceLocator(){
		return By.id("sentenceRangeResultId");
	}
	
	public WebElement getNotionalSentence(){
		return waitForElementPresent(getNotionalSentenceLocator());
	}
	
	public WebElement labelByText(String text){
		return waitForExpectedElement(By.xpath("//*[contains(text(),'" + text + "')]"));
	}
	
	
//	public WebElement tips(){
//		return waitForElementVisible(tipsLocator());
//	}
	
	public List<WebElement> tips(){
		return waitForExpectedElements(TIPS_LOCATOR);
	}
	
	public void tipsIsAbsent() {
		waitForElementAbsent(TIPS_LOCATOR);
	}
		
}
