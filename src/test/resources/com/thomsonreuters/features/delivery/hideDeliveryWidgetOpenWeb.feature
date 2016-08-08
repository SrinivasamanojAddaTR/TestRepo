Feature: [818358] [820465] Delivery Widget

Background:
	Given ANZ user is not logged in

  Scenario: [818358] As a Not logged in user I want to not be able to to use delivery options on document page
    When he is viewing a free ANZ document "/Document/I7ab265477b3d11e498db8b09b4f043e0/View/FullText.html"
    Then he does not see in the document page any link related to delivery options (email, download, print)
    And he is not able to use these features on document page

  Scenario: [820465] As a Not logged in user I want to not be able to to use delivery options on search page
    When the user navigates to the main PL ANZ page
    And he does a search "tax"
    Then he does not see in the search results page any link related to delivery options (email, download, print)
    And he is not able to use these features on search page