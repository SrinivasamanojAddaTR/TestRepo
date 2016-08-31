Feature: [812636]  Super Remember Me Option

  Background: 
    Given ANZ user is not logged in
    When the user navigates to the main PLANZ page
    
    
  #do not remove this tag! this is to delete SRM option after test
  @RemoveSRMOptionANZ 
  Scenario: [752718]
  	Given a username/password user in the login screen
      | userName | ANZtestuser1          |
      | routing  | NONE                   |
      | role     | SUPER_REMEMBER_ME_USER |
    When he selects the option to be remembered on this computer
    Then he activates the super remember me cookie
    And when the user logs out
    And he clicks the sign in button on the log out page
    Then he will be automatically authenticated
    And he will not see the Log in page

  #do not remove this tag! this is to delete SRM option after test
  @RemoveSRMOptionANZ  
  Scenario: [752718]
    Given a username/password user in the login screen
      | userName | ANZtestuser1             |
      | routing  | NONE                   |
      | role     | SUPER_REMEMBER_ME_USER |
    When he selects the option to be remembered on this computer
    Then he activates the super remember me cookie
    And when the user logs out
    And he tries to access ANZ PL+
    Then he will be automatically authenticated
    And he will not see the Log in page

  #do not remove this tag! this is to delete SRM option after test
  @RemoveSRMOptionANZ
  Scenario: [812636]
    Given a username/password user in the login screen
      | userName | ANZtestuser2           |
      | routing  | NONE                   |
      | role     | SUPER_REMEMBER_ME_USER |
    When he selects the option to be remembered on this computer
    Then he activates the super remember me cookie
    Then this logged in user clicks on Sign off
    And there will be two options Resume as user [X] Or [Sign in with a different account]
    Then the user selects Resume as user [X]
    Then he will be automatically authenticated
    And he will not see the Log in page
