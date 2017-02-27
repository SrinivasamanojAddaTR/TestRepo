package com.thomsonreuters.pageobjects.pages.siteStructure;

import com.thomsonreuters.driver.framework.AbstractPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BreadCrumbPage extends AbstractPage {

    public WebElement breadCrumbCurrent(){
        return waitForExpectedElement(By.xpath("//div[@class='co_breadcrumbs']"));
    }

    public WebElement breadCrumbLink(String crumbLink) {
        return waitForExpectedElement(By.xpath("//div[@class='co_breadcrumbs']//a[@title='" + crumbLink + "']"));
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
        return isElementPresent(By.className("co_dropDownMenuContent"));
    }

    public boolean isBreadCrumbDropdownArrowPresent(){
        return isElementPresent(By.xpath("//button[@title='View recent documents']"));
    }

    public boolean isBreadCrumbPresent(){
        return isElementPresent(By.className("co_breadcrumbs"));
    }
}
