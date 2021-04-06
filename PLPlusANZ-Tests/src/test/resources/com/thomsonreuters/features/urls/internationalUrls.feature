Feature: [822645] International Page URLs

  Background: 
   Given ANZ user is logged in

	#bug 834766 International - 'Indonesia Recently Published' page title does not contain colon

  @archived
  Scenario Outline: International page URL
    When the user opens "<url>" url on PL AU website
    Then the user is presented with a page with header "<header>"
    And the page URL contains "<url>"

    Examples: 
      | url                                                                                            | header                                                                      | id |
      ######################################################################################################
      ###########Countries - 4 items selected + 1 with bug
     # | /Browse/Home/International/IndonesiaRecentlyPublished                                          | Indonesia Recently published                                               |
    #  | /Browse/Home/International/Argentina                                                           | Argentina                                                                   |
    #  | /Browse/Home/International/ArgentinaRecentlypublished                                          | Argentina: Recently published                                               |
    #  | /Browse/Home/International/Australia                                                           | Australia                                                                   |
    #  | /Browse/Home/International/AustraliaRecentlypublished                                          | Australia: Recently published                                               |
#      | /Browse/Home/International/Austria                                                             | Austria                                                                     |
#      | /Browse/Home/International/AustriaRecentlypublished                                            | Austria: Recently published                                                 |
#      | /Browse/Home/International/Bahrain                                                             | Bahrain                                                                     |
#      | /Browse/Home/International/Belgium                                                             | Belgium                                                                     |
#      | /Browse/Home/International/Bermuda                                                             | Bermuda                                                                     |
#      | /Browse/Home/International/Brazil                                                              | Brazil                                                                      |
#      | /Browse/Home/International/BrazilRecentlypublished                                             | Brazil: Recently published                                                  |
#      | /Browse/Home/International/BritishVirginIslands                                                | British Virgin Islands                                                      |
#      | /Browse/Home/International/Bulgaria                                                            | Bulgaria                                                                    |
#      | /Browse/Home/International/Canada                                                              | Canada                                                                      |
#      | /Browse/Home/International/CanadaRecentlypublished                                             | Canada: Recently published                                                  |
#      | /Browse/Home/International/CaymanIslands                                                       | Cayman Islands                                                              |
#      | /Browse/Home/International/ChannelIslandsGuernsey                                              | Channel Islands: Guernsey                                                    |
#      | /Browse/Home/International/ChannelIslandsJersey                                                | Channel Islands: Jersey                                                      |
#      | /Browse/Home/International/Chile                                                               | Chile                                                                       |
#      | /Browse/Home/International/China                                                               | China                                                                       |
#      | /Browse/Home/International/Colombia                                                            | Colombia                                                                    |
#      | /Browse/Home/International/Croatia                                                             | Croatia                                                                     |
#      | /Browse/Home/International/Cyprus                                                              | Cyprus                                                                      |
#      | /Browse/Home/International/CzechRepublic                                                       | Czech Republic                                                              |
#      | /Browse/Home/International/Denmark                                                             | Denmark                                                                     |
#      | /Browse/Home/International/Egypt                                                               | Egypt                                                                       |
#      | /Browse/Home/International/Estonia                                                             | Estonia                                                                     |
#      | /Browse/Home/Practice/EULaw                                                               	   | EU Law                                                                       |
#      | /Browse/Home/International/Finland                                                             | Finland                                                                     |
#      | /Browse/Home/International/France                                                              | France                                                                      |
#      | /Browse/Home/International/FranceRecentlypublished                                             | France: Recently published                                                  |
#      | /Browse/Home/International/Germany                                                             | Germany                                                                     |
#      | /Browse/Home/International/GermanyRecentlypublished                                            | Germany: Recently published                                                 |
#      | /Browse/Home/International/Gibraltar                                                           | Gibraltar                                                                   |
#      | /Browse/Home/International/Greece                                                              | Greece                                                                      |
#      | /Browse/Home/International/HongKong                                                        	   | Hong Kong                                                               |
#      | /Browse/Home/International/HongKongRecentlypublished                                           | Hong Kong: Recently published                                           |
#      | /Browse/Home/International/Hungary                                                             | Hungary                                                                     |
#      | /Browse/Home/International/Iceland                                                             | Iceland                                                                     |
#      | /Browse/Home/International/India                                                               | India                                                                       |
#      | /Browse/Home/International/IndiaRecentlypublished                                              | India: Recently published                                                   |
#      | /Browse/Home/International/Indonesia                                                           | Indonesia                                                                   |
# 	   | /Browse/Home/International/Ireland                                                             | Ireland                                                                     |
#      | /Browse/Home/International/IsleofMan                                                           | Isle of Man                                                                 |
#      | /Browse/Home/International/Israel                                                              | Israel                                                                      |
#      | /Browse/Home/International/Italy                                                               | Italy                                                                       |
#      | /Browse/Home/International/ItalyRecentlypublished                                              | Italy: Recently published                                                   |
#      | /Browse/Home/International/Japan                                                               | Japan                                                                       |
#      | /Browse/Home/International/JapanRecentlypublished                                              | Japan: Recently published                                                   |
#      | /Browse/Home/International/Kazakhstan                                                          | Kazakhstan                                                                  |
#      | /Browse/Home/International/Luxembourg                                                          | Luxembourg                                                                  |
#      | /Browse/Home/International/Malaysia                                                            | Malaysia                                                                    |
#      | /Browse/Home/International/Mexico                                                              | Mexico                                                                      |
#      | /Browse/Home/International/MexicoRecentlypublished                                             | Mexico: Recently published                                                  |
#      | /Browse/Home/International/NewZealand                                                          | New Zealand                                                                  |
#      | /Browse/Home/International/Nigeria                                                             | Nigeria                                                                     |
#      | /Browse/Home/International/Norway                                                              | Norway                                                                      |
#      | /Browse/Home/International/NorwayRecentlypublished                                             | Norway: Recently published                                                  |
#      | /Browse/Home/International/Oman                                                                | Oman                                                                        |
#      | /Browse/Home/International/Peru                                                                | Peru                                                                        |
#      | /Browse/Home/International/Poland                                                              | Poland                                                                      |
#      | /Browse/Home/International/Portugal                                                            | Portugal                                                                    |
#      | /Browse/Home/International/Qatar                                                               | Qatar                                                                       |
#      | /Browse/Home/International/Romania                                                             | Romania                                                                     |
#      | /Browse/Home/International/RussianFederation                                                   | Russian Federation                                                          |
#      | /Browse/Home/International/RussianFederationRecentlypublished                                  | Russian Federation: Recently published                                      |
#      | /Browse/Home/International/SaudiArabia                                                         | Saudi Arabia                                                                |
#      | /Browse/Home/International/Serbia                                                              | Serbia                                                                      |
#      | /Browse/Home/International/Singapore                                                           | Singapore                                                                   |
#      | /Browse/Home/International/SingaporeRecentlypublished                                          | Singapore: Recently published                                               |
#      | /Browse/Home/International/SouthAfrica                                                         | South Africa                                                                |
#      | /Browse/Home/International/SouthAfricaRecentlypublished                                        | South Africa: Recently published                                            |
#      | /Browse/Home/International/SouthKorea                                                          | South Korea                                                                 |
#      | /Browse/Home/International/SouthKoreaRecentlypublished                                         | South Korea: Recently published                                             |
#      | /Browse/Home/International/Spain                                                               | Spain                                                                       |
#      | /Browse/Home/International/SpainRecentlypublished                                              | Spain: Recently published                                                   |
#      | /Browse/Home/International/Sweden                                                              | Sweden                                                                      |
#      | /Browse/Home/International/SwedenRecentlypublished                                             | Sweden: Recently published                                                  |
#      | /Browse/Home/International/Switzerland                                                         | Switzerland                                                                 |
#      | /Browse/Home/International/SwitzerlandRecentlypublished                                        | Switzerland: Recently published                                             |
#      | /Browse/Home/International/Taiwan                                                              | Taiwan                                                                      |
#      | /Browse/Home/International/Thailand                                                            | Thailand                                                                    |
#      | /Browse/Home/International/TheNetherlands                                                      | The Netherlands                                                             |
#      | /Browse/Home/International/Turkey                                                              | Turkey                                                                      |
#      | /Browse/Home/International/TurkeyRecentlypublished                                             | Turkey: Recently published                                                  |
#      | /Browse/Home/International/Ukraine                                                             | Ukraine                                                                     |
#      | /Browse/Home/International/UnitedArabEmirates                                                  | United Arab Emirates                                                        |
#      | /Browse/Home/International/UnitedKingdom                                                       | United Kingdom                                                              |
#      | /Browse/Home/International/UnitedStates                                                        | United States                                                               |
#      | /Browse/Home/International/Venezuela                                                           | Venezuela                                                                   |
#      | /Browse/Home/International/Vietnam                                                             | Vietnam                                                                     |
      #################################################################
      ##### Global Guides - 4 items selected
    #  | /Browse/Home/International/ShareholdersRightsinPrivateandPublicCompaniesGlobalGuide            | Shareholders' Rights in Private and Public Companies Global Guide           |
    #  | /Browse/Home/International/AUOutsourcingGlobalGuide                                              | Outsourcing Global Guide                                                    |
    #  | /Browse/Home/International/Globalguides                                                        | Global Guide                                                                |
    #  | /Browse/Home/International/AgriculturalLawGlobalGuide                                          | Agricultural Law Global Guide                                               |
