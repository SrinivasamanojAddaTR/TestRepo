package com.thomsonreuters.pageobjects.rest.model.response.document.annotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomsonreuters.pageobjects.rest.model.response.ABaseResponse;

import java.util.List;

/**
 * Created by Pavel_Ardenka on 28/06/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnnotationsResponse extends ABaseResponse{

    @JsonProperty("DocumentLevelNotes")
    private List<Annotation> documentLevelNotes;

    @JsonProperty("InlineHighlightsAndNotes")
    private List<Annotation> inlineHighlightAndNotes;

    @JsonProperty("OrphanedHighlightsAndNotes")
    private List<Annotation> orphanedHighlightsAndNotes;

    public List<Annotation> getDocumentLevelNotes() {
        return documentLevelNotes;
    }

    public void setDocumentLevelNotes(List<Annotation> documentLevelNotes) {
        this.documentLevelNotes = documentLevelNotes;
    }

    public List<Annotation> getInlineHighlightAndNotes() {
        return inlineHighlightAndNotes;
    }

    public void setInlineHighlightAndNotes(List<Annotation> inlineHighlightAndNotes) {
        this.inlineHighlightAndNotes = inlineHighlightAndNotes;
    }

    public List<Annotation> getOrphanedHighlightsAndNotes() {
        return orphanedHighlightsAndNotes;
    }

    public void setOrphanedHighlightsAndNotes(List<Annotation> orphanedHighlightsAndNotes) {
        this.orphanedHighlightsAndNotes = orphanedHighlightsAndNotes;
    }
}
