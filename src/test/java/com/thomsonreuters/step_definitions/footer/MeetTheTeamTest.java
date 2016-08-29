package com.thomsonreuters.step_definitions.footer;

import com.thomsonreuters.pageobjects.pages.company.MeetTheTeam;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class MeetTheTeamTest extends BaseStepDef {
	
	private MeetTheTeam meetTheTeam = new MeetTheTeam();
	private AboutPracticalLaw aboutPracticalLaw = new AboutPracticalLaw();
	
	private List<String> notSortedpaNamesFromTOC;
	private List<String> sortedpaNamesFromTOC;
	
	@Then("^the table of contents will list practice areas in alphabetical order$")
    public void tableOfContentsWillListPAinAlphabeticalOrder() throws Throwable { 
		notSortedpaNamesFromTOC = getPAnamesFromTOC();
		sortedpaNamesFromTOC = new ArrayList<String>();
		sortedpaNamesFromTOC.addAll(notSortedpaNamesFromTOC);
		Collections.sort(sortedpaNamesFromTOC);
		assertTrue("the table of contents list practice areas not in alphabetical order", sortedpaNamesFromTOC.equals(notSortedpaNamesFromTOC));
		
	}
	
	@Then("^the user selects a practice area \"(.*?)\" within the table of contents$")
	public void theUserSelectsAPracticeAreaWithinTheTableOfContents(String paName) throws Throwable {
		aboutPracticalLaw.theUserSelectsTabWithinTheTableOfContents(paName);
	}

	@Then("^practice area \"(.*?)\" in Table of Contents is active$")
	public void practiceAreaInTOCIsActive(String paName) throws Throwable {
		aboutPracticalLaw.tabInTOCIsActive(paName);
	}

	@Then("^there are \"(.*?)\" team members listed for practice area$")
	public void practiceAreaListContainsNumberOfProfiles(String profilesCount) throws Throwable {
		assertTrue("Number of profiles is not correct", meetTheTeam.contributorProfiles().size() == Integer.valueOf(profilesCount));
	}
	
	@Then("^profiles are not empty$")
	public void profilesAreNotEpmty() throws Throwable {
		SoftAssertions softly = new SoftAssertions();
		List<WebElement> contributorProfiles = meetTheTeam.contributorProfiles();
		for(WebElement element : contributorProfiles) {
			softly.assertThat(element.getText().isEmpty()).overridingErrorMessage("One of profiles is empty").isFalse();
		}
		softly.assertAll();
	}
	
	@Then("^there are '(\\d+)' team members listed$")
	public void userShouldSeeProfiles(int profilesCount) throws Throwable {
		assertTrue("Number of profiles is not correct on main page", meetTheTeam.contributorProfiles().size() == profilesCount);
	}
	
	
	private List<String> getPAnamesFromTOC() {
		ArrayList<String> paNamesFromTOC = new ArrayList<String>();
		List<WebElement> practiceAreaTabs = meetTheTeam.tabsInTableOfContents();
		for(WebElement element: practiceAreaTabs) {
			paNamesFromTOC.add(element.getText());
		}
		return paNamesFromTOC;
	}
}
