
Feature: [820475] Search within Glossary 
	As a PLAU User
	I want to search for glossary documents
	So that I can find and link to the glossary document I am interested in using
	
Background: 
	Given ANZ user is logged in
	And user navigates to a glossary page 

#intermittent issue:
#848623 Bug [PLAU only] Glossary: Error when opening glossary article (Stream not readable)
Scenario Outline: Verification of the search text box 
	Then the user should be able to see the Search text box on the right hand side of the page 
	And the user saves the list of terms containing "<term>"
	And the user saves the list of terms containing "<term>a"
	And the search text "Find in list" should be pre-populated in the textbox 
	When searches for the term "<term>" using the glossary search 
	Then the user should be able to see a list of resulting glossary terms containing this search "<term>" highlighted 
	And the total matches for the term "<term>" should be displayed  
	And the first result item's definition should be displayed in the right hand pane 
	And clicking on listed result 2 displays the corresponding definition on the right hand page 
	And no alphabets are selected while the search is active 
	And the result list for the term "<term>" displayed should be sorted alphabetically
	When searches for the term "a" using the glossary search 
	Then the user should be able to see a list of resulting glossary terms containing this search "<term>a" highlighted 
	And the total matches for the term "<term>a" should be displayed  
	Examples:
	|term	|
	|dis	|
		
	
Scenario: Verify the closing of search box functionality
    When the user has selected the letter "D" from the tabbed alphabet list
    And searches for the term "disability" using the glossary search
    Then the search icon should be changed to a "cross" icon
    And clicking on this "cross" icon should reset the page back to the default state
    And the search query is removed from the search input field

Scenario: Verify zero search results 
	When searches for the term "bug" using the glossary search 
	Then the total should be displayed as "0 matches" 
	And the user should be able to view the definition of the term "Cut throat" on the page
	
	