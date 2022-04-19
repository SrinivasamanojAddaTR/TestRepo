Feature: Contact Express

  @gold
  Scenario: Verify My Automated Documents is present in Silhoutte Menu
    Given ANZ user is logged in with following details
      | userName | ANZtestuser1 |
    When the user selects the user profile symbol
    Then my Automated Documents option should be displayed

  @gold
  Scenario: Verify clicking on My Automated Documents navigates to Contract Express page
    Given ANZ user is logged in with following details
      | userName | ANZtestuser1 |
    When the user selects the user profile symbol
    And the user selects My Automated Document option
    Then user is taken to "My Projects" Page