@gold
Feature: [850457] As a PLAU User I want to see legal updates documents and facets in search results
  So that I can explore updates during searches.

  Scenario: 
    Given ANZ user is logged in
    Given the user runs a free text search for the query "unfair"
    Then the user is able to verify that a page of search results is displayed
    And the know how facet "Legal updates" is displayed under "Resource Type"
