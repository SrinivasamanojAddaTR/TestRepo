Feature: [814203] As a PLAU User, I want to view the home page So that I can view the practice areas, resources and international materials that are available in the PLAU web site 

Scenario: [814203] User verifies the home page and its relative tabs
	Given ANZ user is logged in
	Then user verifies the "Home" page
	And user verifies the following tabs are displayed
		|Practice areas|
		|Resources     |
		|International |
    When user opens "Practice areas" link
	And user verifies the following practice area links are displayed 
		|Employment            |
		|Company Law           |
		|Corporate Transactions|
	When user selects the following tab and see the relative links or content 
		|tab | content |
		| Resources | Practice notes, Standard documents and drafting notes, Standard clauses and drafting notes, Checklists, Glossary, Global guides|
		|International | Countries, International subscriptions, International practice areas,Country Q&A comparison tool |