Feature: [848112][848110] view legal updates page
  As a PLAU User I want to view legal updates on selected practice area and all existing legal updates on PL content


  Background:
    Given ANZ user is logged in


  Scenario Outline: [848112] User verifies the Legal updates by Practice Area going through Legal Update Resource page
    When the user select Legal Updates link on Resource tab area
    Then list of practice areas will be displayed
      | Employment |
    When the user opens "<practiceArea>" link
    Then the user should be presented with a list of LU documents
    And the results should be from the relevant PA "<practiceArea>"
    Examples:
      | practiceArea |
      | Employment   |


  Scenario Outline: [848112] User verifies the Legal updates by Practice Area going through PA page
    When the user clicks on the tab "Practice areas"
    And the user opens "<practiceArea>" link
    Then the user is presented with a page with header "<practiceArea>"
    When the user select Legal Updates link on Resource tab area
    Then the user should be presented with a list of LU documents
    And the results should be from the relevant PA "<practiceArea>"
    Examples:
      | practiceArea |
      | Employment   |


  Scenario: [848110] As a PLAU User I want to view all existing legal updates on PL content
    When the user select Legal Updates link on Resource tab area
    And the user is presented with the Legal Updates widget
    When the user clicks on the 'View all' link of the "Legal updates" widget
    Then the user should be presented with a list of LU documents
    And the user is presented with a page with header "Legal updates | All"
    And the user verifies the presence of the legul updates facet groups for PLAU
    And the user verifies that facets not selected
    When the user clicks link 'Email Preferences' on 'Legal updates page' page
    Then the user is presented with a page with header "Email preferences"
    
   
