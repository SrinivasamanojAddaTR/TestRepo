Feature: To view Contract Express Home Page

  @gold @qed
  Scenario: Verify Contract Express Home page
    Given ANZ user is logged in with following details
      | userName | ANZtestuser1 |
    When the user selects the user profile symbol
    And the user selects My Automated Document option
    Then the User verifies the "My Projects" title
    And user verifies the following CE links are displayed
      | Back to Practical Law |
      | Projects              |
      | Address Book          |
    And the help icon should be displayed
    And the user dropdown should be displayed
@qed
  Scenario: Verify Contract Express Home page Footer

    Given ANZ user is logged in with following details
      | userName | ANZtestuser1 |
    When the user selects the user profile symbol
    And the user selects My Automated Document option
    When the user clicks the link "User Notice and Privacy Statement" in footer
    Then the user is taken to "https://au-practicallaw-test.contractexpress.com/IdServ/core/TermsOfService" resource


  @gold @qed
  Scenario: Verify clicking on Project link should traverse to My Projects Page
    Given ANZ user is logged in with following details
      | userName | ANZtestuser1 |
    When the user selects the user profile symbol
    And the user selects My Automated Document option
    And the user clicks on "Projects" link
    Then user is taken to "My Projects" Page


  @gold @qed
  Scenario: Verify clicking on Address book  should traverse to Address book page
    Given ANZ user is logged in with following details
      | userName | ANZtestuser1 |
    When the user selects the user profile symbol
    And the user selects My Automated Document option
    And the user clicks on "Projects" link
    Then user is taken to "My Projects" Page
    And the user clicks on "Address Book" link
    And user waits until Address Page is displayed
    Then user is taken to "Address Book" Page