package com.thomsonreuters.pageobjects.utils.document;

public enum ContentType {

	LEGAL_UPDATES("KnowHow", "Know-how", "Practical Law"),
	CURRENT_AWARENESS("International - News", "News", "News"),
	KNOW_HOW("KnowHow", "Know-how", "Practical Law"),
	WHATS_MARKET("WhatsMarket", "What's Market", "What's Market"),
	CASES(ContentType.CASES_TEXT, ContentType.CASES_TEXT, ContentType.CASES_TEXT),
	INTERNATIONAL_CASES("International - Cases", ContentType.CASES_TEXT, ContentType.CASES_TEXT),
	LEGISLATION("International - Statutes", "Legislation", "Statutes"),
	PRIMARY_SOURCES("PrimarySources", "Primary Sources", ""),
	JOURNALS("International - Secondary Sources", "Journals", "Secondary Sources"),
	CONVICTIONSTRACKER("CONVICTIONSTRACKER", "Convictions Tracker", "");

	private String name;
	private String documentMetaInfoName;
	private String wlnName;
	private static final String CASES_TEXT = "Cases";

	ContentType(String documentMetaInfoName, String plcukNname, String wlnName) {
		this.setName(plcukNname);
		this.setNameFromDocumentMetaInfo(documentMetaInfoName);
		this.setWlnName(wlnName);
	}

	public String getPLCUKName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getNameFromDocumentMetaInfo() {
		return documentMetaInfoName;
	}

	private void setNameFromDocumentMetaInfo(String nameFromDocumentMetaInfo) {
		this.documentMetaInfoName = nameFromDocumentMetaInfo;
	}

	public String getWlnName() {
		return wlnName;
	}

	private void setWlnName(String wlnName) {
		this.wlnName = wlnName;
	}

	public static ContentType getContentTypeByMetaInfoName(String metaInfoName) {
		for (ContentType type : ContentType.values()) {
			if (type.getNameFromDocumentMetaInfo().equals(metaInfoName)) {
				return type;
			}
		}
		throw new NullPointerException(String.format("Content type with name '%s' is not found.", metaInfoName));
	}
}
