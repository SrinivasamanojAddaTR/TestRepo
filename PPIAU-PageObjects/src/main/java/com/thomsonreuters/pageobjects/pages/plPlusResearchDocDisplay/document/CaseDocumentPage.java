package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.common.CommonMethods;

/**
 * This page object is to identify the Case Document elements and depicts the
 * page functionality.
 * <p/>
 */
public class CaseDocumentPage extends DocumentDisplayAbstractPage {

    private static final By PDF_IMAGE = By.className("co_pdfIcon");
    private static final By LEFT_NAVIGATION_COLUMN = By.className("kh_toc-content");
    private static final By DOCUMENT_IN_PDF = By.xpath(".//div[@id='co_docContentBody']//a[@type = 'application/pdf']");
    private static final By SHOW_AND_HIDE_LINK = By.id("co_ExpandCollapseLegislationAnnotationSection");
    private static final By ANNOTATIONS_SECTION = By.id("co_AnnotationDocumentSource");
    private static final By CONTENT_COLUMN = By.id("co_docContentBody");
    private static final By STATUS_ICON = By.xpath(".//div[@id='co_docContentMetaInfo']//img");
    private static final By STATUS_DESCRIPTION = By.xpath(".//div[@id='co_docContentMetaInfo']/div[@class='co_greenStatus']");
    private static final By PARTY_NAMES_IN_CASELAW = By.className("co_title");
    private static final By ALIAS_PARTY_NAMES = By.xpath(".//*[contains(@class, 'co_title')]/following-sibling::div[contains(text(), '')]");
    private static final By PDF_DOWNLOAD_OPTION = By.xpath("//a[@id='deliveryLinkRowDownloadPDF']");
	private static final By PDF_DOWNLOAD_TOOLTIP = By.xpath("//div[@id='co_DownloadPDFWidget']/div[@class='tooltip-left']");
    private static final String PL_DOCUMENT_LINK_XPATH = "//*[starts-with(@id,'co_ukReferences')]//a[contains(text(),'%s')]";
    private static final String PL_PUBLISHED_LINK_XPATH = "//*[starts-with(@id,'co_ukReferences')]//a[contains(text(),'%s')]/following-sibling::span[contains(text(),'Published')][@class='co_redStatus']";

