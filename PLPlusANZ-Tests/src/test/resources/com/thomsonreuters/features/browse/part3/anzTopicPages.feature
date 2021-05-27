Feature: [815698]As a PLAU User, I want to view a Topics page So that I can view the documents available for that Topic


  @gold
  Scenario Outline: [831936] User verifies the topic page.-<id>
    Given ANZ user is logged in
    And user opens "Practice Areas" link
    And the user navigates to practice area "<PA>" filtered by "<Topic Page>" topic page
    Then user verifies that there is no metadata and summary on the page
    And user verifies the "<Topic Page>" page
    And user verifies the "<Resources>" sections are displayed on topic page in alphabetical order
    And user expands the "Resource Type" facets group
    And user verifies the "<Resources>" facets are displayed on the topic page
    Examples:
      | PA                     | Topic Page                  | Resources                                    | id |
      | Employment             | Ill and injured employees   | Practice notes,Standard documents,Checklists | 1  |
      | Company Law            | Corporate governance        | Practice notes,Standard documents,Checklists | 2  |
      | Corporate Transactions | Due diligence: acquisitions | Practice notes,Standard documents,Checklists | 3  |


  @gold
  Scenario Outline: User verifies topic page faceting-<id>
    Given ANZ user is logged in
    And user opens "Practice Areas" link
    And the user navigates to practice area "<PA>" filtered by "<Topic Page>" topic page
    And user expands the "Resource Type" facets group
    And the user is able to verify the absence of the facet group heading Jurisdiction
    And the user selects single facet selection mode
    When clicks on the facet group "<Resource Type>"
    Then the user verifies the topic facet "<Resource Type>" count is equivalent to no. of results displayed
    Examples:
      | PA                     | Topic Page                  | Resource Type  | id |
      | Employment             | Ill and injured employees   | Checklists     | 1  |
      | Company Law            | Execution formalities       | Practice notes | 2  |
      | Corporate Transactions | Share acquisitions: private | Checklists     | 3  |

 #Althogh this test works fine on PLCUK but there won't be enough documents on Australia's topic pages. Therefore putting   tag

  Scenario: User verifies topic page pagination
    Given PL+ user is logged in
    And user opens "Practice Areas" link
    And the user navigates to practice area "Employment" filtered by "Employment contracts" topic page
    Then the user should see the page no "1"
    And the user varifies each page by navigates through each of the following pages
      | Pages |
      | 2     |