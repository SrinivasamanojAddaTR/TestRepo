
Feature: [821891] As a PLAU user I want to search for a document in my folders So that I can find a document I want to view

Background:
    Given ANZ user is logged in

#bug 852192 Bug [PLAU Only] Search in History and Folders does not return any results

  Scenario: 
    Then API cleans all folders and history and user relogs in
    And the user runs a free text search for the query "tax"
    And the user waits search result to load
    And the user selects '4' documents, stores its titles and guids and saves to "root" folder

  Scenario Outline: 
    When the user clicks on 'Folders' link on the header
    And the user searches the term "<searchTerm>" in folders
    Then for search result the matching search term is highlighted "<highlightedTerm>" in folders search results

    Examples: 
      | searchTerm   | highlightedTerm |
      | tax          | tax             |
      | tax deed     | tax, deed       |
      | tax OR deed  | tax, deed       |
      | tax AND deed | tax, deed       |
