package com.thomsonreuters.pageobjects.utils.document;

import com.thomsonreuters.pageobjects.utils.document.content.Section;
import com.thomsonreuters.pageobjects.utils.document.metadata.Jurisdiction;
import com.thomsonreuters.pageobjects.utils.document.metadata.Product;
import com.thomsonreuters.pageobjects.utils.folders.FoldersUtils;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Document {

	// first 60 letters from document title
	private String title;
	// Full document title without FoldersUtils.makeDocumentShorterForFoldersAndHistoryChecks
	private String fullTitle;
	private String status;
	private String resourceType;
	private String contentType;
	private String guid;
	private Date sortingDate;
	private List<Product> products = new ArrayList<>();
	private List<Jurisdiction> jurisdictions = new ArrayList<>();
	private List<Section> sections = new ArrayList<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String documentTitle) {
		this.fullTitle = documentTitle;
		this.title = FoldersUtils.makeDocumentShorterForFoldersAndHistoryChecks(documentTitle);
	}

	public String getFullTitle() {
		return fullTitle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String documentStatus) {
		this.status = documentStatus;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Jurisdiction> getJurisdictions() {
		return jurisdictions;
	}

	public void setJurisdictions(List<Jurisdiction> jurisdictions) {
		this.jurisdictions = jurisdictions;
	}

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

	public Date getSortingDate() {
		return sortingDate;
	}

	public void setSortingDate(Date sortingDate) {
		this.sortingDate = sortingDate;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Document: \n");
		sb.append(" - Document Name: " + getTitle() + ",\n");
		sb.append(" - Document GUID: " + getGuid() + ",\n");
		sb.append(" - Document Status : " + getStatus() + ",\n");
		sb.append(" - Document Resource Type: " + getResourceType() + ",\n");
		sb.append(" - Document Content Type: " + getContentType() + ",\n");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Document)) {
			return false;
		}
		Document entry = (Document) obj;
		return title.equals(entry.title) && status.equals(entry.status) && resourceType.equals(entry.resourceType);
	}

	@Override
	public int hashCode() {
		int hash = 37;
		hash = hash * 17 + title.hashCode();
		hash = hash * 17 + status.hashCode();
		hash = hash * 17 + resourceType.hashCode();
		return hash;
	}

}
