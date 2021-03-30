package com.thomsonreuters.pageobjects.utils.novus;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Pavel_Ardenka on 18/07/2016.
 * This enumeration contains Easel-like search queries
 */
public enum NovusSearchCriteria {

    LEGISLATION_BY_FLAG_COLOR_CODE {
        /**
         * Get query to search legislation documents by the flag color code (except historical documents)
         *
         * @param args Only one argument allowed:
         *             1. Color code. With status code pass any additional non-word symbol if there is expected that date is
         *             already appointed (for example, for code "N" we can have appointed date and not. If you need to
         *             "Not in force" document with appointed date, pass status code something like that: N*)
         * @return String for searching query for legislation documents by color code
         */
        @Override
        public String get(String... args) {
            // If any non-word character is present in the code, we need to find the documents with date in force specified
            String commencementOrDate = StringUtils.isAlpha(args[0]) ? "commencement": "start-date";

            // Just in case: remove all non-word characters from the code if it was passed for searching doc by date
            String codeForQuery = args[0].replaceAll("\\W+", "");
            return "((=md.flag.color.code(" + codeForQuery + ") & =" + commencementOrDate + ") OR (=md.flag.color.code(" + codeForQuery + "))) % =md.infotype(legis-aop-nc)  % =md.infotype(legis-historical) ";
        }
    },
    LEGIS_BILL_BY_FLAG_COLOR_CODE_AND_BILL_TYPE_NAME {
        /**
         * Get query to search legislation provision bills documents by the flag color code and bill type
         *
         * @param args Only two arguments allowed:
         *             1. Color code
         *             2. Bill type name {@link com.thomsonreuters.pageobjects.utils.document.LegislationDocument.BillType#getTypeName()}
         * @return String for searching query for legislation bills by color code and bill type name
         */
        @Override
        public String get(String... args) {
            return "((=md.flag.color.code(" + args[0] + ") & =commencement) OR (=md.flag.color.code(" + args[0] + ")) & " +
                    "(=type(" + args[1] + "))) % =md.infotype(legis-aop-nc) % =md.infotype(legis-AOP) % =md.infotype(legis-AOA)";
        }
    },
    CASES_BY_FLAG_COLOR_CODE {
        /**
         * Get query to search case documents by the flag color code
         *
         * @param args Only one argument allowed:
         *             1. Color code
         * @return String for searching query for case documents by color code
         */
        @Override
        public String get(String... args) {
            return "=md.flag.color.code(" + args[0] + ")";
        }
    },
    ANY_CASES {
        /**
         * Get query to search any case documents with parties
         * @param args There are no arguments
         * @return String for search query for any case documents which contains parties
         */
        @Override
        public String get(String... args) {
            return "=case-treatment-status-facet";
        }
    },
    CASES_BY_TREATMENT_FACET {
        /**
         * Get query to search case documents with case treatment status facet
         * @param args Two or more treatment statuses.
         *             See {@link com.thomsonreuters.pageobjects.utils.document.CaseDocument.CaseDocumentStatus#getCaseTreatmentFacets}
         *             If there are only one argument or none, then {@link #ANY_CASES} query will be used, because
         *             Novus has too much results when we search for the one case treatment facet status only.
         * @return String for search query for any case documents which contains parties
         */
        @Override
        public String get(String... args) {
            int facetsCount = args.length;
            if (facetsCount > 1) {
                int i = 1;
                StringBuilder stringBuilder = new StringBuilder();
                for (String facet : args) {
                    stringBuilder.append("=case-treatment-status-facet(").append(facet).append(")");
                    if (i < facetsCount) {
                        stringBuilder.append(" & ");
                    }
                    i++;
                }
                return stringBuilder.toString();
            } else {
                return ANY_CASES.get();
            }
        }
    },
    PLC_DOC_BY_PLC_REF {
        /**
         * Get any document from PLC by plcRef
         *
         * @param args Only one argument allowed:
         *             1. Document plcRef
         * @return String for search query for any document by PLC Ref
         */
        @Override
        public String get(String... args) {
            return "=md.legacy.id(" + args[0] + ")";
        }
    };

    public abstract String get(String... args);

}
