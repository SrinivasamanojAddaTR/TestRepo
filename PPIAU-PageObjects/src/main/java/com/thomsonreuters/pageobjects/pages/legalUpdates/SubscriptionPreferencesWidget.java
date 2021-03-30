package com.thomsonreuters.pageobjects.pages.legalUpdates;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SubscriptionPreferencesWidget extends AbstractPage {

    public SubscriptionPreferencesWidget() {

    }

    public WebElement closeButton() {
        return findElement(By.xpath("//a[@class='co_overlayBox_closeButton' and @id='co_search_lightbox_closeButton']"));
    }

    public WebElement subscriptionNameInput() {
        return findElement(By.xpath("//div[@id='nameBox']//descendant::input[@id='SubscriptionName']"));
    }

    public WebElement deliveryEmailCheckBox() {
        return findElement(By.xpath("//div[@id='deliveryBox']//descendant::input[@id='emailCheck']"));
    }

    public WebElement deliveryMyUpdatesCheckBox() {
        return findElement(By.xpath("//div[@id='deliveryBox']//descendant::input[@id='myUpdatesCheck']"));
    }

    public Select deliveryFrequencySelectBox() {
        return new Select(findElement(By.xpath("//div[@id='frequencyBox']//descendant::select[@id='frequency']")));
    }

    public WebElement emailAddressForNotificationInput() {
        return findElement(By.xpath("//div[@id='emailBox']//descendant::input[@id='email']"));
    }

    public WebElement notificationMessageInput() {
        return findElement(By.xpath("//div[@id='emailBox']//descendant::input[@id='message']"));
    }

    public WebElement notifyMeCheckBox() {
        return findElement(By.xpath("//div[@id='emailBox']//descendant::input[@id='notifyMecheck']"));
    }

    public WebElement saveButton() {
        return findElement(By.xpath("//ul[@id='submitBox']//descendant::input[@type='button' and @value='Save']"));
    }

    public WebElement cancelButton() {
        return findElement(By.xpath("//ul[@id='submitBox']//descendant::a[@id='cancelLink']"));
    }
    
    public WebElement detailsBox() {
    	return findElement(By.xpath("//div[@id='rightSection' and @ng-controller='StyledLightboxSubscriptionDetailsController']"));
    }
    
    public List<WebElement> detailsBoxPracticeAreas() {
    	return findElements(By.xpath("//div//span[text()='Practice Areas']//parent::div//following-sibling::div//span"));
    }
    
    public List<WebElement> detailsBoxTopics() {
    	return findElements(By.xpath("//div//span[text()='Topics']//parent::div//following-sibling::div//span"));
    }
    
    public List<WebElement> detailsBoxJurisdictions() {
    	return findElements(By.xpath("//div//span[text()='Jurisdictions']//parent::div//following-sibling::div//span"));
    }
}
