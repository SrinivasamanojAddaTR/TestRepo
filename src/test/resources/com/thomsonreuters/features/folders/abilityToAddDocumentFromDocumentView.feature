Feature: [821529][821558] Add, remove documents to folder
[821529] As a PLAU UserI want to be able to add a document to a folder So that I can  
[821558] As a PLAU User I want to remove a document from my folder So that I can ensure my folder only contains documents I am interested in working with

#TO FIX: date utils to use timezones

  Background: 
     Given ANZ user is logged in with following details
      | userName         | ANZtestuser2 |

  Scenario:
    When API cleans all folders and history
    And user relogs in

   Scenario Outline: Add document to folder from document view
	And the user runs a free text search for the query "<query>"
    And the user opens '1' link in the search result and store its title and guid
    And the user adds current document to new "<folder>" folder with parent folder "<parentFolder>"
    When the user clicks on 'Folders' link on the header
    Then document present in the "<folder>" folder
    And the document Content type is correct
    And the document Resource type is correct
    And the document date is correct
    When the user deletes the document from "<folder>" folder
    Then document does not present in the "<folder>" folder
    Then document present in the "Trash" folder
    And the document Content type in Trash is correct
    And the document date in Trash is correct
    Examples: 
      | query    	| folder | parentFolder |
      | test      	| test10 | root         |
      | tax      	| test11 | root         |
      | board      	| test12 | root         |

