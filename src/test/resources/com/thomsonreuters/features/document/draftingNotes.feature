Feature: [818022] As a: website user  I want: to be able to show or hide individual drafting notes for Standard Documents and clauses



  @gold
  Scenario Outline: Expand and Close Individual Drafting Notes for <documentType>
   Given ANZ user is logged in
    When ANZ user navigates directly to document with guid "<guid>"
    Then user is able to expand the first drafting notes
    And see the expanded drafting notes heading is "<heading>"
    And user is able to close the first drafting notes
    Examples:
      | documentType      | guid                              | heading             |
      | Standard Document | Ifb5c26ca995811e598dc8b09b4f043e0 | About this document |
      | Standard Clauses  | I1c042c1a98eb11e598dc8b09b4f043e0 | About this document |

 @gold
 Scenario: Verify the 'Show All', 'Hide All' and Show Notes only functionality of a standard document
   Given ANZ user is logged in
    When ANZ user navigates directly to document with guid "Ifb5c26ca995811e598dc8b09b4f043e0"
    And clicks on the Show/Hide Drafting Notes option on the delivery toolbar
    Then the following drafting notes options are displayed
      | Show All        |
      | Hide All        |
      | Show Notes Only |
    When user clicks on the 'Show All' option
    Then the document text along with the drafting notes expanded version is displayed
    When user clicks on the 'Hide All' option
    Then the document text along with drafting notes collapsed version is displayed
    When user clicks on the 'Show Notes Only' option
    Then only the drafting notes expanded version is displayed

  Scenario: Verify the standard document does not contain drafting notes
   Given ANZ user is logged in
    When ANZ user navigates directly to document with guid "I53cfffa798de11e598dc8b09b4f043e0"
    Then user should not see drafting notes
