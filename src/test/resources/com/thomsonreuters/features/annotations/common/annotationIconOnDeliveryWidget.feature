Feature: Annotation functionallity on the delivery widget

  Scenario: Verify Annotations links on Document delivery tool bar and title on mouse over
    Given ANZ user is logged in with following details
      | userName | topsecret1234 |
    When user navigates directly to document with guid "I747eef9fa1eb11e598dc8b09b4f043e0" on PL AU website
    Then the user is able to see new annotations link is present
    And verify new annotations link is clickable
    When the user moves the mouse over on add annotations link
    Then New annotation tooltip should be displayed