package com.thomsonreuters.pageobjects.rest;


import com.google.common.base.Preconditions;
import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.driver.framework.WebDriverDiscovery;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.FileActions;
import com.thomsonreuters.pageobjects.pages.urls.plcuk.KHDocumentPage;
import com.thomsonreuters.pageobjects.rest.model.request.delivery.initiate_delivery.InitiateDelivery;
import com.thomsonreuters.pageobjects.rest.model.response.delivery.initiate_delivery.InitiateDeliveryResponse;
import com.thomsonreuters.pageobjects.rest.model.response.delivery.status.StatusResponse;
import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceDeliveryImpl;
import com.thomsonreuters.pageobjects.utils.pdf.PDFBoxUtil;
import com.thomsonreuters.pageobjects.utils.pl_plus_research_docdisplay.AssetPageUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.BadLocationException;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class DeliveryBaseUtils {

    private RestServiceDeliveryImpl deliveryService = new RestServiceDeliveryImpl();
    private CommonMethods commonMethods = new CommonMethods();
    private WebDriverDiscovery webDriverDiscovery = commonMethods.getWebDriverDiscovery();
    private FileActions fileActions = new FileActions();
    private KHDocumentPage khDocumentPage = new KHDocumentPage();

    private static final Logger LOG = LoggerFactory.getLogger(DeliveryBaseUtils.class);
    private static final String REGEX_NEW_LINE = "\\r\\n";
    private static final String REPLACE_NEW_LINE_WITH = " ";
    private static final String HREF_ATTR = "href";
	private static File downloadedDoc;

    /**
     * Download document via HTTP and check it not empty
     * WARNING: method should be called when document ready for download.
     *
     * @param docFormat   Document format {@link InitiateDelivery.DocFormat}
     * @param isPrintable Is delivery called for printable document?
     */
    public boolean isDocDownloadedAndChecked(InitiateDelivery.DocFormat docFormat, boolean isPrintable) {
        File downloadedDocument = downloadAndGetDocument(isPrintable);
        return isDocumentNotEmpty(downloadedDocument, docFormat);
    }

    /**
     * Check that downloaded document is not empty
     *
     * @param documentFile Downloaded document file
     * @param docFormat    Document format
     * @return True - if check passed. Otherwise - false.
     */
    public boolean isDocumentNotEmpty(File documentFile, InitiateDelivery.DocFormat docFormat) {
        try {
            switch (docFormat) {
                case PDF:
                    return !new PDFBoxUtil().extractText(documentFile.getAbsolutePath()).trim().isEmpty();
                case RTF:
                case DOC:
                    return !new AssetPageUtils().getTextFromFileWithRTForDOCextension(documentFile.getAbsolutePath()).isEmpty();
                case CSV:
                case XLS:
                case ZIP:
                    return documentFile.length() > 0;
                default:
                    return false;
            }
        } catch (IOException e) {
            LOG.warn("Document downloading / parsing failed", e);
            return false;
        }
    }

    /**
     * Get transaction ID for delivery using Cobalt JS library
     * Call method when file ready to download ("Ready For Download" pop-up is showing)
     *
     * @return Transaction id which can be given to Status Response to check deliver status
     */
    public String getTransactionId() {
        Function<AbstractPage, String> waitCondition = page -> {
            String transactionId = (String) page.executeScript("return typeof Cobalt_Testing_Automation != 'undefined' ? Cobalt_Testing_Automation.CurrentDeliveryTransactionId :  Cobalt.Delivery.DeliveryOptionsDialog.Instance()._currentTransactionId;");
                return (transactionId != null && !transactionId.isEmpty()) ? transactionId : null;
            };
        return AbstractPage.waitFor(waitCondition, khDocumentPage);
    }

    /**
     * Download document and get downloaded doc as file.
     * WARNING: method should be called when document prepared in UI ("Ready For Download" dialog is displayed)
     *
     * @return File with downloaded document
     */
    public File downloadAndGetDocument(boolean isPrintable) {
        InitiateDeliveryResponse initiateDeliveryResponse = new InitiateDeliveryResponse();
        initiateDeliveryResponse.setTransactionId(getTransactionId());
        StatusResponse statusResponse = getStatusResponseForInitiateDeliveryResponse(initiateDeliveryResponse);
        File result = deliveryService.downloadDocumentAndGetFile(statusResponse, isPrintable);
        setDownloadedDoc(result);
        return result;
    }

    /**
     * Download document via "Open In Word" action
     *
     * @return File with downloaded document
     */
    public File downloadViaOpenInWordAndGetDocument() {
        String fileNameScript = "($('.co_title') !== null ? $('.co_title').text() : 'Quick Draft').replace(/\\W/g, '')";
        String fileUrl = (String) khDocumentPage.executeScript(
                "return $('.kh_standardDocumentAttachment a').attr('href') + '&imageFileName=' + " + fileNameScript + ";");
        String fileName = (String) khDocumentPage.executeScript(
                "return " + fileNameScript + ";");
        return deliveryService.getFileViaHttp(fileUrl, fileName);
    }

    /**
     * Download document as Firm Style
     * WARNING:
     * 1. User should have an ability to use firm style (e.g., FirmStyle == Grant option on the routing page)
     * 2. Test should be on the document view page for properly work of this method
     *
     * Just FYI: FS url can be obtained by JS "return Cobalt.Url.Absolute(Cobalt.Url.Page.GetDocumentInFirmStyle({" +
     *                                        "legacyId: documentTabView.documentData.LegacyId, " +
     *                                        "documentGuid: documentTabView.ViewData.DocumentGuid})) + \"&cookie=true\"";
     *
     * @return File with downloaded Firm Style document
     */
    public File downloadFsDocument() {

        ExpectedCondition<Boolean> condition = webDriver -> {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
            return (jsExecutor.executeScript("return typeof documentTabView != 'undefined' && typeof Cobalt.Url.Absolute != 'undefined';")).equals("true");
        };

        AbstractPage.waitFor(condition, webDriverDiscovery.getWebDriver());
        String plcRefScript = "return documentTabView.documentData.LegacyId;";
        String fileUrl = (String) khDocumentPage.executeScript("return " +
                "Cobalt.Url.Absolute(Cobalt.Url.Page.GetDocumentInFirmStyle({" +
                "legacyId: documentTabView.documentData.metaInformation.legacyId, " +
                "documentGuid: documentTabView.documentData.metaInformation.docGuid})) + \"&cookie=true\";");
        String fileName =khDocumentPage.executeScript(plcRefScript) + ".fs.doc";
        return deliveryService.getFileViaHttp(fileUrl, fileName);
    }

    /**
     * Download document as Lawtel Transcript
     * WARNING:
     * 1. Test should be on the document view page for properly work of this method
     *
     * @return File with downloaded Lawtel Transcript document
     */
    public File downloadLawtelTranscript() {
        String href = khDocumentPage.getDocumentAttachment().getAttribute(HREF_ATTR);
        String fileName = ((String) khDocumentPage.executeScript("return documentTabView.ViewData.DocumentTitle;"))
                .replaceAll("\\W", ""); // Replace all non-word chars
        // Lawtel transcript - is a PDF file
        File result = deliveryService.getFileViaHttp(href, fileName + ".pdf");
        setDownloadedDoc(result);
        return result;
    }

    /**
     * Check that give document contains and / or not contains expected phrases
     *
     * @param document           File with document (can be obtained by {@link RestServiceDeliveryImpl#downloadDocumentAndGetFile(StatusResponse, boolean)})
     * @param docFormat          Document format {@link InitiateDelivery.DocFormat}
     * @param phraseShouldExists Text, which should be exists in the document. There is can be few phrases joined with comma ",".
     * @param phraseShouldAbsent Text, which should not be exists in the document.
     *                           There is can be few phrases joined with comma ",".
     *                           OPTIONAL: if phrase absence check is not needed, than this argument should be passed
     *                           as empty string.
     * @return True - if document successfully downloaded and it contains some text, otherwise - false.
     * <p/>
     * WARNING: Exception can be thrown by {@link org.springframework.web.client.RestTemplate} if response will
     * not be successfull (when response code 4**, 5**)
     * @throws BadLocationException if document was not parsed successfully
     */
    public boolean isDocContainsOrNotContains(File document, InitiateDelivery.DocFormat docFormat,
                                              String phraseShouldExists, String phraseShouldAbsent) throws BadLocationException {
        String docText = getTextFromFile(document);
        return commonMethods.isStringContainsOrNotContains(docText, phraseShouldExists, phraseShouldAbsent);
    }

    /**
     * Check that give document contains and / or not contains expected phrases
     * WARNING! Text content of the given file will be transformed to a single line. New line characters will be
     *          replaced with a space
     *
     * @param document           File with document (can be obtained by {@link RestServiceDeliveryImpl#downloadDocumentAndGetFile(StatusResponse, boolean)})
     * @param phraseShouldExists Text, which should be exists in the document. There is can be few phrases joined with comma ",".
     * @param phraseShouldAbsent Text, which should not be exists in the document.
     *                           There is can be few phrases joined with comma ",".
     *                           OPTIONAL: if phrase absence check is not needed, than this argument should be passed
     *                           as empty string.
     * @return True - if document successfully downloaded and it contains some text, otherwise - false.
     * <p/>
     * WARNING: Exception can be thrown by {@link org.springframework.web.client.RestTemplate} if response will
     * not be successfull (when response code 4**, 5**)
     */
    public boolean isDocAsSingleStringContainsOrNotContains(File document, String phraseShouldExists, String phraseShouldAbsent) {
        Preconditions.checkNotNull(phraseShouldExists, "phraseShouldExists parameter(Text, which should exist in the document) is NULL");
        String docText = getTextFromFile(document).replace(REGEX_NEW_LINE, REPLACE_NEW_LINE_WITH);
        return commonMethods.isStringContainsOrNotContains(docText, phraseShouldExists, phraseShouldAbsent);
    }

    public boolean isDocAsSingleStringContains(File document, String phraseShouldExists){
        return isDocAsSingleStringContainsOrNotContains(document, phraseShouldExists, null);
    }

	public static File getDownloadedDoc() {
        return downloadedDoc;
    }

	public static void setDownloadedDoc(File downloadedDoc) {
		DeliveryBaseUtils.downloadedDoc = downloadedDoc;
    }

    private StatusResponse getStatusResponseForInitiateDeliveryResponse(final InitiateDeliveryResponse initiateDeliveryResponse) {
        if (initiateDeliveryResponse.getTransactionId() == null) {
            throw new IllegalArgumentException("Initiate Delivery Response does not contains transaction Id");
        }
        Function<RestServiceDeliveryImpl, StatusResponse> waitCondition = service -> (service.isDocumentReady(initiateDeliveryResponse)) ?
                    service.getDocumentStatus(initiateDeliveryResponse) : null;
        // Wait until document status will be "completed" and return StatusResponse result
        return AbstractPage.waitFor(waitCondition, deliveryService);
    }

    /**
     * Get text content from a file. Supported formats:
     * - PDF
     * - RTF
     * - DOC (as RTF)
     * - CSV
     *
     * @param document Given document for parsing
     * @return Text content of the given file
     * @throws UnsupportedOperationException In case when given file has unknown format
     */
    private String getTextFromFile(File document) {
        InitiateDelivery.DocFormat docFormat = InitiateDelivery.DocFormat.getFormatForFile(document);
        String docText = "";
        try {
            switch (docFormat) {
                case PDF:
                    docText = new PDFBoxUtil().extractText(document.getAbsolutePath());
                    break;
                case RTF:
                case DOC:
                    docText = new AssetPageUtils().getTextOnlyFromRtf(document);
                    break;
                case CSV:
                    docText = fileActions.getTextFromFile(document);
                    break;
                default:
                    throw new UnsupportedOperationException("Text extraction is not implemented for format: " + docFormat);
            }
        } catch (IOException e) {
            LOG.warn("Parsing failed of file: " + document.getAbsolutePath(), e);
        }
        return docText;
    }

}
