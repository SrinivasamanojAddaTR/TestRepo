package com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document;

import java.util.Arrays;
import java.util.List;

import com.thomsonreuters.driver.exception.PageOperationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static java.lang.String.format;

/**
 * Created by u4400015 on 20/06/2016.
 */

/**
 * Page representing objects on Case Analysis Page
 */


public class CaseAnalysisPage extends DocumentDisplayAbstractPage {

    /**
     * Case name at top of case analysis
     */

	private static final By CASE_DIGEST_HEADER_XPATH = By.xpath("//*[@id='co_docContentBody']//h2[contains(text(),'Case Digest')]");
	private static final By RELATED_CASES_HEADER_XPATH = By.xpath("//*[@id='co_docContentBody']//h2[contains(text(),'Related Cases')]");
	private static final By GRAPHICAL_HISTORY_BUTTON = By.id("co_ukGraphicalHistoryLink");
	private static final String COMPLEX_APPELLATE_HISTORY_MESSAGE = "//span[contains(@class,'co_ukAppellateHistoryComplexText')]";
	private static final String ELEMENT_XPATH = "//div[@id='%s']/div[%s]/div%s";
	private static final String CLASS = "class";
	private static final String WIDGET_HEADER = ".//*[contains(@id, 'co_ukReference')]/h2[contains(text(), '%s')]";
	private static final String WIDGET_HEADER_PATTERN = WIDGET_HEADER + "/following-sibling::div[%s]";
	private static final String SUBSECTION_HEADER_XPATH = "//*[@id='co_docContentBody']//h3[contains(text(),'%s')]";
	private static final String SHOW_CASE_SUMMARIES_XPATH = "//div[@id='%s']/h2/span/input";
	private static final String SORTING_SELECT_XPATH = "//div[@id='%s']/h2/span/select";

	public enum RelatedContentSections {
		RELATED_CASES("related case", "co_ukReferences_relatedCases"),
		KEY_CASES_CITED("key cases cited entry", "co_ukReferences_keyCasesCited"),
		ALL_CASES_CITED("all cases cited entry", "co_ukReferences_allCasesCited"),
		KEY_CASES_CITING("key cases citing entry", "co_ukReferences_keyCasesCiting"),
		ALL_CASES_CITING("all cases citing entry", "co_ukReferences_allCasesCiting"),
		KEY_LEGISLATION_CITED("key legislation cited entry", "co_ukReferences_keyLegislationCited"),
		LEGISLATION_CITED("legislation cited entry", "co_ukReferences_legislationCited");

		public final String description;
		public final String id;

		private RelatedContentSections(String description, String id) {
			this.description = description;
			this.id = id;
		}

		public static RelatedContentSections getByDescription(String description) {
			return Arrays.stream(RelatedContentSections.values()).filter(section -> section.description.equals(description)).findFirst()
					.orElseThrow(() -> new PageOperationException(format("Related content sections by description %s not available", description)));
		}
	}

    public WebElement caseName(String name) {

        return waitForExpectedElement(By.xpath("//*[@id='co_docHeaderContainer']//h1[contains(text(),'" + name + "')]"));
    }


    /**
     * Case Digest heading at top of case analysis
     */

    public WebElement caseDigestHeader() {

		return waitForExpectedElement(CASE_DIGEST_HEADER_XPATH);

    }

	public boolean isCaseDigestHeaderPresent() {
		return isExists(CASE_DIGEST_HEADER_XPATH);
	}

	public boolean isCaseDigestHeaderDisplayed() {
		return isElementDisplayed(CASE_DIGEST_HEADER_XPATH);
	}

	public boolean isRelatedCasesHeaderPresent() {
		return isExists(RELATED_CASES_HEADER_XPATH);
	}


    /**
     * heading within case digest which in turn within case analysis
     */

	public WebElement subsectionHeader(String subsection) {

		return waitForExpectedElement(By.xpath(format(SUBSECTION_HEADER_XPATH, subsection)));
    }

	public boolean isSubsectionHeaderDisplayed(String subsection) {

		return isElementDisplayed(By.xpath(format(SUBSECTION_HEADER_XPATH, subsection)));
	}

    /**
     * links within case digest
     */


