package com.thomsonreuters.pageobjects.common;


/**
 * Created by Pavel_Ardenka on 04/05/2016.
 *
 * This enumeration describes available options to download fast draft document:
 * - Export as editable PDF form
 * - Export as printable PDF form
 * - Word document
 */
public enum FdDeliveryDocument {
    PDF_FORM_EDITABLE("pdfform.doconly", ".pdf"),
    PDF_FORM_PRINTABLE("pdf.doconly", ".pdf"),
    WORD_FORM("word.doconly", ".doc");

    private String name;
    private String extension;

    FdDeliveryDocument(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public static FdDeliveryDocument getByExt(String extension) {
        for (FdDeliveryDocument fdDeliveryDocument : FdDeliveryDocument.values()) {
            if (fdDeliveryDocument.getExtension().toLowerCase().endsWith(extension.toLowerCase())) {
                return fdDeliveryDocument;
            }
        }
        throw new UnsupportedOperationException("Unknown extension to FD download: " + extension);
    }
}
