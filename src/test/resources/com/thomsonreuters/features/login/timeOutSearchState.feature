Feature: [812852] Time Out: Search State 
As a PLAU user
I want to my search results and facets to be retained when I login after my session times out
So that I do not have to perform a new search

#bug 868963 Bug [PLAU & PLUK] Search Timeout: child facets state on search page is not retained after timeout

	@bug
  Scenario Outline: Users who have logged in and NOT selected the Super Remember Me Option
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2						|
      | mandatoryRouting | YES  					|
      | routing  | SPECIFIED_USER_TIMEOUT_3_MINUTES |
    When the user runs a free text cobalt search with query "<query>"
    And the user clicks 'select multiple filters' button
    And the user expands the know how facet "<facet1>"
    And the user selects the know how child facet "<facet11>"    
    And the user selects the know how option to apply filters
    And he has a session open and timed out after "100" sec
    Then the user is presented with a warning message that session is expired
    And he should stay on same search page as OpenWeb user
  	And the user verifies that the know how facet is selected "<facet11>"
    When the user refreshes the page
    Then he should stay on same search page as OpenWeb user
 	When the user deselects the know how facet "<facet11>"
    And the user clicks 'select multiple filters' button
    And the user expands the know how facet "<facet2>"
    And the user selects the know how child facet "<facet22>"    
    And the user selects the know how option to apply filters
    And the user saves the page title
    And the user clicks Log in button
    And a PPI user enter its username and password
      | userName | ANZtestuser2						|
    And clicks on Sign in
    Then user gets redirected to the search page that he was visiting and is logged in
    And the user verifies that the know how facet is selected "<facet22>"
    Examples: 
      | query   | facet1    	|facet11							| facet2       		| facet22 					| 
   	  | tax 	| Company Law 	|Company administration and meetings| Practice Notes	| Practice note: overview 	| 
  
  @RemoveSRMOptionANZ
  Scenario Outline: Users who have logged in and NOT selected the Super Remember Me Option
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2						|
      | routing  | SPECIFIED_USER_TIMEOUT_3_MINUTES                  |
      | role     | SUPER_REMEMBER_ME_USER |
      | mandatoryRouting | YES  					|
    When the user runs a free text cobalt search with query "<query>"
    And the user clicks 'select multiple filters' button
    And the user expands the know how facet "<facet1>"
    And the user selects the know how child facet "<facet2>"
    And the user selects the know how option to apply filters
    And he has a session open and timed out after "200" sec
    Then user gets redirected to the search page that he was visiting and is logged in
    And the user verifies that the know how facet is selected "<facet2>"

    Examples: 
      | query   | facet1    	| facet2  								| 
      | tax 	| Company Law 	| Company administration and meetings 	| 
