Feature: [810047]As a PLAU User, I want to view a list of search operators and what they mean
  So that I understand how to build searches that will help me narrow my search results

  Scenario: User verifies the search connector guides display correctly
    Given ANZ user is logged in
    When user clicks on the "i"
    Then user should see the search guide popup
    And user should see the following character with its related explanation on the popup
    | Character | Explanation                                          |
    |    &      | Search terms in the same document                    |
    |   or      | Either search term or both terms                     |
    |    %      | Not containing terms                                 |
    |   ""      | to-Search for an exact phrase within quotation marks |
    |    /p     | Terms in same paragraph                              |
    |    +p     | Preceding term within same paragraph                 |
    |    /n     | Within n terms of                                    |
    |    +n     | Preceding within n terms of                          |
    |    *      | Universal character                                  |
    |    #      | Turn off plurals and equivalents                     |
    |    !      | Search for terms with multiple endings               |

