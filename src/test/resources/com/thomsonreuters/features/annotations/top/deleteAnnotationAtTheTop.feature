Feature:[825685] Delete an annotation at the top of the document

  @e2e  @deletionAnnotations
  Scenario: Delete annotation at the top, undo Deleted Annotations and close link on Delete Annotations
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    When user navigates directly to document with guid "I7e704ac6b3ec11e598dc8b09b4f043e0" on PL AU website
    And user added new annotation
    And user clicks the saved annotation
    Then annotations text box will be displayed with delete icon
    And user deletes the annotations
    Then "Note has been deleted" text will be displayed with undo and close links
    Then user unable to find the deleted annotations
    And user clicks the undo link
    Then user able to see the deleted annotations are displayed
    And user clicks the saved annotation
    And user deletes the annotations
    And user clicks the close link
    Then annotations close and undo links will be disappeared