Feature: [850067] Not logged in user can log in as username and password

  Scenario: [850067] As a not logged in user I want to use Sign On link on the header to login.
    Given ANZ user is not logged in
    When the user navigates to the main PLANZ page
    And the user clicks on Sign On link on the header
    Then the user is able to sign in with OnePass
     | userName | AUtestuser14 |
    And Sign On link is not shown to user
    And Sign In link is not shown to user

  Scenario: [850067] As a not logged in user I want to use Sign In link on the footer to login.
    Given ANZ user is not logged in
    When the user navigates to the main PLANZ page
    And the user clicks on Sign In link on the footer
    Then the user is able to sign in with OnePass
      | userName | AUtestuser14 |
    And Sign In link is not shown to user
    And Sign On link is not shown to user
