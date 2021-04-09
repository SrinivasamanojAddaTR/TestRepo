package com.thomsonreuters.pageobjects.pages.globalPage.crossBorderDashboard;

import java.util.ArrayList;
import java.util.List;

public enum TransactionIndicators {

	ACCESSIBLE("Accessible", "The relevant legal framework is considered to be in line with commonly recognised/accepted standards and accessible to foreign practitioners"), 
	DEVELOPPING("Developing", "The relevant legal framework is still in development or not rigorously enforced and some aspects may be unfamiliar or onerous for foreign practitioners"), 
	DIFFICULT("Difficult", "The relevant legal framework is out of line with commonly recognised/accepted standards or particularly complex, restrictive or difficult to negotiate for foreign practitioners"),
	NOT_AVAILABLE("Not available", "Information is not available");
	
	TransactionIndicators(String name, String textMessage) {
		this.name = name;
		this.textMessage = textMessage;
	}

	private String name;
	private String textMessage;

	public String getName() {
		return name;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public static String getTextMessageByName(String name) {
		for (TransactionIndicators transactionIndicator : TransactionIndicators.values()) {
			if (transactionIndicator.getName().equalsIgnoreCase(name)) {
				return transactionIndicator.getTextMessage();
			}
		}
		throw new IllegalArgumentException("There is no Transaction Indicator for the name: " + name);
	}

	public static List<String> getTransactionIndicatorsNames() {
		List<String> names = new ArrayList<>();
		for (TransactionIndicators transactionIndicator : TransactionIndicators.values()) {
			names.add(transactionIndicator.getName());
		}
		return names;
	}

}
