Feature: [812852] Time Out: Search State
  As a PLAU user
  I want to my search results and facets to be retained when I login after my session times out
  So that I do not have to perform a new search

#bug 868963 Bug [PLAU & PLUK] Search Timeout: child facets state on search page is not retained after timeout

@archived
  Scenario Outline: Users who have logged in and NOT selected the Super Remember Me Option-<id>
    Given ANZ user is logged in with following details
      | mandatoryRouting | YES                              |
      | routing          | SPECIFIED_USER_TIMEOUT_7_MINUTES |
    When the user runs a free text cobalt search with query "<query>"
#    And the user clicks 'select multiple filters' button
    And user expands the "Practice Area" facets group
    And the user expands the know how facet "<facet1>"
    And the user selects the know how child facet "<facet11>"
    And the user selects the know how option to apply filters
    And he has a session open and timed out after "300" sec
    Then the user is presented with a warning message that session is expired
    And he should stay on same search page as OpenWeb user
    And the user verifies that the know how facet is selected "<facet11>"
    When the user refreshes the page
    Then he should stay on same search page as OpenWeb user
    When the user deselects the know how facet "<facet11>"
    And the user clicks 'select multiple filters' button
    And user expands the "Resource Type" facets group
    And the user expands the know how facet "<facet2>"
    And the user selects the know how child facet "<facet22>"
    And the user selects the know how option to apply filters
    And the user saves the page title
    And the user clicks Log in button
    And a PPI user enter its username and password
      | userName | AUtestuser14 |
    And clicks on Sign in
    Then user gets redirected to the search page that he was visiting and is logged in
    And the user verifies that the know how facet is selected "<facet22>"
    Examples:
      | query | facet1      | facet11                             | facet2         | facet22                 | id |
      | tax   | Company Law | Company administration and meetings | Practice notes | Practice note: overview | 1  |

  @RemoveSRMOptionANZ
  Scenario Outline: Users who have logged in and NOT selected the Super Remember Me Option-<id>
    Given ANZ user is logged in with following details
      | routing          | SPECIFIED_USER_TIMEOUT_7_MINUTES |
      | role             | SUPER_REMEMBER_ME_USER           |
      | mandatoryRouting | YES                              |
    When the user runs a free text cobalt search with query "<query>"
#    And the user clicks 'select multiple filters' button
    And user expands the "Practice Area" facets group
    And the user expands the know how facet "<facet1>"
    And the user selects the know how child facet "<facet2>"
    And the user selects the know how option to apply filters
    And he has a session open and timed out after "40" sec
    Then user gets redirected to the search page that he was visiting and is logged in
    And the user verifies that the know how facet is selected "<facet2>"

    Examples:
      | query | facet1      | facet2                              | id |
      | tax   | Company Law | Company administration and meetings | 1  |
