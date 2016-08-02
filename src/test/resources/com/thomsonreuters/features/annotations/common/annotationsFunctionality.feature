Feature: Annotations functionality

  Scenario: Verify Annotations links on Document delivery tool bar and title on mouse over
    When PL+ user is logged in
    When user navigates directly to document with guid "I33f1066be8cd11e398db8b09b4f043e0"
    Then the user is able to see new annotations link is present
    And verify new annotations link is clickable
    When the user moves the mouse over on add annotations link
    Then New annotation tooltip should be displayed

  @AnnotationsSmokeTests @e2e
  Scenario: Verify Annotations feature and modify annotations
    When PL+ user is logged in
    And the user removes all annotations for the document with GUID "I33f1066be8cd11e398db8b09b4f043e0"
    And user navigates directly to document with guid "I33f1066be8cd11e398db8b09b4f043e0"
    And user click on new Annotations link
    Then annotations textbox will be displayed with tinymce editor
    When the user can insert the text and save it
    Then verify saved annotations text will be displayed with metadata
    When user clicks the saved annotation
    Then annotations text box will be displayed with existing text
    When user modifies the text
    And saving the annotation
    Then modified annotations text will be displayed with metadata

  @e2e
  Scenario: Delete Annotations, undo Deleted Annotations and close link on Delete Annotations
    When PL+ user is logged in
    And the user removes all annotations for the document with GUID "I33f1066be8cd11e398db8b09b4f043e0"
    And user navigates directly to document with guid "I33f1066be8cd11e398db8b09b4f043e0"
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

  Scenario: 803697 Verify ClientId is not displayed as part of Annotations metadata
    When PL+ user is logged in
    And the user removes all annotations for the document with GUID "Ib5551f79e83211e398db8b09b4f043e0"
    When user navigates directly to document with guid "Ib5551f79e83211e398db8b09b4f043e0"
    And user added new annotation
    Then verify saved annotations text will be displayed with metadata
    And the Client ID next to the timestamp will not be displayed

  Scenario: User should be able to see notes added icon in history and folders
    Given PL+ user is logged in with following details
      | newSession | TRUE             |
    When user navigates directly to document with guid "Ib5551f79e83211e398db8b09b4f043e0"
    Then adds current document to "root" folder
    When the user clicks on 'History' link on the header
    And the user clicks on 'Documents' tab on the History page
    Then the "1" document will be displayed along with nodes added link
    When the user clicks on 'Folders' link on the header
    Then the "1" document will be displayed along with nodes added link

  Scenario: User should be able to see notes added icon in search results list
    When PL+ user is logged in
    When the user runs a free text search for the query "Children"
    And the user opens '1' link in the search result and store its title and guid
    Then user added new annotation
    When the user runs a free text search for the query "Children"
    Then the search result "1" document will be displayed along with nodes added link

  Scenario: User should be able to see the disabled save button when annotation text exceeds 3000 chars length and to save annotation text equals 3000 chars length
    When PL+ user is logged in
    And user navigates directly to document with guid "I1559faa0eef211e28578f7ccc38dcbee"
    And user enters annotation text with "3001" chars length
    Then user should be able to see the warning message for exceeded text
    When select ok button on warning message
    And user removes the excess input from the annotations text
    Then saving the annotation
 
  @should
  Scenario: User should be able to see the disabled save button when annotation text is below 3000 chars length and tags length is > 10000 and to save annotation richtext equals 10000 chars length
    When PL+ user is logged in
    And the user removes all annotations for the document with GUID "I1559faa0eef211e28578f7ccc38dcbee"
    And user navigates directly to document with guid "I1559faa0eef211e28578f7ccc38dcbee"
    And user enters annotation with richText
    Then user should be able to see the warning message for exceeded richText
    When select ok button on warning message
    And user removes the excess input from the annotations richText
    Then saving the annotation

  Scenario: User should be able to see the disabled save button when annotation textbox is empty
    When PL+ user is logged in
    And the user removes all annotations for the document with GUID "I1559faa0eef211e28578f7ccc38dcbee"
    And user navigates directly to document with guid "I1559faa0eef211e28578f7ccc38dcbee"
    And user enters annotation text with "empty" chars length
    Then user verifies Save button is disabled
    When user enters annotation text with "100" chars length
    Then user verifies Save button is enabled
    And clearing existing styles and annotation text
    Then user verifies Save button is disabled

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
