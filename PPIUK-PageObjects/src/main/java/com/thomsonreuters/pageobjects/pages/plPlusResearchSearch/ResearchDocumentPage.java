package com.thomsonreuters.pageobjects.pages.plPlusResearchSearch;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.pages.search.CommonDocumentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


/**
 * Created by Chiran Bairaagoni on 06/07/2015.
 */

public class ResearchDocumentPage extends CommonDocumentPage {
    /**
     * This method gets the displayed text as string value from the full text document.
     *
     * @return String
     */
    public String getFullText() {
        StringBuffer sb = new StringBuffer();
        try {
            WebElement title = getTitle(By.cssSelector("#co_document #co_document_0"));
            sb.append(title.getText());
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
        }
        return sb.toString();
    }

    /**
     * This method is to verify the search terms are displayed in given sequence in the single paragraph of full text document and returns the boolean accordingly.
     *
     * @param searchTerms
     * @return boolean
     */
    public boolean isSearchTermsPresentInParagraph(CommonDocumentPage.TermsInSequence termsInSequence, String... searchTerms) {
        if(!verifySearchTermInParagraph(termsInSequence,By.cssSelector("#co_document_0>table>tbody>tr>td"), searchTerms)){
            if(!verifySearchTermInParagraph(termsInSequence,By.cssSelector(".co_paragraph"), searchTerms)){
                if(!verifySearchTermInParagraph(termsInSequence,By.cssSelector(".co_bullList>li"), searchTerms)){
                    return verifySearchTermInParagraph(termsInSequence,By.cssSelector(".co_indentLeft1"),searchTerms);
                }
            }
        }

        return true;
    }

    /**
     * This method finds the given words are present or not in the given number of words sequence and returns the boolean value accordingly.
     *
     * @param termsInSequence
     * @param withInTerms
     * @param searchTerms
     * @return boolean
     */
    public boolean isSearchTermsPresentInParagraphWithInNumberOfWords(CommonDocumentPage.TermsInSequence termsInSequence, int withInTerms, String... searchTerms) {
        if(!verifySearchTermInSequence(termsInSequence,withInTerms,By.cssSelector("#co_document_0>table>tbody>tr>td"), searchTerms)){
            if(!verifySearchTermInSequence(termsInSequence,withInTerms,By.cssSelector(".co_paragraph"), searchTerms)){
                if(!verifySearchTermInSequence(termsInSequence,withInTerms,By.cssSelector(".co_bullList>li"), searchTerms)){
                    return verifySearchTermInSequence(termsInSequence,withInTerms,By.cssSelector(".co_indentLeft1"),searchTerms);
                }
            }
        }

        return true;
    }

    private boolean verifySearchTermInParagraph(CommonDocumentPage.TermsInSequence termsInSequence, By by, String... searchTerms){
        return (isAvailable(by) && isSearchTermsPresentInParagraph(termsInSequence, by, searchTerms));
    }

    private boolean verifySearchTermInSequence(CommonDocumentPage.TermsInSequence termsInSequence, int withInTerms, By by, String... searchTerms){
        return (isAvailable(by) && isSearchTermsPresentInParagraphWithInNumberOfWords(termsInSequence, withInTerms, by, searchTerms));
    }

    private boolean isAvailable(By by){
        try{
            findElement(by);
        }catch(NoSuchElementException e){return false;}
        return true;
    }
}
