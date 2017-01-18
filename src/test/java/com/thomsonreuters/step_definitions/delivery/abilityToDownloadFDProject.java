package com.thomsonreuters.step_definitions.delivery;

import com.thomsonreuters.pageobjects.common.FileActions;
import com.thomsonreuters.pageobjects.common.WindowHandler;
import com.thomsonreuters.pageobjects.pages.fastDraft.DraftViewPage;
import com.thomsonreuters.pageobjects.utils.fastDraft.FastDraftUtils;
import com.thomsonreuters.pageobjects.utils.pdf.PDFBoxUtil;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.FileSystems;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AbilityToDownloadFDProject extends BaseStepDef {

    private final static String DOWNLOADED_FILE_PATH = System.getProperty("user.home") + "/Downloads";
    private static final String DRAFT = "draft";
    private File downloadedFile = null;
    private DraftViewPage draftViewPage = new DraftViewPage();
    private WindowHandler windowHandler = new WindowHandler();
    private PDFBoxUtil pdfBoxUtil = new PDFBoxUtil();
    private FastDraftUtils fastDraftUtils = new FastDraftUtils();
    private FileActions fileActions = new FileActions();

    @When("^the user exports Form E as editable PDF$")
    public void exportEditablePDF() throws Throwable {
        draftViewPage.export().click();
        windowHandler.fileDownloadAutomatically(draftViewPage.exportEditablePDF());
    }

    @When("^the user exports Form E as editable PDF with changes$")
    public void exportEditablePDFWithChanges() throws Throwable {
        draftViewPage.export().click();
        windowHandler.fileDownloadAutomatically(draftViewPage.exportEditablePDF());
    }

    @When("^the user updates PFD with new name \"([^\"]*)\", new date \"([^\"]*)\", new month \"([^\"]*)\" and new year \"([^\"]*)\" in Date of birth$")
    public void changeFormEField(String newName, String newDate, String newMonth, String newYear) throws Throwable {
        PDDocument document = pdfBoxUtil.readDocument(downloadedFile.getAbsolutePath());
        pdfBoxUtil.editName(document, newName);
        pdfBoxUtil.editBirthDate(document, newDate, newMonth, newYear);
        pdfBoxUtil.save(document, downloadedFile.getAbsolutePath());
    }

    @When("^the user uploads edited PDF to the document name \"([^\"]*)\"$")
    public void uploadEditedPDF(String documentName) throws Throwable {
        fastDraftUtils.uploadFormEFromFD(documentName, downloadedFile.getAbsolutePath());
    }

    @When("^the user uploads new file with name \"([^\"]*)\" end extension \"([^\"]*)\" to the document name \"([^\"]*)\"$")
    public void uploadNewFile(String name, String extension, String documentName) throws Throwable {
        String path = DOWNLOADED_FILE_PATH + "/" + name + extension;
        fileActions.createTestFile(path);
        fastDraftUtils.uploadFormEFromFD(documentName, FileSystems.getDefault().getPath(path).toString());
    }

    @When("^the user Uploads Form E for Form E page$")
    public void uploadFormE() throws Throwable {
        fastDraftUtils.uploadFormEFromFormEPage(downloadedFile.getAbsolutePath());
    }

    @When("^the user exports Form E as printable PDF$")
    public void exportPrintablePDF() throws Throwable {
        draftViewPage.export().click();
        windowHandler.fileDownloadAutomatically(draftViewPage.exportPrintablePDF());
    }

    @When("^the user clicks Word document and saves .doc file$")
    public void clickWordDocument() throws Throwable {
        windowHandler.fileDownload(draftViewPage.wordDocument());
    }

    @Then("^draft file with extension \"([^\"]*)\" should download to the users machine$")
    public void fileShouldDownloadToTheUsersMachine(String extension) throws Throwable {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        downloadedFile = fileActions.findFile(DRAFT, extension, DOWNLOADED_FILE_PATH);
        assertTrue("File was not downloaded", downloadedFile != null && downloadedFile.exists());
    }

    @Then("^the file should be removed$")
    public void deleteDownloadedDocFile() throws Throwable {
        fileActions.deleteFile(downloadedFile);
    }

    @When("^the user deletes all files with name \"([^\"]*)\" and extension \"([^\"]*)\" from Downloads$")
    public void deleteFilesFormDownloads(String name, String extension) throws Throwable {
        File dir = new File(DOWNLOADED_FILE_PATH);
        List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, null);
        for (File file : files) {
            if (file.getName().contains(name) && file.getName().contains(extension)) {
                file.delete();
            }
        }
    }
}