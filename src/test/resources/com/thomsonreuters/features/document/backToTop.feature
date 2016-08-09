Feature: [818031] Back to top

  Scenario: [818031] As a PL AU login/password user I want to be able to get to the top of the document
   Given ANZ user is logged in with following details
	  | userName         | ANZtestuser4 |
    When he is viewing a free ANZ document "/Document/Iabd1cc71995911e598dc8b09b4f043e0/View/FullText.html"
    And user scroll down the resource by offset 2500
    Then back to top sticky link is displayed
    And user can navigate to top from anywhere in the document by clicking on the back to top link
    And the user scrolled to the top of the document
