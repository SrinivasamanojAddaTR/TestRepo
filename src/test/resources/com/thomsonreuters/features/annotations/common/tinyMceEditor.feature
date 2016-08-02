Feature: Integration of TinyMce Editor

  Scenario Outline: Character/Header Styles: BOLD/ITALIC/UNDERLINE/STRIKETHROUGH/ALIGN RIGHT/LEFT/CENTER selected after entering text
    Given PL+ user is logged in with following details
      | userName | librarian1 |
    And the user removes all annotations for the document with GUID "Ib5551f79e83211e398db8b09b4f043e0"
    When user navigates directly to document with guid "Ib5551f79e83211e398db8b09b4f043e0"
    Then the user has accessed annotations text box
    When clearing existing styles and annotation text
    And selecting "<style>" and writing text
    Then text displays with "<style>" character style
  Examples:
    | style         |
    | bold          |
    | italic        |
    | number        |
    | bullet        |
    | strikethrough |
    | underlined    |

  Scenario Outline: Adding Annotaiton with Character/Header Styles: BOLD/ITALIC/UNDERLINE/STRIKETHROUGH/ALIGN RIGHT/LEFT/CENTER Applying to selected text
    Given PL+ user is logged in with following details
      | userName | librarian1 |
    And the user removes all annotations for the document with GUID "Ib5551f79e83211e398db8b09b4f043e0"
    When user navigates directly to document with guid "Ib5551f79e83211e398db8b09b4f043e0"
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

  Scenario Outline: Header Styles: HEADING/SUB_HEADING Styles and adding Annotaiton with Header Styles: HEADING/SUB_HEADING Applying to selected text
    Given PL+ user is logged in with following details
      | userName | librarian1 |
    And the user removes all annotations for the document with GUID "Ib5551f79e83211e398db8b09b4f043e0"
    When user navigates directly to document with guid "Ib5551f6ce83211e398db8b09b4f043e0"
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

  Scenario Outline: Inline Styles: BOLD/ITALIC/STRIKETHROUGH/ITALIC/SUPERSCRIPT/SUBSCRIPT/CODE selected after entering text
    Given PL+ user is logged in with following details
      | userName | librarian1 |
    And the user removes all annotations for the document with GUID "I072d4b0ee84211e398db8b09b4f043e0"
    When user navigates directly to document with guid "I072d4b0ee84211e398db8b09b4f043e0"
    When the user has accessed annotations text box
    And selecting Inline format "<style>" and writing text
    Then text displays with "<style>" Inline style
  Examples:
    | style         |
    | Bold          |
    | Italic        |
    | Underline     |
    | Strikethrough |

  Scenario Outline: Adding Annotaiton with Inline Styles: BOLD/ITALIC/STRIKETHROUGH/ITALIC/SUPERSCRIPT/SUBSCRIPT/CODE Applying to selected text
    Given PL+ user is logged in with following details
      | userName | librarian1 |
    And the user removes all annotations for the document with GUID "I072d4b0ee84211e398db8b09b4f043e0"
    When user navigates directly to document with guid "I072d4b0ee84211e398db8b09b4f043e0"
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

  Scenario Outline: Inline Styles: Adding Annotaiton with Alignment Styles: LEFT/CENTER/RIGHT/JUSTIFY Applying to selected text
    Given PL+ user is logged in with following details
      | userName | librarian1 |
    And the user removes all annotations for the document with GUID "I072d4b0ee84211e398db8b09b4f043e0"
    When user navigates directly to document with guid "I072d4b0ee84211e398db8b09b4f043e0"
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

  Scenario: Blocks Styles: PARAGRAPH style by default
    Given PL+ user is logged in with following details
      | userName | librarian1 |
    And the user removes all annotations for the document with GUID "I3351a7b0e8da11e398db8b09b4f043e0"
    When user navigates directly to document with guid "I3351a7b0e8da11e398db8b09b4f043e0"
    Then the user has accessed annotations text box
    And enter the sample text
    Then text should be added with the "Paragraph" style by default

  Scenario Outline: AA Bullet/Number Styles: UNORDERED LIST/ORDERED LIST
    Given PL+ user is logged in with following details
      | userName | librarian1 |
    And the user removes all annotations for the document with GUID "I3351a7b0e8da11e398db8b09b4f043e0"
    When user navigates directly to document with guid "I3351a7b0e8da11e398db8b09b4f043e0"
    When the user has accessed annotations text box
    And selecting "<style>" from the toolbar and writing text in multiple lines
    Then multiple lines text displays same "<style>" format
    When saving the annotation
    Then the saved annotations multiple text should be displayed in the "<style>" format
  Examples:
    | style  |
    | number |
    | bullet |

  Scenario Outline: Cut/Copy/Paste
    Given PL+ user is logged in with following details
      | userName | librarian1 |
    And the user removes all annotations for the document with GUID "I3351a7b0e8da11e398db8b09b4f043e0"
    When user navigates directly to document with guid "I3351a7b0e8da11e398db8b09b4f043e0"
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

  Scenario: Convert Pasted URL strings into Links
    Given PL+ user is logged in with following details
      | userName | librarian3 |
    And the user removes all annotations for the document with GUID "Ic9d547982b9f11e598dc8b09b4f043e0"
    When user navigates directly to document with guid "Ic9d547982b9f11e598dc8b09b4f043e0"
    And the user has accessed annotations text box
    And the user has inserted the url string "http://google.co.uk" into textbox
    And saving the annotation
    Then url string "http://google.co.uk" become as hyperlinked text
    When click on that link text "http://google.co.uk"
    Then hyperlinked url will be opened in new tab with title "Google"
  @wip
  Scenario Outline: Sharing annotations to reviewer with rich format text
    Given PL+ user is logged in with following details
      | userName   | librarian1 |
      | newSession | TRUE       |
    And the user removes all annotations for the document with GUID "Ic9d547982b9f11e598dc8b09b4f043e0"
    When user navigates directly to document with guid "I63cd7ba5e68b11e398db8b09b4f043e0"
    Then user has created the annotations with "<style>"
    And user shared the annotations with another contact "librarian3"
    Given PL+ user is logged in with following details
      | userName | librarian3 |
    When user navigates directly to document with guid "I63cd7ba5e68b11e398db8b09b4f043e0"
    Then the annotations text should be displayed in the "<style>" format
  Examples:
    | style         |
    | bold          |
    | italic        |
    | strikethrough |