    public WebElement digestLink(String text) {

		return waitForElementVisible(By.xpath("//*[@id='co_docContentBody']//a[contains(text(),'" + text + "')]"));
    }


    /**
     * message displayed to the user when the case analysis does not contain a digest
     */

    public WebElement noDigestMessage() {

        return waitForExpectedElement(By.xpath("//*[@id='co_docContentBody']//div[contains (text(),'There is no digest available for this case')]"));
    }

    /**
     * heading for appellate history section
     */

    public WebElement appellateHistoryHeading() {

        return waitForExpectedElement(By.xpath("//*[@id='co_docContentBody']//h2[contains(text(),'Appellate History')]"));
    }

    /**
     * individual judgment date within appellate history section
     */

    public WebElement appellateHistoryJudgmentDate(String date) {

		return waitForExpectedElement(
				By.xpath("//*[@class='co_ukAppellateHistoryDate ng-binding'][contains(text(),'" + date + "')]"));

    }

    /**
     * individual judgment date within appellate history section by position (the class identifies appellate history by First, Middle, Last - all
     * rows after first and before last will be identified by middle - consider how to identify one within two middle sections
     */

    public WebElement appellateHistoryJudgmentDateByPosition(String position, String text) {

        return waitForExpectedElement(By.xpath("//*[@class='co_ukAppellateHistory'" + position + "'Row']/preceding-sibling::*//div[contains(text(),'" + text + "')]"));
    }


    /**
     * individual casename within appellate history section (a link)
     */

    public WebElement appellateHistoryCaseName (String name) {

		return waitForExpectedElement(By.xpath("//*[@id='co_docContentBody']//a[contains(text(),'" + name + "')]"));
    }

    /**
     * individual case treatment within appellate history section - only useful if you do not need
     * to identify which case is being referred to
     */

    public WebElement appellateHistoryTreatmentText(String treatment) {

		return waitForExpectedElement(
				By.xpath("//*[@class='co_ukReferenceOutlineEffect ng-binding'][contains(text(),'" + treatment + "')]"));
    }

    /**
     * object representing an individual case name within appellate history by it's position
     */


    public WebElement appellateHistoryIndividualCaseNameText(String position, String text) {

        return waitForExpectedElement(By.xpath("//*[@class='co_ukAppellateHistory" + position + "Row']//a[contains(text(),'" + text + "')]"));
    }

    /**
     * object representing an entire individual case (excluding the judgment date) - so this grabs an object
     * which includes treatment/name/court/citation/subjects
     */

    public WebElement appellateHistoryIndividualCaseText(String position, String text) {

        return waitForExpectedElement(By.xpath("//*[@class='co_ukAppellateHistory" + position + "Row']//a[contains(text(),'" + text + "')]"));
    }



    /**
     * object representing the entire appellate history table including the judgment dates
     */

    public WebElement appellateHistoryTable() {

        return waitForExpectedElement(By.xpath("//*[@class='co_ukAppellateHistoryTable']"));
    }

    public List<WebElement> appellateHistoryEntries(String followingXpath) {

        return waitForExpectedElements(By.xpath("//div[starts-with(@class,'co_ukAppellateHistoryRow')]" + followingXpath));
    }


    public By documentWidget(String widget){
    	String xpath = format(WIDGET_HEADER,  widget);
		return By.xpath(xpath);   	
    }
    
	public List<WebElement> documentStatusElements(String className) {
		return findElements(By.xpath("//*[@id='co_ukReferences_appellateHistory']//*[contains(@class, '" + className
				+ "')]"));
	}

	public List<WebElement> appellateHistoryReferences() {
		return findElements(By.xpath(".//*[@id='co_ukReferences_appellateHistory']//*[contains(@class, 'co_ukReferenceCaption')]/div"));
	}
	
	public WebElement rowStatusTextInAppellateHistory(int entry) {
		return waitForExpectedElement(By.xpath(".//*[contains(@class, 'co_ukAppellateHistoryRow')][" + entry
				+ "]//*[contains(@class, 'co_ukReferenceStatusText')]"));
	}
	
	public List<WebElement> statusIconsInAppellateHistory() {
		return findElements(By
				.xpath(".//*[contains(@class, 'co_ukAppellateHistoryRow ')]//*[contains(@class, 'co_ukReferenceStatusIcon')]"));
	}
	
