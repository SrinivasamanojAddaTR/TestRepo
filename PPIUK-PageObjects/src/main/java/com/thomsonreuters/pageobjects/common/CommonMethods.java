package com.thomsonreuters.pageobjects.common;

import static java.math.BigInteger.ZERO;
import static org.junit.Assert.assertTrue;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thomsonreuters.driver.configuration.Hosts;
import com.thomsonreuters.driver.framework.ScreenShots;
import com.thomsonreuters.utils.CalendarAndDate;
import com.thomsonreuters.utils.TimeoutUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UselessFileDetector;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.LoggerFactory;
import static java.math.BigInteger.ZERO;
import com.google.common.base.Function;
import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.WebDriverDiscovery;
import com.thomsonreuters.pageobjects.pages.generic.PPIGenericDocDisplay;
import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.JournalDocumentPage;
import com.thomsonreuters.pageobjects.utils.Product;

public class CommonMethods {

    private CommonStringMethods commonStringsMethods = new CommonStringMethods();
    private JournalDocumentPage journalDocumentPage = new JournalDocumentPage();
    private WebDriverDiscovery webDriverDiscovery = new WebDriverDiscovery();
    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CommonMethods.class);
    private static final int FLUENT_TIMEOUT_IN_SECONDS = 30;
    private static final int FLUENT_POLLINGTIME_IN_MILLISECONDS = 2000;

    private WebDriver driver;
    private boolean isGrid = System.getProperty("driverType", "").equalsIgnoreCase("seleniumGrid");
	private static final String SPLIT_BY_LINE = "\\r?\\n";
	private HomePage homePage = new HomePage();

    public CommonMethods() {
        this.driver = webDriverDiscovery.getWebDriver();
    }

    public WebDriverDiscovery getWebDriverDiscovery() {
        return webDriverDiscovery;
    }

    public WebDriver getDriver() {
        return driver;
    }

    private PPIGenericDocDisplay ppiGenericDocDisplay = new PPIGenericDocDisplay();

    public Boolean theDisplayedDocumentWillHaveTheTermsMarkedUpAsHits(String searchTerms) throws Throwable {
        Boolean result = false;
        if (!searchTerms.equals("") && !searchTerms.isEmpty()) {
            /** Split each term using the space character */
            String eachTerms[] = searchTerms.split(" ");
            Boolean termFound;
            String textFromElement;
            for (int dataRow = 0; dataRow < eachTerms.length; dataRow++) {
                String currentTerm = eachTerms[dataRow].toUpperCase();
                /** remove any white spaces */
                currentTerm = currentTerm.replaceAll("\\s+", "");
                /** Ignore And and Or */
                if ((!currentTerm.equals("AND")) && (!currentTerm.equals("&")) && (!currentTerm.equals("OR"))) {
                    if (currentTerm.length() > 0) {
                        termFound = false;
                        /** If a single term is connected with a + plus sign split that into multiple terms, any of which will pass */
                        String equivalentTerms[] = currentTerm.split("\\+");
                        for (int dataRowTwo = 0; dataRowTwo < equivalentTerms.length; dataRowTwo++) {
                            currentTerm = equivalentTerms[dataRowTwo];
                            LOG.info(" ...Checking that the term '" + currentTerm + "' is marked up as a hit");
                            List<WebElement> searchTermsFound = ppiGenericDocDisplay.ppiTermNavigationHitMarkupCheckTermsAsList();
                            LOG.info("The number of marked up search terms found is: " + searchTermsFound.size());
                            for (WebElement element : searchTermsFound) {
                                textFromElement = element.getText().toUpperCase();
                                if (Pattern.matches(wildcardToRegex("*" + currentTerm + "*"), textFromElement)) {
                                    termFound = true;
                                    break;
                                }
                            }
                        }
                        result = termFound;
                    }
                }
            }
        }
        return result;
    }

    public Boolean theDisplayedDocumentWillHaveAnyOfTheTermsMarkedUpAsHits(String searchTerms) throws Throwable {
        Boolean result = false;
        if (!searchTerms.equals("") && !searchTerms.isEmpty()) {
            /** Split each term using the space character */
            String eachTerms[] = searchTerms.split(" ");
            Boolean termFound = false;
            String textFromElement;
            for (int dataRow = 0; dataRow < eachTerms.length; dataRow++) {
                String currentTerm = eachTerms[dataRow].toUpperCase();
                /** remove any white spaces */
                currentTerm = currentTerm.replaceAll("\\s+", "");
                /** Ignore And and Or */
                if ((!currentTerm.equals("AND")) && (!currentTerm.equals("&")) && (!currentTerm.equals("OR"))) {
                    if (currentTerm.length() > 0) {
                        /** If a single term is connected with a + plus sign split that into multiple terms, any of which will pass */
                        String equivalentTerms[] = currentTerm.split("\\+");
                        for (int dataRowTwo = 0; dataRowTwo < equivalentTerms.length; dataRowTwo++) {
                            currentTerm = equivalentTerms[dataRowTwo];
                            LOG.info(" ...Checking that the term '" + currentTerm + "' is marked up as a hit");
                            List<WebElement> searchTermsFound = ppiGenericDocDisplay.ppiTermNavigationHitMarkupCheckTermsAsList();
                            LOG.info("The number of marked up search terms found is: " + searchTermsFound.size());
                            for (WebElement element : searchTermsFound) {
                                textFromElement = element.getText().toUpperCase();
                                if (Pattern.matches(wildcardToRegex("*" + currentTerm + "*"), textFromElement)) {
                                    termFound = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            result = termFound;
        }
        return result;
    }

    /**
     * Taken from  http://www.rgagnon.com/javadetails/java-0515.html
     * This is to allow wildcard matches
     */
    public static String wildcardToRegex(String wildcard) {
        String outputString;
        StringBuilder s = new StringBuilder(wildcard.length());
        s.append('^');
        for (int i = 0, is = wildcard.length(); i < is; i++) {
            char c = wildcard.charAt(i);
            switch (c) {
                case '*':
                    s.append(".*");
                    break;
                case '?':
                    s.append(".");
                    break;
                /** escape special regexp-characters */
                case '(':
                case ')':
                case '[':
                case ']':
                case '$':
                case '^':
                case '.':
                case '{':
                case '}':
                case '|':
                case '\\':
                    s.append("\\");
                    s.append(c);
                    break;
                default:
                    s.append(c);
                    break;
            }
        }
        s.append('$');
        outputString = s.toString();
        return (outputString);
    }

    public String getAlertDialogMsg() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void acceptAlertDialogMsg() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void acceptDialogIfAppears() {
        try {
            Alert alert = null;
            alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            LOG.info("No Alert present", e);
        }
    }

    public void dismissDialogIfAppears() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public WebElement findElementByText(List<WebElement> eList, String text) {
        WebElement webElement = null;
        for (WebElement e : eList) {
            if (e.getText().equalsIgnoreCase(text)) {
                webElement = e;
                break;
            }
        }
        return webElement;
    }

    public WebElement findElementByAttribute(List<WebElement> eList, String attribute, String value) {
        WebElement webElement = null;
        for (WebElement e : eList) {
            if (e.getAttribute(attribute).equalsIgnoreCase(value)) {
                webElement = e;
                break;
            }
        }
        return webElement;
    }

    public WebElement waitForExpectedElement(final By by) {
        return waitForExpectedElement(by, FLUENT_TIMEOUT_IN_SECONDS, FLUENT_POLLINGTIME_IN_MILLISECONDS);
    }

    public WebElement waitForExpectedElement(final By by, int timeOut, int pollingTime) {
        WebElement element = null;
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOut, TimeUnit.SECONDS)
                .pollingEvery(pollingTime, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
        element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
        return element;
    }
    
    public void selectParagraphFromDocumentWithJS(WebElement element) {
        homePage.executeScript("selection = window.getSelection();"
        		+ " range = document.createRange();"
        		+ "range.selectNodeContents(arguments[0]);"
        		+ "selection.removeAllRanges();"
        		+ " selection.addRange(range); ", element);
    }


    public void switchToIframe(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void switchOutFromIframe() {
        driver.switchTo().defaultContent();
    }

    /**
     * use @deprecated isExists(), isExists()
     */
    @Deprecated
    public boolean waitForElement(WebElement element, int waitTime) {
        try {
            int i = 0;
            do {
                Thread.sleep(waitTime);
                try {
                    if (element.isDisplayed()) {
                        return true;
                    }
                } catch (Exception e) {
                    LOG.warn("Element not yet found", e);
                }
                i++;
            } while (i < 5);
        } catch (Exception e) {
            LOG.warn("Element not yet found", e);
        }
        return false;
    }

    /**
     * use @deprecated waitForExpectedElement(), waitForElementToBeVisible(), waitForElementPresent()
     */
    @Deprecated
    public WebElement waitForElement(By by, int waitTime) {
        try {
            int i = 0;
            do {
                Thread.sleep(waitTime);
                try {
                    List<WebElement> elements = driver.findElements(by);
                    if (!elements.isEmpty()) {
                        return elements.get(0);
                    }
                } catch (Exception e) {
                    LOG.warn("Element not yet found", e);
                }
                i++;
            } while (i < 5);
        } catch (Exception e) {
            LOG.warn("Element not yet found", e);
        }
        return null;
    }

    public WebElement waitForElementToBeVisible(By by, int waitTime) {
        try {
            int i = 0;
            do {
                Thread.sleep(waitTime);
                try {
                    List<WebElement> elements = driver.findElements(by);
                    if (elements.get(0).isDisplayed()) {
                        return elements.get(0);
                    }
                } catch (Exception e) {
                    LOG.warn("Element not yet visible..!", e.getMessage());
                }
                i++;
            } while (i < 5);
        } catch (Exception e) {
            LOG.warn("Element not yet found", e);
        }
        return null;
    }

    public WebElement getElement(By by) {
        List<WebElement> elements = driver.findElements(by);
        if (!elements.isEmpty()) {
            return elements.get(0);
        }
        return null;
    }

    /**
     * Recreating the action of hovering over a particular HTML element on a
     * page.
     *
     * @param element
     */
    public void mouseOver(WebElement element) {
        if (element == null) {
            throw new IllegalArgumentException("WebElement is required");
        }
        String code = "var fireOnThis = arguments[0];" + "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initEvent( 'mouseover', true, true );" + "fireOnThis.dispatchEvent(evObj);";
        ((JavascriptExecutor) driver).executeScript(code, element);
    }

    public void clickLink(String linkText) {
        try {
            driver.findElement(By.linkText(linkText)).click();
        } catch (Exception e) {
            LOG.info("context", e);
        }
    }

	public void scrollIntoAndClickLink(String linkText) {
		homePage.waitForViewScrollingToElement(By.linkText(linkText));
		homePage.scrollIntoViewAndClick(homePage.getElementByLinkText(linkText));
	}
	
	public void waitForExpectedLinkAndClick(String partialLinkText) {
		homePage.scrollIntoViewAndClick(homePage.waitForExpectedElement(By.linkText(partialLinkText)));
	}

    public WebElement getElementByLinkText(String linkText) {
        try {
            return driver.findElement(By.linkText(linkText));
        } catch (Exception e) {
            LOG.info("context", e);
            return null;
        }
    }

    public WebElement waitElementByLinkText(String linkText) {
        return homePage.waitForExpectedElement(By.linkText(linkText));
    }

    public void clickElementUsingJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public WebElement moveToElementUsingJS(WebElement element) {
        homePage.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

	public void scrollBy(int offset) {
        homePage.executeScript("window.scrollBy(0," + offset + ");");
	}

	public void scrollTo(int offset) {
        homePage.executeScript("window.scrollTo(0," + offset + ");");
	}

    public void moveToElementUsingJSThenClick(WebElement element) {
        homePage.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void moveToElementUsingLocation(WebElement element) {
        Coordinates elementCoords = ((Locatable) element).getCoordinates();
        elementCoords.inViewPort();
    }

    /**
     * This is generic method to find out the given list of elements is given
     * expected order or not and returns the boolean value accordingly.
     *
     * @param listOfT
     * @param sortOptions
     * @param <T>
     * @return boolean
     */
    public <T extends Comparable> boolean isSorted(List<T> listOfT, SortOptions sortOptions) {
        T previous = null;
        String condition;
        if (SortOptions.DESC.equals(sortOptions)) {
            for (T t : listOfT) {
                if (previous != null && t.compareTo(previous) > 0)
                    return false;
                previous = t;
            }
        } else if (SortOptions.ASC.equals(sortOptions)) {
            for (T t : listOfT) {
                if (previous != null && t.compareTo(previous) < 0)
                    return false;
                previous = t;
            }
        }
        return true;
    }

    public void checkDateFormatsAreValid(List<WebElement> dateElements, String dateFormat) {
        String dateString;
        for (int loopCount=0; loopCount<dateElements.size(); loopCount++) {
            dateString = dateElements.get(loopCount).getText();
            dateString = dateString.replace("Published on ","");
            //System.out.println("Result date is: " + dateString);
            Assert.assertTrue(isDateInValidFormat(dateString, dateFormat));
        }
    }

    /**
     * This method verifies the displayed search results are in expected sorting order by date or not and returns the boolean value accordingly.
     *
     * @param sortOptions
     * @return boolean
     */
    public Boolean isResultsSortedByDate(List<WebElement> dateElements, SortOptions sortOptions) {
        List<Date> dates = new ArrayList<Date>();
        Date resultDate;
        Boolean areDatesValid = true;
        for (WebElement element : dateElements) {
            String dateString = element.getText();
            DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
            try {
                resultDate = df.parse(dateString);
                dates.add(resultDate);
            } catch (ParseException e) {
                areDatesValid = false;
                LOG.info("context", e);
                throw new PageOperationException("Application is displaying the dates in different format." + e.getMessage());
            }
        }
        if (areDatesValid) {
            return isSorted(dates, sortOptions);
        } else {
            // Return false - failed as dates are not valid to begin with
            return areDatesValid;
        }
    }

    /**
     * This method verifies the displayed search results are displaying dates starting with 0 if the day has single digit.
     *
     * @return boolean
     */
    public Boolean isDateStartsWithZeroForSingleDigitDay(List<WebElement> dateElements) {
        Boolean result = true;
        for (WebElement element : dateElements) {
            String dateString = element.getText();
            dateString = dateString.replace("Published on ","");
            String[] dateStrings = dateString.split(" ");
            String dayString = dateStrings[0];
            if (Integer.valueOf(dayString) < 10 && !dayString.startsWith("0")) {
                result = false;
            }
        }
        return result;
    }

    public boolean isDateInValidFormat(String s, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            sdf.setLenient(false);
            Date date = sdf.parse(s);
            //LOG.info("Given date is in Valid format." + date);
            return true;
        } catch (ParseException e) {
            LOG.info("Given date is not in Valid format." + s);
            return false;
        }
    }

    /**
     * This method is to switch to new window found by given window name.
     *
     * @param newWindowTitle
     * @throws InterruptedException
     */
    public void switchDriverToAnotherWindow(String newWindowTitle) throws InterruptedException {
        Set<String> windowsHandles = driver.getWindowHandles();
        boolean windowFound = false;
        String currentWindowName = driver.getTitle();
        for (int i = 0; i < 20; i++) {
            for (String window : windowsHandles) {
                driver.switchTo().window(window);
                if (!driver.getTitle().equals(currentWindowName)
                        && driver.getTitle().toLowerCase().contains(newWindowTitle.toLowerCase())) {
                    windowFound = true;
                    break;
                }
            }
            if (windowFound) {
                break;
            } else {
                Thread.sleep(200);
            }
        }
        assertTrue(windowFound);
    }

    /**
     * This method is to find and close the given window name and stay back on
     * the current window.
     *
     * @param windowName
     */
    public void findAndCloseWindow(String windowName) {
        String currentHandle = driver.getWindowHandle();
        Set<String> windowsHandles = driver.getWindowHandles();
        boolean windowFound = false;
        String currentWindowName = driver.getTitle();
        for (String window : windowsHandles) {
            driver.switchTo().window(window);
            if (driver.getTitle().contains(windowName)) {
                driver.close();
            }
        }
        driver.switchTo().window(currentHandle);
    }

    /**
     * This is a pageobjects method to serve the execution of given regexp against
     * the given text and returns the boolean according to the finding.
     *
     * @param regExp
     * @param fullText
     * @return boolean
     */
    public boolean isRegExpFound(String regExp, String fullText) {
        try {
            Pattern pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
            return pattern.matcher(fullText).find();
        } catch (Exception e) {
            LOG.info("context", e);
            return false;
        }
    }

    public List<String> getRegExpGroupValue(String regExp, String fullText){
        return commonStringsMethods.getRegExpGroupValue(regExp, fullText);
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
    public boolean isCurrentDocumentFromKnowHow() {
        return driver.getCurrentUrl().contains("KNOWHOW");
    }

//	public boolean isTextPresent(By byElement, String expectedText, int waitTime) {
//		try {
//			int i = 0;
//			do {
//				Thread.sleep(waitTime);
//				try {
//
//					if (driver.findElement(byElement).getText().trim().contains(expectedText)) {
//						return true;
//					}
//				} catch (Exception e) {
//					LOG.warn("Text not yet found", e);
//				}
//				i++;
//			} while (i < 5);
//		} catch (Exception e) {
//			LOG.warn("Text not yet found", e);
//		}
//		return false;
//	}

    public void scrollUpOrDown(int y) {
        ((JavascriptExecutor) driver).executeScript("scroll(0," + y + ");");
    }

    public void scrollRightOrLeft(int x) {
        ((JavascriptExecutor) driver).executeScript("scroll(\"+x+\",0);");
    }

    public boolean isLinkTextPresent(String expectedLinkText, int waitTime) {
        try {
            int i = 0;
            do {
                Thread.sleep(waitTime);
                try {

                    if (driver.findElement(By.linkText(expectedLinkText)) != null) {
                        return true;
                    }
                } catch (Exception e) {
                    LOG.warn("Text not yet found", e);
                }
                i++;
            } while (i < 5);
        } catch (Exception e) {
            LOG.warn("Text not yet found", e);
        }
        return false;
    }

    /**
     * Method to find duplicate words in a Sentence or String
     *
     * @param input String
     * @return set of duplicate words
     */
    public static Set<String> duplicateWords(String input) {
        Set<String> duplicates = new HashSet<>();
        Set<String> set = new HashSet<>();

        if (input == null || input.isEmpty()) {
            return Collections.emptySet();
        }

        String[] words = input.split("\\s+");

        for (String word : words) {
            if (!set.add(word)) {
                duplicates.add(word);
            }
        }
        return duplicates;
    }

    public boolean isImageLoaded(WebElement image) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Object result = jse.executeScript("return arguments[0].complete && " + "typeof arguments[0].naturalWidth != \"undefined\" && "
                + "arguments[0].naturalWidth > 0", image);

        return (Boolean) result;
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException ne) {
            return false;
        }
    }

    public String getTableCellByOtherColumnValueAndHeader(WebElement table, String referenceHeader, String referenceCellContains,
                                                          String header) {
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
        int referenceHeaderIndex = -1;
        int headerIndex = -1;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equals(referenceHeader)) {
                referenceHeaderIndex = i;
            }
            if (headers.get(i).getText().trim().equals(header)) {
                headerIndex = i;
            }
        }
        if (referenceHeaderIndex == -1 || headerIndex == -1) {
            return "";
        }

        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            if (cells.get(referenceHeaderIndex).getText().contains(referenceCellContains)) {
                return cells.get(headerIndex).getText();
            }
        }
        return "";
    }

    public String firstHundredChars(String text) {
        if (text.length() > 100) {
            return text.substring(0, 100) + "...";
        } else {
            return text;
        }
    }

    /**
     * This method is to create the random string with given length of chars
     *
     * @param length
     * @return String
     */
    public String getRandomStringWithGivenLength(final int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    /**
     * Get all numbers from string and return value as integer.
     * RegEx [^\d] is useing
     *
     * @param stringWithNumbers String which contains numbers
     * @return Integer from numbers from string
     */
    public int getIntFromString(String stringWithNumbers) {
        return Integer.parseInt(stringWithNumbers.replaceAll("[^\\d]", ""));
    }

    /**
     * Get list of each WebElement text from the given WebElement list.
     *
     * @param webElements  List of WebElements which text do you need {@link WebElement#getText()}.
     *                     Also, each string will be trimmed.
     * @param splitByRegex Regular expression for splitting string from each WebElement, if necessary.
     *                     Can be omitted by passing null or empty string
     * @return List with trimmed strings from each WebElement of list
     */
    public List<String> getTextsFromWebElements(List<WebElement> webElements, String splitByRegex) {
        List<String> result = new ArrayList<>();
        for (WebElement webElement : webElements) {
            if (splitByRegex != null && !splitByRegex.isEmpty()) {
                String[] splittedString = webElement.getText().trim().split(splitByRegex);
                result.addAll(Arrays.asList(ArrayUtils.removeElements(splittedString,"",null)));
            } else {
                result.add(webElement.getText().trim());
            }
        }
        return result;
    }

    /**
     * Get list of each WebElement text from the given WebElement list.
     *
     * @param webElements List of WebElements which text do you need {@link WebElement#getText()}.
     *                    Also, each string will be trimmed.
     * @return List with trimmed strings from each WebElement of list
     */
    public List<String> getTextsFromWebElements(List<WebElement> webElements) {
        return getTextsFromWebElements(webElements, null);
    }

    /**
     * Is one string contains another joined string
     *
     * @param source              String with source text, where expected text should be
     * @param target              Joined string which should be exists in source text
     * @param targetSplitterRegex RegEx splitter for target joined string. Target sub-strings will be obtained by it splitting
     *                            with this parameter.
     * @return True - if check passed, false - otherwise.
     */
    public boolean isStringContains(String source, String target, String targetSplitterRegex) {
        String[] targets = target.split(targetSplitterRegex);
        for (String str : targets) {
            if (!source.contains(str)) {
                return false;
            }
        }
        return true;
    }

    public String getPlUkRootAddressFromPropertiesWithoutProtocol() {
        return getRootAddressFromProperties(Product.PLC, false);
    }

    /**
     * Enable ability to push local file to the remote selenium grid node
     */
    //TODO remove comments once setFileDetector is implemented
    /*public void enableLocalFileDetector() {
        setFileDetector(true);
    }*/

    /**
     * Disable ability to push local file to the remote selenium grid node
     */
    //TODO remove comments once setFileDetector is implemented
   /* public void disableLocalFileDetector() {
        setFileDetector(false);
    }*/

    /**
     * Get Date object from the string
     *
     * @param dateToParse String with parsable date
     * @param pattern Pattern to parse date from string
     * @return Date object with parsed date
     */
    public Date getDateFromString(String dateToParse, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(dateToParse);
        } catch (ParseException e) {
            LOG.info("Unable to parse date '" + dateToParse + "' with pattern '" + pattern + "'", e);
            return new Date(0L); // 1970-01-01
        }
    }

    /**
     * Make RemoteWebDriver on selenium grid node able to work with local files
     * TODO @toFramework - this ability should be part of the core framework
     *
     *
     * param isLocalFileDetector True - if there is necessary to push local files to remote selenium grid node, otherwise - false.
     */
    //TODO [phase1] Need to know getFileDetector and setFileDetector purpose and add in TAF accordingly
    /*private void setFileDetector(boolean isLocalFileDetector) {
        if (isGrid) {
            if (isLocalFileDetector && driver.getFileDetector() instanceof UselessFileDetector) {
                driver.setFileDetector(new LocalFileDetector());
            } else if (!isLocalFileDetector && !(driver.getFileDetector() instanceof UselessFileDetector)) {
                driver.setFileDetector(new UselessFileDetector());
            }
        }
    }*/

    private String getRootAddressFromProperties(Product product, boolean withProtocol) {
        String urlFromProperties;
        switch (product) {
            case PLC:
                urlFromProperties = Hosts.getInstance().getPlcukProductBase()  + System.getProperty("base.url") + Hosts.getInstance().getPlcukProductBase() ;
                break;
            default:
                throw new UnsupportedOperationException("Not implemented for product " + product);
        }
        if (!withProtocol) {
            urlFromProperties = urlFromProperties.replaceAll("http://", "").replaceAll("https://", "");
        }
        return urlFromProperties;
    }

    /**
     * Close welcome pop-up on PL+ UK via JavaScript
     */
    public void closeWelcomePopupPlUk() {
        homePage.executeScript("if (typeof jQuery != 'undefined') { $('.co_overlayBox_closeButton').click(); }");
    }

    public void moveSlider(WebElement slider, int positionOffSet) {
    	moveSlider(slider, positionOffSet, 0, 100);
	}

    public void moveSlider(WebElement slider, int positionOffSetX, int positionOffSetY, int steps) {
		int width = slider.getSize().getWidth();
		int hight = slider.getSize().getHeight();
		Actions move = new Actions(driver);
		move.moveToElement(slider, ((width * positionOffSetX) / steps), ((hight* positionOffSetY) / steps)).click();
		move.build().perform();
	}

    public String getElementCSSValueByName(WebElement element, String cssSettingName){
        if (element != null && cssSettingName != null) {
            String value = element.getCssValue(cssSettingName);
            if (StringUtils.isEmpty(value)){
                LOG.info("Received Css value is empty");
            } else{
                LOG.info(cssSettingName + ": "+ value);
            }
            return value;
        }
        LOG.info("Css value or element is not specified or empty");
        return "";
    }
    public String getElementFontSize(WebElement element) {
        return getElementCSSValueByName(element, "font-size");
    }

	public void pressEscape() {
		Actions action = new Actions(homePage.getDriver);
		action.sendKeys(Keys.ESCAPE).build().perform();
	}

	public String[] splitTextByLines(String text) {
		String splitedText[] = text.split(SPLIT_BY_LINE);
		return splitedText;
	}


	public List<String> addTheTextToTheListExcludingTheBlankLines(String text) {
		List<String> result = new ArrayList<String>();
		for (String line : splitTextByLines(text)) {
			if (line.length() > 1) {
				result.add(line);
			}
		}
		return result;
	}


	public void uncheckTheCheckbox(WebElement element) {
		if (element.getAttribute("checked") != null) {// if Checked
			element.click();
		}
	}

	public void clearSessionstorage() {
		homePage.executeScript("window.sessionStorage.clear();");
	}

	public void clickOutsideThePopup(WebElement popup, int offset) {
		Actions action = new Actions(driver);
		action.moveToElement(popup, offset, offset).click().build().perform();
	}


    /**
     * Check that given string contains and / or not contains expected phrases
     *
     * @param source Text, which should contains or not contains phrases
     * @param phraseShouldExists Text, which should be exists in the document. There is can be few phrases joined with comma ",".
     * @param phraseShouldAbsent Text, which should not be exists in the document.
     *                           There is can be few phrases joined with comma ",".
     *                           OPTIONAL: if phrase absence check is not needed, than this argument should be passed
     *                           as empty string.
     * @return True - if given source text contains some text, otherwise - false.
     */
    public boolean isStringContainsOrNotContains(String source, String phraseShouldExists, String phraseShouldAbsent) {
        return isStringContains(source, phraseShouldExists, ",") &&
                (StringUtils.isEmpty(phraseShouldAbsent) || !isStringContains(source, phraseShouldAbsent, ","));
    }

    public String getDocumentGUIDFromCurrentURL() {
        return getDocumentGUIDFromURL(getDriver().getCurrentUrl());
    }

	public String getDocumentGUIDFromURL(String url) {
		Pattern p = Pattern.compile("(Document/)(.{33})(/View)");
		Matcher m = p.matcher(url);
		m.find();
		return m.group(2);
	}

	public boolean isScrolledIntoViewInTheDocumentPageUsingJS(WebElement element) {
		String distance = getDistanceBetweenTopOfThePageAndWebElementUsingJS(journalDocumentPage.stickyHeader()).toString();
		return (Boolean) homePage.executeScript("function isScrolledIntoView(elem,off) {"
				+ " var $elem = $(elem); var $window = $(window); " + "var docViewTop = $window.scrollTop()+off; "
				+ "var docViewBottom = docViewTop + $window.height();" + "var elemTop = $elem.offset().top;"
				+ "var elemBottom = elemTop + $elem.height();"
				+ "return ((elemBottom <= docViewBottom) && (elemTop >= docViewTop));}"
				+ "return isScrolledIntoView(arguments[0]," + distance + ")",
				element);
	}

	public Object getDistanceBetweenTopOfThePageAndWebElementUsingJS(WebElement element) {
		return (Object) homePage
				.executeScript("function getDistance(elem){ return elem.getBoundingClientRect().top + elem.getBoundingClientRect().height;}"
						+ "return getDistance(arguments[0])", element);
	}

    public Calendar convertInCalendar(String time) {
        String[] parts = time.split(":");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(parts[1]));
        return cal;
    }
    
    public boolean isLinkTextDisplayed(String linkText) {
        return homePage.isElementDisplayed(By.linkText(linkText));
    }
    public void navigateBack()
    {
    	driver.navigate().back();
    }
    /**
     * This method provides a possibility to perform actions in a new browser window,
     * then closing the last opened window and returning back to a main browser window.
     *
     * @param mainWindowHandle a unique handle reference of the main window to return into on action completion.
     * @param action           lambda expression or method reference to execute, returning a <T>T value
     * @return <T>T value, returned by supplier's expression
     */
    public <T> T performActionsInNewWindow(String mainWindowHandle, Supplier<T> action) {
        return performActionsInNewWindow(mainWindowHandle, action, ZERO);
    }

    /**
     * This method provides a possibility to perform actions in a new browser window,
     * then closing the last opened window and returning back to a main browser window.
     *
     * @param mainWindowHandle             a unique handle reference of the main window to return into on action completion.
     * @param action                       lambda expression or method reference to execute, returning a <T>T value
     * @param timeoutInSecondsBeforeAction timeout in seconds after switching to new window and before performing action
     * @return <T>T value, returned by supplier's expression
     */
    private <T> T performActionsInNewWindow(String mainWindowHandle, Supplier<T> action, Number timeoutInSecondsBeforeAction) {
        homePage.switchToLastOpenedWindow();
        try {
            TimeoutUtils.sleepInSeconds(timeoutInSecondsBeforeAction.intValue());
            return action.get();
        } catch (NoSuchWindowException ex) {
            ScreenShots.add("Action failed in new browser window_" + CalendarAndDate.getCurrentDateAndTime(),
                    homePage.getScreenshot());
            throw new NoSuchWindowException("Error performing action in a new browser window \n", ex);
        } finally {
            homePage.close();
            homePage.switchToWindow(mainWindowHandle);
        }
    }

}
