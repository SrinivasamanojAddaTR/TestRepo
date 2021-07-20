package com.thomsonreuters.pageobjects.pages.site_structure;

import com.thomsonreuters.driver.framework.AbstractPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BreadCrumbPage extends AbstractPage {
	
    private static final By BREAD_CRUMBS_BY = By.className("co_breadcrumbs");

    public WebElement breadCrumbCurrent(){
        return waitForExpectedElement(By.xpath("//div[@class='co_breadcrumbs']"));
    }

    public WebElement breadCrumbLink(String crumbLink) {
        return waitForExpectedElement(By.xpath("//div[@class='co_breadcrumbs']//a[@title='" + crumbLink + "']"));
    }

    public WebElement breadcrumbHomeLink(){
        return waitForExpectedElement(By.xpath("//div[@class='co_breadcrumbs']//a[@title='Home']"));
    }
    
    public WebElement breadCrumbDropdownArrow(){
        return waitForExpectedElement(By.xpath("//button[@title='View recent documents']"));
    }
   
    public List<WebElement> breadCrumbDropdownList(){
        return waitForExpectedElements(By.xpath("//*[@class='co_dropDownMenu-selector align-right']//*[@class='co_dropDownMenuList']/li"));
    }
    
    public WebElement breadCrumbDropdownDocument(String documentName){
    	return waitForExpectedElement(By.xpath("//*[@class='co_dropDownMenu-selector align-right']//*[@class='co_dropDownMenuList']//*[contains(text(),'" + documentName + "')]"));
    }

    public boolean isBreadCrumbDropdownListPresent(){
        return isExists(By.className("co_dropDownMenuContent"));
    }

    public boolean isBreadCrumbDropdownArrowPresent(){
        return isExists(By.xpath("//button[@title='View recent documents']"));
    }

    public boolean isBreadCrumbPresent(){
        return isExists(By.className("co_breadcrumbs"));
    }
    
    public boolean isViewAllLinkPresent(){
    	return isExists(By.xpath("//div[@class='co_genericBoxFooter']/a"));
    }

    
    public WebElement viewAllLinkPresent(){
    	return waitForExpectedElement(By.xpath("//div[@class='co_genericBoxFooter']/a"));
    }
    
    public WebElement waitForBreadcrumbToBeDisplayed() { 
    	return waitForExpectedElement(BREAD_CRUMBS_BY);
	  		   
	  		}
    public WebElement documentName()
    {
    	return waitForExpectedElement(By.xpath("//span[@class='co_constrainedCrumbItem']"));	
    }
    
}
