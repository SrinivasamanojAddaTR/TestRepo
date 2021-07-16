package com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.DocumentDisplayAbstractPage;
import com.thomsonreuters.utils.TimeoutUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;


public class DocumentRightPanelPage extends DocumentDisplayAbstractPage {

    private static final String META_DATA = "co_docContentMetaInfo";
    private static final By JURISDICTIONS_VIEW_ALL_LINK = By.id("co_docContentMetaInfoJurisdictionsAllButton");

    @Override
    public WebElement documentStatus() {
        try {
            return findElement(By.cssSelector("#co_docContentMetaInfo >span"));
        } catch (NoSuchElementException e) {
            return findElement(By.className("askDocPublishDate"));
        }
    }

    public boolean isDocumentStatusPresent(){
        return isExists(By.cssSelector("#co_docContentMetaInfo >span"));
    }

    public WebElement relatedOrHistoryLink(String linkText) {
        return findElement(By.id(META_DATA)).findElement(By.linkText(linkText));
    }

    public boolean isRelatedContentLinkPresent(){
       return isExists(By.cssSelector("#co_docContentResourceRelatedContentQuickLink"));
    }

    public WebElement viewResourceHistoryLink() {
        return findElement(By.id(META_DATA)).findElement(By.linkText("View Resource History"));
    }

    private List<WebElement> listOfJurisdictions() {
        return findElements(By.cssSelector("#co_docContentMetaInfoJurisdictions ul li"));
    }

    public List<String> getVisibleJurisdictions() {
        List<String> visibleJurisdictions = new ArrayList<>();
        for (WebElement element : listOfJurisdictions()) {
            if (element.isDisplayed() && element.getAttribute("id").equals("")) {
                visibleJurisdictions.add(element.getText().trim());
            }
        }
        return visibleJurisdictions;
    }

    public WebElement jurisdictionViewAllLink() {
        return findElement(JURISDICTIONS_VIEW_ALL_LINK);
    }

    public WebElement relatedContentLink() {
        return waitForExpectedElement(By.id(META_DATA)).findElement(By.linkText("Related Content"));
    }

    public boolean isViewAllLinkDisplayed() {
        return isExists(JURISDICTIONS_VIEW_ALL_LINK);
    }

    public boolean isSectionDisplayed(String navigationLink) {
        String id = getNavigationLinkID(navigationLink);
        double resourceHeightPosition = 0;
        double screenHeight = Double.parseDouble((executeScript("return window.innerHeight;").toString()));
        for (int i = 0; i < 20; i++) {
            try {
                waitForPageToLoadAndJQueryProcessing();
                waitForPageToLoad();
                resourceHeightPosition = (double) executeScript("return document.getElementById('" + id + "').getBoundingClientRect().top;");
            } catch (Exception e) {
                LOG.info(e.getMessage());
            }
            if (resourceHeightPosition > 0 && resourceHeightPosition < screenHeight / 2) {
                return true;
            } else {
                LOG.info("retrying the script execution");
            }
        }
        return false;
    }

    private String getNavigationLinkID(String navigationLink) {
        String id = null;
        switch(navigationLink){
            case "Related Content": id = "co_relatedContent"; break;
            case "Resource History" : id = "co_EodHistory"; break;
            default: throw new PageOperationException("Navigation link name is not exist in the switch statement.");
        }
        return id;
    }

    public Object retryExecuteScript(String script){
        Object obj = null;
        for(int i=0;i< 3; i++){
                try{
                   TimeoutUtils.sleepInSeconds(3);
                    obj = executeScript(script);
                }catch(Exception e)
                {
                    LOG.info("context", e);
                }
            if(obj == null){
                LOG.info("retrying the script execution {}", script);
                obj = retryExecuteScript(script);
            }else{break;}
        }
        return obj;
    }

}
