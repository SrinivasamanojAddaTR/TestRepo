package com.thomsonreuters.pageobjects.pages.folders;

import org.openqa.selenium.By;

public enum HistoryTabs {

	DOCUMENTS("Documents", By.xpath("//*[text()='Documents']"), By.id("co_ro_history_search_types_documents_clickable"),
			By.id("co_ro_history_search_types_documents_non_clickable")),
	SEARCHES("Searches", By.xpath("//*[text()='Searches']"), By.id("co_ro_history_search_types_searches_clickable"),
			By.id("co_ro_history_search_types_searches_non_clickable")),
	ALL_HISTORY("All_History", By.xpath("//*[text()='All History']"), By.id("co_ro_history_search_types_all_history_clickable"),
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

	public static HistoryTabs valueOfTab(String name) {
		HistoryTabs[] var1 = values();
		int var2 = var1.length;

		for(int var3 = 0; var3 < var2; ++var3) {
			HistoryTabs ht = var1[var3];
			if (ht.name.equals(name)) {
				return ht;
			}
		}

		throw new IllegalArgumentException("No enum const " + name);
	}

}
