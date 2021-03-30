package com.thomsonreuters.pageobjects.rest.model.request.delivery.initiateDelivery;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thomsonreuters.pageobjects.rest.model.request.ABaseRequest;

/**
 * Created by Pavel_Ardenka on 26.10.2015.
 */
class AdditionalContentOptions extends ABaseRequest {

    private static final String INCLUDE_DOC_WITH_RI_LISTS_NAME = "IncludeDocWithRILists";
    private static final String CITING_REFERENCES_NAME = "CitingReferences";
    private static final String EXCLUDE_COURT_DOCS_FROM_CITING_REFERENCES_NAME = "ExcludeCourtDocsFromCitingReferences";
    private static final String APPELLATE_HISTORY_NAME = "AppellateHistory";
    private static final String APPELLATE_HISTORY_DIAGRAM_NAME = "AppellateHistoryDiagram";
    private static final String FILINGS_NAME = "Filings";
    private static final String NEGATIVE_TREATMENTS_NAME = "NegativeTreatments";
    private static final String STATUTE_HISTORY_NEGATIVE_TREATMENTS_NAME = "StatuteHistoryNegativeTreatments";
    private static final String TOPICS_NAME = "Topics";
    private static final String NOTES_OF_DECISIONS_NAME = "NotesOfDecisions";
    private static final String BILL_DRAFTS_NAME = "BillDrafts";
    private static final String LEGISLATIVE_HISTORY_NOTES_NAME = "LegislativeHistoryNotes";
    private static final String REPORTS_AND_RELATED_MATERIALS_NAME = "ReportsAndRelatedMaterials";
    private static final String VERSIONS_NAME = "Versions";
    private static final String RELATED_OPINIONS_NAME = "RelatedOpinions";
    private static final String HISTORY_NAME = "History";
    private static final String REGULATORY_HISTORY_NAME = "RegulatoryHistory";
    private static final String CONTEXT_ANALYSIS_NAME = "ContextAnalysis";
    private static final String STATUTES_AFFECTED_NAME = "StatutesAffected";
    private static final String BILL_ACTIVITY_NAME = "BillActivity";
    private static final String BILL_TRACKING_NAME = "BillTracking";
    private static final String TABLE_OF_AUTHORITIES_NAME = "TableOfAuthorities";
    private static final String REFERENCES_NAME = "References";
    private static final String ARBITRATION_RULES_NAME = "ArbitrationRules";
    private static final String ANNOTATIONS_NAME = "Annotations";
    private static final String COURT_DOCS_NAME = "CourtDocs";
    private static final String LEGAL_MEMOS_NAME = "LegalMemos";
    private static final String CANADIAN_EXPERT_WITNESS_NAME = "CanadianExpertWitness";
    private static final String EXHIBITS_NAME = "Exhibits";
    private static final String INCLUDE_FILING_NAME = "IncludeFiling";
    private static final String RULEBOOK_CATEGORY_VERSIONS_NAME = "RulebookCategoryVersions";
    private static final String RULEBOOK_CATEGORY_CITING_REFERENCES_NAME = "RulebookCategoryCitingReferences";
    private static final String RULEBOOK_CATEGORY_PROPOSED_LEGISLATION_NAME = "RulebookCategoryProposedLegislation";
    private static final String RULEBOOK_CATEGORY_PROPOSED_REGULATION_NAME = "RulebookCategoryProposedRegulation";
    private static final String RULEBOOK_CATEGORY_ADOPTED_REGULATION_NAME = "RulebookCategoryAdoptedRegulation";
    private static final String RULEBOOK_CATEGORY_ENACTED_LEGISLATION_NAME = "RulebookCategoryEnactedLegislation";
    private static final String RULEBOOK_CATEGORY_PRACTICAL_LAW_NAME = "RulebookCategoryPracticalLaw";
    private static final String RULEBOOK_CATEGORY_RELATED_RULE_BOOKS_NAME = "RulebookCategoryRelatedRulebooks";
    private static final String RULEBOOK_CATEGORY_CURRENT_TEXT_NAME = "RulebookCategoryCurrentText";
    private static final String IS_TILE_VIEW_NAME = "IsTileView";
    private static final String IS_GRID_VIEW_NAME = "IsGridView";
    private static final String GRID_VIEW_COLUMNS_NAME = "GridViewColumns";
    private static final String KEY_CITE_WARNING_NAME = "KeyCiteWarning";
    private static final String EXPANDED_LIST_NAME = "ExpandedList";
    private static final String EXTENDED_PATENT_FAMILY_NAME = "ExtendedPatentFamily";
    private static final String EXTENDED_FAMILY_ERROR_NAME = "ExtendedFamilyError";
    private static final String PEOPLE_MAP_MINI_REPORT_NAME = "PeopleMapMiniReport";
    private static final String IP_TOOLS_NAME = "IPTools";
    private static final String IP_TOOLS_REFERENCES_CITED_NAME = "IPToolsReferencesCited";
    private static final String IP_TOOLS_CLAIMS_HISTORY_NAME = "IPToolsClaimsHistory";
    private static final String IP_TOOLS_PATENT_FILE_HISTORY_NAME = "IPToolsPatentFileHistory";
    private static final String IP_TOOLS_FAMILY_LEGAL_STATUS_NAME = "IPToolsFamilyLegalStatus";
    private static final String MEDICAL_REFERENCES_NAME = "MedicalReferences";
    private static final String RI_ASSET_PAGE_NAME = "RIAssetPage";
    private static final String META_DATA_NAME = "MetaData";

