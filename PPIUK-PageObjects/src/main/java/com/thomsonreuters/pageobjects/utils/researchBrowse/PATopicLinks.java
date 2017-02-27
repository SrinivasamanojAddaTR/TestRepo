package com.thomsonreuters.pageobjects.utils.researchBrowse;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/*
TODO:Still WIP. deciding the best solution
 */
public final class PATopicLinks {

    public static final Map<String, List> TAX =
            new ImmutableMap.Builder<String, List>()
                    .put("Companies and groups", Lists.newArrayList("Companies and Groups"))
                    .put("Corporate transactions", Lists.newArrayList("Asset Acquisitions", "Equity Capital Markets", "Joint Ventures", "Private equity and venture capital", "Share Acquisitions"))
                    .put("Cross-border", Lists.newArrayList("Cross-border"))
                    .put("Disputes and anti-avoidance", Lists.newArrayList("Anti-avoidance", "Disputes and Investigations"))
                    .put("Employment", Lists.newArrayList("Cross-border and Immigration", "Employment Income, Pensions and Benefits", "Employment Status and Independent Contractors", "National Insurance Contributions", "Termination of Employment"))
                    .put("Energy and environment", Lists.newArrayList("Energy and Environment"))
                    .put("Finance", Lists.newArrayList("Asset Finance", "Cross-border Finance", "Debt Capital Markets", "Islamic Finance", "Lending", "Structured Finance", "Swaps and Derivatives"))
                    .put("Investment funds", Lists.newArrayList("Investment Funds and Asset Management"))
                    .put("IP and IT", Lists.newArrayList("IP and IT"))
                    .put("Partnerships and OMBs", Lists.newArrayList("Owner-managed Businesses", "Partnerships and LLPs"))
                    .put("Property", Lists.newArrayList("Development and Construction", "Landlord and Tenant", "Real Estate Finance and Investment", "Stamp Duty Land Tax"))
                    .put("Reorganisations and distributions", Lists.newArrayList("Dividends and Distributions", "Reorganisations, Schemes and Demergers", "Returns of value"))
                    .put("Restructuring and insolvency", Lists.newArrayList("Restructuring and Insolvency"))
                    .put("Stamp duty and SDRT", Lists.newArrayList("Stamp Duty and SDRT"))
                    .put("VAT", Lists.newArrayList("VAT"))
                    .put("General", Lists.newArrayList("Taxes", "General Contract and Boilerplate", "Legal Concepts and Miscellaneous"))
                    .build();

}
