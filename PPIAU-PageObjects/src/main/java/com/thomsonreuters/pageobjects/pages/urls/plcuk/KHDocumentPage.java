package com.thomsonreuters.pageobjects.pages.urls.plcuk;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.pages.urls.DocumentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;


public class KHDocumentPage extends AbstractPage implements DocumentPage {
    private static final By DOCUMENT_WIDGET = By.id("coid_website_documentWidgetDiv");
    private static final By SEARCH_RESULT_HEADING_CONTENT = By.className("co_search_result_heading_content");
    private static final By LINK_OUT = By.id("co_linkOutBox");

    public boolean isDocumentBlockPresent() {

        waitForPageToLoad();
        List<By> allElements = Arrays.asList(DOCUMENT_WIDGET, SEARCH_RESULT_HEADING_CONTENT, LINK_OUT,
                By.cssSelector(".co_title.noTOC"), By.cssSelector(".co_scrollWrapper.co_categoryBoxTabContents"));
        for (int i = 0; i < allElements.size(); i++) {
            if (isListNotEmpty(allElements.get(i)))
                return true;
        }

        return false;
    }


    public String getDocumentTitle() {

        List<WebElement> list = findElements(By.xpath("//div[@class='co_title']/div/div"));
        StringBuilder result = new StringBuilder();

        if (isListNotEmpty(list)) {
            for (WebElement element : list) {
                result.append(element.getText() + " ");
            }

        }
        List<By> otherElements = Arrays.asList(By.xpath("//div[@class='co_title noTOC']"), By.cssSelector("#co_docHeaderContainer>h1[class~='co_title']"),
                By.xpath("//div[@class='co_headtext co_center']"));
        for (int i = 0; i < otherElements.size(); i++) {
            if (isListNotEmpty(otherElements.get(i))) {
                list = findElements(otherElements.get(i));
                result.append(list.get(0).getText());
                break;
            }
        }

        return result.toString().toLowerCase();


    }

    private boolean isListNotEmpty(By element) {
        return !findElements(element).isEmpty();
    }

    private boolean isListNotEmpty(List<WebElement> elements) {
        return !elements.isEmpty();
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
        List<WebElement> elements = findElements(By.xpath("//div[@id='coid_website_documentWidgetDiv']//a[text()='" + name + "']"));
        return !elements.isEmpty();
    }

    public boolean isContainEmailLinkTo(String email) {
        List<WebElement> elements = findElements(By.xpath("//a[text()='" + email + "']"));
        for (WebElement element : elements) {
            String href = element.getAttribute("href");
            if (href != null && href.startsWith("mailto")) {
                return true;
            }
        }
        return false;
    }

    public boolean isDocumentContainImages() {
        return !getAllImages().isEmpty();
    }

    public List<WebElement> getAllImages() {
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
        waitForPageToLoad();
        waitForElementToDissappear(By.className(".co_loading"));
    }
}