    // Default values
    private boolean includeDocWithRILists = false;
    private boolean citingReferences = false;
    private boolean excludeCourtDocsFromCitingReferences = false;
    private boolean appellateHistory = false;
    private boolean appellateHistoryDiagram = false;
    private boolean filings = false;
    private boolean negativeTreatments = false;
    private boolean statuteHistoryNegativeTreatments = false;
    private boolean topics = false;
    private boolean notesOfDecisions = false;
    private boolean billDrafts = false;
    private boolean legislativeHistoryNotes = false;
    private boolean reportsAndRelatedMaterials = false;
    private boolean versions = false;
    private boolean relatedOpinions = false;
    private boolean history = false;
    private boolean regulatoryHistory = false;
    private boolean contextAnalysis = false;
    private boolean statutesAffected = false;
    private boolean billActivity = false;
    private boolean billTracking = false;
    private boolean tableOfAuthorities = false;
    private boolean references = false;
    private boolean arbitrationRules = false;
    private boolean annotations = false;
    private boolean courtDocs = false;
    private boolean legalMemos = false;
    private boolean canadianExpertWitness = false;
    private boolean exhibits = false;
    private boolean includeFiling = false;
    private boolean rulebookCategoryVersions = false;
    private boolean rulebookCategoryCitingReferences = false;
    private boolean rulebookCategoryProposedLegislation = false;
    private boolean rulebookCategoryProposedRegulation = false;
    private boolean rulebookCategoryAdoptedRegulation = false;
    private boolean rulebookCategoryEnactedLegislation = false;
    private boolean rulebookCategoryPracticalLaw = false;
    private boolean rulebookCategoryRelatedRulebooks = false;
    private boolean rulebookCategoryCurrentText = false;
    private boolean isTileView = false;
    private boolean isGridView = false;
    private boolean gridViewColumns = false;
    private boolean keyCiteWarning = false;
    private boolean expandedList = false;
    private boolean extendedPatentFamily = true;
    private boolean extendedFamilyError = false;
    private boolean peopleMapMiniReport = false;
    private boolean ipTools = false;
    private boolean ipToolsReferencesCited = false;
    private boolean ipToolsClaimsHistory = false;
    private boolean ipToolsPatentFileHistory = false;
    private boolean ipToolsFamilyLegalStatus = false;
    private boolean medicalReferences = false;
    private boolean riAssetPage = false;
    private MetaData metaData = new MetaData();

