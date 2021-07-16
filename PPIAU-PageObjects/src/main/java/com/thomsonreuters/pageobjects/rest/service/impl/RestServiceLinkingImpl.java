package com.thomsonreuters.pageobjects.rest.service.impl;


import com.thomsonreuters.pageobjects.pages.page_creation.HomePage;
import com.thomsonreuters.pageobjects.rest.service.RestService;
import com.thomsonreuters.pageobjects.utils.novus.PlcLegacyNovusUtils;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestServiceLinkingImpl extends RestServiceImpl implements RestService {

	private HomePage homePage = new HomePage();
    private static final String HEADER_ACCEPT_HTML_XML = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
    private static final int STATUS_CODE_OK = 200;
    private static final String FATWIRE_TOOL = "http://us.p02edi.practicallaw.com/cs/Satellite/?pagename=XMLWrapper&childpagename=PLC/PLC_Doc_C/XmlDataViewExt&plcref=";
    private static final String ENTITYID_TOOL = "http://entityid.int.next.westlaw.com/EntityIdentification/v1/find/document?findValue=Practical Law Resource ID ";
    private static final String VELMA_TOOL = "https://legaltechtools.int.thomsonreuters.com/Velma/Novus/Document?guid=";
    private int latestResponseCode = -1;
    private PlcLegacyNovusUtils plcLegacyNovusUtils = new PlcLegacyNovusUtils();

    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RestServiceLinkingImpl.class);

    /**
     * Get document GUID for the PL+ UK for the PLC Ref
     *
     * @param plcRef Document plcRef
     * @return String that contains document GUID
     */
    public String getGuid(String plcRef) {
        try {
            return plcLegacyNovusUtils.getDocumentByPlcRef(plcRef).getGuid();
        } catch (IllegalArgumentException e) {
            LOG.info("No documents found with plcRef " + plcRef);
            return "";
        }
    }

    /**
     * Get XML document structure from FatWire
     * WARNING! There is an editorial tool used that may get document with non-published changes
     *
     * @param plcRef Document plcRef
     * @return String that contains XML Document
     */
    public String getXmlDocumentFromFatwire(String plcRef) {
        return getResponseFor(FATWIRE_TOOL + plcRef, HEADER_ACCEPT_HTML_XML);
    }

    /**
     * Get XML document structure from Novus
     *
     * @param guid Document GUID
     * @return String XML representation of the document
     */
    public String getXmlDocumentFromNovus(String guid) {
        return getResponseFor(VELMA_TOOL + guid, HEADER_ACCEPT_HTML_XML);
    }


    /**
     * Check if link OK and returning document
     *
     * @param hrefAttr Href attribute from "a" element or just URL. If value point to relative resource then
     *                 current domain will be added to absolute resource path
     * @return True - if response code for link is 200 and response body not contains "error" word.
     */
    public boolean isLinkReturnsTheDocument(String hrefAttr) {
        getResponseFor(hrefAttr, HEADER_ACCEPT_HTML_XML);
        return latestResponseCode == STATUS_CODE_OK;
    }

    /**
     * Get HTML page source for page presented by navigating to link
     *
     * @param link Link of page (full or relative)
     * @return String with page HTML code
     */
    public String getPageSourceForLink(String link) {
        return getResponseFor(link, HEADER_ACCEPT_HTML_XML);
    }

    /**
     * Get Response body as string for link
     *
     * @param absoluteOrRelativeUrl Full or relative URL
     * @param acceptHeader          "Accept" header value (e.g., "text/html", "application/json", ...)
     *
     * @return String with response body
     */
    private String  getResponseFor(String absoluteOrRelativeUrl, String acceptHeader) {
        return getResponseFor(absoluteOrRelativeUrl, acceptHeader, String.class);
    }

    /**
     * Get Response object for link
     *
     * @param absoluteOrRelativeUrl Full or relative URL
     * @param acceptHeader          "Accept" header value (e.g., "text/html", "application/json", ...)
     * @param expectedResponseType Expected type fo the response
     *
     * @return Object with response of expected type
     */
    private <T> T getResponseFor(String absoluteOrRelativeUrl, String acceptHeader, Class<T> expectedResponseType) {
        HttpHeaders httpHeaders = configureHeaders();
        httpHeaders.set("Accept", acceptHeader);
        String requestTo = absoluteOrRelativeUrl;
        if (!absoluteOrRelativeUrl.startsWith("http")) {
            String currUrl = homePage.getCurrentUrl();
            String[] splitedUrlBySlash = currUrl.split("/");
            requestTo = splitedUrlBySlash[0] + "//" + splitedUrlBySlash[2];
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        //LOG.info("Linking GET REQUEST '" + requestEntity.toString() + "', TO: " + requestTo);
        ResponseEntity<T> response = getRestTemplate().exchange(requestTo, HttpMethod.GET, requestEntity, expectedResponseType);
        //LOG.info("Linking GET RESPONSE '" + response.getBody() + "'");
        latestResponseCode = response.getStatusCode().value();
        return response.getBody();
    }

    @Override
    public HttpHeaders configureHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Cookie", webDriverDiscovery.getBrowserCookiesAsString());
        httpHeaders.set("Content-Type", "application/json; charset=UTF-8");
        return httpHeaders;
    }
}
