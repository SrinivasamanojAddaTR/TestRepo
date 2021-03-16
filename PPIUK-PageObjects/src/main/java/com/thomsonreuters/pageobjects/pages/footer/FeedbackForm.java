package com.thomsonreuters.pageobjects.pages.footer;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class FeedbackForm extends AbstractPage {

    private static final String FEEDBACK_FORM = "//*[@id='co_feedback']//*[contains(text(),'%s')]";

	public WebElement label(String field) {
		return waitForExpectedElement(By.xpath("//label[starts-with(text(),'" + field + "')]"));
	}

	
	public WebElement submitButton() {
		return waitForExpectedElement(By.id("co_submitFeedbackButton"));
	}
	
	public WebElement closeButton() {
		return waitForExpectedElement(By.id("co_search_lightbox_closeButton"));
	}

    public void closeFeedBackForm() {
        waitForExpectedElement(By.id("co_search_lightbox_closeButton")).click();
    }
	
    public boolean isFeedBackFormPresent(){
    	return isExists(By.xpath("//*[contains(text(),'Practical Law Feedback')]"));
    }

    public WebElement telephoneNumber() {
        return waitForExpectedElement(By.id("co_feedback_phone"));
    }

    public WebElement emailAddress() {
        return waitForExpectedElement(By.id("co_feedback_email"));
    }

    public WebElement captcha() {
        return waitForExpectedElement(By.id("recaptcha"));
    }

    public boolean isFeedBackFormPresent(String feedbackFormName) {
        return isExists(By.xpath(String.format(FEEDBACK_FORM, feedbackFormName)));
    }
}
