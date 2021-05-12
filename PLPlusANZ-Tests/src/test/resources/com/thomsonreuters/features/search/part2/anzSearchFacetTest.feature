@gold
Feature: [814276] As a PLAU user, I want to narrow my search results
  So that I can display resource types, practice areas, topics and jurisdictions that are relevant to my work needs

  Background:
    Given ANZ user is logged in
    When the user runs a free text search for the query "law"


  Scenario: [814280] User validates the facet groups, i.e resource type/practice area and jurisdiction, are present on results page
    Then the user verifies the presence of following know how facet groups for PLAU
      | Resource Type |
      | Practice Area |
      | Jurisdiction  |
    And all the facet counts are less or equal to the total number of search results


  Scenario: User verifies the selection of single facet and its count
    And the user selects single facet selection mode
    And user expands the "Resource Type" facets group
    And the user selects the know how following parent facets with single selection
      | Practice notes |
      #Then the user verifies the topic facet "Practice notes" count is equivalent to no. of results displayed
    Then the user verifies that the know how following facet is selected and their count is equal to total count
      | Practice notes |


  Scenario: Verify the selection of same group facets and apply filter button
    And the user selects multiple facet selection mode
    And user expands the "Resource Type" facets group
    And the user selects the know how following parent facets
      | Practice notes |
      | Checklists     |
      | Glossary       |
    And the user selects the know how option to apply filters
    And the user verifies that the know how following facet is selected and their count is equal to total count
      | Practice notes |
      | Checklists     |
      | Glossary       |
    When the user clicks on clear all link
    And user expands the "Resource Type" facets group
    Then the user verifies that the following parent facets are not selected
      | Practice notes |
      | Checklists     |
      | Glossary       |

  Scenario: Verify the selection of different groups with multiple filter button
    And the user selects multiple facet selection mode
    And user expands the "Resource Type" facets group
    And the user selects the know how following parent facets
      | Practice notes |
    And user expands the "Practice Area" facets group
    And the user selects the know how following parent facets
      | Employment     |
    And the user selects the know how option to apply filters
    And the user clicks on clear all link
    And user expands the "Resource Type" facets group
    Then the user verifies that the following parent facets are not selected
      | Practice notes |
    And user expands the "Practice Area" facets group
    And the user verifies that the following parent facets are not selected
      | Employment     |


  Scenario: Verify All Australian Jurisdiction and results are displayed without error after facet selection
    And user expands the "Jurisdiction" facets group
    When the user selects the know how following parent facets
      | Federal  |
#      | Australian Capital Territory|
#      | New South Wales             |
#      | Northern Territory          |
#      | Queensland                  |
#      | South Australia             |
#      | Tasmania                    |
      | Victoria |
#      | Western Australia           |
    And the user selects the know how option to apply filters
    Then the user is able to verify that a page of search results is displayed
    And there is no error message on search results page
   
   