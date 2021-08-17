Feature: [813246][808751] As a PLAU User, I want to perform a home page search and view search results
  So that I can select and retrieve a document I am interested in researching

  Background:
    Given ANZ user is logged in

  Scenario Outline: Verify valid search results for single and multiple terms-<id>
    When the user runs specific operator search for the query "<Search Term>"
    And the user can select the option to show less detail
    Then the user can verify that the less detail icon is displayed
    And the user should verify the presence of following search structure for "less" option
      | Title         |
      | Resource Type |
      | Jurisdiction  |
      | Status        |
    And the user is able to verify that a page of search results is displayed
    And the user selects the "20" from per page dropdown
    And the user can select the option to show more detail
    And the user should see the each search result with search term "<Search Term>" in the search result snippet
  @sanity
    Examples:
      | Search Term | id |
      | Tax         | 1  |
  @smoke @gold
    Examples:
      | Search Term     | id |
      | TAX DEED        | 2  |
      | Tax And Account | 3  |
      | TAX Or DEED     | 4  |


  @gold
  Scenario Outline: Verify invalid search results for single and multiple terms-<id>
    When the user runs a free text search for the query "<Search Term>"
    Then user should see the label "0 Results for "<Search Term>"" in the search result heading
    Then user should have suggestion i.e. "Did you mean: <Correct Term>"
    And user should not see any filters on the left hand side
    Examples:
      | Search Term | Correct Term | id |
      | taxx        | tax          | 1  |
      | TAX DEEDD   | TAX DEED     | 2  |


  @gold
  Scenario Outline: User can open the document from search results-<id>
    When the user runs a free text search for the query "<term>"
    And the user clicks on the first link in results
    Then the user can verify that the full text of the document contains the search term "<term>"
    Examples:
      | term | id |
      | law  | 1  |
	