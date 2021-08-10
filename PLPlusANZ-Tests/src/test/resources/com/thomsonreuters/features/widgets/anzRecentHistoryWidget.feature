Feature: [820326] As a PLAU User I want to view my recent history So that I can view and link to the documents I have recently accessed
@ffh
  Scenario: [820326] The user should see recent history widget
    Given ANZ user is logged in
    When API cleans all folders and history and user relogs in
    And ANZ user navigates directly to documents with guids and saves their details
      | I53cfffa798de11e598dc8b09b4f043e0 |
      | Ifb5c26ca995811e598dc8b09b4f043e0 |
      | I1c042c1a98eb11e598dc8b09b4f043e0 |
    And the user navigates to the main PLANZ page
    #Next step could fail due to 830073:[REGRESSION] Several documents don't display in History
   # Then user should see recent history widget which contains recently opened documents
    Then user should see recent history widget on "Practice notes" on resource page which contains recently opened documents
    Then user clicks View all link on recent history widget
    Then user should see history page
    #And the user navigates to the main PLANZ page
    #And the user clicks document link on recent history widget and document should open


	Scenario:  [850126] Open web user should not see recent history widget
	Given ANZ user is not logged in
	When the user navigates to the main PLANZ page
	Then the following widgets should not be displayed
      | Recent history                |