Feature: [850132] open web user views legal updates page
As a open web user
I can browse to legal updates
So that I can find the latest updates in the industry 


	Background:
    Given PL+ ANZ user navigates to home page

  Scenario Outline: [850132] Open web user verifies the Legal updates by Practice Area going through Legal Update Resource page
    When the user select Legal Updates link on Resource tab area
    Then list of practice areas will be displayed
      | Employment |
    When the user opens "<practiceArea>" link
    Then the user should be presented with a list of LU documents
    And he does not see in the search results page any link related to delivery options (email, download, print)
    And the results should be from the relevant PA "<practiceArea>"
    Examples: 
      | practiceArea |
      | Employment   |

  Scenario Outline: [850132] Open web user verifies the Legal updates by Practice Area going through PA page
    When the user clicks on the tab "Practice areas"
    And the user opens "<practiceArea>" link
	Then the user is presented with a page with header "<practiceArea>"
    When the user select Legal Updates link on Resource tab area
    Then the user should be presented with a list of LU documents
    And the results should be from the relevant PA "<practiceArea>"
    Examples: 
      | practiceArea |
      | Employment   |
 
   Scenario: [850132] As a open web User I want to view all existing legal updates on PL content
    When the user select Legal Updates link on Resource tab area
	When the user clicks on the 'View all' link of the "Legal updates" widget
    Then the user should be presented with a list of LU documents
    And the user is presented with a page with header "Legal updates | All"
    And the user verifies the presence of the legul updates facet groups for PLAU
    And the user verifies that facets not selected
    And he does not see in the search results page any link related to delivery options (email, download, print)
    And the user verifies that link 'Email Preferences' is  not displayed on 'Legal updates page'

    
   
