package com.thomsonreuters.pageobjects.pages.legalUpdates;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import java.util.ArrayList;
import java.util.List;


public class EmailDeliverWidget extends AbstractPage {
	
	private List<String> formatOptions;
	
	public EmailDeliverWidget() {
		
	}
	
	public WebElement toInput() {
		return retryingFindElement(By.id("co_delivery_emailAddress"));
	}
	
	public WebElement subjectInput() {
		return retryingFindElement(By.xpath("//input[@id='co_delivery_subject']"));
	}
	
	public WebElement listOfItemsRadioButton() {
		return retryingFindElement(By.xpath("//input[@id='co_deliveryWhatToDeliverList']"));
	}
	
	public WebElement documentsRadioButton() {
		return retryingFindElement(By.xpath("//input[@id='co_deliveryWhatToDeliverDocumentOnly']"));
	}
	
	public WebElement formatSelect() {
		return retryingFindElement(By.xpath("//select[@id='co_delivery_format_fulltext']"));
	}
	
	public WebElement emailNoteInput() {
		return retryingFindElement(By.xpath("//textarea[@id='co_delivery_note']"));
	}
	
	public WebElement emailButton() {
		return retryingFindElement(By.xpath("//input[@id='co_deliveryEmailButton' and @type='button']"));
	}
	
	
	public boolean isDeliveryInfoMessageTextDisplayed() {
		return isTextPresent(By.id("co_deliveryWaitMessageTitle"), "The item will be emailed.");
	}
	
	public List<String> getAllFormatOptionsFromSelect(){
		formatOptions = new ArrayList<String>();
		for(WebElement option : new Select(formatSelect()).getOptions()) {
			formatOptions.add(option.getText());
		}
		return formatOptions;
	}
	
	public void fillAddressInput(String emailAddress) {
		toInput().clear();
		toInput().sendKeys(emailAddress);
	}

}
