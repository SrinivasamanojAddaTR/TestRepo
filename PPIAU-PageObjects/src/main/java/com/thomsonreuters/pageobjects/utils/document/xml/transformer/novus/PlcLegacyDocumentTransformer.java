package com.thomsonreuters.pageobjects.utils.document.xml.transformer.novus;

import com.thomsonreuters.pageobjects.utils.document.Document;
import com.thomsonreuters.pageobjects.utils.document.xml.transformer.BaseDocumentTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;

/**
 * Created by Pavel_Ardenka on 27/09/2016.
 */
public class PlcLegacyDocumentTransformer extends BaseDocumentTransformer {

    private static final Logger LOG = LoggerFactory.getLogger(PlcLegacyDocumentTransformer.class);
    private static final String XML_NOVUS_GUID_XPATH = "//md.uuid";

    public void setDocumentGuid(String xmlFromNovus, Document document) {
        try {
            NodeList provisionNodeList = returnXpathNodes(xmlFromNovus, XML_NOVUS_GUID_XPATH);
            document.setGuid(provisionNodeList.item(0).getTextContent());
        } catch (Exception e) {
            LOG.info("XML processing error. GUID: " + document.getGuid(), e);
        }
    }

}
