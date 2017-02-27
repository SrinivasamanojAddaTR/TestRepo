package com.thomsonreuters.pageobjects.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;



public class WhatsMarketDocumentPage extends CommonDocumentPage {

    /**
     * This method gets the displayed text as string value from the full text document.
     *
     * @return String
     */
    public String getFullText() {
        StringBuffer sb = new StringBuffer();
        try {
            WebElement title = getTitle(By.cssSelector("#co_docHeaderContainer div"));
            sb.append(title.getText());
            try {
                sb.append(findElement(By.id("kh_docBodyMain")).getText());
            } catch (Exception e) {
                LOG.info("context", e);
            }
        } catch (TimeoutException te) {
            LOG.info("context", te);
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
        if (!isSearchTermsPresentInParagraph(termsInSequence, By.cssSelector("#co_docHeaderContainer div"), searchTerms)) {
            if (!isSearchTermsPresentInParagraph(termsInSequence, By.cssSelector("#kh_docBodyMain"), searchTerms)) {
                return isSearchTermsPresentInParagraph(termsInSequence, By.className("co_paragraphText"), searchTerms);
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
    public boolean isSearchTermsPresentInParagraphWithInNumberOfWords(TermsInSequence termsInSequence, int withInTerms, String... searchTerms) {
        if (!isSearchTermsPresentInParagraphWithInNumberOfWords(termsInSequence, withInTerms, By.cssSelector("#co_docHeaderContainer div"), searchTerms)) {
            if (!isSearchTermsPresentInParagraphWithInNumberOfWords(termsInSequence, withInTerms, By.cssSelector("#kh_docBodyMain"), searchTerms)) {
                return isSearchTermsPresentInParagraphWithInNumberOfWords(termsInSequence, withInTerms, By.className("co_paragraphText"), searchTerms);
            }
        }
        return true;
    }

}