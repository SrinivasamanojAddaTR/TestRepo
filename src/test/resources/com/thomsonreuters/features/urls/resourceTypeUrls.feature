Feature: [822642] ResourceTypePage URLs

  Background: 
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2 |

	
  Scenario Outline: Resource Type page URL
    When the user opens "<url>" url on PL AU website
    Then the user is presented with a page with header "<header>"
    And the page URL contains "<url>"

    Examples: 
      | url                                                                                  | header                                                 |
      | /Browse/Home/resources/international-transaction-guides                              | International Transaction Guides                       |
      | /Browse/Home/Resources/Practicenotes                                                 | Practice Notes                                         |
      | /Browse/Home/Resources/Checklists                                                    | Checklists                                             |
      | /Browse/Home/Resources/Standarddocumentsanddraftingnotes                             | Standard Documents                                     |
      | /Browse/Home/Resources/Standardclausesanddraftingnotes                               | Standard Clauses                                       |
      | /Browse/Home/Resources/LegalUpdates                                                  | Legal Updates                                          |
      ##################################################################################
      ####### Global guides - 2 items selected to test
      | /Browse/Home/Resources/Globalguides                                                  | Global Guides                                          |
      | /Browse/Home/Resources/Globalguides/ArbitrationGlobalGuide                           | Arbitration Global Guide                               |
#      | /Browse/Home/Resources/Globalguides/CapitalMarketsGlobalGuide                        | Capital Markets Global Guide                           |
#      | /Browse/Home/Resources/Globalguides/CompetitionGlobalGuideMergerControl              | Competition Global Guide: Merger Control                |
#      | /Browse/Home/Resources/Globalguides/ConstructionandProjectsGlobalGuide               | Construction and Projects Global Guide                 |
#      | /Browse/Home/Resources/Globalguides/CorporateCrimeFraudandInvestigationsGlobalGuide  | Financial and Business Crime Global Guide   |
#      | /Browse/Home/Resources/Globalguides/CorporateGovernanceandDirectorsDutiesGlobalGuide | Corporate Governance and Directors' Duties Global Guide |
#      | /Browse/Home/Resources/Globalguides/CorporateRealEstateGlobalGuide                   | Corporate Real Estate Global Guide                     |
#      | /Browse/Home/Resources/Globalguides/DataProtectionGlobalGuide                        | Data Protection Global Guide                           |
#      | /Browse/Home/Resources/Globalguides/DisputeResolutionGlobalGuide                     | Dispute Resolution Global Guide                        |
#      | /Browse/Home/Resources/Globalguides/DoingbusinessinGlobalGuide                       | Doing business in... Global Guide                         |
#      | /Browse/Home/Resources/Globalguides/EmployeeSharePlansGlobalGuide                    | Employee Share Plans Global Guide                      |
#      | /Browse/Home/Resources/Globalguides/EmploymentandEmployeeBenefitsGlobalGuide         | Employment and Employee Benefits Global Guide          |
#      | /Browse/Home/Resources/Globalguides/EnergyandNaturalResourcesGlobalGuide             | Energy and Natural Resources Global Guide              |
#      | /Browse/Home/Resources/Globalguides/EnvironmentGlobalGuide                           | Environment Global Guide                               |
#      | /Browse/Home/Resources/Globalguides/FinanceGlobalGuide                               | Finance Global Guide                                   |
#      | /Browse/Home/Resources/Globalguides/InsuranceandReinsuranceGlobalGuide               | Insurance and Reinsurance Global Guide                 |
#      | /Browse/Home/Resources/Globalguides/InvestmentFundsGlobalGuide                       | Investment Funds Global Guide                          |
#      | /Browse/Home/Resources/Globalguides/IPinBusinessTransactionsGlobalGuide              | IP in Business Transactions Global Guide               |
#      | /Browse/Home/Resources/Globalguides/LifeSciencesGlobalGuide                          | Life Sciences Global Guide                             |
#      | /Browse/Home/Resources/Globalguides/PrivateClientGlobalGuide                         | Private Client Global Guide                            |
#      | /Browse/Home/Resources/Globalguides/PrivateEquityandVentureCapitalGlobalGuide        | Private Equity and Venture Capital Global Guide        |
#      | /Browse/Home/Resources/Globalguides/RestructuringandInsolvencyGlobalGuide            | Restructuring and Insolvency Global Guide              |
#      | /Browse/Home/Resources/Globalguides/StructuredFinanceandSecuritisationGlobalGuide    | Structured Finance and Securitisation Global Guide     |
#      | /Browse/Home/Resources/Globalguides/TaxonTransactionsGlobalGuide                     | Tax on Transactions Global Guide                       |
      
   Scenario: Glossary URL
	 When the user opens "/Glossary/AUPracticalLaw" url on PL AU website
   	 Then the user is able to see the tabbed alphabetical list    
