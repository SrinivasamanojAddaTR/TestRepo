package com.thomsonreuters.pageobjects.pages.contactexpress;
import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ContractExpressHeader extends AbstractPage {
	
	private CommonMethods comMethods = new CommonMethods();
	
	  public boolean iscontractExpressTitlePresent() {
	    	//return waitForExpectedElement(By.xpath(".//*[@id='main']/div[1]/div[1]/div"));
	    	return isElementDisplayed(By.xpath(".//*[@id='cecTopActionsTitle']/span[2]"));
	    }
	  
	  public WebElement contractExpressTitle() {
		  return findElement(By.xpath(".//*[@id='cecTopActionsTitle']/span[contains(text(),'Welcome to Contract Express')]"));
	  }
	  
	  public boolean ishelpiconPresent() {
	    
	    	return isElementDisplayed(By.xpath("//a[@title='Help']/span[@class='icon-help']"));
	    }
	  
	  public boolean isuserDropdownPresent() {
	    	
	    	return isElementDisplayed(By.xpath("//a[@class='nav-username']"));
	    }
	  
	  public boolean isWelcomeCheckboxPresent()
	  {
		  return isElementDisplayed(By.xpath(".//*[@id='cecShowWelcome']"));
	  }
	  
	  public WebElement userNoticeAndPrivacyStatement() {
	        return waitForExpectedElement(By.linkText("User Notice and Privacy Statement"));
	    }
	  
	  public WebElement cEHeaderTitle(){
		  
		  return waitForExpectedElement(By.xpath(".//*[@id='cecTopActionsTitle']/span[2]"));
	    }
	  
	  public boolean isFilterSearchDisplayed()
	  {
		  return isElementDisplayed(By.xpath(".//*[@id='filter_search']"));
	  
	  }
}

