package com.thomsonreuters.pageobjects.pages.legalUpdates;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class LegalUpdatesBasePage extends AbstractPage {
    
    public LegalUpdatesBasePage() {
    	
    }


    public WebElement headerMetaDataTag() {
        return retryingFindElement(By.id("co_browsePageLabel"));
    }
    
    public String headerMetaDataTagText() {
        return headerMetaDataTag().getText();
    }

    public WebElement subscriptionPreferencesWidget() {
        return findElement(By.xpath("//div[contains(@class,'co_overlayBox_container co_search_advancedSearch_termFrequency_lightboxclass') and @id='co_browseHeader']"));
    }

    public WebElement searchMetaDataTag() {
        return waitForExpectedElement(By.xpath("//li[@id='co_searchWidgetCustomTab']//a"));
    }

    public WebElement successfulSaveSubscriptionNotificationMessage() {
        return findElement(By.xpath("//div[@id='infoboxContainer' and contains(@class, 'co_infoBox success right')]"));

    }
    
    public WebElement researchFoldersWidget() {
    	return waitForExpectedElement(By.xpath("//div[@class='co_dockContent']//div[@class='co_overlayBox_content']"));
    }
    
    public WebElement emailDeliveryWidget() {
    	return waitForExpectedElement(By.id("co_deliveryLightbox"));
    }
}