    public AdditionalContentOptions setIncludeDocWithRILists(boolean includeDocWithRILists) {
        this.includeDocWithRILists = includeDocWithRILists;
        return this;
    }

    public AdditionalContentOptions setCitingReferences(boolean citingReferences) {
        this.citingReferences = citingReferences;
        return this;
    }

    public AdditionalContentOptions setExcludeCourtDocsFromCitingReferences(boolean excludeCourtDocsFromCitingReferences) {
        this.excludeCourtDocsFromCitingReferences = excludeCourtDocsFromCitingReferences;
        return this;
    }

    public AdditionalContentOptions setAppellateHistory(boolean appellateHistory) {
        this.appellateHistory = appellateHistory;
        return this;
    }

    public AdditionalContentOptions setAppellateHistoryDiagram(boolean appellateHistoryDiagram) {
        this.appellateHistoryDiagram = appellateHistoryDiagram;
        return this;
    }

    public AdditionalContentOptions setFilings(boolean filings) {
        this.filings = filings;
        return this;
    }

    public AdditionalContentOptions setNegativeTreatments(boolean negativeTreatments) {
        this.negativeTreatments = negativeTreatments;
        return this;
    }

    public AdditionalContentOptions setStatuteHistoryNegativeTreatments(boolean statuteHistoryNegativeTreatments) {
        this.statuteHistoryNegativeTreatments = statuteHistoryNegativeTreatments;
        return this;
    }

    public AdditionalContentOptions setTopics(boolean topics) {
        this.topics = topics;
        return this;
    }

    public AdditionalContentOptions setNotesOfDecisions(boolean notesOfDecisions) {
        this.notesOfDecisions = notesOfDecisions;
        return this;
    }

    public AdditionalContentOptions setBillDrafts(boolean billDrafts) {
        this.billDrafts = billDrafts;
        return this;
    }

    public AdditionalContentOptions setLegislativeHistoryNotes(boolean legislativeHistoryNotes) {
        this.legislativeHistoryNotes = legislativeHistoryNotes;
        return this;
    }

    public AdditionalContentOptions setReportsAndRelatedMaterials(boolean reportsAndRelatedMaterials) {
        this.reportsAndRelatedMaterials = reportsAndRelatedMaterials;
        return this;
    }

    public AdditionalContentOptions setVersions(boolean versions) {
        this.versions = versions;
        return this;
    }

    public AdditionalContentOptions setRelatedOpinions(boolean relatedOpinions) {
        this.relatedOpinions = relatedOpinions;
        return this;
    }

    public AdditionalContentOptions setHistory(boolean history) {
        this.history = history;
        return this;
    }

    public AdditionalContentOptions setRegulatoryHistory(boolean regulatoryHistory) {
        this.regulatoryHistory = regulatoryHistory;
        return this;
    }

    public AdditionalContentOptions setContextAnalysis(boolean contextAnalysis) {
        this.contextAnalysis = contextAnalysis;
        return this;
    }

    public AdditionalContentOptions setStatutesAffected(boolean statutesAffected) {
        this.statutesAffected = statutesAffected;
        return this;
    }

    public AdditionalContentOptions setBillActivity(boolean billActivity) {
        this.billActivity = billActivity;
        return this;
    }

    public AdditionalContentOptions setBillTracking(boolean billTracking) {
        this.billTracking = billTracking;
        return this;
    }

    public AdditionalContentOptions setTableOfAuthorities(boolean tableOfAuthorities) {
        this.tableOfAuthorities = tableOfAuthorities;
        return this;
    }

    public AdditionalContentOptions setReferences(boolean references) {
        this.references = references;
        return this;
    }

    public AdditionalContentOptions setArbitrationRules(boolean arbitrationRules) {
        this.arbitrationRules = arbitrationRules;
        return this;
    }

