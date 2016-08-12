Feature: Annotations functionality

  @AnnotationsSmokeTests @e2e  @deletionAnnotations
  Scenario: Verify Annotations feature and modify annotations
    Given ANZ user is logged in with following details
      | userName | topsecret1234 |
    And user navigates directly to document with guid "I747eef9fa1eb11e598dc8b09b4f043e0" on PL AU website
    And user click on new Annotations link
    Then annotations textbox will be displayed with tinymce editor
    When the user can insert the text and save it
    Then verify saved annotations text will be displayed with metadata
    When user clicks the saved annotation
    Then annotations text box will be displayed with existing text
    When user modifies the text
    And saving the annotation
    Then modified annotations text will be displayed with metadata

  @e2e  @deletionAnnotations
  Scenario: Delete Annotations, undo Deleted Annotations and close link on Delete Annotations
    Given ANZ user is logged in with following details
      | userName | topsecret1234 |
    And user navigates directly to document with guid "I747eef9fa1eb11e598dc8b09b4f043e0" on PL AU website
    And user added new annotation
    And user clicks the saved annotation
    Then annotations text box will be displayed with delete icon
    When user deletes the annotations
    Then "Note has been deleted" text will be displayed with undo and close links
    And user unable to find the deleted annotations
    When user clicks the undo link
    And user able to see the deleted annotations are displayed
    When user clicks the saved annotation
    Then user deletes the annotations
    When user clicks the close link
    Then annotations close and undo links will be disappeared






  @e2e
  Scenario: Verify show/hide Annotations links behaviour
    When PL+ user is logged in
    When user navigates directly to document with guid "Ie6a01717518811e498db8b09b4f043e0"
    And user added new annotation
    Then the user is able to see show/hide annotations link is present
    And verify show/hide annotations link is clickable
    And user selects show/hide to hide annotations
    When the user hovers the Show And Hide link
    Then verify show and hide tooltip appears
    When the user clicks on Show And Hide link
    And Hidden annotations are displayed
    When the user clicks on Show And Hide link
    Then Displayed annotations are hidden
    When the user clicks on Show And Hide link
    And the user verifies the annotations count under link
    Then Annotations count should match with annotations present on document.
    When user added new annotation
    And the user verifies the annotations count under link
    Then Annotations count should match with annotations present on document.

  Scenario: Delete All Annotations Present on Document
    When PL+ user is logged in
    Then user navigates directly to document with guid and removes annotations on it
      | I33f1066be8cd11e398db8b09b4f043e0 |
      | Ib5551f79e83211e398db8b09b4f043e0 |
      | Ie6a01717518811e498db8b09b4f043e0 |
      | I1559faa0eef211e28578f7ccc38dcbee |
    And user logs out

    @manual
    Scenario: Verifying the Text formatting in doc-level annotations text field
      Given PL+ user is logged in
      When user navigates directly to document with guid "I33f1066be8cd11e398db8b09b4f043e0"
      And user click on new Annotations link
      Then annotations textbox will be displayed with tinymce editor
      When user enters sample text in annotation text box
      And click on the textbox using left mouse button
      Then verify that entered text/formatting is not deleted from annotations text box

  @manual
  Scenario: Verifying the Text formatting in inline notes text field
    Given PL+ user is logged in
    When user navigates directly to document with guid "I33f1066be8cd11e398db8b09b4f043e0"
    And  highlight some text in the document
    And create an inline notes via choosing annotations color
    And user enters sample text in inline notes text box
    And click on the textbox using left mouse button
    Then verify that entered text/formatting is not deleted from inline notes text box
