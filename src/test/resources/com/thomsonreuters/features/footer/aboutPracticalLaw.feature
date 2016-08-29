Feature: [809369] I want to view the "About Practical Law"

  Scenario Outline: [809369]I want to view the "About Us - About Practical Law" page So that I can find out what is practical law
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2 |
    When user clicks on "<Page>" link
    Then the user verifies that the current PageTitle contains '<pageTitle>'
    And tab "<tab>" in Table of Contents is active
    And about company page is not empty
    When the user selects a tab "<tabToSelect>" within the table of contents
    And tab "<tabToSelect>" in Table of Contents is active
    Then the user verifies that the current PageTitle contains '<pageTitleForSelectedTab>'
    And about company page is not empty

    Examples: 
      | Page                | pageTitle           | tab                 | tabToSelect            | pageTitleForSelectedTab |
      | About Practical Law | About Practical Law | About Practical Law | Why use Practical Law? | Why use Practical Law?  |
      | About Practical Law | About Practical Law | About Practical Law | Resources              | Resources               |
      | About Practical Law | About Practical Law | About Practical Law | Our team               | Our team                |
