package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.exception.PageOperationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class KnowHowDocumentPage extends CommonDocumentPage {

    private static final By DOCUMENT_TITLE = By.cssSelector("#co_docContentHeader");

    /**
     * This method gets the displayed text as string value from the full text document.
     *
     * @return String
     */
    public String getFullText() {
        StringBuilder sb = new StringBuilder();
        try {
            WebElement title = getTitle(DOCUMENT_TITLE);
            sb.append(title.getText());
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
        }
        sb.append(getParagraphsFromFullText());
        return sb.toString();
    }

    /**
     * This method is to verify the search terms are displayed in given sequence in the single paragraph of full text document and returns the boolean accordingly.
     *
     * @param searchTerms
     * @return boolean
     */
    public boolean isSearchTermsPresentInParagraph(TermsInSequence termsInSequence, String... searchTerms) {
        if (!isSearchTermsPresentInParagraph(termsInSequence, DOCUMENT_TITLE, searchTerms)) {
            return isSearchTermsPresentInParagraph(termsInSequence, By.className("co_paragraphText"), searchTerms);
        }
        return true;
    }

    public boolean isSearchTermsPresentInSentence(TermsInSequence termsInSequence, String firstTerm, String secondTerm) {
        return isSearchTermsPresentInSentence(termsInSequence, By.id("co_document"), firstTerm, secondTerm);
    }

    /**
     * This method finds the given words are present or not in the given number of words sequence and returns the boolean value accordingly.
     *
     * @param termsInSequence
     * @param withInTerms
     * @param searchTerms
     * @return boolean
     */
    public boolean isSearchTermsPresentInParagraphWithInNumberOfWords(TermsInSequence termsInSequence, int withInTerms, String... searchTerms) {
        if (!isSearchTermsPresentInParagraphWithInNumberOfWords(termsInSequence, withInTerms, DOCUMENT_TITLE, searchTerms)) {
            return isSearchTermsPresentInParagraphWithInNumberOfWords(termsInSequence, withInTerms, By.className("co_paragraphText"), searchTerms);
        }
        return true;
    }

    /**
     * This object represents the product codes in the full text of the know how document under the heading Products with the desired code passed in as a variable
     */
    public WebElement knowHowProductCodes(String code) {
        return waitForExpectedElement(By.xpath("//div[@id='co_docContentMetaInfo']//div[@class='co_practiceAreaName']/ul/li[contains(text(),'" + code + "')]"));
    }

    /**
     * This object represents the product code section as a whole
     */
    public WebElement productCodeSection() {
        return waitForExpectedElement(By.xpath("//div[@id='co_docContentMetaInfo']//div[@class='co_practiceAreaName']/ul"));
    }

    /**
     * This object represents the message "Request a free trial" displayed on document display
     */
    public WebElement requestFreeTrialMessage() {
        return waitForExpectedElement(By.xpath("//div[@id='co_loggedOutContentRight']//a[contains(text(),'Request a free trial')]"));
    }

    /**
     * This object represents the topic section as a whole - this is found at the end of a document under the heading Also Found In and
     * is only accessible behind an FAC - at the moment this is the routing option pre release content
     */
    public WebElement topicInformation(String topicname) {
        return waitForExpectedElement(By.xpath("//div[@id='co_endOfDocument']//div[@class='co_topics']//a[contains(text(),'" + topicname + "')]"), 10);
    }

    /**
     * This object represents the heading "Topic" on document display, Related content section
     */
    public WebElement getRelatedContentTopicsHeader() {
        return waitForExpectedElement(By.xpath("//div[@id='co_relatedContent']//h3[contains(., 'Topics')]"));
    }

    /**
     * This object represents the topic links within the "Topics" section on document display, Related Content section
     */
    public WebElement topicPageLink(String topic) {
        return waitForExpectedElement(By.xpath("//li[@ng-show='hasTopics']//a[contains(., '" + topic + "')]"));
    }

    public WebElement topicPageLinkByURL(String topicURL) {
        return waitForExpectedElement(By.xpath("//div[@class='co_topics']//li//a[contains(@href,'" + topicURL + "')]"), 10);
    }

    public WebElement tableOfContentLeftHandTable() {
        return waitForExpectedElement(By.xpath("//div[@class='kh_toc-header']"), 15);
    }

    public boolean isSearchTermHighlighted(String highlightedTerm) {
        return isExists(By.xpath("//div[(@id='co_document')]//span[text()='" + highlightedTerm + "' and @class='co_searchTerm']"));
    }

    public boolean isHighlightedWordsPresent() {
        return isElementDisplayed(By.xpath("//span[@class='co_searchTerm']"));
    }

    public WebElement highlightedOptionCheckbox() {
        return waitForExpectedElement(By.xpath("//input[@id='co_showHideSearchTerm']"));
    }

    public WebElement textInSearchToggle() {
        return waitForExpectedElement(By.xpath("//span[@class='searchTermMessage']"));
    }

    public boolean isHighlightedOptionCheckboxSelected() {
        return highlightedOptionCheckbox().isSelected();
    }


    public By getDocumentTitle() {
        return DOCUMENT_TITLE;
    }

    public WebElement alsoFoundInHeader() {
        return waitForExpectedElement(By.xpath("//div[@id='co_endOfDocument']//div[contains(text(),'Also Found In')]"), 10);
    }
}