package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by u4400015 on 26/10/2015.
 */


public class InternationalTransactionGuidesComparisonToolPage extends AbstractPage {


    public InternationalTransactionGuidesComparisonToolPage() {
    }

    /**
     * Object representing live topic links for selection e.g. Sponsorship
     */

    public WebElement topicLink(String name) {

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//a[@class='ng-scope ng-binding'][contains(text(),'" + name + "')]"),10);
    }

    /**
     * Object representing the Select a Topic link
     */

    public WebElement selectATopicLink() {

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//a[contains(text(),'Select a Topic')]"));
    }


    /**
     * Object representing the Select Jurisdictions link
     */

    public WebElement selectJurisdictionsLink() {

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//a[contains(text(),'Select Jurisdictions')]"));
    }

    /**
     * Object representing the question checkbox options
     */

    public WebElement questionCheckbox(String text) {

        String text1 = "'" + text + "'";
        if (text.contains("'")) {
            text = "\"" + text + "\"";
            text1 = text;

        }

        return waitForExpectedElement(By.xpath("//label[@class='ng-binding'][contains(.,'"+ text + "')]/input[@type='checkbox']"));
    }


    /**
     * Object representing the question text on the question page
     */

    public WebElement questionText(String text) {

        String text1 = "'" + text + "'";
        if (text.contains("'")) {
            text = "\"" + text + "\"";
            text1 = text;

        }

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//label[contains(.,'" + text + "')]"));
    }

    /**
     * Object representing the Jurisdiction checkboxes
     */

    public WebElement jurisdictionCheckbox(String text) {

        return waitForExpectedElement(By.xpath("//li[not(contains(@class,'ng-hide'))][contains(.,'"+ text + "')]//input[@type='checkbox']"));
    }


    /**
     * Object representing the jurisdiction label e.g. Italy
     */

    public WebElement jurisdictionLabel(String text) {

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//label[contains(.,'" + text + "')]"));
    }


    /**
     * Object representing the Compare link
     */

    public WebElement compareLink() {

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//a[contains(text(),'Compare')]"));
    }

    /**
     * Object representing the Select Questions link
     */

    public WebElement selectQuestionsLink() {

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//a[contains(text(),'Select Questions')]"));
    }

    /**
     * Object on the comparison tool results page representing the selected Jurisdiction
     */


    public WebElement selectedCountry(String name) {

        return waitForExpectedElement(By.xpath("//div[@id='co_jurisdictionSelection']//input[@class='selectedButton']/self::input[@value='" + name + "']"));
    }


    /**
     * Object on the comparison tool results page representing the non selected Jurisdictions
     */


    public WebElement nonSelectedCountry(String name) {

        return waitForExpectedElement(By.xpath("//div[@id='co_jurisdictionSelection']//input[@class='notSelectedButton']/self::input[@value='" + name + "']"));
    }


    /**
     * Object on the comparison tool results page representing the question text displayed at the top of the page
     */


    public WebElement question(String text) {

        String text1 = "'" + text + "'";
        if (text.contains("'")) {
            text = "\"" + text + "\"";
            text1 = text;

        }

        return waitForExpectedElement(By.xpath("//div[@id='co_stateQAQuestions']//div[contains(text(),'" + text + "')]/self::div[contains(@class,'subSectionTitle')]"));
    }


    /**
     * Object on the comparison tool results page representing the previous question arrow
     */


    public WebElement previousQuestionArrow() {

        return waitForExpectedElement(By.xpath("//div[@id='co_stateQAQuestions']//span[@class='kh_icon icon-left-arrow is-active']"));
    }


    /**
     * Object on the comparison tool results page representing the next question arrow
     */


    public WebElement nextQuestionArrow() {

        return waitForExpectedElement(By.xpath("//div[@id='co_stateQAQuestions']//span[@class='kh_icon icon-right-arrow is-active']"));
    }


    /**
     * Object on the comparison tool results page representing option to edit the selected country
     */


    public WebElement editCountryButton() {

        return waitForExpectedElement(By.xpath("//a[@id='jurisdictionEditButtonLink']"));
    }

    /**
     * Object on the comparison tool results page edit pop up representing country checkbox
     */


    public WebElement countryCheckbox(String name) {

        return waitForExpectedElement(By.xpath("//a[@id='questionEditButtonLink']"));
    }

    /**
     * Object on the comparison tool topic option page representing the heading Select a Topic
     */


    public WebElement selectTopicHeader() {

        return waitForExpectedElement(By.xpath("//div[@id='co_docContentBody']//span[contains(text(),'Select a Topic')]"));
    }


    /**
     * Object on the comparison tool results page representing the All option (left hand side)
     */


    public WebElement allLink() {

        return waitForExpectedElement(By.xpath("//div[@id='co_jurisdictionSelection']//input[@value='All']"));
    }




}
