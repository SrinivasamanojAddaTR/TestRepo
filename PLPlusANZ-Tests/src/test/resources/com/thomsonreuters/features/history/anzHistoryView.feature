Feature: [821953] As a PLAU User, I want to view my history So that I can view my recent searches and the documents I have recently viewed

Background:
  Given ANZ user is logged in

  Scenario Outline: user verifies the search tab-<id>
    And the user runs a free text search for the query "<search term>"
    And the user waits search result to load
    When the user clicks on 'History' link on the header
    And the user selects the date All with -
    When the user clicks on 'Searches' tab on the History page
    Then the '1' link contains text "<search term>" and url '/Search/Results.html?query=<search term>'
    Then the '1' link contains ClientId and date
  Examples:
      |search term  | id |
      |tax          | 1  |
      |contract     | 2  |

  Scenario Outline: user verifies the Documents and All History tab-<id>
    And the user runs a free text search for the query "<search term>"
    And the user clicks on the first result and stores its title
    When user clicks on "History" link
    And the user selects the date All with -
    When the user clicks on 'Documents' tab on the History page
    Then user should see the same first title in the recent history's "1st" result
    And user should see the "1st" document's meta data and todays date
    When the user clicks on 'All_History' tab on the History page
    Then the '2' link contains text "<search term>" and url '/Search/Results.html?query=<search term>'
    Then the '1' link contains ClientId and date
  Examples:
        |search term | id |
        |tax         | 1  |
        |contract    | 2  |


  Scenario Outline:[821972] user verifies the document link in the document history-<id>
    And the user runs a free text search for the query "<search term>"
    And the user clicks on the first result and stores its title
    When user clicks on "History" link
    And the user selects the date All with -
    When the user clicks on 'Documents' tab on the History page
    Then user should see the same first title in the recent history's "1st" result
    When user clicks on the "1st" title link in the history tab
    Then user should be able to see the same document
    Examples:
      |search term | id |
      |tax         | 1  |
      |contract    | 2  |

  Scenario Outline: [821971]As a PLAU User I want to be able to link to a specific search I previously performed So that I can run the search again-<id>
    And the user runs a free text search for the query "<search term>"
    And the user stores the search title and count
    When the user clicks on 'History' link on the header
    And the user selects the date All with -
    When the user clicks on 'Searches' tab on the History page
    Then the '1' link contains text "<search term>" and url '/Search/Results.html?query=<search term>'
    When user clicks on the "1st" title link in the history tab
    Then user should be able to see the same search result with title and count
    Examples:
      | search term  | id |
      |tax           | 1  |
      |contract      | 2  |


  Scenario: [821985] As a PLAU User,I want to increase or decrease the number of documents and searches listed in my history page view
  So that I can view a list of documents and searches based on my needs
    When the user clicks on 'History' link on the header
    And the user selects the date All with -
    When the user selects the "20" from per page dropdown
    Then the user should be seeing "20" per page selected
    And the user selects the "50" from per page dropdown
    Then the user should be seeing "50" per page selected
    And the user selects the "100" from per page dropdown
    Then the user should be seeing "100" per page selected



