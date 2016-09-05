Feature: [824488]As a PLAU user, I want to change my locale So that my dates and times are displayed in the locale that I have selected


  Scenario: User verifies the change of time-zone on history page results
    Given ANZ user is logged in
    And the user runs a free text search for the query "test"
    When the user opens time-zone popup using user dropdown
    And user changes the time-zone to "London"
    And user navigates to the History Page to store the time at row "1"
    When the user opens time-zone popup using user dropdown
    And user changes the time-zone to "Sydney"
    Then user verifies the time at row "1" on History page changes to "Sydney" time

# This test would only work on QED as it is a DEMO environment issue that doesn't show the exact time
   Scenario: User verifies the time-zone timestamp on the sign off page
    Given ANZ user is logged in
    When the user opens time-zone popup using user dropdown
    And user changes the time-zone to "Sydney"
    And when the user logs out
    Then user verifies the sign out time is according to "Sydney" time-zone

