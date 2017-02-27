package com.thomsonreuters.pageobjects.pages.globalPage.crossBorderDashboard;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class DashboardParameters {

	private String transactionGuide;
	private List<String> countryList;
	private List<String> areasOfEnquiry;

	DashboardParameters(String transactionGuide, List<String> countryList, List<String> areasOfEnquiry) {
		this.transactionGuide = transactionGuide;
		this.countryList = countryList;
		this.areasOfEnquiry = areasOfEnquiry;
	}
	
	public DashboardParameters() {
		super();
	}

	public String getTransactionGuide() {
		return transactionGuide;
	}

	public void setTransactionGuide(String transactionGuide) {
		this.transactionGuide = transactionGuide;
	}

	public List<String> getCountryList() {
		return countryList;
	}

	public void setCountries(List<String> countryList) {
		this.countryList = countryList;
	}

	public List<String> getAreasOfEnquiry() {
		return areasOfEnquiry;
	}

	public void setAreasOfEnquiry(List<String> areasOfEnquiry) {
		this.areasOfEnquiry = areasOfEnquiry;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		DashboardParameters entry = (DashboardParameters) obj;
		return new EqualsBuilder().append(transactionGuide, entry.getTransactionGuide())
				.append(countryList, entry.getCountryList()).append(areasOfEnquiry, entry.getAreasOfEnquiry())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(transactionGuide).append(countryList).append(areasOfEnquiry).toHashCode();
	}

}
