package com.thomsonreuters.pageobjects.utils.askRewrite.askQuery;

import com.thomsonreuters.pageobjects.utils.askRewrite.Database;

import java.lang.annotation.*;


@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface AskQueryFields {
    /**
     * @return the fact whether the value of the annotated field should be sent to the server
     */
    boolean sendToServer() default true;
    /**
     * @return name of the field in the request for creating a new Ask Query
     * {@link NewAskQuery} with the {@link AskQueryType} QUESTION
     */
    String questionFieldInRequest() default "";
    /**
     * @return name of the field in the request for creating a new Ask Query
     * {@link NewAskQuery} with the {@link AskQueryType} COMMENT
     */
    String commentFieldInRequest() default "";
    /**
     * @return array of the SQL queries that refer to the fields in the Ask Database
     * {@link Database} which are expected to be populated with the value of the annotated field
     */
    String[] relatedSqlQueries() default "";
    /**
     * @return name of the annotated field expected to be displayed on the Ask Dashboard
     * {@link com.thomsonreuters.pageobjects.ask_re_write.AskRewriteDashboardPage}
     */
    String elementOnDashboard() default "";
}
