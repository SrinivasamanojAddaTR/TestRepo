Feature: [850092] As an open web user I want to perform a search So that I can view the documents that are available in PL Australia

  Background:
      Given PL+ ANZ user navigates to home page

  @gold
  Scenario Outline: Verify valid search results for single and multiple terms in Open Web
	When the user runs specific operator search for the query "<Search Term>" 
	And the user can select the option to show less detail 
	Then the user can verify that the less detail icon is displayed 
	And the user should verify the presence of following search structure for "less" option 
		|Title         |
		|Resource Type |
		|Jurisdiction  |
		|Status        |
	And the user is able to verify that a page of search results is displayed 
	And the user selects the "20" from per page dropdown 
	And the user can select the option to show more detail 
	Examples:
		| Search Term  |
		| Tax          |

  Scenario Outline: Verify invalid search results for single term in Open Web
	When the user runs a free text search for the query "<Search Term>" 
	Then user should see the label "0 Results for "<Search Term>"" in the search result heading 
	Then user should have suggestion i.e. "Did you mean: <Correct Term>" 
	And user should not see any filters on the left hand side 
	Examples: 
		| Search Term | Correct Term |
		| taxx        |     tax      |

  Scenario Outline: User can see the sort by relevance is selected by default in Open Web
	When the user runs a free text search for the query "<term>" 
    And the user verifies that the option for sorting by relevance is displayed by default
    And the user clicks on the first link in results
	Examples:
		| term |
		| law |

  Scenario: [850123] Verify valid search results for multiple terms and also the document retrieved in Open web
     Given PL+ ANZ user navigates to home page
     When the user runs specific operator search for the query "Tax And Accounting"
     And the user can select the option to show more detail
     And user clicks on "Share purchases" link
     And user verifies the "Share purchases" page Title in Open Web
     When user clicks on "Search Results" link
     Then the user is able to verify that a page of search results is displayed
