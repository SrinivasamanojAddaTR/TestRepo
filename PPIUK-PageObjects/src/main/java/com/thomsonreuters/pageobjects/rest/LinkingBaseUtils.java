package com.thomsonreuters.pageobjects.rest;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceLinkingImpl;
import com.thomsonreuters.pageobjects.utils.document.CaseDocument;
import com.thomsonreuters.pageobjects.utils.document.LegislationDocument;
import com.thomsonreuters.pageobjects.utils.document.ResourceType;
import com.thomsonreuters.pageobjects.utils.document.content.Section;
import com.thomsonreuters.pageobjects.utils.document.metadata.Jurisdiction;
import com.thomsonreuters.pageobjects.utils.document.metadata.Product;
import com.thomsonreuters.pageobjects.utils.document.xml.transformer.novus.CasesDocumentTransformer;
import com.thomsonreuters.pageobjects.utils.document.xml.transformer.novus.GenericDocumentTransformer;
import com.thomsonreuters.pageobjects.utils.document.xml.transformer.novus.LegislationDocumentTransformer;
import com.thomsonreuters.pageobjects.utils.novus.GenericNovusUtils;
import org.jsoup.Jsoup;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pavel_Ardenka on 13/04/2016.
 */
public class LinkingBaseUtils {

    private RestServiceLinkingImpl restServiceLinking = new RestServiceLinkingImpl();
    private CommonMethods commonMethods = new CommonMethods();
    private GenericDocumentTransformer genericDocumentTransformer = new GenericDocumentTransformer();
    private CasesDocumentTransformer casesDocumentTransformer = new CasesDocumentTransformer();
    private LegislationDocumentTransformer legislationDocumentTransformer = new LegislationDocumentTransformer();
    private GenericNovusUtils genericNovusUtils = new GenericNovusUtils();
    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LinkingBaseUtils.class);
    private final Map<String, String> documentXmlStorage = new LinkedHashMap<>();

    // TODO 1. do we need this? 2. Refactor all usages to user transformer directly
    public String getXLINKURI() {
        return genericDocumentTransformer.getXLINKURI();
    }

    // TODO 1. do we need this? 2. Refactor all usages to user transformer directly
    public String getATICTURI() {
        return genericDocumentTransformer.getATICTURI();
    }

    // TODO Refactor all usages to user transformer directly
    public NodeList returnXpathNodes(String pageSource, String strXpath) throws Exception {
        return genericDocumentTransformer.returnXpathNodes(pageSource, strXpath);
    }

    public String getGuid(String plcRef) {
        return restServiceLinking.getGuid(plcRef);
    }

    /**
     * Get document object with it metadata and document sections only.
     *
     * @param plcRef Document PLC Reference
     * @return {@link com.thomsonreuters.pageobjects.utils.document.Document} object with resourceType, products,
     * jurisdictions and document scetions
     * Other object fields will be null.
     */
    public com.thomsonreuters.pageobjects.utils.document.Document getDocumentMetaDataAndSectionsFromFatWire(String plcRef) {
        //to replace this method with FatWire one when the problem with updating of xml file is solved (Paul Parkins will inform us about it)
        String responseXmlBody = restServiceLinking.getXmlDocumentFromNovus(restServiceLinking.getGuid(plcRef));
        com.thomsonreuters.pageobjects.utils.document.Document docMetadata = new com.thomsonreuters.pageobjects.utils.document.Document();
        List<Product> products = new ArrayList<>();
        List<Jurisdiction> jurisdictions = new ArrayList<>();
        List<Section> sections = new ArrayList<>();
        try {
            NodeList productsNodeList = returnXpathNodes(responseXmlBody, "//md.practice.area");
            NodeList jurisdictionsNodeList = returnXpathNodes(responseXmlBody, "//plcmd.jurisdiction");
            NodeList resourceTypeNodeList = returnXpathNodes(responseXmlBody, "//plcmd.resource.type/plcmd.name");
            NodeList sectionsNodeList = returnXpathNodes(responseXmlBody,
                    "//fulltext//*[contains(local-name(), 'section') and ./title and not(ancestor::*[contains(local-name(),'del')])]");
            products.addAll(genericDocumentTransformer.getMetaDataProductFromNodeList(productsNodeList));
            jurisdictions.addAll(getMetaDataJurisdictionsFromNodeList(jurisdictionsNodeList));
            sections.addAll(getDocumentSectionsFromNodeList(sectionsNodeList));
            docMetadata.setProducts(products);
            docMetadata.setJurisdictions(jurisdictions);
            docMetadata.setResourceType(resourceTypeNodeList.item(0).getTextContent());
            docMetadata.setSections(sections);
        } catch (Exception e) {
            LOG.info("XML processing error", e);
        }
        return docMetadata;
    }

    /**
     * Get elements from HTML DOM
     *
     * @param htmlPageSource HTML page source
     * @param sizzle         Extended CSS selector. For additional documentation please see http://jsoup.org/cookbook/extracting-data/selector-syntax
     *                       WARNING: possible unexpected work if selector has parents (e.g., "div[id='someId'] a")
     * @return List of found elements
     */
    public org.jsoup.select.Elements getElementsFromHtml(String htmlPageSource, String sizzle) {
        org.jsoup.nodes.Document doc = Jsoup.parse(htmlPageSource);
        return doc.select(sizzle);
    }

    public boolean isLinkReturnsTheDocument(String hrefAttr) {
        return restServiceLinking.isLinkReturnsTheDocument(hrefAttr);
    }

    public String getPageSourceForLink(String link) {
        return restServiceLinking.getPageSourceForLink(link);
    }


    /**
     * Get jurisdictions list from Fatwire XML document for meta data
     *
     * @param jurisdictionsNodeList Nodes with jurisdictions
     * @return List with jurisdictions {@link Jurisdiction}
     */
    // TODO To move to GenericDocumentTransformer class
    private List<Jurisdiction> getMetaDataJurisdictionsFromNodeList(NodeList jurisdictionsNodeList) {
        List<Jurisdiction> jurisdictions = new ArrayList<>();
        int itemsCount = jurisdictionsNodeList.getLength();
        for (int i = 0; i < itemsCount; i++) {
            Node jurisdictionNode = jurisdictionsNodeList.item(i);
            NodeList namePlcRefNodes = jurisdictionNode.getChildNodes();
            String name = namePlcRefNodes.item(0).getTextContent();
            // Any UK jurisdiction is ignored in the metadata block
            if (name.equals("Any UK jurisdiction")) {
                continue;
            }
            Jurisdiction jurisdiction = new Jurisdiction();
            // First child is "name"
            jurisdiction.setName(namePlcRefNodes.item(0).getTextContent().trim());
            // Second child is "plc reference"
            jurisdiction.setPlcReference(namePlcRefNodes.item(1).getTextContent());
            jurisdictions.add(jurisdiction);
        }
        return jurisdictions;
    }

    /**
     * Get list of sections from Fatwire XML document
     *
     * @param sectionsNodeList Nodes with sections
     * @return List with jurisdictions {@link Section}
     */
    // TODO To move to GenericDocumentTransformer class
    private List<Section> getDocumentSectionsFromNodeList(NodeList sectionsNodeList) {
        List<Section> sections = new ArrayList<>();
        int itemsCount = sectionsNodeList.getLength();
        for (int i = 0; i < itemsCount; i++) {
            Node sectionNode = sectionsNodeList.item(i);
            NodeList sectionChildNodes = sectionNode.getChildNodes();
            Section section = new Section();
            // First child is "title"
            section.setTitle(sectionChildNodes.item(0).getTextContent().trim());
            sections.add(section);
        }
        return sections;
    }

    /**
     * Get documents based on info from Novus
     *
     * @param guids List with the document GUID
     * @return List od Document objects with obtained info from Novus
     */
    public List<com.thomsonreuters.pageobjects.utils.document.Document> getDocumentsFromNovus(List<String> guids, ResourceType resourceType) {
        List<com.thomsonreuters.pageobjects.utils.document.Document> documentsFromNovus = new ArrayList<>();
        for (String guid : guids) {
            storeXmlDocument(guid);
            String xmlFromNovus = documentXmlStorage.get(guid);
            com.thomsonreuters.pageobjects.utils.document.Document document;
            // Do not move setCommonDocumentInfo() call out of cases. If do so - legislation document title will be wrong
            switch (resourceType) {
                case LEGISLATION:
                    document = new LegislationDocument();
                    genericDocumentTransformer.setCommonDocumentInfo(xmlFromNovus, document);
                    legislationDocumentTransformer.
                            setLegislationSpecificDocumentInfo(xmlFromNovus, (LegislationDocument) document);
                    break;
                case GENERIC:
                case JOURNALS:
                    document = new com.thomsonreuters.pageobjects.utils.document.Document();
                    genericDocumentTransformer.setCommonDocumentInfo(xmlFromNovus, document);
                    break;
                case CASES:
                    document = new CaseDocument();
                    genericDocumentTransformer.setCommonDocumentInfo(xmlFromNovus, document);
                    casesDocumentTransformer.setCasesSpecificDocumentInfo(xmlFromNovus, (CaseDocument) document);
                    break;
                default:
                    throw new IllegalArgumentException("Unable to get document for the resource type: " + resourceType);
            }
            documentsFromNovus.add(document);
        }
        return documentsFromNovus;
    }

    /**
     * Obtain the document from Novus (via Velma tool) and store it in map
     *
     * @param guid Document GUID to obtaining
     */
    private void storeXmlDocument(String guid) {
        if (!documentXmlStorage.containsKey(guid)) {
            documentXmlStorage.put(guid, genericNovusUtils.getRawXmlDocumentByGuid(guid));
        }
    }
}
