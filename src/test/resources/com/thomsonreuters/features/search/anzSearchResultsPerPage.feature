Feature: [813347] As a PLAU user, I want to increase or decrease the number of search results per page
  So that I can display the search results relevant to my work needs

 Background: Loging in and Searching in the phrase
      Given ANZ user is logged in
     When the user runs a free text search for the query "contract"

  Scenario: Option to select number of results per Page Control option
    When the user selects the "100" from per page dropdown
    Then the user should be seeing "100" per page
    And the user selects the "50" from per page dropdown
    Then the user should be seeing "50" per page
    When the user selects the "20" from per page dropdown
    Then the user should be seeing "20" per page


  Scenario: Page per selection after deleting the cookies
    When the user selects the "50" from per page dropdown
	And user relogs in
    When the user runs a free text search for the query "contract"
    Then the user should be seeing "50" per page
