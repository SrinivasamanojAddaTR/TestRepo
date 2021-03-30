package com.thomsonreuters.pageobjects.utils.document;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Pavel_Ardenka on 30/06/2016.
 */
public enum ResourceType {

    LEGISLATION("Legislation", "legis", "//date-facet", Arrays.asList("UK_SMG_LEGIS", "UK_SMG_LEGHIST", "UK_SMG_LEGFUT", "UK_SMG_LEGLOC")),
    CASES("Cases", "case", "//md.publisheddate", Arrays.asList("UK_SMG_CASES", "UK_SMG_CASESLOC", "UK_SMG_CASESSVG")),
    JOURNALS("Journals", "journal", "//md.publisheddate", Arrays.asList("UK_SMG_JRN")),
    // Generic type is not using on the site and just for code purposes only and means Document object
    GENERIC("Generic document", "", "//md.publisheddate", Arrays.asList("")),
    PLC_LEGACY("PLC Legacy document", "", "//md.publisheddate", Arrays.asList("w_plc_find"));

    private String friendlyName;
    //md.infotype prefix, e.g. legis, legis-AOP, case, caseloc
    private String novusResourceTypePrefix;
    private String novusSortingDateXpath;
    private List<String> documentCollections;

    ResourceType(String friendlyName, String novusResourceTypePrefix, String novusSortingDateXpath, List<String> documentCollections) {
        this.friendlyName = friendlyName;
        this.novusResourceTypePrefix = novusResourceTypePrefix;
        this.novusSortingDateXpath = novusSortingDateXpath;
        this.documentCollections = documentCollections;
    }

    public String getNovusResourceTypePrefix() {
        return novusResourceTypePrefix;
    }

    public String getNovusSortingDateXpath() {
        return novusSortingDateXpath;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public List<String> getDocumentCollections() {
        return documentCollections;
    }

    public static ResourceType getByNovusInfoType(String novusInfoType) {
        for (ResourceType resourceType : ResourceType.values()) {
            if (novusInfoType.startsWith(resourceType.getNovusResourceTypePrefix())) {
                return resourceType;
            }
        }
        throw new IllegalArgumentException("Resource type not found for given md.infotype " + novusInfoType);
    }

    public static ResourceType getByFriendlyName(String friendlyName) {
        for (ResourceType resourceType : ResourceType.values()) {
            if (resourceType.getFriendlyName().contains(friendlyName)) {
                return resourceType;
            }
        }
        throw new IllegalArgumentException("Resource type not found for given friendly name " + friendlyName);
    }
}
