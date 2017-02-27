package com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.springframework.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DocumentDeliveryOptionsPage extends AbstractPage
{

    public WebElement email() {
        return retryingFindElement(By.id("deliveryLinkRowEmail"));
    }

    public WebElement print() {
        return retryingFindElement(By.id("deliveryLinkRowPrint"));
    }

    public WebElement download() {
        return retryingFindElement(By.id("deliveryLinkRowDownload"));
    }

    public WebElement addToFolder() {
        return waitForExpectedElement(By.className("co_dropdownTitle"));
    }
    
    public WebElement quickDraft() {
        return retryingFindElement(By.id("deliveryLinkRowDownloadQuickDraft"));
    }

    public WebElement rss(){
        return retryingFindElement(By.id("co_docToolbarDeliveryWidgetRss"));
    }

    /**
     * @param deliveryOption one of delivery options, that should be selected. Can take the value "Email", "Print", "Download"
     */
    public WebElement accurateDeliveryOption(String deliveryOption) {
        return waitForExpectedElement(By.id("deliveryLinkRow" + StringUtils.capitalize(deliveryOption.toLowerCase())));
    }

    public WebElement advancedTab() { return waitForExpectedElement(By.xpath("//a[contains(@class,'co_tabLink') and contains(.,'Advanced']"));}
    
	public boolean isEmailPresent() {
		return isElementDisplayed(By.id("deliveryLinkRowEmail"));
	}
	
	public boolean isDownloadPresent() {
		return isElementDisplayed(By.id("deliveryLinkRowDownload"));
	}
	
	public boolean isPrintPresent() {
		return isElementDisplayed(By.id("deliveryLinkRowPrint"));
	}
}
