Feature:[825696][825697] Annotation note: Highlight text, copy with or without reference

  @deletionAnnotations
  Scenario: Checking that user can highlight text
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser3 |
    When user navigates directly to document with guid "I2436c8a5e0c011e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user looks through the body of the document and select text with colour "pinkBox" for highlight text
    Then verify that saved highlighted text is displayed with "pink" colour
    And user clicks on highlighted "pink" text
    And user clicks on delete highlighted text
    And user refreshes page
    Then verify that highlighted text with "pink" colour was deleted


  Scenario: Checking that user can copy without reference
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser3 |
    When user navigates directly to document with guid "I2436c8a5e0c011e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user looks through the body of the document and select text with Copy without reference option
    And the user has accessed annotations text box
    And user copies text from clipboard to annotation text box
    Then user checks that text was copied without reference and equals "This note examines age discrimination in employment under the Age Discrimination Act 2004 (Cth). It covers direct and indirect age discrimination, vicarious liability of an employer in an age discrimination claim, victimisation of an employee in an age discrimination claim, and the exemptions under the Age Discrimination Act 2004 (Cth) when discrimination will not be unlawful."


  Scenario: Checking that user can copy with reference
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser3 |
    When user navigates directly to document with guid "I2436c8a5e0c011e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user looks through the body of the document and select text with Copy with reference option
    And the user has accessed annotations text box
    And user copies text from clipboard to annotation text box
    Then user checks that text was copied with reference and equals "This note examines age discrimination in employment under the Age Discrimination Act 2004 (Cth). It covers direct and indirect age discrimination, vicarious liability of an employer in an age discrimination claim, victimisation of an employee in an age discrimination claim, and the exemptions under the Age Discrimination Act 2004 (Cth) when discrimination will not be unlawful.  Overview of federal age discrimination, Practical Law ANZ Practice Note w-001-4954"