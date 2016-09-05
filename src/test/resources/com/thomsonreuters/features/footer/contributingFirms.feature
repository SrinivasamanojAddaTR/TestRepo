@wip
Feature: [809413] I want to view the Contributing Firms pages
As a PL Australia User I want to view the Contributing Firms pages so that I can find out who the has contributed to the Practical Law website

  Background: 
    Given ANZ user is logged in
	And user clicks on "Contributing Firms" link

@wip 
  Scenario: [809413] The letter anchors on the top works correctly
    When the user cliks on letter "J" 
    Then the user is taken to the list of Contributing Firms with names starting with the letter "J"
    When the user cliks on letter "C" 
    Then the user is taken to the list of Contributing Firms with names starting with the letter "C"
@wip 
  Scenario: [809413] The contributing firm page has title, main information, contributed resources, logo and contact information 
    When the user cliks on random contributing firm
	Then the user verifies that the PageTitle is present on current firm page
	And main information about firm is on current firm page
	And contributed resources is on current firm page
	And logo is on current firm page
	And contact information is on current firm page
	