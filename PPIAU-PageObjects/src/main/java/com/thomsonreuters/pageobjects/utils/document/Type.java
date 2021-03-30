package com.thomsonreuters.pageobjects.utils.document;

public enum Type {
	
	DOCUMENT("Document"),
	SNIPPET("Snippet")
	;

	private String name;

	Type(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
