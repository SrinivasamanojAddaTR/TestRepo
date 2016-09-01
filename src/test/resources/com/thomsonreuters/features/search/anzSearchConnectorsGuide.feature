Feature: [810047]As a PLAU User, I want to view a list of search operators and what they mean
  So that I understand how to build searches that will help me narrow my search results

  Scenario: User verifies the search connector guides display correctly
    Given ANZ user is logged in with following details
          | userName | ANZtestuser2 |
    When user clicks on the "i"
    Then user should see the search guide popup
    And user should see the following character with its related explanation on the popup
    | Character | Explanation                                          |
    |    &      | Search terms in the same document                    |
    |   or      | Either search or both                                |
    |    %      | Not containing terms                                 |
    |   ""      | Terms appear in the same order as in quotation marks |
    |    /p     | In same paragraph                                    |
    |    +p     | Preceding within same paragraph                      |
    |    /n     | Within n terms of                                    |
    |    +n     | Preceding within n terms of                          |
    |    *      | Universal character                                  |
    |    #      | Turn off plurals and equivalents                     |

