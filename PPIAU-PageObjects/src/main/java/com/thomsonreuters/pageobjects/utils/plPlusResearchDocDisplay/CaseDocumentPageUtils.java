package com.thomsonreuters.pageobjects.utils.plPlusResearchDocDisplay;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.PageActions;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.CaseDocumentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;

public class CaseDocumentPageUtils extends AbstractPage {

    private CaseDocumentPage caseDocumentPage = new CaseDocumentPage();
    private PageActions pageActions = new PageActions();

    protected static final Logger LOG = org.slf4j.LoggerFactory.getLogger(CaseDocumentPageUtils.class);


    public void selectOnShowAndHideLink() {
        caseDocumentPage.executeScript("document.getElementById('co_ExpandCollapseLegislationAnnotationSection').scrollIntoView(true); window.scrollBy(0,-300);");
        pageActions.mouseOver(caseDocumentPage.showAndHideLink());
        caseDocumentPage.showAndHideLink().click();
    }

    public boolean isAnnotationSectionIsDisplayed() {
        caseDocumentPage.waitForPageToLoad();
        try {
            return caseDocumentPage.annotationsSection().isDisplayed();
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
            return false;
        }
    }


    public boolean isTheLinkPresent(String text) {
        try {
            findElement(By.linkText(text));
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

}
