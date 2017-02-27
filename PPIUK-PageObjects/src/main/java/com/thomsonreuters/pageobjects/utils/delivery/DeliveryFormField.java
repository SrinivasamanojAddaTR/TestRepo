package com.thomsonreuters.pageobjects.utils.delivery;

import com.thomsonreuters.pageobjects.utils.form.CheckBoxOrRadioButton;
import com.thomsonreuters.pageobjects.utils.form.DefaultFormField;
import com.thomsonreuters.pageobjects.utils.form.DropDown;
import com.thomsonreuters.pageobjects.utils.form.FormField;
import com.thomsonreuters.pageobjects.utils.form.FormFieldStrategy;
import org.openqa.selenium.By;

public enum DeliveryFormField implements FormField {

    BASIC_TAB("Basic", By.cssSelector("#co_deliveryOptionsTab1 .co_tabLink"), DefaultFormField.newInstance()),
    ADVANCED_TAB("Advanced", By.cssSelector("#co_deliveryOptionsTab2 .co_tabLink"), DefaultFormField.newInstance()),

    TO("To", By.id("co_delivery_emailAddress"), DefaultFormField.newInstance()),
    SUBJECT("Subject", By.id("co_delivery_subject"), DefaultFormField.newInstance().getTextByValue()),
    EMAIL_NOTE("Email Note", By.id("co_delivery_note"), DefaultFormField.newInstance()),
    FORMAT("Format", By.id("co_delivery_format_fulltext"), DropDown.newInstance()),
    JURISDICTION("Jurisdiction", By.id("kh_delivery_country_list"), DropDown.newInstance()),
    FORMAT_LIST("Format Value", By.id("co_delivery_format_list"), DropDown.newInstance()),
    TABLE_OF_CONTENTS("Table of Contents", By.id("coid_chkDdcLayoutTableOfContents"), CheckBoxOrRadioButton.newInstance()),
    RELATED_CONTENT("Related content", By.id("coid_chkDdcRelatedContent"), CheckBoxOrRadioButton.newInstance()),
    RESOURCE_HISTORY("Resource history", By.id("coid_chkDdcResourceHistory"), CheckBoxOrRadioButton.newInstance()),
	ANNOTATED_STATUTES_CONTENT("Annotated Statutes Content", By.id("TO ADD"), CheckBoxOrRadioButton.newInstance()),
    ANNOTATIONS("Annotations", By.id("coid_chkDdcContentAnnotations"), CheckBoxOrRadioButton.newInstance()),
    LIST_OF_ITEMS("List of Items", By.id("co_deliveryWhatToDeliverList"), CheckBoxOrRadioButton.newInstance()),
    DOCUMENTS("Documents", By.id("co_deliveryWhatToDeliverDocumentOnly"), CheckBoxOrRadioButton.newInstance()),
    AS("As", By.id("co_delivery_fileContainer"), DropDown.newInstance()),
	ONLY_PAGES_WITH_TERMS("Only pages with terms", By.id("coid_chkDdcLayoutOnlyPagesWithSearchTerms"), CheckBoxOrRadioButton.newInstance()),
	DUAL_COLUMN_LAYOUT("Dual column layout for Cases", By.id("coid_chkDdcLayoutUseDualColumnsForCases"), CheckBoxOrRadioButton
			.newInstance()),
	JURISDICTION_SPECIFIC_MATERIAL("Insert jurisdiction-specific material", By.id("kh_delivery_country_list"), DropDown.newInstance()),

	APPELLATE_HISTORY("Appellate History", By.id("coid_chkDdcContentAppellateHistory"), CheckBoxOrRadioButton.newInstance()),
	RELATED_CASES("Related Cases", By.id("coid_chkDdcContentRelatedCases"), CheckBoxOrRadioButton.newInstance()),
	PRIMARY_REFERENCES("Primary References", By.id("coid_chkDdcContentPrimaryReferences"), CheckBoxOrRadioButton.newInstance()),
	COMMENTARY_REFERENCES("Commentary References", By.id("coid_chkDdcContentCommentaryReferences"), CheckBoxOrRadioButton.newInstance()),
	PRACTICAL_LAW_REFERENCES("Practical Law References", By.id("coid_chkDdcContentPracticalLawReferences"), CheckBoxOrRadioButton
			.newInstance()),

    DOCUMENT("Document", By.id("co_deliveryWhatToDeliverDocumentOnly"), CheckBoxOrRadioButton.newInstance()),
    ONLY_DRAFTING_NOTES("Only Drafting Notes", By.id("co_deliveryWhatToDeliverDraftingNotesOnly"), CheckBoxOrRadioButton.newInstance()),
    DOCUMENT_AND_DRAFTING_NOTES("Document and Drafting Notes", By.id("co_deliveryWhatToDeliverDocumentAndDraftingNotes"), CheckBoxOrRadioButton.newInstance()),

	PAGE_RANGES_FULL_TEXT("Page Ranges Full Text", By.id("co_delivery_FullText"), CheckBoxOrRadioButton.newInstance()),
	PAGE_RANGES_STAR_PAGES("Page Ranges Star Pages", By.id("co_delivery_StarPageRanges"), CheckBoxOrRadioButton.newInstance()),
	STAR_PAGES_VALUE("Star Pages", By.id("co_delivery_StarPagesRanges"), DefaultFormField.newInstance()),

    EXPAND_MARGIN_FOR_NOTES("Expanded Margin for Notes", By.id("coid_chkDdcLayoutRightNoteMarging"), CheckBoxOrRadioButton.newInstance()),
    COVER_PAGE("Cover Page", By.id("coid_chkDdcLayoutCoverPage"), CheckBoxOrRadioButton.newInstance()),
    COVER_PAGE_COMMENT("Cover Page Comments", By.id("coid_DdcLayoutCoverPageComment"), DefaultFormField.newInstance()),
    LINKS("Links", By.id("co_delivery_linkColor"), DropDown.newInstance()),
    UNDERLINE("Underline", By.id("co_delivery_linkUnderline"), CheckBoxOrRadioButton.newInstance()),
    FONTSIZE("Font Size", By.id("co_delivery_fontSize"), DropDown.newInstance());

    private String displayName;
    private By by;
    private FormFieldStrategy formFieldStrategy;

    DeliveryFormField(String displayName, By by, FormFieldStrategy formFieldStrategy) {
        this.displayName = displayName;
        this.by = by;
        this.formFieldStrategy = formFieldStrategy;
    }

    public String getDisplayName() {
        return displayName;
    }

    public By getBy() {
        return by;
    }

    public FormFieldStrategy getFormFieldStrategy() {
        return formFieldStrategy;
    }

    public static DeliveryFormField getByFieldDisplayName(String label) {
        for (DeliveryFormField emailDeliveryBasicForm : DeliveryFormField.values()) {
            if (emailDeliveryBasicForm.getDisplayName().equalsIgnoreCase(label)) {
                return emailDeliveryBasicForm;
            }
        }
        throw new UnsupportedOperationException("Field " + label + " is not in the ENUM");
    }

}
