Feature:[825694] Add inline annotation for the document - Part 2

  Scenario Outline: Verifying that text can be displayed in bold/italic/underline/strikethrough for inline annotation-<id>
  Given ANZ user is logged in with following details
  | userName | auAnnotationUser2 |
  And user navigates directly to document with guid "Iaf266bf1870011e9adfea82903531a62" on PL AU website
  And user closes disclaimer in the bottom
  And user looks through the body of the document and select text with colour "blueBox"
  When clearing existing styles and annotation text
  And selecting "<style>" and writing text
  Then text displays with "<style>" character style
  Examples:
  | style         | id |
  | bold          | 1  |
  | italic        | 2  |
  | underlined    | 3  |
  | strikethrough | 4  |

  @deletionAnnotations
  Scenario Outline: Adding Annotaiton with character Styles: bold/italic/ALIGN_LEFT/ALIGN_CENTER/ALIGN_RIGHT/strikethrough/underlined/number/bullet applying to selected text for the inline annotation-<id>
  Given ANZ user is logged in with following details
  | userName | auAnnotationUser2 |
  And user navigates directly to document with guid "Iaf266bf1870011e9adfea82903531a62" on PL AU website
  And user closes disclaimer in the bottom
  And user looks through the body of the document and select text with colour "blueBox"
  And clearing existing styles and annotation text
  And highlighted text with the cursor
  And selecting "<style>"
  Then character style of highlighted text changes to "<style>"
  When saving the annotation
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
  | userName | auAnnotationUser2 |
  And user navigates directly to document with guid "Iaf266bf1870011e9adfea82903531a62" on PL AU website
  And user closes disclaimer in the bottom
  And user looks through the body of the document and select text with colour "blueBox"
  And selecting Inline format "<style>" and writing text
  Then text displays with "<style>" Inline style
  Examples:
  | style         | id |
  | Bold          | 1  |
  | Italic        | 2  |
  | Underline     | 3  |
  | Strikethrough | 4  |

  @deletionAnnotations
  Scenario Outline: Adding Annotaiton with Inline Styles: BOLD/ITALIC/STRIKETHROUGH/ITALIC/SUPERSCRIPT/SUBSCRIPT/CODE applying to selected text-<id>
  Given ANZ user is logged in with following details
  | userName | auAnnotationUser2 |
  And user navigates directly to document with guid "Iaf266bf1870011e9adfea82903531a62" on PL AU website
  And user closes disclaimer in the bottom
  And user looks through the body of the document and select text with colour "blueBox"
  And highlighted text with the cursor
  And selecting Inline format "<style>"
  Then highlighted text changes to Inline format "<style>"
  When saving the annotation
  Then the saved annotations text should be displayed in the Inline "<style>" format
  Examples:
  | style         | id |
  | Bold          | 1  |
  | Italic        | 2  |
  | Underline     | 3  |
  | Strikethrough | 4  |


  @deletionAnnotations
  Scenario Outline: Bullet and number styles for inline annotation-<id>
  Given ANZ user is logged in with following details
  | userName | auAnnotationUser2 |
  And user navigates directly to document with guid "Iaf266bf1870011e9adfea82903531a62" on PL AU website
  And user closes disclaimer in the bottom
  And user looks through the body of the document and select text with colour "blueBox"
  And selecting "<style>" from the toolbar and writing text in multiple lines
  Then multiple lines text displays same "<style>" format
  When saving the annotation
  Then the saved annotations multiple text should be displayed in the "<style>" format
  Examples:
  | style  | id |
  | number | 1  |
  | bullet | 2  |

  @deletionAnnotations
  Scenario Outline: Inline Styles: Adding Annotaiton with Alignment Styles: LEFT/CENTER/RIGHT applying to selected text-<id>
  Given ANZ user is logged in with following details
  | userName | auAnnotationUser2 |
  And user navigates directly to document with guid "Iaf266bf1870011e9adfea82903531a62" on PL AU website
  And user closes disclaimer in the bottom
  And user looks through the body of the document and select text with colour "blueBox"
  And highlighted text with the cursor
  And selecting Alignment format "<style>"
  Then highlighted text changes to Alignment format "<style>"
  When saving the annotation
  Then the saved annotations text should be displayed in the Alignment "<style>" format
  Examples:
  | style  | id |
  | LEFT   | 1  |
  | CENTER | 2  |
  | RIGHT  | 3  |