    private CommonMethods commonMethods = new CommonMethods();

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CaseDocumentPage.class);
    private By pdfDownloadLinkLocator = By.cssSelector("");

    public WebElement pdfImage() {
        return waitForExpectedElement(PDF_IMAGE);
    }

    public WebElement columnForTheLeftHandNavigation() {
        return waitForExpectedElement(LEFT_NAVIGATION_COLUMN);
    }

    public WebElement documentInPdf() {
        return waitForExpectedElement(DOCUMENT_IN_PDF);
    }

    public WebElement footnotesSection(String section) {
		return waitForExpectedElement(By.xpath("//div[@id='co_footnoteSection']/*[contains(text(), '" + section + "')]"));
	}
	
	public WebElement footnotesNumber(String section) {
		return waitForExpectedElement(By.xpath("//*[contains(text(), '" + section + "')]/following-sibling::*/*[@class='co_footnoteNumber']"));
	}

	public WebElement footnotesBody(String section) {
		return waitForExpectedElement(By.xpath("//*[contains(text(), '" + section + "')]/following-sibling::*/*[@class='co_footnoteBody']"));
	}

    public WebElement annotationsLink(String annotationsLinkText) {
        return waitForExpectedElement(By.linkText(annotationsLinkText));
    }

    public WebElement annotationsText(String annotationsText) {
        return waitForExpectedElement(By.xpath(".//h2[contains(text(),'" + annotationsText + "')]"));
    }

    public WebElement showAndHideLink() {
        return waitForExpectedElement(SHOW_AND_HIDE_LINK);
    }

    public WebElement annotationsSection() {
        return waitForExpectedElement(ANNOTATIONS_SECTION);
    }

    public WebElement contentColumn() {
        return waitForExpectedElement(CONTENT_COLUMN);
    }

    public WebElement statusIcon() {
        return waitForExpectedElement(STATUS_ICON);
    }

    public WebElement statusDescription() {
        return waitForExpectedElement(STATUS_DESCRIPTION);
    }

    public WebElement partyNamesInCaseLaw() {
        return waitForExpectedElement(PARTY_NAMES_IN_CASELAW);
    }

    public WebElement aliasPartyNames() {
        return waitForExpectedElement(ALIAS_PARTY_NAMES);
    }

    public WebElement menuItem(String text) {
        return waitForExpectedElement(By.xpath(".//*[@class='kh_toc-content']//*[contains(text(),'" + text + "')]"));
    }

    public WebElement dateText() {
		return waitForExpectedElement(By.xpath(".//*[@id='co_docContentMetaInfo']"));
	}

	public WebElement starPageInTitle() {
		return waitForExpectedElement(By.xpath("//div[@id='co_docHeaderContainer']//span[@class='co_starPage']"));
	}

    public CaseDocumentPage() {
    }

    /**
     * This method verify the given star paging word is present or not in the
     * case law report and returns boolean vlaue accordingly.
     *
     * @param starPagingWord
     * @return boolean
     */
    public boolean isStarPagingWordPresent(String starPagingWord) {
        try {
            waitForElementPresent(By.xpath(".//span[@class='co_starPage'] [contains(text(),'" + starPagingWord + "')]"));
            return true;
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return false;
    }

    /**
     * This method is to verify the Download as PDF link is present or not and
     * returns the boolean value accordingly.
     *
     * @return boolean
     */
    
    public boolean isDownloadAsPDFLinkDisplayed() {
    	return isElementDisplayed(pdfDownloadLinkLocator);
    }
    

    /**
     * This method does the Clicking on Download As PDF link.
     */
    public void clickOnDownloadAsPDFLink() {
        getDownloadAsPDFLink().click();
    }

    /**
     * This method gets the Download As PDF link webelement.
     *
     * @return WebElement
     */
    private WebElement getDownloadAsPDFLink() {
        try {
            return waitForElementPresent(pdfDownloadLinkLocator);
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the PDF link on case document." + te.getMessage());
        }
    }

    /**
     * This method finds the given judgement section details on Case Judgment
     * document and returns the size of the section elements.
     *
     * @param judgmentSectionDetailsLocator
     * @return int
     */
    public int getJudgmentSectionDetailsList(By judgmentSectionDetailsLocator) {
        try {
            return waitForExpectedElements(judgmentSectionDetailsLocator).size();
        } catch (TimeoutException te) {
            LOG.info("context", te);
            logger.warn("Exceeded time to find the judgmentSectionDetailsLocator." + te.getMessage());
            return Collections.EMPTY_LIST.size();
        }
    }

    /**
     * This method verifies the party names are displayed on the Case judgment
     * document and returns the boolean value accordingly.
     *
     * @param partyNames
     * @return boolean
     */
    public boolean isJudgmentPartyNamesDisplayed(String partyNames) {
        try {
            return waitForExpectedElement(By.cssSelector("")).isDisplayed(); // Need
            // to
            // amend
            // the
            // css.
        } catch (TimeoutException te) {
            LOG.info("context", te);
            return false;
        }
    }

    /**
     * This method verifies the party names are displayed on the left hand side
     * of Case judgment document and returns the boolean value accordingly.
     *
     * @param pageAttribute
     * @return boolean
     */
    public boolean isDisplayedOnLeftHandSide(String pageAttribute) {
        try {
            return waitForExpectedElement(By.cssSelector("")).isDisplayed(); // Need
            // to
            // amend
            // the
            // css.
        } catch (TimeoutException te) {
            LOG.info("context", te);
            return false;
        }
    }

    public boolean isDateInValidFormat(String s, String dateFormat) {
        return commonMethods.isDateInValidFormat(s, dateFormat);
    }


    public WebElement metadataCaseStatusIcon(String status) {

		return waitForExpectedElement(By
				.xpath("//*[@id='co_docContentMetaInfo']/div[contains(@class,'co_statusIcon_" + status + "_large')]"));

    }

    public WebElement metadataCaseStatusText(String statustext) {

        String text = "'" + statustext + "'";
        if (statustext.contains("'")) {
            statustext = "\"" + statustext + "\"";
            text = statustext;

        }

        return waitForExpectedElement(By.xpath("//*[@id='co_docContentMetaInfo']//div[contains(@class,'co_docContentMetaInfoStatusText')][contains(text()," + text + ")]"));
    }


    public WebElement metadataHeadings(String heading) {

        return waitForExpectedElement(By.xpath("//*[@id='co_docContentMetaInfo']//b[contains(text(),'" + heading + "')]"));
    }

    public WebElement metadataCourtData(String court) {

        return waitForExpectedElement(By.xpath("//*[@class='co_docContentMetaInfoCourt']//span[contains(text(),'" + court + "')]"));
    }

    public WebElement metadataJudgmentDateData(String date) {

        return waitForExpectedElement(By.xpath("//*[@class='co_docContentMetaInfoDate']//span[contains(text(),'" + date + "')]"));
    }

	public WebElement metadataReportCitationData(String citation) {

		return waitForExpectedElement(By.xpath("//*[@class='co_docContentMetaInfoReportCitation']//div[text()='"
				+ citation + "']"));
	}


    public WebElement metadataSubjectData(String subject) {

        return waitForExpectedElement(By.xpath("//*[@class='co_docContentMetaMainSubject']//span[contains(text(),'" + subject + "')]"));
    }

    public WebElement metadataRelatedSubjectData(String related) {

        return waitForExpectedElement(By.xpath("//*[@class='co_docContentMetaSubjects']//span[contains(text(),'" + related + "')]"));
    }

    public WebElement metadataKeywordsData(String keywords) {

        return waitForExpectedElement(By.xpath("//*[@class='co_docContentMetaKeywords']//span[contains(text(),'" + keywords + "')]"));
    }

    public WebElement metadataJudgeData(String judge) {

        return waitForExpectedElement(By.xpath("//*[@class='co_docContentMetaInfoJudge']//span[contains(text(),'" + judge + "')]"));
    }

    public WebElement metadataCounselData(String judge) {

        return waitForExpectedElement(By.xpath("//*[@class='co_docContentMetaInfoCounsel']//span[contains(text(),'" + judge + "')]"));
    }

    public WebElement metadataSolicitorData(String solicitor) {

        return waitForExpectedElement(By.xpath("//*[@class='co_docContentMetaInfoSolicitor']//span[contains(text(),'" + solicitor + "')]"));
    }

    public WebElement metadataWhereReportedData(String whereReported) {

        return waitForExpectedElement(By.xpath("//*[@class='co_docContentMetaInfoWhereReported']//div[contains(text(),'" + whereReported + "')]"));
    }


    public WebElement metadataWhereReportedLink(String link) {

        return waitForExpectedElement(By.xpath("//*[@class='co_docContentMetaInfoWhereReported']//a[contains(text(),'" + link + "')]"));
    }


	public WebElement judgeNames() {
		return findChildElement(contentBody(), By.xpath("//div[contains(@class, 'co_judge')]"));
	}

	public WebElement lawReportDates() {
		return findChildElement(contentBody(), By.xpath("//div[contains(@class, 'co_date')]"));
	}

	public WebElement introductionText(String text) {
		return findChildElement(contentBody(),
				By.xpath("//*[@class='co_paragraph']/em[contains(text(), '" + text + "')]"));
	}

	public WebElement textInSubsection(String subsection, String text) {
		return waitForExpectedElement(By.xpath(".//*[contains(text(),'" + subsection
				+ "')]/following-sibling::div//*[contains(text(),'" + text + "')]"));
	}
	
	public WebElement caseDocumentLogo(){
		return waitForExpectedElement(By.xpath("//*[@class='co_docLawReportsLogo']/img"));
	}
	
	public WebElement titleCaseNo() {
		return findChildElement(contentBody(), By.xpath("//*[contains(@class, 'co_titlecaseno')]"));
	}

	public WebElement reports() {
		return findChildElement(contentBody(), By.xpath("//*[contains(@class, 'co_report')]"));
	}
	
	public WebElement othername() {
		return findChildElement(contentBody(), By.xpath("//*[contains(@class, 'co_othername')]"));
	}
	
	public WebElement shipname() {
		return findChildElement(contentBody(), By.xpath("//*[contains(@class, 'co_shipname')]"));
	}
	
	public List<WebElement> titleCaseNumbers() {
		return contentBody().findElements(By.xpath("//*[contains(@class, 'co_titlecaseno')]"));
	}
	
	public WebElement footnotesSectionEntry(String entry){
		return findElement(By.xpath(".//*[@id='co_footnoteSection']/div[" + entry + "]/*[@class='co_footnoteBody']"));
	}
	
	public boolean isTheFootnotesEntryContainsLink(String entry) {
		return footnotesSectionEntry(entry).findElements(By.tagName("a")).isEmpty();
	}

    public boolean isCasePDFDownloadOptionPresent() {
        return isElementDisplayed(PDF_DOWNLOAD_OPTION);
    }

    public WebElement casePDFDownloadOption() {
        return waitForExpectedElement(PDF_DOWNLOAD_OPTION);
    }

	public WebElement pdfDownloadTooltip() {
		return waitForElementVisible(PDF_DOWNLOAD_TOOLTIP);
	}
	
	
	
	    /**
     * object representing links to individual PL documents
     */

    public WebElement plDocumentLinks(String name) {
        return waitForExpectedElement(By.xpath("//*[starts-with(@id,'co_ukReferences')]//a[contains(text(),'" + name + "')]"));
    }

    public Boolean isPLDocumentLinkDisplayed(String name) {
        return isElementDisplayed(By.xpath(String.format(PL_DOCUMENT_LINK_XPATH, name)));
    }

    /**
     * object representing link to individual PL document has maintained status in green text
     */

    public WebElement plDocumentLinkWithMaintainedStatus(String name) {
        return waitForExpectedElement(By.xpath("//*[starts-with(@id,'co_ukReferences')]//a[contains(text(),'" + name + "')]/following-sibling::span[contains(text(),'Maintained')][@class='co_greenStatus']"));
    }

	public Boolean isPLStatusDisplayed(String name, String status, String colour) {
		return isElementDisplayed(By.xpath("//*[starts-with(@id,'co_ukReferences')]//a[contains(text(),'" + name
				+ "')]/following-sibling::span[contains(@class, 'co_" + colour + "Status') and contains(text(),'"
				+ status + "')]"));
	}

    /**
     * object representing link to individual PL document with Published date in red text
     */

    public WebElement plDocumentLinkWithPublishedStatus(String name) {
        return waitForExpectedElement(By.xpath("//*[starts-with(@id,'co_ukReferences')]//a[contains(text(),'" + name + "')]/following-sibling::span[contains(text(),'Published')][@class='co_redStatus']"));
    }

    public Boolean isPLPublishedStatusDisplayed(String name) {
        return isElementDisplayed(By.xpath(String.format(PL_PUBLISHED_LINK_XPATH, name)));
    }

    public List<WebElement> allPLHeaders() {
        return waitForExpectedElements(By.xpath("*//div[@id='co_ukReferences_practicalLaw']//h2[starts-with(@class,'doc_headText co_ukReferenceGroupCaption')]"));
    }

    /**
     * document dropdown link on breadcrumb - which then clicked displays a list of previous documents viewed
     */

    public WebElement documentDropdownLink() {
        return waitForExpectedElement(By.xpath("//*[starts-with(@class,'co_dropDownAnchor co_dropDownButton')][contains(text(),'Document')]"));
    }

    /**
     * individual document link within document dropdown option on breadcrumb
     */

    public WebElement priorDocumentLink(String name) {
        return waitForExpectedElement(By.xpath("//*[@id='coid_browseHeaderContent_smartBreadCrumbTrail']//a[contains(text(),'" + name + "')]"));
    }

}