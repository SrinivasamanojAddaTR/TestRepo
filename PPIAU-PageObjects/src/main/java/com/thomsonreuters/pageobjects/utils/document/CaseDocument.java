package com.thomsonreuters.pageobjects.utils.document;

import com.thomsonreuters.pageobjects.common.Link;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Pavel_Ardenka on 11/07/2016.
 */
public class CaseDocument extends Document {

    private List<String> partyNames = new ArrayList<>();
    private CaseDocumentStatus documentStatus;
    private String docTitleHref;
    private String court;
    private String judgmentDate;
    private List<Link> whereReported;
    private String summary;
    private String subject;
    private String keywords;
    private List<Link> termsInContext;

    public enum CaseDocumentStatus {
        NO_TREATMENT("", "0", "", new String[]{"No Treatment"}),
        NEUTRAL_TREATMENT("Positive/Neutral Judicial Consideration", "1", "icon_circle_checkmark_green_small", new String[]{"Positive/Neutral"}),
        MILDLY_TREATMENT("Mixed Judicial Consideration", "2", "icon_circle_exclamation_orange_small", new String[]{"Mixed/Mildly Negative"}),
        NEGATIVE_TREATMENT("Negative Judicial Consideration", "3", "icon_circle_minus_red_small", new String[]{"Negative"}),
        POSITIVE_TREATMENT(NEUTRAL_TREATMENT.getName(), "4", NEUTRAL_TREATMENT.getStatusIconClassName(), NEUTRAL_TREATMENT.getCaseTreatmentFacets()),
        POSSIBLY_TREATMENT(NEUTRAL_TREATMENT.getName(), "5", NEUTRAL_TREATMENT.getStatusIconClassName(), NEUTRAL_TREATMENT.getCaseTreatmentFacets()),
        APPEAL_NO_TREATMENT("Appeal Outstanding", "0A", MILDLY_TREATMENT.getStatusIconClassName(), new String[]{"No Treatment", "Appeal Outstanding"}),
        APPEAL_NEUTRAL("Positive/Neutral Judicial Consideration With Appeal Outstanding", "1A", "icon_circle_checkmark_green_exclamation_yellow_small", new String[]{"Positive/Neutral", APPEAL_NO_TREATMENT.getName()}),
        APPEAL_MILDLY("Mixed Judicial Consideration With Appeal Outstanding", "2A", MILDLY_TREATMENT.getStatusIconClassName(), new String[]{"Mixed/Mildly Negative", APPEAL_NO_TREATMENT.getName()}),
        APPEAL_NEGATIVE("Negative Judicial Consideration With Appeal Outstanding", "3A", "icon_circle_minus_red_exclamation_yellow_small", new String[]{"Negative", APPEAL_NO_TREATMENT.getName()}),
        APPEAL_POSITIVE(APPEAL_NEUTRAL.getName(), "4A", APPEAL_NEUTRAL.getStatusIconClassName(), APPEAL_NEUTRAL.getCaseTreatmentFacets()),
        APPEAL_POSSIBLY(APPEAL_NEUTRAL.getName(), "5A", APPEAL_NEUTRAL.getStatusIconClassName(), APPEAL_NEUTRAL.getCaseTreatmentFacets());

        private String name;
        private String colorCode;
        private String statusIconClassName;
        private String[] caseTreatmentFacets;

        CaseDocumentStatus(String name, String colorCode, String statusIconClassName, String[] caseTreatmentFacets) {
            this.name = name;
            this.colorCode = colorCode;
            this.statusIconClassName = statusIconClassName;
            this.caseTreatmentFacets = caseTreatmentFacets;
        }

        public String getName() {
            return name;
        }

        public String getColorCode() {
            return colorCode;
        }

        public String getStatusIconClassName() {
            return statusIconClassName;
        }

        public String[] getCaseTreatmentFacets() {
            return caseTreatmentFacets;
        }

        public static CaseDocumentStatus getStatusByColorCode(String colorCode) {
            for (CaseDocumentStatus caseDocumentStatus : CaseDocumentStatus.values()) {
                if (caseDocumentStatus.getColorCode().equalsIgnoreCase(colorCode)) {
                    return caseDocumentStatus;
                }
            }
            throw new IllegalArgumentException("There is no status for the color code: " + colorCode);
        }

        public static CaseDocumentStatus getStatusByName(String name) {
            for (CaseDocumentStatus caseDocumentStatus : CaseDocumentStatus.values()) {
                if (caseDocumentStatus.getName().equalsIgnoreCase(name)) {
                    return caseDocumentStatus;
                }
            }
            throw new IllegalArgumentException("There is no status for the name: " + name);
        }
    }

    public List<String> getPartyNames() {
        return partyNames;
    }

    public void setPartyNames(List<String> partyNames) {
        this.partyNames = partyNames;
    }

    public String getPartiesAsString() {
        StringBuilder sb = new StringBuilder();
        for (String party : partyNames) {
            sb.append(party);
        }
        return sb.toString();
    }

    public CaseDocumentStatus getCasesDocStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(CaseDocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getDocTitleHref() {
        return docTitleHref;
    }

    public void setDocTitleHref(String docTitleHref) {
        this.docTitleHref = docTitleHref;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getJudgmentDate() {
        return judgmentDate;
    }

    public void setJudgmentDate(String date) {
        this.judgmentDate = date;
    }

    public List<Link> getWhereReported() {
        return Collections.unmodifiableList(whereReported);
    }

    public void setWhereReported(List<Link> whereReported) {
        this.whereReported = whereReported;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<Link> getTermsInContext() {
        return Collections.unmodifiableList(termsInContext);
    }

    public void setTermsInContext(List<Link> termsInContext) {
        this.termsInContext = termsInContext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CaseDocument that = (CaseDocument) o;
        return Objects.equals(partyNames, that.partyNames) &&
                documentStatus == that.documentStatus &&
                Objects.equals(docTitleHref, that.docTitleHref) &&
                Objects.equals(court, that.court) &&
                Objects.equals(judgmentDate, that.judgmentDate) &&
                Objects.equals(whereReported, that.whereReported) &&
                Objects.equals(summary, that.summary) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(keywords, that.keywords) &&
                Objects.equals(termsInContext, that.termsInContext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), partyNames, documentStatus, docTitleHref, court, judgmentDate, whereReported, summary, subject, keywords, termsInContext);
    }

}
