Feature:[825683] Edit an annotation at the top of the document

  @AnnotationsSmokeTests @e2e  @deletionAnnotations
  Scenario: Verify that user can modify annotations at the top
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    When user navigates directly to document with guid "I7e704ac6b3ec11e598dc8b09b4f043e0" on PL AU website
    And user click on new Annotations link
    Then annotations textbox will be displayed with tinymce editor
    And the user can insert the text and save it
    Then verify saved annotations text will be displayed with metadata
    And user clicks the saved annotation
    Then annotations text box will be displayed with existing text
    And user modifies the text
    And saving the annotation
    Then modified annotations text will be displayed with metadata

  @deletionAnnotations
  Scenario: Verify that annotation at the top will not be updated if user clicks on cancel button
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    When user navigates directly to document with guid "I7e704ac6b3ec11e598dc8b09b4f043e0" on PL AU website
    And user click on new Annotations link
    Then annotations textbox will be displayed with tinymce editor
    And the user can insert the text and save it
    Then verify saved annotations text will be displayed with metadata
    And user clicks the saved annotation
    Then annotations text box will be displayed with existing text
    And user modifies the text
    And user clicks on cancel button
    Then verify saved annotations text will be displayed with metadata