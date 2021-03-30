package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.exception.PageOperationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by S Armytage on 18/06/2015.
 */


public class JournalsDocumentPage extends CommonDocumentPage{

    /**
     * This method gets the displayed text as string value from the full text document.
     *
     * @return String
     */
    public String getFullText() {
        StringBuffer sb = new StringBuffer();
        try {
            WebElement title = getTitle(By.cssSelector("#co_docContentHeader"));
            sb.append(title.getText());
        } catch (PageOperationException poe) {
        }
        sb.append(getParagraphsFromFullText());
        return sb.toString();
    }

    /**
     * Object representing the heading for a journal abstract
     */

    public WebElement journalAbstractArticleHeader() {

        return waitForExpectedElement(By.xpath("//div[@id='co_document_0']/div[1][contains(text(),'Article - Journal')]"));
    }



}
