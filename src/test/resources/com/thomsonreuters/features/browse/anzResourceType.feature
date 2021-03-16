Feature: [825619]As a PLAU User, I want to view a Resource Type by Practice Areas page So that I can view and link to a list of documents for a specific resource type and practice area

  #TODO global guides is under consideration will appear in future
  @wip
  Scenario: User verifies the Pratice area pages and its relative tabs
   Given ANZ user is logged in
    When user selects the following tab and see the relative links or content
      |tab | content |
      | Resources | Practice notes, Standard documents and drafting notes, Standard clauses and drafting notes, Checklists, Glossary, Global guides|
    Then user verifies following Resource Types with presence of all Practice Areas
      | Resource Types                        | Practice Areas |
      | Practice notes                        | Company Law, Corporate Transactions, Employment |
      | Standard documents and drafting notes | Company Law, Corporate Transactions, Employment |
      | Standard clauses and drafting notes   | Company Law, Corporate Transactions, Employment |
      | Checklists                            | Company Law , Corporate Transactions, Employment|
      | Glossary                              |                 -                               |
  #TODO global guides is under consideration will appear in future
  @wip
  Scenario Outline:[815726] I want to view a Resource Type by [Practice Area X] page So that I can link to a document I am interested in working
   Given ANZ user is logged in
    When user selects the following tab and see the relative links or content
      |tab | content |
      | Resources | Practice notes, Standard documents and drafting notes, Standard clauses and drafting notes, Checklists, Glossary, Global guides|
    And the user navigates to resource page "<Resource Type>" filtered by "<PA>" practice area
	And user should see the label "<PA> <Resource Type>" in the search result heading 
     Examples:
      | Resource Type                         | PA          			|
      | Practice notes                        | Company Law 			|
      | Practice notes                        | Corporate Transactions 	|
      | Practice notes                        | Employment 				|
      | Standard documents and drafting notes | Company Law 			|
      | Standard documents and drafting notes | Employment 				|
      | Standard clauses and drafting notes   | Company Law 			|
      | Checklists                            | Corporate Transactions 	|
      | Checklists                            | Employment 				|


  @smoke @gold
  Scenario: [848134] Facets on resource type pages
	Given ANZ user is logged in
	And user navigates to resource type "Practice notes" for practice area "Company Law"
    And user expands the "Practice Area" facets group
    When the user selects the know how following parent facets with single selection
      | Company administration and meetings |
    Then the user is able to verify that a page of search results is displayed
    And there is no error message on search results page