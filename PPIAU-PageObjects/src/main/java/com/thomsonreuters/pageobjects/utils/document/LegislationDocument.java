package com.thomsonreuters.pageobjects.utils.document;

import java.util.*;

/**
 * Created by Pavel_Ardenka on 11/07/2016.
 */
public class LegislationDocument extends Document {

    private Document parentArrangementDocument;
    private List<Document> sectionsDocuments;
    private String provision;
    private String searchResultLinkTitle;
    private int rank;
    private LegislationDocStatus legislationDocStatus;
    private int version;
    private int versionsCount;
    private Date startDate = null;
    private Date endDate = null;
    private Map<String, List<String>> aopProvisions = new LinkedHashMap<>();
    private static final List<String> topLevelDocumentTypes = Arrays.asList("Arrangement of SI", "Arrangement of Provisions", "Arrangement of Document", "Arrangement of Act");

    public Map<String, List<String>> getAopProvisions() {
        return aopProvisions;
    }

    public void setAopProvisions(Map<String, List<String>> aopProvisions) {
        this.aopProvisions = aopProvisions;
    }

    public enum LegislationDocStatus {
        AMENDMENTS_PENDING("Amendments Pending", "A", true, "icon_circle_exclamation_orange_small", BillType.NONE),
        BILL_HYBRID(BillType.HYBRID.getTypeName(), "BC", false, "icon_circle_right_dashed_arrow_purple_small", BillType.HYBRID),
        BILL_PUBLIC_GOVERNMENT(BillType.PUBLIC_GOVERNMENT.getTypeName(), "BC", false, BILL_HYBRID.getStatusIconClassName(), BillType.PUBLIC_GOVERNMENT),
        BILL_PUBLIC_PRIVATE(BillType.PUBLIC_PRIVATE.getTypeName(), "BC", false, "icon_circle_right_dashed_arrow_purple_small", BillType.PUBLIC_PRIVATE),
        FUTURE("Prospective Law", "F", true, "icon_circle_redo_blue_small", BillType.NONE),
        HISTORIC("Superseded", "H", true, "icon_circle_minus_red_small", BillType.NONE),
        PARTIALLY_REPEALED("Partially Repealed", "I", true, AMENDMENTS_PENDING.getStatusIconClassName(), BillType.NONE),
        LEGISLATION_IN_FORCE("Law in Force", "L", true, "icon_circle_checkmark_green_small", BillType.NONE),
        NOT_YET_IN_FORCE("Not Yet in Force", "N", true, HISTORIC.getStatusIconClassName(), BillType.NONE),
        PARTIALLY_IN_FORCE("Partially in Force", "P", true, AMENDMENTS_PENDING.getStatusIconClassName(), BillType.NONE),
        REPEALED("Repealed", "R", true, HISTORIC.getStatusIconClassName(), BillType.NONE),
        UNKNOWN("", "U", false, "", BillType.NONE), // Is it correct status code? Need an example
        AS_ORIGINALLY_ENACTED("As Originally Enacted", "V", false, "icon_circle_dotted_small", BillType.NONE),
        NOT_APPLICABLE("", "X", true, "", BillType.NONE),
        SUPERSEDED_BILL("Historic Law", "BS", true, "icon_circle_minus_red_small", BillType.NONE),
        LIF_AMENDMENTS_PENDING("Law in Force, Amendments Pending", "LA", true, "icon_circle_checkmark_green_exclamation_yellow_small", BillType.NONE),
        PARTIAL_AMENDMENTS_PENDING("Partially in Force, Amendments Pending", "PA", true, AMENDMENTS_PENDING.getStatusIconClassName(), BillType.NONE),
        NYIF_AMENDMENTS_PENDING("Not Yet in Force, Amendments Pending", "NA", true, "icon_circle_minus_red_exclamation_yellow_small", BillType.NONE),
        FUTURE_AMENDMENTS_PENDING("Prospective Law, Amendments Pending", "FA", true, "icon_circle_redo_blue_exclamation_yellow_small", BillType.NONE), // has date? Need an example
        PARTIALLY_REPEALED_AMENDMENTS_PENDING("Partially Repealed, Amendments Pending", "IA", true, AMENDMENTS_PENDING.getStatusIconClassName(), BillType.NONE),
        NOT_AVAILABLE("", null, true, "icon_circle_b_purple_exclamation_yellow_small", BillType.NONE);

        private String name;
        private String colorCode;
        // Flag which indicates that document has start / end date and / or versions in XML
        private boolean hasDatesOrVersions;
        private String statusIconClassName;
        private BillType billType;

        LegislationDocStatus(String name, String colorCode, boolean hasDatesOrVersions, String statusIconClassName, BillType billType) {
            this.name = name;
            this.colorCode = colorCode;
            this.hasDatesOrVersions = hasDatesOrVersions;
            this.statusIconClassName = statusIconClassName;
            this.billType = billType;
        }

