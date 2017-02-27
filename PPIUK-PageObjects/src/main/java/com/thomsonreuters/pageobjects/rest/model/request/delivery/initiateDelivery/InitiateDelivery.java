package com.thomsonreuters.pageobjects.rest.model.request.delivery.initiateDelivery;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thomsonreuters.pageobjects.rest.model.request.ABaseRequest;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class InitiateDelivery extends ABaseRequest {

    private static final String PREFERENCES_STATE_NAME = "PreferencesState";
    private static final String DELIVERY_OPTIONS_NAME = "DeliveryOptions";

    public enum DocFormat {
        Pdf, // Add another formats
        Rtf,
        Doc,
        Csv,
        Xls,
        Zip;

        public static DocFormat getFormatIgnoreCase(String format) {
            for (DocFormat docFormat : DocFormat.values()) {
                if (format.equalsIgnoreCase(docFormat.toString())) {
                    return docFormat;
                }
            }
            return null;
        }

        public static DocFormat getFormatForFile(File file) {
            return getFormatIgnoreCase(FilenameUtils.getExtension(file.getName()));
        }
    }

    public enum WhatToDeliverOption {
        DocumentOnly("Document"),
        DocumentAndDraftingNotes("Document and Drafting Notes"),
        DraftingNotesOnly("Only Drafting Notes");

        private String uiName;

        WhatToDeliverOption(String uiName) {
            this.uiName = uiName;
        }

        public String getUiName() {
            return uiName;
        }

        public static WhatToDeliverOption getByUiName(String uiName) {
            for (WhatToDeliverOption deliverOption : WhatToDeliverOption.values()) {
                if (deliverOption.getUiName().equalsIgnoreCase(uiName)) {
                    return deliverOption;
                }
            }
            return null;
        }
    }

    @Override
    public ObjectNode getNode() {
        return getCustomRequestNode(null, null);
    }

    private ObjectNode getCustomRequestNode(PreferencesState preferencesState, DeliveryOptions deliveryOptions) {
        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
        jsonFactory.setCodec(objectMapper);
        ObjectNode result = objectMapper.createObjectNode();
        result.set(PREFERENCES_STATE_NAME, (preferencesState == null) ? new PreferencesState().getNode() : preferencesState.getNode());
        result.set(DELIVERY_OPTIONS_NAME, (deliveryOptions == null) ? new DeliveryOptions().getNode() : deliveryOptions.getNode());
        return result;
    }

}
