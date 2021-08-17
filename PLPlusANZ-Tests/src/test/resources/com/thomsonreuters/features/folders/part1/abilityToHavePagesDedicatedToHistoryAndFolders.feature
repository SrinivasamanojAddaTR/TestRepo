@ffh
Feature: [808911] As a PLAU user I want to view folders, favourites and history So that I can folder my favourite documents and pages and view the history of my session

  @smoke @sanity
  Scenario: 
    Given ANZ user is logged in
    When the user clicks on 'History' link on the header
    Then 'History' page opens
    And All tabs present on History page
    When the user clicks on 'Folders' tab
    Then 'Folders' page opens
    When the user clicks on 'History' tab
    Then 'History' page opens
    When the user clicks on 'Folders' link on the header
    Then 'Folders' page opens
    When the user clicks on 'History' tab
    Then 'History' page opens
    And All tabs present on History page
    When the user clicks on 'Folders' tab
    Then 'Folders' page opens
