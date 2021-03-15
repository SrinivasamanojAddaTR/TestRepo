@archived
Feature: [809784] As a PLAU User I want add my client ID So that I can track how much time I spent on a specific client

  Scenario: [809784] As a PLAU User I want add my client ID So that I can track how much time I spent on a specific client
    Given ANZ user is logged in
    And he is not prompted to enter its Client ID
    And user is able to see default client id "PRACTICAL LAW AU" in the header
    When user clicks on client id "PRACTICAL LAW AU"
    Then user is able to change client id "TESTACCOUNTID"
    And user can see new client id "TESTACCOUNTID" in the header
    And user clicks on Sign out


  Scenario: [809784] As a PLAU User I want add my client ID So that I can track how much time I spent on a specific client
    Given ANZ user is logged in
    And user is able to see default client id "PRACTICAL LAW AU" in the header
    And user is able to change client id
      | QWERTY123 |
      | QWERTY12  |
      | QWERTY1   |
    And user clicks on Sign out
    And ANZ user is logged in with following details
      | userName | AUtestuser14 |
    And user is able to see default client id "PRACTICAL LAW AU" in the header
    And user clicks on client id "PRACTICAL LAW AU"
    And user see a list of recent client ids
      | QWERTY123 |
      | QWERTY12  |
      | QWERTY1   |
    And the user selects a recent client ID "QWERTY12"
    Then selected Client ID "QWERTY12" will be the new Client ID for the session
    And user clicks on Sign out
