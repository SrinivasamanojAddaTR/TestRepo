package com.thomsonreuters.step_definitions.header;

import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class HeaderTests extends BaseStepDef {
	
    private WLNHeader header = new WLNHeader();
	
    @Then("^user should see the following countries with respective links$")
    public void userShouldSeeUserIconLink(DataTable dataTable) throws Throwable {
        Map<String,String> countries= dataTable.asMap(String.class,String.class);
        int row=0;
        for(Map.Entry<String,String> entry: countries.entrySet()){
          if(!entry.getKey().equalsIgnoreCase("Country")) {
              assertTrue(entry.getKey()+" not present..!",
                      header.countryDropdownMenuLinks().get(row).getText().equalsIgnoreCase(entry.getKey()));
             if(!entry.getKey().equalsIgnoreCase("Australia") && !entry.getKey().contains("Westlaw")) {
                 assertTrue(entry.getKey() + " link not present..!",
                         header.countryDropdownMenuLinks().get(row).getAttribute("href").equalsIgnoreCase(entry.getValue()));
             }
              row++;
          }
        }
    }

	@Then("^user should see the Browse Menu popup with Practice Area and Resources and International sub-menu$")
	public void userShouldSeeThePopupWithPracticeAreaAndResourcesAndInternationalSubMenu() throws Throwable {
		assertTrue("Practice Area is not displayed..!", header.browseMenuSubMenuList().get(0).isDisplayed()
				&& header.browseMenuSubMenuList().get(0).getText().contains("Practice areas"));
		assertTrue("Resources is not displayed..!", header.browseMenuSubMenuList().get(1).isDisplayed()
				&& header.browseMenuSubMenuList().get(1).getText().contains("Resources"));
		assertTrue("International is not displayed..!", header.browseMenuSubMenuList().get(2).isDisplayed()
				&& header.browseMenuSubMenuList().get(2).getText().contains("International"));
	}

	@Then("^the practice areas option will be the default selected option$")
	public void thePracticeAreasOptionWillBeTheDefaultSelectedOption() {
		assertTrue("Practice Area Links are not displayed by default!", header.practiceAreaFirstColumnLinks().get(0).isDisplayed());
	}

}
