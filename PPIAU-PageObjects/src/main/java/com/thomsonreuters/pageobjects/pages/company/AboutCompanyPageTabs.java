package com.thomsonreuters.pageobjects.pages.company;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public enum AboutCompanyPageTabs {
	
	ABOUT_US("About us", "About", By.xpath("//li[@id='coid_categoryTab1_main_0']//a")),
	TESTIMONIALS("Testimonials","Testimonials",By.xpath("//li[@id='coid_categoryTab2_main_0']//a")),
	CAREERS("Careers", "Careers", By.xpath("//li[@id='coid_categoryTab3_main_0']//a")),
	TEAM_MEMBERS("Team members","Team", By.xpath("//li[@id='coid_categoryTab1_main_0']//a")),
	ADVISORY_BOARDS("Advisory Boards","Advisory", By.xpath("//li[@id='coid_categoryTab2_main_0']//a")),
	CONSULTATION_BOARDS("Consultation Boards","Consultation", By.xpath("//li[@id='coid_categoryTab3_main_0']//a")),
	CONTRIBUTORS("Contributors", "Contributors", By.xpath("//li[@id='coid_categoryTab4_main_0']//a"));
	
	private static final  Map<String, AboutCompanyPageTabs> map = new HashMap<>();

    static {
        for (AboutCompanyPageTabs aboutCompanyPageTab : AboutCompanyPageTabs.values()) {
            map.put(aboutCompanyPageTab.tabName, aboutCompanyPageTab);
        }
    }
	
	private String tabName;
	private String tabShortName;
	private By xpath;
	
	AboutCompanyPageTabs(String tabName, String tabShortName, By xpath) {
		this.tabName = tabName;
		this.tabShortName = tabShortName;
		this.xpath = xpath;
	}

	public String getTabName() {
		return tabName;
	}

	public String getTabShortName() {
		return tabShortName;
	}

	public By getXpath() {
		return xpath;
	}

	public static AboutCompanyPageTabs get(String tabName) {
        return map.get(tabName);
    }

}
