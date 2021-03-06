Feature:[825694] Add inline annotation for the document - Part 1

  @gold @deletionAnnotations
  Scenario: Checking that user can save  inline annotation
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser2 |
    And user navigates directly to document with guid "I23c506d9c07111e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user looks through the body of the document and select text with colour "blueBox"
    And user added new inline annotation
    Then verify saved annotations text will be displayed with metadata


  @gold @deletionAnnotations
  Scenario: User should be able to see the disabled save button when inline annotation text exceeds 3000 chars length and to save annotation text equals 3000 chars length
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser2 |
    And user navigates directly to document with guid "I23c506d9c07111e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user looks through the body of the document and select text with colour "blueBox"
    And user enters inline annotation text with "3001" chars length
    Then user should be able to see the warning message for exceeded text
    When select ok button on warning message
    Then user check that save button is disabled
    And user removes the excess input from the annotations text
    Then user check that save button is enabled
    And saving the annotation


  Scenario: User should be able to see the disabled save button when inline annotation textbox is empty
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser2 |
    And user navigates directly to document with guid "I23c506d9c07111e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user looks through the body of the document and select text with colour "blueBox"
    And user enters inline annotation text with "empty" chars length
    Then user check that save button is disabled
    When user enters inline annotation text with "100" chars length
    Then user check that save button is enabled
    And clearing existing styles and annotation text
    Then user check that save button is disabled

  @deletionAnnotations
  Scenario Outline: Header Styles: HEADING/SUB_HEADING Styles and adding Annotaiton with Header Styles: HEADING/SUB_HEADING Applying to selected text-<id>
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser2 |
    And user navigates directly to document with guid "Iaf266bf1870011e9adfea82903531a62" on PL AU website
    And user closes disclaimer in the bottom
    And user looks through the body of the document and select text with colour "blueBox"
    And highlighted text with the cursor
    And selecting Headers format "<style>"
    Then highlighted text changes to Headers format "<style>"
    When saving the annotation
    Then the saved annotations text should be displayed in the Headers "<style>" format
    Examples:
      | style    | id |
      | Header 1 | 1  |
      | Header 2 | 2  |
      | Header 3 | 3  |
      | Header 4 | 4  |
      | Header 5 | 5  |
      | Header 6 | 6  |


  Scenario: Blocks Styles: PARAGRAPH style by default
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser2 |
    And user navigates directly to document with guid "Iaf266bf1870011e9adfea82903531a62" on PL AU website
    And user closes disclaimer in the bottom
    And user looks through the body of the document and select text with colour "blueBox"
    And enter the sample text
    Then text should be added with the "Paragraph" style by default


  @gold
  Scenario Outline: Cut/Copy/Paste-<id>
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser2 |
    And user navigates directly to document with guid "Iaf266bf1870011e9adfea82903531a62" on PL AU website
    And user closes disclaimer in the bottom
    And user looks through the body of the document and select text with colour "blueBox"
    And use the "<input>" to select options
    And highlighted text with the cursor
    And selecting "cut"
    Then textbox will not be having that text
    When selecting "paste"
    Then textbox will be having copied text
    When selecting "copy"
    And  empty the textbox
    And  selecting "paste"
    Then textbox will be having copied text
    Examples:
      | input    | id |
      | keyboard | 1  |

  @deletionAnnotations
  Scenario: Convert Pasted URL strings into Links
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser2 |
    And user navigates directly to document with guid "Iaf266bf1870011e9adfea82903531a62" on PL AU website
    And user closes disclaimer in the bottom
    And user looks through the body of the document and select text with colour "blueBox"
    And the user has inserted the url string "http://google.co.uk" into textbox
    And saving the annotation
    Then url string "http://google.co.uk" become as hyperlinked text
    When click on that link text "http://google.co.uk"
    Then hyperlinked url will be opened in new tab with title "Google"

    ###################################################


  @gold
  Scenario: Verifying cancel button
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser2 |
    And user navigates directly to document with guid "Iaf266bf1870011e9adfea82903531a62" on PL AU website
    And user closes disclaimer in the bottom
    And user looks through the body of the document and select text with colour "blueBox"
    And user added text for annotation
    Then annotations textbox will be displayed with tinymce editor
    And user clicks on cancel button
    Then annotations textbox will not be displayed with tinymce editor


  Scenario: Verifying that not logged in user can't see annotation link
    Given ANZ user is not logged in
    And user navigates directly to document with guid "Iaf266bf1870011e9adfea82903531a62" on PL AU website
    And user closes disclaimer in the bottom
    And user looks through the body of the document and select text
    Then choosing colour panel is not displayed



