Feature: [821959] As a PLAU User, I want to view my history based on a specific date or days So that I can display the data that is relevant to me


#bug 851064  Bug [PLAU only] On History page date faceting works incorrectly
# (will not fail the test during regression run, but it will fail if you execute it later during the day)

  @smoke
  Scenario Outline: User verifies date picker is present with all the options-<id>
    Given ANZ user is logged in
    And ANZ user navigates directly to document with guid "I53cfffa798de11e598dc8b09b4f043e0"
    When the user clicks on 'History' link on the header
    Then the user should be seeing Date Picker dropdown
    When the user clicks on the Date Pikcer dropdown
    Then the user should see all the following options
      | Date Picker Options |
      | Today               |
      | Last 7 Days         |
      | Last 30 Days        |
      | All                 |
      | All Dates Before    |
      | All Dates After     |
      | Specific Date       |
      | Date Range          |
    And the user runs the search "contract" if today's history is not present
    And the user selects the "20" from per page dropdown
    When the user selects the date <Option> with <Date>
    Then the user should see the results <Option> with any selected <Date>
    Examples:
      | Option           | Date                     | id |
      | Today            | -                        | 1  |
      | Last 7 Days      | -                        | 2  |
      | Last 30 Days     | -                        | 3  |
      | All              | -                        | 4  |
      | All Dates Before | 05-05-2021               | 5  |
      | All Dates After  | 08-09-2016               | 6  |
      | Specific Date    | 05-05-2021               | 7  |
      | Date Range       | 07-09-2016 to 05-05-2021 | 8  |







