Feature: [822650] FFH URLs

Background:
   Given ANZ user is logged in with following details
      | userName         | ANZtestuser2 |
      
Scenario Outline: Favourites URL
	When the user opens "<url>" url on PL AU website
	Then the user is presented with a page with header "<name>"
    And the page URL contains "<url>"
  Examples:
   | url          					| name   	 | 
   | /FavoritesOrganizer/Home.html	| Favourites |
   
   
   

Scenario Outline: History URL
	When the user opens "<url>" url on PL AU website
	Then the user should be seeing Date Picker dropdown
    And the page URL contains "<url>"
  Examples:
   | url          					| 
   | /ResearchOrganizer/Home.html   | 