Feature: [813317] As a PLAU User,  I want to perform a pre-filtered scoped search and view search results
  So that I can select and retrieve a document I am interested in researching without having to browse to that page

Background:
	Given ANZ user is logged in


  @gold
  Scenario: Verify valid search results for single and multiple terms
    Given the user can verify that the scoped search dropdown states "All Content"
    When the user can display the scoped search dropdown menu options
    Then the scoped search drop down options should provide the following areas
      | All Content                     |
      | Company Law                     |
      | Corporate Transactions          |
      | Employment                      |
    When user selects the dropdown option "Company Law"
    Then the user runs a free text search for the query "tax"
    And the user can verify that the scoped search dropdown states "Company Law"
    When the user can display the scoped search dropdown menu options
    When user selects the dropdown option "Employment"
    Then the user runs a free text search for the query "tax"
    And the user can verify that the scoped search dropdown states "Employment"

    Scenario: [813292]Verify valid Scoped Search for single and multiple term on Practice Area
     When user clicks on "Browse Menu" dropdown
     And user selects sub-menu "Practice areas" and clicks on the link "Employment"
     And the user runs a free text search for the query "tax"
     Then the user can verify that the scoped search dropdown states "Employment"
     And the user runs a free text search for the query "Income Tax"
     Then the user can verify that the scoped search dropdown states "Employment"
     And the user runs a free text search for the query "taxx"
     Then user should see the label "0 Results for "taxx"" in the search result heading

    Scenario: [813292]Verify valid Scoped Search for single and multiple term on Resource pages
     When user clicks on "Browse Menu" dropdown
     And user selects sub-menu "Resources" and clicks on the link "Practice notes"
     And the user runs a free text search for the query "tax"
     Then the user can verify that the title listed above the search results is "All Content"
     And the user runs a free text search for the query "Income Tax"
     Then the user can verify that the title listed above the search results is "All Content"
     And the user runs a free text search for the query "taxx"
     Then user should see the label "0 Results for "taxx"" in the search result heading
@archived
   Scenario: [813292]Verify valid Scoped Search for single and multiple term on International pages
     When user clicks on "Browse Menu" dropdown
     And user selects sub-menu "International" and clicks on the link "Austria"
     And the user runs a free text search for the query "tax"
     Then the user can verify that the scoped search dropdown states "Austria"
     And the user runs a free text search for the query "Income Tax"
     Then the user can verify that the scoped search dropdown states "Austria"
     And the user runs a free text search for the query "taxx"
     Then user should see the label "0 Results for "taxx"" in the search result heading

