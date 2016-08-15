Feature:[845624] Edit an inline annotation

  @AnnotationsSmokeTests @e2e  @deletionAnnotations
  Scenario: Verify that user can modify inline annotations
    Given ANZ user is logged in with following details
      | userName | topsecret1234 |
    When user navigates directly to document with guid "If9293e5fdb5911e598dc8b09b4f043e0" on PL AU website
    And user looks through the body of the document and select text with colour "blueBox"
    And user added new inline annotation
    Then verify saved annotations text will be displayed with metadata
    And user clicks the saved annotation
    Then annotations text box will be displayed with existing text
    And user modifies the text
    And saving the annotation
    Then modified annotations text will be displayed with metadata

  @deletionAnnotations
  Scenario: Verify that inline annotation will not be updated if user clicks on cancel button
    Given ANZ user is logged in with following details
      | userName | topsecret1234 |
    When user navigates directly to document with guid "If9293e5fdb5911e598dc8b09b4f043e0" on PL AU website
    And user looks through the body of the document and select text with colour "blueBox"
    And user added new inline annotation
    Then verify saved annotations text will be displayed with metadata
    And user clicks the saved annotation
    Then annotations text box will be displayed with existing text
    And user modifies the text
    And user clicks on cancel button
    Then verify saved annotations text will be displayed with metadata