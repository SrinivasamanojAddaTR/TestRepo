package com.thomsonreuters.pageobjects.utils.document;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class depicts the each case/legislation properties file as document object.
 * <p/>
 */
public class DocumentObject {

    private String docName;
    private String docId;
    private Set<String> primaryLinks;
    private Map<String, List<String>> secondaryLinks;

    public Set<String> getPrimaryLinks() {
        return primaryLinks;
    }

    public void setPrimaryLinks(Set<String> primaryLinks) {
        this.primaryLinks = primaryLinks;
    }

    public Map<String, List<String>> getSecondaryLinks() {
        return secondaryLinks;
    }

    public void setSecondaryLinks(Map<String, List<String>> secondaryLinks) {
        this.secondaryLinks = secondaryLinks;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }
}
