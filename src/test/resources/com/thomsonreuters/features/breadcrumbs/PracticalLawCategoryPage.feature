Feature: As a PLAU user,I want a dropdown list to appear at the end of by breadcrumb,so that I can be presented with a list of documents that I've visited in my path to the document I'm viewing

Background:
 Given ANZ user is logged in
 

  Scenario: Verify user can view Breadcrumb trial in Practice Area Page
    When the user opens 'Practice Areas' link
    And the user opens 'Commercial' link
    And the user opens 'Franchising' link
    Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising" 
    

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
    Then the user verifies that the complete breadcrumb is "Home >Commercial >Commercial Standard clauses and drafting notes "
    

  Scenario: verify user can view breadcrumb in practice area after clicking back in browser
    When the user opens 'Practice Areas' link
    And the user opens 'Commercial' link
    And the user opens 'Resources' link
    And the user opens 'Standard clauses and drafting notes' link
    And the user goes back in browser
    Then the user verifies that the complete breadcrumb is "Home >Commercial"
  
  @archived
   Scenario: Verify user can view  breadcrumb in international topic page
    When the user opens 'International' link
    And the user opens 'Austria' link
    And the user opens 'Arbitration resources' link
    Then the user verifies that the complete breadcrumb is "Home > Austria > Search Results"
    
    
   Scenario: Verify  breadcrumb trial in  Topic page, by clicking on home link in the trial from a topic page 
    When the user opens 'Practice Areas' link
    And the user opens 'Commercial' link
    And the user opens 'Topics' link
    And the user opens 'Contract law' link
    And the user clicks on the home link in the breadcrumb
    And the user opens 'Practice Areas' link
    And the user opens 'Dispute Resolution' link
    And the user opens 'ADR and compromise' link
    Then the user verifies that the complete breadcrumb is "Home >Dispute Resolution >ADR and compromise "
   

   Scenario: Verify user can view Breadcrumb trial in Practice Area Page and when clicked on Home link, it should navigate to home page
    When the user opens 'Practice Areas' link
    And the user opens 'Commercial' link
    And the user opens 'Franchising' link
    Then the user verifies that the complete breadcrumb is "Home > Commercial > Franchising"  
    When the user clicks on the home link in the breadcrumb
    Then user verifies title "Home" page