Feature: [848107] Ability to use register button on document view

  @gold
  Scenario: [848107] As a user i want to use register button on document view
    Given ANZ user is not logged in
    When ANZ user navigates directly to document with guid "Ic8badd78e0b511e598dc8b09b4f043e0"
    And "Register" button is present in document body
	When the user clicks on button with title "Register"
	Then the source document with guid "Ic8badd78e0b511e598dc8b09b4f043e0" remains open and new tab opens
    And the user is taken to "https://legal.thomsonreuters.com.au/products/practical-law/contact-us.aspx" resource
