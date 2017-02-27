package com.thomsonreuters.pageobjects.rest.model.request.delivery.initiateDelivery;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thomsonreuters.pageobjects.rest.model.request.ABaseRequest;

import java.util.ArrayList;
import java.util.List;

class RecipientOptions extends ABaseRequest {

    private static final String EMAIL_ADDRESSES_NAME = "EmailAddresses";
    private static final String EMAIL_CC_ADDRESSES_NAME = "EmailCcAddresses";
    private static final String EMAIL_SUBJECT_NAME = "EmailSubject";
    private static final String COMMENT_NAME = "Comment";
    private static final String MATTER_NUMBER_NAME = "MatterNumber";
    private static final String CATEGORY_NAME = "Category";
    private static final String RESEARCH_EVENT_NAME = "ResearchEvent";
    private static final String RESEARCH_TITLE_NAME = "ResearchTitle";

    // Default values
    private List<String> emailAddresses = new ArrayList<>();
    private List<String> emailCcAddresses = new ArrayList<>();
    private String emailSubject = "";
    private String comment = "";
    private String matterNumber = "";
    private String category = "";
    private String researchEvent = "";
    private String researchTitle = "";

    public RecipientOptions setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
        return this;
    }

    public RecipientOptions setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public RecipientOptions setMatterNumber(String matterNumber) {
        this.matterNumber = matterNumber;
        return this;
    }

    public RecipientOptions setCategory(String category) {
        this.category = category;
        return this;
    }

    public RecipientOptions setResearchEvent(String researchEvent) {
        this.researchEvent = researchEvent;
        return this;
    }

    public RecipientOptions setResearchTitle(String researchTitle) {
        this.researchTitle = researchTitle;
        return this;
    }

    public RecipientOptions addEmailAddress(String emailAddress) {
        this.emailAddresses.add(emailAddress);
        return this;
    }

    public RecipientOptions addCcEmailAddress(String emailCcAddress) {
        this.emailCcAddresses.add(emailCcAddress);
        return this;
    }

    @Override
    public ObjectNode getNode() {
        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
        jsonFactory.setCodec(objectMapper);
        ObjectNode recipientOptionsNode = objectMapper.createObjectNode();
        recipientOptionsNode.set(EMAIL_ADDRESSES_NAME, objectMapper.valueToTree(emailAddresses));
        recipientOptionsNode.set(EMAIL_CC_ADDRESSES_NAME, objectMapper.valueToTree(emailCcAddresses));
        recipientOptionsNode.put(EMAIL_SUBJECT_NAME, emailSubject);
        recipientOptionsNode.put(COMMENT_NAME, comment);
        recipientOptionsNode.put(MATTER_NUMBER_NAME, matterNumber);
        recipientOptionsNode.put(CATEGORY_NAME, category);
        recipientOptionsNode.put(RESEARCH_EVENT_NAME, researchEvent);
        recipientOptionsNode.put(RESEARCH_TITLE_NAME, researchTitle);
        return recipientOptionsNode;
    }
}
