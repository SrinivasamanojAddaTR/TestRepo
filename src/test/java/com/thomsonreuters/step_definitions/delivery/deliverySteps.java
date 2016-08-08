package com.thomsonreuters.step_definitions.delivery;

import com.thomsonreuters.pageobjects.common.FileActions;
import com.thomsonreuters.pageobjects.common.WindowHandler;
import com.thomsonreuters.pageobjects.pages.delivery.DownloadOptionsPage;
import com.thomsonreuters.pageobjects.pages.delivery.EmailOptionsPage;
import com.thomsonreuters.pageobjects.pages.delivery.PrintOptionsPage;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.DocumentDeliveryOptionsPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.StandardDocumentPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.rest.DeliveryBaseUtils;
import com.thomsonreuters.pageobjects.rest.model.request.delivery.initiateDelivery.InitiateDelivery;
import com.thomsonreuters.pageobjects.utils.email.EmailMessageUtils;
import com.thomsonreuters.pageobjects.utils.email.Mailbox;
import com.thomsonreuters.pageobjects.utils.email.MailboxFactory;
import com.thomsonreuters.pageobjects.utils.pdf.PDFBoxUtil;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import javax.mail.Message;
import javax.swing.text.BadLocationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class deliverySteps extends BaseStepDef {

	private EmailOptionsPage email = new EmailOptionsPage();
	private DownloadOptionsPage download = new DownloadOptionsPage();
	private PDFBoxUtil pdfBoxUtil = new PDFBoxUtil();
	private EmailMessageUtils emailMessageUtils = new EmailMessageUtils();
	private WindowHandler windowHandler = new WindowHandler();
	private FileActions fileActions = new FileActions();
	private PrintOptionsPage print = new PrintOptionsPage();
	private DocumentDeliveryOptionsPage deliveryOptionsPage = new DocumentDeliveryOptionsPage();
	private StandardDocumentPage document = new StandardDocumentPage();
	private DeliveryBaseUtils deliveryBaseUtils = new DeliveryBaseUtils();
	private SearchResultsPage searchResultsPage = new SearchResultsPage();

	private File downloadedFile = null;

	private final static String DOWNLOADED_FILE_PATH = System.getProperty("user.home") + "/Downloads";
	private final static String TITLE = "title";
	private final static String DOCUMENT_BODY = "document body";
	private final static String FILE_NAME = "file name";
	private String messageBody;

    private Map<String, Map<String,String>> resultsContent = new HashMap<String, Map<String,String>>();

	@Then("^user receives an email at \"(.*?)\" with document in (Microsoft Word|PDF|Word Processor \\(RTF\\)) format and with subject \"(.*?)\"(| and downloads the document)$")
	public void userReceivesAnEmailWithDocument(String email, String format, String subject, String download) throws Throwable {
		Mailbox mailbox = MailboxFactory.getMailboxByEmail(email);
		Message message = mailbox.waitForMessageWithTitle(subject, 120, 10);
		String extension = emailMessageUtils.getAttachmentExtension(message);
		if (format.equals("Microsoft Word")) {
			Assert.assertTrue("File extension is not Microsoft Word: " + extension,
					extension.equalsIgnoreCase("doc") || extension.equalsIgnoreCase("doc"));
		} else if (format.equals("PDF")) {
			Assert.assertTrue("File extension is not PDF: " + extension, extension.equalsIgnoreCase("pdf"));
		} else if (format.equals("Word Processor (RTF)")) {
			Assert.assertTrue("File extension is not RTF: " + extension, extension.equalsIgnoreCase("rtf"));
		}

		if (!download.trim().isEmpty()) {
			downloadedFile = emailMessageUtils.downloadAttachment(message);
		}

	}

	@Then("^user receives an email at \"(.*?)\" without attachments and with link to the (AU|UK) document \"(.*?)\" and with subject \"(.*?)\"$")
	public void userReceivesAnEmailWithoutAttachmentsWithLink(String email, String country, String link, String subject) throws Throwable {
		Mailbox mailbox = MailboxFactory.getMailboxByEmail(email);
		Message message = mailbox.waitForMessageWithTitle(subject, 120, 10);
		String expectedUrl = country + ".practicallaw." + System.getProperty("base.url") + ".thomsonreuters.com";
		String expectedParams = "/View/FullText.html";
		messageBody = emailMessageUtils.getMessageBody(message);
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(emailMessageUtils.hasAttachment(message)).overridingErrorMessage("Email contains attachment").isFalse();
		softly.assertThat(emailMessageUtils.isEmailContainsText(message, expectedUrl))
				.overridingErrorMessage("Email does not contain expected link: %s", expectedUrl).isTrue();
		softly.assertThat(emailMessageUtils.isEmailContainsText(message, link))
				.overridingErrorMessage("Email does not contain expected document id: %s", link).isTrue();
		softly.assertThat(emailMessageUtils.isEmailContainsText(message, expectedParams))
				.overridingErrorMessage("Email does not contain expected link parts: %s", expectedParams).isTrue();
		softly.assertAll();
	}
	
	@Then("^user copies the link in valid format from email into the browser$")
	public void userCopiesLinkFromEmailIntoBrowser() throws Throwable {
		Pattern p = Pattern.compile("(<a href=.+>)(https:\\/\\/a?\\.?au\\.practicallaw\\..+\\.thomsonreuters\\.com\\/.-\\d{3}-\\d{4})(<\\/a>)");
		Matcher m = p.matcher(messageBody);
		if (m.find()){
			String url = m.group(2);
			deliveryOptionsPage.navigate(url);
		}
		else{
			Assert.assertTrue("URL does not match the pattern: \n" + messageBody, false);
		}

	}

	@When("^Email button is clicked$")
	public void emailButtonIsClicked() throws Throwable {
		Thread.sleep(1000);
		email.emailButton().click();
	}

	@When("^user (downloads|prints|exports) the document with name \"(.*?)\" and extension \"(.*?)\"$")
	public void userDownloadsTheDocument(String action, String name, String extension) throws Throwable {
		Thread.sleep(1000);
		if (action.equals("downloads")) {
			download.downloadButton().click();
			assertDocumentReadyToDownload();
		} else if (action.equals("exports")) {
			download.exportButton().click();
			assertDocumentReadyToDownload();
		}
		else { // print
			print.printButton().click();
			download.waitForPageToLoad();
			// Minimize delivery window to prevent Download browser pop-up showing up
			// seleniumKeyboard.sendEscape();
		}

		doDownloadUsingRestApi(extension, action.equals("prints"));

		if (!action.equals("prints")) {
			Assert.assertTrue("The file name is different: " + downloadedFile.getName() + ", while expected: " + name, downloadedFile
					.getName().toLowerCase().contains(name.toLowerCase()));
		}
	}


	@When("^user (downloads|prints|exports) the document with name from result \"(.*?)\" and extension \"(.*?)\"$")
	public void userDownloadsTheDocumentFromResult(String action, String result, String extension) throws Throwable {
		String name = resultsContent.get(result).get(FILE_NAME);
		userDownloadsTheDocument(action, name, extension);
	}
	
	@When("^the user clicks on \"Open in Word\" delivery option for the document$")
	public void clicksOnQuickDraftDeliveryOptionForTheDocument() throws Throwable {

		windowHandler.fileDownload(deliveryOptionsPage.quickDraft());
	}
	
	@When("^the document is downloaded with name \"(.*?)\" and extension \"(.*?)\"$")
	public void hasTheDocument(String name, String extension) throws Throwable {
		downloadedFile = fileActions.findFile(name, extension, DOWNLOADED_FILE_PATH);
		assertTrue("File was not downloaded", downloadedFile != null && downloadedFile.exists());
	}
	
	@Then("^the document (includes|does not include) (document body that contains text|title) \"(.*?)\"$")
	public void emailedDocumentIncludesBodyWithText(String includes, String bodyOrTitle, String text) throws IOException {
		String pdfText = pdfBoxUtil.extractText(downloadedFile.getAbsolutePath());
		if (includes.equals("includes")) {
			Assert.assertTrue("PDF document does not contain expected text '" + text + "'", pdfText.contains(text));
		} else {
			Assert.assertFalse("PDF document contains document body with text '" + text + "'", pdfText.contains(text));
		}
	}
	
    @Then("^the user saves (.*?) from following results$")
    public void theUserSavesDocumentBodyFromFollowingResults(String data,List<String> results) throws Throwable {
    	String title, text;
    	for (String result:results) {
    		searchResultsPage.resultByNumber(result).click();
    		HashMap<String, String> content = new HashMap<String, String>();
    		title = document.documentTitle().getText();
    		for (String item:data.split(", ")){
    			switch (item) {
				case TITLE:
					text = title;
		    		if (text.length()>40){
		    			text=text.substring(0, 40);
		    		}
	        		content.put(TITLE, text);
					break;
				case DOCUMENT_BODY:
		    		text = document.contentBody().getText().replace(title, "");
		    		content.put(DOCUMENT_BODY, text.substring(0, 40));
					break;
				case FILE_NAME:
	    			if ((title.length()>81)){
	        			title=title.substring(0, 81);
	    			}	
        			title=title.replace(":", "");
            		content.put(FILE_NAME, title);
					break;					
				}
        		resultsContent.put(result, content);
    		}
			getDriver().navigate().back();
		}
    }
    
    @When("^the user deletes all files with name from result \"([^\"]*)\" and extension \"([^\"]*)\" from Downloads$")
    public void deleteFilesFormDownloads(String result, String extension) throws Throwable {
        File dir = new File(DOWNLOADED_FILE_PATH);
        List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, null);
        for (File file : files) {
            if (file.getName().contains(resultsContent.get(result).get(FILE_NAME)) && file.getName().contains(extension)) {
                file.delete();
            }
        }
    }
    
	@Then("^the document (includes|does not include) (document body that contains text|title) from result \"(.*?)\"$")
	public void emailedDocumentIncludesBodyWithTextFromResult(String includes, String bodyOrTitle, String result) throws IOException {
		String pdfText = pdfBoxUtil.extractText(downloadedFile.getAbsolutePath());
		String text;
		if (bodyOrTitle.equals("title")){
			text = resultsContent.get(result).get(TITLE);
		}
		else{
	    	text = resultsContent.get(result).get(DOCUMENT_BODY);
		}
		if (includes.equals("includes")) {
			Assert.assertTrue("PDF document does not contain expected text from search result '" + result + "'", pdfText.contains(text));
		} else {
			Assert.assertFalse("PDF document contains document body with text search result '" + result + "'", pdfText.contains(text));
		}
	}
    
	@Then("^the document (includes|does not include) table of contents that contains title \"(.*?)\"$")
	public void emailedDocumentIncludesTableOfContents(String includes, String text) throws IOException {
		String pdfText = pdfBoxUtil.extractFirstPageText(downloadedFile.getAbsolutePath());
		if (includes.equals("includes")) {
			Assert.assertTrue("PDF document does not contain expected title on the first page '" + text + "'", pdfText.contains(text));
		} else {
			Assert.assertFalse("PDF document contains table of contents with title on the first page '" + text + "'",
					pdfText.contains(text));
		}
	}
	
	@Then("^the document (includes|does not include) drafting notes$")
	public void emailedDocumentIncludesTableOfContents(String includes) throws IOException {
		String pdfText = pdfBoxUtil.extractText(downloadedFile.getAbsolutePath());
		String dnMark1 = "Drafting Note: ";
		String dnMark2 = "END DRAFTING NOTE";
		if (includes.equals("includes")) {
			Assert.assertTrue("PDF document does not contain drafting notes", pdfText.contains(dnMark1) && pdfText.contains(dnMark2));
		} else {
			Assert.assertFalse("PDF document contains drafting notes", pdfText.contains(dnMark1) || pdfText.contains(dnMark2));
		}
	}

	@When("^user clicks on (Email|Print|Download) delivery option for (Search|Folder|History)$")
	public void userClicksOnDeliveryOptionForSearch(String deliveryOption, String source) throws Throwable {
		switch (deliveryOption) {
		case "Email":
			searchResultsPage.emailDeliveryIcon().click();
			email.basicTab().isDisplayed();
			email.emailButton().isDisplayed();
			break;
		case "Print":
			searchResultsPage.printDeliveryIcon().click();
			print.basicTab().isDisplayed();
			print.printButton().isDisplayed();
			break;
		case "Download":
			searchResultsPage.downloadDeliveryIcon().click();
			download.basicTab().isDisplayed();
			download.downloadButton().isDisplayed();
			break;
		default:
			break;
		}
	}

	private void assertDocumentReadyToDownload() {
		assertTrue("Download button absent", download.getDownloadButtonWhenDocReadyToDownload().isDisplayed());
		assertTrue("Document is not ready to download", download.getReadyForDownloadWindow().getText().contains("ready"));
	}

	private void doDownloadUsingRestApi(String extension, boolean printable) throws BadLocationException {
		InitiateDelivery.DocFormat docFormat = InitiateDelivery.DocFormat.getFormatIgnoreCase(extension.replace(".", ""));
		assertTrue("Document not downloaded", deliveryBaseUtils.isDocDownloadedAndChecked(docFormat, printable));
		downloadedFile = deliveryBaseUtils.getDownloadedDoc();
	}

}
