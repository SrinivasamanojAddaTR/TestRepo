package com.thomsonreuters.pageobjects.pages.search;

import org.openqa.selenium.By;

/**
 * Created by Pavel_Ardenka on 20/10/2016.
 */
public enum SearchResultsDatePicker {

    LAST_6_MONTHS(
            "Last 6 months",
            By.xpath("//span[contains(@id, 'last6mo')]/a"),
            null,
            null
    ),
    LAST_12_MONTHS(
            "Last 12 months",
            By.xpath("//span[contains(@id, 'last12mo')]/a"),
            null,
            null
    ),
    LAST_3_YEARS(
            "Last 3 years",
            By.xpath("//span[contains(@id, 'last3yr')]"),
            null,
            null
    ),
    ALL_DATES_BEFORE(
            "All Dates Before",
            By.xpath("//li[contains(@id, 'before')]/a"),
            By.xpath("//ol[contains(@id, 'before') and not(contains(@class, 'hide'))]//input[contains(@id, 'before')]"),
            null
    ),
    ALL_DATES_AFTER(
            "All Dates After",
            By.xpath("//li[contains(@id, 'after')]/a"),
            By.xpath("//ol[contains(@id, 'after') and not(contains(@class, 'hide'))]//input[contains(@id, 'after')]"),
            null),
    SPECIFIC_DATE(
            "Specific Date",
            By.xpath("//li[contains(@id, 'dateSummary_on')]/a"),
            By.xpath("//ol[contains(@id, '_on') and not(contains(@class, 'hide'))]//input[contains(@id, '_on')]"),
            null
    ),
    DATE_RANGE(
            "Date Range",
            By.xpath("//li[contains(@id, 'fromUntil')]/a"),
            By.xpath("//ol[contains(@id, 'fromUntil') and not(contains(@class, 'hide'))]/li[contains(.,'From')]/input[contains(@id, 'from')]"),
            By.xpath("//ol[contains(@id, 'fromUntil') and not(contains(@class, 'hide'))]/li[contains(.,'til')]/input[contains(@id, 'from')]")
    ),
    ALL(
            "All",
            By.xpath("//span[contains(@id, 'all') and not(@class)]"),
            null,
            null
    );

    private String name;
    private By itemBy;
    private By dateFromBy;
    private By dateUntilBy;

    SearchResultsDatePicker(String name, By itemBy, By dateFromBy, By dateUntilBy) {
        this.name = name;
        this.itemBy = itemBy;
        this.dateFromBy = dateFromBy;
        this.dateUntilBy = dateUntilBy;
    }

    public String getName() {
        return name;
    }

    public By getItemBy() {
        return itemBy;
    }

    public By getDateFromBy() {
        return dateFromBy;
    }

    public By getDateUntilBy() {
        return dateUntilBy;
    }

    public static SearchResultsDatePicker getByName(String name) {
        for (SearchResultsDatePicker datePicker : SearchResultsDatePicker.values()) {
            if (datePicker.getName().equalsIgnoreCase(name)) {
                return datePicker;
            }
        }
        throw new IllegalArgumentException("There is no search result date-picker item with name: " + name);
    }
}
