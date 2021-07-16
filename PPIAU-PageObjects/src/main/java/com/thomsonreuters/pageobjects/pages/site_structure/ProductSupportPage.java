package com.thomsonreuters.pageobjects.pages.site_structure;

import org.openqa.selenium.By;

import com.thomsonreuters.driver.framework.AbstractPage;

public class ProductSupportPage extends AbstractPage {

	public boolean isRequestTrainingPageTitlePresent() {
		return isExists(By.xpath("//*[@id='co_browsePageLabel' and contains(text(), 'Request Westlaw UK Training')]"));
	}

	public boolean isTabActive() {
		return isExists(By.xpath("//li[@class='co_tabLeft co_tabActive']/descendant::a"));
	}

	public boolean isWhatsNewPageTitlePresent() {
		return isExists(By.xpath("//*[@id='co_browsePageLabel' and contains(text(), 'Whats New For Westlaw UK')]"));
	}
	
	public boolean isWestlawUKUserGuidesTitlePresent() {
		return isExists(By.xpath("//*[@id='co_browsePageLabel' and contains(text(), 'Westlaw UK User Guides')]"));
	}

	public boolean isFeedBackFormPresent(){
	    	return isExists(By.xpath("//*[contains(text(),'Westlaw UK Feedback')]"));
	}
}
