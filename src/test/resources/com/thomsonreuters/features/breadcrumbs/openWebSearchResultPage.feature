Feature: As a open web user user,I want to view a breadcrumb trail with clickable links to Search results on the Practical Law Australia website, so that I can view my navigation path whilst browsing the website and easily navigate to the Search Results that I have visited in my way to the viewing page or document.


Background:
	Given PL+ ANZ user navigates to home page
 

 Scenario: Verify user can view Breadcrumb trial for HomePage Search
 When the user searches for term "law"
 And the user is able to verify that a page of search results is displayed 
 Then the user verifies that the complete breadcrumb is "Home > Search Results"
 

  Scenario: Verify user can view Breadcrumb trial for Filter search
	When the user searches for freetext term "law"
    And the user expands the know how facet "Practice Area"
	And the user selects the know how following parent facets with single selection
      |Corporate Transactions|
	Then the user verifies that the complete breadcrumb is "Home > Search Results"
	

 Scenario: Verify user can view Breadcrumb trial for Simple search
	When the user opens 'Practice Areas' link 
	And the user opens 'Commercial' link 
	And the user opens 'Franchising' link 
	When the user searches for term "law"
	Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising > Search Results" 
	
Scenario: Verify user can view Breadcrumb trial for Page switching search
	When the user opens 'Practice Areas' link
	And the user opens 'Commercial' link 
	And the user opens 'Franchising' link 
	When the user searches for term "law"
	Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising > Search Results" 
	And user clicks on Page "2" of the Topic page results list 
	Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising > Search Results" 
		

Scenario:  Verify user can view Breadcrumb trial for Search in different areas
	When the user opens 'Practice Areas' link 
	And the user opens 'Commercial' link
	And the user opens 'Franchising' link 
	When the user searches for freetext term "law"
	Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising > Search Results" 
	And the user can display the scoped search dropdown menu options
	And user selects the dropdown option "Corporate"
	When the user searches for term "tax"
	Then the user verifies that the complete breadcrumb is "Home > Corporate > Search Results"
	
	
 Scenario: Verify user can view Breadcrumb trial for All content search
    And the user can display scoped search dropdown menu options
	And user selects the dropdown option "Corporate" 
	When the user searches for term "tax"
	Then the user verifies that the complete breadcrumb is "Home > Corporate > Search Results" 
	And the user can display scoped search dropdown menu options
	And user selects the dropdown option "All Content" 
	When the user searches for term "tax"
	Then the user verifies that the complete breadcrumb is "Home > Search Results" 
	
	
		
 Scenario: Verify user can view Breadcrumb trial for Breadcrumb click move
	When the user opens 'Practice Areas' link 
	And the user opens 'Commercial' link 
	And the user searches for freetext term "tax"
	Then the user verifies that the complete breadcrumb is "Home > Commercial > Search Results" 
	Then the user clicks on the 'Commercial' link in the breadcrumb
    And the user opens 'Franchising' link
	When the user searches for freetext term "tax"
	Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising > Search Results"
	
	
Scenario: Verify user can view Breadcrumb trial for Search on a topic page
	When the user opens 'Practice Areas' link 
	And the user opens 'Employment' link 
	And the user opens 'Building industry' link 
	And the user can display the scoped search dropdown menu options
	And user selects the dropdown option "Company Law" 
	When the user searches for term "tax"
	Then the user verifies that the complete breadcrumb is "Home > Company Law > Search Results" 
	
	
Scenario: Verify user can view Breadcrumb trial Search on a document page
	When the user opens 'Resources' link 
	And the user opens 'Practice notes' link 
	And the user opens 'Commercial' link 
	When the user searches for term "law"
	Then the user verifies that the complete breadcrumb is "Home > Practice notes > Commercial Practice notes > Search Results"
	
	
Scenario: User verifies new breadcrumb trail after another free search run
    When the user opens 'Practice areas' link
    And the user opens 'Commercial' link
    And the user opens 'Franchising' link
    And the user searches for term "Tax"
    Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising > Search Results"
    When the user can display the scoped search dropdown menu options
    And user selects the dropdown option "Company Law" 
    And the user searches for term "Tax"
    Then the user verifies that the complete breadcrumb is "Home > Corporate > Search Results"
    When the user clicks on the 'Corporate' link in the breadcrumb
    And the user is presented with a topic page with title "Corporate"

@archived
Scenario: Verify user can view Breadcrumb trial for Search on a international page
    When the user opens 'International' link
    And the user opens 'Austria' link
    When the user searches for term "law"
	Then the user verifies that the complete breadcrumb is "Home > Austria > Search Results"