	public List<WebElement> statusTextInAppellateHistory() {
		return findElements(By
				.xpath(".//*[contains(@class, 'co_ukAppellateHistoryRow ')]//*[contains(@class, 'co_ukReferenceStatusText')]"));
	}

	public WebElement graphicalHistoryButton() {
		return waitForExpectedElement(GRAPHICAL_HISTORY_BUTTON);
	}

	public boolean isGraphicalHistoryButtonDisplayed() {
		return isElementDisplayed(GRAPHICAL_HISTORY_BUTTON);
	}

	public boolean isComplexAppellateHistoryMessageDisplayed() {
		if (isExists(By.xpath(COMPLEX_APPELLATE_HISTORY_MESSAGE))) {
			return waitForElementPresent(By.xpath(COMPLEX_APPELLATE_HISTORY_MESSAGE)).isDisplayed();
		} else {
			return false;
		}

	}

	public WebElement complexAppellateHistoryMessageGraphicalHistoryLink() {
		return waitForExpectedElement(By.xpath(COMPLEX_APPELLATE_HISTORY_MESSAGE + "/following-sibling::a"));
	}

	public WebElement relatedCaseCaption(String section, int entry) {
		String xpath = format(ELEMENT_XPATH, RelatedContentSections.getByDescription(section).id, entry, "/div[@class='co_ukReferenceCaption']");
		return waitForExpectedElement(By.xpath(xpath));
	}

	public WebElement legislationCitedCaption(String section, int entry) {
		String xpath = format(ELEMENT_XPATH, RelatedContentSections.getByDescription(section).id, entry, "/div");
		return waitForExpectedElement(By.xpath(xpath));
	}

	public WebElement relatedCaseCaptionLink(String section, int entry) {
		return findChildElement(relatedCaseCaption(section, entry), By.tagName("a"));
	}

	public WebElement legislationCitedCaptionLink(String section, int entry) {
		return findChildElement(legislationCitedCaption(section, entry), By.tagName("a"));
	}

	public boolean isLegislationCitedCaptionLinkExists(String section, int entry) {
		String xpath = format(ELEMENT_XPATH, RelatedContentSections.getByDescription(section).id, entry,
				"/div/a");
		return isElementDisplayed(By.xpath(xpath));
	}

	public boolean isRelatedCaseCaptionLinkPresent(String section, int entry) {
		return !relatedCaseCaption(section, entry).findElements(By.tagName("a")).isEmpty();
	}

	public WebElement relatedCaseStatusIcon(String section, int entry) {
		String xpath = format(ELEMENT_XPATH, RelatedContentSections.getByDescription(section).id, entry,
				"/div[contains(@class, 'co_ukReferenceStatusIcon')]");
		return waitForExpectedElement(By.xpath(xpath));
	}

	public String relatedCaseStatusIconClass(String section, int entry) {
		return relatedCaseStatusIcon(section, entry).getAttribute(CLASS);
	}

	public WebElement relatedCaseStatus(String section, int entry) {
		String xpath = format(ELEMENT_XPATH, RelatedContentSections.getByDescription(section).id, entry,
				"/div[@class='co_ukReferenceInfo']/span[contains(@class, 'co_ukReferenceStatusText')]");
		return waitForExpectedElement(By.xpath(xpath));
	}

	public String relatedCaseStatusClass(String section, int entry) {
		return relatedCaseStatus(section, entry).getAttribute(CLASS);
	}

	public WebElement caseReferenceMark(String section, int entry) {
		String xpath = format(ELEMENT_XPATH, RelatedContentSections.getByDescription(section).id, entry, "/div[@class='co_ukReferenceEffect']");
		return waitForElementVisible(By.xpath(xpath));
	}

	public WebElement caseReferenceTopMark(String section, int entry) {
		String xpath = format(ELEMENT_XPATH, RelatedContentSections.getByDescription(section).id, entry, "/div[@class='co_ukReferenceTopEffect']");
		return waitForElementVisible(By.xpath(xpath));
	}

	public boolean isCaseReferenceTopMarkDisplayed(String section, int entry) {
		String xpath = format(ELEMENT_XPATH, RelatedContentSections.getByDescription(section).id, entry, "/div[@class='co_ukReferenceTopEffect']");
		return isElementDisplayed(By.xpath(xpath));
	}

