package com.thomsonreuters.pageobjects.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PageActions {

    private WebDriver getDriver;

    public PageActions() {
        getDriver = new CommonMethods().getDriver();

    }

    private Actions getActions() {
        return new Actions(getDriver);
    }

    public void dragAndDrop(WebElement dragElement, WebElement dropElement) {
        getActions().dragAndDrop(dragElement, dropElement).build().perform();
    }

    public void rightClick(WebElement element) {
        getActions().contextClick(element).build().perform();
    }

    public void keyPress(Keys key) {
        getActions().sendKeys(key).build().perform();
    }

    public void doubleClick(WebElement element) {
        getActions().doubleClick(element).build().perform();
    }

    public void mouseOver(WebElement element) {
        getActions().moveToElement(element).build().perform();
    }

    public void mouseOverAndClickElement(WebElement element) {
        getActions().moveToElement(element).build().perform();
        clickElementUsingJS(element);
    }

    public void openInNewWindow(WebElement element) {
        getActions().keyDown(Keys.SHIFT).click(element).keyUp(Keys.SHIFT).build().perform();
    }

    public void dragAndMoveElement(WebElement firstElement, WebElement secondElement) {
        getActions().clickAndHold(firstElement)
                .moveToElement(secondElement)
                .perform();
    }

    public void clickElementUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver;
        js.executeScript("arguments[0].click();", element);
    }
}
