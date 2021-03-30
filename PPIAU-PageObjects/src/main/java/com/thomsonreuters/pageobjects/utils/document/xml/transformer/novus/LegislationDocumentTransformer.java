package com.thomsonreuters.pageobjects.utils.document.xml.transformer.novus;

import com.thomsonreuters.pageobjects.utils.document.Document;
import com.thomsonreuters.pageobjects.utils.document.LegislationDocument;
import com.thomsonreuters.pageobjects.utils.document.xml.transformer.BaseDocumentTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pavel_Ardenka on 18/07/2016.
 * Transform cases documents from XML to {@link LegislationDocument}
 */
public class LegislationDocumentTransformer extends BaseDocumentTransformer {

    private static final Logger LOG = LoggerFactory.getLogger(LegislationDocumentTransformer.class);
    // The first item is a most related arrangement document, where the current doc is mentioned
    private static final String XML_NOVUS_LEGISLATION_ARRANGEMENT_PARENT = "//resultList/headings/heading[@rank='1']/link";
    private static final String XML_NOVUS_LEGISLATION_SECTIONS = "//resultList/headings/heading[position() > 1 and position() < count(//resultList/headings/heading)]";
    private static final String XML_NOVUS_LEGISLATION_PROVISION = "//headings/heading[@rank][last()]";
    // The following link should be displayed on the search results page for the document
    private static final String XML_NOVUS_LEGISLATION_SEARCH_RESULT_TITLE = "//headings/heading[@rank][last()-1]";
    private static final String XML_NOVUS_LEGISLATION_RANK = "//legislation-doc-type-rank";
    private static final String XML_NOVUS_LEGISLATION_START_DATE = "//commencement/start-date";
    private static final String XML_NOVUS_LEGISLATION_END_DATE = "//commencement/end-date";
    private static final String XML_NOVUS_LEGISLATION_NAVIGATOR = "//commencement/navigator";
    private static final String XML_NOVUS_LEGISLATION_FLAG_COLOR_CODE = "//md.flag.color.code";
    private static final String XML_NOVUS_GUID_ATTRIBUTE_NAME = "tuuid";
    private static final String XML_NOVUS_VERSION_ATTRIBUTE_NAME = "version";
    private static final String XML_NOVUS_VERSIONS_ATTRIBUTE_NAME = "versions";
    private static final String XML_NOVUS_PROVISION_GROUPS = "//provisions-table//pgroup";
    private static final String XML_NOVUS_PROVISION_GROUP = "(//provisions-table//pgroup)[%1$d]";
    private static final String XML_NOVUS_PROVISION_GROUP_CITE = XML_NOVUS_PROVISION_GROUP + "/pgroup-number//cite.query";
    private static final String XML_NOVUS_PROVISION_GROUP_TITLE = XML_NOVUS_PROVISION_GROUP + "/pgroup-title";
    private static final String XML_NOVUS_PROVISION_GROUP_ENTRIES = XML_NOVUS_PROVISION_GROUP + "/pgroup-entry";
    private static final String XML_NOVUS_PROVISION_GROUP_ENTRY = XML_NOVUS_PROVISION_GROUP_ENTRIES + "[%2$d]";
    private static final String XML_NOVUS_PROVISION_GROUP_ENTRY_CITE = XML_NOVUS_PROVISION_GROUP_ENTRY + "/pgroup-number//cite.query";
    private static final String XML_NOVUS_PROVISION_GROUP_ENTRY_TITLE = XML_NOVUS_PROVISION_GROUP_ENTRY + "/pgroup-title";
    private static final String XML_NOVUS_BILL_TYPE_NAME = "//resultList/bill/type";


    /**
     * Set legislation specific info for {@link LegislationDocument} object:
     * - arrangement heading document
     * - document title (which equals to arrangement heading title)
     * - list of the documents for legislation sections
     * - provision
     * - legislation rank
     * - document status (based on md.flag.color.code)
     * - commencement data (if available, {@link com.thomsonreuters.pageobjects.utils.document.LegislationDocument.LegislationDocStatus#hasDatesOrVersions})
     *
     * @param xmlFromNovus XML document representation
     * @param document Link to the legislation document object
     */
    public void setLegislationSpecificDocumentInfo(String xmlFromNovus, LegislationDocument document) {
        try {
            NodeList provisionNodeList = returnXpathNodes(xmlFromNovus, XML_NOVUS_LEGISLATION_PROVISION);
            document.setProvision(provisionNodeList.item(0).getTextContent());
            setLegislationHeadingArrangement(xmlFromNovus, document);
            setLegislationSections(xmlFromNovus, document);
            NodeList rankNodeList = returnXpathNodes(xmlFromNovus, XML_NOVUS_LEGISLATION_RANK);
            document.setRank(Integer.valueOf(rankNodeList.item(0).getTextContent()));
            NodeList flagColorCodeNodeList = returnXpathNodes(xmlFromNovus, XML_NOVUS_LEGISLATION_FLAG_COLOR_CODE);
            NodeList billTypeNameNodeList = returnXpathNodes(xmlFromNovus, XML_NOVUS_BILL_TYPE_NAME);
            String flagColorCode = flagColorCodeNodeList.item(0).getTextContent();
            if (billTypeNameNodeList.getLength() == 0) {
                document.setLegislationDocStatusByColorCode(flagColorCode);
            } else {
                document.setLegislationDocStatusByColorCodeAndBillTypeName(flagColorCode, billTypeNameNodeList.item(0).getTextContent());
            }
            setCommencementData(xmlFromNovus, document);
        } catch (Exception e) {
            LOG.info("XML processing error. GUID: " + document.getGuid(), e);
        }
    }

