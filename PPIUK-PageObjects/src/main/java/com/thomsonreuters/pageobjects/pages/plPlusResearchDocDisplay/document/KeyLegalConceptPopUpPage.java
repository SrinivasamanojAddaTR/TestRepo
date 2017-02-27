package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by U4400015 on 17/01/2017.
 */
public class KeyLegalConceptPopUpPage extends AbstractPage {

	public WebElement klcPopup() {
		return waitForElementVisible(By.xpath("//*[contains(@class,'co_keyLegalConcept_lightbox')]"));
	}

	public WebElement linkInKlcPopup(String linkname) {
		return klcPopup().findElement(By.linkText(linkname));
	}

    public WebElement klcHeader() {
        return waitForExpectedElement(By.xpath("//*[@class='co_keyLegalConceptPreHeader'][contains(text(),'Key Legal Concept')]"));
    }

    public Boolean isKlcHeaderDisplayed() {
        return isElementDisplayed(By.xpath("//*[@class='co_keyLegalConceptPreHeader'][contains(text(),'Key Legal Concept')]"));
    }


    public Boolean isKlcTitleDisplayed(String title) {
        return isElementDisplayed(By.xpath("//*[@id='co_document_0']//h2[contains(text(),'" + title + "')]"));
    }


    public Boolean isWrittenAndMaintainedByTextDisplayed() {
        return isElementDisplayed(By.xpath("//*[@id='co_document_0']//em[contains(text(),'Written and maintained by')]"));
    }

    public Boolean isAuthorNameDisplayed(String name) {
        return isElementDisplayed(By.xpath("//*[@id='co_document_0']//a[contains(text(),'" + name + "')]"));
    }

    public WebElement closePopUpButton() {
        return waitForExpectedElement(By.xpath("//*[@id='co_keyLegalConcept_lightbox_closeButton']"));
    }

    public WebElement textWithinPopUp(String text) {
	    return waitForExpectedElement(By.xpath(".//*[@id='co_docContentBody']//li[contains(text(),'" + text + "')]"));
    }



}
