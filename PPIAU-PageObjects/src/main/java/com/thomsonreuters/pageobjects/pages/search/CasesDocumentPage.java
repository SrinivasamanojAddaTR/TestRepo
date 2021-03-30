package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by S Armytage on 02/06/2015. This page is the full text case document - either the analysis or the full text judgment
 */


public class CasesDocumentPage extends AbstractPage {

    public CasesDocumentPage() {
    }


    /**
     * Object representing out of plan document pop up
     */

    public WebElement viewDocumentLink() {

        return waitForExpectedElement(By.id("co_WarnViewOkButton"));
    }

    /**
     * Object checking terms with hit mark up
     */

    public List termNavigationHitMarkupCheckTermsAsList() {
        // Get a list of any terms marked up
        List<WebElement> eList =
                findElements(By.xpath("//span[contains(@class,'searchTerm')]"));
        // Prepare a list of strings to store the text returned
        List<String> getTextStringList = new ArrayList<String>();

        for (WebElement element : eList) {
            getTextStringList.add(element.getText());
        }
        return getTextStringList;
    }

    /**
     * Object representing case analysis full text
     */

    public WebElement caseAnalysisFullText() {

        return waitForExpectedElement(By.xpath("//div[@id='co_document']"));
    }


    /**
     * Object representing full text judgment - this can be a law report or a transcript
     */

    public WebElement fullTextJudgment() {

        return waitForExpectedElement(By.xpath("//div[@id='coid_website_documentWidgetDiv']"));
    }

    /**
     * Object representing "Return to list" link taking user back to search results
     */

    public WebElement returnToList() {

        return waitForExpectedElement(By.xpath("//*[@id='coid_backToResults']//span[contains(text(),'Return to list')]"));
    }


}
