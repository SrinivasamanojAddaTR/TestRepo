package com.thomsonreuters.pageobjects.rest.service.impl;


import com.thomsonreuters.pageobjects.rest.model.request.AskFormRequestData;
import com.thomsonreuters.pageobjects.rest.service.RestService;
import com.thomsonreuters.pageobjects.utils.ask_re_write.ask_query.AskQueryType;
import com.thomsonreuters.pageobjects.utils.ask_re_write.ask_query.NewAskQuery;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@Service
public class RestServiceAskImpl extends RestServiceImpl implements RestService{

    private static final String ASK_QUESTION_FORM = "/AskForm/PostForm.html";
    private static final String ASK_COMMENT_FORM = "/AskCommentForm/PostComment";

    private AskFormRequestData askFormRequest = new AskFormRequestData();

    /**
     * POST request to /AskForm/PostForm.html with
     * body Question={QUESTION_TEXT}
     * &ResourceId={DOCUMENT_URL}
     * &FirstName={NAME}
     * &LastName={LASTNAME}
     * &Email={USER_EMAIL}
     * &OrganisationType={ORGANISATION}
     * &Position={POSITION}
     * &Jurisdiction=UK
     * &AnsweringService={PRACTICE_AREA}
     * &Submit=Submit
     *
     * @param queryDescription
     *            description of the data set from AskFormRequestData request entity builder
     * @param uniqueIdentifier
     *            generated date & timestamp value to make submitted query unique
     *
     * @return text/html object
     */
    public NewAskQuery postAskQuestion(String queryDescription, String uniqueIdentifier) {
        NewAskQuery newAskQuery = askFormRequest.getAskQueryRequestData(queryDescription, uniqueIdentifier);
        postFormRequest(newAskQuery, AskQueryType.QUESTION);
        return newAskQuery;
    }

    /**
     * POST request to /AskCommentForm/PostComment with
     * body documentId={DOCUMENT_GUID}
     * &commentId={COMMENT_ID} optional
     * &comment={COMMENT_TEXT}
     * &userFullName={USER_FULL_NAME} optional
     * &userFirmName={USER_FIRM_NAME} optional
     * &commentSubmit=Submit
     *
     * @param queryDescription
     *            description of the data set from AskFormRequestData request entity builder
     * @param uniqueIdentifier
     *            generated date & timestamp value to make submitted query unique
     *
     * @return text/html object
     */
    public NewAskQuery postAskReply(String queryDescription, String uniqueIdentifier) {
        NewAskQuery newAskQuery = askFormRequest.getAskQueryRequestData(queryDescription, uniqueIdentifier);
        postFormRequest(newAskQuery, AskQueryType.COMMENT);
        return newAskQuery;
    }

    private void postFormRequest(NewAskQuery newAskQuery, AskQueryType askQueryType) {
        MultiValueMap<String, String> mapRequest = new LinkedMultiValueMap<>();
        String requestTo = null;
        if (askQueryType.equals(AskQueryType.QUESTION)) {
            mapRequest.setAll(newAskQuery.getKeyValuesForRequest(askQueryType));
            requestTo = this.getProtocol() + getCurrentBaseUrl() + ASK_QUESTION_FORM;
        } else if (askQueryType.equals(AskQueryType.COMMENT)) {
            mapRequest.setAll(newAskQuery.getKeyValuesForRequest(askQueryType));
            requestTo = this.getProtocol() + getCurrentBaseUrl() + ASK_COMMENT_FORM;
        }
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(mapRequest, configureHeaders());
        LOG.info("Request: {}", requestEntity);
        HttpEntity<String> response = getRestTemplate().postForEntity(requestTo, requestEntity, String.class);
        LOG.info("Response: {}", response);
    }

    @Override
    public HttpHeaders configureHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/json");
        httpHeaders.set("Accept-Encoding", "gzip, deflate, br");
        httpHeaders.set("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
        httpHeaders.set("Content-Type", "application/x-www-form-urlencoded");
        httpHeaders.set("Cookie", webDriverDiscovery.getBrowserCookiesAsString());
        httpHeaders.set("Host", getWebDriverDiscovery().getCurrentRootAddress(false));
        httpHeaders.set("Origin", getWebDriverDiscovery().getCurrentRootAddress(true));
        httpHeaders.set("Referer", getWebDriverDiscovery().getCurrentRootAddress(true));
        httpHeaders.set("Upgrade-Insecure-Requests", "1");
        return httpHeaders;
    }
}