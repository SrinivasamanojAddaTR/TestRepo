@manual
Feature: Inline Notes functionality

  Background: : Pre conditions for Inline notes
    Given PL+ user is logged in with following details
      | userName | librarian1 |
    And user navigates directly to document with guid "Ib5551f79e83211e398db8b09b4f043e0"

  @e2e
  Scenario: Verify Inline Notes feature
    When user selects the text "A practice note on the election that trustees of offshore trustees can make to limit the capital gains tax exposure of non-UK domiciled beneficiaries in respect of pre-6 April 2008 asset growth under paragraph 126 of Schedule 7 to the Finance Act 2008."
    And user selects an add note color link
    Then Inline Notes textbox will be displayed with tinymce editor
    When the user can insert the text and save it
    Then verify saved Inline Notes text will be displayed with metadata

  @e2e
  Scenario: Modify Inline Notes
    When user clicks the saved annotation
    Then Inline Notes text box will be displayed with existing text
    When User scrolls up to the start of document and scrolls down to end of document
    Then verify inline notes show allow to edit text
    When user modifies the text
    And saving the annotation
    Then modified Inline Notes text will be displayed with metadata

  @e2e
  Scenario: Delete Inline Notes
    Given PL+ user is logged in with following details
      | userName | librarian1 |
    When user navigates directly to document with guid "I33f1066be8cd11e398db8b09b4f043e0"
    And user added new inline Notes
    When user clicks the saved inline Notes
    Then Inline Notes text box will be displayed with delete icon
    When user deletes the Inline Notes
    Then user unable to find the deleted Inline Notes

  Scenario: Adding multiple inline notes
    When user selects the text "Taxation of Chargeable Gains Act 1992"
    And user selects an add note color link
    Then Inline Notes textbox will be displayed with tinymce editor
    When the user can insert the text and save it
    Then verify saved Inline Notes text will be displayed with metadata
    When user refresh the page
    Then verify the saved inline notes will be displayed in the same location where it was created
    When user create inline notes on reviewer notes text
    Then user should be able be able to see the both notes pointing to same line
    And user will not be having any overlapping issue
    When user create inline notes on existint inline note text
    Then user should be able be able to see the both notes pointing to same line
    And user will not be having any overlapping issue

  Scenario: Inline notes with selected color
    When user selects the text "Taxation of Chargeable Gains Act 1992"
    And user selects an add note with black color
    Then Inline Notes textbox will be displayed with tinymce editor
    When the user can insert the text and save it
    Then verify saved Inline Notes text will be displayed with metadata and in black color
    When user refresh the page
    Then verify the saved inline notes will be displayed in the same location where it was created
    And verify that inline notes color is black color

  Scenario Outline: Character/Header Styles: BOLD/ITALIC/UNDERLINE/STRIKETHROUGH/ALIGN RIGHT/LEFT/CENTER selected after entering text
    When clearing existing styles and Inline Notes text
    And selecting "<style>" and writing text
    Then text displays with "<style>" character style
  Examples:
    | style         |
    | bold          |
    | italic        |
    | underlined    |
    | number        |
    | bullet        |
    | strikethrough |
    | ALIGN_RIGHT   |
    | ALIGN_LEFT    |
    | ALIGN_CENTER  |
    | Heading 1     |
    | Heading 2     |
    | Heading 3     |
    | Heading 4     |
    | Heading 5     |
    | Heading 6     |

  Scenario Outline: Adding Inline Notes with Character/Header Styles: BOLD/ITALIC/UNDERLINE/STRIKETHROUGH/ALIGN RIGHT/LEFT/CENTER Applying to selected text
    When the user has accessed Inline Notes text box
    And clearing existing styles and Inline Notes text
    And highlighted text with the cursor
    And selecting "<style>"
    Then character style of highlighted text changes to "<style>"
    When saving the annotation
    Then the saved Inline Notes text should be displayed in the selected Link "<style>" format
  Examples:
    | style         |
    | bold          |
    | italic        |
    | underlined    |
    | strikethrough |
    | number        |
    | bullet        |
    | ALIGN_LEFT    |
    | ALIGN_CENTER  |
    | ALIGN_RIGHT   |
    | Heading 1     |
    | Heading 2     |
    | Heading 3     |
    | Heading 4     |
    | Heading 5     |
    | Heading 6     |

  Scenario: Verify Undo/Redo options
    Given PL+ user is logged in with following details
      | userName | librarian1 |
    When user navigates directly to document with guid "I3351a7b0e8da11e398db8b09b4f043e0"
    And the user has accessed Inline Notes text box
    Then verify the UNDO and REDO is disabled
    And enter the sample text
    Then verify the "UNDO" is enabled
    And verify the "REDO" is disabled
    When selecting "UNDO"
    Then entered text will be removed
    And verify the "REDO" is enabled
    And verify the "UNDO" is disabled
    When selecting "REDO"
    Then removed text will be displayed
    And verify the "REDO" is disabled
    And verify the "UNDO" is enabled

  Scenario Outline: Cut/Copy/Paste
    Given PL+ user is logged in with following details
      | userName | librarian1 |
    When user navigates directly to document with guid "I3351a7b0e8da11e398db8b09b4f043e0"
    And the user has accessed Inline Notes text box
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

  @e2e
  Scenario:User should be able to see the Inline Notes social icons
    Given PL+ user is logged in with following details
      | userName | librarian1 |
    When user navigates directly to document with guid "5-531-2145"
    Then user navigates to Inline Notes textbox with text
    And user has shared the Inline Notes with another contact "librarian3"
    And verify that user sharing icon is displayed before the createdby
    And user logs out
    Given PL+ user is logged in with following details
      | userName | librarian3 |
    When user navigates directly to document with guid "5-531-2145"
    Then shared Inline Notes should be displayed
    And verify that user sharing icon is displayed before the createdby