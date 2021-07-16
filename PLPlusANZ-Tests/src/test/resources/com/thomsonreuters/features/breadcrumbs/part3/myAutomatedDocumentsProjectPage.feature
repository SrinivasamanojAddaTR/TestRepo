Feature: To view Contract Express Projects Page

  Background:
    Given ANZ user is logged in with following details
      | userName | ANZtestuser1 |
    When the user selects the user profile symbol
    And the user selects My Automated Document option
    And the user clicks on "Projects" link

  @gold @qed
  Scenario: To verify New Folder, New Project and Search bar are displayed
    Given user is taken to "My Projects" Page
    Then user verifies the following CE links are displayed
      | New Folder  |
      | New Project |
    And User verifies the Filter Search