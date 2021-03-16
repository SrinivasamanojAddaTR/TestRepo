Feature: [817996] As a PLAU user I want to view a table of contents So that I can see what sections are included in the document

  Background: 
   Given ANZ user is logged in

  Scenario Outline: [817996] The case documents contain Table of content
    When ANZ user navigates directly to document with guid "<guid>"
    Then the document opens correctly
    Then the user see Table of content
    When the user click on "Table of Contents" Table of content
    Then the Table of content will hide
    When the user click on "Table of Contents" Table of content
    Then the user see Table of content

    Examples: 
      | guid                              |
      | If13ba3b4d4d811e598dc8b09b4f043e0 |
      | I53cfffa798de11e598dc8b09b4f043e0 |

  @gold
  Scenario: [817996] The case documents contain Table of content
    When ANZ user navigates directly to document with guid "I53cfffa798de11e598dc8b09b4f043e0"
    Then the document opens correctly
    Then the user see Table of content
    When the user clicks on "Contract terms" jump link
    Then the user is taken to selected part of the document
