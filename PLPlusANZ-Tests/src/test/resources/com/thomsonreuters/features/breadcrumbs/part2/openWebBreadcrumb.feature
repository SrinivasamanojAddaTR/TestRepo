@open_web
Feature: Verify that logged in Open Web user can see breadcrumb trail with clickable Homepage link on PLAU site and navigate back to Homepage when clicking on this link.

Background: 
	Given PL+ ANZ user navigates to home page
	

  Scenario: Verify user can view Breadcrumb trial in Practice Area Page
    When the user opens 'Practice Areas' link
    And the user opens 'Commercial' link
    And the user opens 'Franchising' link
    Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising"
    When the user clicks on the home link in the breadcrumb 
   Then user verifies title "Home" page
    

  Scenario: Verify User can view Breadcrumb trail in Resource page
    When the user opens 'Resources' link
    And the user opens 'Legal updates' link
    And the user opens 'Employment' link
   Then the user verifies that the complete breadcrumb is "Home >Legal updates >Legal Updates | Employment"
   

   Scenario: Verify user can view  breadcrumb in selected practice area in resources tab
    When the user opens 'Practice Areas' link
    And the user opens 'Commercial' link
    And the user opens 'Resources' link
    And the user opens 'Standard clauses and drafting notes' link
    Then the user verifies that the complete breadcrumb is "Home >Commercial >Commercial Standard clauses and drafting notes"
  
@archived
    Scenario: Verify user can view  breadcrumb in international topic page
    When the user opens 'International' link
    And the user opens 'Austria' link
    And the user opens 'Arbitration resources' link
    Then the user verifies that the complete breadcrumb is "Home > Austria > Search Results"