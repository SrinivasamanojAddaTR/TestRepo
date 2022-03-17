Feature:[845628] Delete an inline annotation

  @smoke @gold @e2e @deletionAnnotations
  Scenario: Delete inline annotation, undo Deleted Annotations and close link on Delete Annotations
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser2 |
    When user navigates directly to document with guid "I3646c6209aaf11e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user looks through the body of the document and select text with colour "blueBox"
    And user added new inline annotation
    And user clicks the saved annotation
    Then annotations text box will be displayed with delete icon
    And user deletes the annotations
    Then "Note has been deleted" text will be displayed with undo and close links
    Then user unable to find the deleted annotations
    And user clicks undo link and wait annotation icon
    And user refreshes page
    And the user clicks on the inline annotation icon
    Then user able to see the deleted annotations are displayed
    And user clicks the saved annotation
    And user deletes the annotations
    And user clicks the close link
    Then annotations close and undo links will be disappeared