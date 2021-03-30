package com.thomsonreuters.pageobjects.utils.Linking;

import com.westgroup.novus.message.DOMUtil;
import org.w3c.dom.Node;

import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

public class DocFamilyRecord {

    DocInstance[] docInstances;
    String docFamilyGuid;

    @Override
    public String toString() {
        return "DocFamilyRecord{" +
                "docInstances=" + Arrays.toString(docInstances) +
                ", docFamilyGuid='" + docFamilyGuid + '\'' +
                '}';
    }

    public DocInstance[] getDocInstances() {
        return docInstances;
    }

    public void setDocInstances(DocInstance[] docInstances) {
        this.docInstances = docInstances;
    }

    public String getDocFamilyGuid() {
        return docFamilyGuid;
    }

    public void setDocFamilyGuid(String docFamilyGuid) {
        this.docFamilyGuid = docFamilyGuid;
    }

    public void saveToDOM(Node buffer) throws Exception {
        Node dfrNode = buffer.getOwnerDocument().createElement(
                "docFamilyRecord");
        buffer.appendChild(dfrNode);
        for (DocInstance inst : docInstances) {
            inst.saveToDOM(dfrNode);
        }
        DOMUtil.addElement(dfrNode, "docFamilyGuid",
                defaultIfBlank(docFamilyGuid, ""));
    }

}
