package com.thomsonreuters.pageobjects.utils.novus;

import com.thomsonreuters.pageobjects.utils.document.Document;
import com.westgroup.novus.productapi.Content;

import java.util.List;

/**
 * Created by Pavel_Ardenka on 08/09/2016.
 * Generic Novus Utils class to work with non-specific document types
 */
public class GenericNovusUtils extends BaseNovusUtils {

    /**
     * Get full document as XML String (Meta data + text) by document GUID
     *
     * @param guid Document GUID
     * @return String representation of document or empty string if there are some errors were occurred during processing
     */
    public String getRawXmlDocumentByGuid(String guid) {
        return super.getRawXmlDocumentByGuid(guid);
    }

    @Override
    protected List<Document> transformDocs(Content[] documents) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
