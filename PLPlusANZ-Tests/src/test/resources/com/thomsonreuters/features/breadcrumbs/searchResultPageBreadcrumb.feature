Feature: As a PLAU user,I want to view a breadcrumb trail with clickable links to Search results on the Practical Law Australia website, so that I can view my navigation path whilst browsing the website and easily navigate to the Search Results that I have visited in my way to the viewing page or document.


Background:
 Given ANZ user is logged in
 

 Scenario: Verify user can view Breadcrumb trial for HomePage Search
 When the user searches for term "law"
 And the user is able to verify that a page of search results is displayed 
 Then the user verifies that the complete breadcrumb is "Home > Search Results"
 

  Scenario: Verify user can view Breadcrumb trial for Filter search
	When the user searches for term "law"
	And the user selects facets
      |Corporate |
	Then the user verifies that the complete breadcrumb is "Home > Search Results"
	

 Scenario: Verify user can view Breadcrumb trial for Simple search
	When the user opens 'Practice Areas' link 
	And the user opens 'Commercial' link
 	And the user opens 'Topics' link
	And the user opens 'Franchising' link 
	When the user searches for term "law"
	Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising > Search Results" 
	
Scenario: Verify user can view Breadcrumb trial for Page switching search
	When the user opens 'Practice Areas' link
	And the user opens 'Commercial' link
	And the user opens 'Topics' link
	And the user opens 'Franchising' link 
	When the user searches for freetext term "law"
	Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising > Search Results" 
	And user clicks on Page "2" of the Topic page results list 
	Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising > Search Results" 
		

Scenario:  Verify user can view Breadcrumb trial for Search in different areas
	When the user opens 'Practice Areas' link 
	And the user opens 'Commercial' link
	And the user opens 'Topics' link
	And the user opens 'Franchising' link 
	When the user searches for freetext term "law"
	Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising > Search Results" 
	And the user can display the scoped search dropdown menu options
	And user selects the dropdown option "Corporate Transactions"
	When the user searches for freetext term "tax"
	Then the user verifies that the complete breadcrumb is "Home > Corporate Transactions> Search Results"

 Scenario: Verify user can view Breadcrumb trial for All content search
    And the user can display scoped search dropdown menu options
	And user selects the dropdown option "Corporate Transactions"
	When the user searches for freetext term "tax"
	Then the user verifies that the complete breadcrumb is "Home > Corporate Transactions> Search Results"
	And the user can display scoped search dropdown menu options
	And user selects the dropdown option "All Content" 
	When the user searches for freetext term "tax"
	Then the user verifies that the complete breadcrumb is "Home > Search Results" 
	
	
		
 Scenario: Verify user can view Breadcrumb trial for Breadcrumb click move
	When the user opens 'Practice Areas' link 
	And the user opens 'Commercial' link 
	And the user searches for term "tax"
	Then the user verifies that the complete breadcrumb is "Home > Commercial > Search Results" 
	Then the user clicks on the 'Commercial' link in the breadcrumb
    And the user opens 'Franchising' link
	When the user searches for term "tax"
	Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising > Search Results"
	
	
Scenario: Verify user can view Breadcrumb trial for Search on a topic page
	When the user opens 'Practice Areas' link 
	And the user opens 'Employment' link
	And the user opens 'Topics' link
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
    When the user opens 'Practice Areas' link
    And the user opens 'Commercial' link
	And the user opens 'Topics' link
    And the user opens 'Franchising' link
    And the user searches for freetext term "Tax"
    Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising > Search Results"
    When the user can display the scoped search dropdown menu options
    And user selects the dropdown option "Company Law" 
    And the user searches for freetext term "Tax"
    Then the user verifies that the complete breadcrumb is "Home > Company Law > Search Results"
    When the user clicks on the 'Company Law' link in the breadcrumb
    And the user is presented with a topic page with title "Company Law"

@archived
Scenario: Verify user can view Breadcrumb trial for Search on a international page
    When the user opens 'International' link
    And the user opens 'Austria' link
    When the user searches for term "law"
	Then the user verifies that the complete breadcrumb is "Home > Austria > Search Results"


	Scenario Outline: User verifies new breadcrumb after clicking cross-reference link-<id>
		When the user opens 'Practice Areas' link
		And the user opens 'Commercial' link
		And the user searches for freetext term "<Doc name>"
		And the user clicks on search result "<Doc name>" title link
		Then the user verifies that the complete breadcrumb is "<Breadcrumb1>"
		When the user clicks on the '<Internal link>' link in the doc
		Then the user verifies that the complete breadcrumb is "<Breadcrumb2>"
		Examples:
			| Doc name                     | Breadcrumb1                                                       | Internal link                                      | Breadcrumb2                                                                             | id |
			| Goods and Services Tax (GST) | Home > Commercial > Search Results > Goods and Services Tax (GST) | A New Tax System (Goods and Services Tax) Act 1999 | Home > Commercial > Search Results > A New Tax System (Goods and Services Tax) Act 1999 | 1  |

     
      