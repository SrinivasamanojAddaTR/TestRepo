@ffh
Feature: [821891] As a PLAU user I want to search for a document in my folders So that I can find a document I want to view


#bug 852192 Bug [PLAU Only] Search in History and Folders does not return any results
  Scenario Outline: Search within folders-<id>
    Given ANZ user is logged in
    Then API cleans all folders and history and user relogs in
    And the user runs a free text search for the query "<searchTerm>"
    And the user waits search result to load
    And the user selects '4' documents, stores its titles and guids and saves to "root" folder
    When the user clicks on 'Folders' link on the header
    And the user searches the term "<searchTerm>" in folders
    Then for search result the matching search term is highlighted "<highlightedTerm>" in folders search results

    Examples:
      | searchTerm   | highlightedTerm | id |
      | tax          | tax             | 1  |
      | tax deed     | tax, deed       | 2  |
      | tax OR deed  | tax, deed       | 3  |
      | tax AND deed | tax, deed       | 4  |
