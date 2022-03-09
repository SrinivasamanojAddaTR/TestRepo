@ffh
Feature: [821898] As a PLAU user I want to filter my foldered documents So that I can quickly find the types of documents I am interested in viewing


#Search checks are absent, because there are no Cases, Legislation, Journals and Current Awareness documents type on PL+ now.

  Scenario:
    Given ANZ user is logged in
    When API cleans all folders and history
    And user relogs in
   	#Know-how
    When the user opens 'Resources' link
    And the user opens 'Standard documents and drafting notes' link
    And the user runs a free text search for the query "tax"
    And the user waits search result to load
    And the user opens '2' link in the search result and store its details
    And the user adds current document to "root" folder

    #Know-how
    And the user come back on to Home page
    When the user opens 'Resources' link
    When the user opens 'Legal updates' link
    And the user runs a free text search for the query "law"
    And the user waits search result to load
    And the user opens '1' link in the search result and store its details
    And the user adds current document to "root" folder

    #Check on Folders page
    And the user clicks on 'Folders' link on the header
    And the user clicks Select Multiple Filters
    And the user selects 'Document' Type
    And the user selects 'Know-how' Content type
    And the user clicks Apply Filters
    Then the following documents content type present only
      | Know-how |
    
    #Check on History page
    When the user clicks on 'History' link on the header
    And the user clicks on 'Documents' tab on the History page
    And the user clicks Select Multiple Filters
    And the user selects 'PRACTICAL LAW AU' Client ID
    And the user selects 'Know-how' Content type
    And the user clicks Apply Filters
    Then the following documents content type present only
      | Know-how |

