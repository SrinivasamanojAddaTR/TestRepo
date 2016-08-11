Feature: [850131] View and Navigate Glossary Open Web 

#intermittent issue:
#848623 Bug [PLAU only] Glossary: Error when opening glossary article (Stream not readable)

Background: 
	Given ANZ user is not logged in
	And PL+ ANZ user navigates to home page 
	And user navigates to a glossary page 
	
Scenario: Verify Tabbed Alphabets functionality 
	When the user is able to see the tabbed alphabetical list 
	And the user clicks on the alphabet "B" in the tabbed alphabetical list 
	Then letter "B" should be selected on the alphabet tab 
	And the glossary list rolls up and the first term in the respective list is selected (except x, Y and Z) 
	And the corresponding definition of the selected term should be displayed on the right hand side 
	And the corresponding terms for "B" are displayed 
	And the term "Australian Human Rights Commission (AHRC)" is not visible in the list 
	
	
Scenario: Navigation to glossary terms on glossary definition page 
	When the user clicks on glossary term "Disability" 
	Then the title "Disability" is displayed in the definition 
	And the definition contains text "A total or partial loss" 
	And letter "D" should be selected on the alphabet tab 
	

Scenario: Navigation from one glossary page to another 
	When the user clicks on the alphabet "R" in the tabbed alphabetical list 
	And the user clicks on glossary term "Reasonable adjustments" 
	And clicks on the glossary term link "inherent requirements of the position" in the definition page 
	Then the title "Inherent requirements of the position" is displayed in the definition 
	And letter "I" should be selected on the alphabet tab 
	
	
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

Scenario: Verification of the navigation from glossary definition page to actual know how resource
    When the user clicks on glossary term "Disability Discrimination Act 1992 (Cth) (DDA)"
    And clicks on the know how resource link "Practice note: Disability discrimination: an overview of the operation of the Disability Discrimination Act 1992 (Cth)" in the definition page
    Then document title is displayed as "Disability discrimination: overview of the Disability Discrimination Act 1992 (Cth)"
	And "Register now" button is present in document body
		