    /**
     * Set the following legislation info for {@link LegislationDocument} object:
     * - arrangement heading document
     * - document title (which equals to arrangement heading title)
     * - search result link title, which should be shown as a link on the search results page
     * WARNING: The document should have provision already {@link LegislationDocument#setProvision(String)}
     *
     * @param xmlFromNovus XML document representation
     * @param document Link to the legislation document object
     */
    private void setLegislationHeadingArrangement(String xmlFromNovus, LegislationDocument document) {
        try {
            NodeList arrangementHeadingNodeList = returnXpathNodes(xmlFromNovus, XML_NOVUS_LEGISLATION_ARRANGEMENT_PARENT);
            // Only one heading item with rank = 1 can be exists
            String arrangementHeadingGuid =
                    arrangementHeadingNodeList.item(0).getAttributes().getNamedItem(XML_NOVUS_GUID_ATTRIBUTE_NAME).getTextContent();
            // Heading contains only one element
            String arrangementHeadingTitle =
                    arrangementHeadingNodeList.item(0).getChildNodes().item(0).getTextContent();
            com.thomsonreuters.pageobjects.utils.document.Document arrangementParentDocument =
                    new com.thomsonreuters.pageobjects.utils.document.Document();
            arrangementParentDocument.setGuid(arrangementHeadingGuid);
            arrangementParentDocument.setTitle(arrangementHeadingTitle);
            document.setParentArrangementDocument(arrangementParentDocument);
            // Title of the document will be the same as for highest ranked document
            document.setTitle(arrangementHeadingTitle);
            if (document.isTopLeveled()) {
                NodeList searchResultLink = returnXpathNodes(xmlFromNovus, XML_NOVUS_LEGISLATION_SEARCH_RESULT_TITLE);
                document.setSearchResultLinkTitle(searchResultLink.item(0).getTextContent());
            } else {
                document.setSearchResultLinkTitle(document.getProvision());
            }
        } catch (Exception e) {
            LOG.info("XML processing error. GUID: " + document.getGuid(), e);
        }
    }

    /**
     * Set the following legislation info for generic {@link LegislationDocument}:
     * - list of the documents for legislation sections
     *
     * @param xmlFromNovus XML document representation
     * @param document Link to the legislation document object
     */
    private void setLegislationSections(String xmlFromNovus, LegislationDocument document) {
        try {
            NodeList arrangementHeadingsNodeList = returnXpathNodes(xmlFromNovus, XML_NOVUS_LEGISLATION_SECTIONS);
            List<Document> sectionsDocuments = new ArrayList<>();
            int sectionsCount = arrangementHeadingsNodeList.getLength();
            for (int i = 0; i < sectionsCount; i++) {
                com.thomsonreuters.pageobjects.utils.document.Document sectionDocument =
                        new com.thomsonreuters.pageobjects.utils.document.Document();
                Node sectionGuidNode = arrangementHeadingsNodeList.item(i)
                        .getAttributes().getNamedItem(XML_NOVUS_GUID_ATTRIBUTE_NAME);
                String sectionGuid = (sectionGuidNode != null) ? sectionGuidNode.getTextContent() : "";
                String sectionTitle = arrangementHeadingsNodeList.item(i).getTextContent();
                sectionDocument.setGuid(sectionGuid);
                sectionDocument.setTitle(sectionTitle);
                sectionsDocuments.add(sectionDocument);
            }
            document.setSectionsDocuments(sectionsDocuments);
        } catch (Exception e) {
            LOG.info("XML processing error. GUID: " + document.getGuid(), e);
        }
    }

    /**
     * Set the following legislation info for generic {@link LegislationDocument}:
     * - start date
     * - end date
     * - version
     * - versions count
     *
     * @param xmlFromNovus XML document representation
     * @param document Link to the legislation document object
     */
    private void setCommencementData(String xmlFromNovus, LegislationDocument document) {
        if (document.getLegislationDocStatus().hasDatesOrVersions()) {
            setStartDate(xmlFromNovus, document);
            setEndDate(xmlFromNovus, document);
            setVersions(xmlFromNovus, document);
        }
    }

