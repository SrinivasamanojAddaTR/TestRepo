package com.thomsonreuters.pageobjects.utils.document.xml.transformer;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.utils.document.metadata.Product;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pavel_Ardenka on 18/07/2016.
 * Base abstract class for transformation XML document representation to
 * {@link com.thomsonreuters.pageobjects.utils.document.Document} objects and it inheritances
 */
public abstract class BaseDocumentTransformer {

    protected CommonMethods commonMethods = new CommonMethods();

    protected static final String XML_NOVUS_ELEMENT_DATE_FORMAT = "yyyyMMdd";

    // TODO Do we need this?
    public String getXLINKURI() {
        return "http://www.w3.org/1999/xlink";
    }

    // TODO Do we need this?
    public String getATICTURI() {
        return "http://www.arbortext.com/namespace/atict";
    }

    /**
     * Get nodes from XML string by XPath
     *
     * @param pageSource XML String
     * @param strXpath XPath to search
     * @return NodeList with found node
     * @throws Exception if there are parse or IO error occured
     */
    public NodeList returnXpathNodes(String pageSource, String strXpath) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setNamespaceAware(true);
        DocumentBuilder builder = docFactory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(pageSource)));
        XPath xpath = XPathFactory.newInstance().newXPath();
        xpath.setNamespaceContext(new NamespaceContext() {

            @Override
            public String getNamespaceURI(String prefix) {
                if ("xlink".equals(prefix)) {
                    return getXLINKURI();
                } else if ("atict".equals(prefix)) {
                    return getATICTURI();
                }
                return "";
            }

            @Override
            public String getPrefix(String namespaceURI) {
                return "";
            }

            @Override
            public Iterator getPrefixes(String namespaceURI) {
                return Collections.emptyIterator();
            }
        });

        NodeList nodes = (NodeList) xpath.evaluate(strXpath, doc, XPathConstants.NODESET);
        return nodes;
    }

    /**
     * Get products list from Fatwire XML document for meta data
     *
     * @param productsNodeList Nodes with products
     * @return List with products {@link Product}
     */
    public List<Product> getMetaDataProductFromNodeList(NodeList productsNodeList) {
        List<Product> products = new ArrayList<>();
        int itemsCount = productsNodeList.getLength();
        for (int i = 0; i < itemsCount; i++) {
            Node productNode = productsNodeList.item(i);
            NodeList namePlcRefNodes = productNode.getChildNodes();
            Product product = new Product();
            // First child is "name"
            product.setName(namePlcRefNodes.item(0).getTextContent().trim());
            // Second child is "plc reference"
            product.setPlcReference(namePlcRefNodes.item(1).getTextContent());
            products.add(product);
        }
        return products;
    }
}
