package com.thomsonreuters.pageobjects.utils.delivery;

import com.google.common.base.Function;
import com.thomsonreuters.driver.configuration.Hosts;
import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.SeleniumKeyboard;
import com.thomsonreuters.pageobjects.pages.ask.AskResourcePage;
import com.thomsonreuters.pageobjects.pages.bciTools.CalculatorsTab;
import com.thomsonreuters.pageobjects.pages.delivery.DownloadOptionsPage;
import com.thomsonreuters.pageobjects.pages.delivery.EmailOptionsPage;
import com.thomsonreuters.pageobjects.pages.delivery.PrintOptionsPage;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.DocumentDeliveryOptionsPage;
import com.thomsonreuters.pageobjects.rest.DeliveryBaseUtils;
import com.thomsonreuters.pageobjects.rest.model.request.delivery.initiateDelivery.InitiateDelivery;
import com.thomsonreuters.pageobjects.utils.document.Document;
import com.thomsonreuters.pageobjects.utils.email.EmailMessageUtils;
import com.thomsonreuters.pageobjects.utils.email.Mailbox;
import com.thomsonreuters.pageobjects.utils.email.MailboxFactory;
import com.thomsonreuters.pageobjects.utils.form.FormUtils;
import com.thomsonreuters.pageobjects.utils.plPlusResearchDocDisplay.AssetPageUtils;
import cucumber.api.DataTable;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by Hleb_Kazlou on 12/23/2016.
 */
public class DeliveryUtils {

    private DocumentDeliveryOptionsPage deliveryOptionsPage = new DocumentDeliveryOptionsPage();
    private EmailOptionsPage email = new EmailOptionsPage();
    private PrintOptionsPage print = new PrintOptionsPage();
    private DownloadOptionsPage download = new DownloadOptionsPage();
    private FormUtils formUtils = new FormUtils();
    private DeliveryBaseUtils deliveryBaseUtils = new DeliveryBaseUtils();
    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private List<Document> documents = new ArrayList<>();
    private EmailMessageUtils emailMessageUtils = new EmailMessageUtils();
    private CalculatorsTab calculatorPage = new CalculatorsTab();
    private AssetPageUtils assetPageUtils = new AssetPageUtils();
    private SeleniumKeyboard seleniumKeyboard = new SeleniumKeyboard();
    private AskResourcePage askResourcePage = new AskResourcePage();
    public File downloadedFile;

    private static final Logger LOG = LoggerFactory.getLogger(DeliveryUtils.class);

    private static final String CALCULATORS_PAGE_URL = "/Browse/Home/Resources/SentenceRangeCalculators";
    private static final String READY_TO_DOWNLOAD_MESSAGE_PART = "ready to download";


    public void clicksOnDeliveryOptionForTheDocument(String deliveryOption) {
        switch (deliveryOption) {
            case "email":
                deliveryOptionsPage.email().click();
                email.basicTab().isDisplayed();
                email.emailButton().isDisplayed();
                break;
            case "Print":
                deliveryOptionsPage.print().click();
                print.printButton().isDisplayed();
                break;
            case "Download":
                deliveryOptionsPage.download().click();
                download.basicTab().isDisplayed();
                download.downloadButton().isDisplayed();
                break;
            default:
                break;
        }
    }

    public void editTheBasicDeliveryOptionsAsFollows(DataTable dataTable) throws Throwable {
        for (Map.Entry<String, String> entry : dataTable.asMap(String.class, String.class).entrySet()) {
            try {
                formUtils.editValue(DeliveryFormField.getByFieldDisplayName(entry.getKey()), entry.getValue());
            } catch (Exception e) {
                throw new Exception("Could not find or modify field '" + entry.getKey() + "'", e);
            }
        }
    }

