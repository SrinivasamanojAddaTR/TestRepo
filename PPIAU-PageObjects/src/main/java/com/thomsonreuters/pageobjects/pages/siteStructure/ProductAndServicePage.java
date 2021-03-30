package com.thomsonreuters.pageobjects.pages.siteStructure;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductAndServicePage extends AbstractPage  {

    public WebElement westLawTRLogo() {
        return waitForExpectedElement(By.id("co_trLogo_link"));
    }
    
    public boolean isProductAndServicesButtonPresent(){
    	return isElementDisplayed(By.xpath("//*[contains(text(), 'Products & Services')]"));
    }
    
    public WebElement trLogoLoginPages() {
        return waitForExpectedElement(By.className("thomsonreuterslogo"));
    }

}
