Feature: Annotation functionallity on the delivery widget

  Scenario: Verify Annotations links on Document delivery tool bar and title on mouse over
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser3 |
    When user navigates directly to document with guid "I7e704ac6b3ec11e598dc8b09b4f043e0" on PL AU website
    Then the user is able to see new annotations link is present
    And verify new annotations link is clickable
    When the user moves the mouse over on add annotations link
    Then New annotation tooltip should be displayed


  @e2e @deletionAnnotations
  Scenario: Verify show/hide Annotations links behaviour on the delivery panel
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser3 |
    When user navigates directly to document with guid "I7e704ac6b3ec11e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user added new annotation
    And user looks through the body of the document and select text with colour "blueBox"
    And user added inline annotation after annotation at the top
    Then the user is able to see show/hide annotations link is present
    And the user clicks on Show And Hide link
    Then Displayed annotations are hidden
    And the user clicks on Show And Hide link
    Then Hidden annotations are displayed
    Then the user verifies the annotations count under link
    Then Annotations count should match with annotations present on document.
