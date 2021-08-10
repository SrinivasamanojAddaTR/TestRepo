@ffh
Feature: [821934] As a PLAU User, I want to increase or decrease the number of documents listed in my foldered page view
  So that I can view a list of documents based on my needs

  Scenario: [821934] User verifies the Per Page dropdown behaviour on the Folders page
	  So that I can view a list of documents and searches based on my needs
    Given ANZ user is logged in
    When the user clicks on 'Folders' link on the header
    And the user selects the "100" from per page dropdown
    Then the user should be seeing "100" per page selected
    When the user selects the "50" from per page dropdown
    Then the user should be seeing "50" per page selected
    When the user selects the "20" from per page dropdown
    Then the user should be seeing "20" per page selected



