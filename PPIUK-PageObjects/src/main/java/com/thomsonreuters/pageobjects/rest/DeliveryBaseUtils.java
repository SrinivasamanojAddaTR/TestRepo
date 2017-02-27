package com.thomsonreuters.pageobjects.rest;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.driver.framework.WebDriverDiscovery;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.FileActions;
import com.thomsonreuters.pageobjects.pages.urls.plcuk.KHDocumentPage;
import com.thomsonreuters.pageobjects.rest.model.request.delivery.initiateDelivery.InitiateDelivery;
import com.thomsonreuters.pageobjects.rest.model.response.delivery.initiateDelivery.InitiateDeliveryResponse;
import com.thomsonreuters.pageobjects.rest.model.response.delivery.status.StatusResponse;
import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceDeliveryImpl;
import com.thomsonreuters.pageobjects.utils.pdf.PDFBoxUtil;
import com.thomsonreuters.pageobjects.utils.plPlusResearchDocDisplay.AssetPageUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.BadLocationException;
import java.io.File;
import java.io.IOException;

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
     * @throws BadLocationException
     */
    public boolean isDocDownloadedAndChecked(InitiateDelivery.DocFormat docFormat, boolean isPrintable) throws BadLocationException {
        File downloadedDocument = downloadAndGetDocument(isPrintable);
        return isDocumentNotEmpty(downloadedDocument, docFormat);
    }

    /**
     * Check that downloaded document is not empty
     *
     * @param documentFile Downloaded document file
     * @param docFormat    Document format
     * @return True - if check passed. Otherwise - false.
     * @throws BadLocationException
     */
    public boolean isDocumentNotEmpty(File documentFile, InitiateDelivery.DocFormat docFormat) throws BadLocationException {
        try {
            switch (docFormat) {
                case Pdf:
                    return !new PDFBoxUtil().extractText(documentFile.getAbsolutePath()).trim().isEmpty();
                case Rtf:
                case Doc:
                    return !new AssetPageUtils().getTextFromFileWithRTForDOCextension(documentFile.getAbsolutePath()).isEmpty();
                case Csv:
                case Xls:
                case Zip:
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
        Function<RemoteWebDriver, String> waitCondition = new Function<RemoteWebDriver, String>() {
            @Override
            public String apply(RemoteWebDriver driver) {
                String transactionId = (String) driver.executeScript("return Cobalt.Delivery.DeliveryOptionsDialog.Instance()._currentTransactionId;");
                return (transactionId != null && !transactionId.isEmpty()) ? transactionId : null;
            }
        };
        return AbstractPage.waitFor(waitCondition, webDriverDiscovery.getRemoteWebDriver());
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
        String fileUrl = (String) webDriverDiscovery.getRemoteWebDriver().executeScript(
                "return $('.kh_standardDocumentAttachment a').attr('href') + '&imageFileName=' + " + fileNameScript + ";");
        String fileName = (String) webDriverDiscovery.getRemoteWebDriver().executeScript(
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
    	ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
                return (jsExecutor.executeScript("return typeof documentTabView != 'undefined' && typeof Cobalt.Url.Absolute != 'undefined';")).equals("true");
            }
        };
        AbstractPage.waitFor(condition, webDriverDiscovery.getRemoteWebDriver());
        String plcRefScript = "return documentTabView.documentData.LegacyId;";
        String fileUrl = (String) webDriverDiscovery.getRemoteWebDriver().executeScript("return " +
                "Cobalt.Url.Absolute(Cobalt.Url.Page.GetDocumentInFirmStyle({" +
                "legacyId: documentTabView.documentData.metaInformation.legacyId, " +
                "documentGuid: documentTabView.documentData.metaInformation.docGuid})) + \"&cookie=true\";");
        String fileName = webDriverDiscovery.getRemoteWebDriver().executeScript(plcRefScript) + ".fs.doc";
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
        String fileName = ((String) webDriverDiscovery.getRemoteWebDriver()
                .executeScript("return documentTabView.ViewData.DocumentTitle;"))
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
    // TODO To refactor: we don't need docFormat arg here. It can be obtained from file
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
        //TODO Pavel should rename this method
        Preconditions.checkNotNull(phraseShouldExists, "phraseShouldExists parameter(Text, which should exist in the document) is NULL");
        String docText = getTextFromFile(document).replaceAll(REGEX_NEW_LINE, REPLACE_NEW_LINE_WITH);
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
        Function<RestServiceDeliveryImpl, StatusResponse> waitCondition = new Function<RestServiceDeliveryImpl, StatusResponse>() {
            @Override
            public StatusResponse apply(RestServiceDeliveryImpl service) {
                return (service.isDocumentReady(initiateDeliveryResponse)) ?
                        service.getDocumentStatus(initiateDeliveryResponse) : null;
            }
        };
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
                case Pdf:
                    docText = new PDFBoxUtil().extractText(document.getAbsolutePath());
                    break;
                case Rtf:
                case Doc:
                    docText = new AssetPageUtils().getTextOnlyFromRtf(document);
                    break;
                case Csv:
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
