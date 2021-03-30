package com.thomsonreuters.pageobjects.pages.folders;

import org.openqa.selenium.By;

public enum HistoryTabs {

	Documents("Documents", By.xpath("//*[text()='Documents']"), By.id("co_ro_history_search_types_documents_clickable"), 
			By.id("co_ro_history_search_types_documents_non_clickable")), 
	Searches("Searches", By.xpath("//*[text()='Searches']"), By.id("co_ro_history_search_types_searches_clickable"), 
			By.id("co_ro_history_search_types_searches_non_clickable")), 
	All_History("All History", By.xpath("//*[text()='All History']"), By.id("co_ro_history_search_types_all_history_clickable"), 
			By.id("co_ro_history_search_types_all_history_non_clickable"));

	private String name;
	private By pageHeader;
	private By id;
	private By idClickable;

	HistoryTabs(String name, By pageHeader, By id, By idClickable) {
		this.name = name;
		this.pageHeader = pageHeader;
		this.id = id;
		this.idClickable = idClickable;
	}

	public By getId() {
		return id;
	}

	public By getIdClickable() {
		return idClickable;
	}

	public String getName() {
		return name;
	}

	public By getPageHeader() {
		return pageHeader;
	}

}
