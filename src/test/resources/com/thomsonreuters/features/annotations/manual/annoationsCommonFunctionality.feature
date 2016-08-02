@manual
Feature: Annotations Common Functionality Manual tests

Scenario:User should be able to see the disabled save button when inline notes text exceeds 3000 chars length & User should be able to see the disabled save button when inline notes text is below 3000 chars length and tags length is > 10000
Given PL+ user is logged in with following details
| userName | librarian1 |
When user navigates directly to WLN document with guid "I3351a6a1e8da11e398db8b09b4f043e0"
And user enters inline notes text with "3001" chars length
Then user verifies Save button is disabled
And user should be able to see the warning message for exceeded text
When select ok button on warning message
And user removes the excess input from the inline notes text
Then user able to save the inline notes
When user enters new inline notes with richText with 3000 chars length and tags length is > 10000
Then user verifies Save button is disabled
And user should be able to see the warning message for exceeded richText
When select ok button on warning message
And user removes the excess input from the inline notes richText
Then user able to save the inline notes

@manual
Scenario:Cut/Copy/Paste is not available in TinyMCE editor
Given PL+ user is logged in with following details
| userName | librarian1 |
When user navigates directly to WLN document with guid "I3351a7b0e8da11e398db8b09b4f043e0"
And the user has accessed Inline Notes text box
Then Cut/Copy/Paste icons are not avaialble on TinyMCE toolbar
When the user has accessed New Annotations text box
Then Cut/Copy/Paste icons are not avaialble on TinyMCE toolbar

@manual
Scenario: Verify show/hide Annotations for inline notes
Given PL+ user is logged in with following details
| userName | librarian1 |
When user navigates directly to WLN document with guid "Ie6a01717518811e498db8b09b4f043e0"
And user added new inline notes
And the user clicks on Show And Hide link
Then verify that different Show and Hide icon displayed
And Hidden inline notes are displayed
When the user clicks on Show And Hide link
Then verify that different Show and Hide icon displayed
And Displayed inline notes are hidden
When user deletes All annotations
And the user verifies the annotations count under link
Then Annotations count should be displayed as zero

@manual
Scenario Outline: Share inline notes to reviewer with the rich text style text
  Given PL+ user is logged in with following details
    | userName | librarian1 |
  And user navigates directly to document with guid "I63cd7ba5e68b11e398db8b09b4f043e0"
  When user has created the inline notes with "<style>"
  Then inline notes saved with the "<style>"
  And user has shared the inline notes with another contact "librarian3"
  And user logs out
  Given PL+ user is logged in with following details
    | userName | librarian3 |
  When user navigates directly to document with guid "I63cd7ba5e68b11e398db8b09b4f043e0"
  Then the inline notes should be displayed in the "<style>" format
  Examples:
    | style         |
    | bold          |
    | italic        |
    | underline     |
    | strikethrough |

@manual
Scenario: Verify Undo/Redo options
  Given PL+ user is logged in with following details
    | userName | librarian1 |
  When user navigates directly to document with guid "I3351a7b0e8da11e398db8b09b4f043e0"
  And the user has accessed annotations text box
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

@manual
Scenario: Create Inline Notes on PL+ document And Verify PL+ Inline Notes are not accessible in WLN
  Given PL+ user is logged in with following details
    | userName   | librarian1 |
    | newSession | TRUE       |
  When user navigates directly to document with guid "I33f12c20e8cd11e398db8b09b4f043e0"
  And user added new Inline Notes
  Then verify saved Inline Notes text will be displayed with metadata
  Given WLN user is logged in with following details
    | userName         | librarian1      |
    | product          | WLN             |
    | routing          | WLN_ANNOTATIONS |
    | mandatoryRouting | YES             |
    | newSession       | TRUE            |
  And user navigates directly to WLN document with guid "I33f12c20e8cd11e398db8b09b4f043e0"
  Then user should not be able to see the Inline Notes created in PLC site

@manual
Scenario: Create Inline Notes on WLN document And Verify WLN Inline Notes are not accessible in PL+
  Given WLN user is logged in with following details
    | userName         | librarian1      |
    | product          | WLN             |
    | routing          | WLN_ANNOTATIONS |
    | mandatoryRouting | YES             |
    | newSession       | TRUE            |
  And user navigates directly to WLN document with guid "Ic9d547982b9f11e598dc8b09b4f043e0"
  And user added WLN new Inline Notes
  Then verify saved Inline Notes text will be displayed with metadata in WLN
  Given PL+ user is logged in with following details
    | userName   | librarian1 |
    | newSession | TRUE       |
  When user navigates directly to document with guid "Ic9d547982b9f11e598dc8b09b4f043e0"
  Then user should not be able to see the Inline Notes created in WLN site
