Feature: [822650] FFH URLs

Background:
   Given ANZ user is logged in
      
Scenario Outline: Favourites URL-<id>
	When the user opens "<url>" url on PL AU website
	Then the user is presented with a page with header "<name>"
    And the page URL contains "<url>"
  Examples:
   | url          					| name   	 | id |
   | /FavoritesOrganizer/Home.html	| Favourites | 1  |
   
   
   

Scenario Outline: History URL-<id>
	When the user opens "<url>" url on PL AU website
	Then the user should be seeing Date Picker dropdown
    And the page URL contains "<url>"
  Examples:
   | url          					| id |
   | /ResearchOrganizer/Home.html   | 1  |