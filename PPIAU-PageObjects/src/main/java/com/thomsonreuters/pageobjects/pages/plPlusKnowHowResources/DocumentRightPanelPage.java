package com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.DocumentDisplayAbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;


public class DocumentRightPanelPage extends DocumentDisplayAbstractPage {

    public WebElement documentStatus() {
        try {
            return findElement(By.cssSelector("#co_docContentMetaInfo >span"));
        } catch (NoSuchElementException e) {
            return findElement(By.className("askDocPublishDate"));
        }
        // return findElement(By.className("askDocPublishDate"));
    }

    public boolean isDocumentStatusPresent(){
        return isExists(By.cssSelector("#co_docContentMetaInfo >span"));
    }

    public WebElement relatedOrHistoryLink(String linkText) {
        return findElement(By.id("co_docContentMetaInfo")).findElement(By.linkText(linkText));
    }

    public boolean isRelatedContentLinkPresent(){
       return isExists(By.cssSelector("#co_docContentResourceRelatedContentQuickLink"));
    }

    public WebElement viewResourceHistoryLink() {
        return findElement(By.id("co_docContentMetaInfo")).findElement(By.linkText("View Resource History"));
    }

    //TODO: Change methodname
    private List<WebElement> jurisdictions() {
        return findElements(By.cssSelector("#co_docContentMetaInfoJurisdictions ul li"));
    }

    public List<String> getVisibleJurisdictions() {
        List<String> visibleJurisdictions = new ArrayList<String>();
        for (WebElement element : jurisdictions()) {
            if (element.isDisplayed() && element.getAttribute("id").equals("")) {
                visibleJurisdictions.add(element.getText().trim());
            }
        }
        return visibleJurisdictions;
    }

    public WebElement jurisdictionViewAllLink() {
        return findElement(By.id("co_docContentMetaInfoJurisdictionsAllButton"));
    }

   // private List<WebElement> jurisdictionSection() {
   //     return findElements(By.id("co_docContentMetaInfoJurisdictions"));
   // }
    public WebElement relatedContentLink() {
        return waitForExpectedElement(By.id("co_docContentMetaInfo")).findElement(By.linkText("Related Content"));
    }

    public boolean isViewAllLinkDisplayed() {
        return findElements(By.id("co_docContentMetaInfoJurisdictionsAllButton")).size() > 0;
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
                    Thread.sleep(3000);
                    obj = executeScript(script);
                }catch(Exception e){}
            if(obj == null){
                LOG.info("retrying the script execution" + script);
                obj = retryExecuteScript(script);
            }else{break;}
        }
        return obj;
    }

}
