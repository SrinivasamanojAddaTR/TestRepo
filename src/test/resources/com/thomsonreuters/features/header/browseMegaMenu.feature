Feature: [809745] Browse Mega Menu 


Scenario: [809745] As a PL AU login/password user I want to use browse mega menu 
	Given ANZ user is logged in
	When user clicks on "Browse Menu" dropdown
    Then  user should see the Browse Menu popup with Practice Area and Resources and International sub-menu
    And the practice areas option will be the default selected option
    And  user clicks on following sub-menu and see the respective links according to the design
      | Practice areas |
      | Resources      |
     # | International  |
		
	