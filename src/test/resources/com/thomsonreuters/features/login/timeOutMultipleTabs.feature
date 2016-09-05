Feature: [812863] Time Out: Multiple Tabs 
As a PLAU user
I want to view an open web view of my tabbed search results and documents when my session times out
So that I do not have to login and perform another search or find the document I was viewing

  Scenario Outline: Users who have logged in and NOT selected the Super Remember Me Option
    Given ANZ user is logged in with following details
      | mandatoryRouting | YES  					|
      | routing  | SPECIFIED_USER_TIMEOUT_3_MINUTES |
    When the user runs a free text search for the query "<query>"
    And the user opens new tab and switch on it
    And ANZ user navigates directly to document with guid "Ifb5c26cb995811e598dc8b09b4f043e0" 
    And the user saves the page title for second tab
    And he has a session open and timed out after "100" sec
    Then the user is presented with a warning message that session is expired
    And he should stay on same document page as OpenWeb user on second tab
    And he should stay on same search page as OpenWeb user on first tab
    When the user refreshes the first and the second pages
    Then he should stay on same document page as OpenWeb user on second tab
    And he should stay on same search page as OpenWeb user on first tab
    And user closes second page
    Examples: 
      | query   | 
   	  | tax 	| 
  
  @RemoveSRMOptionANZ
  Scenario Outline: Users who have logged in and selected the Super Remember Me Option
    Given ANZ user is logged in with following details
      | mandatoryRouting | YES  					|
      | routing  | SPECIFIED_USER_TIMEOUT_3_MINUTES                  |
      | role     | SUPER_REMEMBER_ME_USER |
    When the user runs a free text cobalt search with query "<query>"
    And the user opens new tab and switch on it
    And ANZ user navigates directly to document with guid "Ifb5c26cb995811e598dc8b09b4f043e0" 
    And the user saves the page title for second tab
    And he has a session open and timed out after "200" sec
    Then user gets redirected to the document page on second tab that he was visiting and is logged in
    And user gets redirected to the search page on first tab that he was visiting and is logged in
    When the user refreshes the first and the second pages
    Then user gets redirected to the document page on second tab that he was visiting and is logged in
    And user gets redirected to the search page on first tab that he was visiting and is logged in
	And user closes second page  

    Examples: 
      | query   | 
      | tax 	|
