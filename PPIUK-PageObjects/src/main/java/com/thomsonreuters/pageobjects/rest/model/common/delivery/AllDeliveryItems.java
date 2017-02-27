package com.thomsonreuters.pageobjects.rest.model.common.delivery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllDeliveryItems extends JsonObject {

    @JsonProperty("DocumentGuid")
    private String documentGuid;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("ItemDate")
    private String itemDate;

    @JsonProperty("ChargeAmount")
    private String chargeAmount;

    @JsonProperty("ReportingName")
    private String reportingName;

    @JsonProperty("BlockTypes")
    private List<Integer> blockTypes;

    public String getDocumentGuid() {
        return documentGuid;
    }

    public void setDocumentGuid(String documentGuid) {
        this.documentGuid = documentGuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemDate() {
        return itemDate;
    }

    public void setItemDate(String itemDate) {
        this.itemDate = itemDate;
    }

    public String getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getReportingName() {
        return reportingName;
    }

    public void setReportingName(String reportingName) {
        this.reportingName = reportingName;
    }

    public List<Integer> getBlockTypes() {
        return blockTypes;
    }

    public void setBlockTypes(List<Integer> blockTypes) {
        this.blockTypes = blockTypes;
    }
}