#      | /Browse/Home/International/ArbitrationGlobalGuide                                              | Arbitration Global Guide                                                    |
#      | /Browse/Home/International/CapitalMarketsGlobalGuide                                           | Capital Markets Global Guide                                                |
#      | /Browse/Home/International/ConstructionandProjectsGlobalGuide                                  | Construction and Projects Global Guide                                      |
#      | /Browse/Home/International/CorporateCrimeFraudandInvestigationsGlobalGuide                     | Financial and Business Crime Global Guide                       			  |
#      | /Browse/Home/International/CorporateGovernanceandDirectorsDutiesGlobalGuide                    | Corporate Governance and Directors' Duties Global Guide                     |
#      | /Browse/Home/International/CorporateRealEstateGlobalGuide                                      | Corporate Real Estate Global Guide                                          |
#      | /Browse/Home/International/DataProtectionGlobalGuide                                           | Data Protection Global Guide                                                |
#      | /Browse/Home/International/DisputeResolutionGlobalGuide                                        | Dispute Resolution Global Guide                                             |
#      | /Browse/Home/International/DoingbusinessinGlobalGuide                                          | Doing business in... Global Guide                                           |
#      | /Browse/Home/International/EmployeeSharePlansGlobalGuide                                       | Employee Share Plans Global Guide                                           |
#      | /Browse/Home/International/EmploymentandEmployeeBenefitsGlobalGuide                            | Employment and Employee Benefits Global Guide                               |
#      | /Browse/Home/International/EnergyandNaturalResourcesGlobalGuide                                | Energy and Natural Resources Global Guide                                   |
#      | /Browse/Home/International/EnvironmentGlobalGuide                                              | Environment Global Guide                                                    |
#      | /Browse/Home/International/EstablishingaBusinessinGlobalGuide                                  | Establishing a Business in... Global Guide                                  |
#      | /Browse/Home/International/FamilyLawGlobalGuide                                                | Family Law Global Guide                                                     |
#      | /Browse/Home/International/FinanceGlobalGuide                                                  | Finance Global Guide                                                        |
#      | /Browse/Home/International/InsuranceandReinsuranceGlobalGuide                                  | Insurance and Reinsurance Global Guide                                      |
#      | /Browse/Home/International/InternationalInsolvencyGroupInsolvencyandDirectorsDutiesGlobalGuide | International Insolvency: Group Insolvency and Directors' Duties Global Guide |
#      | /Browse/Home/International/InvestinginGlobalGuide                                              | Investing in... Global Guide                                                   |
#      | /Browse/Home/International/InvestmentFundsGlobalGuide                                          | Investment Funds Global Guide                                               |
#      | /Browse/Home/International/IPinBusinessTransactionsGlobalGuide                                 | IP in Business Transactions Global Guide                                     |
#      | /Browse/Home/International/LifeSciencesGlobalGuide                                             | Life Sciences Global Guide                                                  |
#      | /Browse/Home/International/PensionsGlobalGuide                                                 | Pensions Global Guide                                                       |
#      | /Browse/Home/International/PrivateClientGlobalGuide                                            | Private Client Global Guide                                                 |
#      | /Browse/Home/International/PrivateEquityandVentureCapitalGlobalGuide                           | Private Equity and Venture Capital Global Guide                             |
#      | /Browse/Home/International/PrivateMergersandAcquisitionsGlobalGuide                            | Private Mergers and Acquisitions Global Guide                               |
#      | /Browse/Home/International/PublicMergersandAcquisitionsGlobalGuide                             | Public Mergers and Acquisitions Global Guide                                |
#      | /Browse/Home/International/PublicProcurementGlobalGuide                                        | Public Procurement Global Guide                                             |
#      | /Browse/Home/International/RestructuringandInsolvencyGlobalGuide                               | Restructuring and Insolvency Global Guide                                   |
#      | /Browse/Home/International/StructuredFinanceandSecuritisationGlobalGuide                       | Structured Finance and Securitisation Global Guide                          |
#      | /Browse/Home/International/TaxonTransactionsGlobalGuide                                        | Tax on Transactions Global Guide                                            |
#      | /Browse/Home/International/CartelLeniencyGlobalGuide                                           | Cartel Leniency Global Guide                                                |
#      | /Browse/Home/International/CompetitionGlobalGuideMergerControl                                 | Competition Global Guide: Merger Control                                     |
#      | /Browse/Home/International/CompetitionGlobalGuideRestraintsofTradeandDominance                 | Competition Global Guide: Restraints of Trade and Dominance                  |
      #################################################################
      #Transaction Guides - 1 item selected
    #  | /Browse/Home/International/PrivateCompanyAcquisitionsInternationalTransactionGuide             | Private Company Acquisitions International Transaction Guide                |
