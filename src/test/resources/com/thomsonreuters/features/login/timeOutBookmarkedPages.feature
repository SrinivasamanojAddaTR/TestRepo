Feature: [812864] Time out - bookmarked pages 
	As a PLAU user
	I want to bookmark PLAU pages, documents and search results
	So that I can quickly access pages, documents and search results at a later date

Scenario: [812864] Non-SRM user - bookmark to topic page 
	Given ANZ user is logged in with following details 
		| routing  | SPECIFIED_USER_TIMEOUT_3_MINUTES |
	    | mandatoryRouting | YES                              |	  
	And the user opens "/topic/w-000-5049" url on PL AU website 
	And the user saves the page url into bookmark 
	And the user goes to third-party site and stays there for "200" seconds 
	When the user selects a bookmark 
	Then he should stay on same category page as OpenWeb user 
	

Scenario: [812864] Non-SRM user - bookmark to document 
	Given ANZ user is logged in with following details 
		| routing  | SPECIFIED_USER_TIMEOUT_3_MINUTES |
	    | mandatoryRouting | YES                              |	  
	And ANZ user navigates directly to document with guid "Ifb5c26cb995811e598dc8b09b4f043e0" 
	And the user saves the page url into bookmark 
	And the user goes to third-party site and stays there for "200" seconds 
	When the user selects a bookmark 
	Then he should stay on same document page as OpenWeb user 
	
Scenario: [812864] Non-SRM user - bookmark to search result 
	Given ANZ user is logged in with following details 
		| routing  | SPECIFIED_USER_TIMEOUT_7_MINUTES |
	    | mandatoryRouting | YES                              |	  
	And the user runs a free text cobalt search with query "test" 
	And the user saves the page url into bookmark 
	And the user goes to third-party site and stays there for "300" seconds
	When the user selects a bookmark 
	Then he should stay on same search page as OpenWeb user 
	
@RemoveSRMOptionANZ
Scenario: [812864] SRM user - bookmark to topic page 
	Given ANZ user is logged in with following details 
		| role     | SUPER_REMEMBER_ME_USER |
		| routing  | SPECIFIED_USER_TIMEOUT_3_MINUTES |	  
	    | mandatoryRouting | YES                              |	  		
	And the user opens "/topic/w-000-5049" url on PL AU website 
	And the user saves the page url into bookmark 
	And the user goes to third-party site and stays there for "200" seconds 
	When the user selects a bookmark 
	Then user gets redirected to the category page that he was visiting and is logged in 
	
@RemoveSRMOptionANZ
Scenario: [812864] SRM user - bookmark to document 
	Given ANZ user is logged in with following details 
		| role     | SUPER_REMEMBER_ME_USER |
		| routing  | SPECIFIED_USER_TIMEOUT_3_MINUTES |	  
	    | mandatoryRouting | YES                              |	  
	And ANZ user navigates directly to document with guid "Ifb5c26cb995811e598dc8b09b4f043e0" 
	And the user saves the page url into bookmark 
	And the user goes to third-party site and stays there for "200" seconds 
	When the user selects a bookmark 
	Then user gets redirected to the document page that he was visiting and is logged in 
		
@RemoveSRMOptionANZ	
Scenario: [812864] SRM user - bookmark to search result 
	Given ANZ user is logged in with following details 
		| role     | SUPER_REMEMBER_ME_USER |
		| routing  | SPECIFIED_USER_TIMEOUT_7_MINUTES |
	    | mandatoryRouting | YES                              |	  
	And the user runs a free text cobalt search with query "test" 
	And the user saves the page url into bookmark 
	And the user goes to third-party site and stays there for "300" seconds
	When the user selects a bookmark 
	Then user gets redirected to the search page that he was visiting and is logged in 
	
	