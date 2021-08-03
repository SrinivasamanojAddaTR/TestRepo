package com.thomsonreuters.pageobjects.utils.document.xml.transformer.novus;

import com.thomsonreuters.pageobjects.utils.document.Document;
import com.thomsonreuters.pageobjects.utils.document.ResourceType;
import com.thomsonreuters.pageobjects.utils.document.metadata.Jurisdiction;
import com.thomsonreuters.pageobjects.utils.document.xml.transformer.BaseDocumentTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel_Ardenka on 18/07/2016.
 * Transform common documents data from XML to {@link Document}
 */
public class GenericDocumentTransformer extends BaseDocumentTransformer {

    private static final Logger LOG = LoggerFactory.getLogger(GenericDocumentTransformer.class);
    private static final String XML_NOVUS_GUID_XPATH = "//md.uuid";
    private static final String XML_NOVUS_TITLE_XPATH = "//md.title";
    private static final String XML_NOVUS_INFO_TYPE_XPATH = "//md.infotype";
    private static final String XML_NOVUS_PRACTICE_AREA_XPATH = "//md.practice.area";
    private static final String XML_NOVUS_FACET_NORMALIZED_JURISDICTION_XPATH = "//facets/normalized-jurisdiction-facet";

    /**
     * Set common info for generic {@link Document}:
     * - title
     * - resource type
     * - guid
     * - sorting date
     * - products
     * - jurisdictions (normalized)
     *
     * @param xmlFromNovus XML document representation
     * @param document Link to the document object
     */
    public void setCommonDocumentInfo(String xmlFromNovus, Document document) {
        try {
            document.setGuid(returnXpathNodes(xmlFromNovus, XML_NOVUS_GUID_XPATH).item(0).getTextContent());
            document.setTitle(returnXpathNodes(xmlFromNovus, XML_NOVUS_TITLE_XPATH).item(0).getTextContent());
            document.setResourceType(returnXpathNodes(xmlFromNovus, XML_NOVUS_INFO_TYPE_XPATH).item(0).getTextContent());
            ResourceType documentResourceType = ResourceType.getByNovusInfoType(document.getResourceType());
            document.setSortingDate(commonMethods
                    .getDateFromString(returnXpathNodes(xmlFromNovus, documentResourceType.getNovusSortingDateXpath()).item(0)
                            .getTextContent(), XML_NOVUS_ELEMENT_DATE_FORMAT));
            NodeList productsNodeList = returnXpathNodes(xmlFromNovus, XML_NOVUS_PRACTICE_AREA_XPATH);
            document.setProducts(getMetaDataProductFromNodeList(productsNodeList));
            document.setResourceType(documentResourceType.getFriendlyName());

            // Some documents do not have normalized jurisdiction at the moment. Let the following 3 lines of code be
            // the last in this method
            NodeList jurisdictionNodeList = returnXpathNodes(xmlFromNovus, XML_NOVUS_FACET_NORMALIZED_JURISDICTION_XPATH);
            List<Jurisdiction> jurisdictions = new ArrayList<>();
            for (int i = 0; i < jurisdictionNodeList.getLength(); i++) {
                Jurisdiction jurisdiction = new Jurisdiction();
                jurisdiction.setName(jurisdictionNodeList.item(i).getTextContent());
                jurisdictions.add(jurisdiction);
            }
            document.setJurisdictions(jurisdictions);
        } catch (Exception e) {
            LOG.info("XML processing error. GUID: " + document.getGuid(), e);
        }
    }

}
