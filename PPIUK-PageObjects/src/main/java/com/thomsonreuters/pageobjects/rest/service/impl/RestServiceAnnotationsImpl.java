package com.thomsonreuters.pageobjects.rest.service.impl;

import com.thomsonreuters.pageobjects.rest.model.response.document.annotation.Annotation;
import com.thomsonreuters.pageobjects.rest.model.response.document.annotation.AnnotationsResponse;
import com.thomsonreuters.pageobjects.rest.service.RestService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel_Ardenka on 28/06/2016.
 * Please, use the methods from this class when the document page is opened
 */
@Service
public class RestServiceAnnotationsImpl extends RestServiceImpl implements RestService {

    // This code is expected when annotation successfully deleted
    private static final Integer STATUS_CODE_NO_CONTENT = 204;
    private final List<Integer> responseCodes = new ArrayList<>();
    // Indicates whether there was an attempt to delete annotation
    private boolean isInvoked = false;

    /**
     * Delete all annotations for the document with given GUID
     * WARNING: Method should be called when the document page is opened
     * WARNING 2: After method invocation document page should be refreshed
     * WARNING 3: Shared annotations will be skipped, because only author can delete them
     *
     * @param docGuid Document GUID where annotations should be removed
     */
    public boolean deleteAnnotations(String docGuid) {
        AnnotationsResponse annotationResponse = getAnnotations(docGuid);
        deleteAnnotations(annotationResponse.getDocumentLevelNotes(), "Notes");
        deleteAnnotations(annotationResponse.getInlineHighlightAndNotes(), "Highlights");
        deleteAnnotations(annotationResponse.getOrphanedHighlightsAndNotes(), "Highlights");
        return isAnnotationsDeleted();
    }

    @Override
    public HttpHeaders configureHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/json");
        httpHeaders.set("Accept-Encoding", "identity");
        httpHeaders.set("Accept-Language", "en-GB");
        httpHeaders.set("Cookie", webDriverDiscovery.getBrowserCookiesAsString());
        httpHeaders.set("Connection", "keep-alive");
        httpHeaders.set("Host", webDriverDiscovery.getCurrentRootAddress(false));
        httpHeaders.set("Referer", webDriverDiscovery.getCurrentUrl());
        httpHeaders.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0");
        httpHeaders.set("X-Requested-With", "XMLHttpRequest");
        httpHeaders.set("x-cobalt-exectype", "async");
        httpHeaders.set("x-cobalt-host", "document.int.next.demo.westlaw.com");
        httpHeaders.set("x-cobalt-pcid", getXCobaltPcId());
        httpHeaders.set("x-cobalt-rtid", getXCobaltRtId());
        httpHeaders.set("x-cobalt-documentContentCacheKey", getXCobaltDocumentContentCacheKey());
        return httpHeaders;
    }

    /**
     * GET annotations for the document
     *
     * @param docGuid Document GUID
     * @return Object {@link AnnotationsResponse} with lists of annotations
     */
    private AnnotationsResponse getAnnotations(String docGuid) {
        LOG.info("-------------------BEGIN getAnnotations--------------------");
        HttpHeaders httpHeaders = configureHeaders();
        String requestTo = webDriverDiscovery.getCurrentRootAddress(true) + "/Document/v1/UserDocuments/" + docGuid +
                "/null/Annotations?onePassUserName=" + getUserName() + "&tz=GMT Standard Time";
        LOG.info("TO: " + requestTo);
        LOG.info("HEADERS: " + httpHeaders);
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        LOG.info("REQ: " + requestEntity.toString());
        HttpEntity<AnnotationsResponse> response = getRestTemplate().exchange(requestTo, HttpMethod.GET, requestEntity, AnnotationsResponse.class);
        LOG.info("RESP: " + response.toString());
        LOG.info("-------------------END getAnnotations--------------------");
        return response.getBody();
    }

    /**
     * Delete annotation (shared annotations will be skipped, because only author can delete them)
     *
     * @param annotation Annotation which should be deleted {@link Annotation}
     * @param type Type of annotation. Possible values:
     *             - Notes - for annotations on the document level
     *             - Highlights - for highlighted annotations and orphaned annotations which were created in the
     *                            document text
     */
    private void deleteAnnotation(Annotation annotation, String type) {
        if (annotation.isCreatedByCurrentUser()) {
            isInvoked = true;
            LOG.info("-------------------BEGIN deleteAnnotation--------------------");
            HttpHeaders httpHeaders = configureHeaders();
            String requestTo = getProtocol() + getCurrentBaseUrl() + "/Document/v1/UserDocuments/" + annotation.getDocumentGuid() +
                    "/Annotations/" + type + "/" + annotation.getAnnotationSid();
            LOG.info("TO: " + requestTo);
            LOG.info("HEADERS: " + httpHeaders);
            HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
            LOG.info("REQ: " + requestEntity.toString());
            ResponseEntity<AnnotationsResponse> response = getRestTemplate().exchange(requestTo, HttpMethod.DELETE, requestEntity, AnnotationsResponse.class);
            LOG.info("RESP: " + response.toString());
            LOG.info("-------------------END deleteAnnotation--------------------");
            responseCodes.add(response.getStatusCode().value());
        } else {
            LOG.info("Annotation with ID " + annotation.getAnnotationSid() + " is shared and can't be deleted by " +
                    "current user");
        }
    }

    /**
     * Delete the list of annotations (shared annotations will be skipped, because only author can delete them)
     *
     * @param annotations List with annotations to delete
     * @param type Type of annotation. For possible values, please see the javadoc for
     *             {@link #deleteAnnotation(Annotation, String)}
     */
    private void deleteAnnotations(List<Annotation> annotations, String type) {
        responseCodes.clear();
        isInvoked = false;
        for (Annotation annotation : annotations) {
            deleteAnnotation(annotation, type);
        }
    }

    /**
     * Check that all annotation were successfully deleted. This method checks that all response codes in the
     * {@link #responseCodes} collection are equals to {@link #STATUS_CODE_NO_CONTENT}.
     *
     * @return True - if check was passed. False - otherwise.
     */
    private boolean isAnnotationsDeleted() {
        for (Integer responseCode : responseCodes) {
            if (isInvoked && !responseCode.equals(STATUS_CODE_NO_CONTENT)) {
                return false;
            }
        }
        return !isInvoked || !responseCodes.isEmpty();
    }

}
