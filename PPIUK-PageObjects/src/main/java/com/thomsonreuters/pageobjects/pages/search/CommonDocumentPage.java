package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CommonDocumentPage extends AbstractPage {

    public enum TermsInSequence {
        YES,
        NO;
    }

    protected WebElement getTitle(By by) {
        return waitForExpectedElement(by,30);
    }

    /**
     * This method gets the displayed paragraphs text as String value from the full test document.
     *
     * @return String
     */
    public String getParagraphsFromFullText() {
        StringBuffer sb = new StringBuffer();
        try {
            for (WebElement element : waitForExpectedElements(By.className("co_paragraphText"), 30)) {
                sb.append(element.getText());
            }
        } catch (StaleElementReferenceException se) {
            LOG.info("context", se);
            getParagraphsFromFullText();
        } catch (TimeoutException poe) {
            LOG.info("context", poe);
        }
        return sb.toString();
    }

    /**
     * This method is to verify the search terms are displayed in given sequence in the single paragraph of full text document and returns the boolean accordingly.
     *
     * @param searchTerms
     * @param by
     * @return boolean
     */
    public boolean isSearchTermsPresentInParagraph(TermsInSequence termsInSequence, By by, String... searchTerms) {
        String text;
        int count;
        int termIndex;
        try {
            for (WebElement element : waitForExpectedElements(by)) {
                count = 0;
                text = element.getText().toLowerCase();
                termIndex = 0;

                for (String searchTerm : searchTerms) {
                    searchTerm = searchTerm.toLowerCase();
                    //System.out.println(" ...Checking Search Term is in paragraph" + searchTerm);
                    if (text.contains(searchTerm)) {
                        count++;
                        if (count == searchTerms.length) {
                            return true;
                        }
                        if (termsInSequence.equals(TermsInSequence.YES)) {
                            termIndex = text.indexOf(searchTerm) + searchTerm.length();
                            text = text.substring(termIndex, text.length() - 1);
                        }
                    } else {
                        break;
                    }
                }
            }
        } catch (StaleElementReferenceException se) {
            LOG.info("context", se);
            isSearchTermsPresentInParagraph(termsInSequence, by, searchTerms);
        } catch (TimeoutException | NoSuchElementException nse) {
            LOG.info("context", nse);
        }
        return false;
    }
    /**
     * This method is to verify the search terms are displayed in given sequence in the single paragraph of full text document and returns the boolean accordingly.
     *
     * @param by
     * @return boolean
     */    
    public boolean isSearchTermsPresentInSentence(TermsInSequence termsInSequence, By by, String firstTerm, String secondTerm) {
        String text=waitForExpectedElement(by).getText();
        Pattern patternInOrder = Pattern.compile(firstTerm+".+"+secondTerm);
        Pattern patternRevers = Pattern.compile(secondTerm+".+"+firstTerm);
        Pattern p = Pattern.compile("(^|(?<=[.!?]\\s))(\\d+\\.\\s?)*[A-Z][^!?]*?[.!?](?=\\s*(\\d+\\.\\s)*[A-Z]|$)", Pattern.MULTILINE);
        Matcher m = p.matcher(text);
        String sentence;
        Boolean matcherInOder;
        Boolean matcherRevers;
        while (m.find()) {
        	sentence=m.group().toLowerCase();
        	matcherInOder = patternInOrder.matcher(sentence).find();
        	matcherRevers = patternRevers.matcher(sentence).find();
            if (termsInSequence.equals(TermsInSequence.YES) & matcherInOder) {
            	return true;
            }
            if (termsInSequence.equals(TermsInSequence.NO) & ((matcherInOder)||(matcherRevers))) {
            	return true;
            }
        }
        return false;
    }
    
	/**
     * This method finds the given words are present or not in the given number of words sequence and returns the boolean value accordingly.
     *
     * @param termsInSequence
     * @param withInTerms
     * @param searchTerms
     * @return boolean
     */
    public boolean isSearchTermsPresentInParagraphWithInNumberOfWords(TermsInSequence termsInSequence, int withInTerms, By by, String... searchTerms) {
        int termsCount;
        List<String> subStrings;
        String actualText = "";
        try {
            for (WebElement element : waitForExpectedElements(by)) {
                actualText = element.getText().toLowerCase();
                for (String searchTerm : searchTerms) {
                    searchTerm = searchTerm.toLowerCase();
                    String text = actualText;
                    while (text.contains(searchTerm)) {
                        termsCount = 0;
                        text = getSplittedParaFromTermOnwards(text, searchTerm);
                        subStrings = getNextWordsFromSearchTermOnwards(withInTerms, text);
                        for (String term : searchTerms) {
                            if (text.contains(term)) {
                                if (!term.equals(searchTerm)) {
                                    if (subStrings.toString().contains(term)) {
                                        termsCount++;

                                        if (termsCount == searchTerms.length - 1) {
                                            return true;
                                        }
                                    }
                                }
                            } else {
                                if (!term.equals(searchTerm)) {
                                    break;
                                }
                            }
                        }
                    }
                    if (TermsInSequence.YES.equals(termsInSequence)) {
                        break;
                    }
                }
            }
        } catch (StaleElementReferenceException se) {
            LOG.info("context", se);
            isSearchTermsPresentInParagraphWithInNumberOfWords(termsInSequence, withInTerms, by, searchTerms);
        } catch (TimeoutException | NoSuchElementException nse) {
            LOG.info("context", nse);
        }
        return false;
    }

    private String removeSplCharsFromText(String text) {
        text = text.replace(",", "");
        text = text.replace(".", "");
        text = text.replace("(", "");
        text = text.replace(")", "");
        text = text.replace(")", "");
        text = text.replace(" s ", "");
        text = text.replace("'", "");
        text = text.replaceFirst(" ", "");
        return text;
    }

    private List<String> getNextWordsFromSearchTermOnwards(int withInTerms, String text) {
        int wordCount = 1;
        List<String> subStrings = new ArrayList<String>();
        text = removeSplCharsFromText(text);
        for (String str : text.split(" ")) {
            if (wordCount > withInTerms - 1) {
                break;
            }
            subStrings.add(str);
            wordCount++;
        }
        return subStrings;
    }

    private String getSplittedParaFromTermOnwards(String text, String searchTerm) {
        int termIndex = text.indexOf(searchTerm) + searchTerm.length();
        if ((termIndex == text.length())||(termIndex == text.length()-1)) {
            return "";
        }
        return text.substring(termIndex + 1, text.length() - 1);
    }

    /**
     * Object checking terms with hit mark up
     */

    public List termNavigationHitMarkupCheckTermsAsList() {
        // Get a list of any terms marked up
        List<WebElement> eList =
                findElements(By.xpath("//span[contains(@class,'searchTerm')]"));
        // Prepare a list of strings to store the text returned
        List<String> getTextStringList = new ArrayList<String>();

        for (WebElement element : eList) {
            getTextStringList.add(element.getText());
        }
        return getTextStringList;
    }

    /**
     * Object for topic links (which is hidden behind IAC so can only be accessed with certain logins/routing)
     */


    public WebElement topicDetails(String topic) {
        return waitForExpectedElement(By.xpath("//div[@id='co_endOfDocument']//div[@class='co_topics'][contains(text(),'" + topic + "')]"));

    }




}

