Feature: [850111] As an open web user, I want to filter my search results So that I can narrow the search results to the area of the law I am interested in


  Background:
    Given PL+ ANZ user navigates to home page

  Scenario: [814280] User validates the facet groups, i.e resource type/practice area and jurisdiction, are present on results page
    When the user runs a free text search for the query "law"
    Then the user verifies the presence of following know how facet groups for PLAU
      |Resource Type|
      |Practice Area|
      |Jurisdiction |
    And all the facet counts are less or equal to the total number of search results


  Scenario Outline: User verifies the selection of single facet and its count
     When the user runs a free text search for the query "law"
     And the user selects the know how following parent facets with single selection
       | <Facet Name> |
      Then the user verifies that the know how following facet is selected and their count is equal to total count
       | <Facet Name> |
  Examples:
    | Facet Name     |
    | Practice Notes |
    | Company Law |

  Scenario: Verify the selection of same group facets and apply filter button
    When the user runs a free text search for the query "law"
    And the user selects the know how following parent facets
      | Practice Notes |
      | Checklists     |
      | Glossary       |
    And the user selects the know how option to apply filters
    And the user verifies that the know how following facet is selected and their count is equal to total count
      | Practice Notes |
      | Checklists     |
      | Glossary       |
    When the user clicks on clear all link
    Then the user verifies that the following parent facets are not selected
      | Practice Notes |
      | Checklists     |
      | Glossary       |


  Scenario: Verify the selection of different groups with multiple filter button
    When the user runs a free text search for the query "law"
    And the user selects the know how following parent facets
      | Practice Notes |
      | Employment     |
      | Federal        |
    When the user clicks on cancel link
    Then the user verifies that the following parent facets are not selected
      | Practice Notes |
      | Employment     |
      | Federal        |

   Scenario: Verify All Australian Jurisdiction and results are displayed without error after facet selection
     When the user runs a free text search for the query "law"
     When the user selects the know how following parent facets
      | Federal                     |
      | Victoria                    |
    And the user selects the know how option to apply filters
    Then the user is able to verify that a page of search results is displayed
    And there is no error message on search results page


  Scenario: Verify the Search and Selection on Topic Pages
    When the user selects "Practice Areas" tab
    And the user navigates to practice area "Employment" filtered by "Federal unfair dismissal" topic page
    And the user runs a free text search for the query "law"
    Then the user can verify that the scoped search dropdown states "Federal unfair dismissal"
    And the user verifies the presence of following know how facet groups for PLAU
      |Resource Type|
      |Practice Area|
      |Jurisdiction |


  Scenario: Verify valid Scoped Search for single and multiple term on International pages
    When user clicks on "Browse Menu" dropdown
    And user selects sub-menu "International" and clicks on the link "Austria"
    And the user runs a free text search for the query "tax"
    Then the user can verify that the scoped search dropdown states "Austria"
    And the user verifies the presence of following know how facet groups for PLAU
      |Resource Type|
      |Practice Area|
      |Jurisdiction |