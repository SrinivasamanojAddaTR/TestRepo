package com.thomsonreuters.pageobjects.utils.novus;

import com.thomsonreuters.pageobjects.utils.document.CaseDocument;
import com.thomsonreuters.pageobjects.utils.document.Document;
import com.thomsonreuters.pageobjects.utils.document.ResourceType;
import com.thomsonreuters.pageobjects.utils.document.xml.transformer.novus.CasesDocumentTransformer;
import com.thomsonreuters.pageobjects.utils.document.xml.transformer.novus.GenericDocumentTransformer;
import com.westgroup.novus.productapi.Content;
import com.westgroup.novus.productapi.Search;

import java.util.*;

/**
 * Created by Pavel_Ardenka on 18/07/2016.
 * Novus utils for work with Cases documents
 */
public class CasesNovusUtils extends BaseNovusUtils {

    // Documents count which will be used for paginated search in the for-each / while loops
    private static final int MAX_PAGINATED_DOC_THRESHOLD = 2000;

    private GenericDocumentTransformer genericDocumentTransformer = new GenericDocumentTransformer();
    private CasesDocumentTransformer casesDocumentTransformer = new CasesDocumentTransformer();

    public CasesNovusUtils() {
        setDocumentCollections(ResourceType.CASES.getDocumentCollections());
    }

    /**
     * Get the cases document by color code
     *
     * @param colorCode Color code value
     * @return List of found documents
     * @throws IllegalArgumentException in case when there are no documents for the given color code
     */
    public Document getDocumentByFlagColorCode(String colorCode) {
        setResultsLimit(DEFAULT_NOVUS_RESULTS_LIMIT);
        resetPaginatedSearch();
        CaseDocument.CaseDocumentStatus expectedStatus = CaseDocument.CaseDocumentStatus.getStatusByColorCode(colorCode);
        String searchQuery = NovusSearchCriteria.CASES_BY_TREATMENT_FACET.get(expectedStatus.getCaseTreatmentFacets());
        int i = 0;
        while (i <= MAX_PAGINATED_DOC_THRESHOLD) {
            Content[] novusDocuments =
                    getResultsForPaginatedSearch(searchQuery, Search.BOOLEAN);
            List<Document> documents = transformDocs(novusDocuments);
            for (Document document : documents) {
                CaseDocument caseDocument = (CaseDocument) document;
                if (caseDocument.getCasesDocStatus() == expectedStatus) {
                    LOG.info("The cases document for the status code '" + colorCode + "' was found: " + document.toString());
                    return document;
                }
            }
            i += documents.size();
        }
        throw new IllegalArgumentException("No cases found for color code '" + colorCode + "' by query '" +
                searchQuery + "' within " + MAX_PAGINATED_DOC_THRESHOLD + " documents.");
    }

    /** {@inheritDoc} */
    @Override
    protected List<Document> transformDocs(Content[] documents) {
        List<Document> caseDocuments = new ArrayList<>();
        for (Content document : documents) {
            String xmlData = getContentMetaData(document);
            CaseDocument caseDocument = new CaseDocument();
            genericDocumentTransformer.setCommonDocumentInfo(xmlData, caseDocument);
            casesDocumentTransformer.setCasesSpecificDocumentInfo(xmlData, caseDocument);
            caseDocuments.add(caseDocument);
        }
        return caseDocuments;
    }
}