    public void selectWhatToDeliverOptionInDeliveryOptionsWindow(String whatToDeliverOptionString) {
        if (download.getWhatToDeliverBlock() != null && whatToDeliverOptionString != null && !whatToDeliverOptionString.isEmpty()) {
            download.getWhatToDeliverRadioButton(whatToDeliverOptionString).click();
        }
    }
    /**
     * Delete this in another suits after @downloadDocDocumentAndCheck()@ will be implemented
     * */
    public void assertDocumentReadyToDownload() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(download.getDownloadButtonWhenDocReadyToDownload().isDisplayed())
                .withFailMessage("Download button absent")
                .isTrue();
        softAssertions
                .assertThat(download.getReadyForDownloadWindow().getText())
                .withFailMessage("Document is not ready to download")
                .contains("ready");
        softAssertions.assertAll();
    }

    public void selectDocFormatInDeliveryOptionsWindow(String format) {
        InitiateDelivery.DocFormat docFormat = InitiateDelivery.DocFormat.getFormatIgnoreCase(format);
        assertNotNull("Unknown document format: " + format, docFormat);
        new Select(download.formatDropdown()).selectByValue(docFormat.toString());
    }

    public void downloadDocDocumentAndCheck(String format, String whatToDeliverOptionString, String phrasesToExists, String phrasesToAbsent) throws Throwable {
        selectWhatToDeliverOptionInDeliveryOptionsWindow(whatToDeliverOptionString);
        selectDocFormatInDeliveryOptionsWindow(format);
        download.downloadButton().click();
        assertDocumentReadyToDownload();
        File downloadedDocument = deliveryBaseUtils.downloadAndGetDocument(false);
        assertTrue("Document was not downloaded", downloadedDocument != null && downloadedDocument.exists());
        assertTrue("Document text does not meet the expectations",
                deliveryBaseUtils.isDocContainsOrNotContains(downloadedDocument,
                        InitiateDelivery.DocFormat.valueOf(format), phrasesToExists, phrasesToAbsent));
    }

    public void downloadDocumentInExtension(String extension) throws Throwable {
        selectDocFormatInDeliveryOptionsWindow(extension);
        download.downloadButton().click();
        assertDocumentReadyToDownload();
        InitiateDelivery.DocFormat docFormat = InitiateDelivery.DocFormat.getFormatIgnoreCase(extension);
        assertTrue("Document not downloaded",
                deliveryBaseUtils.isDocDownloadedAndChecked(docFormat, false));
    }

    public void checkDocumentsWithContentTypesPresentOnly(List<String> expectedContentTypes) throws Throwable {
        int actualDocumentsCount = researchOrganizerPage.getDocumentCountInFolders();
        List<Document> documentsWithExpectedContentTypes = getExpectedDisplayedDocument(expectedContentTypes);
        int expectedDocumentsCount = documentsWithExpectedContentTypes.size();

        /** Check actual and expect documents quantity are equal */
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualDocumentsCount == expectedDocumentsCount)
                .overridingErrorMessage("Actual document count is wrong. Expected '" + expectedDocumentsCount
                        + "'. Actual '" + actualDocumentsCount + "'")
                .isTrue();

        /** Check expected documents present */
        for (Document docToCheck : documentsWithExpectedContentTypes) {
            softAssertions
                    .assertThat(researchOrganizerPage.isLinktoDocumentContentTypePresent(docToCheck.getGuid(),
                            docToCheck.getContentType()))
                    .overridingErrorMessage("Document '" + docToCheck.getGuid() + "' is absent\n").isTrue();
        }
        softAssertions.assertAll();
    }

    /**
     * Get documents with expected Content types
     *
     * @param expectedContentTypes
     * @return List of documents
     */
    private List<Document> getExpectedDisplayedDocument(List<String> expectedContentTypes) {
        List<Document> expectedDocuments = new ArrayList<>();
        for (Document document : documents) {
            for (String expectedContentType : expectedContentTypes) {
                if (expectedContentType.equals(document.getContentType())) {
                    expectedDocuments.add(document);
                }
            }
        }
        return expectedDocuments;
    }


    public void getReceivedEmailWithDocument(String email, String format, String subject, String download) throws Throwable {
        Message message = waitAndGetReceivedEmail(email, subject);
        String extension = emailMessageUtils.getAttachmentExtension(message);
        switch (format) {
            case "Microsoft Word":
                Assert.assertTrue("File extension is not Microsoft Word: " + extension,
                        extension.equalsIgnoreCase("doc") || extension.equalsIgnoreCase("docx"));
                break;
            case "PDF":
                Assert.assertTrue("File extension is not PDF: " + extension, extension.equalsIgnoreCase("pdf"));
                break;
            case "Word Processor (RTF)":
                Assert.assertTrue("File extension is not RTF: " + extension, extension.equalsIgnoreCase("rtf"));
                break;
            case "Microsoft Excel (CSV)":
                assertTrue("File extension is not CSV: " + extension, extension.equalsIgnoreCase("csv"));
                break;
            case "Microsoft Excel (XLS)":
                assertTrue("File extension is not XLS: " + extension, extension.equalsIgnoreCase("xls"));
                break;
        }
        if (!download.trim().isEmpty()) {
            downloadedFile = emailMessageUtils.downloadAttachment(message);
        }
    }

    public void getReceivedEmailWithDocument(String email, String subject) throws Throwable {
        Message message = waitAndGetReceivedEmail(email, subject);
        String text = Hosts.getInstance().getPlcukProductBase() + System.getProperty("base.url") + Hosts.getInstance().getPlcukDomain() + CALCULATORS_PAGE_URL;
        assertTrue("link is not present. Link:" + text + " MessageBody:" + emailMessageUtils.getMessageBody(message), emailMessageUtils.isEmailContainsText(message, text));
    }

    public void getReceivedEmailWithDocument(String email, String format, String subject) throws Throwable {
        Message message = waitAndGetReceivedEmail(email, subject);
        String extension = emailMessageUtils.getAttachmentExtension(message);
        String expected = null;
        switch (format) {
            case "Microsoft Word":
                expected = "doc";
                break;
            case "PDF":
                expected = "pdf";
                break;
            case "Word Processor (RTF)":
                expected = "rtf";
                break;
            case "Microsoft Excel (CSV)":
                expected = "csv";
                break;
        }
        Assert.assertTrue("File extension is not " + format + ":"+ extension, extension.contains(expected));
    }

    private Message waitAndGetReceivedEmail(String email, String subject) throws Throwable {
        Mailbox mailbox = MailboxFactory.getMailboxByEmail(email);
        return mailbox.waitForMessageWithTitle(subject, 300, 10);
    }

    public boolean checkThatDownloadedDocNotContainsLink(String linkText) throws Throwable {
        File downloadedDocFile =  DeliveryBaseUtils.getDownloadedDoc();
        String docFileName = downloadedDocFile.getName();
        return (!linkText.isEmpty() && docFileName.toLowerCase().endsWith(".pdf")) && assetPageUtils.isLinkExistsInThePdfDocument(linkText, downloadedDocFile);
    }

    public void downloadPrintableAndCheck(String whatToDeliverOptionString, String phrasesToExists, String phrasesToAbsent) throws Throwable {
        selectWhatToDeliverOptionInDeliveryOptionsWindow(whatToDeliverOptionString);
        print.printButton().click();
        download.waitForPageToLoadAndJQueryProcessing();
        seleniumKeyboard.sendEscape();
        File downloadedDocument = deliveryBaseUtils.downloadAndGetDocument(true);
        assertTrue("Document was not downloaded", downloadedDocument != null && downloadedDocument.exists());
        assertTrue(deliveryBaseUtils.isDocContainsOrNotContains(downloadedDocument,
                InitiateDelivery.DocFormat.Pdf, phrasesToExists, phrasesToAbsent));
    }

    public void verifyThatEmailBasicTabOptionsIsDisplayed(DataTable dataTable) throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        for (Map.Entry<String, String> entry : dataTable.asMap(String.class, String.class).entrySet()) {
            String value;
            try {
                value = formUtils.getValue(DeliveryFormField.getByFieldDisplayName(entry.getKey())).trim();
            } catch (Exception e) {
                throw new Exception("Could not find or modify field '" + entry.getKey() + "'", e);
            }
            softly.assertThat(value.equals(entry.getValue().trim()))
                    .overridingErrorMessage("actual: " + value + ", expected" + entry.getValue().trim()).isTrue();
        }
        softly.assertAll();
    }

    public void verifyThatReadyForEmailIsDisplayOnOverlay(String header) throws Throwable {
        if (header.contains("Ready")) {
            email.waitForPageToLoadAndJQueryProcessing();
            assertThat("The " + header + " is NOT displayed", askResourcePage.readyMessageOverlayHeader().getText(), containsString(header));
        } else {
            assertThat("The " + header + " is NOT displayed", askResourcePage.prepareMessageOverlayHeader().getText(), containsString(header));
        }
    }

    public boolean isReadyToDownload() {
        return isReadyToDownloadHeaderContains(READY_TO_DOWNLOAD_MESSAGE_PART);
    }

    private boolean isReadyToDownloadHeaderContains(final String headerTitle) {
        try {
            WebElement readyToDownloadPopup = download.getReadyForDownloadWindow();
            Function<WebElement, Boolean> readyToDownloadCondition = new Function<WebElement, Boolean>() {
                @Override
                public Boolean apply(WebElement webElement) {
                    return webElement.getText().contains(headerTitle);
                }
            };
            return AbstractPage.waitFor(readyToDownloadCondition, readyToDownloadPopup);
        } catch (TimeoutException e) {
            LOG.info("Delivery is not successful");
            return false;
        }
    }
}
