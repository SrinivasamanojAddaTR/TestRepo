Feature: [850132] open web user views legal updates page
As a open web user
I can browse to legal updates
So that I can find the latest updates in the industry 


	Background:
    Given PL+ ANZ user navigates to home page

  Scenario Outline: [850132] Open web user verifies the Legal updates by Practice Area going through Legal Update Resource page-<id>
    When the user select Legal Updates link on Resource tab area
    Then list of practice areas will be displayed
      | Employment |
    When the user opens "<practiceArea>" link
    Then the user should be presented with a list of LU documents
    And he does not see in the search results page any link related to delivery options (email, download, print)
    And the results should be from the relevant PA "<practiceArea>"
    Examples: 
      | practiceArea | id |
      | Employment   | 1  |

  Scenario Outline: [850132] Open web user verifies the Legal updates by Practice Area going through PA page-<id>
    When the user clicks on the tab "Practice Areas"
    And the user opens "<practiceArea>" link
	Then the user is presented with a page with header "<practiceArea>"
    When the user select Legal Updates link on Resource tab area
    Then the user should be presented with a list of LU documents
    And the results should be from the relevant PA "<practiceArea>"
    Examples: 
      | practiceArea | id |
      | Employment   | 1  |

   Scenario: [850132] As a open web User I want to view all existing legal updates on PL content
    When the user select Legal Updates link on Resource tab area
	When the user clicks on the 'View all' link of the "Legal updates" widget
    Then the user should be presented with a list of LU documents
    And the user is presented with a page with header "Legal updates | All"
    And the user verifies the presence of the legul updates facet groups for PLAU
    And user expands the "Resource Type" facets group
    Then the user verifies that the following parent facets are not selected
       | Legal updates|
    And user expands the "Practice Area" facets group
    Then the user verifies that the following parent facets are not selected
       | Banking and Finance                 |
       | Commercial                          |
       | Commercial Real Estate              |
       | Company Law                         |
       | Competition and Consumer Protection |
       | Corporate Transactions              |
       | Dispute Resolution                  |
       | Employment                          |
       | In-house Centre                     |
       | Insolvency and Restructuring        |
       | New Zealand Resource Centre         |
    And user expands the "Jurisdiction" facets group
    Then the user verifies that the following parent facets are not selected
       | Federal                             |
       | Australian Capital Territory        |
       | New South Wales                     |
       | Northern Territory                  |
       | Queensland                          |
       | South Australia                     |
       | Tasmania                            |
       | USA (National/Federal)              |
       | Victoria                            |
       | Western Australia                   |
    And he does not see in the search results page any link related to delivery options (email, download, print)
    And the user verifies that link 'Email Preferences' is  not displayed on 'Legal updates page'

    
   
