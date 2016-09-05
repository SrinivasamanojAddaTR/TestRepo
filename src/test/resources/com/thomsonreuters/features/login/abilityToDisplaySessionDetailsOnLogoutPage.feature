Feature: [812304] Display Session Details on Logout Page

#bugs #819042 #819102
#Also incorrect timezone is displayed on Demo pc1 site; according to Nathan McDonald it should be ignored as environment issue

	@bug
	Scenario: [812304] Display Session Details on Logout Page
		Given ANZ user is logged in
		When ANZ user navigates directly to document with guid "Ifb5c2817995811e598dc8b09b4f043e0"
		And the user runs a free text search for the query "test"
		And user clicks on Sign out
		Then he is redirected to a log out confirmation page 
		And user should see session summary on this page
		And the printed session details will include the start and end date and time of the user's session in timezone "Australia/Sydney"
        And the following information will be listed for transaction: Event Type is "Search", Description contains "test", Date/Time is in timezone "Australia/Sydney" and Client ID is "PRACTICAL LAW AU"
        And the following information will be listed for transaction: Event Type is "Document View", Description contains "Financial assistance", Date/Time is in timezone "Australia/Sydney" and Client ID is "PRACTICAL LAW AU"

#for australia: Australia/Sydney
#for uk: Europe/London

