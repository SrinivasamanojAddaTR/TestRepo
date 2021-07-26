
Feature: [813300] As a PLAU user, I want to display less, more or most detail for each of my search results
  So that I can see the level of information I need for my research and legal work

#bug 863546 Bug [PLAU & PLUK] Search: Less, More and Most Detail icon titles should be in sentence case

 Background:
   Given ANZ user is logged in
 

  Scenario Outline: Verify default setting for more detail in open web-<id>
    When the user runs a free text search for the query "<query>"
    When the user can select the option to show more detail
    And the user can verify that the more detail icon is displayed
    Examples:
      | query | id |
      | tax   | 1  |

  Scenario: Verify setting for less, more and most details
    Given the user runs a free text search for the query "tax position"
    When the user can select the option to show less detail
    Then the user can verify that the less detail icon is displayed
    And the user should verify the presence of following search structure for "less" option
      |Title         |
      |Resource Type |
      |Jurisdiction  |
      |Status        |
    When the user can select the option to show more detail
    Then the user can verify that the more detail icon is displayed
    And the user should verify the presence of following search structure for "more" option
      |Title         |
      |Resource Type |
      |Jurisdiction  |
      |Status        |
      |Absract       |
      |First Snippet |
    When the user can select the option to show most detail
    Then the user can verify that the most detail icon is displayed
    And the user should verify the presence of following search structure for "most" option
      |Title         |
      |Resource Type |
      |Jurisdiction  |
      |Status        |
      |Absract       |
      |First Snippet |
      |Second Snippet |




