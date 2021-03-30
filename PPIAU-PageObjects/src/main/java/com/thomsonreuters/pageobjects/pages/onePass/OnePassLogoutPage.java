package com.thomsonreuters.pageobjects.pages.onePass;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class OnePassLogoutPage extends AbstractPage {
	
	public WebElement logOutBrandingLogo() {
        return waitForExpectedElement(By.id("co_logo"));
    }
    
	public boolean isLogOutBrandingLogoCompartmentsPresent() {
        return isExists(By.className("legal-compartments_signOut"));
    }

    public By resumeAsCurrentUserLinkText() {
        return By.partialLinkText("Resume as");
    }

    public WebElement sessionSummaryBox() {
        return waitForExpectedElement(By.className("co_signOff_sessionSummary"));
    }

	public WebElement sessionDetailsTable() {
		return waitForExpectedElement(By.xpath("//div[@id='coid_SessionActivityDetails']/table"));
	}
	
    public WebElement signOffPageSignOnButton() {
        return waitForElementToBeClickable(waitForExpectedElement(By.id("coid_website_signBackOnButton")));
    }
    
    public WebElement resumeAsCurrentUserLink() {
        return waitForExpectedElement(By.partialLinkText("Resume as"));
    }
    
    public String sessionSummary() {
        return (String) executeScript("return $('#coid_SessionActivitySummary').prev().html()");
    }

    public WebElement signInWithDifferentAccountLink() {
        return waitForExpectedElement(By.partialLinkText("Sign in with a different account"));
    }

    public WebElement sessionDateAndTime() {
        return waitForExpectedElement(By.xpath("//div[@class='co_signOff_sessionSummary']//div[2]"));
    }
}