    public AdditionalContentOptions setAnnotations(boolean annotations) {
        this.annotations = annotations;
        return this;
    }

    public AdditionalContentOptions setCourtDocs(boolean courtDocs) {
        this.courtDocs = courtDocs;
        return this;
    }

    public AdditionalContentOptions setLegalMemos(boolean legalMemos) {
        this.legalMemos = legalMemos;
        return this;
    }

    public AdditionalContentOptions setCanadianExpertWitness(boolean canadianExpertWitness) {
        this.canadianExpertWitness = canadianExpertWitness;
        return this;
    }

    public AdditionalContentOptions setExhibits(boolean exhibits) {
        this.exhibits = exhibits;
        return this;
    }

    public AdditionalContentOptions setIncludeFiling(boolean includeFiling) {
        this.includeFiling = includeFiling;
        return this;
    }

    public AdditionalContentOptions setRulebookCategoryVersions(boolean rulebookCategoryVersions) {
        this.rulebookCategoryVersions = rulebookCategoryVersions;
        return this;
    }

    public AdditionalContentOptions setRulebookCategoryCitingReferences(boolean rulebookCategoryCitingReferences) {
        this.rulebookCategoryCitingReferences = rulebookCategoryCitingReferences;
        return this;
    }

    public AdditionalContentOptions setRulebookCategoryProposedLegislation(boolean rulebookCategoryProposedLegislation) {
        this.rulebookCategoryProposedLegislation = rulebookCategoryProposedLegislation;
        return this;
    }

    public AdditionalContentOptions setRulebookCategoryProposedRegulation(boolean rulebookCategoryProposedRegulation) {
        this.rulebookCategoryProposedRegulation = rulebookCategoryProposedRegulation;
        return this;
    }

    public AdditionalContentOptions setRulebookCategoryAdoptedRegulation(boolean rulebookCategoryAdoptedRegulation) {
        this.rulebookCategoryAdoptedRegulation = rulebookCategoryAdoptedRegulation;
        return this;
    }

    public AdditionalContentOptions setRulebookCategoryEnactedLegislation(boolean rulebookCategoryEnactedLegislation) {
        this.rulebookCategoryEnactedLegislation = rulebookCategoryEnactedLegislation;
        return this;
    }

    public AdditionalContentOptions setRulebookCategoryPracticalLaw(boolean rulebookCategoryPracticalLaw) {
        this.rulebookCategoryPracticalLaw = rulebookCategoryPracticalLaw;
        return this;
    }

    public AdditionalContentOptions setRulebookCategoryRelatedRulebooks(boolean rulebookCategoryRelatedRulebooks) {
        this.rulebookCategoryRelatedRulebooks = rulebookCategoryRelatedRulebooks;
        return this;
    }

    public AdditionalContentOptions setRulebookCategoryCurrentText(boolean rulebookCategoryCurrentText) {
        this.rulebookCategoryCurrentText = rulebookCategoryCurrentText;
        return this;
    }

    public AdditionalContentOptions setIsTileView(boolean isTileView) {
        this.isTileView = isTileView;
        return this;
    }

    public AdditionalContentOptions setIsGridView(boolean isGridView) {
        this.isGridView = isGridView;
        return this;
    }

    public AdditionalContentOptions setGridViewColumns(boolean gridViewColumns) {
        this.gridViewColumns = gridViewColumns;
        return this;
    }

    public AdditionalContentOptions setKeyCiteWarning(boolean keyCiteWarning) {
        this.keyCiteWarning = keyCiteWarning;
        return this;
    }

    public AdditionalContentOptions setExpandedList(boolean expandedList) {
        this.expandedList = expandedList;
        return this;
    }

    public AdditionalContentOptions setExtendedPatentFamily(boolean extendedPatentFamily) {
        this.extendedPatentFamily = extendedPatentFamily;
        return this;
    }

