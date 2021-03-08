package com.thomsonreuters.pageobjects.rest.service.impl;

import com.thomsonreuters.pageobjects.rest.auth.UDSCredentials;
import com.thomsonreuters.pageobjects.rest.model.request.DocumentMetaInfoRequeset;
import com.thomsonreuters.pageobjects.rest.model.response.DocumentMetaInfoResponse;
import com.thomsonreuters.pageobjects.rest.service.RestService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestServiceDocumentImpl extends RestServiceImpl implements RestService {

    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RestServiceDocumentImpl.class);
    private final String DOCUMENT_MODULE_AUTHORITY = System.getProperty("documentModuleAuthority",
            "document.int.next." + getEnvironment() + ".westlaw.com");
    private final String DOCUMENT_MODULE_URL = "http://" + DOCUMENT_MODULE_AUTHORITY + "/Document/v1";

    /**
     * POST request to
     * document/v1/MetaInfoList
     * with body
     * "DocumentGUIDS": [{
     * "docGuid": "",
     * "novusSearchHandle": ""
     * }]
     *
     * @return json[] object
     */
    public DocumentMetaInfoResponse[] postGetMetaInfo(String documentGuid) {
        ResponseEntity<DocumentMetaInfoResponse[]> result = null;
        DocumentMetaInfoRequeset docRequest = new DocumentMetaInfoRequeset();
        String request = docRequest.createMetaInfoRequest(documentGuid, "");
        HttpEntity<String> requestEntity = new HttpEntity<String>(request, configureHeaders());
        String requestTo = DOCUMENT_MODULE_URL + "/MetaInfoList";
        result = getRestTemplate().postForEntity(requestTo, requestEntity, DocumentMetaInfoResponse[].class);
        return result.getBody();
    }

    /**
     * GET request to
     * document/v1/MetaInfo/{documentGuid}
     *
     * @return json[] object
     */
    public DocumentMetaInfoResponse getMetaInfo(String documentGuid) {
        ResponseEntity<DocumentMetaInfoResponse> result = null;
        HttpHeaders httpHeaders = configureHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        String url = DOCUMENT_MODULE_URL + "/MetaInfo/" + documentGuid;
        result = getRestTemplate().exchange(url, HttpMethod.GET, requestEntity,
                DocumentMetaInfoResponse.class);
        return result.getBody();
    }
    
	public String getDocumentXML(String documentGuid) {
        ResponseEntity<String> result = null;
        HttpHeaders httpHeaders = configureHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        String url = DOCUMENT_MODULE_URL + "/RawXmlFormatted/"
                + documentGuid + "?clientId=PRACTICAL%20LAW&contextData=(sc.Default)&originalContext=Default";
        result = getRestTemplate().exchange(url, HttpMethod.GET, requestEntity, String.class);
        return result.getBody();
	}

    public HttpHeaders configureHeaders() {
        UDSCredentials credentials = getUDSCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-cobalt-host", DOCUMENT_MODULE_AUTHORITY);
        httpHeaders.set("Host", DOCUMENT_MODULE_AUTHORITY);
        httpHeaders.set("Content-Type", "application/jsonrequest; charset=utf-8");
        httpHeaders.set("x-cobalt-product-container", "WestlawNext");
        httpHeaders.set("X-Cobalt-Security-Container", "Cobalt");
        httpHeaders.set("X-Cobalt-Security-UDS", "http://uds.int.next." + getEnvironment() + ".westlaw.com/");
        httpHeaders.set("X-Cobalt-Security-ProductView", "PLCUK");
        httpHeaders.set("x-cobalt-security-userguid", credentials.getUserGuid());
        httpHeaders.set("x-cobalt-security-sessionid", credentials.getSessionID());
		httpHeaders.set("Cookie", "Co_SessionToken=" + credentials.getCoSessionToken() + "; site=" + getSite());
        httpHeaders.set("x-trmr-product", "WestlawNext");
        httpHeaders.set("Accept-Language", "en-GB");
        httpHeaders.set("Accept", "application/json");
        httpHeaders.set("X-TRMR-BusinessUnit", "LEGAL-US-CORE");
        return httpHeaders;
    }

}
