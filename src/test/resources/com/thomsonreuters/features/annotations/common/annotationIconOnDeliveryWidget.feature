Feature: Annotation functionallity on the delivery widget

  Scenario: Verify Annotations links on Document delivery tool bar and title on mouse over
    Given ANZ user is logged in with following details
      | userName | topsecret1234 |
    When user navigates directly to document with guid "I747eef9fa1eb11e598dc8b09b4f043e0" on PL AU website
    Then the user is able to see new annotations link is present
    And verify new annotations link is clickable
    When the user moves the mouse over on add annotations link
    Then New annotation tooltip should be displayed


  @e2e @deletionAnnotations
  Scenario: Verify show/hide Annotations links behaviour
    Given PL+ user is logged in
    When user navigates directly to document with guid "Ie6a01717518811e498db8b09b4f043e0"
    And user added new annotation
    Then the user is able to see show/hide annotations link is present
    Then verify show/hide annotations link is clickable
    And user selects show/hide to hide annotations
    And the user hovers the Show And Hide link
    Then verify show and hide tooltip appears
    And the user clicks on Show And Hide link
    Then Hidden annotations are displayed
    And the user clicks on Show And Hide link
    Then Displayed annotations are hidden
    And the user clicks on Show And Hide link
    Then the user verifies the annotations count under link
    Then Annotations count should match with annotations present on document.
    And user added new annotation
    Then the user verifies the annotations count under link
    Then Annotations count should match with annotations present on document.