    /**
     * Set the following legislation info for generic {@link LegislationDocument}:
     * - start date
     *
     * @param xmlFromNovus XML document representation
     * @param document Link to the legislation document object
     */
    private void setStartDate(String xmlFromNovus, LegislationDocument document) {
        try {
            NodeList startDateNodeList = returnXpathNodes(xmlFromNovus, XML_NOVUS_LEGISLATION_START_DATE);
            document.setStartDate(commonMethods.getDateFromString(startDateNodeList.item(0).getTextContent(),
                    XML_NOVUS_ELEMENT_DATE_FORMAT));
        } catch (Exception e) {
            LOG.warn("There is no start date for the document: " + document.getGuid());
        }
    }

    /**
     * Set the following legislation info for generic {@link LegislationDocument}:
     * - end date
     *
     * @param xmlFromNovus XML document representation
     * @param document Link to the legislation document object
     */
    private void setEndDate(String xmlFromNovus, LegislationDocument document) {
        try {
            NodeList endDateNodeList = returnXpathNodes(xmlFromNovus, XML_NOVUS_LEGISLATION_END_DATE);
            document.setEndDate(commonMethods.getDateFromString(endDateNodeList.item(0).getTextContent(),
                    XML_NOVUS_ELEMENT_DATE_FORMAT));
        } catch (Exception e) {
            LOG.warn("There is no end date for the document: " + document.getGuid());
        }
    }

    /**
     * Set the following legislation info for generic {@link LegislationDocument}:
     * - version
     * - versions count
     *
     * @param xmlFromNovus XML document representation
     * @param document Link to the legislation document object
     */
    private void setVersions(String xmlFromNovus, LegislationDocument document) {
        try {
            NodeList navigaorNodeList = returnXpathNodes(xmlFromNovus, XML_NOVUS_LEGISLATION_NAVIGATOR);
            int version = Integer.parseInt(navigaorNodeList.item(0).getAttributes()
                    .getNamedItem(XML_NOVUS_VERSION_ATTRIBUTE_NAME).getTextContent());
            int versions = Integer.parseInt(navigaorNodeList.item(0).getAttributes()
                    .getNamedItem(XML_NOVUS_VERSIONS_ATTRIBUTE_NAME).getTextContent());
            document.setVersion(version);
            document.setVersionsCount(versions);
        } catch (Exception e) {
            LOG.warn("There is no version / versions for the document: " + document.getGuid());
        }
    }

    /**
     * Set provisions table for AOP / AOA / SI
     * WARNING: The document should have provision already {@link LegislationDocument#setProvision(String)}
     *
     * @param xmlFromNovus XML document representation
     * @param document Link to the legislation document object
     */
    private void setProvisionsTable(String xmlFromNovus, LegislationDocument document) {
        try {
            if (document.isTopLeveled()) {
                Map<String, List<String>> aopProvisions = new LinkedHashMap<>();
                int provisionGroupsCount = returnXpathNodes(xmlFromNovus, XML_NOVUS_PROVISION_GROUPS).getLength();
                for (int i = 0; i < provisionGroupsCount; i++) {
                    List<String> provisions = new ArrayList<>();
                    int groupNodeIndex = i + 1;
                    String groupName = returnXpathNodes(xmlFromNovus,
                            String.format(XML_NOVUS_PROVISION_GROUP_CITE, groupNodeIndex)).item(0).getTextContent();
                    NodeList groupTitleNodeList = returnXpathNodes(xmlFromNovus,
                            String.format(XML_NOVUS_PROVISION_GROUP_TITLE, groupNodeIndex));
                    groupName = (groupTitleNodeList.getLength() > 0) ?
                            groupName + " " + groupTitleNodeList.item(0).getTextContent() : groupName;
                    int entriesCount = returnXpathNodes(xmlFromNovus,
                            String.format(XML_NOVUS_PROVISION_GROUP_ENTRIES, groupNodeIndex)).getLength();
                    // The first root section "Whole document" should be skipped because it not shown in the search results and not a provision
                    if (i > 0) {
                        provisions.add(groupName);
                    }
                    for (int k = 0; k < entriesCount; k++) {
                        int entryNodeIndex = k + 1;
                        String entryName = returnXpathNodes(xmlFromNovus,
                                String.format(XML_NOVUS_PROVISION_GROUP_ENTRY_CITE, groupNodeIndex, entryNodeIndex)).item(0).getTextContent();
                        NodeList entryTitleNodeList = returnXpathNodes(xmlFromNovus,
                                String.format(XML_NOVUS_PROVISION_GROUP_ENTRY_TITLE, groupNodeIndex, entryNodeIndex));
                        entryName = (entryTitleNodeList.getLength() > 0) ?
                                entryName + " " + entryTitleNodeList.item(0).getTextContent() : entryName;
                        provisions.add(entryName);
                    }
                    aopProvisions.put(groupName, provisions);
                }

                document.setAopProvisions(aopProvisions);
            }
        } catch (Exception e) {
            LOG.warn("There is no version / versions for the document: " + document.getGuid());
        }
    }

}
