@open_web
Feature: [812218]  Password Authentication: Login to Practical Law Australia

  Background: 
    Given ANZ user is not logged in
    When the user navigates to the main PLANZ page
    And the user clicks on Sign On link on the header

  Scenario: [812218] As a PPI User I want to be able to login by using the saved username
    When a PPI user enter its username and password
      | userName | AUtestuser14 |
    And user selects Save my Username checkbox
    And clicks on Sign in
    Then user logs out
    And user tries to log in again
    Then he will see the username box in the log in page populated with his username

  Scenario: [812218] As a PPI User I want to be able to login using the previously saved login credentials
    When a PPI user enter its username and password
      | userName | AUtestuser14 |
    And user selects Save my Username and Password checkbox
    And clicks on Sign in
    Then user logs out
    And user tries to log in again
    Then he will see the username box in the log in page populated with his username
    And his password will be populated in the password box
    And his password will not be readable in the screen and only asterisks will be visible

  @smoke @gold @sanity
  Scenario: [812218] As a User I want to be able to have a dedicated page to login
    Then the user will be redirected to a login page
    And in this login page he will be able to see username and password boxes
    And he will be able to see a link to Forget my username and password
    And he will be able to select a save username option
    And he will be able to select a save username and password option
    And he will be able to select a Remember me on this computer option to enable the Super Remember me cookie
    And he will be able to click a link to see some help/information about the use of the Super Remember me cookie
    And he will be able to see a link to Create a new OnePass Profile
    And he will be able to see a link to Update an existing OnePass Profile
    And he will be able to see a link to Learn more about OnePass

  Scenario: Ability to see error message for short login name
    And the user enters their username "test1" and password "Password1"
    When the user selects sign in
    Then the user is presented with the following "validation" error message "The username is too short or too long."

  Scenario: Ability to see error message for incorrect password
    And the user enters their username "Login_Negative" and password "password1"
    When the user selects sign in
    Then the user is presented with the following "OnePass" error message "We don't recognise that username and/or password. Both are case sensitive."

  Scenario: Ability to see error message for incorrect username
    And the user enters their username "Login_negative" and password "Password1"
    When the user selects sign in
    Then the user is presented with the following "OnePass" error message "We don't recognise that username and/or password. Both are case sensitive."