    public AdditionalContentOptions setExtendedFamilyError(boolean extendedFamilyError) {
        this.extendedFamilyError = extendedFamilyError;
        return this;
    }

    public AdditionalContentOptions setPeopleMapMiniReport(boolean peopleMapMiniReport) {
        this.peopleMapMiniReport = peopleMapMiniReport;
        return this;
    }

    public AdditionalContentOptions setIpTools(boolean ipTools) {
        this.ipTools = ipTools;
        return this;
    }

    public AdditionalContentOptions setIpToolsReferencesCited(boolean ipToolsReferencesCited) {
        this.ipToolsReferencesCited = ipToolsReferencesCited;
        return this;
    }

    public AdditionalContentOptions setIpToolsClaimsHistory(boolean ipToolsClaimsHistory) {
        this.ipToolsClaimsHistory = ipToolsClaimsHistory;
        return this;
    }

    public AdditionalContentOptions setIpToolsPatentFileHistory(boolean ipToolsPatentFileHistory) {
        this.ipToolsPatentFileHistory = ipToolsPatentFileHistory;
        return this;
    }

    public AdditionalContentOptions setIpToolsFamilyLegalStatus(boolean ipToolsFamilyLegalStatus) {
        this.ipToolsFamilyLegalStatus = ipToolsFamilyLegalStatus;
        return this;
    }

    public AdditionalContentOptions setMedicalReferences(boolean medicalReferences) {
        this.medicalReferences = medicalReferences;
        return this;
    }

    public AdditionalContentOptions setRiAssetPage(boolean riAssetPage) {
        this.riAssetPage = riAssetPage;
        return this;
    }

    public AdditionalContentOptions setMetaData(MetaData metaData) {
        this.metaData = metaData;
        return this;
    }

