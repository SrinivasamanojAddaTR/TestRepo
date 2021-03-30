package com.thomsonreuters.pageobjects.utils.novus;

import com.thomsonreuters.pageobjects.utils.document.Document;
import com.thomsonreuters.pageobjects.utils.document.ResourceType;
import com.thomsonreuters.pageobjects.utils.document.xml.transformer.novus.PlcLegacyDocumentTransformer;
import com.westgroup.novus.productapi.Content;
import com.westgroup.novus.productapi.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel_Ardenka on 27/09/2016.
 */
public class PlcLegacyNovusUtils extends BaseNovusUtils {

    private PlcLegacyDocumentTransformer plcLegacyDocumentTransformer = new PlcLegacyDocumentTransformer();

    public Document getDocumentByPlcRef(String plcRef) {
        setDocumentCollectionSets(ResourceType.PLC_LEGACY.getDocumentCollections());
        Content[] novusDocuments =
                getResultsFor(NovusSearchCriteria.PLC_DOC_BY_PLC_REF.get(plcRef), Search.BOOLEAN);
        return transformDocs(novusDocuments).get(0);
    }

    @Override
    protected List<Document> transformDocs(Content[] documents) {
        List<Document> plcLegacyDocuments = new ArrayList<>();
        for (Content document : documents) {
            String xmlData = getContentMetaData(document);
            Document plcLegacyDocument = new Document();
            plcLegacyDocumentTransformer.setDocumentGuid(xmlData, plcLegacyDocument);
            plcLegacyDocuments.add(plcLegacyDocument);
        }
        return plcLegacyDocuments;
    }
}
