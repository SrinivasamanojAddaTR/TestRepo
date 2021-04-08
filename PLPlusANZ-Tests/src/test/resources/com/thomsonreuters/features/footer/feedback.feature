@gold
Feature: [857832] Feedback form changes


  Scenario:  [857832] Feedback link leads to nicereply.com - open web
    Given ANZ user is not logged in
    When the user navigates to the main PLANZ page
    And user should see footer
    And user clicks on "Feedback" link
    Then the feedback form with title "Practical Law Feedback" is displayed
    Then the user verify character limit message is 'Maximum length is 3000 characters'

  Scenario:  [857832] Feedback link leads to nicereply.com - PA user
    Given ANZ user is logged in
    When user should see footer
    And user clicks on "Feedback" link
    Then the feedback form with title "Practical Law Feedback" is displayed
    Then the user verify character limit message is 'Maximum length is 3000 characters'


