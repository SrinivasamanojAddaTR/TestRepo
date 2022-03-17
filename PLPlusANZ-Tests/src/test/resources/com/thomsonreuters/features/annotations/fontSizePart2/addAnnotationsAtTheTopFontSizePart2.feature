Feature:[825682] Add annotation at the top of the document


   @deletionAnnotations
   Scenario Outline: Adding Annotaiton with Inline Styles: BOLD/ITALIC/STRIKETHROUGH/ITALIC/SUPERSCRIPT/SUBSCRIPT/CODE applying to selected text-<id>
      Given ANZ user is logged in with following details
         | userName | auAnnotationUser6 |
      When user navigates directly to document with guid "Ic51def0b9d3c11e598dc8b09b4f043e0" on PL AU website
      And user closes disclaimer in the bottom
      And the user has accessed annotations text box
      And highlighted text with the cursor
      And selecting Inline format "<style>"
      Then highlighted text changes to Inline format "<style>"
      And saving the annotation
      Then the saved annotations text should be displayed in the Inline "<style>" format
      Examples:
         | style         | id |
         | Bold          | 1  |
         | Italic        | 2  |
         | Underline     | 3  |
         | Strikethrough | 4  |


   @deletionAnnotations
   Scenario Outline: Bullet and number styles for annotation at the top-<id>
      Given ANZ user is logged in with following details
         | userName | auAnnotationUser6 |
      When user navigates directly to document with guid "Ic51def0b9d3c11e598dc8b09b4f043e0" on PL AU website
      And user closes disclaimer in the bottom
      And the user has accessed annotations text box
      And selecting "<style>" from the toolbar and writing text in multiple lines
      Then multiple lines text displays same "<style>" format
      And saving the annotation
      Then the saved annotations multiple text should be displayed in the "<style>" format
      Examples:
         | style  | id |
         | number | 1  |
         | bullet | 2  |

   @deletionAnnotations
   Scenario Outline: Inline Styles: Adding Annotaiton with Alignment Styles: LEFT/CENTER/RIGHT applying to selected text-<id>
      Given ANZ user is logged in with following details
         | userName | auAnnotationUser6 |
      When user navigates directly to document with guid "Ic51def0b9d3c11e598dc8b09b4f043e0" on PL AU website
      And user closes disclaimer in the bottom
      And the user has accessed annotations text box
      And highlighted text with the cursor
      And selecting Alignment format "<style>"
      Then highlighted text changes to Alignment format "<style>"
      And saving the annotation
      Then the saved annotations text should be displayed in the Alignment "<style>" format
      Examples:
         | style  | id |
         | LEFT   | 1  |
         | CENTER | 2  |
         | RIGHT  | 3  |

   @deletionAnnotations
   Scenario Outline: Header Styles: HEADING/SUB_HEADING Styles and adding Annotaiton with Header Styles: HEADING/SUB_HEADING Applying to selected text-<id>
      Given ANZ user is logged in with following details
         | userName | auAnnotationUser6 |
      When user navigates directly to document with guid "Ic51def0b9d3c11e598dc8b09b4f043e0" on PL AU website
      And user closes disclaimer in the bottom
      Then the user has accessed annotations text box
      And highlighted text with the cursor
      And selecting Headers format "<style>"
      Then highlighted text changes to Headers format "<style>"
      And saving the annotation
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
         | userName | auAnnotationUser6 |
      When user navigates directly to document with guid "Ic51def0b9d3c11e598dc8b09b4f043e0" on PL AU website
      And user closes disclaimer in the bottom
      Then the user has accessed annotations text box
      And enter the sample text
      Then text should be added with the "Paragraph" style by default