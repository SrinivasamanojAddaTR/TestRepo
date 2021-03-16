@gold
Feature: [821528] As a PLAU User I want to view the folder option on my document view So that I can quickly tell if I can folder a document or not
  [823569]As a PLAU User I want to view the folder option on my search resulrs view  So that I can folder the one or many documents in my search results

  
  Scenario Outline: 
    Given ANZ user is logged in
    Given ANZ user navigates directly to document with guid "<guid>"
    Then folders widget on document will be displayed after print option and download option
    And user clicks on Add To Folder icon on document delivery
    Then user should see Add To Folder pop up
    Examples: 
      | guid                                                      |
      | I085e949f360511e598dc8b09b4f043e0 |
      | Ia391521db49311e598dc8b09b4f043e0 |


  Scenario: 
    Given ANZ user is logged in
    When the user runs a free text search for the query "tax"
    And the user waits search result to load
    And the user selects the checkbox associated with result "1"
    And user clicks on Add To Folder icon on search delivery
    Then user should see Add To Folder pop up
