package com.thomsonreuters.pageobjects.utils.researchBrowse;


public enum ResearchContentTypeEnum {
    Cases("Cases",
            "Cases",
            "UK court decisions, full text transcripts and analysis, beginning in 1865, brought to you by Westlaw UK.",
            "/Browse/Home/RBHome/Cases160"),
    Journals("Journals",
            "Journals",
            "Over half a million abstracts of articles from legal journals published in the United Kingdom and Europe, brought to you by Westlaw UK''s Legal Journals Index.",
            "/Browse/Home/RBHome/Journals"),
    Legislation("Legislation",
            "Legislation",
            "Fully consolidated full text of Acts since 1267 and Statutory instruments since 1948 brought to you by Westlaw UK.",
            "/Browse/Home/RBHome/Legislation"),

    CasesTax("Tax",
            "Cases-Tax",
            "UK court decisions, full text transcripts and analysis, beginning in 1865, brought to you by Westlaw UK.",
            "/Browse/Home/RBHome/Cases160/CasesTax"),
    JournalsTax("Tax",
            "Journals Tax",
            "Over half a million abstracts of articles from legal journals published in the United Kingdom and Europe, brought to you by Westlaw UK's Legal Journal Index.",
            "/Browse/Home/RBHome/Journals/JournalsTax"),
    LegislationTax("Legislation",
            "Legislation Tax",
            "Fully consolidated full text of Acts since 1267 and Statutory instruments since 1948 brought to you by Westlaw UK.",
            "/Browse/Home/RBHome/Legislation/LegislationTax");


    private String linkText;
    private String heading;
    private String staticText;
    private String relativeUrl;

    ResearchContentTypeEnum(String linkText, String heading, String staticText, String url) {
        this.linkText = linkText;
        this.heading = heading;
        this.staticText = staticText;
        this.relativeUrl = url;
    }

    public String getStaticText() {
        return staticText;
    }

    public String getLinkText() {
        return linkText;
    }

    public String getHeading() {
        return heading;
    }

    public String getRelativeUrl() {
        return relativeUrl;
    }
}
