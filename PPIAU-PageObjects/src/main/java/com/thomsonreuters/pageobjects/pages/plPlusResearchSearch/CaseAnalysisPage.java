package com.thomsonreuters.pageobjects.pages.plPlusResearchSearch;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class CaseAnalysisPage extends AbstractPage {

    public CaseAnalysisPage() {
    }

    public WebElement subjectText() {
        return waitForExpectedElement(By.xpath("//div[@id='co_document']//td/../strong[contains(text(),'Subject:')]"));
    }

}