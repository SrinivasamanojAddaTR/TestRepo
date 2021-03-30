package com.thomsonreuters.pageobjects.utils.Linking;

import com.westgroup.novus.message.DOMUtil;
import org.w3c.dom.Node;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

public class DocInstance {

    String docLoc, docIdType, docPubWestCode, docGuid;

    public String getDocLoc() {
        return docLoc;
    }

    public void setDocLoc(String docLoc) {
        this.docLoc = docLoc;
    }

    public String getDocIdType() {
        return docIdType;
    }

    public void setDocIdType(String docIdType) {
        this.docIdType = docIdType;
    }

    public String getDocPubWestCode() {
        return docPubWestCode;
    }

    public void setDocPubWestCode(String docPubWestCode) {
        this.docPubWestCode = docPubWestCode;
    }

    public String getDocGuid() {
        return docGuid;
    }

    public void setDocGuid(String docGuid) {
        this.docGuid = docGuid;
    }

    @Override
    public String toString() {
        return "DocInstance{" +
                "docLoc='" + docLoc + '\'' +
                ", docIdType='" + docIdType + '\'' +
                ", docPubWestCode='" + docPubWestCode + '\'' +
                ", docGuid='" + docGuid + '\'' +
                '}';
    }

    public void saveToDOM(Node buffer) throws Exception {
        Node dfrNode = buffer.getOwnerDocument().createElement(
                "docInstance");
        buffer.appendChild(dfrNode);
        DOMUtil.addElement(dfrNode, "docLoc", defaultIfBlank(docLoc, ""));
        DOMUtil.addElement(dfrNode, "docIdType",
                defaultIfBlank(docIdType, ""));
        DOMUtil.addElement(dfrNode, "docPubWestCode",
                defaultIfBlank(docPubWestCode, ""));
        DOMUtil.addElement(dfrNode, "docGuid", defaultIfBlank(docGuid, ""));
    }

}
