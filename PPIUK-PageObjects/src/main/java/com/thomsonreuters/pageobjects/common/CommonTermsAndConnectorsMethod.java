package com.thomsonreuters.pageobjects.common;

import com.thomsonreuters.pageobjects.pages.search.CommonDocumentPage;
import org.openqa.selenium.By;

/**
 * Created by u4400015 on 12/10/2016.
 */
public class CommonTermsAndConnectorsMethod extends CommonDocumentPage {

    /**
     * This method is to verify the search terms are displayed in given sequence in the single paragraph of full text document and returns the boolean accordingly.
     *
     * @param searchTerms
     * @return boolean
     */
    public boolean isSearchTermsPresentInParagraph(TermsInSequence termsInSequence, String... searchTerms) {
        if (!isSearchTermsPresentInParagraph(termsInSequence, By.cssSelector("#co_docContentHeader"), searchTerms)) {
            return isSearchTermsPresentInParagraph(termsInSequence, By.className("co_paragraph"), searchTerms);
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
        if (!isSearchTermsPresentInParagraphWithInNumberOfWords(termsInSequence, withInTerms, By.cssSelector("#co_docContentHeader"), searchTerms)) {
            return isSearchTermsPresentInParagraphWithInNumberOfWords(termsInSequence, withInTerms, By.className("co_paragraph"), searchTerms);
        }
        return true;
    }
}
