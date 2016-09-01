Feature: [850104] As an open web user, I want to perform a pre-filter search So that I can scope my search to a specific practice area and see what content is available

  Background:
     Given PL+ ANZ user navigates to home page

  Scenario: Verify valid search results for single and multiple terms in Open Web
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

    Scenario: [850105]Verify valid Scoped Search for single and multiple term on Practice Area in Open Web
     When user clicks on "Browse Menu" dropdown
     And user selects sub-menu "Practice areas" and clicks on the link "Employment"
     And the user runs a free text search for the query "tax"
     Then the user can verify that the scoped search dropdown states "Employment"
     And the user runs a free text search for the query "Income Tax"
     Then the user can verify that the scoped search dropdown states "Employment"
     And the user runs a free text search for the query "taxx"
     Then user should see the label "0 Results for "taxx"" in the search result heading

    Scenario: [850105]Verify valid Scoped Search for single and multiple term on Resource pages in Open Web
     When user clicks on "Browse Menu" dropdown
     And user selects sub-menu "Resources" and clicks on the link "Practice notes"
     And the user runs a free text search for the query "tax"
     Then the user can verify that the title listed above the search results is "All Content"
     And the user runs a free text search for the query "Income Tax"
     Then the user can verify that the title listed above the search results is "All Content"
     And the user runs a free text search for the query "taxx"
     Then user should see the label "0 Results for "taxx"" in the search result heading

   Scenario: [850105]Verify valid Scoped Search for single and multiple term on International pages in Open Web
     When user clicks on "Browse Menu" dropdown
     And user selects sub-menu "International" and clicks on the link "Austria"
     And the user runs a free text search for the query "tax"
     Then the user can verify that the scoped search dropdown states "Austria"
     And the user runs a free text search for the query "Income Tax"
     Then the user can verify that the scoped search dropdown states "Austria"
     And the user runs a free text search for the query "taxx"
     Then user should see the label "0 Results for "taxx"" in the search result heading

