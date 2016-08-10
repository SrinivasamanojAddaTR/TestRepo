Feature: [821888] As a PLAU User I want to view the Trash can feature So that I can permanently delete my foldered documents

  Scenario Outline: 
    Given ANZ user is logged in with following details
      | userName         | ANZtestuser2 |
    When API cleans all folders and history and user relogs in
    When the user opens 'Resources' link
    And the user opens 'Standard documents and drafting notes' link
    And the user runs a free text search for the query "<query>"
    And the user waits search result to load
    And the user opens '1' link in the search result and store its title and guid
    And the user adds current document to new "<folder>" folder with parent folder "<parentFolder>"
    When the user clicks on 'Folders' link on the header
    When the user deletes the document from "<folder>" folder
    Then document does not present in the "<folder>" folder
    Then document present in the "Trash" folder
    And the document date in Trash is correct
    When the user selects the document and moves back to original folder "<folder>"
    Then the document should be removed from Trash and be moved to folder "<folder>"
    Examples: 
      | query | folder | parentFolder |
      | Tax   | test22 | root         |
