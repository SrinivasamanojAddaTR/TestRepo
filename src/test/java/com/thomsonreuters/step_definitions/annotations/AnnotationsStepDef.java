package com.thomsonreuters.step_definitions.annotations;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.annotations.FormatType;
import com.thomsonreuters.pageobjects.pages.annotations.InsertEditLink;
import com.thomsonreuters.pageobjects.pages.annotations.SharedAnnotationsPage;
import com.thomsonreuters.pageobjects.pages.folders.HistoryTabs;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.folders.SaveToPopup;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawUKCategoryPage;
import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.StandardDocumentPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation.DocumentDeliveryPage;
import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceAnnotationsImpl;
import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceFFHImpl;
import com.thomsonreuters.pageobjects.utils.RoutingPage;
import com.thomsonreuters.pageobjects.utils.User;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class AnnotationsStepDef extends BaseStepDef {

    private DocumentDeliveryPage deliveryPage;

    private CommonMethods commonMethods;

    private NavigationCobalt navigationCobalt;

    private SharedAnnotationsPage sharedAnnotationsPage;

    private InsertEditLink insertEditLink;

    private SaveToPopup saveToPopup;

    private KnowHowSearchResultsPage knowHowSearchResultsPage;

    private ResearchOrganizerPage researchOrganizerPage;
    private PracticalLawUKCategoryPage practicalLawUKCategoryPage;
    private SearchResultsPage searchResultsPage;
    private RoutingPage routingPage;
    private OnepassLogin onePassLogin;
    private WLNHeader wlnHeader;
    private StandardDocumentPage standardDocumentPage;
    private RestServiceFFHImpl restServiceFFHImpl;
    private RestServiceAnnotationsImpl restServiceAnnotations;
    private String guidDoc;
    public static String input;
    public static String modifiedInput;

    public static List<String> numbersList;

    private static final Logger LOG = LoggerFactory.getLogger(AnnotationsStepDef.class);

    private static final String ANNOTATIONS_RICH_TEXT_WARNING_MESSAGE_1 = "Yournotecannotbesavedbecauseitcontainstoomuchformatting/hiddenHTMLstyling(mostlikelyduetotextcopiedfromawebpage).Pleaseremovesomeoftheformattingandtryagain.Suggestions:";
    private static final String ANNOTATIONS_RICH_TEXT_WARNING_MESSAGE_2 = "UseCtrl+Shift+Vorrightclickandselect\"pasteasplaintext\"topastethetextcopiedfromelsewherewithoutformatting;itshouldstillbepossibletoaddformattingwithourtool.";
    private static final String ANNOTATIONS_RICH_TEXT_WARNING_MESSAGE_3 = "Copyandpasteyourtextintoaworddocumentandthenpasteitbackwithreducedformatting";
    private static final String ANNOTATIONS_TEXT_WARNING_MESSAGE = "You have exceeded the 3,000 character limit for this field. Please remove some of your text and try again.";
    private static final String richTextInput = "<h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span>";
    private static final String exactLengthRichText = "<h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4><p>vbnvbn<span style=\"text-decoration: underline;\"><em><strong>v</strong></em></span><h4 style=\"text-align: center;\">Sample of complex formatting</h4>";

    private final static int CUSTOM_DRIVER_WAIT_TIME = 120;

    public AnnotationsStepDef() {
        deliveryPage = new DocumentDeliveryPage();
        commonMethods = new CommonMethods();
        navigationCobalt = new NavigationCobalt();
        sharedAnnotationsPage = new SharedAnnotationsPage();
        insertEditLink = new InsertEditLink();
        knowHowSearchResultsPage = new KnowHowSearchResultsPage();
        researchOrganizerPage = new ResearchOrganizerPage();
        routingPage = new RoutingPage();
        saveToPopup = new SaveToPopup();
        onePassLogin = new OnepassLogin();
        wlnHeader = new WLNHeader();
        practicalLawUKCategoryPage = new PracticalLawUKCategoryPage();
        searchResultsPage = new SearchResultsPage();
        standardDocumentPage = new StandardDocumentPage();
        restServiceFFHImpl = new RestServiceFFHImpl();
        restServiceAnnotations = new RestServiceAnnotationsImpl();
    }

    @When("^the user has accessed annotations text box$")
    public void theUserHasAccessedAnnotationsTextBox() throws Throwable {
        deliveryPage.clickOnLink(DocumentDeliveryPage.Links.NEW_ANNOTATION);
        editOption = "toolbar";
        LOG.info("The user has accessed annotations text box");
    }

    @When("^clearing existing styles and annotation text$")
    public void cleanStyle() {
        sharedAnnotationsPage.clearAll();
        LOG.info("Existing styles and annotation text have been cleaned");
    }

    @When("^selecting \"(.*?)\" and writing text$")
    public void selectingAndWritingText(String style) throws Throwable {
        input = "input" + System.currentTimeMillis();
        sharedAnnotationsPage.selectStyle(getFormatType(style));
        sharedAnnotationsPage.amendInput(input);
        LOG.info(style + " has been selected, and text has been written");
    }

    @When("^selecting (Headers|Inline|Blocks|Alignment) format \"(.*?)\" and writing text$")
    public void selectingMenuItemAndWritingText(String styleGroupName, String menuItem) throws Throwable {
        input = "input" + System.currentTimeMillis();
        sharedAnnotationsPage.selectStyleGroupFromFormatsMenu(styleGroupName);
        String style = styleGroupName + "_" + StringUtils.trimAllWhitespace(menuItem);
        sharedAnnotationsPage.selectStyle(getFormatType(style));
        sharedAnnotationsPage.amendInput(input);
        LOG.info("The style group has been selected from 'Formats' menu, and the text has been written");
    }

    @When("selecting (Headers|Inline|Blocks|Alignment) format \"(.*?)\"$")
    public void selectedMenuFormatStyle(String menu, String styleName) {
        sharedAnnotationsPage.selectStyleGroupFromFormatsMenu(menu);
        String style = menu + "_" + StringUtils.trimAllWhitespace(styleName);
        sharedAnnotationsPage.selectStyle(getFormatType(style));
        LOG.info("The style group has been selected from 'Formats' menu");
    }

    @Then("^highlighted text changes to (Headers|Inline|Blocks|Alignment) format \"(.*?)\"$")
    public void highLightedTextChangesToMenuFormat(String menu, String styleName) {
        String style = menu + "_" + StringUtils.trimAllWhitespace(styleName);
        assertTrue(sharedAnnotationsPage.isAnnoatationsTextDisplayedWithCharacterStyle(getFormatType(style), input));
        LOG.info("The highlighted text has been changed");
    }

    @Then("^text displays with \"(.*?)\" character style$")
    public void textDisplaysWithCharacterStyle(String style) throws Throwable {
        assertTrue(sharedAnnotationsPage.isAnnoatationsTextDisplayedWithCharacterStyle(getFormatType(style), input));
        LOG.info("The text has been displayed with " + style + " character style");
    }

    @Then("^text displays with \"(.*?)\" (Headers|Inline|Blocks|Alignment) style$")
    public void textDisplaysWithCharacterStyle(String styleName, String styleGroupName) throws Throwable {
        String style = styleGroupName + "_" + StringUtils.trimAllWhitespace(styleName);
        assertTrue(sharedAnnotationsPage.isAnnoatationsTextDisplayedWithCharacterStyle(getFormatType(style), input));
        LOG.info("The annotation's text has been displayed with an appropriate character style");
    }

    @Then("^multiple lines text displays same \"(.*?)\" format$")
    public void textInLinesWithStyle(String styleName) throws Throwable {
        assertTrue(sharedAnnotationsPage.isAnnoatationsTextDisplayedWithCharacterStyle(getFormatType(styleName), numbersList.get(0), numbersList.get(2), numbersList.get(2)));
        LOG.info("The text has displayed in character style");
    }

    @Then("^text displays with (Headers|Inline|Blocks|Alignment) \"(.*?)\" style$")
    public void textDisplaysWithMenuStyle(String styleGroupName, String menuItem) throws Throwable {
        String style = styleGroupName + "_" + StringUtils.trimAllWhitespace(menuItem);
        assertTrue(sharedAnnotationsPage.isAnnoatationsTextDisplayedWithCharacterStyle(getFormatType(style), input));
        LOG.info("The text has been displayed");
    }

    @When("^highlighted text with the cursor$")
    public void highlightedTextWithTheCursor() throws Throwable {
        input = "input" + System.currentTimeMillis();
        sharedAnnotationsPage.amendInput(input);
        sharedAnnotationsPage.selectText();
        LOG.info("The text has been highlighted with the cursor");
    }
    @When("^user looks through the body of the document and select text with colour \"(.*?)\"$")
    public void selectTextFromDocument(String colour) throws Throwable {
        sharedAnnotationsPage.selectTextFromDocument();
        sharedAnnotationsPage.chooseColorForNote(colour);
        LOG.info("Select text from document");
    }

    @When("^highlighted text$")
    public void highlightedText() throws Throwable {
        sharedAnnotationsPage.selectText();
        LOG.info("The text has been highlighted");
    }

    @When("^selecting \"(.*?)\"$")
    public void selecting(String style) throws Throwable {
        String styleChar = null;
        if (editOption.equals("keyboard")) {
            if (style.contains("copy"))
                styleChar = "c";
            else if (style.contains("paste"))
                styleChar = "v";
            else if (style.contains("cut"))
                styleChar = "x";
            sharedAnnotationsPage.amendInput(Keys.chord(Keys.CONTROL + styleChar));
        } else {
            sharedAnnotationsPage.selectStyle(getFormatType(style));
        }
        LOG.info(style + " has been selected");
    }

    @Then("^verify the UNDO and REDO is disabled$")
    public void verifyTheUndoAndRedoIsDisabled() {
        assertFalse(sharedAnnotationsPage.isLinkOptionEnabled(FormatType.UNDO));
        assertFalse(sharedAnnotationsPage.isLinkOptionEnabled(FormatType.REDO));
        LOG.info("UNDO and REDO have been displayed");
    }

    @Then("^entered text will be removed$")
    public void verifyEnteredTextIsRemoved() {
        assertFalse(sharedAnnotationsPage.isAnnoatationsTextDisplayedWithCharacterStyle(FormatType.BLOCKS_PARAGRAPH, input));
        LOG.info("The entered text has been removed");
    }

    @Then("^removed text will be displayed$")
    public void verifyRemovedTextIsDisplayed() {
        assertTrue(sharedAnnotationsPage.isAnnoatationsTextDisplayedWithCharacterStyle(FormatType.BLOCKS_PARAGRAPH, input));
        LOG.info("The removed text has been displayed");
    }

    @Then("^character style of highlighted text changes to \"(.*?)\"$")
    public void characterStyleOfHighlightedTextChangesTo(String style) throws Throwable {
        assertTrue(sharedAnnotationsPage.isAnnoatationsTextDisplayedWithCharacterStyle(getFormatType(style), input));
        LOG.info("Character style of the highlighted text has changed to " + style);
    }

    @When("^the user verifies the annotations link is present$")
    public void theUserVerifiesTheAnnotationsLinkIsPresent() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^user click on Annotations$")
    public void userClickOnAnnotations() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^annotations textbox will be displayed with tinymce editor$")
    public void annotationsTextboxWillBeDisplayedWithTinymceEditor() throws Throwable {
        assertTrue(sharedAnnotationsPage.isTextBoxDisplayed());
        LOG.info("The annotations text box has been displayed with tinymce editor");
    }

    @When("^enter the sample text$")
    public void creatingANewNote() throws Throwable {
        input = "input" + System.currentTimeMillis();
        sharedAnnotationsPage.amendInput(input);
        LOG.info("The sample text has been entered");
    }

    @Then("^verify the \"(.*?)\" is enabled$")
    public void verifyTheOptionEnabled(String style) {
        assertTrue(sharedAnnotationsPage.isLinkOptionEnabled(getFormatType(style)));
        LOG.info("The " + style + " has been enabled");
    }

    @Then("^verify the \"(.*?)\" is disabled$")
    public void verifyTheOptionDisabled(String style) {
        assertFalse(sharedAnnotationsPage.isLinkOptionEnabled(getFormatType(style)));
        LOG.info(style + " has been disabled");
    }

    @Then("^text should be added with the \"(.*?)\" style by default$")
    public void textShouldBeAddedInTheParagraphStyleByDefault(String style) throws Throwable {
        assertTrue(sharedAnnotationsPage.isParagraphStyleAddedAsDefault(input));
        LOG.info("The text has been added with an appropriate style by default");
    }

    @When("^selecting \"(.*?)\" from the toolbar and writing text in multiple lines$")
    public void selectingFromTheToolbarAndWritingTextInMultipleLines(String style) throws Throwable {
        sharedAnnotationsPage.selectStyle(getFormatType(style));
        numbersList = new ArrayList<String>();
        numbersList.add("input" + 1);
        numbersList.add("input" + 2);
        numbersList.add("input" + 3);
        sharedAnnotationsPage.amendInput(numbersList.get(0));
        sharedAnnotationsPage.amendInput("\n");
        sharedAnnotationsPage.amendInput(numbersList.get(1));
        sharedAnnotationsPage.amendInput("\n");
        sharedAnnotationsPage.amendInput(numbersList.get(2));
        LOG.info(style + " has been selected from the toolbar, and the text has been written in multiple lines");
    }

    @When("^the user has accessed annotations text box in multiple lines$")
    public void theUserHasAccessedAnnotationsTextBoxInMultipleLines() throws Throwable {
        numbersList = new ArrayList<String>();
        numbersList.add("input" + 1);
        numbersList.add("input" + 2);
        numbersList.add("input" + 3);
        sharedAnnotationsPage.amendInput(numbersList.get(0));
        sharedAnnotationsPage.amendInput("\n");
        sharedAnnotationsPage.amendInput(numbersList.get(1));
        sharedAnnotationsPage.amendInput("\n");
        sharedAnnotationsPage.amendInput(numbersList.get(2));
        LOG.info("the user has accessed annotations text box in multiple lines");
    }

    public static String editOption;

    @When("^use the \"(.*?)\" to select options$")
    public void useTheToSelectOptions(String option) throws Throwable {
        editOption = option;
        LOG.info(option + " has been used to select options");
    }

    @Then("^textbox will not be having that text$")
    public void textboxWillNotBeHavingThatText() throws Throwable {
        assertTrue(StringUtils.isEmpty(sharedAnnotationsPage.getText()));
        LOG.info("The text box doesn't contain the text any more");
    }

    @Then("^textbox will be having copied text$")
    public void textboxWillBeHavingCopiedText() throws Throwable {
        assertTrue(sharedAnnotationsPage.getText().contains(input));
        LOG.info("The text box has copied text");
    }

    @When("^empty the textbox$")
    public void emptyTheTextbox() throws Throwable {
        sharedAnnotationsPage.clearAll();
        LOG.info("The text box is empty");
    }

    @When("^selecting the Insert/Edit link on the toolbar$")
    public void selectingTheInsertEditLinkOnTheToolbar() throws Throwable {
        sharedAnnotationsPage.selectStyle(FormatType.INSERT_EDIT_LINK);
        LOG.info("The Insert/Edit link has been selected on the toolbar");
    }

    @Then("^a pop up box will display to enter a URL$")
    public void aPopUpBoxWillDisplayToEnterAURL() throws Throwable {
        assertTrue(insertEditLink.isPopUpDisplayed());
        LOG.info("The pop-up box for entering a URL has been displayed");
    }

    @Then("^text is already populated with the selected text$")
    public void enteredTextIsPopulated() throws Throwable {
        assertTrue(insertEditLink.isTextDisplayed(input));
        LOG.info("text is already populated with the selected text");
    }

    @Then("^Title and Target fields are should not be displayed$")
    public void titleAndTargetFieldsShouldNotBeDisplayed() throws Throwable {
        assertFalse(insertEditLink.isTitleFieldDisplayed());
        assertFalse(insertEditLink.isTargetFieldDisplayed());
        LOG.info("The Title and Target fields are not displayed");
    }

    @Then("Text,URL fields and buttons displayed")
    public void textURLButtonsDisplayed() {
        assertTrue(insertEditLink.isTextFieldDisplayed());
        assertTrue(insertEditLink.isUrlFieldDisplayed());
        assertTrue(insertEditLink.isOKButtonDisplayed());
        assertTrue(insertEditLink.isCancelButtonDisplayed());
        LOG.info("The Text, URL fields and buttons are displayed");
    }

    @When("^the user (adds|cancels) the url \"(.*?)\"$")
    public void theUserAddsOrCancelsTheUrlAmending(String action, String url) {
        insertEditLink.enterUrl(url);
        if (action.equals("adds")) {
            insertEditLink.clickOK();
            LOG.info("The user has added the url");
        } else if (action.equals("cancels")) {
            insertEditLink.clickCancel();
            LOG.info("The user has canceled the url");
        }

    }

    @When("^the user has inserted the url string \"(.*?)\" into textbox$")
    public void theUserAddsUrlString(String url) {
        sharedAnnotationsPage.amendInput("\n");
        sharedAnnotationsPage.amendInput(url);
        sharedAnnotationsPage.amendInput("\n");
        LOG.info("The user has added the URL string into the text box");
    }

    @When("^the user can insert the text and save it$")
    public void theUserCanInsertTheURLAndSaveIt() throws Throwable {
        input = "input" + System.currentTimeMillis();
        sharedAnnotationsPage.amendInput(input);
        sharedAnnotationsPage.saveAnnotation();
        LOG.info("The user has inserted and saved the text|URL");
    }

    @When("^user navigates to annotations textbox with text$")
    public void theUserCanInsertText() throws Throwable {
        deliveryPage.clickOnLink(DocumentDeliveryPage.Links.NEW_ANNOTATION);
        input = "input" + System.currentTimeMillis();
        sharedAnnotationsPage.insertInput(input);
        LOG.info("The user has navigated to the annotations text box with text");
    }

    @When("^user navigates to WLN annotations textbox with text$")
    public void theUserCanInsertTextIntoWLNAnnotationTextBox() throws Throwable {
        deliveryPage.clickOnLink(DocumentDeliveryPage.Links.NEW_ANNOTATION);
        input = "input" + System.currentTimeMillis();
        sharedAnnotationsPage.insertInputInWLNAnnotationTextBox(input);
        LOG.info("The user has navigated to WLN annotations text box with text");
    }

    @When("^saving the annotation$")
    public void savingTheAnnotation() throws Throwable {
        sharedAnnotationsPage.saveAnnotation();
        LOG.info("The annotation has been saved");
    }

    @Then("^that url hyperlinked to the selected text\\.$")
    public void thatUrlHyperlinkedToTheSelectedText() throws Throwable {
        assertTrue(sharedAnnotationsPage.isSavedAnnotationTextDisplayedAsLink(input));
        LOG.info("The URL is hyperlinked to the selected text");
    }

    public static String mainWindow;

    @When("^click on that link text$")
    public void clickOnLinkText() throws Throwable {
        mainWindow = sharedAnnotationsPage.getCurrentWindowHandle();
        sharedAnnotationsPage.clickOnAnnotationLinkText(input);
        LOG.info("The user clicked on the link text");
    }

    @When("^click on that link text \"(.*?)\"$")
    public void clickOnThatLinkText(String url) throws Throwable {
        mainWindow = sharedAnnotationsPage.getCurrentWindowHandle();
        sharedAnnotationsPage.clickOnAnnotationLinkText(url);
        LOG.info("The user clicked on the link text " + url);
    }

    @When("^click on shared url link$")
    public void clickOnURL() throws Throwable {
        mainWindow = sharedAnnotationsPage.getCurrentWindowHandle();
        sharedAnnotationsPage.clickOnURLLink(input);
        LOG.info("The user has clicked on the shared URL link");
    }

    @Then("^url string \"(.*?)\" become as hyperlinked text$")
    public void urlStringBecomesLink(String url) throws Throwable {
        assertTrue(sharedAnnotationsPage.isSavedAnnotationTextDisplayedAsLink(url));
        LOG.info("The URL string " + url + "has become as hyperlinked text");
    }

    @Then("^hyperlinked url will be opened in new tab with title \"(.*?)\"$")
    public void hyperlinkedUrlWillBeOpenedInNewWindow(String windowName) throws Throwable {
        commonMethods.switchDriverToAnotherWindow(windowName);
        commonMethods.close();
        commonMethods.switchToWindow(mainWindow);
        LOG.info("The hyperlinked URL have been opened in a new tab with a title " + windowName);
    }

    @Then("^verify No link is added to annotation$")
    public void verifyNoLinkIsAddedToAnnotation() {
        sharedAnnotationsPage.saveAnnotation();
        assertFalse(sharedAnnotationsPage.isSavedAnnotationTextDisplayedAsLink(input));
        LOG.info("No link have been added to the annotation");
    }

    @When("^clicking on the \"(.*?)\" button from the toolbar$")
    public void clickingOnTheButtonFromTheToolbar(String style) throws Throwable {
        sharedAnnotationsPage.selectStyle(getFormatType(style));
        LOG.info("The button " + style + " has been clicked from the toolbar");
    }

    @Then("^text doesn't render as a hyperlink$")
    public void textDoesnTRenderAsAHyperlink() throws Throwable {
        assertFalse(sharedAnnotationsPage.isSavedAnnotationTextDisplayedAsLink(input));
        LOG.info("The text doesn't render as a hyperlink");
    }

    @Then("^the saved annotations text should be displayed in the (selected Link|Headers|Inline|Blocks|Alignment) \"(.*?)\" format$")
    public void theAnnotationsTextShouldBeDisplayedInTheFormat(String menu, String styleOption) throws Throwable {
        if (!menu.equals("selected Link")) {
            styleOption = menu + "_" + StringUtils.trimAllWhitespace(styleOption);
        }
        assertTrue(sharedAnnotationsPage.isSavedAnnotationDisplayedWithSelectedStyle(getFormatType(styleOption), SharedAnnotationsPage.ExpectedResult.VISIBLE, input));
        LOG.info("The saved annotation text has been saved in the selected format");
    }

    public void verifySharedAnnotationIsDisplayed(String input) {
        assertTrue(sharedAnnotationsPage.isSavedAnnotationDisplayed(input, SharedAnnotationsPage.ExpectedResult.VISIBLE));
    }

    @Then("^shared annotation should be displayed$")
    public void sharedAnnotationIsDisplayedToViewer() throws Throwable {
        if (!sharedAnnotationsPage.isAnnotationsDisplayed()) {
            deliveryPage.clickOnLink(DocumentDeliveryPage.Links.SHOW_HIDE_ANNOTATIONS);
        }
        LOG.info("Annotation contains text: " + input);
        verifySharedAnnotationIsDisplayed(input);
    }

    @Then("^shared annotation should be displayed with \"(.*?)\"$")
    public void sharedAnnotationIsDisplayedToViewer(String linkName) throws Throwable {
        verifySharedAnnotationIsDisplayed(linkName);
        LOG.info("The shared annotation has displayed with " + linkName);
    }

    @Then("^shared annotation text will be displayed as link$")
    public void sharedAnnotationIsDisplayedToViewerAsLink() throws Throwable {
        assertTrue(sharedAnnotationsPage.isSavedAnnotationTextDisplayedAsLink(input));
        LOG.info("The shared annotation text has been displayed as link");
    }

    @Then("^annotation text with url is displayed$")
    public void sharedAnnotationTextWithUrlIsDisplayed() throws Throwable {
        sharedAnnotationIsDisplayedToViewer();
        assertTrue(sharedAnnotationsPage.isSavedAnnotationTextDisplayedWithLink(input));
        LOG.info("The annotation text with URL has been displayed");
    }

    @Then("^the saved annotations multiple text should be displayed in the \"(.*?)\" format$")
    public void theAnnotationsTextShouldBeDisplayedInTheFormat(String styleOption) throws Throwable {
        assertTrue(sharedAnnotationsPage.isSavedAnnotationDisplayedWithSelectedStyle(getFormatType(styleOption), SharedAnnotationsPage.ExpectedResult.VISIBLE, numbersList.get(0), numbersList.get(1), numbersList.get(2)));
        LOG.info("The saved annotation multiple text has been displayed in the " + styleOption + " format");
    }

    @Then("^verify the share with Contacts and Previous Contacts link is present$")
    public void verifyTheShareWithContactsAndGroupsLinkIsPresent() throws Throwable {
        assertTrue("Contacts Link is not displayed", sharedAnnotationsPage.isContactsLinkDisplayed());
        assertTrue("Previously Shared Link is not displayed", sharedAnnotationsPage.isPreviousContactsLinkDisplayed());
        LOG.info("The shared with Contacts and Previous Contacts link is present");

    }

    @Then("^verify that below options is present in BlockShareNoteLink dropdown$")
    public void verifyThatBelowOptionsIsPresentInBlockShareNoteLinkDropDown(List<String> expectedOptions) {
        assertTrue("BLOCK SHARE NOTE LINK dropdown values are changed in routing page", expectedOptions.containsAll(routingPage.getFACDropdownOptionValues("BlockShareNoteLink")));
        LOG.info("The below options are present in BlockShareNoteLink dropdown");
    }

    @Then("^verify that \"(.*?)\" dropdown is present routing Feature$")
    public void verifyThatBlockShareNoteLinkDropDown(String facName) {
        assertTrue("BLOCK SHARE NOTE LINK FAC is not available", routingPage.isBlockShareNoteLinkPresent());
        LOG.info(facName + " dropdown is present routing feature");
    }

    @Then("^verify the share with Contacts and Previous Contacts link is not present$")
    public void verifyTheShareWithContactsAndGroupsLinkIsNotPresent() throws Throwable {
        assertFalse("Contacts Link is displayed", sharedAnnotationsPage.isContactsLinkDisplayed());
        assertFalse("Previously Shared Link is displayed", sharedAnnotationsPage.isPreviousContactsLinkDisplayed());
        LOG.info("The shared with Contacts and Previous Contacts link is not present");
    }

    @When("^selecting Contacts link$")
    public void selectingContactsAndGroupsLink() throws Throwable {
        sharedAnnotationsPage.clickOnContactsLink();
        LOG.info("The Contacts link has been selected");
    }

    @Then("^contacts and Groups popup window will be displayed$")
    public void contactsAndGroupsPopupWindowWillBeDisplayed() throws Throwable {
        assertTrue(sharedAnnotationsPage.isContactsGroupsPageDisplayed());
        LOG.info("Contacts and Groups popup window is displayed");
    }

    @When("^search for Contact \"(.*?)\"$")
    public void searchForContact(String contact) throws Throwable {
        sharedAnnotationsPage.searchContact(getUserFullName(contact));
        LOG.info("Contact " + contact + " has been found");
    }

    @Then("^\"(.*?)\" is found in the contacts list$")
    public void isFoundInTheContactsList(String contact) throws Throwable {
        assertTrue(sharedAnnotationsPage.isContactFoundInSearch(getUserNameStartswithLastName(contact)));
        LOG.info(contact + " has ben found in the contacts list");
    }

    @When("^selecting the add Group option$")
    public void selectingTheAddGroupOption() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^add \"(.*?)\" as group member$")
    public void addAsGroupMember(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^save group name as \"(.*?)\"$")
    public void saveGroupNameAs(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^\"(.*?)\" is saved with \"(.*?)\" as a group memeber$")
    public void isSavedWithAsAGroupMember(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^user has created the annotations with \"(.*?)\"$")
    public void userHasCreatedTheAnnotationsWith(String style) throws Throwable {
        deliveryPage.clickOnLink(DocumentDeliveryPage.Links.NEW_ANNOTATION);
        input = "input" + System.currentTimeMillis();
        sharedAnnotationsPage.selectStyle(getFormatType(style));
        sharedAnnotationsPage.amendInput(input);
        LOG.info("The user has created the annotation with " + style);
    }

    @When("^user shared the annotations with another contact \"(.*?)\"$")
    public void userSharedTheAnnotationsWithAnotherContact(String contact) throws Throwable {
        sharedAnnotationsPage.clickOnContactsLink();
        sharedAnnotationsPage.searchContact(getUserFullName(contact));
        sharedAnnotationsPage.selectContact(getUserNameStartswithLastName(contact));
        sharedAnnotationsPage.selectInsertButtonOnContactsPage();
        sharedAnnotationsPage.scrollToTinyMceEditor();
        sharedAnnotationsPage.saveAnnotation();
        assertTrue("Application having page loading issue", sharedAnnotationsPage.isMetaDataDispalyed(input));
    }

    @Then("^annotations saved with the \"(.*?)\"$")
    public void annotationsSavedWithThe(String style) throws Throwable {
        assertTrue(sharedAnnotationsPage.isAnnoatationsTextDisplayedWithCharacterStyle(getFormatType(style), input));
        LOG.info("The annotations have been saved with the " + style);
    }

    @Then(("^the annotations text should be displayed in the \"(.*?)\" format$"))
    public void theAnnotationTextShouldBeDisplayedInStyleFormat(String style) {
        assertTrue(sharedAnnotationsPage.isSavedAnnotationDisplayedWithSelectedStyle(getFormatType(style), SharedAnnotationsPage.ExpectedResult.VISIBLE, input));
        LOG.info("The annotations text is displayed in the " + style + " format");
    }

    @When("^highlight the text with cursor$")
    public void highlightTextWithCursor() throws Throwable {
        input = "input" + System.currentTimeMillis();
        sharedAnnotationsPage.amendInput(input);
        sharedAnnotationsPage.selectText();
    }

    @When("^user has shared the annotations with another contact \"(.*?)\"$")
    public void userHasSharedTheAnnotationsWithAnotherContact(String contact) throws Throwable {
        sharedAnnotationsPage.clickOnContactsLink();
        sharedAnnotationsPage.searchContact(contact);
        sharedAnnotationsPage.selectContact(contact);
        sharedAnnotationsPage.selectInsertButtonOnContactsPage();
        sharedAnnotationsPage.scrollToTinyMceEditor();
        sharedAnnotationsPage.saveAnnotation();
        assertTrue("Application having page loading issue", sharedAnnotationsPage.isMetaDataDispalyed(input));
        LOG.info("The user has shared the annotation with a contact " + contact);
    }

    public static final String groupName = "annotationsTestGroup";

    @When("^user has shared the annotations with new group and \"(.*?)\" as member$")
    public void userHasSharedTheAnnotationsWithAnotherGroup(String contact) throws Throwable {
        sharedAnnotationsPage.clickOnContactsLink();
        sharedAnnotationsPage.searchGroup(groupName);
        if (!sharedAnnotationsPage.isGroupFoundInSearch(groupName)) {
            sharedAnnotationsPage.addGroup(groupName, getUserNameStartswithLastName(contact));
        }
        sharedAnnotationsPage.selectGroup(groupName);
        assertFalse("User's group was not selected", sharedAnnotationsPage.getSharedGroupLinks().isEmpty());
        assertTrue("Selected group is not correct", sharedAnnotationsPage.getSharedGroupLinks().get(0).getText().contains(groupName));
        sharedAnnotationsPage.selectInsertButtonOnContactsPage();
        sharedAnnotationsPage.saveAnnotation();
        assertTrue(sharedAnnotationsPage.isSavedAnnotationDisplayedInWLN(input));
        LOG.info("The user has shared annotations with a new group and " + contact + " as a member");
    }

    @When("^\"(.*?)\" clicks the link$")
    public void clicksTheLink(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^link will be direct the \"(.*?)\" user to the hyperlinked URL by opening a new window/tab$")
    public void linkWillBeDirectTheUserToTheHyperlinkedURLByOpeningANewWindowTab(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^\"(.*?)\" navigates to the same document with guid \"(.*?)\"$")
    public void navigatesToTheSameDocumentWithGuid(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^verify new annotations link is clickable$")
    public void verifyAddAnnotationsLinkIsClickable() throws Throwable {
        assertTrue(deliveryPage.isLinkClickable(DocumentDeliveryPage.Links.NEW_ANNOTATION));
        LOG.info("The new annotations link is clickable");
    }

    @When("^the user is able to see new annotations link is present$")
    public void theUserIsAbleToSeeNewAnnotationsLinkIsPresent() throws Throwable {
        assertTrue(deliveryPage.isLinkPresent(DocumentDeliveryPage.Links.NEW_ANNOTATION));
        LOG.info("The annotations link is present");
    }

    @When("^the user moves the mouse over on add annotations link$")
    public void theUserMovesTheMouseOverOnAddAnnotationsLink() throws Throwable {
        deliveryPage.mouseOverOnLink(DocumentDeliveryPage.Links.NEW_ANNOTATION);
        LOG.info("The user has moved the mouse over add annotations link");
    }

    @Then("^New annotation tooltip should be displayed$")
    public void addAnnotationTooltipShouldBeDisplayed() throws Throwable {
        assertTrue(deliveryPage.isToolTipDisplayed(DocumentDeliveryPage.Links.NEW_ANNOTATION));
        LOG.info("A new annotation tooltip is displayed");
    }

    @When("^user click on new Annotations link$")
    public void userClickOnNewAnnotationsLink() throws Throwable {
        deliveryPage.clickOnLink(DocumentDeliveryPage.Links.NEW_ANNOTATION);
        LOG.info("The user has clicked on a new Annotations link");
    }

    @When("^user cancels new annotation$")
    public void userCancelsNewAnnotation() throws Throwable {
        sharedAnnotationsPage.cancelSavingAnnotation();
        LOG.info("The user has canceled a new annotation");
    }

    @Then("^verify saved annotations text will be displayed with metadata$")
    public void verifySavedAnnotationsTextWillBeDisplyedWithMetadata() throws Throwable {
        assertTrue(sharedAnnotationsPage.isSavedAnnotationDisplayed(input, SharedAnnotationsPage.ExpectedResult.VISIBLE));
        assertTrue(sharedAnnotationsPage.isMetaDataDispalyed(input));
        LOG.info("The saved annotations text will be displayed with metadata");
    }

    @Then("^check that annotations at the top are expanded$")
    public void checkAtTheTopAnnotationIsExpanded() {
        assertTrue("Annotation body at the top is not diplayed",sharedAnnotationsPage.isBodyAnnotationPresent());
        LOG.info("Annotation is displayed");
    }
    @And("^user collaps annotations at the top$")
    public void userCollapsAnnotationsAtTheTop() {
        sharedAnnotationsPage.clickOnCollapsOptionAtTheTop();
        LOG.info("Click on the collaps option");

    }
    @And("^user expands annotations at the top$")
    public void userExpandsAnnotationsAtTheTop() {
        sharedAnnotationsPage.clickOnExpandOptionAtTheTop();
        LOG.info("Click on the expand option at the top");

    }

    @Then("^check that annotations at the top are collapsed$")
    public void checkThatAnnotationCollapsed() {
        assertFalse("Annotations are not collapsed", sharedAnnotationsPage.isBodyAnnotationPresent());

    }

    @Then("^check that inline annotations is collapsed$")
    public void checkInlineAnnotationIsCollapsed() {
        assertTrue("Body of inline annotation is displayed", sharedAnnotationsPage.isBodyInlineAnnotationNotPresent());
        LOG.info("Inline annotation is not displayed");
        }

    @Then("^the inline annotation is expanded$")
    public void inlineAnnotationIsExpanded() {
        assertFalse("Body of inline annotation is not displayed", sharedAnnotationsPage.isBodyInlineAnnotationNotPresent());
        LOG.info("Inline annotation is expanded");
    }

    @Then("^verify saved annotations text is displayed$")
    public void verifySavedAnnotationsTextWillBeDisplyed() throws Throwable {
        assertTrue(sharedAnnotationsPage.isSavedAnnotationDisplayed(input, SharedAnnotationsPage.ExpectedResult.VISIBLE));
        LOG.info("The saved annotations text is displayed");
    }

    @Then("^verify saved annotations text will be displayed with metadata in WLN$")
    public void verifySavedAnnotationsTextWillBeDisplyedWithMetadataWLN() throws Throwable {
        assertTrue(sharedAnnotationsPage.isSavedAnnotationDisplayedInWLN(input));
        LOG.info("The saved annotations text will be displayed with metadata in WLN");
    }

    @And("^the Client ID next to the timestamp will not be displayed$")
    public void noClientId() {
        assertTrue(sharedAnnotationsPage.verifyClientIDNotDisplayed(input));
        LOG.info("The Client ID next to the timestamp is not displayed");
    }

    @When("^user clicks the saved annotation$")
    public void userClicksTheSavedAnnotation() throws Throwable {
        sharedAnnotationsPage.selectEditMode(input);
        LOG.info("The user has clicked on the saved annotation");
    }

    @Then("^annotations text box will be displayed with existing text$")
    public void annotationsTextBoxWillBeDisplayedWithExistingText() throws Throwable {
        assertTrue(sharedAnnotationsPage.isEditModeDisplayedWithText(input));
        LOG.info("The annotations text box is displayed with existing text");
    }

    @When("^user modifies the text$")
    public void userModifiesTheText() throws Throwable {
        modifiedInput = "modified" + System.currentTimeMillis();
        sharedAnnotationsPage.insertInput(modifiedInput);
        LOG.info("The user has modified the text");
    }

    @Then("^modified annotations text will be displayed with metadata$")
    public void verifyTheModifiedTextIsDisplayed() throws Throwable {
        assertTrue(sharedAnnotationsPage.isSavedAnnotationDisplayed(modifiedInput, SharedAnnotationsPage.ExpectedResult.VISIBLE));
        assertFalse(sharedAnnotationsPage.isSavedAnnotationDisplayed(input, SharedAnnotationsPage.ExpectedResult.NOT_VISIBLE));
        assertTrue(sharedAnnotationsPage.isMetaDataDispalyed(modifiedInput));
        LOG.info("The modified annotations text is displayed with metadata");
    }

    @Then("^annotations text box will be displayed with delete icon$")
    public void annotationsTextBoxWillBeDisplayedWithDeleteIcon() throws Throwable {
        assertTrue("Bug #808769- Delete Icon is hiding by tinymce editor.", sharedAnnotationsPage.isDeleteIconDisplayedOnAnnotation(input));
        LOG.info("The annotations text box is displayed with the delete icon");
    }

    @When("^user deletes the annotations$")
    public void userDeletesTheAnnotations() throws Throwable {
        sharedAnnotationsPage.deleteAnnotation(input);
        LOG.info("The user has deleted the annotation");
    }

    @Then("^\"(.*?)\" text will be displayed with undo and close links$")
    public void withUndoAndCloseLinks(String message) throws Throwable {
        assertTrue(sharedAnnotationsPage.isDeleteNotesDisplayed(message));
        assertTrue(sharedAnnotationsPage.isUndoButtonDisplayed());
        assertTrue(sharedAnnotationsPage.isCloseButtonDisplayed());
        LOG.info(message + " text is displayed with undo and has closed links");
    }

    @Then("^user unable to find the deleted annotations$")
    public void userUnableToFindTheDeletedAnnotations() throws Throwable {
        assertFalse(sharedAnnotationsPage.isSavedAnnotationDisplayed(input, SharedAnnotationsPage.ExpectedResult.NOT_VISIBLE));
        LOG.info("The user has found the deleted annotations");
    }

    @When("^user clicks the undo link$")
    public void userClicksTheUndoLink() throws Throwable {
        sharedAnnotationsPage.undoDelete();
        LOG.info("The user has clicked the undo link");
    }

    @When("^user able to see the deleted annotations are displayed$")
    public void userAbleToSeeTheDeletedAnnotationsAreDisplayed() throws Throwable {
        assertTrue(sharedAnnotationsPage.isSavedAnnotationDisplayed(input, SharedAnnotationsPage.ExpectedResult.VISIBLE));
        LOG.info("The user has seen the deleted annotations displayed");
    }

    @When("^user clicks the close link$")
    public void userClicksTheCloseLink() throws Throwable {
        sharedAnnotationsPage.closeDeleteMessage();
        LOG.info("The user has clicked on the close link");
    }

    @Then("^annotations close and undo links will be disappeared$")
    public void annotationsCloseAndUndoLinksWillBeDisappeared() throws Throwable {
        assertFalse(sharedAnnotationsPage.isCloseButtonDisplayed());
        assertFalse(sharedAnnotationsPage.isUndoButtonDisplayed());
        LOG.info("The annotations Close and Undo links are displayed");
    }

    @When("user added new annotation")
    public void userAddedNewAnnotation() {
        deliveryPage.clickOnLink(DocumentDeliveryPage.Links.NEW_ANNOTATION);
        input = "input" + System.currentTimeMillis();
        sharedAnnotationsPage.amendInput(input);
        sharedAnnotationsPage.saveAnnotation();
        LOG.info("The user has added annotations at the top");

    }

    @When("user added new inline annotation")
    public void userAddedNewInlineAnnotation() {
        input = "input" + System.currentTimeMillis();
        sharedAnnotationsPage.amendInput(input);
        sharedAnnotationsPage.saveAnnotation();
        LOG.info("The user has added inline annotations");

    }
    @When("user refreshes page")
    public void userRefreshPage() {
        sharedAnnotationsPage.refreshPage();
        navigationCobalt.waitForPageToLoad();
        navigationCobalt.waitForPageToLoadAndJQueryProcessing();
        LOG.info("The user refreshes the page");

    }

    @When("inline annotation is collapsed")
    public void inlineAnnotationIsCollapsed() {
        sharedAnnotationsPage.saveAnnotation();
        LOG.info("Inline annotation is collapsed");

    }

    @When("the user clicks on the inline annotation icon")
    public void clickOnInlineAnnotationIcon() {
        sharedAnnotationsPage.clickOnInlineAnnotationIcon();
        LOG.info("Click on inline annotation icon");

    }

    @When("the user clicks on minimize option inline annotation icon")
    public void clickOnMinimizeOptionForInlineAnnotation() {
        sharedAnnotationsPage.clickOnMinimizeOptionForInlineAnnotation();
        LOG.info("Click on minimize option for inline annotation");

    }

    @When("user enters annotation text with \"(.*?)\" chars length")
    public void userAddedNewAnnotationWithLength(String length) {
        deliveryPage.clickOnLink(DocumentDeliveryPage.Links.NEW_ANNOTATION);
        if (!length.equals("empty")) {
            input = commonMethods.getRandomStringWithGivenLength(Integer.parseInt(length));
            sharedAnnotationsPage.amendInput(input);
        }
        LOG.info("The user has entered an annotation text with " + length + " chars length");
    }

    @Then("^user verifies Save button is (enabled|disabled)$")
    public void userVerifiesSaveButtonIsDisabled(String status) {
        if (status.equals("enabled")) {
            assertTrue(sharedAnnotationsPage.isSaveAnnotationEnabled());
            LOG.info("The user has verified Save button is enabled");
        } else if (status.equals("disabled")) {
            assertFalse(sharedAnnotationsPage.isSaveAnnotationEnabled());
            LOG.info("The user has verified Save button is disabled");
        }
    }

    @When("user added WLN new annotation")
    public void userAddedNewAnnotationInWLN() {
        deliveryPage.clickOnLink(DocumentDeliveryPage.Links.NEW_ANNOTATION);
        input = "input" + System.currentTimeMillis();
        sharedAnnotationsPage.insertInputInWLNAnnotationTextBox(input);
        sharedAnnotationsPage.saveAnnotation();
        LOG.info("The user has added a WLN new annotation");

    }

    @Then("^verify that user sharing icon is displayed before the createdby$")
    public void userSharingIcons() {
        assertTrue(sharedAnnotationsPage.isSharingIconVisible(input));
        LOG.info("The user has verified that sharing icon has been displayed before the createdby");
    }

    @Then("^user should not be able to see the annotations created in (PLC|WLN) site$")
    public void userShouldNotBeAbleToSeeAnnotation(String site) {
        boolean status = true;

        if (site.equals("WLN")) {
            status = sharedAnnotationsPage.isSavedAnnotationDisplayed(input, SharedAnnotationsPage.ExpectedResult.NOT_VISIBLE);
            LOG.info("The annotations created in WLN are displayed");
        } else if (site.equals("PLC")) {
            status = sharedAnnotationsPage.isSavedAnnotationDisplayedInWLN(input);
            LOG.info("The annotations created in PLC are displayed");
        }
        assertFalse("Annotations are displaying which are created in " + site, status);
    }

    @When("^user navigates directly to WLN document with guid \"(.*?)\"$")
    public void userNavigatesDirectlyToDocumentWithGuid(String guid) throws Throwable {
        navigationCobalt.navigateToWLNSpecificURL("/Document/" + guid + "/View/FullText.html");
        navigationCobalt.waitForPageToLoadAndJQueryProcessing();
        LOG.info("The user has navigated directly to WLN document with guid " + guid);
    }

    @When("^the user removes all annotations for the document with GUID \"(.*)\"$")
    public void removeAllAnnotations(String guid) throws Throwable {
        userNavigatesToDocumentWithGuid(guid);
        restServiceAnnotations.deleteAnnotations(guid);
    }
    @When("^the user removes all annotations for WLN document with GUID \"(.*)\"$")
    public void removeAllAnnotationsForWLNDocument(String guid) throws Throwable {
        userNavigatesDirectlyToDocumentWithGuid(guid);
        restServiceAnnotations.deleteAnnotations(guid);
    }

    @When("^user navigates directly to document with guid \"(.*?)\" on PL AU website$")
    public void theUserOpensUrlOnPLAUSite(String guid) throws Throwable {
        this.guidDoc = guid;
        navigationCobalt.navigateToPLCANZSpecificURL("/Document/" + guid + "/View/FullText.html");
        sharedAnnotationsPage.waitForPageToLoad();
        sharedAnnotationsPage.waitForPageToLoadAndJQueryProcessing();
        LOG.info("The user has navigated directly to the document with guid " + guid);
    }

    @Given("^user navigates directly to document with guid \"(.*?)\"$")
    public void userNavigatesToDocumentWithGuid(String guid) throws Throwable {
        navigationCobalt.navigateToWLNSpecificResourcePage("/Document/" + guid + "/View/FullText.html");
        navigationCobalt.waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(CUSTOM_DRIVER_WAIT_TIME);
        LOG.info("The user has navigated directly to the document with guid " + guid);
    }

    @When("^user navigates directly to document with guid and removes annotations on it$")
    public void userNavigatesDirectlyToDocumentWithGuid(List<String> guids) throws Throwable {
        for (String guid : guids) {
            navigationCobalt.navigateToWLNSpecificResourcePage("/Document/" + guid + "/View/FullText.html");
            //navigationCobalt.waitForPageToLoad();
            sharedAnnotationsPage.deleteAllAnnotations(getUserFullName(currentUser.getUserName()));
        }
        LOG.info("The user navigates directly to the document with the guid and removes annotations from it");
    }

    @When("^user selects the text \"(.*?)\"$")
    public void userSelectTheGivenText(String text) {
        sharedAnnotationsPage.selectTextPresentInParaUsingDoubleClick(text);
        LOG.info("The user has selected the text " + text);
    }

    @When("^user selects an add note color link$")
    public void userSelectsAnAddNoteColorLink() throws Throwable {
        sharedAnnotationsPage.selectInlineNotesYellowColor();
        LOG.info("The user has selected an add note color link");
    }

    public String getUserFullName(String contact) {
        User user = annotationUsers.get(contact);
        if (StringUtils.isEmpty(user)) {
            throw new PageOperationException("Usernames are not matching between usernameAndPassword properties and plPlusUser username value.");
        }
        LOG.info("A full user's name is got");
        return user.getFullName();
    }

    public String getUserNameStartswithLastName(String contact) {
        User user = annotationUsers.get(contact);
        if (StringUtils.isEmpty(user)) {
            throw new PageOperationException("Usernames are not matching between usernameAndPassword properties and plPlusUser username value.");
        }
        LOG.info("The user's name started with a last name is got");
        return user.getLastName() + ", " + user.getFirstName();
    }

    private FormatType getFormatType(String style) {
        LOG.info("The format type is got");
        return FormatType.valueOf(style.toUpperCase().trim());
    }

    @When("^adds current document to \"(.*?)\" folder$")
    public void addToFolder(String folderName) {
        deliveryPage.clickOnAddToFolderLink();
        saveToFolder(folderName);
        deliveryPage.waitForPageToLoad();
        LOG.info("A current document is added to " + folderName + " folder");
    }

    @Then("^the \"(.*?)\" document will be displayed along with nodes added link$")
    public void notesIconOnList(String index) {
        assertTrue(researchOrganizerPage.isNotesIconPresentForDocument(index));
        LOG.info("The " + index + " document will be displayed along with a nodes added link");
    }

    @Then("^the search result \"(.*?)\" document will be displayed along with nodes added link$")
    public void listNotesIconPresentOrNot(String index) {
        assertTrue(knowHowSearchResultsPage.isNotesAddedLinkPresent(index));
        LOG.info("The search result " + index + " is displayed along with a nodes added link");
    }

    @Then("^user should be able to see the warning message for exceeded (text|richText)$")
    public void userShouldSeeTheWarningMessage(String messageType) {
        if (messageType.equals("richText")) {
            String actualMessage = StringUtils.trimAllWhitespace(sharedAnnotationsPage.getWarningMessage(SharedAnnotationsPage.MessageType.RICH_TEXT));
            assertTrue(actualMessage.contains(ANNOTATIONS_RICH_TEXT_WARNING_MESSAGE_1) && actualMessage.contains(ANNOTATIONS_RICH_TEXT_WARNING_MESSAGE_2) && actualMessage.contains(ANNOTATIONS_RICH_TEXT_WARNING_MESSAGE_3));
            LOG.info("The user is able to see a warning message for exceeded richText");
        } else if (messageType.equals("text")) {
            assertTrue(sharedAnnotationsPage.getWarningMessage(SharedAnnotationsPage.MessageType.TEXT).contains(ANNOTATIONS_TEXT_WARNING_MESSAGE));
            LOG.info("The user is able to see a warning message for exceeded text");
        }
    }

    @Then("^select ok button on warning message$")
    public void userSelectsOkayButton() {
        sharedAnnotationsPage.selectOKButtonOnWarningMessage();
        LOG.info("The Ok button is selected on a warning message");
    }

    @When("^user removes the excess input from the annotations (text|richText)$")
    public void userRemovesTheExcessInputFromTheAnnotationsText(String contentType) {
        if (contentType.equals("text")) {
            sharedAnnotationsPage.insertContent(input.substring(0, input.length() - 2));
            LOG.info("The user has removed the excess input from the annotations 'text'");
        } else if (contentType.equals("richText")) {
            sharedAnnotationsPage.insertContent(exactLengthRichText);
            LOG.info("The user has removed the excess input from the annotations 'richText'");
        }
    }

    @When("^user enters annotation with richText$")
    public void userEntersRichTextWith10000Length() {
        deliveryPage.clickOnLink(DocumentDeliveryPage.Links.NEW_ANNOTATION);
        sharedAnnotationsPage.insertContent(richTextInput);
        LOG.info("The user has entered an annotation with richText");
    }

    @When("^the user is able to see show/hide annotations link is present$")
    public void theUserIsAbleToSeeShowHideAnnotationsLinkIsPresent() throws Throwable {
        assertTrue(deliveryPage.isLinkPresent(DocumentDeliveryPage.Links.SHOW_HIDE_ANNOTATIONS));
        LOG.info("The show/hide annotations are displayed");
    }

    @Then("^verify show/hide annotations link is clickable$")
    public void verifyShowHideAnnotationsLinkIsClickable() throws Throwable {
        assertTrue(deliveryPage.isLinkClickable(DocumentDeliveryPage.Links.SHOW_HIDE_ANNOTATIONS));
        LOG.info("The show/hide annotations are clickable");
    }

    @When("^the user hovers the Show And Hide link$")
    public void theUserHoversTheShowAndHideLink() throws Throwable {
        deliveryPage.mouseOverOnLink(DocumentDeliveryPage.Links.SHOW_HIDE_ANNOTATIONS);
        LOG.info("The user has hovered the Show And Hide link");
    }

    @Then("^verify show and hide tooltip appears$")
    public void verifyThatTooltipAppears() throws Throwable {
        assertTrue(deliveryPage.isToolTipDisplayed(DocumentDeliveryPage.Links.SHOW_HIDE_ANNOTATIONS));
        LOG.info("The Show and Hide tooltip appeared ");
    }

    @When("^user selects show/hide to hide annotations$")
    public void theUserSelectsToHideAnnotations() throws Throwable {
        deliveryPage.mouseOverOnLink(DocumentDeliveryPage.Links.SHOW_HIDE_ANNOTATIONS);
        if (deliveryPage.getTootlTipText().contains("Hide")) {
            deliveryPage.clickOnLink(DocumentDeliveryPage.Links.SHOW_HIDE_ANNOTATIONS);
            LOG.info("The user has selected to hide annotations");
        }
    }

    @When("^the user clicks on Show And Hide link$")
    public void theUserClicksOnShowAndHideLink() throws Throwable {
        deliveryPage.clickOnLink(DocumentDeliveryPage.Links.SHOW_HIDE_ANNOTATIONS);
        LOG.info("The user has clicked on Show And Hide link");
    }

    @Then("^verify that different Show and Hide icon displayed$")
    public void verifyThatDifferentShowAndHideLinkDisplayed() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Hidden annotations are displayed$")
    public void hiddenAnnotationsAreDisplayed() throws Throwable {
        assertTrue(sharedAnnotationsPage.isAnnotationsDisplayed());
        LOG.info("Hidden annotations are displayed");
    }

    @Then("^Displayed annotations are hidden$")
    public void displayedAnnotationsAreHidden() throws Throwable {
        assertFalse(sharedAnnotationsPage.isAnnotationsDisplayed());
        LOG.info("Displayed annotations are hidden");
    }

    @When("^user deletes All annotations$")
    public void userDeletesAllAnnotations() {
        sharedAnnotationsPage.deleteAllAnnotations(getUserFullName(currentUser.getUserName()));
        sharedAnnotationsPage.deleteInlineAnnotations();
        LOG.info("The user has deleted All annotations");
    }

    private static int annotationsCount;

    @When("^the user verifies the annotations count under link$")
    public void theUserVerifiesTheAnnotationsCountUnderLink() throws Throwable {
        assertTrue(sharedAnnotationsPage.isAnnotationsCountDisplayed());
        annotationsCount = sharedAnnotationsPage.getNotesCountFromShowAndHideIcon();
        LOG.info("The user has verified the annotations count under the link");
    }

    @Then("^Annotations count should match with annotations present on document\\.$")
    public void annotationsCountShouldMatchWithAnnotationsPresentOnDocument() throws Throwable {
        assertTrue(annotationsCount == sharedAnnotationsPage.getTotalNotesOnDocument());
        LOG.info("The annotations count match with the annotations present on a document");
    }

    @Then("^Annotations count should be displayed as zero$")
    public void annotationsCountShouldBeDisplayedAsZero() throws Throwable {
        sharedAnnotationsPage.deleteAllAnnotations(getUserFullName(currentUser.getUserName()));
        sharedAnnotationsPage.deleteInlineAnnotations();
        assertTrue(0 == sharedAnnotationsPage.getTotalNotesOnDocument());
        LOG.info("The annotations count are displayed as zero");
    }

    public String saveToFolder(String folder) {
        String folderName = null;
        saveToPopup.waitForPageToLoad();
        saveToPopup.waitForPageToLoadAndJQueryProcessing();
        if (folder.equals("root")) {
            saveToPopup.selectRootFolder().click();
            folderName = saveToPopup.selectRootFolder().getText();
        } else {
            saveToPopup.waitForPageToLoadAndJQueryProcessing();
            try {
                saveToPopup.expandRootFolderWait().click();
                saveToPopup.selectFolderWait(folder).click();
            } catch (NoSuchElementException e) {
                throw new RuntimeException("Folder '" + folder + "'doesn't present");
            }
        }
        saveToPopup.save().click();
        return folderName;
    }

    @Then("^user logs out$")
    public void userLogsOut() throws Throwable {
        wlnHeader.signOff();
        LOG.info("The user has logged out");
        //onePassLogin.waitForPageToLoad();
    }

    @When("^the user clicks on '(.+)' link on the header$")
    public void theUserClicksOnLinkOnTheHeader(String linkName) throws Throwable {
        researchOrganizerPage.waitForPageToLoad();
        switch (linkName) {
            case "Folders":
                wlnHeader.foldersLink().click();
                LOG.info("The user clicked on 'Folders' link");
                break;
            case "History":
                wlnHeader.historyLink().click();
                LOG.info("The user clicked on 'History' link");
                break;
            case "Favourites":
                wlnHeader.favouritesLink().click();
                LOG.info("The user clicked on 'Favourites' link");
                break;
            default:
        }
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user clicks on '(.+)' tab on the History page$")
    public void theUserClicksOnTabOnHistoryPage(String tabName) throws Throwable {
        HistoryTabs tab = HistoryTabs.valueOf(tabName);
        openHistoryTab(tab);
        LOG.info("The user clicked on '(.+)' tab on the History page");
    }

    @When("^the user runs a free text search for the query \"(.*)\"$")
    public void theUserRunsAFreeTextSearchForTheQuery(String query) throws Throwable {

        //paste string into the system clipboard instead
        StringSelection stringSelection = new StringSelection(query);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();

        // Ensure the search button has rendered
        practicalLawUKCategoryPage.searchButton().isDisplayed();

        practicalLawUKCategoryPage.freeTextField().clear();
        //sendKeys isn't always working
        //practicalLawUKCategoryPage.freeTextField().sendKeys(query);

        //Paste the clipboard text if the query contains brackets or ampersand
        if (query.contains("(") || query.contains(")") || query.contains("&")) {
            clpbrd.setContents(stringSelection, null);
            practicalLawUKCategoryPage.freeTextField().sendKeys(Keys.CONTROL + "v");
        } else {
            practicalLawUKCategoryPage.freeTextField().sendKeys(query);
        }

//        if (practicalLawUKCategoryPage.getDriver().getClass().equals(ChromeDriver.class)) {
//            pageActions.keyPress(Keys.ENTER);
//        } else {
        practicalLawUKCategoryPage.searchButton().click();
        //}

        // Wait for the results list to display
        theUserVerifiesThatTheResultsListPageIsDisplayed();
        LOG.info("The user has run a free text search for the query " + query);
    }

    protected void openHistoryTab(HistoryTabs tab) {
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        WebElement historyTabNonClicked = researchOrganizerPage.findElement(tab.getId());
        WebElement historyTabClicked = researchOrganizerPage.findElement(tab.getIdClickable());
        if (historyTabNonClicked.isDisplayed()) {
            historyTabNonClicked.click();
            researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        }
        if (historyTabClicked.isDisplayed()) {
            researchOrganizerPage.waitForElementPresent(tab.getPageHeader());
        } else {
            throw new RuntimeException("History tab '" + tab.getName() + "' absent!");
        }
        LOG.info("A history tab is opened");
    }

    @When("^the user verifies that the results list page is displayed$")
    public void theUserVerifiesThatTheResultsListPageIsDisplayed() throws Throwable {
        //Waiting for page to refresh
        searchResultsPage.waitForPageToLoad();
        try {
            searchResultsPage.resultsListHeader().isDisplayed();
            searchResultsPage.filterHeader().isDisplayed();
        } catch (Exception e) {
            LOG.error("The result page isn't displayed", e);
        }
        LOG.info("The user has verified that the results list page is displayed");
    }

    @After("@deletionAnnotations")
    public void after() throws Throwable {
        navigationCobalt.navigateToPLCANZSpecificURL("/Document/" + guidDoc + "/View/FullText.html");
        restServiceAnnotations.deleteAnnotations(guidDoc);
    }

}