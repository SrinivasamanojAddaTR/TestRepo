Feature: [809918]Edit Preferences (User Preferences)

  Scenario: [809918] As a PLAU user I want to edit my profile (i.e. one pass profile) So that I change my password and security questions
    Given ANZ user is logged in
    When the user selects the user profile symbol
    Then the Edit Profile option will be displayed
    Then the user selects Edit profile option
    Then the user will be taken to the Manage One Pass page
    

    Scenario: [850126] Open Web user should not see user avatar link
    Given ANZ user is not logged in
    And PL+ ANZ user navigates to home page
    When the user navigates to the main PLANZ page
    Then user should not see user icon link
