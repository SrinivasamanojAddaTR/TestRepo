package com.thomsonreuters.pageobjects.utils.novus;

import com.thomsonreuters.pageobjects.utils.document.Document;
import com.thomsonreuters.pageobjects.utils.document.LegislationDocument;
import com.thomsonreuters.pageobjects.utils.document.ResourceType;
import com.thomsonreuters.pageobjects.utils.document.xml.transformer.novus.GenericDocumentTransformer;
import com.thomsonreuters.pageobjects.utils.document.xml.transformer.novus.LegislationDocumentTransformer;
import com.westgroup.novus.productapi.Content;
import com.westgroup.novus.productapi.Search;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel_Ardenka on 18/07/2016.
 * Novus utils for work with Legislation documents
 */
public class LegislationNovusUtils extends BaseNovusUtils {

    // Minimum amount of the search results documents required in specific cases
    private static final int LOW_RESULTS_LIMIT = 1;

    private GenericDocumentTransformer genericDocumentTransformer = new GenericDocumentTransformer();
    private LegislationDocumentTransformer legislationDocumentTransformer = new LegislationDocumentTransformer();

    public LegislationNovusUtils() {
        setDocumentCollections(ResourceType.LEGISLATION.getDocumentCollections());
    }

    /**
     * Get the legislation document by color code
     *
     * @param colorCode Color code value
     * @return Found document
     */
    public Document getDocumentByFlagColorCode(String colorCode, String billTypeName) {
        setResultsLimit(LOW_RESULTS_LIMIT);
        String query = (StringUtils.isEmpty(billTypeName)) ?
                NovusSearchCriteria.LEGISLATION_BY_FLAG_COLOR_CODE.get(colorCode) :
                NovusSearchCriteria.LEGIS_BILL_BY_FLAG_COLOR_CODE_AND_BILL_TYPE_NAME.get(colorCode, billTypeName);
        Content[] novusDocuments = getResultsFor(query, Search.BOOLEAN);
        Document document = transformDocs(novusDocuments).get(0);
        LOG.info("The legislation document for the status code '" + colorCode + "' was found: " + document.toString());
        return document;
    }

    /** {@inheritDoc} */
    @Override
    protected List<Document> transformDocs(Content[] documents) {
        List<Document> legislationDocuments = new ArrayList<>();
        for (com.westgroup.novus.productapi.Content document : documents) {
            String xmlData = getContentMetaData(document);
            LegislationDocument legislationDocument = new LegislationDocument();
            genericDocumentTransformer.setCommonDocumentInfo(xmlData, legislationDocument);
            legislationDocumentTransformer.setLegislationSpecificDocumentInfo(xmlData, legislationDocument);
            legislationDocuments.add(legislationDocument);
        }
        return legislationDocuments;
    }
}
