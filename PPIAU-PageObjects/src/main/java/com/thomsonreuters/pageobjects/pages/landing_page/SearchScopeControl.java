package com.thomsonreuters.pageobjects.pages.landing_page;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchScopeControl extends AbstractPage {

    /**
     * Object representing scoped search title
     */
    public WebElement scopedSearchDropdownTitle() {
        return waitForExpectedElement(By.id("co_currentSelectedCategoryText"), 15);
    }

    /**
     * Object representing the button/option to click and then display scoped search dropdown menu options
     */
    public WebElement scopedSearchDropdownMenuButton() {
        return waitForExpectedElement(By.id("co_currentSelectedCategoryToggle"));
    }

    /**
     * Object representing scoped search dropdown options
     */
    public WebElement scopedSearchDropdownOptions(String option) {
        return waitForExpectedElement(By.xpath("//div[@id='co_searchCategoryDropdown']//a[contains(text(),'" + option + "')]"));
    }

    public List<String> scopedSearchDropdownOptionsList() {
        List<String> list = new ArrayList<>();
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
