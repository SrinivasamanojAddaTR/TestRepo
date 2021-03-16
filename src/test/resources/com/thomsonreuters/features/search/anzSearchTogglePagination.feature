Feature: [813335] As a PLAU user, I want to view the page number for my search result
  So that I can identify which page of search results I am viewing

  Background: 
   Given ANZ user is logged in

  Scenario: User checks the "Pagination" appearance for KH search results.
    When the user runs a free text search for the query "law"
    And the user selects the "20" from per page dropdown
    Then the user verifies that page "1" is selected
    And the user is able to verify the presence of below page numbers
      | 2 |
      | 3 |
      | 4 |
      | 5 |
      | 6 |
      | 7 |
      | 8 |
      | 9 |
    And user verifies the "Last Page" present
    And user verifies the navigation to "First Page" not present
    When user clicks on "7" link
    And user verifies the "First Page" present
    When user clicks on "5" link
    And user verifies the "Last Page" present
    When clicks on the "Last Page" pagination link
    Then user verifies the navigation to "Last Page" not present
    And user verifies the "First Page" present

# Changed number of results per page to 100, as the query register has more results and has more pages.
  Scenario: User checks the "Pagination" appearance for less than 8 pages results.
    When the user runs a free text search for the query "register"
    And the user selects the "100" from per page dropdown
    Then the user verifies that page "1" is selected
    And the user is able to verify the presence of below page numbers
      | 2 |
      | 3 |
    And user verifies the "Last Page" present
    And user verifies the navigation to "First Page" not present
    When user clicks on "3" link
    And user verifies the "Last Page" present
    And user verifies the "First Page" present