#      | /Browse/Home/International/JointVenturesInternationalTransactionGuide                          | Joint Ventures International Transaction Guide                              |
#      | /Browse/Home/International/SalesandMarketingInternationalTransactionGuide                      | Sales and Marketing International Transaction Guide                         |
#      | /Browse/Home/International/PrivateCompanyAcquisitionsInternationalTransactionGuide             | Private Company Acquisitions International Transaction Guide                |
#      | /Browse/Home/International/JointVenturesInternationalTransactionGuide                          | Joint Ventures International Transaction Guide                              |
#      | /Browse/Home/International/SalesandMarketingInternationalTransactionGuide                      | Sales and Marketing International Transaction Guide                         |
      #################################################################
      #Practice Areas
      | /Browse/Home/Practice/Arbitration                                                              | Arbitration                                                                 | 1  |
      | /Browse/Home/Practice/EULaw                                                                    | EU Law                                                                      | 2  |
      | /Browse/Home/Practice/Competition                                                              | Competition                                                                 | 3  |
        
     
    Scenario Outline: Country Q&A Comparison page URL-<id>
    When the user opens "<url>" url on PL AU website
    Then the user is presented with comparison tool page with header "<header>"
    And the page URL contains "<url>"

    Examples: 
      | url                                                     | header                       | id |
      | /QACompare/Builder/Country                              | Country Q&A Comparison Tool  | 1  |
         