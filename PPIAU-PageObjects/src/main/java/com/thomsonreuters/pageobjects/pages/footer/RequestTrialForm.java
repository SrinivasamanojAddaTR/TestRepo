package com.thomsonreuters.pageobjects.pages.footer;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RequestTrialForm extends AbstractPage {

	private CommonMethods commonMethods = new CommonMethods();

	public WebElement label(String field) {
		return waitForExpectedElement(By.xpath("//label[starts-with(text(),'"+field+"')]"));
	}
	
	public WebElement practiceAreaCheckbox(String area) {
		WebElement practiceAreas = waitForExpectedElement(By.xpath("//label[starts-with(text(),'Your practice')]/.."));
		return practiceAreas.findElement(By.xpath(".//*[@class='inner' and text()='"+area+"']/../input[@type='checkbox']"));
	}
	
	public WebElement submitButton() {
		return waitForExpectedElement(By.xpath("//input[@type='submit']"));
	}

	public boolean isFieldMandatory(String field) {		
		return commonMethods.isElementDisplayed(label(field).findElement(By.className("required")));
	}

	public boolean isOptionPresent(String option, String field) {
		return isExists(By.xpath("//label[starts-with(text(),'"+field+"')]/../select/option[@value='"+option+"']"));
	}
	
	public boolean isLabelPresent(String field) {
		return commonMethods.isElementDisplayed(label(field));
	}
	
	public boolean isAreaPresent(String area) {
		return commonMethods.isElementDisplayed(practiceAreaCheckbox(area));
	}

	public boolean isSubmitButtonPresent() {
		return commonMethods.isElementDisplayed(submitButton());
	}



}