	public WebElement relatedCaseMetadata(String section, int entry) {
		String xpath = format(ELEMENT_XPATH, RelatedContentSections.getByDescription(section).id, entry,
				"/div[@class='co_ukReferenceInfo']/span[2]");
		return waitForExpectedElement(By.xpath(xpath));
	}

	public WebElement relatedCaseSubjects(String section, int entry) {
		String xpath = format(ELEMENT_XPATH, RelatedContentSections.getByDescription(section).id, entry,
				"/div[@class='co_ukReferenceInfo']/span[3]");
		return waitForExpectedElement(By.xpath(xpath));
	}

	public WebElement referenceSummary(String section, int entry) {
		String xpath = format(ELEMENT_XPATH, RelatedContentSections.getByDescription(section).id, entry, "/div[@class='co_ukReferenceSummary']");
		return waitForElementVisible(By.xpath(xpath));
	}

	public boolean isReferenceSummaryDisplayed(String section, int entry) {
		String xpath = format(ELEMENT_XPATH, RelatedContentSections.getByDescription(section).id, entry, "/div[@class='co_ukReferenceSummary']");
		return isElementDisplayed(By.xpath(xpath));
	}

	public WebElement showCaseSummariesButton(String section) {
		return waitForElementVisible(By.xpath(format(SHOW_CASE_SUMMARIES_XPATH, RelatedContentSections.getByDescription(section).id)));
	}

	public Select sectionSortingDropdown(String section) {
		return new Select(sectionSortingDropdownElement(section));
	}

	public boolean isRelatedCasesSortingDropdownDisplayed(String section) {
		return isElementDisplayed(By.xpath(format(SORTING_SELECT_XPATH, RelatedContentSections.getByDescription(section).id)));
	}

	public WebElement sectionSortingDropdownElement(String section) {
		return waitForElementVisible(By.xpath(format(SORTING_SELECT_XPATH,
				RelatedContentSections.getByDescription(section).id)));
	}

	public List<WebElement> commentaryReferencesTitles(String widget) {
		String xpath = format(WIDGET_HEADER, widget)
				+ "/following-sibling::div//*[contains(@class, 'co_ukReferenceCaption')]";
		return findElements(By.xpath(xpath));
	}

	public List<WebElement> commentaryReferencesSummaries(String summary) {
		return findElements(By.xpath(".//*[@class='co_ukCommentaryReferenceSummary']//*[contains(text(), '" + summary
				+ "')]"));
	}
	
	public List<WebElement> commentaryReferencesRows(String widget){
		String xpath = format(WIDGET_HEADER, widget) + "/following-sibling::div";
		return findElements(By.xpath(xpath));
	}

	public WebElement commentaryReferencesTitle(int entry, String widget) {
		String xpath = format(WIDGET_HEADER_PATTERN, widget,entry) + "//div[contains(@class, 'co_ukReferenceCaption')]";
		return findElement(By.xpath(xpath));
	}

	public WebElement commentaryReferencesCitationOrChapter(int entry, String widget) {
		String xpath = format(WIDGET_HEADER_PATTERN, widget,entry) + "//*[contains(@class, 'co_ukCommentaryReferenceInfo')]/span[1]";
		return findElement(By.xpath(xpath));
	}

	public WebElement commentaryReferencesJournalSubject(int entry, String widget) {
		String xpath = format(WIDGET_HEADER_PATTERN, widget,entry) + "//*[contains(@class, 'co_ukCommentaryReferenceInfo')]/span[2]";
		return findElement(By.xpath(xpath));
	}

	public WebElement commentaryReferencesBookSection(int entry, String widget) {
		String xpath = format(WIDGET_HEADER_PATTERN, widget,entry) + "//*[contains(@class, 'co_ukCommentaryReferenceInfo')]//a";
		return findElement(By.xpath(xpath));
	}

	public WebElement commentaryReferencesSummary(int entry) {
		return waitForExpectedElement(By.xpath(".//*[@id='co_ukReferences_journalArticles']/div[" + entry
				+ "]//*[@class='co_ukCommentaryReferenceSummary']"));
	}

 }
