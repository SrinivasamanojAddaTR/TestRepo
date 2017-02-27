package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation;

import com.thomsonreuters.driver.exception.PageOperationException;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import java.util.List;


public class AnnotationPage extends DocumentNavigationPage {

    //TODO: Actions do not work in chromedriver see https://code.google.com/p/chromedriver/issues/detail?id=917 and https://code.google.com/p/chromedriver/issues/detail?id=841
    public void makeTextSelectionToOpenCreateAnnotationLightBox(By xpathOfStartSelectionElement, By xpathOfFinishSelectionElement) throws Exception {
        WebElement startElement = waitForExpectedElement(xpathOfStartSelectionElement);
        int selectionStartX = startElement.getLocation().getX();
        int selectionStartY = startElement.getLocation().getY();
        WebElement finishElement = waitForExpectedElement(xpathOfFinishSelectionElement);
        int selectionFinishX = finishElement.getLocation().getX();
        int selectionFinishY = finishElement.getLocation().getY();
        Actions act = new Actions(getDriver);
        LOG.info("Mooving mouse to a start selection element X: " + selectionStartX + " Y: " + selectionStartY);
        act.moveByOffset(selectionStartX, selectionStartY).perform();
        Thread.sleep(15000);
        LOG.info("Click And Hold left mouse button");
        act.clickAndHold().perform();
        Thread.sleep(15000);
        LOG.info("Mooving mouse to a finish selection element X: " + selectionFinishX + " Y: " + selectionFinishY);
        act.moveByOffset(selectionFinishX, selectionFinishY).perform();
        Thread.sleep(15000);
        LOG.info("Releasing Left mouse button");
        act.release().perform();
        waitForPageToLoadAndJQueryProcessing();
    }

    public boolean isAnnotationIconsPresent() {
    	return isElementDisplayed(By.xpath("//span[contains(@id,'co_noteHolder_')]"));
    }

    public boolean isAnnotationWidgetsPresent() {
    	return isElementDisplayed(By.xpath("//div[@class='co_noteContainer' and contains(@id,'co_note_')]"));
    }

    public boolean isCreateAnnotationsWidgetPresent() {
    	return isElementDisplayed(By.id("co_selectedTextMenuListItem_AddNote"));
    }

}