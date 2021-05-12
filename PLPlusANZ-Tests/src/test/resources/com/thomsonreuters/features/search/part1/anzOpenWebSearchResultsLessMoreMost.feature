
Feature: [813300]As an open web user, I want to be able to choose to show less or more detail in my search result So that I can display the appropriate level of detail for each search result

  Background:
    Given PL+ ANZ user navigates to home page

  Scenario: Verify default setting for more detail in open web
    When the user runs a free text search for the query "tax"
    And the user can verify that the more detail icon is displayed

  Scenario: Verify setting for less, more and most details
    Given the user runs a free text search for the query "tax"
    When the user can select the option to show less detail
    Then the user can verify that the less detail icon is displayed
    And the user should verify the presence of following search structure for "less" option
      |Title         |
      |Resource Type |
      |Jurisdiction  |
      |Status        |
    When the user can select the option to show more detail
    Then the user can verify that the more detail icon is displayed
    And the user should verify the presence of following search structure for "more in OpenWeb" option
      |Title         |
      |Resource Type |
      |Jurisdiction  |
      |Status        |
      |Absract       |






