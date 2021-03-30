package com.thomsonreuters.pageobjects.utils.adestra;




public class SubscriptionParameters {

	private String categoryName;
	private String commonName;
	private String productFrequency;
	private String name;
	private String description;
	
	public SubscriptionParameters(){
		
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getProductFrequency() {
		return productFrequency;
	}

	public void setProductFrequency(String productFrequency) {
		this.productFrequency = productFrequency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Sub Pref: \n");
		sb.append(" - Category Name: " + getCategoryName() + ",\n");
		sb.append(" - Common Name: " + getCommonName() + ",\n");
		sb.append(" - Frequency: " + getProductFrequency() + ",\n");
		sb.append(" - Name: " + getName() + ",\n");
		sb.append(" - Description: " + getDescription() + ",\n");

		return sb.toString();
	}

}
