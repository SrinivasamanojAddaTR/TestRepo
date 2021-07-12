package com.thomsonreuters.pageobjects.pages.currentAwareness;

import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.DocumentDisplayAbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Olga_Nadenenko on 9/15/2016.
 * Entities of this class represents page objects for Current Awareness document pages.
 */
public class CADocumentPage extends DocumentDisplayAbstractPage{

    private static final By CA_DOCUMENT_TYPE = By.xpath("//div[@id='co_document']//div[contains(@class, 'co_title')]/preceding-sibling::div[1]/div");

    public WebElement caDocumentType() {return waitForExpectedElement(CA_DOCUMENT_TYPE); }

    public WebElement documentField(String subsection) {
        return waitForExpectedElement(By.xpath("//div[@id='co_document']//div[contains(., '" + subsection + "')]"));
    }

    public WebElement documentFieldName(String subsection) {
        return waitForExpectedElement(By.xpath("//div[@id='co_document']//strong[contains(text(), '" + subsection + "')]"));
    }

    public WebElement linkInCADocument(String linkText) {
        return waitForExpectedElement(By.linkText(linkText));
    }
}
