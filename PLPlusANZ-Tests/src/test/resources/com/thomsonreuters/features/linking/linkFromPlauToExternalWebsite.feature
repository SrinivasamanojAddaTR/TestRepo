Feature: [836861] Remove / Hide the Pause Session from PLAU (when linking to other Websites) 

Background:
	Given ANZ user is logged in
@archived
Scenario Outline: [836861]Linking from one PLAU document to an number
    Given has selected the link entitled International
    When the user has selected the international subscriptions link for the country "<country>"
    And the page URL contains "<url>"
    Examples: 
      | country  	| 	url						| 
      | Canada 		|	https://ca.practicallaw.thomsonreuters.com	|
      | China 		|	https://uk.practicallaw.thomsonreuters.com	|
      | US			|	https://content.next.westlaw.com/	|


      
