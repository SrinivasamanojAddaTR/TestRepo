Feature: [809387] I want to view the "Meet the Team"

  Scenario Outline: [809387] As a PL Australia User (PLAU) I want to view the "Meet the Team" page So that I can find out who works on the Practical Law website and what their background is-<id>
    Given ANZ user is logged in
    When user clicks on meet the team link
    Then the user verifies that the current PageTitle contains '<pageTitle>'
    And there are '26' team members listed
    And profiles are not empty
    And the table of contents will list practice areas in alphabetical order
    When the user selects a practice area "<PA>" within the table of contents
    And practice area "<PA>" in Table of Contents is active
    And there are "<numberOfProfiles>" team members listed for practice area
    And profiles are not empty

    Examples:
      | pageTitle | PA                     | numberOfProfiles | id |
      | Our team  | Company Law            | 6                | 1  |
      | Our team  | Corporate Transactions | 6                | 2  |
      | Our team  | Employment             | 2                | 3  |
