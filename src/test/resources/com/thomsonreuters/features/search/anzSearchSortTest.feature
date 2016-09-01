Feature: [815735] As a PLAU User, I want to sort my search results by relevancy, date and Title [A-Z]
  So that I can view the list of search results in my preferred order

#bug 863874 Bug [PLAU & PLUK] Search is sorted by date when relevance is selected

@bug
  Scenario: [815735] Verify sort results by date. Verify that the user is able to sort search results by relevancy
   Given ANZ user is logged in with following details
      | userName         | ANZtestuser2 |
    When the user runs a free text search for the query "contract"
    And the user verifies that the option for sorting by relevance is displayed by default
    And the user obtains the title of the first, second and third result and stores it
    And the user can select the option to sort by "Date"
    And the user can verify that the title of the first, second and third results should not be the same as the stored values
    And the user can select the option to sort by "Relevance"
    Then the user is able to verify that the title of the first,second and third results should be the same as the stored values