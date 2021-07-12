package com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.lang.WordUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

/**
 * This is a page representing the glossary definition page - which includes
 * a tabbed alphabetical list from which the user can select glossary terms
 */

public class GlossaryPage extends AbstractPage {

    /**
     * This is the heading Glossary and includes the selected glossary term which appears on the main page (not part of the list
     * in the left hand pane)
     */
    public WebElement glossaryHeading() {
        return waitForElementVisible(By.id("co_docHeaderContainer"));
    }

	public WebElement glossaryPreHeading() {
		return waitForElementVisible(By.className("co_keyLegalConceptPreHeader"));
	}

    public List<WebElement> listOfTabbedAlphabets() {
        return findElements(By.cssSelector("ul.co_glossary_tab li"));
    }

    public List<String> alphabetListToString() {
        List<String> stringList = new ArrayList<>();
        for (WebElement element : listOfTabbedAlphabets()) {
            stringList.add(element.getText().trim());
        }
        return stringList;
    }


    /**
     * This is an object representing the glossary page and the glossary heading on that main page e.g. 18 to 25 trust
     */

    public WebElement glossaryTermHeader() {
        return waitForExpectedElement(By.xpath("//div[@id='co_docHeaderContainer']/h1[@class='co_title']"));
    }

	public WebElement glossaryTermBody() {
		return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']"));
	}

    /**
     * This represents an individual glossary term e.g. Headlease as it appears in the list on the left hand pane
     */
    public WebElement glossaryTerm(String glossaryTerm) {
        return waitForElementPresent(By.linkText(glossaryTerm));
    }
    
	public WebElement glossaryTermLinkByPosition(String glossaryTermPosition) {
		return waitForExpectedElement(By.xpath("(//*[@id='co_glossary_terms_list']//a[contains(@href,'/Glossary/')])["
				+ glossaryTermPosition + "]"));
	}

    /**
     * This is the option to close the glossary pop up box which is displayed when a user selects a glossary term from
     * within a document
     */
    public WebElement glossaryModalClose() {
        return waitForExpectedElement(By.id("co_closeGlossary"));
    }

    /**
     * This is the title of the glossary pop up box
     */
    public WebElement glossaryModalTitle() {
        return waitForExpectedElement(By.cssSelector("div#co_glossaryLightBox h1.co_title"));
    }

    public boolean isGlossaryModalTitleDisplayed() {
    	return isElementDisplayed(By.cssSelector("div#co_glossaryLightBox h1.co_title"));
    }

    public WebElement glossaryModalTermDefinition() {
        return waitForExpectedElement(By.cssSelector("div#co_glossaryLightBox .co_paragraphText"), 10);
    }

    /**
     * This object represents an individual (non-selected) letter in the alphabetical list
     */
    public WebElement glossaryLetter(String letter) {
        return waitForExpectedElement(By.xpath("//ul[@class='co_glossary_tab']/li/a[contains(text(),'" + letter + "')]"));
    }

    /**
     * Returns the selected alphabet from left hand side alphabetical list
     */
    public boolean isSelectedLetterPresent() {
        return isExists(By.cssSelector("ul.co_glossary_tab li.co_selected a"));
    }

    /**
     * This object represents an individual letter sitting above each set of glossary terms in the left hand pane
     * e.g. letter H sitting above the term list starting with Habitual residence
     * this is not the A,B,C list to the far left of the page
     */
    public WebElement glossaryTermListLetter(String letter) {
        return waitForElementVisible(By.xpath("//li[@class='co_glossary_term_heading'][contains(text(),'" + letter + "')]"));
    }

    public WebElement selectedGlossaryTermLink() {
        return waitForElementVisible(By.xpath("//div[@id='co_glossary_terms_list']//li[@class='co_selected']//a"));
    }

    public List<WebElement> listOfGlossaryTermsHeadings() {
        return waitForExpectedElements(By.xpath("//div[@id='co_glossary_terms_list']//li[@class='co_glossary_term_heading']"));
    }

    public List<WebElement> glossaryTermsList() {
        return waitForExpectedElements(By.xpath("//div[@id='co_glossary_terms_list']//li"));
    }

    public WebElement glossaryTermOnModalBox(String modalBoxGlossaryTerm) {
        return waitForExpectedElement(By.linkText(modalBoxGlossaryTerm));
    }

