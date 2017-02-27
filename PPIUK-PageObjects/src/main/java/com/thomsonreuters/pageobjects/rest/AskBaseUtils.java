package com.thomsonreuters.pageobjects.rest;


import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceAskImpl;
import com.thomsonreuters.pageobjects.utils.askRewrite.askQuery.AskQueryType;
import com.thomsonreuters.pageobjects.utils.askRewrite.askQuery.NewAskQuery;


public class AskBaseUtils {
    private RestServiceAskImpl restServiceAsk = new RestServiceAskImpl();

    /**
     * @return the new {@link NewAskQuery} that was sent to the Ask Editorial System
     * based on the type {@link AskQueryType} of the Ask Query to be created
     *
     * @param queryDescription
     *        the {@link String} representation of the description in which the search will be
     *        carried out among a possible types of the Ask Query in the {@link AskQueryType}
     *
     *@param uniqueIdentifier
     *       the unique value that will be passed into the new Ask Query {@link NewAskQuery}
     *       when sending a request for creating it in the Ask Editorial System
     *       <p>Identifier will allow to find this specific Ask Query among a lot of others</p>
     */
    public NewAskQuery sendAskQuery(String queryDescription, String uniqueIdentifier) {
        AskQueryType askQueryType = AskQueryType.getQueryTypeByDescription(queryDescription);
        NewAskQuery newAskQuery = null;
        switch (askQueryType) {
            case QUESTION:
                newAskQuery = restServiceAsk.postAskQuestion(queryDescription, uniqueIdentifier);
                break;
            case COMMENT:
                newAskQuery = restServiceAsk.postAskReply(queryDescription, uniqueIdentifier);
                break;
            case FOLLOWUP:
                throw new UnsupportedOperationException("Followups are not implemented");
        }
        newAskQuery.setQueryType(askQueryType.name());
        return newAskQuery;
    }
}