        public String getName() {
            return name;
        }

        public String getColorCode() {
            return colorCode;
        }

        public boolean hasDatesOrVersions() {
            return hasDatesOrVersions;
        }

        public String getStatusIconClassName() {
            return statusIconClassName;
        }

        public BillType getBillType() {
            return billType;
        }

        public static LegislationDocStatus getStatusByColorCode(String colorCode) {
            for (LegislationDocStatus legislationDocStatus : LegislationDocStatus.values()) {
                if (legislationDocStatus.getColorCode().equalsIgnoreCase(colorCode)) {
                    return legislationDocStatus;
                }
            }
            throw new IllegalArgumentException("There is no status for the color code: " + colorCode);
        }

        public static LegislationDocStatus getStatusByName(String name) {
            for (LegislationDocStatus legislationDocStatus : LegislationDocStatus.values()) {
                if (legislationDocStatus.getName().equalsIgnoreCase(name)) {
                    return legislationDocStatus;
                }
            }
            throw new IllegalArgumentException("There is no status for the name: " + name);
        }

        public static LegislationDocStatus getStatusByColorCodeAndBillTypeName(String colorCode, String billTypeName) {
            for (LegislationDocStatus legislationDocStatus : LegislationDocStatus.values()) {
                if (legislationDocStatus.getColorCode().equalsIgnoreCase(colorCode) &&
                        BillType.getBillTypeByName(billTypeName) == legislationDocStatus.getBillType()) {
                    return legislationDocStatus;
                }
            }
            throw new IllegalArgumentException("There is no status for the color code '" + colorCode + "' " +
                    "and bill type name '" + billTypeName + "'");
        }
    }

    public enum BillType {
        NONE(""),
        PUBLIC_GOVERNMENT("Public Bill (Government Bill)"),
        PUBLIC_PRIVATE("Public Bill (Private Members' Bill)"),
        HYBRID("Hybrid Bill");

        private String typeName;

        BillType(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }

        public static BillType getBillTypeByName(String name) {
            for (BillType billType : BillType.values()) {
                if (billType.getTypeName().equalsIgnoreCase(name)) {
                    return billType;
                }
            }
            throw new IllegalArgumentException("There is no bill type for the name: " + name);
        }
    }

    public Document getParentArrangementDocument() {
        return parentArrangementDocument;
    }

    public void setParentArrangementDocument(Document parentArrangementDocument) {
        this.parentArrangementDocument = parentArrangementDocument;
    }

    public List<Document> getSectionsDocuments() {
        return sectionsDocuments;
    }

    public void setSectionsDocuments(List<Document> sectionsDocuments) {
        this.sectionsDocuments = sectionsDocuments;
    }

    public String getProvision() {
        return provision;
    }

    public void setProvision(String provision) {
        this.provision = provision;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public LegislationDocStatus getLegislationDocStatus() {
        return legislationDocStatus;
    }

    public void setLegislationDocStatus(LegislationDocStatus legislationDocStatus) {
        this.legislationDocStatus = legislationDocStatus;
    }

    public void setLegislationDocStatusByColorCode(String colorCode) {
        this.legislationDocStatus = LegislationDocStatus.getStatusByColorCode(colorCode);
    }

    public void setLegislationDocStatusByColorCodeAndBillTypeName(String colorCode, String billTypeName) {
        this.legislationDocStatus = LegislationDocStatus.getStatusByColorCodeAndBillTypeName(colorCode, billTypeName);
    }

    public void setLegislationDocStatusByStatusName(String statusName) {
        this.legislationDocStatus = LegislationDocStatus.getStatusByName(statusName);
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersionsCount() {
        return versionsCount;
    }

    public void setVersionsCount(int versionsCount) {
        this.versionsCount = versionsCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isTopLeveled() {
        return topLevelDocumentTypes.contains(provision);
    }

    public String getSearchResultLinkTitle() {
        return searchResultLinkTitle;
    }

    public void setSearchResultLinkTitle(String searchResultLinkTitle) {
        this.searchResultLinkTitle = searchResultLinkTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LegislationDocument)) return false;
        if (!super.equals(o)) return false;
        LegislationDocument that = (LegislationDocument) o;
        return rank == that.rank &&
                version == that.version &&
                versionsCount == that.versionsCount &&
                parentArrangementDocument.equals(that.parentArrangementDocument) &&
                sectionsDocuments.equals(that.sectionsDocuments) &&
                provision.equals(that.provision) &&
                searchResultLinkTitle.equals(that.searchResultLinkTitle) &&
                legislationDocStatus == that.legislationDocStatus &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate) &&
                aopProvisions.equals(that.aopProvisions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), parentArrangementDocument, sectionsDocuments, provision, searchResultLinkTitle, rank, legislationDocStatus, version, versionsCount, startDate, endDate, aopProvisions);
    }
}
