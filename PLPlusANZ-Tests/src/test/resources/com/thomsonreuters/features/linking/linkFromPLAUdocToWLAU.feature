Feature: [826375] Dependency on content: Linking from one PLAU document to WLAU content

#User PLANZFolderUser20 doesn't have permission to access to WLAU content
@archived
Scenario Outline: [826375] Linking from PLAU document to a WestLaw Australia document - user not logged into WLAU-<id>
	Given ANZ user is logged in
   	And ANZ user navigates directly to document with guid "<guid>"
    When the user clicks on hardcoded "<linktext>" link
    Then the source document with guid "<guid>" remains open and new tab opens
    And the user is taken to the login page in WLAU
    Examples: 
      | guid                              	|linktext | id |
      |Ifbf42042995811e598dc8b09b4f043e0	|545      | 1  |
   
#User ANZtestuser1 has permission to access to WLAU content
@gold @archived
Scenario Outline: [826375] Linking from PLAU document to a WestLaw Australia document - user logged into WLAU-<id>
	Given PL+ ANZ user navigates to home page
	And ANZ user is logged in with following details
      | userName         | ANZtestuser1          |
      | role             | SUPER_REMEMBER_ME_USER|
   	And ANZ user navigates directly to document with guid "<guid>"
    When the user clicks on hardcoded "<linktext>" link
    Then the source document with guid "<guid>" remains open and new tab opens
    And the user see opened document in WLAU
    Examples: 
      | guid                               	|linktext | id |
      |Ifbf42042995811e598dc8b09b4f043e0	|545      | 1  |