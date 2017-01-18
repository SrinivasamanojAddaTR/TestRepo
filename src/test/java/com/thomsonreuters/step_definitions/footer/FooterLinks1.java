package com.thomsonreuters.step_definitions.footer;

import com.thomsonreuters.pageobjects.pages.company.AboutCompanyPage;
import com.thomsonreuters.pageobjects.pages.company.AboutCompanyPageTabs;
import com.thomsonreuters.pageobjects.pages.footer.WLNFooter;
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class FooterLinks1 extends BaseStepDef {

	private WLNFooter footer = new WLNFooter();
    private FooterUtils footerUtils = new FooterUtils();
	private AboutCompanyPage aboutCompanyPage = new AboutCompanyPage();
	
	@Then("^user should see the \"(.*?)\" page is opened$")
    public void userShouldseethePage(String pageTitle) throws Throwable {
        assertTrue("Title " + aboutCompanyPage.getPageTitle() + " is Not contains " + pageTitle, aboutCompanyPage.getPageTitle().contains(pageTitle));
    }
	
	@Then("^user should see the following FooterLinks under heading \"(.*?)\" with links to pages$")
	public void userShouldTheFollowingFooterLinksLink(String columnHeading,
			DataTable dataTable) throws Throwable {
		Map<String, String> footerColumnLinks = dataTable.asMap(String.class,
				String.class);
		int col1Row = 0, col2Row = 0, col3Row = 0;
		for (Map.Entry<String, String> entry : footerColumnLinks.entrySet()) {
			if (entry.getKey().equalsIgnoreCase("About Practical Law")
					|| entry.getKey().equalsIgnoreCase("Testimonials")
					|| entry.getKey().equalsIgnoreCase("Careers")) {
				assertTrue(entry.getKey() + " not present..!", footer
						.footerByColumnLinks(columnHeading).get(col1Row)
						.getText().equalsIgnoreCase(entry.getKey()));
				assertTrue(entry.getKey() + " link not present..!", footer
						.footerByColumnLinks(columnHeading).get(col1Row)
						.getAttribute("href").contains(entry.getValue()));
				col1Row++;
			} else if (entry.getKey().equalsIgnoreCase("Meet the Team")
					|| entry.getKey().equalsIgnoreCase("Consultation Boards")
					|| entry.getKey().equalsIgnoreCase("Contributing Firms")
					|| entry.getKey().equalsIgnoreCase("Advisory Board")) {
				assertTrue(entry.getKey() + " not present..!", footer
						.footerByColumnLinks(columnHeading).get(col2Row)
						.getText().equalsIgnoreCase(entry.getKey()));
				assertTrue(entry.getKey() + " link not present..!", footer
						.footerByColumnLinks(columnHeading).get(col2Row)
						.getAttribute("href").contains(entry.getValue()));
				col2Row++;
			} else if (entry.getKey().equalsIgnoreCase("User Guides")
					|| entry.getKey().equalsIgnoreCase("Request Training")
					|| entry.getKey().equalsIgnoreCase("Feedback")) {
				assertTrue(entry.getKey() + " not present..!", footer
						.footerByColumnLinks(columnHeading).get(col3Row)
						.getText().equalsIgnoreCase(entry.getKey()));
				assertTrue(entry.getKey() + " link not present..!", footer
						.footerByColumnLinks(columnHeading).get(col3Row)
						.getAttribute("href").contains(entry.getValue()));
				col3Row++;
			}

		}
	}
	@Then("^tabs are displayed on about company page$")
    public void tabsAreDisplayed(List<String> tabs) throws Throwable {
		SoftAssertions softly = new SoftAssertions();
		for (String tab : tabs) {
			softly.assertThat(aboutCompanyPage.isElementDisplayed(aboutCompanyPage.specifiedTab(tab))).overridingErrorMessage("Tab is not displayed: <%s>", tab).isTrue();
		}
		softly.assertAll();
    }
	
	@Then("^tab \"(.*?)\" on about company page is active$")
    public void tabIsActive(String tabName) throws Throwable { 
		String activeTab = aboutCompanyPage.activeTab().getText().trim();
		assertTrue("Tab is not active! Active tab text is: " + activeTab, activeTab.contains(AboutCompanyPageTabs.get(tabName).getTabShortName()));
	}
	
	@Then("^user selects a tab \"(.*?)\" on about company page and this tab becomes active$")
    public void userSelectsATabAndThisTabBecomeActive(String tabName) throws Throwable { 
		userSelectsATab(tabName);
		String activeTab = aboutCompanyPage.activeTab().getText().trim();
		assertTrue("Tab is not active! Active tab text is: " + activeTab, activeTab.contains(AboutCompanyPageTabs.get(tabName).getTabShortName()));
	}
	
	@Then("^user selects a tab \"(.*?)\" on about company page$")
    public void userSelectsATab(String tabName) throws Throwable {
		aboutCompanyPage.specifiedTab(tabName).click();
		aboutCompanyPage.waitForPageToLoad();
	}
	
	@Then("^user was taken to url \"(.*?)\"$")
    public void userWasTakenTo(String expectedUrl) throws Throwable { 
		String currentPageUrl = footer.getCurrentUrl();
        assertTrue("User was redirected to another page", expectedUrl.equalsIgnoreCase(currentPageUrl));
	}
	
	@When("^the user clicks link '(.*?)' on footer$")
	public void theUserClicksButtonRequestATrialOnFooter(String link) throws Throwable {
        footerUtils.closeDisclaimerMessage();
        switch (link) {
		case "Request Training":
            footer.scrollIntoViewAndClick(footer.requestTraining());
			break;
		case "User Guides":
            footer.scrollIntoViewAndClick(footer.userGuides());
			break;
		case "Request a Trial":
            footer.scrollIntoViewAndClick(footer.requestTrial());
			break;
		}

	}
	
}
