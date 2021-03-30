package com.thomsonreuters.pageobjects.rest.model.request;

import com.thomsonreuters.pageobjects.utils.askRewrite.askQuery.NewAskQuery;
import com.thomsonreuters.pageobjects.utils.askRewrite.dataManager.AskQueryDataYamlReader;


public class AskFormRequestData {
    /**
     * @return the one {@link NewAskQuery} from a number of Ask Queries
     * constructed in the {@link AskQueryDataYamlReader}
     *
     * @param description
     *        the {@link String} representation of the description in which the search
     *        will be carried out among a number of {@link NewAskQuery} new Ask Queries
     *
     * @param uniqueIdentifier
     *        the unique value that will be passed into the new Ask Query {@link NewAskQuery}
     *        <p>Identifier will allow to find this specific Ask Query among a lot of others</p>
     */
    public NewAskQuery getAskQueryRequestData(String description, String uniqueIdentifier) {
        AskQueryDataYamlReader askFormDataYamlReader = new AskQueryDataYamlReader();
        NewAskQuery requestTemplate = askFormDataYamlReader.getNewAskQuery(description);
        appendTestIdentifier(requestTemplate, uniqueIdentifier);
        return requestTemplate;
    }

    private NewAskQuery appendTestIdentifier(NewAskQuery request, String identifier) {
        request.setQueryText(request.getQueryText() + identifier);
        return request;
    }
}
