Feature: [809734] I want the company logo to display correctly within the header
  So that the logo of the product can be correctly identified by the viewer

  Scenario: [809734]I want the company log to display and function correctly.
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2 |
    Then user should see company logo
    And user clicks on company logo
    And user should see the "Practical Law" page is opened
    Then user should see company logo
