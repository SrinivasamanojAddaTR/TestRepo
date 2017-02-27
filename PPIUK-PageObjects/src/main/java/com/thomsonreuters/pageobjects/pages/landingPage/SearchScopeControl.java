package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchScopeControl extends AbstractPage {

    public SearchScopeControl() {
    }

    /**
     * Object representing scoped search title
     */
    public WebElement scopedSearchDropdownTitle() {
        return waitForExpectedElement(By.xpath("//span[@id='scopedSearchTitle']"), 15);
    }

    /**
     * Object representing the button/option to click and then display scoped search dropdown menu options
     */
    public WebElement scopedSearchDropdownMenuButton() {
        return waitForExpectedElement(By.id("scopedSearchMenu"));
    }

    /**
     * Object representing scoped search dropdown options
     */
    public WebElement scopedSearchDropdownOptions(String option) {
        return waitForExpectedElement(By.xpath("//div[@id='scopedSearchMenuItems']//a[contains(text(),'" + option + "')]"));
    }

    public List<String> scopedSearchDropdownOptionsList() {
        List<String> list = new ArrayList<String>();
        List<WebElement> optionList = waitForExpectedElements(By.cssSelector("#scopedSearchMenuItems ul li"));
        for (WebElement element : optionList) {
            if (!element.getAttribute("class").contains("co_separator")) {
                String option = element.getText().trim();
                list.add(option);
            }
        }
        return list;
    }

}
