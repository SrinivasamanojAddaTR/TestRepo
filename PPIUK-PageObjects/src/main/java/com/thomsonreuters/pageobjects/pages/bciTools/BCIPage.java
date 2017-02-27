package com.thomsonreuters.pageobjects.pages.bciTools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.thomsonreuters.driver.framework.AbstractPage;

public class BCIPage extends AbstractPage{
	
    public WebElement widgetHeadNameText() {
        return waitForExpectedElement(By.xpath("//div[@class='co_featureBoxInner image_right']//h3"));
    }

    public WebElement widgetText() {
        return waitForExpectedElement(By.xpath("//div[@class='co_featureBoxInner image_right']//p"));
    }
    
    public WebElement widgetIcon() {
        return waitForExpectedElement(By.xpath("//div[@class='co_featureBoxImage image_right']//img[contains(@src,'.png')]"));
    }
    
    
}
