package com.thomsonreuters.pageobjects.common;

import com.thomsonreuters.pageobjects.pages.generic.PPIGenericDocDisplay;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.TableOfContentPage;
import com.thomsonreuters.pageobjects.pages.search.CommonDocumentPage;
import com.thomsonreuters.pageobjects.pages.urls.plcuk.KHDocumentPage;

import java.util.List;

/**
 * Created by Pavel_Ardenka on 09/12/2016.
 * Object which represents search terms with Boolean connectors
 */
public class ConnectorTerm {

    // Object fields
    private String firstTerm;
    private String secondTerm;
    private int wordsBetweenTerms;
    private Type type;

    // Page objects and helper classes
    private CommonTermsAndConnectorsMethod connectorsMethod = new CommonTermsAndConnectorsMethod();
    private CommonMethods commonMethods = new CommonMethods();
    private TableOfContentPage tocPage = new TableOfContentPage();
    private PPIGenericDocDisplay ppiGenericDocDisplay = new PPIGenericDocDisplay();
    private KHDocumentPage khDocumentPage = new KHDocumentPage();

    /**
     * Connector type
     */
    public enum Type {
        /**
         * Search first term only in the full document text
         */
        SINGLE_FIRST_TERM,

        /**
         * Search first term and second term within the same paragraph
         */
        SINGLE_PARA,

        /**
         * Search both terms, where the first precedes the second in the same paragraph
         */
        FIRST_PRECEDES_PARA,

        /**
         * Search both terms, where the first precedes the second and the number of words lower
         * than {@link #wordsBetweenTerms}
         */
        WORDS_BETWEEN_TERMS_FIRST_PRECEDES_SECOND,

        /**
         * Search both terms, where the number of words lower than {@link #wordsBetweenTerms}
         */
        WORDS_BETWEEN_TERMS
    }

    public ConnectorTerm setFirstTerm(String firstTerm) {
        this.firstTerm = firstTerm;
        return this;
    }

    public ConnectorTerm setSecondTerm(String secondTerm) {
        this.secondTerm = secondTerm;
        return this;
    }

    public ConnectorTerm setWordsBetweenTerms(int wordsBetweenTerms) {
        this.wordsBetweenTerms = wordsBetweenTerms;
        return this;
    }

    public ConnectorTerm setType(Type type) {
        this.type = type;
        return this;
    }

    /**
     * Verify that opened search result contains searched term(s).
     * If the document des not contain the term(s), then sub-documents will be checked.
     * For example, if for Case search result Case Analysis was opened, which does not contains expected terms,
     * then Law Reports, Judgment and other sub-document will be checked as well.
     *
     * @return True - if a document contains searched term(s), false - otherwise
     */
    public boolean areTermsFoundInDocAndSubDocs() {
        if (!areTermsFound()) {
            List<String> documentTocLinks = commonMethods.getTextsFromWebElements(tocPage.getVisibleTocLinks());
            for (String documentTocLink : documentTocLinks) {
                tocPage.getAnyLevelSectionLink(documentTocLink).click();
                khDocumentPage.waitDocumentPageToLoad();
                if (areTermsFound()) {
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * Verify that current opened document contains searched term(s)
     *
     * @return True - if searched term(s) are exists in the document, false - otherwise.
     */
    public boolean areTermsFound() {
        switch (type) {
            case SINGLE_PARA:
                return connectorsMethod.isSearchTermsPresentInParagraph(CommonDocumentPage.TermsInSequence.NO,
                        firstTerm, secondTerm);
            case FIRST_PRECEDES_PARA:
                return connectorsMethod.isSearchTermsPresentInParagraph(CommonDocumentPage.TermsInSequence.YES,
                        firstTerm, secondTerm);
            case WORDS_BETWEEN_TERMS:
                return connectorsMethod.isSearchTermsPresentInParagraphWithInNumberOfWords(
                        CommonDocumentPage.TermsInSequence.NO, wordsBetweenTerms, firstTerm, secondTerm);
            case WORDS_BETWEEN_TERMS_FIRST_PRECEDES_SECOND:
                return connectorsMethod.isSearchTermsPresentInParagraphWithInNumberOfWords(
                        CommonDocumentPage.TermsInSequence.YES, wordsBetweenTerms, firstTerm, secondTerm);
            case SINGLE_FIRST_TERM:
                return ppiGenericDocDisplay.mainDocumentFullText().toLowerCase().contains(firstTerm.toLowerCase());
            default:
                throw new UnsupportedOperationException("Terms verification is not implemented for type: " + type);
        }
    }

}
