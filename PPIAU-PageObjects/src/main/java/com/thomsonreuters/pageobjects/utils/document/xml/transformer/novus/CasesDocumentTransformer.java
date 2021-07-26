package com.thomsonreuters.pageobjects.utils.document.xml.transformer.novus;

import com.thomsonreuters.pageobjects.utils.document.CaseDocument;
import com.thomsonreuters.pageobjects.utils.document.xml.transformer.BaseDocumentTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel_Ardenka on 18/07/2016.
 * Transform cases documents from XML to {@link CaseDocument}
 */
public class CasesDocumentTransformer extends BaseDocumentTransformer {

    private static final Logger LOG = LoggerFactory.getLogger(CasesDocumentTransformer.class);

    private static final String XML_NOVUS_CASES_PARTIES = "//parties/*[contains(local-name(), 'party')]";
    private static final String XML_NOVUS_CASES_FLAG_COLOR_CODE = "//md.flag.color.code";

    /**
     * Set case specific info for case object document {@link CaseDocument}:
     * - party names
     *
     * @param xmlFromNovus XML document representation
     * @param document Link to the case document object
     */
    public void setCasesSpecificDocumentInfo(String xmlFromNovus, CaseDocument document) {
        try {
            NodeList partiesNodeList = returnXpathNodes(xmlFromNovus, XML_NOVUS_CASES_PARTIES);
            List<String> parties = new ArrayList<>();
            int partiesCount = partiesNodeList.getLength();
            for (int i = 0; i < partiesCount; i++) {
                parties.add(partiesNodeList.item(i).getTextContent());
            }
            document.setPartyNames(parties);
            setDocumentStatus(xmlFromNovus, document);
        } catch (Exception e) {
            LOG.info("XML processing error. GUID: " + document.getGuid(), e);
        }
    }

    /**
     * Set case specific info for case object document {@link CaseDocument}:
     * - document status (by flag color code if available)
     *
     * @param xmlFromNovus XML document representation
     * @param document Link to the case document object
     */
    private void setDocumentStatus(String xmlFromNovus, CaseDocument document) {
        try {
            NodeList flagColorCodeNodeList = returnXpathNodes(xmlFromNovus, XML_NOVUS_CASES_FLAG_COLOR_CODE);
            document.setDocumentStatus(CaseDocument.CaseDocumentStatus.getStatusByColorCode(
                    flagColorCodeNodeList.item(0).getTextContent()));
        } catch (Exception e) {
            LOG.warn("There is no flag color code for the document: {}", document.getGuid());
        }
    }

}