    public WebElement nextElementToAlphabetTitle(String alphabet) {
        return waitForElementVisible(By.xpath("//li[@class='co_glossary_term_heading' and contains(text(),'" + alphabet + "')]/following::li"));
    }

    public WebElement selectedGlossaryAlphabetLink() {
        return waitForExpectedElement(By.xpath("//ul[@class='co_glossary_tab']//li[@class='co_selected']//a"));
    }

    public WebElement scrollUpButton() {
        return waitForExpectedElement(By.xpath("//button[@class='co_glossary_scroll_buttons' and text()='Scroll up']"));
    }

    public WebElement scrollDownButton() {
		return waitForExpectedElement(By.xpath("//button[@class='co_glossary_scroll_buttons' and text()='Scroll down']"));
    }

    public WebElement practiceNoteLink(String resourceLink) {
        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//a[contains(text(),'" + resourceLink + "')]"));
    }

    public WebElement glossarySearchField() {
        return waitForExpectedElement(By.id("co_searchGlossary_input"));
    }

    public WebElement searchGlossaryButton() {
        return waitForExpectedElement(By.id("co_searchGlossary_input_search"));
    }

    public WebElement selectedGlossaryTerm(String term) {
        return waitForExpectedElement(By.xpath("//div[@id='co_glossary_terms_list']//li[@class='co_selected']/a[contains(text(),'" + term + "')]"));
    }

    public WebElement searchGlossaryButtonReset() {
        return waitForExpectedElement(By.id("co_searchGlossary_input_reset"));
    }

    public WebElement searchMatchesText() {
        return waitForExpectedElement(By.id("co_searchglossary_found_numbers_holder"));
    }

    public List<WebElement> glossaryTermsWithSearchTermList() {
        return findElements(By.cssSelector("#co_glossary_terms_list ul li.co_searchTerm a"));
    }

    public WebElement resourcePageGlossaryTermLink() {
        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//span[@class='co_glossaryTerm']//a"));
    }

    public WebElement closeGloasaryModalBoxLink() {
        return waitForExpectedElement(By.xpath("//div[@id='co_glossaryLightBox']//a[@id='co_closeGlossary']"));
    }

    public By closeGloasaryModalBoxByLink() {
        return By.xpath("//div[@id='co_glossaryLightBox']//a[@id='co_closeGlossary']");
    }

    public WebElement getParentElement(String linkName) {
        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//span[@class='co_glossaryTerm']//a[text()='" + linkName + "']/../../../../../.."));
    }

    /**
     * This method returns the glossary search results as list of string values.
     *
     * @return List<String>
     */
    public List<String> getGlossarySearchResultsList() {
        List<String> listOfGlossaryTerms = new ArrayList<>();
        try {
            for (WebElement glossaryTerm : getGlossarySearchResultElements()) {
                listOfGlossaryTerms.add(glossaryTerm.getText());
            }
        } catch (PageOperationException | NoSuchElementException exc) {
            LOG.error("Issue in finding the glossary search results text", exc);
        }
        return listOfGlossaryTerms;
    }

    /**
     * This method gets the search result elements of glossary terms.
     *
     * @return List<WebElement>
     */
    private List<WebElement> getGlossarySearchResultElements() {
        try {
            return waitForElementsVisible(By.cssSelector(".co_searchTerm>a"));
        } catch (TimeoutException te) {
            throw new PageOperationException("Exceeded time to find the GlossarySearch Results" + te.getMessage());
        }
    }


    public WebElement saveToFolder() {

        return waitForExpectedElement(By.xpath("//li[@id='co_docToolbarSaveToWidget']//a[contains(text(),'Save To Folder')]"));
    }

    public List<String> alphabetListWithTermToString(String term) {
        List<String> stringList = new ArrayList<>();
        for (WebElement element : listOfTabbedAlphabetsWithTerm(term)) {
            stringList.add(element.getText().trim());
        }
        return stringList;
    }

    public List<WebElement> listOfTabbedAlphabetsWithTerm(String term) {
        return findElements(By.xpath("id('co_glossary_terms_list')//a[contains(text(),'"+term+"') or contains(text(),'"+ WordUtils.capitalizeFully(term)+"')]"));
    }
}
