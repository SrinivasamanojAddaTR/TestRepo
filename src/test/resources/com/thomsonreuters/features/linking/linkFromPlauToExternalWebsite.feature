Feature: [836861] Remove / Hide the Pause Session from PLAU (when linking to other Websites) 

Background:
	Given ANZ user is logged in with following details
      | userName         | ANZtestuser2 	|

  Scenario Outline: [836861]Linking from one PLAU document to an number
    Given has selected the link entitled International
    When the user has selected the international subscriptions link for the country "<country>"
    And the page URL contains "<url>"
    Examples: 
      | country  	| 	url						| 
      | Canada 		|	http://ca.practicallaw.com/	|
      | China 		|	http://global.practicallaw.com/country/china	|
      | US			|	http://us.practicallaw.com/	|


      
