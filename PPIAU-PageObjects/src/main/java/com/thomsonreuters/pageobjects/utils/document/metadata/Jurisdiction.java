package com.thomsonreuters.pageobjects.utils.document.metadata;

/**
 * Created by Pavel_Ardenka on 10/12/2015.
 */
public class Jurisdiction {

    private String name;
    private String plcReference;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlcReference() {
        return plcReference;
    }

    public void setPlcReference(String plcReference) {
        this.plcReference = plcReference;
    }

    @Override
    public String toString() {
        return "Jurisdiction{" +
                "name='" + name + '\'' +
                ", plcReference='" + plcReference + '\'' +
                '}';
    }
}
