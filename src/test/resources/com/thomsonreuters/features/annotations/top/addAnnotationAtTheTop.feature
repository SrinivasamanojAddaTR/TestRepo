Feature:[825682] Add annotation at the top of the document

  @AnnotationsSmokeTests @e2e  @deletionAnnotations
  Scenario: Check document icon on the top of annotations box
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I747eef9fa1eb11e598dc8b09b4f043e0" on PL AU website
    And user added new annotation
    Then verify saved annotations text will be displayed with metadata
    Then user see annotation icon on the top of annotation box
    And user click on the annotation icon on the top of annotation box
    Then annotations textbox will be displayed with tinymce editor

  @deletionAnnotations
  Scenario: 803697 Verify ClientId is not displayed as part of Annotations metadata
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I747eef9fa1eb11e598dc8b09b4f043e0" on PL AU website
    And user added new annotation
    Then verify saved annotations text will be displayed with metadata
    And the Client ID next to the timestamp will not be displayed


  Scenario: User should be able to see notes added icon in history and folders
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I747eef9fa1eb11e598dc8b09b4f043e0" on PL AU website
    When adds current document to "root" folder
    When the user clicks on 'History' link on the header
    And the user clicks on 'Documents' tab on the History page
    Then the "1" document will be displayed along with nodes added link
    When the user clicks on 'Folders' link on the header
    Then the "1" document will be displayed along with nodes added link

  @deletionAnnotations
  Scenario: User should be able to see the disabled save button when annotation text exceeds 3000 chars length and to save annotation text equals 3000 chars length
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I747eef9fa1eb11e598dc8b09b4f043e0" on PL AU website
    And user enters annotation text with "3001" chars length
    Then user should be able to see the warning message for exceeded text
    When select ok button on warning message
    Then user check that save button is disabled
    And user removes the excess input from the annotations text
    Then user check that save button is enabled
    And saving the annotation


  Scenario: User should be able to see the disabled save button when annotation textbox is empty
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I747eef9fa1eb11e598dc8b09b4f043e0" on PL AU website
    And user enters annotation text with "empty" chars length
    Then user check that save button is disabled
    When user enters annotation text with "100" chars length
    Then user check that save button is enabled
    And clearing existing styles and annotation text
    Then user check that save button is disabled

  ##########################Font size

  Scenario Outline: Verifying that text can be displayed in bold/italic/underline/strikethrough for annotation at the top
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I0342dc0900b211e698dc8b09b4f043e0" on PL AU website
    Then the user has accessed annotations text box
    When clearing existing styles and annotation text
    And selecting "<style>" and writing text
    Then text displays with "<style>" character style
    Examples:
      | style         |
      | bold          |
      | italic        |
      | underlined    |
      | strikethrough |

  @deletionAnnotations
  Scenario Outline: Adding Annotaiton with character Styles: bold/italic/ALIGN_LEFT/ALIGN_CENTER/ALIGN_RIGHT/strikethrough/underlined/number/bullet applying to selected text for the annotation at the top
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I0342dc0900b211e698dc8b09b4f043e0" on PL AU website
    When the user has accessed annotations text box
    And clearing existing styles and annotation text
    And highlighted text with the cursor
    And selecting "<style>"
    Then character style of highlighted text changes to "<style>"
    When saving the annotation
    Then the saved annotations text should be displayed in the selected Link "<style>" format
    Examples:
      | style         |
      | bold          |
      | italic        |
      | ALIGN_LEFT    |
      | ALIGN_CENTER  |
      | ALIGN_RIGHT   |
      | strikethrough |
      | underlined    |
      | number        |
      | bullet        |


  Scenario Outline: Inline Styles: BOLD/ITALIC/STRIKETHROUGH/ITALIC/SUPERSCRIPT/SUBSCRIPT/CODE selected after entering text
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I0342dc0900b211e698dc8b09b4f043e0" on PL AU website
    When the user has accessed annotations text box
    And selecting Inline format "<style>" and writing text
    Then text displays with "<style>" Inline style
    Examples:
      | style         |
      | Bold          |
      | Italic        |
      | Underline     |
      | Strikethrough |

  @deletionAnnotations
  Scenario Outline: Adding Annotaiton with Inline Styles: BOLD/ITALIC/STRIKETHROUGH/ITALIC/SUPERSCRIPT/SUBSCRIPT/CODE applying to selected text
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I0342dc0900b211e698dc8b09b4f043e0" on PL AU website
    When the user has accessed annotations text box
    And highlighted text with the cursor
    And selecting Inline format "<style>"
    Then highlighted text changes to Inline format "<style>"
    When saving the annotation
    Then the saved annotations text should be displayed in the Inline "<style>" format
    Examples:
      | style         |
      | Bold          |
      | Italic        |
      | Underline     |
      | Strikethrough |


  @deletionAnnotations
  Scenario Outline: Bullet and number styles for annotation at the top
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I0342dc0900b211e698dc8b09b4f043e0" on PL AU website
    When the user has accessed annotations text box
    And selecting "<style>" from the toolbar and writing text in multiple lines
    Then multiple lines text displays same "<style>" format
    When saving the annotation
    Then the saved annotations multiple text should be displayed in the "<style>" format
    Examples:
      | style  |
      | number |
      | bullet |

  @deletionAnnotations
  Scenario Outline: Inline Styles: Adding Annotaiton with Alignment Styles: LEFT/CENTER/RIGHT applying to selected text
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I0342dc0900b211e698dc8b09b4f043e0" on PL AU website
    When the user has accessed annotations text box
    And highlighted text with the cursor
    And selecting Alignment format "<style>"
    Then highlighted text changes to Alignment format "<style>"
    When saving the annotation
    Then the saved annotations text should be displayed in the Alignment "<style>" format
    Examples:
      | style  |
      | LEFT   |
      | CENTER |
      | RIGHT  |

  @deletionAnnotations
  Scenario Outline: Header Styles: HEADING/SUB_HEADING Styles and adding Annotaiton with Header Styles: HEADING/SUB_HEADING Applying to selected text
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I0342dc0900b211e698dc8b09b4f043e0" on PL AU website
    Then the user has accessed annotations text box
    And highlighted text with the cursor
    And selecting Headers format "<style>"
    Then highlighted text changes to Headers format "<style>"
    When saving the annotation
    Then the saved annotations text should be displayed in the Headers "<style>" format
    Examples:
      | style    |
      | Header 1 |
      | Header 2 |
      | Header 3 |
      | Header 4 |
      | Header 5 |
      | Header 6 |


  Scenario: Blocks Styles: PARAGRAPH style by default
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I0342dc0900b211e698dc8b09b4f043e0" on PL AU website
    Then the user has accessed annotations text box
    And enter the sample text
    Then text should be added with the "Paragraph" style by default


  Scenario Outline: Cut/Copy/Paste
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I0342dc0900b211e698dc8b09b4f043e0" on PL AU website
    When the user has accessed annotations text box
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
      | input    |
      | keyboard |

  @deletionAnnotations
  Scenario: Convert Pasted URL strings into Links
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I0342dc0900b211e698dc8b09b4f043e0" on PL AU website
    And the user has accessed annotations text box
    And the user has inserted the url string "http://google.co.uk" into textbox
    And saving the annotation
    Then url string "http://google.co.uk" become as hyperlinked text
    When click on that link text "http://google.co.uk"
    Then hyperlinked url will be opened in new tab with title "Google"

    ###################################################


  Scenario: Verifying cancel button
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I0342dc0900b211e698dc8b09b4f043e0" on PL AU website
    And user added text for annotation
    Then annotations textbox will be displayed with tinymce editor
    And user clicks on cancel button
    Then annotations textbox will not be displayed with tinymce editor


  Scenario: Verifying that not logged in user can't see annotation link
    Given ANZ user is not logged in
    And user navigates directly to document with guid "I0342dc0900b211e698dc8b09b4f043e0" on PL AU website
    Then add annotation link is not displayed

  @deletionAnnotations
  Scenario: Check annotation count on the top of annotations box
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    And user navigates directly to document with guid "I747eef9fa1eb11e598dc8b09b4f043e0" on PL AU website
    And user added new annotation
    Then verify saved annotations text will be displayed with metadata
    Then check that annotation count at the top is displayed "1"



