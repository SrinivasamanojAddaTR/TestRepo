package com.thomsonreuters.step_definitions.header;

import com.thomsonreuters.pageobjects.pages.footer.WLNFooter;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;

import static javax.swing.text.html.HTML.Attribute.HREF;
import static org.junit.Assert.assertTrue;

public class HeaderTests1 extends BaseStepDef {
	
    private WLNHeader header = new WLNHeader();
    private WLNFooter footer = new WLNFooter();
	
    @Then("^user should see the following countries with respective links$")
    public void userShouldSeeUserIconLink(DataTable dataTable) throws Throwable {
            SoftAssertions softAssertions = new SoftAssertions();
            dataTable.asMap(String.class, String.class)
                    .forEach((linkName, linkHref) ->
                            softAssertions.assertThat(footer.footerLink(linkName).getAttribute(HREF.toString()))
                                    .as("Footer link does not contain the right href!")
                                    .contains(linkHref)
                    );
            softAssertions.assertAll();
    }

	@Then("^user should see the Browse Menu popup with Practice Area and Resources sub-menu$")
	public void userShouldSeeThePopupWithPracticeAreaAndResourcesAndInternationalSubMenu() throws Throwable {
		assertTrue("Practice Area is not displayed..!", header.browseMenuSubMenuList().get(0).isDisplayed()
				&& header.browseMenuSubMenuList().get(0).getText().contains("Practice areas"));
		assertTrue("Resources is not displayed..!", header.browseMenuSubMenuList().get(1).isDisplayed()
				&& header.browseMenuSubMenuList().get(1).getText().contains("Resources"));
	}

	@Then("^the practice areas option will be the default selected option$")
	public void thePracticeAreasOptionWillBeTheDefaultSelectedOption() {
		assertTrue("Practice Area Links are not displayed by default!", header.practiceAreaFirstColumnLinks().get(0).isDisplayed());
	}

}
