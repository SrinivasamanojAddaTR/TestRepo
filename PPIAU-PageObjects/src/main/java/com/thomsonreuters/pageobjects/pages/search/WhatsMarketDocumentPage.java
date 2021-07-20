package com.thomsonreuters.pageobjects.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;



public class WhatsMarketDocumentPage extends CommonDocumentPage {

    private static final By DOCUMENT_TITLE = By.cssSelector("#co_docHeaderContainer div");

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
            getMainBody(sb);
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        sb.append(getParagraphsFromFullText());
        return sb.toString();
    }

    private void getMainBody(StringBuilder sb){
        try {
            sb.append(findElement(By.id("kh_docBodyMain")).getText());
        } catch (Exception e) {
            LOG.info("context", e);
        }
    }

    /**
     * This method is to verify the search terms are displayed in given sequence in the single paragraph of full text document and returns the boolean accordingly.
     *
     * @param searchTerms
     * @return boolean
     */
    public boolean isSearchTermsPresentInParagraph(TermsInSequence termsInSequence, String... searchTerms) {
        if (!isSearchTermsPresentInParagraph(termsInSequence, DOCUMENT_TITLE, searchTerms) &&
                !isSearchTermsPresentInParagraph(termsInSequence, By.cssSelector("#kh_docBodyMain"), searchTerms)) {
            return isSearchTermsPresentInParagraph(termsInSequence, By.className("co_paragraphText"), searchTerms);
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
        if (!isSearchTermsPresentInParagraphWithInNumberOfWords(termsInSequence, withInTerms, DOCUMENT_TITLE, searchTerms) &&
                !isSearchTermsPresentInParagraphWithInNumberOfWords(termsInSequence, withInTerms, By.cssSelector("#kh_docBodyMain"), searchTerms)) {
            return isSearchTermsPresentInParagraphWithInNumberOfWords(termsInSequence, withInTerms, By.className("co_paragraphText"), searchTerms);
        }
        return true;
    }

}