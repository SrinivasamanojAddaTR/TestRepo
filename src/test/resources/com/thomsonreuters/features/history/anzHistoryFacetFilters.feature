Feature: [821979]As a PLAU user, I want to filter my history  So that I can quickly find my previous searches and the documents that I have viewed
 
  Scenario Outline: [821979]As a PLAU user, I want to filter my history  So that I can quickly find my previous searches and the documents that I have viewed
    Given ANZ user is logged in
    When the user clicks on 'History' link on the header
    And the user clicks on History left side facet "<HistoryFacet>"
    And  the user clicks 'select multiple filters' button
    And the user selects the date All with -
    When the user clicks on the clientID "<ClientID1>" checkbox
    When the user clicks on the clientID "<ClientID2>" checkbox
    And  the user clicks 'Apply filter' button
    Then the user should see the results from both "<ClientID1>" or "<ClientID2>" clientID
    When the user clicks on the facet "<Filter>" checkbox
    Then the user should see the results from both "<ClientID1>" or "<ClientID2>" and "<Filter>"
  Examples:
       |HistoryFacet | ClientID1 | ClientID2 |  Filter    |
       |All History  | TEST01    | TEST02    |  Search    |
       |Searches     | TEST01    | TEST02    |  -         |
       |Documents    | TEST01    | TEST02    |  Know-how  |




 