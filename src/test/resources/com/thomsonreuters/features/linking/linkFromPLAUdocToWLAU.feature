Feature: [826375] Dependency on content: Linking from one PLAU document to WLAU content

#User ANZtestuser2 doesn't have permission to access to WLAU content
Scenario Outline: [826375] Linking from PLAU document to a WestLaw Australia document - user not logged into WLAU
	Given ANZ user is logged in with following details
      | userName         | ANZtestuser2 |
   	And ANZ user navigates directly to document with guid "<guid>"
    When the user clicks on hardcoded "<linktext>" link
    Then the source document with guid "<guid>" remains open and new tab opens
    And the user is taken to the login page in WLAU
    Examples: 
      | guid                              	|linktext|
      |Ifbf42042995811e598dc8b09b4f043e0	|545|  
   
#User ANZtestuser1 has permission to access to WLAU content   
Scenario Outline: [826375] Linking from PLAU document to a WestLaw Australia document - user logged into WLAU
	Given PL+ ANZ user navigates to home page
	And ANZ user is logged in with following details
      | userName         | ANZtestuser1 |
   	And ANZ user navigates directly to document with guid "<guid>"
    When the user clicks on hardcoded "<linktext>" link
    Then the source document with guid "<guid>" remains open and new tab opens
    And the user see opened document in WLAU
    Examples: 
      | guid                               	|linktext|
      |Ifbf42042995811e598dc8b09b4f043e0	|545|     