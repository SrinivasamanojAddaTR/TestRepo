package com.thomsonreuters.pageobjects.common;

import org.apache.commons.lang.StringEscapeUtils;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ian Hudson u6038385 on 29/07/2016.
 */
public class CommonStringMethods {

    private static final String ASCII_QUOTE = "'";
    private static final String REGEX_ASCII_DOUBLE_QUOTE_AS_OPENED = "((?<=\\W)\"|^\")";
    private static final String REGEX_ASCII_DOUBLE_QUOTE_AS_CLOSED = "(\"(?=\\W)|\"$)";
    private static final String WORD_QUOTE = "\u2019";
    private static final String WORD_DOUBLE_QUOTE_OPENED = "\u201C";
    private static final String WORD_DOUBLE_QUOTE_CLOSED = "\u201D";
    private static final String NON_WORD_CHARACTER = "^\\w";
    private static final Logger LOG = LoggerFactory.getLogger(CommonStringMethods.class);
    public static final String SLASH_DELIMITER = "/";

    /**
     * Get text between two strings. Passed limiting strings are not
     * included into result.
     *
     * @param text           Text to search in.
     * @param textFrom       Text to start cutting from (exclusive).
     * @param textTo         Text to stop cutting at (exclusive).
     * @param caseSensitive  Should the case be checked.*
     */
    public String getBetweenStrings(String text, String textFrom, String textTo, boolean caseSensitive) {
        String result = "";
        if (!caseSensitive) {
            text = text.toLowerCase();
            textFrom = textFrom.toLowerCase();
            textTo = textTo.toLowerCase();
        }

        // Cut the beginning of the text to not occasionally meet a
        // 'textTo' value in it:
        result = text.substring(text.indexOf(textFrom) + textFrom.length(), text.length());

        // Cut the excessive ending of the text (provided textTo exists within the remaining text):
        if (result.indexOf(textTo) >= 0) {
            result = result.substring(0,result.indexOf(textTo));
        }

        return result;
    }

    public List<String> getBetweenStringsForAStringList(
            List<String> inputList,
            String textFrom,
            String textTo) {
        String currentText;
        List<String> outputList = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            currentText = getBetweenStrings(inputList.get(i),textFrom, textTo, false);
            LOG.info("Current text is {}", currentText);
            outputList.add(currentText);
        }

        return outputList;
    }

    public List<String> getBetweenStringsForAWebelementList(
            List<WebElement> inputList,
            String textFrom,
            String textTo) {
        String currentText;
        List<String> outputList = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            currentText = getBetweenStrings(inputList.get(i).getText(),textFrom, textTo, false);
            LOG.info("Current text is {}", currentText);
            outputList.add(currentText);
        }

        return outputList;
    }

    public List<Integer> convertStringListToIntegerList(
            List<String> inputList) {
        Integer currentTextAsInt;
        List<Integer> outputList = new ArrayList<>();
        String currentText;

        for (int i = 0; i < inputList.size(); i++) {
            currentText = inputList.get(i);
            // Remove any alpha characters, keeping the numeric
            currentText = currentText.replaceAll("[^0-9]","");
            // Next convert the string to an integer
            currentTextAsInt = Integer.parseInt(currentText);
            outputList.add(currentTextAsInt);
        }

        return outputList;
    }

	public List<String> unescape(List<String> text) {
		List<String> result = new ArrayList<>();
		for (String str : text) {
			result.add(StringEscapeUtils.unescapeJava(str));
		}
		return result;
	}

    public List<String> getRegExpGroupValue(String regExp, String fullText){
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(fullText);
        List<String> list = new ArrayList<>();
        while (m.find()){
            list.add(m.group());
        }
        return list;
    }

    /**
     * Replace single quotes and double quotes from ASCII table (which we can get on the page in HTML document) with
     * single quotes and opened / closed double quotes which are present in M$ Word / Rtf documents
     *
     * @param textWithAsciiQuotes Text which contains single ot double quotes
     * @return A string with single quotes and opened / closed double quotes which may contains in M$ Word
     */
    public String replaceAsciiQuotesForMsWord(String textWithAsciiQuotes) {
        return textWithAsciiQuotes
                .replace(ASCII_QUOTE, WORD_QUOTE)
                .replace(REGEX_ASCII_DOUBLE_QUOTE_AS_OPENED, WORD_DOUBLE_QUOTE_OPENED)
                .replace(REGEX_ASCII_DOUBLE_QUOTE_AS_CLOSED, WORD_DOUBLE_QUOTE_CLOSED);
    }

    public int getAllNumbersFromString(String source) {
        return Integer.parseInt(source.replaceAll("\\D+", ""));
    }

    public int calculateDiffBetweenStrings(String s1, String s2) {
        return Math.abs(s1.length() - s2.length());
    }

    public boolean compareContentIgnoreSpacesPunctuationAndCase(String s1, String s2) {
        return s1.replaceAll(NON_WORD_CHARACTER, "").substring(0, calculateDiffBetweenStrings(s1, s2))
                .equalsIgnoreCase(s2.replaceAll(NON_WORD_CHARACTER, "")
                        .substring(0, calculateDiffBetweenStrings(s1, s2)));
    }
}

