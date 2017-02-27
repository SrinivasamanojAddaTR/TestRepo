package com.thomsonreuters.pageobjects.pages.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;

public class MeetTheTeam extends AboutCompanyPage {
	
	public List<WebElement> contributorProfiles() {
		return waitForExpectedElements(By.xpath("//div[@class='co_featureBox']//div[@class='co_featureBoxInner']"));
	}
	
}