# The below features will come back into Annotations and these related stories (839785) are handed over to warranty.

#  Scenario: Link url to text using Insert/Edit Link
#    Given PL+ user is logged in with following details
#      | userName | librarian1 |
#    When user navigates directly to document with guid "Ic9d547982b9f11e598dc8b09b4f043e0"
#    And the user has accessed annotations text box
#    And highlighted text with the cursor
#    And selecting the Insert/Edit link on the toolbar
#    Then a pop up box will display to enter a URL
#    And text is already populated with the selected text
#    And Title and Target fields are should not be displayed
#    And Text,URL fields and buttons displayed
#    When the user adds the url "http://www.google.co.uk"
#    And saving the annotation
#    Then that url hyperlinked to the selected text.
#    When click on that link text
#    Then hyperlinked url will be opened in new tab with title "Google"
#
#  Scenario: link URL and cancel the change
#    When user navigates directly to document with guid "I3351a7b0e8da11e398db8b09b4f043e0"
#    And the user has accessed annotations text box
#    And highlighted text with the cursor
#    And selecting the Insert/Edit link on the toolbar
#    Then a pop up box will display to enter a URL
#    When the user cancels the url "http://www.google.co.uk"
#    Then verify No link is added to annotation

#  Scenario: UnLink url to hyperlinked text using Insert/Edit Link
#    Given PL+ user is logged in with following details
#      | userName | librarian1 |
#    When user navigates directly to document with guid "I3351a7b0e8da11e398db8b09b4f043e0"
#    And the user has accessed annotations text box
#    And highlighted text with the cursor
#    And selecting the Insert/Edit link on the toolbar
#    Then a pop up box will display to enter a URL
#    And text is already populated with the selected text
#    And the user adds the url "http://www.google.co.uk"
#    When highlighted text
#    And clicking on the "unlink" button from the toolbar
#    And saving the annotation
#    Then text doesn't render as a hyperlink
