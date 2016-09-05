Feature: [815698]As a PLAU User, I want to view a Topics page So that I can view the documents available for that Topic

 Scenario Outline: [831936] User verifies the topic page.
   Given ANZ user is logged in
   And user opens "Practice areas" link
   And the user navigates to practice area "<PA>" filtered by "<Topic Page>" topic page
   Then user verifies that there is no metadata and summary on the page
   And user verifies the "<Topic Page>" page
   And user verifies the "<Resources>" sections are displayed on topic page in alphabetical order
   And user verifies the "<Resources>" facets are displayed on the topic page
   Examples:
        |PA                     | Topic Page                          | Resources                                     |
        |Employment             | Ill and injured employees           | Practice notes, Standard documents,Checklists |
        |Company Law            | Company administration and meetings | Practice notes                                |
        |Corporate Transactions |Share acquisitions: private          | Practice notes, Checklists, Glossary          |

  Scenario Outline: User verifies topic page faceting
   Given ANZ user is logged in
    And user opens "Practice areas" link
    And the user navigates to practice area "<PA>" filtered by "<Topic Page>" topic page
	And the user is able to verify the absence of the facet group heading Jurisdiction
    When clicks on the facet group "<Resource Type>"
    Then the user verifies the topic facet "<Resource Type>" count is equivalent to no. of results displayed
 Examples:
     |PA                     | Topic Page                | Resource Type |
     |Employment             | Ill and injured employees | Checklists    |
     |Company Law            | Execution formalities     | Practice Notes|
     |Corporate Transactions |Share acquisitions: private| Checklists    |

 #Althogh this test works fine on PLCUK but there won't be enough documents on Australia's topic pages. Therefore putting @wip tag
 @wip
 Scenario: User verifies topic page pagination
  Given PL+ user is logged in
  And user opens "Practice areas" link
  And the user navigates to practice area "Employment" filtered by "Employment tribunals" topic page
  Then the user should see the page no "1"
  And the user varifies each page by navigates through each of the following pages
   |Pages|
   |  2  |
   |  3  |
   |  4  |
   |  5  |
   |  6  |
   |  7  |