Feature: [821529] As a PLAU UserI want to be able to add a document to a folder So that I can
  [821558] As a PLAU User I want to remove a document from my folder So that I can ensure my folder only contains documents I am interested in working with

  Background: 
    Given ANZ user is logged in

  Scenario: 
    When API cleans all folders and history
    And user relogs in

  Scenario Outline: From Home Page
    And the user runs a free text search for the query "<query>"
    And the user selects '2' documents, stores its titles and guids and saves to new "<folder>" folder with parent folder "<parentFolder>"
    When the user clicks on 'Folders' link on the header
    Then all documents present in the "<folder>" folder
    When the user deletes all documents from "<folder>" folder
    Then the folder "<folder>" is empty
    Examples: 
      | query    | folder | parentFolder |
      | document | test20  | root         |


  Scenario Outline: Know how
    When the user opens 'Resources' link
    And the user opens 'Standard documents and drafting notes' link
    And the user opens 'Company Law' link
    And the user selects '2' documents, stores its titles and guids and saves to new "<folder>" folder with parent folder "<parentFolder>"
    When the user clicks on 'Folders' link on the header
    Then all documents present in the "<folder>" folder
    When the user deletes all documents from "<folder>" folder
    Then the folder "<folder>" is empty
    Examples: 
       | folder | parentFolder |
       | test21 | root         |

#Legal updates link is temporary absent. Lisa will add link and notify Kostya.
  Scenario Outline: Legal Updates
    When the user opens 'Resources' link
    When the user opens 'Legal updates' link
    And the user opens 'Employment' link
    And the user selects '1' documents, stores its titles and guids and saves to "<folder>" folder
    When the user clicks on 'Folders' link on the header
    Then all documents present in the "<folder>" folder
    When the user deletes all documents from "<folder>" folder
    Then the folder "<folder>" is empty
    Examples: 
      | folder |
      | root   |

  Scenario Outline: Topics page
    When user opens "Practice Areas" link
    And the user navigates to practice area "Company Law" filtered by "Company administration and meetings" topic page
    And the user selects '2' documents, stores its titles and guids and saves to "<folder>" folder
    When the user clicks on 'Folders' link on the header
    Then all documents present in the "<folder>" folder
    When the user deletes all documents from "<folder>" folder
    Then the folder "<folder>" is empty
    Examples:
      | folder |
      | root   |

    @manual
    Scenario: Add Description to document in folder
      When the user go to folders
      And the user selects any folder from LHS pane
      And the user hovers the mouse cursor over any document link
      Then edit pencil icon appears
      When the user clicks on edit pencil icon
      And the user enters random text till its maximum character
      And the user clicks on save button
      Then the text is not exceed the frame (There isn't scroll the horizontal scroll bar to RHS)
      Then The entered text can be edited (should be Edit link)

