package com.thomsonreuters.pageobjects.utils.ask_re_write.ask_query;


import com.thomsonreuters.pageobjects.utils.ask_re_write.Database;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class represents the entity of the newly created query in the Ask Editorial system
 * <p>
 * All the description of the query being created is indicated by passing into the
 * {@link AskQueryFields} annotation
 * <p>
 * Query fields are pre-defined and mapped from file whose fields match the fields in the current class
 * {@link com.thomsonreuters.pageobjects.utils.ask_re_write.data_manager.AskQueryDataYamlReader} <p>
 * Usage example:
 *
 * <blockquote><pre>
 * File containing a value as following:
 *
 *  email: email@example.com
 *
 * Will populate the {@code email} field below using related {@code setEmail} method
 * </pre></blockquote><p>
 * <p> Passing a field which does not exit in the {@code NewAskQuery} class or does not have a related setter method
 * will cause a {@link org.yaml.snakeyaml.error.YAMLException} to be thrown. <p>
 */

public class NewAskQuery {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NewAskQuery.class);

    private String description;

    @AskQueryFields(elementOnDashboard = "Query Text", questionFieldInRequest = "question", commentFieldInRequest = "comment",
            relatedSqlQueries = {"SUBMISSION_DATA.TEXT", "QUESTION.TEXT"})
    private String queryText;

    @AskQueryFields(questionFieldInRequest = "resourceId", commentFieldInRequest = "documentId",
            relatedSqlQueries = {"SUBMISSION_DATA.SOURCE_INFO"})
    private String documentId;

    @AskQueryFields(elementOnDashboard = "Subscriber", questionFieldInRequest = "firstName", commentFieldInRequest = "userFullName",
            relatedSqlQueries = {"SUBMISSION_DATA.FIRST_NAME"})
    private String firstName;

    @AskQueryFields(elementOnDashboard = "Subscriber", questionFieldInRequest = "lastName", commentFieldInRequest = "userFullName",
            relatedSqlQueries = {"SUBMISSION_DATA.LAST_NAME"})
    private String lastName;

    @AskQueryFields(questionFieldInRequest = "email", relatedSqlQueries = {"SUBMISSION_DATA.EMAIL"})
    private String email;

    @AskQueryFields(elementOnDashboard = "Organisation", questionFieldInRequest = "organisationType", commentFieldInRequest = "userFirmName",
            relatedSqlQueries = {"SUBMISSION_DATA.ORGANISATION_TYPE"})
    private String organisation;

    @AskQueryFields(elementOnDashboard = "Position", questionFieldInRequest = "position",
            relatedSqlQueries = {"SUBMISSION_DATA.POSITION"})
    private String position;

    @AskQueryFields(questionFieldInRequest = "jurisdiction")
    private String jurisdiction;

    @AskQueryFields(elementOnDashboard = "Practice Area", questionFieldInRequest = "answeringService",
            relatedSqlQueries = {"PRACTICE_AREA.FORM_NAME"})
    private String practiceArea;

    @AskQueryFields(questionFieldInRequest = "submit", commentFieldInRequest = "commentSubmit")
    private final String submit = "submit";

    @AskQueryFields(elementOnDashboard = "Resource Id", sendToServer = false, relatedSqlQueries = {"QUESTION.PLC_REF"})
    private String plc_ref;

    @AskQueryFields(elementOnDashboard = "Date Received", sendToServer = false, relatedSqlQueries = {"QUESTION.DATE_CREATED_ON_ASK"})
    private final String dateReceived = String.valueOf(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date()));

    @AskQueryFields(elementOnDashboard = "Query Type", sendToServer = false)
    private String queryType;

    @AskQueryFields(elementOnDashboard = "Status", sendToServer = false, relatedSqlQueries = {"QUESTION.STATUS"})
    private final String status = "Not started";

    /**
     * Allocates a new {@link Map} that contains key-value pairs needed for constructing
     * a request to the Ask Editorial system based on the desired {@code queryType}.
     * <p>
     * Key contains a {@link String} representation of how this particular field is named in the request body.
     * Value contains an exact value of the {@link String} which will be send to the server. </p>
     *
     * Key-value pair will be put into the new {@link Map} only if the
     * {@code sendToServer} parameter of {@link AskQueryFields} is true
     *
     * @param queryType
     *        Type of the new Query that needs to be created by request
     */
    public Map<String, String> getKeyValuesForRequest(AskQueryType queryType) {
        Map<String, String> keyValues = new LinkedHashMap<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(AskQueryFields.class)) {
                AskQueryFields fieldAnnotation = field.getAnnotation(AskQueryFields.class);
                field.setAccessible(true);
                if (fieldAnnotation.sendToServer()) {
                    String fieldInRequest = null;
                    switch (queryType) {
                        case QUESTION:
                            fieldInRequest = fieldAnnotation.questionFieldInRequest();
                            break;
                        case COMMENT:
                            fieldInRequest = fieldAnnotation.commentFieldInRequest();
                            break;
                    }
                    keyValues.put(fieldInRequest, tryGetField(field));
                }
            }
        }
        return keyValues;
    }

    /**
     * Allocates a new {@link Map} that contains key-value pairs representing the mapping between
     * the SQL query to the Ask Database {@link Database}  for the particular field of the Ask Query
     * and the related field in the current {@link NewAskQuery} class.
     * <p>
     * Key contains a {@link String} array of the SQL queries that refer to the fields in the Ask
     * Database {@link Database} which are expected to be populated after the new Ask Query was created.
     * Value - is the specific value of the field stored in the Ask Database </p>
     *
     * Key-value pair will be put into the new {@link Map} only if the
     * {@code relatedSqlQueries} parameter of {@link AskQueryFields} is not empty
     *
     */
    public Map<String[], String> getKeyValuesForSqlQuery() {
        Map<String[], String> keyValues = new LinkedHashMap<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(AskQueryFields.class)) {
                AskQueryFields fieldAnnotation = field.getAnnotation(AskQueryFields.class);
                field.setAccessible(true);
                if (!fieldAnnotation.relatedSqlQueries()[0].isEmpty()) {
                    keyValues.put(fieldAnnotation.relatedSqlQueries(), tryGetField(field));
                }
            }
        }
        return keyValues;
    }

    /**
     * Allocates a new {@link Map} that contains key-value pairs representing the mapping between the element
     * on the Ask Dashboard {@link com.thomsonreuters.pageobjects.ask_re_write.AskRewriteDashboardPage}
     * matching the particular field of the Ask Query created in ihe Ask Editorial system
     * and the related field in the current {@link NewAskQuery} class.
     * <p>
     * Key contains a {@link String} representation of the Ask Query field name on the Dashboard
     * {@link com.thomsonreuters.pageobjects.ask_re_write.AskRewriteDashboardPage}
     * which is expected to be displayed after the new Ask Query was created.
     * Value - is the specific value of the field displayed on the Ask Dashboard web page </p>
     *
     * Key-value pair will be put into the new {@link Map} only if the
     * {@code elementOnDashboard} parameter of {@link AskQueryFields} is not empty
     *
     */
    public Map<String, String> getKeyValuesForDashboard() {
        Map<String, String> keyValues = new LinkedHashMap<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(AskQueryFields.class)) {
                AskQueryFields fieldAnnotation = field.getAnnotation(AskQueryFields.class);
                field.setAccessible(true);
                if (!fieldAnnotation.elementOnDashboard().isEmpty()) {
                    keyValues.put(fieldAnnotation.elementOnDashboard(), tryGetField(field));
                }
            }
        }
        return keyValues;
    }

    private String tryGetField(Field field) {
        try {
            return String.valueOf(field.get(this));
        } catch (IllegalAccessException ex) {
            LOG.error("No access to private field " + field.getName(), ex);
        }
        throw new RuntimeException("Value was not returned from field: " + field.getName());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getPracticeArea() {
        return practiceArea;
    }

    public void setPracticeArea(String practiceArea) {
        this.practiceArea = practiceArea;
    }

    public String getPlc_ref() {
        return plc_ref;
    }

    public void setPlc_ref(String plc_ref) {
        this.plc_ref = plc_ref;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
}