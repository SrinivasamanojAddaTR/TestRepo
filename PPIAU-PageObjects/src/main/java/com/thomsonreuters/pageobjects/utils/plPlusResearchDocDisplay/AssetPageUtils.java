package com.thomsonreuters.pageobjects.utils.plPlusResearchDocDisplay;

import com.rtfparserkit.converter.text.StringTextConverter;
import com.rtfparserkit.parser.IRtfParser;
import com.rtfparserkit.parser.IRtfSource;
import com.rtfparserkit.parser.RtfStreamSource;
import com.rtfparserkit.parser.standard.StandardRtfParser;
import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.WebDriverDiscovery;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.FileActions;
import com.thomsonreuters.pageobjects.common.Link;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.AssetDocumentPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.PrimarySourceDocumentPage;
import com.thomsonreuters.pageobjects.utils.pdf.PDFBoxUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.LoggerFactory;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class AssetPageUtils {

    private WebDriverDiscovery webDriverDiscovery = new CommonMethods().getWebDriverDiscovery();

    private static final String SEPARATOR = " ";
    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CommonMethods.class);
    private static final By SIGN_ON_BUTTON = By.id("signonInputs");
    private static final By USERNAME_FIELD = By.id("uid");
    private static final By PASSWORD_FIELD = By.id("pwd");
    private static final String USERNAME = "mkemp";
    private static final String PASSWORD = "rainbow";

    private static final By SUBMIT_BUTTON = By.id("submitButton");
    private static final By DOCUMENT_BODY = By.id("docBody");
    private static final By SIGNOF_LINK = By.id("signoffLink");
    private static final By EU_DOCUMENT_TITLE = By.xpath("//h1[contains(text(),'European Union')]");
    private static final By TABLE_OF_CONTENTS_SECTION = By.className("kh_toc-list");
    private static final int MAXIMUM_COLOR_INTENSITY = 255;
    private static final int MINIMUM_C0LOR_INTENSITY = 0;
    private static final int MIN_JURISDICTIONS_COUNT = 2;
    private static final Character BULLET_UNICODE_CODE = '\u2022';

    private File downloadedFile = null;

    private String firstUrl;
    private String valueHrefAtribute;
    private static String winHandleFirst;
    private WebElement linkInLinkToThisCaseSection;
    private int numberOfLinksInContentSection;
    //TODO : Need to remove this driver object and replace references with any page object
    private final WebDriver driver = webDriverDiscovery.getWebDriver();

    private AssetDocumentPage assetDocumentPage = new AssetDocumentPage();
    private PrimarySourceDocumentPage primarySourceDocumentPage = new PrimarySourceDocumentPage();
    private PDFBoxUtil pdfBoxUtil = new PDFBoxUtil();
    private FileActions fileActions = new FileActions();

    public WebElement outPutLink() {
        return assetDocumentPage.waitForExpectedElement(SIGNOF_LINK);
    }

    public boolean isSignOutLinkDisplayed () {
        return assetDocumentPage.isElementDisplayed(SIGNOF_LINK);
    }

    private WebElement documentBody() {
        return assetDocumentPage.waitForExpectedElement(DOCUMENT_BODY);
    }

    public WebElement euDocumentTitle() {
        return assetDocumentPage.findChildElement(documentBody(), EU_DOCUMENT_TITLE);
    }

    public boolean isTheUserSeeLinksToBailii(String bailiiLink) {
        try {
            assetDocumentPage.bailiiLink(bailiiLink);
            return true;
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
            return false;
        }
    }

    public void readBasePageParameters() {
        firstUrl = assetDocumentPage.getCurrentUrl();
        winHandleFirst = assetDocumentPage.getCurrentWindowHandle();
        LOG.info("winHandleFirst: " + winHandleFirst);
    }

    public void clickOnBailiiLink(String bailiiLink) {
        readBasePageParameters();
        valueHrefAtribute = assetDocumentPage.bailiiLink(bailiiLink).getAttribute("href");
        assetDocumentPage.bailiiLink(bailiiLink).click();
    }

    public boolean isTheUserSeeLinksToLegalUpdates() {
        assetDocumentPage.waitForPageToLoad();
        return assetDocumentPage.linkToLegalUpdates().isDisplayed();
    }

    public void clickOnLinkToLegalUpdates() {
        readBasePageParameters();
        valueHrefAtribute = assetDocumentPage.linkToLegalUpdates().getAttribute("href");
        assetDocumentPage.waitForPageSourceChangedAfterClick(assetDocumentPage.linkToLegalUpdates());
    }

    public boolean isTheUserSeeHardcodedLinks(String linkText) {
        assetDocumentPage.waitForPageToLoad();
        return assetDocumentPage.hardcodedLink(linkText).isDisplayed();
    }

    public void clickOnHardcodedLink(String linkText) {
        readBasePageParameters();
        valueHrefAtribute = assetDocumentPage.hardcodedLink(linkText).getAttribute("href");
        assetDocumentPage.scrollIntoViewAndClick(assetDocumentPage.hardcodedLink(linkText));
    }

    public boolean isTheUserTakenToTheSelectedResource(String linkText) {
        String secondWinHandle = "";
		if (driver.getWindowHandles().size() == 1) {
			LOG.info("New window is not opened");
			return false;
		}

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            secondWinHandle = winHandle;
        }
        assetDocumentPage.waitForPageToLoad();
        String secondUrl = assetDocumentPage.getCurrentUrl();
		boolean result = false;
		LOG.info("Expected URL: " + linkText);
		LOG.info("Actual   URL: " + secondUrl);
		if (secondUrl.toLowerCase().contains(linkText.toLowerCase())) {
			result = true;
		}
		driver.switchTo().window(secondWinHandle).close();
		driver.switchTo().window(winHandleFirst);
		return result;
    }

    public boolean isTheUserTakenToTheLegalUpdates() {
        String secondUrl = assetDocumentPage.getCurrentUrl();
        return !firstUrl.equals(secondUrl) && secondUrl.contains(valueHrefAtribute);
    }

    public boolean isTheUserSeeCelexLinks(String celexLinkText) {
        assetDocumentPage.waitForPageToLoad();
        return assetDocumentPage.celexLink(celexLinkText).isDisplayed();
    }

    public void clickOnCelexLink(String celexLinkText) {
        readBasePageParameters();
        valueHrefAtribute = assetDocumentPage.celexLink(celexLinkText).getAttribute("href");
        LOG.info("Href attribute = "+ valueHrefAtribute);
        assetDocumentPage.celexLink(celexLinkText).click();
    }

    public boolean isTheUserSeeLinkToWestlawUK(String westlawUkLinkText) {
        assetDocumentPage.waitForPageToLoad();
        return assetDocumentPage.westlawUkLink(westlawUkLinkText).isDisplayed();
    }

    public void clickOnLinkToWestlawUK(String westlawUkLinkText) {
        readBasePageParameters();
        assetDocumentPage.westlawUkLink(westlawUkLinkText).click();
    }

    public void goBackToThePreviousWindow() {
        assetDocumentPage.switchToWindow(winHandleFirst);
    }

    public boolean isTheUserTakenToTheLoginPageInWestlawUkDocument() {
        for (String winHandle : driver.getWindowHandles()) {
            assetDocumentPage.switchToWindow(winHandle);
        }
        assetDocumentPage.waitForPageToLoad();
        return assetDocumentPage.isElementDisplayed(SIGN_ON_BUTTON);
    }

    public boolean isTheUserTakenToTheWestlawUkDocument() {
        for (String winHandle : driver.getWindowHandles()) {
            assetDocumentPage.switchToWindow(winHandle);
        }
        String secondUrl = assetDocumentPage.getCurrentUrl();
        return !firstUrl.equals(secondUrl);

    }

    public void loginWestlawUk() {
        assetDocumentPage.waitForPageToLoad();
        WebElement id = assetDocumentPage.findElement(USERNAME_FIELD);
        id.clear();
        id.sendKeys(USERNAME);
        WebElement pass = assetDocumentPage.findElement(PASSWORD_FIELD);
        pass.clear();
        pass.sendKeys(PASSWORD);
        assetDocumentPage.findElement(SUBMIT_BUTTON).click();
        assetDocumentPage.waitForPageToLoadAndJQueryProcessing();
    }

    public boolean openDocumentInWestlawUK() {

		if (driver.getWindowHandles().size() < 2) {
			LOG.info("New window not opened");
			return false;
		}
        String secondHandle = "";
        for (String winHandle : driver.getWindowHandles()) {
            assetDocumentPage.switchToWindow(winHandle);
            secondHandle = winHandle;
        }
		assetDocumentPage.waitForPageToLoad();
        if (documentBody().isDisplayed()) {
            outPutLink().click();
            assetDocumentPage.waitForPageToLoadAndJQueryProcessing();
            assetDocumentPage.switchToWindow(secondHandle);
            driver.close();
            assetDocumentPage.switchToWindow(winHandleFirst);
            return true;
        } else {
            return false;
        }
    }

    public boolean isTheCasePageIsDisplayedInAssertPage(String casePageText) {
        assetDocumentPage.waitForPageToLoad();
        return assetDocumentPage.casePageText().isDisplayed()
                && assetDocumentPage.casePageText().getText().equals(casePageText);
    }

    public boolean isTheUserTakenToSelectedPartOfTheDocument(String linkText) {
        assetDocumentPage.waitForPageToLoad();
        return  (Boolean) assetDocumentPage.executeScript("function isScrolledIntoView(elem,off) {"
                + " var $elem = $(elem); var $window = $(window); " + "var docViewTop = $window.scrollTop()+off; "
                + "var docViewBottom = docViewTop + $window.height();" + "var elemTop = $elem.offset().top;"
                + "var elemBottom = elemTop + $elem.height();"
                + "return ((elemBottom <= docViewBottom) && (elemTop >= docViewTop));}"
                + "return isScrolledIntoView($(\"h2:contains('" + linkText + "')\"),200)");
    }

    public boolean isTheUserSeeJumpLinksInTheLeftHandSideNavigationPanel(String jumpLinkText) {
        assetDocumentPage.waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(30);
        LOG.info("context", assetDocumentPage.jumpLink(jumpLinkText).getLocation());
        return assetDocumentPage.jumpLink(jumpLinkText).isDisplayed();
    }

    public String clickOnJumpLink(String jumpLinkText) {
        assetDocumentPage.waitForPageToLoad();
        LOG.info("context", assetDocumentPage.jumpLink(jumpLinkText).getLocation());
        assetDocumentPage.waitForPageSourceChangedAfterClick(assetDocumentPage.jumpLink(jumpLinkText));
        return assetDocumentPage.jumpLink(jumpLinkText).getText();
    }

    public boolean isTheFieldInMetadataDisplayed(String text) {
        assetDocumentPage.waitForPageToLoad();
        try {
            assetDocumentPage.metaDataField(text).isDisplayed();
            return true;
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
            return false;
        }
    }

    public boolean isTheUserSeeNameOfFileForDownload() {
        try {
            return assetDocumentPage.nameOfFileForDownload().isDisplayed();
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
            return false;
        }
    }

    public boolean isTheDownloadedPDFDocumentContainHyperlinkToExternalWebSite(String linkHref, String linkText, File docFile) {
        try {
            String bailiiUrlFtomPDF = pdfBoxUtil.extractURLs(docFile.getAbsolutePath()).get(linkText);
            LOG.info("bailiiUrlFtomPDF: " + bailiiUrlFtomPDF);
            LOG.info("linkHref: " + linkHref);
            return linkHref.contains(bailiiUrlFtomPDF);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteFile() {
        fileActions.deleteFile(downloadedFile);
    }

    public boolean isTheEndOfDocumentContainText() {
        return assetDocumentPage.endOfDocument().getText().isEmpty();
    }

    public void scrollToTheBottomOfTheDocument() {
        assetDocumentPage.executeScript("window.scrollTo(0,document.body.scrollHeight);");
    }

    public boolean isTheContentBodyContainEndOfDocument() {
        try {
            assetDocumentPage.endOfDocument();
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isTheUserScrolledToTheTopOfTheDocument() {
        assetDocumentPage.waitForPageToLoad();
        return (Boolean) assetDocumentPage.executeScript("function isScrolledIntoView(elem,off) {"
                + " var $elem = $(elem); var $window = $(window); " + "var docViewTop = $window.scrollTop()+off; "
                + "var docViewBottom = docViewTop + $window.height();" + "var elemTop = $elem.offset().top;"
                + "var elemBottom = elemTop + $elem.height();"
                + "return ((elemBottom <= docViewBottom) && (elemTop >= docViewTop));}"
                + "return isScrolledIntoView($(\"div#co_docHeaderContainer h1.co_title\"),200)");
    }

    public void addTextToTheDocumentPage() {
        assetDocumentPage.executeScript("for (var i = 0; i < 150; i++){$('#co_docContentBody').html('TESTtestTest ' + $('#co_docContentBody').html() )}");
    }

    public boolean isTableOfContentDisplayed() {
        assetDocumentPage.waitForPageToLoad();
        try {
           return assetDocumentPage.isElementDisplayed(TABLE_OF_CONTENTS_SECTION);
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
            return false;
        }
    }

    public boolean isDocumentContainClassWithCaseAssetDoc() {
        assetDocumentPage.waitForPageToLoad();
        boolean result = false;
        try {
            String[] words = assetDocumentPage.caseAssetDocClass().getAttribute("class").split(SEPARATOR);
            for (String word : words) {
                if (word.equals("case-asset-doc")) {
                    result = true;
                    break;
                }
            }
            return result;

        } catch (NoSuchElementException ex) {
            LOG.info("context", ex);
            return false;
        }
    }

    public boolean isTheUserSeeTheValueOfSpecialistCourt() {
        return !assetDocumentPage.valueOfSpecialistCourt().getText().isEmpty();
    }

    public boolean isTheUserTakenToTheInternalDocument(String hrefAtribute) {
    	String secondUrl = assetDocumentPage.getCurrentUrl();
		LOG.info("secondUrl" + secondUrl);
		LOG.info("hrefAtribute" + hrefAtribute);
		return !firstUrl.equals(secondUrl) && secondUrl.contains(webDriverDiscovery.getCurrentRootAddress(true));
    }

    public boolean isTheDocumentContainLink(String linkText) {
        try {

            linkInLinkToThisCaseSection = primarySourceDocumentPage.linkInLinksToThisCaseSection(linkText);

            return true;
        } catch (NoSuchElementException ex) {
            LOG.info("context", ex);
            return false;
        }
    }

    public boolean isTheLinkDisplayedAccodingWithClassNameAndHrefAtribute(String linkText) {
        try {
            WebElement parentLink = primarySourceDocumentPage.parentOflinkInLinksToThisCaseSection(linkText);
            String className = parentLink.getAttribute("class");
            String hrefOfTheLink = linkInLinkToThisCaseSection.getAttribute("href");
            LOG.info("className: " + className);
            LOG.info("hrefOfTheLink: " + hrefOfTheLink);
            if (linkInLinkToThisCaseSection.isDisplayed()) {
                return (className == null ||
                        className.trim().isEmpty()) &&
                        hrefOfTheLink.contains("http");
            } else {
                return className != null && className.equals("co_hideState") && hrefOfTheLink.contains("uk.practicallaw");
            }
        } catch (NoSuchElementException | TimeoutException ex) {
            LOG.info("context", ex);
            return false;
        }
    }

    public boolean isThedownloadedDocumentContainContentRefferingSection(String contentReferringText, File docFile) {
        try {
            String textFromPdf = pdfBoxUtil.extractText(docFile.getAbsolutePath());
            return textFromPdf.contains(contentReferringText);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isTheLinksOfTypeOfDocumentAreSortedAlphabetically(String documentTypeText) {
        List<WebElement> links = primarySourceDocumentPage.listOfLinksByDocumentType(documentTypeText);
        List<String> actualLinkList = new ArrayList<String>();
        List<String> expectedLinkList = new ArrayList<String>();
        for (WebElement link : links) {
            String linkText = link.getText().trim();
            if (!linkText.isEmpty()) {
                actualLinkList.add(linkText);
            }
        }
        expectedLinkList.addAll(actualLinkList);
        Collections.sort(actualLinkList);
        return expectedLinkList.equals(actualLinkList);
    }

    public boolean isTheNumberOfLinksMoreThan(String numberOfLinks) {
        String result = primarySourceDocumentPage.numberOfLinksFoundResult().getText();
        String[] words = result.split(" ");
        numberOfLinksInContentSection = Integer.parseInt(words[0]);
        return Integer.parseInt(words[0]) > Integer.parseInt(numberOfLinks);
    }

    public boolean isTheNumberOfLinksEqualsToTheNumberOfResultsFound() {
        boolean result = true;
        List<WebElement> links = primarySourceDocumentPage.listOfLinksInContentRefferingSection();
        List<String> ar = new ArrayList<String>();
        for (WebElement link : links) {
            if (!link.getText().trim().isEmpty()) {
                ar.add(link.getText());
            }
        }
        if (ar.size() != numberOfLinksInContentSection) {
            result = false;
        }
        return result;
    }


    public boolean isTheJurisdictionsContainLessThanOneJurisdiction(String jurisdictionsText) {
        String[] words = primarySourceDocumentPage.contentOfMetaDataFields(jurisdictionsText).getText().split(",");
        return words.length >= MIN_JURISDICTIONS_COUNT;

    }

    public boolean isTheNumberOfOpenedTabsEqueals(int numberOfOpenedTabs) {
        int i = 0;
        for (String winHandle : assetDocumentPage.getWindowHandles()) {
            assetDocumentPage.switchToWindow(winHandle);
            i++;
        }
        LOG.info("number of opened tubs: " + i);
        return i == numberOfOpenedTabs;
    }

    public boolean isTheHyperlinkOfDownloadedDocumentContainSpecificParameters(String linkText, String text, File docFile) {
        try {
            String hyperlinkFtomPDF = pdfBoxUtil.extractURLs(docFile.getAbsolutePath()).get(linkText);
            LOG.info("hyperlinkFtomPDF: " + hyperlinkFtomPDF);
            return hyperlinkFtomPDF.contains(text);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLinkExistsInThePdfDocument(String linkText, File docFile) {
        try {
            Map<String, String> docLinkNames = pdfBoxUtil.extractURLs(docFile.getAbsolutePath());
            // Can't use List.contains(Object) because pdfBoxUtil.extractURLs can truncate some extracted links
            // due to wrong link area width calculating (maybe bug in PDFBox library)
            for (String docLinkName : docLinkNames.keySet()) {
                if (docLinkName.contains(linkText) || linkText.contains(docLinkName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLinkExistsInThePdfDocumentListItems(String linkText, File docFile) {
        try {
            List<Link> docLinkNames = pdfBoxUtil.extractURLsToLinks(docFile.getAbsolutePath());
            // Can't use List.contains(Object) because pdfBoxUtil.extractURLs can truncate some extracted links
            // due to wrong link area width calculating (maybe bug in PDFBox library)
            for (Link docLinkName : docLinkNames) {
                if (docLinkName.getHref().contains(linkText) || linkText.contains(docLinkName.getHref())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTheOtherProvisionSectionHasStyle(String sectionNameText, String styleText) {
        return !primarySourceDocumentPage.otherProvisionStyle(sectionNameText).getAttribute("class").equals(styleText);
    }

    public void chooseDropdownBox(String text, WebElement element) {
        Select dropDown = new Select(element);
        String selected = dropDown.getFirstSelectedOption().getText();
        if (!selected.equals(text)) {
            List<WebElement> Options = dropDown.getOptions();
            for (WebElement option : Options) {
                if (option.getText().equals(text)) {
                    option.click();
                }
            }
        }
    }

    public boolean isTheBulletsHaveStyle(String styleName) {
        assetDocumentPage.waitForPageToLoad();
        String bulletsStyle = (String) assetDocumentPage.executeScript("return getComputedStyle($('.co_assetList')[0]).listStyleType");
        LOG.info("BulletsStyle: ", bulletsStyle);
        return bulletsStyle.equals(styleName);
    }

    public boolean isTheDoubleLinesHaveStyle(String styleName) {
        assetDocumentPage.waitForPageToLoad();
        String lineStyle = (String) assetDocumentPage.executeScript("return getComputedStyle($('.co_assetList')[0]).borderBottomStyle");
        LOG.info("LineStyle: ", lineStyle);
        return lineStyle.equals(styleName);
    }

    public boolean isTheSpacingBetweenDoubleLinesAndLinksHaveSize(String size) {
        assetDocumentPage.waitForPageToLoad();
        String marginBottom = (String) assetDocumentPage.executeScript("return getComputedStyle($('.co_assetList')[0]).marginBottom");
        String paddingBottom = (String) assetDocumentPage.executeScript("return getComputedStyle($('.co_assetList')[0]).paddingBottom");
        return marginBottom.equals(size) && paddingBottom.equals(size);
    }

    public String getFontSizeOfLink(String linkText) {
        assetDocumentPage.waitForPageToLoad();
        return (String) assetDocumentPage.executeScript("return getComputedStyle($(\".co_assetList a:contains(" + "'"
                + linkText + "'" + ")\")[0]).fontSize");
    }

    public String getFontSizeOfHeader(String headerHame) {
        assetDocumentPage.waitForPageToLoad();
        return (String) assetDocumentPage.executeScript("return getComputedStyle($(\"h2:contains(" + "'" + headerHame + "'"
                + ")\")[0]).fontSize");
    }

    public boolean isTheLinkLocatedOnTheSide(String sideName) {
        assetDocumentPage.waitForPageToLoad();
        String side = (String) assetDocumentPage.executeScript("return getComputedStyle($('#co_RelatedContentPaginationControls')[0]).float");
        return side.equals(sideName);
    }

    public void selectCheckbox(WebElement element) {
        if (element.getAttribute("checked") == null) {// if Checked
            element.click();
        }
    }

    public boolean isTheDownloadedDocumentContainTableOfContent(String linkText, File docFile) {
        try {
            String textFromPdf = pdfBoxUtil.extractText(docFile.getAbsolutePath());
            textFromPdf = textFromPdf.replaceAll("\\n", "").replaceAll("\r", "");
            LOG.info("textFromPdf: ", textFromPdf);
            return textFromPdf.contains(assetDocumentPage.partyNames().getText() + " " + linkText);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getFontSizeOfBullet(String linkText) {
        assetDocumentPage.waitForPageToLoadAndJQueryProcessing();
        return (String) assetDocumentPage.executeScript("return getComputedStyle($(\"a:contains(" + "'" + linkText + "'"
                + ")\").parent()[0]).fontSize");
    }

    public boolean isTheDownloadedDocumentContainRightNumberOfBullets(File docFile)
            throws IOException, BadLocationException {
        assetDocumentPage.waitForPageToLoad();
        List<WebElement> linksInLegalUpdatesSection = assetDocumentPage.listOfLinksInLegalUpdatesSection();
        List<WebElement> linksInLinksToThisPageSection = assetDocumentPage.listOfLinksInLinksToThisPageSection();
        int numberOfLinks = linksInLegalUpdatesSection.size() + linksInLinksToThisPageSection.size();
        String textFromRTF = getTextFromFileWithRTForDOCextension(docFile.getAbsolutePath());

        int court = 0;
        char[] characterArray = textFromRTF.toCharArray();
        for (Character character : characterArray) {
            if (character.equals(BULLET_UNICODE_CODE)) {
                court++;
            }
        }
        return court == numberOfLinks;
    }

    // This method does not return the text of the links!
    public String getTextFromFileWithRTForDOCextension(String path) throws IOException, BadLocationException {
        DefaultStyledDocument styledDoc = new DefaultStyledDocument();
        FileInputStream inStream;
        try {
            inStream = new FileInputStream(path);
            new RTFEditorKit().read(inStream, styledDoc, 0);
            inStream.close();
        } catch (FileNotFoundException e) {
            LOG.info("FileNotFoundException: ");
            e.printStackTrace();
        }
        return styledDoc.getText(0, styledDoc.getLength());
    }

    /**
     * Get text from RTF file. It is also acceptable for "Word document" format which uses on PL+, because it looks like
     * renamed RTF file with ".doc" extension.
     * WARNING: method does not return any special symbols (such as bullets).
     *
     * @param rtfFile File object with RTF document
     * @return String with text of given document or an empty string if exception was occurred
     */
    public String getTextOnlyFromRtf(File rtfFile) {
        try (InputStream is = new FileInputStream(rtfFile)) {
            IRtfSource source = new RtfStreamSource(is);
            IRtfParser parser = new StandardRtfParser();
            StringTextConverter listener = new StringTextConverter();
            parser.parse(source, listener);
            is.close();
            return listener.getText();
        } catch (IOException e) {
            LOG.info("Error of parsing tha file: " + rtfFile.getAbsolutePath(), e);
            return "";
        }
    }

    public boolean isTheSourceDocumentRemainsOpen(String guid) {
        String winHandleFirst = driver.getWindowHandle();
        Boolean isOpen = false;
        for (String handle : driver.getWindowHandles()) {
            assetDocumentPage.switchToWindow(handle);
            String currentUrl = driver.getPageSource();
            if (currentUrl.contains(guid)) {
                try {
                    if (assetDocumentPage.contentBody().isEnabled()) {
                        isOpen = true;
                        break;
                    }
                } catch (PageOperationException e) {
                    LOG.info("There is no document body on the page");
                    break;
                }
            }
        }
        assetDocumentPage.switchToWindow(winHandleFirst);
        return isOpen;
    }

    public String getColorFromPDFLinks(File docFile, String textToFindFromPdf) throws IOException {
        assetDocumentPage.waitForPageToLoad();
        Color color = pdfBoxUtil.getFontColorFromPdf(docFile.getAbsolutePath(), textToFindFromPdf, 0);
        if (color.getRed() == MINIMUM_C0LOR_INTENSITY && color.getGreen() == MINIMUM_C0LOR_INTENSITY
                && color.getBlue() == MAXIMUM_COLOR_INTENSITY) {
            return "blue";
        } else if (color.getRed() == MAXIMUM_COLOR_INTENSITY && color.getGreen() == MINIMUM_C0LOR_INTENSITY
                && color.getBlue() == MINIMUM_C0LOR_INTENSITY) {
            return "red";
        } else if (color.getRed() == MINIMUM_C0LOR_INTENSITY && color.getGreen() == MINIMUM_C0LOR_INTENSITY
                && color.getBlue() == MINIMUM_C0LOR_INTENSITY)
            return "black";
        else {
            LOG.info("fontColorFromPdf: ", color.toString());
            return color.toString();
        }
    }

    public boolean isLegislationLinkPresent(String legislationLinkText) {
        try {
            return primarySourceDocumentPage.legislationLink(legislationLinkText).isDisplayed();
        } catch (TimeoutException poe) {
            LOG.info("context", poe);
            return false;
        }
    }

    public boolean isTheNumberOfOpenedTubsEqueals(int numberOfOpenedTubs) {
        int i = 0;
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            i++;
        }
        return i == numberOfOpenedTubs;
    }
   
}
