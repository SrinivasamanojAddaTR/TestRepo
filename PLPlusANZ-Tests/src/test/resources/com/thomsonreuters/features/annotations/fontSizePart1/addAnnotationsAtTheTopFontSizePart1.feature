Feature:[825682] Add annotation at the top of the document

    ##########################Font size

   Scenario Outline: Verifying that text can be displayed in bold/italic/underline/strikethrough for annotation at the top-<id>
   Given ANZ user is logged in with following details
   | userName | auAnnotationUser4 |
   When user navigates directly to document with guid "Ic51def0b9d3c11e598dc8b09b4f043e0" on PL AU website
   And user closes disclaimer in the bottom
   And the user has accessed annotations text box
   And clearing existing styles and annotation text
   And selecting "<style>" and writing text
   Then text displays with "<style>" character style
   Examples:
   | style         | id |
   | bold          | 1  |
   | italic        | 2  |
   | underlined    | 3  |
   | strikethrough | 4  |

   @deletionAnnotations
   Scenario Outline: Adding Annotaiton with character Styles: bold/italic/ALIGN_LEFT/ALIGN_CENTER/ALIGN_RIGHT/strikethrough/underlined/number/bullet applying to selected text for the annotation at the top-<id>
   Given ANZ user is logged in with following details
   | userName | auAnnotationUser4 |
   When user navigates directly to document with guid "Ic51def0b9d3c11e598dc8b09b4f043e0" on PL AU website
   And user closes disclaimer in the bottom
   And the user has accessed annotations text box
   And clearing existing styles and annotation text
   And highlighted text with the cursor
   And selecting "<style>"
   Then character style of highlighted text changes to "<style>"
   And saving the annotation
   Then the saved annotations text should be displayed in the selected Link "<style>" format
   Examples:
   | style         | id |
   | bold          | 1  |
   | italic        | 2  |
   | ALIGN_LEFT    | 3  |
   | ALIGN_CENTER  | 4  |
   | ALIGN_RIGHT   | 5  |
   | strikethrough | 6  |
   | underlined    | 7  |
   | number        | 8  |
   | bullet        | 9  |


   Scenario Outline: Inline Styles: BOLD/ITALIC/STRIKETHROUGH/ITALIC/SUPERSCRIPT/SUBSCRIPT/CODE selected after entering text-<id>
   Given ANZ user is logged in with following details
   | userName | auAnnotationUser4 |
   When user navigates directly to document with guid "Ic51def0b9d3c11e598dc8b09b4f043e0" on PL AU website
   And user closes disclaimer in the bottom
   And the user has accessed annotations text box
   And selecting Inline format "<style>" and writing text
   Then text displays with "<style>" Inline style
   Examples:
   | style         | id |
   | Bold          | 1  |
   | Italic        | 2  |
   | Underline     | 3  |
   | Strikethrough | 4  |


  @deletionAnnotations
  Scenario: Convert Pasted URL strings into Links
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser4 |
    When user navigates directly to document with guid "Ic51def0b9d3c11e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And the user has accessed annotations text box
    And the user has inserted the url string "http://google.co.uk" into textbox
    And saving the annotation
    Then url string "http://google.co.uk" become as hyperlinked text
    And click on that link text "http://google.co.uk"
    Then hyperlinked url will be opened in new tab with title "Google"