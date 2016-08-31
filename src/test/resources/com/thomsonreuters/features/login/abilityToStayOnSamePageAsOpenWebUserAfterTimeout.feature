Feature: [812856] As a PLAU user I want to see an open web view of my document when my session times out

  Scenario: [812856] Modify timeout re-direct to skip Open web authentication if a user is not Open web (username/password users).
    Given ANZ user is logged in with routing details
      | mandatoryRouting | YES                              |
      | routing          | SPECIFIED_USER_TIMEOUT_3_MINUTES |
      | userName         | ANZtestuser1                     |
    When he is viewing a free ANZ document "/Document/Ifb5c26cb995811e598dc8b09b4f043e0/View/FullText.html"
    And he has a session open and timed out
    Then he should stay on same document page as OpenWeb user
    And the user clicks Log in button
    When a PPI user enter its username and password
      | userName | ANZtestuser1 |
    And clicks on Sign in
    Then user gets redirected to the document page that he was visiting and is logged in

  @RemoveSRMOptionANZ
  Scenario: [812856] View a single document and timeout when Super Remember Me enabled
    Given ANZ user is not logged in
    When the user navigates to the main PLANZ page
    Given a username/password user in the login screen
      | userName | ANZtestuser1           |
      | routing  | NONE                   |
      | role     | SUPER_REMEMBER_ME_USER |
    When he selects the option to be remembered on this computer
    Then he activates the super remember me cookie
    And when the user logs out
    Given ANZ user is applying routing without login
      | mandatoryRouting | YES                              |
      | routing          | SPECIFIED_USER_TIMEOUT_3_MINUTES |
      | userName         | ANZtestuser1                     |
    When he is viewing a free ANZ document "/Document/Ifb5c26cb995811e598dc8b09b4f043e0/View/FullText.html"
    And he has a session open and timed out
    Then user gets redirected to the document page that he was visiting and is logged in
    And the full text document will be displayed including "Reasons for an acquisition"
	And 'Related Content' link is displayed on the right hand panel 
	When the user clicks on "Related Content" link
    Then the user should see the related content section displayed
    	
  @manual
  Scenario: [812856]Uses (with two or more reg keys) sets the super remember me option and their session times out
    Given ANZ user is not logged in
    When the user navigates to the main PLANZ page
    Given a username/password user in the login screen
      | userName | ANZuser3           |
      | routing  | NONE                   |
      | role     | SUPER_REMEMBER_ME_USER |
    When he selects the option to be remembered on this computer
    Then he activates the super remember me cookie
    And when the user logs out
    Given ANZ user is applying routing without login
      | mandatoryRouting | YES                              |
      | routing          | SPECIFIED_USER_TIMEOUT_3_MINUTES |
      | userName         | ANZuser3                    |
    When he is viewing a free ANZ document "/Document/Ifb5c26cb995811e598dc8b09b4f043e0/View/FullText.html"
    And he has a session open and timed out
    When the PLAU refreshes the page
    Then the PLAU user will be presented with the reg key dialog
    And their list of reg keys will be displayed
    When the PLAU user selects a reg key
    Then the user will be logged into PLAU
    Then user gets redirected to the document page that he was visiting and is logged in
