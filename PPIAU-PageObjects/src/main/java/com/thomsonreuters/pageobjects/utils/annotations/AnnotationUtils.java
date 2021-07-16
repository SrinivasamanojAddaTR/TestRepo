package com.thomsonreuters.pageobjects.utils.annotations;

import com.thomsonreuters.pageobjects.pages.annotations.SharedAnnotationsPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document_navigation.DocumentDeliveryPage;

/**
 * Created by Pavel_Ardenka on 31/10/2016.
 */
public class AnnotationUtils {

    private SharedAnnotationsPage sharedAnnotationsPage = new SharedAnnotationsPage();
    private DocumentDeliveryPage deliveryPage = new DocumentDeliveryPage();

    /**
     * Add annotation to a document (document level)
     *
     * @param annotationText Text for annotation
     */
    // Copy-pasted from com.thomsonreuters.annotations.step_definitions.common.AnnotationsStepDef.userAddedNewAnnotation()
    public void addAnnotation(String annotationText) {
        deliveryPage.newAnnotationButton().click();
        sharedAnnotationsPage.amendInput(annotationText);
        sharedAnnotationsPage.saveAnnotation();
    }


}
