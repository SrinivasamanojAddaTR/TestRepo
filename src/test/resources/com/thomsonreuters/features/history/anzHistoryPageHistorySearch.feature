Feature: [821965]As a PLAU User, I want to be able to search within my history So that I can find searches and documents that I recently performed and viewed

Background:
     Given ANZ user is logged in with following details
        | userName         | ANZtestuser2 |


#bug: 830076 Java Bug [PLAU & PLUK TBC] History Search & Facet Filter : Combination of "Search within results" and Facets (like ClientID and Content Type) does not work all the time and results go in loading mode for indefinite time.
#bug 852192 Bug [PLAU Only] Search in History and Folders does not return any results

@bug @wip
  Scenario Outline: User verifies the search within history page
      When the user clicks on 'History' link on the header
      And the user runs the search "contract" if today's history is not present
      Then the user should see search text area on history page
      When the user selects the date All with -
      And the user searches the term "<term>" in history
      Then the user should see the each search result according to the term "<term>" in history
	Examples:
		| term |
		| contract |
		| contract employment |
		| contract AND employment |
		| contract OR employment |