    @Override
    public ObjectNode getNode() {
        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
        jsonFactory.setCodec(objectMapper);
        ObjectNode additionalContentNode = objectMapper.createObjectNode();
        additionalContentNode.put(INCLUDE_DOC_WITH_RI_LISTS_NAME, includeDocWithRILists);
        additionalContentNode.put(CITING_REFERENCES_NAME, citingReferences);
        additionalContentNode.put(EXCLUDE_COURT_DOCS_FROM_CITING_REFERENCES_NAME, excludeCourtDocsFromCitingReferences);
        additionalContentNode.put(APPELLATE_HISTORY_NAME, appellateHistory);
        additionalContentNode.put(APPELLATE_HISTORY_DIAGRAM_NAME, appellateHistoryDiagram);
        additionalContentNode.put(FILINGS_NAME, filings);
        additionalContentNode.put(NEGATIVE_TREATMENTS_NAME, negativeTreatments);
        additionalContentNode.put(STATUTE_HISTORY_NEGATIVE_TREATMENTS_NAME, statuteHistoryNegativeTreatments);
        additionalContentNode.put(TOPICS_NAME, topics);
        additionalContentNode.put(NOTES_OF_DECISIONS_NAME, notesOfDecisions);
        additionalContentNode.put(BILL_DRAFTS_NAME, billDrafts);
        additionalContentNode.put(LEGISLATIVE_HISTORY_NOTES_NAME, legislativeHistoryNotes);
        additionalContentNode.put(REPORTS_AND_RELATED_MATERIALS_NAME, reportsAndRelatedMaterials);
        additionalContentNode.put(VERSIONS_NAME, versions);
        additionalContentNode.put(RELATED_OPINIONS_NAME, relatedOpinions);
        additionalContentNode.put(HISTORY_NAME, history);
        additionalContentNode.put(REGULATORY_HISTORY_NAME, regulatoryHistory);
        additionalContentNode.put(CONTEXT_ANALYSIS_NAME, contextAnalysis);
        additionalContentNode.put(STATUTES_AFFECTED_NAME, statutesAffected);
        additionalContentNode.put(BILL_ACTIVITY_NAME, billActivity);
        additionalContentNode.put(BILL_TRACKING_NAME, billTracking);
        additionalContentNode.put(TABLE_OF_AUTHORITIES_NAME, tableOfAuthorities);
        additionalContentNode.put(REFERENCES_NAME, references);
        additionalContentNode.put(ARBITRATION_RULES_NAME, arbitrationRules);
        additionalContentNode.put(ANNOTATIONS_NAME, annotations);
        additionalContentNode.put(COURT_DOCS_NAME, courtDocs);
        additionalContentNode.put(LEGAL_MEMOS_NAME, legalMemos);
        additionalContentNode.put(CANADIAN_EXPERT_WITNESS_NAME, canadianExpertWitness);
        additionalContentNode.put(EXHIBITS_NAME, exhibits);
        additionalContentNode.put(INCLUDE_FILING_NAME, includeFiling);
        additionalContentNode.put(RULEBOOK_CATEGORY_VERSIONS_NAME, rulebookCategoryVersions);
        additionalContentNode.put(RULEBOOK_CATEGORY_CITING_REFERENCES_NAME, rulebookCategoryCitingReferences);
        additionalContentNode.put(RULEBOOK_CATEGORY_PROPOSED_LEGISLATION_NAME, rulebookCategoryProposedLegislation);
        additionalContentNode.put(RULEBOOK_CATEGORY_PROPOSED_REGULATION_NAME, rulebookCategoryProposedRegulation);
        additionalContentNode.put(RULEBOOK_CATEGORY_ADOPTED_REGULATION_NAME, rulebookCategoryAdoptedRegulation);
        additionalContentNode.put(RULEBOOK_CATEGORY_ENACTED_LEGISLATION_NAME, rulebookCategoryEnactedLegislation);
        additionalContentNode.put(RULEBOOK_CATEGORY_PRACTICAL_LAW_NAME, rulebookCategoryPracticalLaw);
        additionalContentNode.put(RULEBOOK_CATEGORY_RELATED_RULE_BOOKS_NAME, rulebookCategoryRelatedRulebooks);
        additionalContentNode.put(RULEBOOK_CATEGORY_CURRENT_TEXT_NAME, rulebookCategoryCurrentText);
        additionalContentNode.put(IS_TILE_VIEW_NAME, isTileView);
        additionalContentNode.put(IS_GRID_VIEW_NAME, isGridView);
        additionalContentNode.put(GRID_VIEW_COLUMNS_NAME, gridViewColumns);
        additionalContentNode.put(KEY_CITE_WARNING_NAME, keyCiteWarning);
        additionalContentNode.put(EXPANDED_LIST_NAME, expandedList);
        additionalContentNode.put(EXTENDED_PATENT_FAMILY_NAME, extendedPatentFamily);
        additionalContentNode.put(EXTENDED_FAMILY_ERROR_NAME, extendedFamilyError);
        additionalContentNode.put(PEOPLE_MAP_MINI_REPORT_NAME, peopleMapMiniReport);
        additionalContentNode.put(IP_TOOLS_NAME, ipTools);
        additionalContentNode.put(IP_TOOLS_REFERENCES_CITED_NAME, ipToolsReferencesCited);
        additionalContentNode.put(IP_TOOLS_CLAIMS_HISTORY_NAME, ipToolsClaimsHistory);
        additionalContentNode.put(IP_TOOLS_PATENT_FILE_HISTORY_NAME, ipToolsPatentFileHistory);
        additionalContentNode.put(IP_TOOLS_FAMILY_LEGAL_STATUS_NAME, ipToolsFamilyLegalStatus);
        additionalContentNode.put(MEDICAL_REFERENCES_NAME, medicalReferences);
        additionalContentNode.put(RI_ASSET_PAGE_NAME, riAssetPage);
        additionalContentNode.set(META_DATA_NAME, metaData.getNode());
        return additionalContentNode;
    }

}
