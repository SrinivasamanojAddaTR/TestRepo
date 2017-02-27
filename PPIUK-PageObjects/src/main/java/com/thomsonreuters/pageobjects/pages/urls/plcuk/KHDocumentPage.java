package com.thomsonreuters.pageobjects.pages.urls.plcuk;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.pages.urls.DocumentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;


public class KHDocumentPage extends AbstractPage implements DocumentPage {


    public boolean isDocumentBlockPresent() {
       //waitForPageToLoadAndJQueryProcessing();
    	 waitForPageToLoad();
        if (!findElements(By.id("coid_website_documentWidgetDiv")).isEmpty()) {
            return true;
        } else if (!findElements(By.className("co_search_result_heading_content")).isEmpty()) {
            return true;
        } else if (!findElements(By.id("co_linkOutBox")).isEmpty()) {
            return true;
        } else if (!findElements(By.cssSelector(".co_title.noTOC")).isEmpty()) {
            return true;
        }
          else if(!findElements(By.cssSelector(".co_scrollWrapper.co_categoryBoxTabContents")).isEmpty()){
            return true;
        }
        return false;
    }


/*
    public String getDocumentTitle1() {
        List<WebElement> list = findElements(By.xpath("//div[@class='co_title']/div/div"));
        StringBuffer result = new StringBuffer();
        if (!list.isEmpty()) {
            for (WebElement element : list) {
                result.append(element.getText() + " ");
            }
        } else {
            List<WebElement> list2 = findElements(By.xpath("//div[@class='co_title noTOC']"));
            if (!list2.isEmpty()) {
                result.append(list2.get(0).getText());
            } else {
                List<WebElement> list4 =findElements(By.cssSelector("#co_docHeaderContainer h1[class='co_title noTOC']"));
                if(!list4.isEmpty()){
                    result.append(list4.get(0).getText());
                } else {
                    List<WebElement> list3 = findElements(By.xpath("//div[@class='co_headtext co_center']"));
                    if (!list3.isEmpty()) {
                        result.append(list3.get(0).getText());
                    }
                }

            }
        }
        return result.toString().toLowerCase();
    }
*/

    public String getDocumentTitle() {

        List<WebElement> list;
        StringBuffer result = new StringBuffer();

        if((list = findElements(By.xpath("//div[@class='co_title']/div/div"))).size() !=0){
            for (WebElement element : list) {
                result.append(element.getText() + " ");
            }

        }// Assign value to list and then check if it is empty
        // Try to find any one of 3 WebElement types
        else if(   !(  list = findElements(By.xpath("//div[@class='co_title noTOC']"))  ).isEmpty() ||

                    !(   list = findElements(By.cssSelector("#co_docHeaderContainer>h1[class~='co_title']")) ).isEmpty() ||
                    !(   list = findElements(By.xpath("//div[@class='co_headtext co_center']")) ).isEmpty()
                ){
            result.append(list.get(0).getText());
        }else {
            result.append("");
        }
        return result.toString().toLowerCase();


    }


    @Override
    public String getWebSiteName() {
        return "New PLCUK Site";
    }

    public boolean isContainCanonicalAttribute() {
        List<WebElement> webElements = findElements(By.xpath("//head/link[@rel='canonical']"));
        return !webElements.isEmpty();
    }

    public boolean isContainLinkTo(String name) {
        List<WebElement> elements = findElements(By.xpath("//div[@id='coid_website_documentWidgetDiv']//a[text()='"+name+"']"));
        return !elements.isEmpty();
    }

    public boolean isContainEmailLinkTo(String email) {
        List<WebElement> elements = findElements(By.xpath("//a[text()='"+email+"']"));
        for (WebElement element: elements){
            String href = element.getAttribute("href");
            if(href != null && href.startsWith("mailto")){
                return true;
            }
        }
        return false;
    }

    public boolean isDocumentContainImages() {
        return !getAllImages().isEmpty();
    }

    public List<WebElement> getAllImages(){
        return findElements(By.xpath("//div[@id='coid_website_documentWidgetDiv']//a[@class='co_imageLink']"));
    }

    public boolean isErrorPresent() {
        return isElementDisplayed(By.className("co_systemErrorBox"));
    }

    /**
     * Get invisible element with URL to document attachment (e.g., Lawtel Transcript)
     * WARNING! Element is INVISIBLE
     *
     * @return Document with a link to attachment
     */
    public WebElement getDocumentAttachment() {
        return waitForElementPresent(By.cssSelector(".kh_standardDocumentAttachment a"));
    }

    /**
     * Wait until document page will be loaded
     * WARNING! DO NOT USE waitForPageToLoad() before invocation of this method!
     */
    public void waitDocumentPageToLoad() {
        // Wait full page loading
        waitForPageToLoad();

        // Wait until loading picture disappears
        waitForElementAbsent(By.className(".co_loading"));
    }
}
