package com.thomsonreuters.step_definitions.header.responsiveDesign;

import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class CountryToggleDropdownTest extends BaseStepDef {

    private WLNHeader header = new WLNHeader();

    @Then("^user clicks on the country toggle dropdown$")
    public void userHoversOverTheCountryTogglePage() throws Throwable {
        header.countryToggleDropdownLink().click();
    }

    @Then("^user should be seeing the following countries with respective links$")
    public void userShouldSeeUserIconLink(DataTable dataTable) throws Throwable {
        String baseUrl = System.getProperty("base.url");
        String newURL;

        Map<String, String> countries = dataTable.asMap(String.class, String.class);
        int row = 0;
        for (Map.Entry<String, String> entry : countries.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase("Country")) {
                assertTrue(entry.getKey() + " not present..!",
                        header.countryDropdownMenuLinks().get(row).getText().equalsIgnoreCase(entry.getKey()));
                if (baseUrl.equalsIgnoreCase("qed")) {
                    newURL=entry.getValue().replace("demo","qed");
                    newURL = newURL.replace("a.uk","uk");
                 }else{
                    newURL=entry.getValue();
                }
                assertTrue(newURL + " link not present..!",
                        header.countryDropdownMenuLinks().get(row).getAttribute("href").equalsIgnoreCase(newURL));
                row++;
            }
        }
    }

    @Then("^the user selects \"(.*?)\"$")
    public void theUserSelects(String countryName) throws Throwable {
        header.countryLink(countryName).click();
        header.waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(120);
    }

    @Then("^the Practical Law Country \"(.*?)\" web site \"(.*?)\" will be launched in the same window and tab$")
    public void thePracticalLawCountryWebSiteWillBeLaunchedInTheSameWindowAndTab(String websiteUrl, String websiteTitle) throws Throwable {
        String currentPageTitle = header.getPageTitle();
        String currentPageUrl = header.getCurrentUrl();
        assertTrue("User was redirected to another page", websiteTitle.equalsIgnoreCase(currentPageTitle) && currentPageUrl.contains(websiteUrl));
    }

}