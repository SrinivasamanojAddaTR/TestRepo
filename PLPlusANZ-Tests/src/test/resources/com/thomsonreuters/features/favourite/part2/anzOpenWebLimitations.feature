@ffh
Feature: [850126] Open Web user cannot use favourites or my start page

#bug 867500 RULES TBC: Bug [PLAU & PLUK] Security Issue: Open web user can access and modify FFH pages by bookmarked links
#bug 867643 RULES TBC: Bug [PLAU & PLUK] Open web user can see error page by link to email preferences

Scenario: [850126] Open Web user cannot add pages to favourites or set as start page
	Given ANZ user is not logged in
	When the user opens "/Browse/Home/Practice/CorporateTransactions" url on PL AU website
    Then he does not see any favourites icon/link
    And he does not see any Start Page icon/link
    

Scenario Outline: [850126] Open web user cannot access FFH and email config pages-<id>
	Given user deletes all cookies
	When the user opens "<link>" url on PL AU website
	Then the user will be redirected to a login page
	Examples:
	| link 							| id |
	| /FavoritesOrganizer/Home.html | 1  |
	| /ResearchOrganizer/Home.html	| 2  |
	| /Browse/home/emailpreferences	| 3  |
	