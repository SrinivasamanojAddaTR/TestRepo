Feature: [820472][820473] View and Navigate within Glossary
	As a PLAU User
	I want to view a document within the glossary section of Practical Law Australia
	So that I can understand the meaning of a legal term
	I want to navigate between glossary terms
	So that I can quickly find the glossary document I am looking for

Background: 
	Given ANZ user is logged in
	And user navigates to a glossary page 

@smoke @gold
  Scenario: Verify Tabbed Alphabets functionality
	When the user is able to see the tabbed alphabetical list 
	And the user clicks on the alphabet "B" in the tabbed alphabetical list 
	Then letter "B" should be selected on the alphabet tab
	And the glossary list rolls up and the first term in the respective list is selected (except x, Y and Z) 
	And the corresponding definition of the selected term should be displayed on the right hand side 
	And the corresponding terms for "B" are displayed
	And the term "Australian Human Rights Commission (AHRC)" is not visible in the list


Scenario: Scroll up and down buttons	
	When the user clicks on the alphabet "D" in the tabbed alphabetical list 
	Then letter "D" should be selected on the alphabet tab
	And the user should be able to view the scroll up and scroll down button on the list 
	And clicking on the scroll up button the user should be able to roll up the list of terms 
	And clicking on the scroll down button the user should be able to traverse down the list of terms 
#todo the last two steps need to be refactored
	

 Scenario: Navigation to glossary terms on glossary definition page
    When the user clicks on glossary term "Disability"
    Then the title "Disability" is displayed in the definition
    And the definition contains text "A total or partial loss"
    And letter "D" should be selected on the alphabet tab


Scenario: Verification of the navigation from glossary definition page to actual know how resource
    When the user clicks on glossary term "Disability Discrimination Act 1992 (Cth) (DDA)"
    And clicks on the know how resource link "Practice note: Disability discrimination: an overview of the operation of the Disability Discrimination Act 1992 (Cth)" in the definition page
    Then document title is displayed as "Overview of federal disability discrimination"


Scenario: Navigation from one glossary page to another
	When the user clicks on the alphabet "R" in the tabbed alphabetical list 
 	And the user clicks on glossary term "Reasonable adjustments"
 	And clicks on the glossary term link "inherent requirements of the position" in the definition page
 	Then the title "Inherent requirements of the position" is displayed in the definition
    And letter "I" should be selected on the alphabet tab
    
    
    
    
    
    
    