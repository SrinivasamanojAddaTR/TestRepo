package com.thomsonreuters.pageobjects.common;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.driver.framework.WebDriverDiscovery;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PageActions {
	
	private AbstractPage abstractPage;

    private WebDriverDiscovery webDriverDiscovery;

    public PageActions() {
        webDriverDiscovery =  new CommonMethods().getWebDriverDiscovery();
    }

    public void dragAndDrop(WebElement dragElement, WebElement dropElement) {
        new Actions(webDriverDiscovery.getRemoteWebDriver()).dragAndDrop(dragElement, dropElement).build().perform();
    }

    public void rightClick(WebElement element) {
        new Actions(webDriverDiscovery.getRemoteWebDriver()).contextClick(element).build().perform();
    }

    public void keyPress(Keys key) {
        new Actions(webDriverDiscovery.getRemoteWebDriver()).sendKeys(key).build().perform();
    }

    public void doubleClick(WebElement element) {
        new Actions(webDriverDiscovery.getRemoteWebDriver()).doubleClick(element).build().perform();
    }

    public void mouseOver(WebElement element) {    	
        new Actions(webDriverDiscovery.getRemoteWebDriver()).moveToElement(element).build().perform();
    }
	
	public void mouseOverAndClickElement(WebElement element) {
		new Actions(webDriverDiscovery.getRemoteWebDriver()).moveToElement(element).build().perform();		
		JavascriptExecutor js = (JavascriptExecutor)webDriverDiscovery.getRemoteWebDriver();
		js.executeScript("arguments[0].click();", element); 
	}

    public void openInNewWindow(WebElement element){
        new Actions(webDriverDiscovery.getRemoteWebDriver()).keyDown(Keys.SHIFT).click(element).keyUp(Keys.SHIFT).build().perform();
    }

    public void dragAndMoveElement(WebElement firstElement, WebElement secondElement){
        Actions builder = new Actions(webDriverDiscovery.getRemoteWebDriver());
        builder.clickAndHold(firstElement)
                .moveToElement(secondElement)
                .perform();
    }
}
